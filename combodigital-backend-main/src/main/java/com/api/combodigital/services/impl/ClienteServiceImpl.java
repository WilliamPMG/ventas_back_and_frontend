package com.api.combodigital.services.impl;

import com.api.combodigital.excepcion.ExcepcionUsuarioNoEncontrado;
import com.api.combodigital.entities.Cliente;
import com.api.combodigital.entities.Suscripcion;
import com.api.combodigital.repositories.IClienteRepository;
import com.api.combodigital.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    IClienteRepository iClienteRepository;

    @Override
    public List<Cliente> listaClientes() {
        return (List<Cliente>) iClienteRepository.findAll();
    }

    @Override
    public Cliente buscarCliente(Long id) {
        Optional<Cliente> clienteOptional = iClienteRepository.findById(id);
        if (!clienteOptional.isPresent()){
            throw new ExcepcionUsuarioNoEncontrado("El cliente no fue encontrado");
        }
        return clienteOptional.get();
    }

    @Override
    public Collection<Suscripcion> buscarSuscripcionCliente(Long id) {
        return  iClienteRepository.findById(id).get().getSuscripciones();
    }

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        return iClienteRepository.save(cliente);
    }

    @Override
    public Cliente editarCliente(Cliente cliente) {
        Cliente clienteEncontrado= buscarCliente(cliente.getId());
        cliente.setSuscripciones(clienteEncontrado.getSuscripciones());
        return iClienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        buscarCliente(id);
        iClienteRepository.deleteById(id);
    }
}
