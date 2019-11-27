package edu.ufp.esof.EngSoftPro.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExplicadorTest {

    @Test
    void addDisponibilidade() {
        Explicador explicador=new Explicador();
        explicador.setName("Rui");
        explicador.setId(11L);
        Cadeira cadeira=new Cadeira();
        cadeira.setName("BaseDados");
        cadeira.setId(55L);
        explicador.setCadeira(cadeira);


        Disponibilidade disponibilidade=new Disponibilidade();

        disponibilidade.setDia(DayOfWeek.MONDAY);
        disponibilidade.setHora(LocalTime.NOON);

        explicador.addDisponibilidade(disponibilidade);

        //assertEquals(explicador.getDisponibilidades(), disponibilidade);
        assertEquals(55L, explicador.getCadeira().getId());


    }
}