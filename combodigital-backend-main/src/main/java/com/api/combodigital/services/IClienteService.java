package com.api.combodigital.services;

import com.api.combodigital.entities.Cliente;
import com.api.combodigital.entities.Suscripcion;

import java.util.Collection;
import java.util.List;

public interface IClienteService {
  List<Cliente> listaClientes();
  Cliente buscarCliente(Long id);
  Collection<Suscripcion> buscarSuscripcionCliente(Long id);
  Cliente agregarCliente(Cliente cliente);
  Cliente editarCliente(Cliente cliente);
  void eliminarCliente(Long id);

}
