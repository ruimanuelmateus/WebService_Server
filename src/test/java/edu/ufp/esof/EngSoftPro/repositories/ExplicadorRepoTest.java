package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Curso;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExplicadorRepoTest {

    @Autowired
    private ExplicadorRepo explicadorRepo;

    @Test
    public void test(){

        Explicador explicador=new Explicador("Rui");
        Curso curso=new Curso();
        curso.setName("B");

        explicador.setCurso(curso);
        this.explicadorRepo.save(explicador);
        assertEquals(1, this.explicadorRepo.count());
    }

}