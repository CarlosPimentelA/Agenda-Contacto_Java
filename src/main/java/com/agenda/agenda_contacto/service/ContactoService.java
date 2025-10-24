package com.agenda.agenda_contacto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agenda.agenda_contacto.dto.ApellidoRequest;
import com.agenda.agenda_contacto.dto.ContactoRequest;
import com.agenda.agenda_contacto.dto.NombreRequest;
import com.agenda.agenda_contacto.dto.NumeroTelefonoRequest;
import com.agenda.agenda_contacto.exceptions.ContactoDuplicadoException;
import com.agenda.agenda_contacto.exceptions.ContactoNoEncontradoException;
import com.agenda.agenda_contacto.exceptions.NumeroDuplicadoException;
import com.agenda.agenda_contacto.models.*;
import com.agenda.agenda_contacto.repository.ContactoRepository;
import com.mongodb.DuplicateKeyException;

@Service
public class ContactoService {
    private final ContactoRepository repository;

    public ContactoService(ContactoRepository repository) {
        this.repository = repository;
    }

    public List<Contacto> getAll() {
        return repository.findAll();
    }

    public Contacto addContact(Contacto contacto) {
        if (repository.findByNumeroTelefono(contacto.getNumeroTelefono()).isPresent()) {
            throw new ContactoDuplicadoException("Contacto duplicado!");
        } else if (contacto.getApellido() == null || contacto.getApellido().isBlank() && contacto.getNombre() == null
                || contacto.getNombre().isBlank() && contacto.getNumeroTelefono() == null
                || contacto.getNumeroTelefono().isBlank()) {
            throw new IllegalArgumentException("El contacto no puede estar vacio!");
        } else if (contacto.getNumeroTelefono() == null || contacto.getNumeroTelefono().isBlank()) {
            throw new IllegalArgumentException("El numero no puede estar vacio!");
        } else if (contacto.getApellido() == null || contacto.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio!");
        } else if (contacto.getNombre() == null || contacto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio!");
        }
        return repository.save(contacto);
    }

    public Contacto deleteContact(String numero) {
        Contacto contacto = findContactoOrThrowException(numero);
        repository.delete(contacto);
        return contacto;
    }

    public Contacto editContact(String numeroBuscar, ContactoRequest contacto) {
        Contacto contactoDb = findContactoOrThrowException(numeroBuscar);
        if (contactoDb.getNombre().equals(contacto.getNombre()) &&
                contactoDb.getApellido().equals(contacto.getApellido()) &&
                contactoDb.getNumeroTelefono().equals(contacto.getNumeroTelefono())) {
            throw new ContactoDuplicadoException("No hay nada que cambiar!");
        }
        if (!contactoDb.getNumeroTelefono().equals(contacto.getNumeroTelefono())) {
            if (repository.findByNumeroTelefono(contacto.getNumeroTelefono()).isPresent()) {
                throw new ContactoDuplicadoException("El numero ya esta registrado en el sistema!");
            }
            contactoDb.setNumeroTelefono(contacto.getNumeroTelefono());
        }
        contactoDb.setNombre(contacto.getNombre());
        contactoDb.setApellido(contacto.getApellido());
        repository.save(contactoDb);
        return contactoDb;
    }

    // Edit number

    public Contacto editNumber(String numeroActual, NumeroTelefonoRequest numeroNuevo) {
        Contacto contacto = findContactoOrThrowException(numeroActual);
        if (numeroNuevo == null || numeroNuevo.getNumeroTelefono().isBlank()) {
            throw new IllegalArgumentException("El numero no puede estar vacio!");
        }
        contacto.setNumeroTelefono(numeroNuevo.getNumeroTelefono());
        try {
            repository.save(contacto);
            return contacto;
        } catch (DuplicateKeyException e) {
            throw new NumeroDuplicadoException("El numero " + numeroNuevo + " ya esta registrado en el sistema!");
        }
    }

    // Edit name
    public Contacto editName(String numeroActual, NombreRequest nombreNuevo) {
        Contacto contacto = findContactoOrThrowException(numeroActual);
        if (nombreNuevo == null || nombreNuevo.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio!");
        }
        contacto.setNombre(nombreNuevo.getNombre());
        repository.save(contacto);
        return contacto;
    }

    // Edit lastname
    public Contacto editLastName(String numeroActual, ApellidoRequest apellidoNuevo) {
        Contacto contacto = findContactoOrThrowException(numeroActual);
        if (apellidoNuevo == null || apellidoNuevo.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio!");
        }
        contacto.setApellido(apellidoNuevo.getApellido());
        repository.save(contacto);
        return contacto;
    }

    private Contacto findContactoOrThrowException(String numeroTelefono) {
        return repository.findByNumeroTelefono(numeroTelefono).orElseThrow(
                () -> new ContactoNoEncontradoException(
                        "Contacto con el numero " + numeroTelefono + " es inexistente"));
    }

    // Find contact by number
    public Contacto findContactoByNumber(String numero) {
        Contacto contacto = findContactoOrThrowException(numero);
        return contacto;
    }
}
