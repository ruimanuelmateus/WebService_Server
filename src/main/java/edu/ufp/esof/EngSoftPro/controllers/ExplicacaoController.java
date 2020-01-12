package edu.ufp.esof.EngSoftPro.controllers;

import edu.ufp.esof.EngSoftPro.models.Explicacao;
import edu.ufp.esof.EngSoftPro.services.ExplicacaoService;
import edu.ufp.esof.EngSoftPro.services.ExplicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller
@RequestMapping("/explicacao")
public class ExplicacaoController {

    private ExplicacaoService explicacaoService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ExplicacaoController(ExplicacaoService explicacaoService){this.explicacaoService=explicacaoService;}

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicacao> createExplicacao(@RequestBody Explicacao explicacao){
        this.logger.info("Received a post request to add explicacao");
        Optional<Explicacao> explicacaoOptional=this.explicacaoService.createExplicacao(explicacao);
        if(explicacaoOptional.isPresent()){
            return ResponseEntity.ok(explicacaoOptional.get());
        }
        return ResponseEntity.badRequest().build();
    }

}
