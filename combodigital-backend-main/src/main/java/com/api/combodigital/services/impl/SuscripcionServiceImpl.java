package com.api.combodigital.services.impl;

import com.api.combodigital.entities.Suscripcion;
import com.api.combodigital.excepcion.ExcepcionSuscripcionNoEncontrado;
import com.api.combodigital.repositories.ISuscripcionRepository;
import com.api.combodigital.services.ISuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuscripcionServiceImpl implements ISuscripcionService {
    @Autowired
    private ISuscripcionRepository iSuscripcionRepository;

    @Override
    public Collection<Suscripcion> listaSuscripcion() {
        return iSuscripcionRepository.findAll();
    }

    @Override
    public Collection<Suscripcion> listaSuscripcionRenovaciones() {
        return renovacion();
    }

    @Override
    public Collection<Suscripcion> listaSuscripcionVencidas() {
        Collection<Suscripcion> vencidas = iSuscripcionRepository.findAll().stream()
                .filter(resultado->resultado.getEstado()==false).collect(Collectors.toList());
        return vencidas;
    }

    @Override
    public Suscripcion consultarSuscripcion(Long id) {
        Optional<Suscripcion> suscripcionEncontrada = iSuscripcionRepository.findById(id);
        if (!suscripcionEncontrada.isPresent()){
           throw new ExcepcionSuscripcionNoEncontrado("La suscripcion no fue encontrada");
        }
        return suscripcionEncontrada.get();
    }

    @Override
    public Suscripcion agregarSuscripcion(Suscripcion suscripicion) {
        return iSuscripcionRepository.save(suscripicion);
    }

    @Override
    public Suscripcion editarSuscripcion(Suscripcion suscripcion) {
        consultarSuscripcion(suscripcion.getId());
        return iSuscripcionRepository.save(suscripcion);
    }

    @Override
    public void eliminarSuscripcion(Long id) {
        consultarSuscripcion(id);
        iSuscripcionRepository.deleteById(id);
    }

    //selecciona todas las suscripciones que se deben renovar 6 dia antes e inactiva las que ya vencieron
    private Collection<Suscripcion> renovacion(){
        Collection<Suscripcion> renovaciones = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            Period period = Period.ofDays(-i);
            renovaciones = iSuscripcionRepository.findAll().stream()
                    .filter((suscripcion) -> (LocalDate.now().isAfter(suscripcion.getFechaFinal().plus(period))
                            || LocalDate.now().equals(suscripcion.getFechaFinal())) && suscripcion.getEstado())
                    .collect(Collectors.toList());
        }

        //pone la suscripciones que ya se vencieron en inactivas
        renovaciones.forEach((suscripcion) -> {
            if (suscripcion.getFechaFinal().isBefore(LocalDate.now())) {
                suscripcion.setEstado(false);
                editarSuscripcion(suscripcion);
            }
        });
        return  renovaciones;
    }
}
