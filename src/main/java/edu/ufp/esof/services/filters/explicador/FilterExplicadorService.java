package edu.ufp.esof.services.filters.explicador;

import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.filters.AndFilter;
import edu.ufp.esof.services.filters.FilterI;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilterExplicadorService {

    public Set<Explicador> filter(Set<Explicador> explicadores, FilterExplicadorObject filterExplicadorObject) {

        FilterI<Explicador> cursoNameFilter=new ExplicadorFilterCursoName(filterExplicadorObject.getCursoName());
        FilterI<Explicador> diaFilter=new ExplicadorFilterDisponibilidadeDia(filterExplicadorObject.getDia());

        FilterI<Explicador> startHourFilter=new ExplicadorFilterDisponibilidadeStartHour(filterExplicadorObject.getStartHour());
        FilterI<Explicador> endHourFilter=new ExplicadorFilterDisponibilidadeEndHour(filterExplicadorObject.getEndHour());
        FilterI<Explicador> idiomaFilter=new ExplicadorFilterIdioma(filterExplicadorObject.getIdioma());

        //
        //FilterI<Explicador> name=new ExplicadorFilterName(filterExplicadorObject.getExplicadorName());

        FilterI<Explicador> cursoNameAndDiaName=new AndFilter<>(cursoNameFilter,diaFilter);
        FilterI<Explicador> startHourAndEndHour=new AndFilter<>(startHourFilter,endHourFilter);

        FilterI<Explicador> idiomaAndNames=new AndFilter<>(cursoNameAndDiaName,idiomaFilter);

        return new AndFilter<>(idiomaAndNames,startHourAndEndHour).filter(explicadores);
    }

}
