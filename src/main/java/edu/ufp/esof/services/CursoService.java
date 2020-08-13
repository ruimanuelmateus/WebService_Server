package edu.ufp.esof.services;

import edu.ufp.esof.models.Curso;
import edu.ufp.esof.models.Faculdade;
import edu.ufp.esof.repositories.CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class CursoService {

    private CursoRepo cursoRepo;
    private FaculdadeService faculdadeService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CursoService(CursoRepo cursoRepo, FaculdadeService faculdadeService) {
        this.cursoRepo = cursoRepo;
        this.faculdadeService = faculdadeService;
    }

    public Iterable<Curso> findAll() {
        return this.cursoRepo.findAll();
    }


    public Optional<Curso> findByName(String name) {
        return this.cursoRepo.findByName(name);
    }

    public Optional<Curso> createAndAssociate(Curso curso, String faculdadeName) {
        Optional<Faculdade> optionalFaculdade=this.faculdadeService.findByName(faculdadeName);
        if(optionalFaculdade.isPresent()){
            Faculdade faculdade=optionalFaculdade.get();
            //if(this.cursoRepo.findByName(curso.getName()))
            faculdade.addCurso(curso);
            faculdadeService.save(faculdade);
            this.logger.info("Sucess on post request to add curso to universidade");
            return Optional.of(curso);
        }
        return Optional.empty();
    }
/*
    public Optional<Faculdade> saveCursoToFaculdade(Faculdade faculdade, String cursoName) {
        Optional<Curso> cursoOptional = this.cursoRepo.findByName(cursoName);
        Optional<Faculdade> faculdadeOptional = this.faculdadeRepo.findByName(faculdade.getName());
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            Faculdade faculdade1 = faculdadeOptional.get();

            curso.addFaculdade(faculdade1);
            cursoRepo.save(curso);

            faculdade1.addCurso(curso);
            faculdadeRepo.save(faculdade1);

            return faculdadeRepo.findByName(faculdade1.getName());
        }
        return Optional.empty();
    }

 */

    public Curso save(Curso curso) {
        return this.cursoRepo.save(curso);
    }
}
