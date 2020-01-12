package edu.ufp.esof.EngSoftPro.services.filters.explicacao;

import edu.ufp.esof.EngSoftPro.models.Explicacao;
import edu.ufp.esof.EngSoftPro.services.filters.FilterI;

import java.util.HashSet;
import java.util.Set;

public class ExplicacaoFilterAlunoName implements FilterI<Explicacao> {

    private String alunoName;

    public ExplicacaoFilterAlunoName(String alunoName) {
        this.alunoName = alunoName;
    }

    @Override
    public Set<Explicacao> filter(Set<Explicacao> entities) {
        if(this.alunoName==null || this.alunoName.isBlank()){
            return entities;
        }

        Set<Explicacao> explicacoesToBeReturned=new HashSet<>();
        for(Explicacao oi: entities){
            if(oi.getAluno()!=null && oi.getAluno().getName()!=null
                    && oi.getAluno().getName().equals(alunoName)){
                explicacoesToBeReturned.add(oi);
            }
        }
        return explicacoesToBeReturned;
    }
}
