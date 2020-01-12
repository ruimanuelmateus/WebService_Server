package edu.ufp.esof.EngSoftPro.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ufp.esof.EngSoftPro.models.Curso;
import edu.ufp.esof.EngSoftPro.models.Faculdade;
import edu.ufp.esof.EngSoftPro.services.CursoService;
import edu.ufp.esof.EngSoftPro.services.FaculdadeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCurso() throws Exception {
        Curso curso=new Curso();

        String jsonRequest=this.objectMapper.writeValueAsString(curso);

        when(this.cursoService.createAndAssociate(curso, "UFP")).thenReturn(Optional.of(curso));

        this.mockMvc.perform(
                post("/curso/UFP").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Curso existingCurso=new Curso();

        when(this.cursoService.createAndAssociate(existingCurso, "UFP")).thenReturn(Optional.empty());

        String existingFaculdadeJson=this.objectMapper.writeValueAsString(existingCurso);

        this.mockMvc.perform(
                post("/curso/UFP").contentType(MediaType.APPLICATION_JSON).content(existingFaculdadeJson)
        ).andExpect(
                status().isBadRequest()
        );
    }
}