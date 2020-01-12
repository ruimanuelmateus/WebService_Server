package edu.ufp.esof.EngSoftPro.services.filters;

import edu.ufp.esof.EngSoftPro.models.Disponibilidade;
import edu.ufp.esof.EngSoftPro.models.Explicador;

import java.util.Set;

public interface FilterExplicadorI {
    Set<Explicador> filter(Set<Explicador> explicadores);
}
