package com.api.combodigital.controllers;

import com.api.combodigital.entities.Cuenta;
import com.api.combodigital.entities.Suscripcion;
import com.api.combodigital.services.ICuentaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/combodigital/v1/")
@Api(tags = "Controlador Cuenta")
public class CuentaController {

    @Autowired
    ICuentaService iCuentaService;

    @GetMapping("cuenta")
    @ApiOperation("consultar lista de cuentas")
    ResponseEntity<Collection<Cuenta>> lista(){
        return new ResponseEntity<>(iCuentaService.listaCuenta(), HttpStatus.OK);
    }

    @GetMapping("cuenta/{id}")
    @ApiOperation("consultar una cuenta")
    ResponseEntity<Cuenta> consultar(@PathVariable Long id){
            return new ResponseEntity<>(iCuentaService.consultarCuenta(id),HttpStatus.OK);
    }

    @GetMapping("cuenta/{id}/suscripcion")
    @ApiOperation("consultar suscripciones de una cuenta")
    ResponseEntity<Collection<Suscripcion>> suscripcionCuenta(@PathVariable Long id)
    {
        return new ResponseEntity<>(iCuentaService.consultarCuenta(id).getSuscripcion(),HttpStatus.OK);
    }

    @PostMapping("cuenta")
    @ApiOperation("agregar una cuenta")
    ResponseEntity<Cuenta> agregar(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(iCuentaService.agregarCuenta(cuenta),HttpStatus.CREATED);
    }

    @PutMapping("cuenta")
    @ApiOperation("editar una cuenta")
    ResponseEntity<Cuenta> editar(@RequestBody Cuenta cuenta) {
            return new ResponseEntity<>(iCuentaService.editarCuenta(cuenta),HttpStatus.OK);
    }

    @DeleteMapping("cuenta/{id}")
    @ApiOperation("eliminar una cuenta")
    ResponseEntity<Void> eliminar(@PathVariable Long id){
            iCuentaService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
