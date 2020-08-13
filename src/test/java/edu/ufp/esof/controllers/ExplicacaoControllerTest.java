package edu.ufp.esof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ufp.esof.models.Aluno;
import edu.ufp.esof.models.Explicacao;
import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.models.builders.ExplicacaoBuilder;
import edu.ufp.esof.services.ExplicacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExplicacaoController.class)
class ExplicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicacaoService explicacaoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createExplicacao() throws Exception {
        Explicador explicador=new Explicador("Rui");
        Aluno aluno=new Aluno("Vitor");

        ExplicacaoBuilder explicacaoBuilder=new ExplicacaoBuilder();
        Explicacao explicacao=explicacaoBuilder.setDia(DayOfWeek.MONDAY).setHoraIni(LocalTime.NOON)
                .setHoraFim(LocalTime.NOON.plusHours(2)).setExplicador(explicador).setAluno(aluno).build();

        String jsonRequest=this.objectMapper.writeValueAsString(explicacao);

        when(this.explicacaoService.createExplicacao(explicacao)).thenReturn(Optional.of(explicacao));

        this.mockMvc.perform(
                post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Explicacao existingExplicacao=explicacaoBuilder.setDia(DayOfWeek.MONDAY).setHoraIni(LocalTime.NOON)
                .setHoraFim(LocalTime.NOON.plusHours(2)).setExplicador(explicador).setAluno(new Aluno("Eduardo")).build();

        when(this.explicacaoService.createExplicacao(existingExplicacao)).thenReturn(Optional.empty());

        String existingExplicacaoJson=this.objectMapper.writeValueAsString(existingExplicacao);

        this.mockMvc.perform(
                post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(existingExplicacaoJson)
        ).andExpect(
                status().isBadRequest()
        );
    }
}