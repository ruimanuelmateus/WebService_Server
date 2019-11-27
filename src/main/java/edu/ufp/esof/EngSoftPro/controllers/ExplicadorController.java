package edu.ufp.esof.EngSoftPro.controllers;

import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.repositories.ExplicadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    @Autowired
    private ExplicadorRepo explicadorRepo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Explicador>> getAllExplicadores(){
        return ResponseEntity.ok(this.explicadorRepo.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> getExplicadorById(@PathVariable Long id){
        Optional<Explicador> explicadorOptional=this.explicadorRepo.findById(id);
        if(explicadorOptional.isPresent()){
            return ResponseEntity.ok(explicadorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> createExplicador(@RequestBody Explicador explicador){
        System.out.println(explicador);
        Optional<Explicador> explicadorOptional=this.explicadorRepo.findByName(explicador.getName());
        if(explicadorOptional.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.explicadorRepo.save(explicador));
    }

    /*
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Explicador>> searchExplicadorByFilter(@RequestParam Map<String, String> query){

        System.out.println(query.get("attr1"));
        System.out.println(query.get("attr2 "));
        System.out.println(query.get("attr3"));

        return ResponseEntity.ok().build();
    }

     */
}
