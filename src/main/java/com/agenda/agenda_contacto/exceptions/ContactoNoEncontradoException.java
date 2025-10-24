package com.agenda.agenda_contacto.exceptions;

public class ContactoNoEncontradoException extends RuntimeException {
    public ContactoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
