package edu.ufp.esof.services.filters;

import edu.ufp.esof.models.Disponibilidade;
import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.filters.explicador.ExplicadorFilterDisponibilidadeDia;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterDisponibilidadeDiaTest {

    @Test
    void filter() {

        Set<Explicador> explicadores=new HashSet<>();

        Explicador explicador1=new Explicador("Rui");
        Explicador explicador2=new Explicador("Eduardo");

        Disponibilidade disponibilidade1=new Disponibilidade();
        disponibilidade1.setDia(DayOfWeek.MONDAY);
        Disponibilidade disponibilidade2=new Disponibilidade();
        disponibilidade2.setDia(DayOfWeek.TUESDAY);

        explicador1.addDisponibilidade(disponibilidade1);
        explicador1.addDisponibilidade(disponibilidade2);
        explicador2.addDisponibilidade(disponibilidade2);

        explicadores.add(explicador1);
        explicadores.add(explicador2);

        ExplicadorFilterDisponibilidadeDia explicadorFilterDisponibilidadeDia=new ExplicadorFilterDisponibilidadeDia(DayOfWeek.MONDAY);
        assertEquals(1,explicadorFilterDisponibilidadeDia.filter(explicadores).size());

        explicadorFilterDisponibilidadeDia= new ExplicadorFilterDisponibilidadeDia(DayOfWeek.TUESDAY);
        assertEquals(2,explicadorFilterDisponibilidadeDia.filter(explicadores).size());

        explicadorFilterDisponibilidadeDia= new ExplicadorFilterDisponibilidadeDia(DayOfWeek.SUNDAY);
        assertEquals(0,explicadorFilterDisponibilidadeDia.filter(explicadores).size());

        explicadorFilterDisponibilidadeDia= new ExplicadorFilterDisponibilidadeDia(null);
        assertEquals(2,explicadorFilterDisponibilidadeDia.filter(explicadores).size());

    }

}