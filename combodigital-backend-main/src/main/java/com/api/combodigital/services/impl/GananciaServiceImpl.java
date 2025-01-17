package com.api.combodigital.services.impl;

import com.api.combodigital.entities.Ganancia;
import com.api.combodigital.repositories.IGananciaRepository;
import com.api.combodigital.services.IGananciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

@Service
@Transactional
public class GananciaServiceImpl implements IGananciaService {

    @Autowired
    private IGananciaRepository iGananciaRepository;

    @Override
    public Collection<Ganancia> listaGanancia() {
        return iGananciaRepository.findAll();
    }

    @Override
    public Collection<Ganancia> editarValorGanancia(Double valor) {
        iGananciaRepository.findAll().forEach((ganancia -> {
            if (ganancia.getId()== LocalDate.now().getMonthValue()){
                ganancia.setValor(ganancia.getValor()+valor);
                iGananciaRepository.save(ganancia);
            }
        }));
        return iGananciaRepository.findAll();
    }

}
