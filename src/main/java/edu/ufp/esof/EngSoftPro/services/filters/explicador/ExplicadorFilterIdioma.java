package edu.ufp.esof.EngSoftPro.services.filters.explicador;

import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.models.Idioma;
import edu.ufp.esof.EngSoftPro.services.filters.FilterI;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterIdioma implements FilterI<Explicador> {

    private String idiomaName;

    public ExplicadorFilterIdioma(String idiomaName) {
        this.idiomaName = idiomaName;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadores) {
        if(this.idiomaName==null || this.idiomaName.isBlank()){
            return explicadores;
        }

        Set<Explicador> explicadoresToBeReturned=new HashSet<>();
        for(Explicador oi:explicadores){
            for(Idioma io:oi.getIdiomas()){
                if(io!=null && io.getLingua().equals(idiomaName)){
                    explicadoresToBeReturned.add(oi);
                    break;
                }
            }
        }

        return explicadoresToBeReturned;
    }

}