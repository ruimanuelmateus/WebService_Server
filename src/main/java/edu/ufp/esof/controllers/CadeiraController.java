package edu.ufp.esof.controllers;

import edu.ufp.esof.models.Cadeira;
import edu.ufp.esof.services.CadeiraService;
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
@RequestMapping("/cadeira")
public class CadeiraController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private CadeiraService cadeiraService;

    @Autowired
    public CadeiraController(CadeiraService cadeiraService){this.cadeiraService=cadeiraService;}


    @PostMapping(value="/{curso}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cadeira> createCadeira(@RequestBody Cadeira cadeira, @PathVariable("curso")String cursoName){
        this.logger.info("Received a post request to add cadeira to curso");
        Optional<Cadeira> optionalCadeira=this.cadeiraService.createAndAssociate(cadeira,cursoName);
        if(optionalCadeira.isPresent()){
            return ResponseEntity.ok(optionalCadeira.get());
        }
        //return ResponseEntity.badRequest().build();
        throw new CadeiraController.CadeiraAlreadyExistsExcpetion(cadeira.getName());

    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Curso already exists")
    private static class CadeiraAlreadyExistsExcpetion extends RuntimeException {

        CadeiraAlreadyExistsExcpetion(String name) {
            super("A cadeira with name: "+name+" already exists");
        }
    }
}
