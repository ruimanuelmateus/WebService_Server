package edu.ufp.esof.EngSoftPro.services;

import edu.ufp.esof.EngSoftPro.models.*;
import edu.ufp.esof.EngSoftPro.models.builders.AlunoBuilder;
import edu.ufp.esof.EngSoftPro.models.builders.DisponibilidadeBuilder;
import edu.ufp.esof.EngSoftPro.models.builders.ExplicacaoBuilder;
import edu.ufp.esof.EngSoftPro.models.builders.ExplicadorBuilder;
import edu.ufp.esof.EngSoftPro.repositories.ExplicacaoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ExplicacaoService.class)
class ExplicacaoServiceTest {

    @Autowired
    private ExplicacaoService explicacaoService;

    @MockBean
    private ExplicacaoRepo explicacaoRepo;

    @MockBean
    private ExplicadorService explicadorService;

    @Test
    void createExplicacao() {

        Idioma idioma=new Idioma();
        idioma.setLingua("Ingles");

        AlunoBuilder alunoBuilder=new AlunoBuilder();
        Aluno aluno=alunoBuilder.setName("Vitor").build();

        DisponibilidadeBuilder disponibilidadeBuilder=new DisponibilidadeBuilder();
        Disponibilidade disponibilidade=disponibilidadeBuilder.setDia(DayOfWeek.MONDAY).setHoraIni(LocalTime.NOON.plusHours(2))
                .setHoraFim(LocalTime.NOON.plusHours(4)).build();

        ExplicadorBuilder builder=new ExplicadorBuilder();
        Explicador explicador=builder.setName("Rui").addDisponibilidade(disponibilidade).addIdioma(idioma).build();

        ExplicacaoBuilder explicacaoBuilder=new ExplicacaoBuilder();
        Explicacao explicacao=explicacaoBuilder.setExplicador(explicador).setAluno(aluno)
                .setDia(DayOfWeek.MONDAY).setHoraIni(LocalTime.NOON.plusHours(2)).setHoraFim(LocalTime.NOON.plusHours(3)).build();

        when(this.explicadorService.searchByName("Rui")).thenReturn(Optional.of(explicador));

        //System.out.println(explicacao.getExplicador().getName());

        Optional<Explicador> optionalExplicador=this.explicadorService.findByName(explicacao.getExplicador().getName());
        assertNotNull(optionalExplicador);
        //assertTrue(optionalExplicador.isPresent());

        when(this.explicacaoService.createExplicacao(explicacao)).thenReturn(Optional.of(explicacao));
        when(this.explicadorService.findByName("Rui")).thenReturn(Optional.of(explicador));

        Optional<Explicacao> optionalExplicacao=this.explicacaoService.createExplicacao(explicacao);


        assertNotNull(optionalExplicacao);
        assertTrue(optionalExplicacao.isPresent());
        assertEquals(explicador,optionalExplicacao.get().getExplicador());
        assertEquals(aluno,optionalExplicacao.get().getAluno());
        assertEquals(1,explicador.getExplicacoes().size());

        explicador.addExplicacao(explicacao);

        AlunoBuilder alunoBuilder1=new AlunoBuilder();
        Aluno aluno1=alunoBuilder1.setName("Eduardo").build();

        ExplicacaoBuilder explicacaoBuilder1=new ExplicacaoBuilder();
        Explicacao explicacao1=explicacaoBuilder1.setAluno(aluno1).setExplicador(explicador).setDia(DayOfWeek.MONDAY)
                .setHoraIni(LocalTime.NOON.plusHours(2).plusMinutes(30)).setHoraFim(LocalTime.NOON.plusHours(3).plusMinutes(30)).build();

        when(this.explicacaoService.createExplicacao(explicacao1)).thenReturn(Optional.of(explicacao1));
        when(this.explicadorService.findByName("Rui")).thenReturn(Optional.of(explicador));


        Optional<Explicacao> optionalExplicacao1=this.explicacaoService.createExplicacao(explicacao1);
        assertNotNull(optionalExplicacao1);
        assertTrue(optionalExplicacao1.isEmpty());




    }
}