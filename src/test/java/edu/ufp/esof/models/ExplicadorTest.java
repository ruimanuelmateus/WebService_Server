package edu.ufp.esof.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorTest {

    @Test
    void addDisponibilidade() {
        Explicador explicador=new Explicador();
        explicador.setName("Rui");
        explicador.setId(11L);
        Set<Cadeira> cadeiras=new HashSet<>();
        Cadeira cadeira=new Cadeira();
        cadeira.setName("BaseDados");
        cadeira.setId(55L);
        cadeiras.add(cadeira);
        Curso curso1=new Curso();
        Curso curso2=new Curso();
        curso1.setName("Inf");
        curso2.setName("Mec");
        cadeira.setCurso(curso1);
        explicador.setCadeiras(cadeiras);


        Disponibilidade disponibilidade=new Disponibilidade();

        disponibilidade.setDia(DayOfWeek.MONDAY);
        disponibilidade.setHoraIni(LocalTime.NOON);
        disponibilidade.setHoraFim(LocalTime.MIDNIGHT);

        explicador.addDisponibilidade(disponibilidade);

        //assertEquals(explicador.getDisponibilidades().contains(disponibilidade));
        assertEquals(1, explicador.getCadeiras().size());


    }
}