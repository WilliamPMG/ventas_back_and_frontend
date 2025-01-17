package com.api.combodigital.test.services.cliente;

import com.api.combodigital.entities.Cliente;
import com.api.combodigital.excepcion.ExcepcionUsuarioNoEncontrado;
import com.api.combodigital.repositories.IClienteRepository;
import com.api.combodigital.services.impl.ClienteServiceImpl;
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
public class ClienteServicioTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private IClienteRepository iClienteRepository;

    private Cliente cliente;

    @BeforeEach
    public void crear(){
        cliente = new ClienteTestDataBuilder().clientePorDefecto().crear();
    }

    @Test
    public void validarConsultarClienteExito() {
        //Arrange
        Mockito.when(iClienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        //Act
        Cliente clienteRecibido = clienteService.buscarCliente(1L);

        //Assert
        assertEquals(1L, clienteRecibido.getId());
        assertEquals("Fernando", clienteRecibido.getNombre());
        assertEquals("Castillo", clienteRecibido.getApellido());
        assertEquals("3152485896",clienteRecibido.getTelefono());

        Mockito.verify(iClienteRepository).findById(1L);
    }

    @Test
    public void validarConsultarClienteExcepcion() {
        //Arrange
        Mockito.when(iClienteRepository.findById(1L)).thenThrow(new ExcepcionUsuarioNoEncontrado("usuario no encontrado"));

        ExcepcionUsuarioNoEncontrado thrown = assertThrows(ExcepcionUsuarioNoEncontrado.class, () -> {
            clienteService.buscarCliente(1L);
        });

        //Assert
        assertTrue(thrown.getMessage().contains("usuario no encontrado"));
        Mockito.verify(iClienteRepository).findById(1L);
    }

    @Test
    public void validarGuardarClienteExito() {

        //Arrange
        Mockito.when(iClienteRepository.save(cliente)).thenReturn(cliente);

        //Act
        Cliente clienteRecibido = clienteService.agregarCliente(cliente);

        //Assert
        assertEquals(1L, clienteRecibido.getId());
        assertEquals("Fernando", clienteRecibido.getNombre());
        assertEquals("Castillo", clienteRecibido.getApellido());
        assertEquals("3152485896",clienteRecibido.getTelefono());

        Mockito.verify(iClienteRepository).save(cliente);
    }



}
