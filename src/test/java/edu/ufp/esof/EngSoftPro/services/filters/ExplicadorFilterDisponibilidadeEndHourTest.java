package edu.ufp.esof.EngSoftPro.services.filters;

import edu.ufp.esof.EngSoftPro.models.Disponibilidade;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.services.filters.explicador.ExplicadorFilterDisponibilidadeEndHour;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterDisponibilidadeEndHourTest {

    @Test
    void filter() {

        Set<Explicador> explicadores=new HashSet<>();

        Explicador explicador1=new Explicador("Rui");
        Explicador explicador2=new Explicador("Edu");
        Explicador explicador3=new Explicador("Vitor");

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

        ExplicadorFilterDisponibilidadeEndHour explicadorFilterDisponibilidadeEndHour=new ExplicadorFilterDisponibilidadeEndHour(LocalTime.of(16,30));
        assertEquals(1,explicadorFilterDisponibilidadeEndHour.filter(explicadores).size());

        explicadorFilterDisponibilidadeEndHour= new ExplicadorFilterDisponibilidadeEndHour(LocalTime.of(19,0));
        assertEquals(3,explicadorFilterDisponibilidadeEndHour.filter(explicadores).size());

        explicadorFilterDisponibilidadeEndHour= new ExplicadorFilterDisponibilidadeEndHour(null);
        assertEquals(3,explicadorFilterDisponibilidadeEndHour.filter(explicadores).size());

        explicadorFilterDisponibilidadeEndHour= new ExplicadorFilterDisponibilidadeEndHour(LocalTime.of(16,50));
        assertEquals(1,explicadorFilterDisponibilidadeEndHour.filter(explicadores).size());

        explicadorFilterDisponibilidadeEndHour= new ExplicadorFilterDisponibilidadeEndHour(LocalTime.of(10,50));
        assertEquals(0,explicadorFilterDisponibilidadeEndHour.filter(explicadores).size());

    }
}