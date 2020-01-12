package edu.ufp.esof.EngSoftPro.controllers;


import edu.ufp.esof.EngSoftPro.models.Curso;
import edu.ufp.esof.EngSoftPro.models.Faculdade;
import edu.ufp.esof.EngSoftPro.services.FaculdadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private FaculdadeService faculdadeService;

    @Autowired
    public FaculdadeController(FaculdadeService faculdadeService){this.faculdadeService=faculdadeService;}

    /*
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Faculdade>> getAllFaculdades(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.faculdadeService.findAll());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Faculdade> getFaculdadeById(@PathVariable("id") Long id) throws FaculdadeController.NoFaculdadeException {
        this.logger.info("Received a get request");

        Optional<Faculdade> optionalFaculdade=this.faculdadeService.findById(id);
        if(optionalFaculdade.isPresent()) {
            return ResponseEntity.ok(optionalFaculdade.get());
        }
        throw new FaculdadeController.NoFaculdadeException(id);
    }

     */

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> createFaculdade(@RequestBody Faculdade faculdade){
        this.logger.info("Received a post request to create faculdade");
        Optional<Faculdade> faculdadeOptional=this.faculdadeService.createFaculdade(faculdade);
        if(faculdadeOptional.isPresent()){
            return ResponseEntity.ok(faculdadeOptional.get());
        }
        throw new FaculdadeController.FaculdadeAlreadyExistsExcpetion(faculdade.getName());
    }
/*
    @PostMapping(value = "/curso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> saveCurso(@RequestBody Faculdade faculdade, String cursoName) {
        logger.info(faculdade.toString() + " " + cursoName);
        Optional<Faculdade> faculdadeOptional = faculdadeService.saveCursoToFaculdade(faculdade, cursoName);
        if (faculdadeOptional.isPresent()) {
            return ResponseEntity.ok(faculdadeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

 */


    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such explicador")
    private static class NoFaculdadeException extends RuntimeException {

        public NoFaculdadeException(Long id) {
            super("No such faculdade with id: "+id);
        }
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Faculdade already exists")
    private static class FaculdadeAlreadyExistsExcpetion extends RuntimeException {

        public FaculdadeAlreadyExistsExcpetion(String name) {
            super("A faculdade with name: "+name+" already exists");
        }
    }

}
