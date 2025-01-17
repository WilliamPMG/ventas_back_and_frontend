package com.api.combodigital.test.services.suscripcion;

import com.api.combodigital.entities.Suscripcion;
import com.api.combodigital.excepcion.ExcepcionSuscripcionNoEncontrado;
import com.api.combodigital.excepcion.ExcepcionUsuarioNoEncontrado;
import com.api.combodigital.repositories.ISuscripcionRepository;
import com.api.combodigital.services.impl.SuscripcionServiceImpl;
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
public class SuscripcionServicioTest {

    @InjectMocks
    private SuscripcionServiceImpl suscripcionService;

    @Mock
    private ISuscripcionRepository iSuscripcionRepository;

    private Suscripcion suscripcion;

    @BeforeEach
    public void crear(){
        suscripcion = new SuscripcionTestDataBuilder().suscripcionPorDefecto().crear();
    }

    @Test
    public void validarConsultarSuscripcionExito() {
        //Arrange
        Mockito.when(iSuscripcionRepository.findById(1L)).thenReturn(Optional.of(suscripcion));

        //Act
        Suscripcion suscripcionRecibido = suscripcionService.consultarSuscripcion(1L);

        //Assert
        assertEquals(1L, suscripcionRecibido.getId());
        assertEquals("giorman@gmail.com", suscripcionRecibido.getCorreo());
        assertEquals("11111111", suscripcionRecibido.getPassword());
        assertEquals("perfil 1", suscripcionRecibido.getPerfil());
        assertEquals("5487", suscripcionRecibido.getPin());
        assertEquals(10000, suscripcionRecibido.getPrecio());
        assertEquals("My Pantalla", suscripcionRecibido.getProveedor());

        Mockito.verify(iSuscripcionRepository).findById(1L);
    }

    @Test
    public void validarConsultarSuscripcionExcepcion() {
        //Arrange
        Mockito.when(iSuscripcionRepository.findById(1L)).thenThrow(new ExcepcionSuscripcionNoEncontrado("suscripcion no encontrada"));

        ExcepcionSuscripcionNoEncontrado thrown = assertThrows(ExcepcionSuscripcionNoEncontrado.class, () -> {
            suscripcionService.consultarSuscripcion(1L);
        });

        //Assert
        assertTrue(thrown.getMessage().contains("suscripcion no encontrada"));
        Mockito.verify(iSuscripcionRepository).findById(1L);
    }

    @Test
    public void validarGuardarClienteExito() {

        //Arrange
        Mockito.when(iSuscripcionRepository.save(suscripcion)).thenReturn(suscripcion);

        //Act
        Suscripcion suscripcionRecibido = suscripcionService.agregarSuscripcion(this.suscripcion);

        //Assert
        assertEquals(1L, suscripcionRecibido.getId());
        assertEquals("giorman@gmail.com", suscripcionRecibido.getCorreo());
        assertEquals("11111111", suscripcionRecibido.getPassword());
        assertEquals("perfil 1", suscripcionRecibido.getPerfil());
        assertEquals("5487", suscripcionRecibido.getPin());
        assertEquals(10000, suscripcionRecibido.getPrecio());
        assertEquals("My Pantalla", suscripcionRecibido.getProveedor());

        Mockito.verify(iSuscripcionRepository).save(this.suscripcion);
    }



}
