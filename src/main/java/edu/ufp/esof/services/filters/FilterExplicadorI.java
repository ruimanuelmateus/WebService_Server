package edu.ufp.esof.services.filters;

import edu.ufp.esof.models.Explicador;

import java.util.Set;

public interface FilterExplicadorI {
    Set<Explicador> filter(Set<Explicador> explicadores);
}
