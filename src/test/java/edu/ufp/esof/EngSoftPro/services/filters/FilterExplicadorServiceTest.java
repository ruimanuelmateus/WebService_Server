package edu.ufp.esof.EngSoftPro.services.filters;

import edu.ufp.esof.EngSoftPro.models.Cadeira;
import edu.ufp.esof.EngSoftPro.models.Curso;
import edu.ufp.esof.EngSoftPro.models.Disponibilidade;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.services.filters.explicador.FilterExplicadorService;
import edu.ufp.esof.EngSoftPro.services.filters.explicador.FilterExplicadorObject;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilterExplicadorServiceTest {

    @Test
    void filter() {

        String cursoName="Inf";
        DayOfWeek dia=DayOfWeek.MONDAY;

        Set<Explicador> explicadores=new HashSet<>();

        Explicador explicador1=new Explicador("Rui");
        Explicador explicador2=new Explicador("Edu");
        Explicador explicador3=new Explicador("Vitor");

        Curso curso1=new Curso();
        Curso curso2=new Curso();
        curso1.setName("Inf");
        curso2.setName("Mec");

        Cadeira cadeira1=new Cadeira();
        Cadeira cadeira2=new Cadeira();
        Cadeira cadeira3=new Cadeira();
        cadeira1.setName("Eng");
        cadeira2.setName("Base");
        cadeira3.setName("Carro");

        cadeira1.setCurso(curso1);
        cadeira2.setCurso(curso1);
        cadeira3.setCurso(curso2);

        Set<Cadeira> cadeiras1=new HashSet<>();
        Set<Cadeira> cadeiras2=new HashSet<>();
        Set<Cadeira> cadeiras3=new HashSet<>();

        cadeiras1.add(cadeira1);
        cadeiras1.add(cadeira2);
        cadeiras2.add(cadeira3);
        cadeiras3.add(cadeira3);

        curso1.setCadeiras(cadeiras1);
        explicador1.addCadeira(cadeira1);
        curso2.setCadeiras(cadeiras2);
        explicador2.setCadeiras(cadeiras2);
        curso2.setCadeiras(cadeiras3);
        explicador3.setCadeiras(cadeiras3);


        Set<Disponibilidade> disponibilidades1=new HashSet<>();
        Set<Disponibilidade> disponibilidades2=new HashSet<>();
        Set<Disponibilidade> disponibilidades3=new HashSet<>();

        Disponibilidade disponibilidade1=new Disponibilidade();
        disponibilidade1.setDia(DayOfWeek.MONDAY);
        disponibilidade1.setHoraIni(LocalTime.of(13,0));
        disponibilidade1.setHoraFim(LocalTime.of(17,0));

        Disponibilidade disponibilidade2=new Disponibilidade();
        disponibilidade2.setDia(DayOfWeek.TUESDAY);
        disponibilidade2.setHoraIni(LocalTime.of(14,0));
        disponibilidade2.setHoraFim(LocalTime.of(16,30));

        Disponibilidade disponibilidade3=new Disponibilidade();
        disponibilidade3.setDia(DayOfWeek.TUESDAY);
        disponibilidade3.setHoraIni(LocalTime.of(10,0));
        disponibilidade3.setHoraFim(LocalTime.of(18,30));

        Disponibilidade disponibilidade4=new Disponibilidade();
        disponibilidade4.setDia(DayOfWeek.TUESDAY);
        disponibilidade4.setHoraIni(LocalTime.of(12,0));
        disponibilidade4.setHoraFim(LocalTime.of(20,0));

        disponibilidades1.add(disponibilidade1);
        disponibilidades1.add(disponibilidade2);
        disponibilidades2.add(disponibilidade3);
        disponibilidades2.add(disponibilidade4);
        disponibilidades3.add(disponibilidade1);
        disponibilidades3.add(disponibilidade4);

        explicador1.setDisponibilidades(disponibilidades1);
        explicador2.setDisponibilidades(disponibilidades2);
        explicador3.setDisponibilidades(disponibilidades3);

        explicadores.add(explicador1);
        explicadores.add(explicador2);
        explicadores.add(explicador3);


        FilterExplicadorObject filterObject=new FilterExplicadorObject(dia, cursoName, LocalTime.of(12,0),LocalTime.of(19,0));
        //FilterExplicadorObject filterObject=new FilterExplicadorObject("Rui",null,null,null, null);
        FilterExplicadorService filterOrderService=new FilterExplicadorService();
        System.out.println(filterObject);
        assertEquals(1,filterOrderService.filter(explicadores,filterObject).size());
/*
        FilterObject filterObject2=new FilterObject(null, "Mec", null,null);
        FilterExplicadorService filterOrderService2=new FilterExplicadorService();
        System.out.println(filterObject);
        assertEquals(2,filterOrderService.filter(explicadores,filterObject).size());
*/
    }
}