package com.api.combodigital.test.controllers.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Sql("/data-test.sql")
public class ClienteControladorTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private  ClienteTestDataBuilder clienteTestDataBuilder = new ClienteTestDataBuilder();;

    @Test
    public void consultarClienteExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/combodigital/v1/cliente/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Giorman")))
                .andExpect(jsonPath("$.apellido", is("Rodriguez")))
                .andExpect(jsonPath("$.telefono", is("3152485896")));
    }

    @Test
    public void consultarListaClienteExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/combodigital/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]id", is(1)))
                .andExpect(jsonPath("$.[0]nombre", is("Giorman")))
                .andExpect(jsonPath("$.[0]apellido", is("Rodriguez")))
                .andExpect(jsonPath("$.[1]id", is(2)))
                .andExpect(jsonPath("$.[1]nombre", is("Antonio")))
                .andExpect(jsonPath("$.[1]apellido", is("Ramirez")));

    }
    @Test
    public void guardarClienteExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/combodigital/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteTestDataBuilder.clientePorDefecto())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.nombre", is("Fernando")))
                .andExpect(jsonPath("$.apellido", is("Castillo")))
                .andExpect(jsonPath("$.telefono", is("3152485896")));
    }

    @Test
    public void editarClienteExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/combodigital/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteTestDataBuilder.clienteEditado(2L))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.nombre", is("Mauricio")))
                .andExpect(jsonPath("$.apellido", is("Ramirez")))
                .andExpect(jsonPath("$.telefono", is("1111111111")));
    }

    @Test
    public void editarClienteError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/combodigital/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteTestDataBuilder.clienteEditado(10L))))
                .andExpect(status().isNotFound())
                .andExpect((jsonPath("$.mensaje", is("El cliente no fue encontrado"))));
    }

    @Test
    public void eliminarClienteExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/combodigital/v1/cliente/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarClienteError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/combodigital/v1/cliente/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect((jsonPath("$.mensaje", is("El cliente no fue encontrado"))));
    }
}
