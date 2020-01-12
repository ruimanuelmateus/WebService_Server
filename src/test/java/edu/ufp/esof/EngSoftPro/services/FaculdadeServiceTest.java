package edu.ufp.esof.EngSoftPro.services;

import edu.ufp.esof.EngSoftPro.models.Curso;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.models.Faculdade;
import edu.ufp.esof.EngSoftPro.repositories.ExplicadorRepo;
import edu.ufp.esof.EngSoftPro.repositories.FaculdadeRepo;
import edu.ufp.esof.EngSoftPro.services.filters.explicador.FilterExplicadorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FaculdadeService.class)
class FaculdadeServiceTest {

    @Autowired
    private FaculdadeService faculdadeService;

    @MockBean
    private FaculdadeRepo faculdadeRepo;


    @Test
    void createFaculdade() {

        Faculdade faculdade=new Faculdade("UFP");


        //when(this.faculdadeRepo.findByName("UFP")).thenReturn(Optional.empty());
        when(this.faculdadeRepo.save(faculdade)).thenReturn(faculdade);

        Optional<Faculdade> optionalFaculdade=this.faculdadeService.createFaculdade(faculdade);

        assertNotNull(optionalFaculdade);
        assertTrue(optionalFaculdade.isPresent());
        assertEquals(faculdade,optionalFaculdade.get());
    }

    @Test
    void save() {
    }

    @Test
    void findByName() {
    }
}