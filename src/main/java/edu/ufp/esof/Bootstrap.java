package edu.ufp.esof;

import edu.ufp.esof.repositories.ExplicacaoRepo;
import edu.ufp.esof.repositories.ExplicadorRepo;
import edu.ufp.esof.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ExplicacaoRepo explicacaoRepo;

    @Autowired
    private ExplicadorRepo explicadorRepo;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");

        Explicador explicador1=new Explicador("Rui");

        Disponibilidade disponibilidade1=new Disponibilidade();
        disponibilidade1.setDia(DayOfWeek.SUNDAY);
        disponibilidade1.setHoraIni(LocalTime.NOON);
        disponibilidade1.setHoraFim(LocalTime.MIDNIGHT);

        Disponibilidade disponibilidade2=new Disponibilidade();
        disponibilidade2.setDia(DayOfWeek.SATURDAY);
        disponibilidade2.setHoraIni(LocalTime.of(15,30));
        disponibilidade2.setHoraFim(LocalTime.of(18,30));

        explicador1.addDisponibilidade(disponibilidade1);
        explicador1.addDisponibilidade(disponibilidade2);

        Aluno aluno1=new Aluno("Vitor");
        Aluno aluno2=new Aluno("Eduardo");

        Curso curso1=new Curso();
        curso1.setName("Engenharia Inf");

        Curso curso2=new Curso();
        curso2.setName("Engenharia Mec");

        Cadeira cadeira1=new Cadeira();
        cadeira1.setName("Eng Soft");

        Cadeira cadeira2=new Cadeira();
        cadeira2.setName("Eng Car");

        explicador1.addCadeira(cadeira1);
        //explicadorRepo.save(explicador1);

        Faculdade faculdade=new Faculdade();
        faculdade.setName("Pessoa");
        curso1.setFaculdade(faculdade);
        curso2.setFaculdade(faculdade);
        cadeira1.setCurso(curso1);
        cadeira2.setCurso(curso2);

        explicador1.addCurso(curso1);

        aluno1.setCurso(curso1);
        aluno2.setCurso(curso2);

        Explicacao explicacao=new Explicacao();
        explicacao.setAluno(aluno1);
        explicacao.setExplicador(explicador1);
        explicacao.setCadeira(cadeira1);



        this.explicacaoRepo.save(explicacao);

        System.out.println("StartUp...........");


    }
}
