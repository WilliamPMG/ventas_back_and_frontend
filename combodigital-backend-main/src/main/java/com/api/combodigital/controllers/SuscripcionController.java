package com.api.combodigital.controllers;

import com.api.combodigital.entities.Suscripcion;
import com.api.combodigital.services.ISuscripcionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/combodigital/v1/")
@Api(tags = "Controlador Suscripcion")
public class SuscripcionController {
    @Autowired
    private ISuscripcionService iSuscripcionService;

    @GetMapping("suscripcion")
    @ApiOperation("consultar lista suscripciones")
    private ResponseEntity<Collection<Suscripcion>> lista(){
        return new ResponseEntity<>( iSuscripcionService.listaSuscripcion(), HttpStatus.OK);
    }

    @GetMapping("suscripcion/{id}")
    @ApiOperation("consultar una suscripcion")
    private ResponseEntity<Suscripcion> consultar(@PathVariable Long id){
            return new ResponseEntity<>(iSuscripcionService.consultarSuscripcion(id),HttpStatus.OK);
    }

    @PostMapping("suscripcion")
    @ApiOperation("agregar una suscripcion")
    private ResponseEntity<Suscripcion> agregar(@RequestBody Suscripcion suscripcion){
        return new ResponseEntity<>(iSuscripcionService.agregarSuscripcion(suscripcion),HttpStatus.CREATED);

    }

    @PutMapping("suscripcion")
    @ApiOperation("editar una suscripcion")
    private ResponseEntity<Suscripcion> editar(@RequestBody Suscripcion suscripcion){
        return new ResponseEntity<>(iSuscripcionService.editarSuscripcion(suscripcion),HttpStatus.CREATED);
    }

    @DeleteMapping("suscripcion/{id}")
    @ApiOperation("eliminar una suscripcion")
    private ResponseEntity<Void> eliminar(@PathVariable Long id){
        iSuscripcionService.eliminarSuscripcion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("suscripcion/renovaciones")
    @ApiOperation("consular suscripciones para renovar")
    private ResponseEntity<Collection<Suscripcion>> renovaciones(){
            return new ResponseEntity<>(iSuscripcionService.listaSuscripcionRenovaciones(),HttpStatus.OK);
    }

    @GetMapping("suscripcion/vencidas")
    @ApiOperation("consular suscripciones vencidas")
    private ResponseEntity<Collection<Suscripcion>> vencidas(){
        return new ResponseEntity<>(iSuscripcionService.listaSuscripcionVencidas(),HttpStatus.OK);
    }



}
