package com.api.combodigital.config;

import com.api.combodigital.entities.Mensaje;
import com.api.combodigital.excepcion.ExcepcionCuentaNoEncontrado;
import com.api.combodigital.excepcion.ExcepcionSuscripcionNoEncontrado;
import com.api.combodigital.excepcion.ExcepcionUsuarioNoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice(annotations = RestController.class)
public class ConfigException {


    @ExceptionHandler({ExcepcionCuentaNoEncontrado.class, ExcepcionUsuarioNoEncontrado.class, ExcepcionSuscripcionNoEncontrado.class})
    public ResponseEntity<?> notFoundException(Exception e){
        return new ResponseEntity<>(new Mensaje(e.getMessage()),HttpStatus.NOT_FOUND);
    }
}
