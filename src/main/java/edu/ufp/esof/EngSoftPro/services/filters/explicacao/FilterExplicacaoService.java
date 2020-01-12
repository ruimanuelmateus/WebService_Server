package edu.ufp.esof.EngSoftPro.services.filters.explicacao;

import edu.ufp.esof.EngSoftPro.models.Explicacao;
import edu.ufp.esof.EngSoftPro.services.filters.AndFilter;
import edu.ufp.esof.EngSoftPro.services.filters.FilterI;

import java.util.Set;

public class FilterExplicacaoService {

    public Set<Explicacao> filter(Set<Explicacao> explicacoes, FilterExplicacaoObject filterExplicacaoObject) {
        FilterI<Explicacao> alunoNameFilter=new ExplicacaoFilterAlunoName(filterExplicacaoObject.getAlunoName());
        FilterI<Explicacao> explicadorNameFilter=new ExplicacaoFilterExplicadorName(filterExplicacaoObject.getExplicadorName());

        FilterI<Explicacao> startHourFilter=new ExplicacaoFilterStartHour(filterExplicacaoObject.getStartHour());
        FilterI<Explicacao> endHourFilter=new ExplicacaoFilterEndHour(filterExplicacaoObject.getEndHour());

        FilterI<Explicacao> alunoNameAndExplicadorName=new AndFilter<>(alunoNameFilter,explicadorNameFilter);
        FilterI<Explicacao> startHourAndEndHour=new AndFilter<>(startHourFilter,endHourFilter);

        return new AndFilter<>(alunoNameAndExplicadorName,startHourAndEndHour).filter(explicacoes);
    }

}
