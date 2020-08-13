package edu.ufp.esof.services.filters;

import edu.ufp.esof.models.Cadeira;
import edu.ufp.esof.models.Curso;
import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.filters.explicador.ExplicadorFilterCursoName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterCursoNameTest {

    @Test
    void filter() {

        Set<Explicador> explicadores=new HashSet<>();

        Explicador explicador1=new Explicador("Rui");
        Explicador explicador2=new Explicador("Eduardo");
        Explicador explicador3=new Explicador("Vitor");

        Cadeira cadeira1=new Cadeira();
        Cadeira cadeira2=new Cadeira();
        Cadeira cadeira3=new Cadeira();
        Cadeira cadeira4=new Cadeira();

        cadeira1.setName("Base");
        cadeira2.setName("Eng");
        cadeira3.setName("LP");
        cadeira4.setName("AED");

        Curso curso1=new Curso();
        Curso curso2=new Curso();

        curso1.setName("Inf");
        curso2.setName("Ele");

        cadeira1.setCurso(curso1);
        cadeira2.setCurso(curso1);
        cadeira3.setCurso(curso2);
        cadeira4.setCurso(curso2);

        Set<Cadeira> cadeiras1=new HashSet<>();
        cadeiras1.add(cadeira1);
        cadeiras1.add(cadeira2);

        explicador1.setCadeiras(cadeiras1);

        Set<Cadeira> cadeiras2=new HashSet<>();
        cadeiras2.add(cadeira3);
        cadeiras2.add(cadeira1);

        explicador2.setCadeiras(cadeiras2);

        Set<Cadeira> cadeiras3=new HashSet<>();

        cadeiras3.add(cadeira3);
        cadeiras3.add(cadeira4);


        explicador3.setCadeiras(cadeiras3);

        explicadores.add(explicador1);
        explicadores.add(explicador2);
        explicadores.add(explicador3);

        ExplicadorFilterCursoName explicadorFilterCursoName=new ExplicadorFilterCursoName("Inf");
        assertEquals(2,explicadorFilterCursoName.filter(explicadores).size());

        explicadorFilterCursoName= new ExplicadorFilterCursoName("Ele");
        assertEquals(2,explicadorFilterCursoName.filter(explicadores).size());

        explicadorFilterCursoName= new ExplicadorFilterCursoName("Mec");
        assertEquals(0,explicadorFilterCursoName.filter(explicadores).size());

    }
}