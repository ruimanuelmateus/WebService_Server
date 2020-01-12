package edu.ufp.esof.EngSoftPro.services.filters.explicador;

import edu.ufp.esof.EngSoftPro.models.Disponibilidade;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.services.filters.FilterExplicadorI;
import edu.ufp.esof.EngSoftPro.services.filters.FilterI;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterDisponibilidadeStartHour implements FilterI<Explicador> {

    private LocalTime startHour;

    public ExplicadorFilterDisponibilidadeStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadores) {
        if(this.startHour==null){
            return explicadores;
        }
        Set<Explicador> explicadoresToReturn=new HashSet<>();
        for(Explicador oi:explicadores){
            for(Disponibilidade di:oi.getDisponibilidades()){
                if(di!=null && di.getDia()!=null && (di.getHoraIni().isAfter(startHour) || di.getHoraIni().equals(startHour))){
                    explicadoresToReturn.add(oi);
                }
            }
        }
        return explicadoresToReturn;
    }
}
