package edu.ufp.esof.EngSoftPro.services;

import edu.ufp.esof.EngSoftPro.models.Curso;
import edu.ufp.esof.EngSoftPro.models.Disponibilidade;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.repositories.CursoRepo;
import edu.ufp.esof.EngSoftPro.repositories.ExplicadorRepo;
import edu.ufp.esof.EngSoftPro.services.filters.explicador.FilterExplicadorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ExplicadorService.class)
class ExplicadorServiceTest {
/*
    @Autowired
    private ExplicadorService explicadorService;

 */
/*
    @MockBean
    private Explicador clientService;

 */

    @Autowired
    private ExplicadorService explicadorService;

    @MockBean
    private ExplicadorRepo explicadorRepo;
    @MockBean
    private FilterExplicadorService filterService;
    @MockBean
    private CursoService cursoService;
    @MockBean
    private FaculdadeService faculdadeService;


    //private CursoRepo cursoRepo;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void createExplicador() {
    }

    @Test
    void filterExplicadores() {
    }

    @Test
    void searchByName() {
    }

    @Test
    void modifyCurso() {
        Explicador explicador=new Explicador();
        explicador.setName("rui");
        Curso curso1=new Curso();
        curso1.setName("A");
        Curso curso2=new Curso();
        curso2.setName("B");
        when(this.cursoService.findByName("B")).thenReturn(Optional.of(curso2));
        when(this.explicadorService.findByName("rui")).thenReturn(Optional.of(explicador));
//        when(this.cursoService.save(curso2)).thenReturn(curso2);
 //       when(this.cursoService.save(curso1)).thenReturn(curso1);
  //      when(this.explicadorRepo.save(explicador)).thenReturn();

       // explicador.setCurso(curso1);
        Optional<Explicador> optionalExplicador=this.explicadorService.modifyCurso(explicador, "B");
        assertNotNull(optionalExplicador);
        assertTrue(optionalExplicador.isPresent());
        assertEquals("rui",optionalExplicador.get().getName());
        assertEquals(curso2,optionalExplicador.get().getCurso());
 //       System.out.println(optionalExplicador);
    }

    @Test
    void addDisponibilidade() {
        Explicador explicador=new Explicador();
        explicador.setName("Rui");
        //Set<Disponibilidade> disponibilidades=new HashSet<>();
        Disponibilidade disponibilidade=new Disponibilidade();
        disponibilidade.setHoraFim(LocalTime.NOON.plusHours(4));
        disponibilidade.setHoraIni(LocalTime.NOON);
        disponibilidade.setDia(DayOfWeek.MONDAY);
        explicador.addDisponibilidade(disponibilidade);

        Disponibilidade disponibilidadeToBeAdded=new Disponibilidade();
        disponibilidadeToBeAdded.setDia(DayOfWeek.SUNDAY);
        disponibilidadeToBeAdded.setHoraIni(LocalTime.NOON.plusHours(2));
        disponibilidadeToBeAdded.setHoraFim(LocalTime.NOON.plusHours(6));
        explicador.addDisponibilidade(disponibilidadeToBeAdded);

        when(this.explicadorRepo.findByName("Rui")).thenReturn(Optional.of(explicador));
        assertEquals(explicador.getDisponibilidades().size(),2);

        Optional<Explicador> optionalExplicador=this.explicadorService.addDisponibilidade(explicador);
        /*
        assertNotNull(optionalExplicador);
        assertTrue(optionalExplicador.isPresent());
        assertEquals("rui",optionalExplicador.get().getName());
        assertEquals(curso2,optionalExplicador.get().getCurso());

         */

    }

    @Test
    void createAndAssociate() {
    }
}