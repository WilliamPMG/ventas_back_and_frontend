package com.api.combodigital.services;

import com.api.combodigital.entities.Cuenta;

import java.util.Collection;

public interface ICuentaService {

    Collection<Cuenta> listaCuenta();
    Cuenta consultarCuenta(Long id);
    Cuenta agregarCuenta(Cuenta cuenta);
    Cuenta editarCuenta(Cuenta cuenta);
    void eliminar(Long id);
}
