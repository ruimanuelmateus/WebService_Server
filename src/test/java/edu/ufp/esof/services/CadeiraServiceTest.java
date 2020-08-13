package edu.ufp.esof.services;

import edu.ufp.esof.models.Cadeira;
import edu.ufp.esof.models.Curso;
import edu.ufp.esof.repositories.CadeiraRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CadeiraService.class)
class CadeiraServiceTest {

    @Autowired
    private CadeiraService cadeiraService;

    @MockBean
    private CadeiraRepo cadeiraRepo;

    @MockBean
    private CursoService cursoService;

    @Test
    void createAndAssociate() {

        Cadeira cadeira=new Cadeira("Eng");
        Curso curso=new Curso("INF");

        when(this.cursoService.findByName("INF")).thenReturn(Optional.of(curso));

        Optional<Cadeira> optionalCadeira=this.cadeiraService.createAndAssociate(cadeira, "INF");

        assertNotNull(optionalCadeira);
        assertTrue(optionalCadeira.isPresent());

        assertEquals(1, curso.getCadeiras().size());

        when(this.cursoService.findByName("MEC")).thenReturn(Optional.empty());

        Optional<Cadeira> existingCadeira=this.cadeiraService.createAndAssociate(cadeira, "MEC");

        assertNotNull(optionalCadeira);
        assertTrue(existingCadeira.isEmpty());
        assertNotEquals(2, curso.getCadeiras().size());

    }
}