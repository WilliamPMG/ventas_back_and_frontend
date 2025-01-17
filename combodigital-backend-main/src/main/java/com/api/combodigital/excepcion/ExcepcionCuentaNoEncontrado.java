package com.api.combodigital.excepcion;

public class ExcepcionCuentaNoEncontrado extends RuntimeException{
    public ExcepcionCuentaNoEncontrado(String message) {
        super(message);
    }
}
