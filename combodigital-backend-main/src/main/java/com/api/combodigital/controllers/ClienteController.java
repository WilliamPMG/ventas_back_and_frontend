package com.api.combodigital.controllers;

import com.api.combodigital.entities.Cliente;
import com.api.combodigital.entities.Suscripcion;
import com.api.combodigital.services.IClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/combodigital/v1/")
@Api(tags="Controlador Cliente")
public class ClienteController {
    @Autowired
    IClienteService iClienteService;

    @GetMapping("cliente/{id}")
    @ApiOperation("buscar un cliente")
    private ResponseEntity<Cliente> buscar(@PathVariable Long id){
            return new ResponseEntity<Cliente>(iClienteService.buscarCliente(id),HttpStatus.OK);
    }

    @GetMapping("cliente")
    @ApiOperation("buscar lista de clientes")
    private ResponseEntity<List<Cliente>> lista(){
        return new ResponseEntity<>(iClienteService.listaClientes(),HttpStatus.OK);
    }

    @GetMapping("cliente/{id}/suscripcion")
    @ApiOperation("buscar suscripciones de un cliente")
    private ResponseEntity<Collection<Suscripcion>> consultaClienteSuscripcion(@PathVariable Long id){
        return new ResponseEntity<>(iClienteService.buscarSuscripcionCliente(id),HttpStatus.OK);
    }

    @PostMapping("cliente")
    @ApiOperation("guardar un cliente")
    private ResponseEntity<Cliente> agregar(@RequestBody Cliente cliente)
    {
        return new ResponseEntity<Cliente>(iClienteService.agregarCliente(cliente),HttpStatus.CREATED);
    }

    @PutMapping("cliente")
    @ApiOperation("editar un cliente")
    private ResponseEntity<Cliente> editar(@RequestBody Cliente cliente){
           return new ResponseEntity<Cliente>(iClienteService.editarCliente(cliente),HttpStatus.OK) ;
    }

    @DeleteMapping("cliente/{id}")
    @ApiOperation("eliminar un cliente")
    private ResponseEntity<Void> eliminar(@PathVariable Long id){
            iClienteService.eliminarCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    }
}
