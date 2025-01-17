package com.api.combodigital.test.controllers.cuenta;

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
public class CuentaControladorTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private CuentaTestDataBuilder cuentaDataTest = new CuentaTestDataBuilder();


    @Test
    public void consultarCuentaExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/combodigital/v1/cuenta/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Netflix")))
                .andExpect(jsonPath("$.precio", is(10000.0  )))
                .andExpect(jsonPath("$.dia", is(30)));
    }

    @Test
    public void consultarListaCuentaExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/combodigital/v1/cuenta")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]id", is(1)))
                .andExpect(jsonPath("$.[0]nombre", is("Netflix")))
                .andExpect(jsonPath("$.[0]precio", is(10000.0)))
                .andExpect(jsonPath("$.[0]dia", is(30)))
                .andExpect(jsonPath("$.[1]id", is(2)))
                .andExpect(jsonPath("$.[1]nombre", is("Amazon")))
                .andExpect(jsonPath("$.[1]precio", is(5000.0)))
                .andExpect(jsonPath("$.[1]dia", is(60)));

    }
    @Test
    public void guardarCuentaExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/combodigital/v1/cuenta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuentaDataTest.cuentaPorDefecto())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Disney")))
                .andExpect(jsonPath("$.precio", is(7000.0)))
                .andExpect(jsonPath("$.dia", is(30)));
    }

    @Test
    public void editarCuentaExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/combodigital/v1/cuenta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuentaDataTest.cuentaEditado(2L))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.nombre", is("Star")))
                .andExpect(jsonPath("$.precio", is(8000.0)))
                .andExpect(jsonPath("$.dia", is(30)));
    }

    @Test
    public void editarCuentaError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/combodigital/v1/cuenta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuentaDataTest.cuentaEditado(10L))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void eliminarCuentaExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/combodigital/v1/cuenta/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarCuentaError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/combodigital/v1/cuenta/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect((jsonPath("$.mensaje", is("La cuenta no fue encontrada"))));
    }
}
