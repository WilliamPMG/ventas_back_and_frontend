package com.api.combodigital.services;

import com.api.combodigital.entities.Ganancia;

import java.util.Collection;

public interface IGananciaService {

    Collection<Ganancia> listaGanancia();
    Collection<Ganancia> editarValorGanancia(Double valor);
}
