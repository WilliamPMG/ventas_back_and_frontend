package com.api.combodigital.test.controllers.suscripcion;

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

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Sql("/data-test.sql")
public class SuscripcionControladorTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private SuscripcionTestDataBuilder suscripcionTestDataBuilder = new SuscripcionTestDataBuilder().suscripcionPorDefecto();

    @Test
    public void consultarSuscripcionExitoso() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/combodigital/v1/suscripcion/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.correo", is("prueba2@gmail.com")))
                .andExpect(jsonPath("$.fechaInicio", is("2022-09-15")))
                .andExpect(jsonPath("$.fechaFinal", is("2022-10-15")))
                .andExpect(jsonPath("$.password", is("22222222")))
                .andExpect(jsonPath("$.perfil", is("perfil2")))
                .andExpect(jsonPath("$.pin", is("8458")))
                .andExpect(jsonPath("$.precio", is(6000)))
                .andExpect(jsonPath("$.proveedor", is("Mi Entretenimiento")))
                .andExpect(jsonPath("$.cliente.id", is(1)))
                .andExpect(jsonPath("$.cuenta.id", is(1)));
    }


    @Test
    public void consultarListaSuscripcionesExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/combodigital/v1/suscripcion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]id", is(1)))
                .andExpect(jsonPath("$.[0]correo", is("prueba2@gmail.com")))
                .andExpect(jsonPath("$.[0]password", is("22222222")))
                .andExpect(jsonPath("$.[0]cliente.id", is(1)))
                .andExpect(jsonPath("$.[0]cuenta.id", is(1)))
                .andExpect(jsonPath("$.[1]id", is(2)))
                .andExpect(jsonPath("$.[1]correo", is("prueba3@gmail.com")))
                .andExpect(jsonPath("$.[1]password", is("22222222")))
                .andExpect(jsonPath("$.[1]cliente.id", is(2)))
                .andExpect(jsonPath("$.[1]cuenta.id", is(1)));

    }

    @Test
    public void guardarSuscripcionExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/combodigital/v1/suscripcion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcionTestDataBuilder.suscripcionPorDefecto())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.correo", is("giorman@gmail.com")))
                .andExpect(jsonPath("$.fechaInicio", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$.fechaFinal", is(LocalDate.now().plusDays(30).toString())))
                .andExpect(jsonPath("$.password", is("11111111")))
                .andExpect(jsonPath("$.perfil", is("perfil 1")))
                .andExpect(jsonPath("$.pin", is("5487")))
                .andExpect(jsonPath("$.precio", is(10000)))
                .andExpect(jsonPath("$.proveedor", is("My Pantalla")))
                .andExpect(jsonPath("$.cliente.id", is(1)))
                .andExpect(jsonPath("$.cuenta.id", is(1)));
    }

    @Test
    public void editarSuscripcionExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/combodigital/v1/suscripcion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcionTestDataBuilder.suscripcionEditar(2L))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.correo", is("cambio@gmail.com")))
                .andExpect(jsonPath("$.fechaInicio", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$.fechaFinal", is(LocalDate.now().plusDays(30).toString())))
                .andExpect(jsonPath("$.password", is("11111111")))
                .andExpect(jsonPath("$.perfil", is("cambio 1")))
                .andExpect(jsonPath("$.pin", is("5487")))
                .andExpect(jsonPath("$.precio", is(10000)))
                .andExpect(jsonPath("$.proveedor", is("My Pantalla")))
                .andExpect(jsonPath("$.cliente.id", is(1)))
                .andExpect(jsonPath("$.cuenta.id", is(1)));
    }

    @Test
    public void editarSuscripcionError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/combodigital/v1/suscripcion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcionTestDataBuilder.suscripcionEditar(10L))))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje", is("La suscripcion no fue encontrada")));
    }

    @Test
    public void eliminarClienteExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/combodigital/v1/suscripcion/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarClienteError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/combodigital/v1/suscripcion/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect((jsonPath("$.mensaje", is("La suscripcion no fue encontrada"))));
    }
}
