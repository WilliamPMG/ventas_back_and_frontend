package com.api.combodigital.test.controllers.ganancia;

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

    @Test
    public void consultarListaGanaciaExito() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/combodigital/v1/ganancia")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]id", is(1)))
                .andExpect(jsonPath("$.[0]mes", is("Enero")))
                .andExpect(jsonPath("$.[1]id", is(2)))
                .andExpect(jsonPath("$.[1]mes", is("Febrero")))
                .andExpect(jsonPath("$.[1]valor").exists())
                .andExpect(jsonPath("$.[11]id", is(12)))
                .andExpect(jsonPath("$.[11]mes", is("Diciembre")))
                .andExpect(jsonPath("$.[11]valor").exists());

    }
    @Test
    public void guardarCuentaExitoso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/combodigital/v1/ganancia/valor/10000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]id", is(1)))
                .andExpect(jsonPath("$.[0]mes", is("Enero")))
                .andExpect(jsonPath("$.[1]id", is(2)))
                .andExpect(jsonPath("$.[1]mes", is("Febrero")))
                .andExpect(jsonPath("$.[1]valor").exists())
                .andExpect(jsonPath("$.[11]id", is(12)))
                .andExpect(jsonPath("$.[11]mes", is("Diciembre")))
                .andExpect(jsonPath("$.[11]valor").exists());
    }

}
