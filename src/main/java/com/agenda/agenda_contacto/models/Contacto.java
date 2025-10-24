package com.agenda.agenda_contacto.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contactos")
public class Contacto {
    @Id
    private UUID id;
    private String nombre;
    private String apellido;
    @Indexed(unique = true)
    private String numeroTelefono;

    public Contacto() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "-------------------\n" +
                "Nombre: " + nombre + " " + apellido + "\n" +
                "Teléfono: " + numeroTelefono + "\n" +
                "ID: " + id + "\n" +
                "-------------------";
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean isValid() {
        if (nombre == null || nombre.length() < 3)
            return false;
        if (apellido == null || apellido.length() < 3)
            return false;
        if (numeroTelefono == null || !numeroTelefono.matches("\\d{10}"))
            return false;
        return true;
    }

    public void editContact(String nombre, String apellido, String numero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numero;
    }
}
