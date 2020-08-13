package edu.ufp.esof.services.filters.explicador;

import edu.ufp.esof.models.Cadeira;
import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.filters.FilterI;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterCursoName implements FilterI<Explicador> {

    private String cursoName;

    public ExplicadorFilterCursoName(String cursoName) {
        this.cursoName = cursoName;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadores) {
        if(this.cursoName==null || this.cursoName.isBlank()){
            return explicadores;
        }

        Set<Explicador> explicadoresToBeReturned=new HashSet<>();
        for(Explicador oi:explicadores){
            for(Cadeira lo:oi.getCadeiras()){
                if(lo!=null && lo.getCurso().getName().equals(cursoName)){
                    explicadoresToBeReturned.add(oi);
                    break;
                }
            }
        }

        return explicadoresToBeReturned;
    }

}
