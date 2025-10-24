package com.agenda.agenda_contacto.handlers;

public class ErrorResponse {

    private int statusCode;
    private String errorCode;
    private String message;

    public ErrorResponse(int statusCode, String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
