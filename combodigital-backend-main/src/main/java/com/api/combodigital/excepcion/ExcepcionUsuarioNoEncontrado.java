package com.api.combodigital.excepcion;

public class ExcepcionUsuarioNoEncontrado extends RuntimeException{
    public ExcepcionUsuarioNoEncontrado(String message) {
        super(message);
    }
}
