package edu.ufp.esof.services;

import edu.ufp.esof.models.Faculdade;
import edu.ufp.esof.repositories.*;
import edu.ufp.esof.repositories.FaculdadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class FaculdadeService{

    private FaculdadeRepo faculdadeRepo;

    private Logger logger= LoggerFactory.getLogger(this.getClass());


    @Autowired
    public FaculdadeService(FaculdadeRepo faculdadeRepo/*, CursoRepo cursoRepo, CadeiraRepo cadeiraRepo*/) {
        this.faculdadeRepo = faculdadeRepo;
        //this.cursoRepo = cursoRepo;
        //this.cadeiraRepo = cadeiraRepo;
    }

    //private FilterExplicadorService filterService;

/*
        public Set<Faculdade> addFaculdade() {
            Set<Faculdade> faculdades = new HashSet<>();
            for (Faculdade faculdade : this.faculdadeRepo.findAll()) {
                faculdades.add(faculdade);
            }
            return faculdades;
        }

        public Set<Faculdade> findAll() {
            Set<Faculdade> faculdades = new HashSet<>();
            for (Faculdade faculdade : this.faculdadeRepo.findAll()) {
                faculdades.add(faculdade);
            }
            return faculdades;
        }

    public Optional<Faculdade> findById (Long id) {
        return this.faculdadeRepo.findById(id);
    }

        public Optional<Faculdade> getFaculdadeByName(String name) {
            for (Faculdade faculdade : findAll()) {
                if (faculdade.getName().equalsIgnoreCase(name)) {
                    return Optional.of(faculdade);
                }
            }
            return Optional.empty();
        }

 */

    public Optional<Faculdade> createFaculdade(Faculdade faculdade) {
        Optional<Faculdade> optionalFaculdade=this.faculdadeRepo.findByName(faculdade.getName());
        if(optionalFaculdade.isPresent()){
            return Optional.empty();
        }
        Faculdade createdFaculdade= this.faculdadeRepo.save(faculdade);
        this.logger.info("Success on post request to create faculdade");
        return Optional.ofNullable(createdFaculdade);
    }

        public void save(Faculdade faculdade) {
            this.faculdadeRepo.save(faculdade);
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




    public Optional<Faculdade> findByName(String name) {
        return this.faculdadeRepo.findByName(name);
    }
}
