package edu.ufp.esof.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Cadeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Cadeira(String name) {
        this.name = name;
    }

    //@OneToMany(mappedBy = "cadeira", cascade = CascadeType.PERSIST)
    //@JsonManagedReference(value = "explicador-cadeira")
    //@ManyToOne(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference (value = "explicador-cadeira")
    @ManyToMany( cascade = CascadeType.PERSIST)
    private Set<Explicador> explicadores=new HashSet<>();

    public void addExplicador(Explicador explicador){
        this.explicadores.add(explicador);
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference (value = "cadeira-curso")
    private Curso curso;


}
