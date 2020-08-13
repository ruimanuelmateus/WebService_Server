package edu.ufp.esof.services.filters.explicador;

import edu.ufp.esof.models.Disponibilidade;
import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.filters.FilterI;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterDisponibilidadeEndHour implements FilterI<Explicador> {

    private LocalTime endHour;

    public ExplicadorFilterDisponibilidadeEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadores) {
        if(this.endHour==null){
            return explicadores;
        }
        Set<Explicador> explicadoresToReturn=new HashSet<>();
        for(Explicador oi:explicadores){
            for(Disponibilidade di:oi.getDisponibilidades()){
                if(di!=null && di.getDia()!=null && (di.getHoraFim().isBefore(endHour) || di.getHoraFim().equals(endHour))){
                    explicadoresToReturn.add(oi);
                }
            }
        }
        return explicadoresToReturn;
    }
}
