package edu.ufp.esof.models.builders;

import edu.ufp.esof.models.Curso;
import edu.ufp.esof.models.Explicador;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes =ExplicadorBuilder.class)
class ExplicadorBuilderTest {

    @Test
    void createBuilder() {
        Curso curso=new Curso();
        curso.setName("EngSoft");
        ExplicadorBuilder builder=new ExplicadorBuilder();
        Explicador explicador=builder.setName("explicador").setPassword("ruiMateus")
                .setCurso(curso)
                .setCadeiras(new HashSet<>())
                .build();

        assertEquals("explicador",explicador.getName());
        assertEquals("ruiMateus",explicador.getPassword());
        assertEquals(curso,explicador.getCurso());
    }



}