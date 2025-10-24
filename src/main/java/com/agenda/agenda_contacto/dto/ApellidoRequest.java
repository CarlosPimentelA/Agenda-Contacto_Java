package com.agenda.agenda_contacto.dto;

import org.springframework.lang.NonNull;

public class ApellidoRequest {
    @NonNull
    private String apellido;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
