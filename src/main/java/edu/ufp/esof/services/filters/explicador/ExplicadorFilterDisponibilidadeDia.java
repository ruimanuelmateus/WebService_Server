package edu.ufp.esof.services.filters.explicador;

import edu.ufp.esof.models.Disponibilidade;
import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.filters.FilterI;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterDisponibilidadeDia implements FilterI<Explicador> {

    private DayOfWeek dia;

    public ExplicadorFilterDisponibilidadeDia(DayOfWeek dia) {
        this.dia = dia;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadores) {
        if(this.dia==null){
            return explicadores;
        }
        Set<Explicador> explicadoresToReturn=new HashSet<>();
        for(Explicador oi:explicadores){
            for(Disponibilidade d:oi.getDisponibilidades()){
                if(d.getDia().equals(this.dia)) {
                    explicadoresToReturn.add(oi);
                }
            }
        }
        return explicadoresToReturn;
    }
}
