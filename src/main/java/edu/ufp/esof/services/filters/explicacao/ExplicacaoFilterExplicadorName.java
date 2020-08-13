package edu.ufp.esof.services.filters.explicacao;

import edu.ufp.esof.models.Explicacao;
import edu.ufp.esof.services.filters.FilterI;

import java.util.HashSet;
import java.util.Set;

public class ExplicacaoFilterExplicadorName implements FilterI<Explicacao> {

    private String explicadorName;

    public ExplicacaoFilterExplicadorName(String explicadorName) {
        this.explicadorName = explicadorName;
    }

    @Override
    public Set<Explicacao> filter(Set<Explicacao> entities) {
        if(this.explicadorName==null || this.explicadorName.isBlank()){
            return entities;
        }

        Set<Explicacao> explicacoesToBeReturned=new HashSet<>();
        for(Explicacao oi: entities){
            if(oi.getExplicador()!=null && oi.getExplicador().getName()!=null
                    && oi.getExplicador().getName().equals(explicadorName)){
                explicacoesToBeReturned.add(oi);
            }
        }
        return explicacoesToBeReturned;
    }
}
