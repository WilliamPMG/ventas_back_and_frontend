package com.api.combodigital.test.services.cuenta;

import com.api.combodigital.entities.Cuenta;
import com.api.combodigital.excepcion.ExcepcionCuentaNoEncontrado;
import com.api.combodigital.repositories.ICuentaRepository;
import com.api.combodigital.services.impl.CuentaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class CuentaServicioTest {

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @Mock
    private ICuentaRepository iCuentaRepository;

    private Cuenta cuenta;

    @BeforeEach
    public void crear(){
        cuenta = new CuentaTestDataBuilder().cuentaPorDefecto().crear();
    }

    @Test
    public void validarConsultarCuentaExito() {
        //Arrange
        Mockito.when(iCuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));

        //Act
        Cuenta cuentaRecibido = cuentaService.consultarCuenta(1L);

        //Assert
        assertEquals(1L, cuentaRecibido.getId());
        assertEquals("Disney", cuentaRecibido.getNombre());
        assertEquals(7000, cuentaRecibido.getPrecio());
        assertEquals(30,cuentaRecibido.getDia());


        Mockito.verify(iCuentaRepository).findById(1L);
    }

    @Test
    public void validarConsultarCuentaExcepcion() {
        //Arrange
        Mockito.when(iCuentaRepository.findById(1L)).thenThrow(new ExcepcionCuentaNoEncontrado("cuenta no encontrado"));

        ExcepcionCuentaNoEncontrado thrown = assertThrows(ExcepcionCuentaNoEncontrado.class, () -> {
            cuentaService.consultarCuenta(1L);
        });

        //Assert
        assertTrue(thrown.getMessage().contains("cuenta no encontrado"));
        Mockito.verify(iCuentaRepository).findById(1L);
    }

    @Test
    public void validarGuardarCuentaExito() {

        //Arrange
        Mockito.when(iCuentaRepository.save(cuenta)).thenReturn(cuenta);

        //Act
        Cuenta cuentaRecibido = cuentaService.agregarCuenta(cuenta);

        //Assert
        assertEquals(1L, cuentaRecibido.getId());
        assertEquals("Disney", cuentaRecibido.getNombre());
        assertEquals(7000, cuentaRecibido.getPrecio());
        assertEquals(30,cuentaRecibido.getDia());

        Mockito.verify(iCuentaRepository).save(cuenta);
    }



}
