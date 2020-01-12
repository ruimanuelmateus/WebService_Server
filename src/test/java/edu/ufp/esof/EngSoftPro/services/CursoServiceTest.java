package edu.ufp.esof.EngSoftPro.services;

import edu.ufp.esof.EngSoftPro.models.Curso;
import edu.ufp.esof.EngSoftPro.models.Faculdade;
import edu.ufp.esof.EngSoftPro.repositories.CursoRepo;
import edu.ufp.esof.EngSoftPro.repositories.ExplicacaoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CursoService.class)
class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockBean
    private CursoRepo cursoRepo;

    @MockBean
    private FaculdadeService faculdadeService;

    @Test
    void findByName() {
    }

    @Test
    void createAndAssociate() {

        Curso curso=new Curso("Eng");
        Curso sameCurso=new Curso("Soft");

        Faculdade faculdade=new Faculdade("UFP");

        when(this.faculdadeService.findByName("UFP")).thenReturn(Optional.of(faculdade));

        Optional<Curso> optionalCurso=this.cursoService.createAndAssociate(curso, "UFP");

        assertNotNull(optionalCurso);
        assertTrue(optionalCurso.isPresent());

        assertEquals(1, faculdade.getCursos().size());

        when(this.faculdadeService.findByName("UFP")).thenReturn(Optional.of(faculdade));

        Optional<Curso> existingCurso=this.cursoService.createAndAssociate(sameCurso, "UFP");

        assertNotNull(optionalCurso);
        assertTrue(existingCurso.isPresent());
        assertEquals(2, faculdade.getCursos().size());
        assertNotEquals(3, faculdade.getCursos().size());

    }

    @Test
    void save() {
    }
}