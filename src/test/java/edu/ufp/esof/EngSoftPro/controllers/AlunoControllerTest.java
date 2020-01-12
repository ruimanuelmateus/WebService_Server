package edu.ufp.esof.EngSoftPro.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ufp.esof.EngSoftPro.models.Aluno;
import edu.ufp.esof.EngSoftPro.services.AlunoService;
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

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllAlunos() {
    }

    @Test
    void getAlunoById() throws Exception {
        Aluno existingAluno=new Aluno("Rui");
        existingAluno.setId( 10L);
        when(this.alunoService.findById(existingAluno.getId())).thenReturn(Optional.of(existingAluno));

        String responseJSON=this.mockMvc.perform(
                get("/aluno/10")
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Aluno resultAluno=this.objectMapper.readValue(responseJSON, Aluno.class);

        System.out.println(responseJSON);

        assertEquals(existingAluno, resultAluno);

        this.mockMvc.perform(
                get("/aluno/11")
        ).andExpect(status().isNotFound());
    }


    @Test
    void createAluno() throws Exception {
        Aluno aluno=new Aluno("Rui");

        String requestJson=this.objectMapper.writeValueAsString(aluno);

        when(alunoService.createAluno(aluno)).thenReturn(Optional.of(aluno));

        this.mockMvc.perform(
                post("/aluno").contentType(MediaType.APPLICATION_JSON).content(requestJson)
        ).andExpect(status().isOk());

        Aluno existingAluno=new Aluno("Mateus");
        when(this.alunoService.findByName("Mateus")).thenReturn(Optional.of(existingAluno));

        String existingAlunoJSON=this.objectMapper.writeValueAsString(existingAluno);

        this.mockMvc.perform(
                post("/aluno").contentType(MediaType.APPLICATION_JSON).content(existingAlunoJSON)
        ).andExpect(status().isBadRequest());
    }
}