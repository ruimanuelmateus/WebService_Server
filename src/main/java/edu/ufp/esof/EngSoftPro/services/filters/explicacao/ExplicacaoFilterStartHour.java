package edu.ufp.esof.EngSoftPro.services.filters.explicacao;

import edu.ufp.esof.EngSoftPro.models.Explicacao;
import edu.ufp.esof.EngSoftPro.services.filters.FilterI;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ExplicacaoFilterStartHour implements FilterI<Explicacao> {

    private LocalTime startHour;

    public ExplicacaoFilterStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    @Override
    public Set<Explicacao> filter(Set<Explicacao> explicacoes) {
        if(this.startHour==null){
            return explicacoes;
        }
        Set<Explicacao> explicacoesToReturn=new HashSet<>();
        for(Explicacao oi:explicacoes){
            if(oi.getInicio()!=null && (oi.getInicio().isAfter(startHour) || oi.getInicio().equals(startHour))){
                explicacoesToReturn.add(oi);
            }
        }
        return explicacoesToReturn;
    }

}
