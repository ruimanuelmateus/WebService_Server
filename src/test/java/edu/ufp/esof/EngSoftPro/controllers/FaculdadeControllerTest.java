package edu.ufp.esof.EngSoftPro.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ufp.esof.EngSoftPro.models.Aluno;
import edu.ufp.esof.EngSoftPro.models.Explicacao;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.models.Faculdade;
import edu.ufp.esof.EngSoftPro.models.builders.ExplicacaoBuilder;
import edu.ufp.esof.EngSoftPro.services.ExplicacaoService;
import edu.ufp.esof.EngSoftPro.services.FaculdadeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FaculdadeController.class)
class FaculdadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaculdadeService faculdadeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFaculdade() throws Exception {
        Faculdade faculdade=new Faculdade("UFP");

        String jsonRequest=this.objectMapper.writeValueAsString(faculdade);

        when(this.faculdadeService.createFaculdade(faculdade)).thenReturn(Optional.of(faculdade));

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Faculdade existingFaculdade=new Faculdade("UP");

        when(this.faculdadeService.createFaculdade(existingFaculdade)).thenReturn(Optional.empty());

        String existingFaculdadeJson=this.objectMapper.writeValueAsString(existingFaculdade);

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON).content(existingFaculdadeJson)
        ).andExpect(
                status().isBadRequest()
        );
    }
}