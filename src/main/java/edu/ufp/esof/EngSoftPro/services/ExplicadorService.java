package edu.ufp.esof.EngSoftPro.services;

import edu.ufp.esof.EngSoftPro.models.*;
import edu.ufp.esof.EngSoftPro.repositories.ExplicadorRepo;
import edu.ufp.esof.EngSoftPro.services.filters.explicador.FilterExplicadorObject;
import edu.ufp.esof.EngSoftPro.services.filters.explicador.FilterExplicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class ExplicadorService {

    private ExplicadorRepo explicadorRepo;
    private FilterExplicadorService filterService;
    private CursoService cursoService;
    private FaculdadeService faculdadeService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ExplicadorService(ExplicadorRepo explicadorRepo, FilterExplicadorService filterService,CursoService cursoService,FaculdadeService faculdadeService) {
        this.filterService=filterService;
        this.explicadorRepo=explicadorRepo;
        this.cursoService=cursoService;
        this.faculdadeService=faculdadeService;
    }

    public Set<Explicador> findAll() {
        Set<Explicador> explicadores=new HashSet<>();
        for(Explicador explicador:this.explicadorRepo.findAll()){
            explicadores.add(explicador);
        }
        return explicadores;
    }

    public Optional<Explicador> findById(Long id) {
        return this.explicadorRepo.findById(id);
    }

    public Optional<Explicador> createExplicador(Explicador explicador) {
        Optional<Explicador> optionalExplicador=this.explicadorRepo.findByName(explicador.getName());
        if(optionalExplicador.isPresent()){
            this.logger.info("Fail on post request to create explicador, already exists");
            return Optional.empty();
        }
        Explicador createdExplicador=this.explicadorRepo.save(explicador);
        this.logger.info("Sucess on post request to create explicador");
        return Optional.of(createdExplicador);
    }

    public Set<Explicador> filterExplicadores(Map<String, String> searchParams) {
        FilterExplicadorObject filterExplicadorObject =new FilterExplicadorObject(searchParams);
        Set<Explicador> explicadores=this.findAll();
        return this.filterService.filter(explicadores, filterExplicadorObject);
    }

    public Optional<Explicador> modifyCurso(Explicador explicador, String cursoName){
        Optional<Curso> optionalCurso=this.cursoService.findByName(cursoName);
        Optional<Explicador> optionalExplicador=this.explicadorRepo.findByName(explicador.getName());
        if(optionalCurso.isPresent() && optionalExplicador.isPresent()){
            Explicador explicadorFromDB=optionalExplicador.get();
            if(explicadorFromDB.getCurso()!=null){
               Curso cursoToRemove=explicadorFromDB.getCurso();
               cursoToRemove.removeExplicador(explicadorFromDB);
            }
            Curso cursoToBeAdded=optionalCurso.get();
            cursoToBeAdded.addExplicador(explicadorFromDB);
            explicadorFromDB.setCurso(cursoToBeAdded);
            this.explicadorRepo.save(explicadorFromDB);
            this.logger.info("Sucess on put request to define curso to explicador");
            return Optional.of(explicadorFromDB);
        }
        return Optional.empty();
    }

    public Optional<Explicador> addDisponibilidade(Explicador explicador){
        Optional<Explicador> optionalExplicador=this.explicadorRepo.findByName(explicador.getName());
        if(optionalExplicador.isPresent()){
            Explicador newExplicador=optionalExplicador.get();
            if (newExplicador.getDisponibilidades()!=null){
                newExplicador.removeDisponibilidade();
            }
            Set<Disponibilidade> disponibilidadeToAdd=explicador.getDisponibilidades();
            newExplicador.setDisponibilidades(disponibilidadeToAdd);
            this.explicadorRepo.save(newExplicador);
            this.logger.info("Sucess on put request to add disponibilidade");
            return Optional.of(newExplicador);
        }
        return Optional.empty();
    }

    public Optional<Explicador> createAndAssociate(Explicador explicador, String faculdadeNome) {
        Optional<Faculdade> optionalFaculdade=this.faculdadeService.findByName(faculdadeNome);
        if(optionalFaculdade.isPresent()){
            Curso curso=explicador.getCurso();
            Faculdade faculdade=optionalFaculdade.get();
            faculdade.addCurso(curso);
            this.logger.info("Sucess on post request to add explicador on universidade");
            //faculdadeService.saveCursoToFaculdade(faculdade, curso.getName());
            return Optional.of(explicador);
        }
        return Optional.empty();
    }


    public Optional<Explicador> findByName(String name) {
        return this.explicadorRepo.findByName(name);
    }

    public Optional<Explicador> searchByName(String name){
        return this.explicadorRepo.findByName(name);
    }
}
