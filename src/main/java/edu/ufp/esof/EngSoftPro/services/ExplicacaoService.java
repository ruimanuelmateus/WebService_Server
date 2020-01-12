package edu.ufp.esof.EngSoftPro.services;

import edu.ufp.esof.EngSoftPro.models.Explicacao;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.repositories.ExplicacaoRepo;
import edu.ufp.esof.EngSoftPro.repositories.ExplicadorRepo;
import edu.ufp.esof.EngSoftPro.services.filters.explicador.FilterExplicadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExplicacaoService {

    private ExplicacaoRepo explicacaoRepo;
    private ExplicadorService explicadorService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ExplicacaoService(ExplicacaoRepo explicacaoRepo, ExplicadorService explicadorService) {
        this.explicacaoRepo=explicacaoRepo;
        this.explicadorService=explicadorService;
    }

    public Optional<Explicacao> createExplicacao(Explicacao explicacao) {
        String name=explicacao.getExplicador().getName();
        Optional<Explicador> optionalExplicador=this.explicadorService.findByName(name);
        if(optionalExplicador.isPresent()){
            Explicador explicador=optionalExplicador.get();
            if(explicador.canSchedule(explicacao)){
                for(Explicacao ex:explicador.getExplicacoes()) {
                    if (ex.overlaps(explicacao)) {
                        return Optional.empty();
                    }
                }
                explicador.addExplicacao(explicacao);
                this.explicacaoRepo.save(explicacao);
                this.logger.info("Success on post request to add explicacao");
                return Optional.of(explicacao);
            }
        }
        return Optional.empty();
    }

}
