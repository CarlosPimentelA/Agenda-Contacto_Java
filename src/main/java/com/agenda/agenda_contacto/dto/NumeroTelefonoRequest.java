package com.agenda.agenda_contacto.dto;

import org.springframework.lang.NonNull;

public class NumeroTelefonoRequest {
    @NonNull
    private String numeroTelefono;

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
