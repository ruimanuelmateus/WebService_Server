package edu.ufp.esof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.ExplicadorService;
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
    private ExplicadorService explicadorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getExplicadorById() throws Exception
    {
        Explicador existingExplicador=new Explicador("Rui");
        existingExplicador.setId( 10L);
        when(this.explicadorService.findById(10L)).thenReturn(Optional.of(existingExplicador));

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

        String jsonRequest=this.objectMapper.writeValueAsString(explicador);

        when(explicadorService.createExplicador(explicador)).thenReturn(Optional.of(explicador));

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Explicador existingExplicador=new Explicador("Mateus");

        when(this.explicadorService.createExplicador(existingExplicador)).thenReturn(Optional.empty());

        String existingExplicadorJson=this.objectMapper.writeValueAsString(existingExplicador);

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(existingExplicadorJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    void getAllExplicadores() {
    }


    @Test
    void searchExplicadores() {
    }

    @Test
    void searchExplicadorName() throws Exception{
        Explicador existingExplicador=new Explicador("Rui");
        existingExplicador.setId(1L);
        when(this.explicadorService.searchByName("Rui")).thenReturn(Optional.of(existingExplicador));

        String responseJSON=this.mockMvc.perform(
                get("/explicador/{name}","Rui")
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Explicador resultExplicador=this.objectMapper.readValue(responseJSON, Explicador.class);

        System.out.println(responseJSON);

        assertEquals(existingExplicador, resultExplicador);

        this.mockMvc.perform(
                get("/explicador/11")
        ).andExpect(status().isNotFound());
    }


    @Test
    void addCursoToExplicador() {
    }

    @Test
    void addDisponibilidade() {
    }

    @Test
    void addExplicadorToFaculdade() {
    }
}