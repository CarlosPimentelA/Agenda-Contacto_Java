package com.agenda.agenda_contacto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agenda_contacto.dto.ApellidoRequest;
import com.agenda.agenda_contacto.dto.ContactoRequest;
import com.agenda.agenda_contacto.dto.NombreRequest;
import com.agenda.agenda_contacto.dto.NumeroTelefonoRequest;
import com.agenda.agenda_contacto.models.Contacto;
import com.agenda.agenda_contacto.service.ContactoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contactos")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService service) {
        this.contactoService = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Contacto>> getContacts() {
        return ResponseEntity.ok(contactoService.getAll());
    }

    @GetMapping("{numero}")
    public ResponseEntity<Contacto> getContact(@PathVariable("numero") String numero) {
        return ResponseEntity.ok(contactoService.findContactoByNumber(numero));
    }

    @PostMapping("/create")
    public ResponseEntity<Contacto> createContact(@RequestBody Contacto contacto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactoService.addContact(contacto));
    }

    @DeleteMapping("/delete/{numero}")
    public ResponseEntity<Contacto> deleteContact(@PathVariable("numero") String numero) {
        return ResponseEntity.ok(contactoService.deleteContact(numero));
    }

    @PatchMapping("/edit/{numero}/name")
    public ResponseEntity<Contacto> editName(@PathVariable("numero") String numero,
            @RequestBody @Validated NombreRequest nombreNuevo) {
        return ResponseEntity.ok(contactoService.editName(numero, nombreNuevo));
    }

    @PatchMapping("/edit/{numero}/lastname")
    public ResponseEntity<Contacto> editLastName(@PathVariable("numero") String numero,
            @RequestBody @Validated ApellidoRequest apellidoNuevo) {
        return ResponseEntity.ok(contactoService.editLastName(numero, apellidoNuevo));
    }

    @PatchMapping("/edit/{numero}/phone_number")
    public ResponseEntity<Contacto> editPhoneNumber(@PathVariable("numero") String numero,
            @RequestBody @Validated NumeroTelefonoRequest numeroNuevo) {
        return ResponseEntity.ok(contactoService.editNumber(numero, numeroNuevo));
    }

    @PatchMapping("/edit/{numero}/contact")
    public ResponseEntity<Contacto> editContacto(@PathVariable("numero") String numero,
            @RequestBody @Valid ContactoRequest contacto) {
        return ResponseEntity.ok(contactoService.editContact(numero, contacto));
    }
}
