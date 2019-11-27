package edu.ufp.esof.EngSoftPro.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.repositories.ExplicadorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExplicadorController.class)
class ExplicadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicadorRepo explicadorRepo;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllExplicadores() {
        }

    @Test
    void getExplicadorById() throws Exception
    {
        Explicador existingExplicador=new Explicador("Rui");
        existingExplicador.setId( 10L);
        when(this.explicadorRepo.findById(existingExplicador.getId())).thenReturn(Optional.of(existingExplicador));

        String responseJSON=this.mockMvc.perform(
                get("/explicador/10")
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Explicador resultExplicador=this.objectMapper.readValue(responseJSON, Explicador.class);

        System.out.println(responseJSON);

        assertEquals(existingExplicador, resultExplicador);

        this.mockMvc.perform(
                get("/explicador/11")
        ).andExpect(status().isNotFound());

    }

    @Test
    void createExplicador() throws Exception {
        Explicador explicador=new Explicador("Rui");

        String requestJson=this.objectMapper.writeValueAsString(explicador);

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(requestJson)
        ).andExpect(status().isOk());

        Explicador existingExplicador=new Explicador("Mateus");
        when(this.explicadorRepo.findByName("Mateus")).thenReturn(Optional.of(existingExplicador));

        String existingExplicadorJSON=this.objectMapper.writeValueAsString(existingExplicador);

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(existingExplicadorJSON)
        ).andExpect(status().isBadRequest());
    }
}