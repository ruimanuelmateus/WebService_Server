package edu.ufp.esof.services;

import edu.ufp.esof.models.Cadeira;
import edu.ufp.esof.models.Curso;
import edu.ufp.esof.repositories.CadeiraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class CadeiraService {

    private CadeiraRepo cadeiraRepo;
    private CursoService cursoService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CadeiraService(CadeiraRepo cadeiraRepo, CursoService cursoService) {
        this.cadeiraRepo = cadeiraRepo;
        this.cursoService = cursoService;
    }
/*
    public Iterable<Curso> findAll() {
        return this.cadeiraRepo.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return this.cadeiraRepo.findById(id);
    }

 */

    public Optional<Cadeira> createCadeira(Cadeira cadeira) {
        Optional<Cadeira> optionalCadeira=this.cadeiraRepo.findByName(cadeira.getName());
        if(optionalCadeira.isPresent()){
            return Optional.empty();
        }
        Cadeira createdCadeira=this.cadeiraRepo.save(cadeira);
        return Optional.of(createdCadeira);
    }

    public Optional<Cadeira> findByName(String name) {
        return this.cadeiraRepo.findByName(name);
    }

    public Optional<Cadeira> createAndAssociate(Cadeira cadeira, String cursoNome) {
        Optional<Curso> optionalCurso=this.cursoService.findByName(cursoNome);
        if(optionalCurso.isPresent()){
            Curso curso=optionalCurso.get();
            curso.addCadeira(cadeira);
            cursoService.save(curso);
            this.logger.info("Sucees on post request to add cadeira to curso");
            return Optional.of(cadeira);
        }
        return Optional.empty();
    }
}
