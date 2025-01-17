package com.api.combodigital.services.impl;

import com.api.combodigital.entities.Cuenta;
import com.api.combodigital.excepcion.ExcepcionCuentaNoEncontrado;
import com.api.combodigital.repositories.ICuentaRepository;
import com.api.combodigital.services.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class CuentaServiceImpl implements ICuentaService {
    @Autowired
    ICuentaRepository iCuentaRepository;

    @Override
    public Collection<Cuenta> listaCuenta() {
        return iCuentaRepository.findAll();
    }

    @Override
    public Cuenta consultarCuenta(Long id) {
        Optional<Cuenta> cuentaEncontrada = iCuentaRepository.findById(id);
        if (!cuentaEncontrada.isPresent()){
            throw new ExcepcionCuentaNoEncontrado("La cuenta no fue encontrada");
        }
        return cuentaEncontrada.get();
    }

    @Override
    public Cuenta agregarCuenta(Cuenta cuenta) {
        return iCuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta editarCuenta(Cuenta cuenta) {
         consultarCuenta(cuenta.getId());
        return iCuentaRepository.save(cuenta);
    }

    @Override
    public void eliminar(Long id) {
        consultarCuenta(id);
        iCuentaRepository.deleteById(id);
    }
}
