package edu.ufp.esof.EngSoftPro.services.filters.explicacao;

import edu.ufp.esof.EngSoftPro.models.Disponibilidade;
import edu.ufp.esof.EngSoftPro.models.Explicacao;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.services.filters.FilterI;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ExplicacaoFilterEndHour implements FilterI<Explicacao> {

    private LocalTime endHour;

    public ExplicacaoFilterEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    @Override
    public Set<Explicacao> filter(Set<Explicacao> explicacoes) {
        if(this.endHour==null){
            return explicacoes;
        }
        Set<Explicacao> explicacoesToReturn=new HashSet<>();
        for(Explicacao oi:explicacoes){
            if(oi.getFim()!=null && (oi.getFim().isBefore(endHour) || oi.getFim().equals(endHour))){
                explicacoesToReturn.add(oi);
            }
        }
        return explicacoesToReturn;
    }
}
