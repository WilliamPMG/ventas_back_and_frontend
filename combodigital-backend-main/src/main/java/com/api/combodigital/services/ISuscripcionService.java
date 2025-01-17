package com.api.combodigital.services;

import com.api.combodigital.entities.Suscripcion;

import java.util.Collection;

public interface ISuscripcionService {

    Collection<Suscripcion> listaSuscripcion();
    Collection<Suscripcion> listaSuscripcionRenovaciones();
    Collection<Suscripcion> listaSuscripcionVencidas();
    Suscripcion consultarSuscripcion(Long id);
    Suscripcion agregarSuscripcion(Suscripcion suscripicion);
    Suscripcion editarSuscripcion(Suscripcion suscripcion);
    void eliminarSuscripcion(Long id);
}
