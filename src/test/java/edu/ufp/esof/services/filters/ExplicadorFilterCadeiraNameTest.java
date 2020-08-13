package edu.ufp.esof.services.filters;

import edu.ufp.esof.models.Cadeira;
import edu.ufp.esof.models.Disponibilidade;
import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.filters.explicador.ExplicadorFilterCadeiraName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterCadeiraNameTest {

    @Test
    void filter() {

        Set<Explicador> explicadores=new HashSet<>();

        Explicador explicador1=new Explicador("Rui");
        Explicador explicador2=new Explicador("Eduardo");
        Explicador explicador3=new Explicador("Vitor");

        Disponibilidade disponibilidade1=new Disponibilidade();
        disponibilidade1.setDia(DayOfWeek.MONDAY);
        Disponibilidade disponibilidade2=new Disponibilidade();
        disponibilidade2.setDia(DayOfWeek.TUESDAY);

        Cadeira cadeira1=new Cadeira();
        Cadeira cadeira2=new Cadeira();
        Cadeira cadeira3=new Cadeira();
        Cadeira cadeira4=new Cadeira();

        cadeira1.setName("Base");
        cadeira2.setName("Eng");
        cadeira3.setName("LP");
        cadeira4.setName("AED");

        explicador1.addDisponibilidade(disponibilidade1);
        explicador1.addDisponibilidade(disponibilidade2);
        explicador2.addDisponibilidade(disponibilidade2);

        Set<Cadeira> cadeiras1=new HashSet<>();
        cadeiras1.add(cadeira1);
        cadeiras1.add(cadeira2);

        explicador1.setCadeiras(cadeiras1);

        Set<Cadeira> cadeiras2=new HashSet<>();
        cadeiras2.add(cadeira3);
        cadeiras2.add(cadeira1);

        Set<Cadeira> cadeiras3=new HashSet<>();
        explicador2.setCadeiras(cadeiras2);


        cadeiras3.add(cadeira3);
        cadeiras3.add(cadeira1);

        explicador3.setCadeiras(cadeiras3);

        explicadores.add(explicador1);
        explicadores.add(explicador2);
        explicadores.add(explicador3);

        ExplicadorFilterCadeiraName explicadorFilterCadeiraName=new ExplicadorFilterCadeiraName("AED");
        assertEquals(0,explicadorFilterCadeiraName.filter(explicadores).size());

        explicadorFilterCadeiraName= new ExplicadorFilterCadeiraName("Base");
        assertEquals(3,explicadorFilterCadeiraName.filter(explicadores).size());

        explicadorFilterCadeiraName= new ExplicadorFilterCadeiraName("LP");
        assertEquals(2,explicadorFilterCadeiraName.filter(explicadores).size());

        explicadorFilterCadeiraName= new ExplicadorFilterCadeiraName("Eng");
        assertEquals(1,explicadorFilterCadeiraName.filter(explicadores).size());
    }
}