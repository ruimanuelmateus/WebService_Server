package edu.ufp.esof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ufp.esof.models.Cadeira;
import edu.ufp.esof.services.CadeiraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CadeiraController.class)
class CadeiraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadeiraService cadeiraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCadeira() throws Exception {

        Cadeira cadeira=new Cadeira("Eng");

        String jsonRequest=this.objectMapper.writeValueAsString(cadeira);

        when(this.cadeiraService.createAndAssociate(cadeira, "INF")).thenReturn(Optional.of(cadeira));

        this.mockMvc.perform(
                post("/cadeira/INF").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Cadeira existingCadeira=new Cadeira("Soft");

        when(this.cadeiraService.createAndAssociate(existingCadeira, "INF")).thenReturn(Optional.empty());

        String existingCadeiraJson=this.objectMapper.writeValueAsString(existingCadeira);

        this.mockMvc.perform(
                post("/cadeira/INF").contentType(MediaType.APPLICATION_JSON).content(existingCadeiraJson)
        ).andExpect(
                status().isBadRequest()
        );
    }
}