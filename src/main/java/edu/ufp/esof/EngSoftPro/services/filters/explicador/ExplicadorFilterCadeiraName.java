package edu.ufp.esof.EngSoftPro.services.filters.explicador;

import edu.ufp.esof.EngSoftPro.models.Cadeira;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.services.filters.FilterExplicadorI;
import edu.ufp.esof.EngSoftPro.services.filters.FilterI;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterCadeiraName implements FilterI<Explicador> {

    private String cadeiraName;

    public ExplicadorFilterCadeiraName(String cadeiraName) {
        this.cadeiraName = cadeiraName;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadores) {
        if(this.cadeiraName==null || this.cadeiraName.isBlank()){
            return explicadores;
        }

        Set<Explicador> cadeirasToBeReturned=new HashSet<>();
        for(Explicador oi:explicadores){
            for(Cadeira lo:oi.getCadeiras()){
                if(lo!=null &&  lo.getName().equals(cadeiraName)){
                    cadeirasToBeReturned.add(oi);
                    break;
                }
            }
        }

        return cadeirasToBeReturned;
    }
}
