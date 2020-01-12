package edu.ufp.esof.EngSoftPro.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference (value = "uni-curso")
    private Faculdade faculdade;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference (value = "cadeira-curso")
    private Set<Cadeira> cadeiras=new HashSet<>();

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "aluno-curso")
    private Set<Aluno> alunos=new HashSet<>();

    @OneToMany(mappedBy = "curso",cascade = {CascadeType.PERSIST,CascadeType.ALL})
    @JsonManagedReference(value = "exp-curso")
    //@EqualsAndHashCode.Exclude
    private Set<Explicador> explicadores=new HashSet<>();

    public Curso(String name){
        this.name=name;
    }

    public void addFaculdade(Faculdade faculdade) {
        this.faculdade=faculdade;
    }

    public void addExplicador(Explicador explicador){
        this.explicadores.add(explicador);
    }

    public void addCadeira(Cadeira cadeira){
        this.cadeiras.add(cadeira);
    }

    public void removeExplicador(Explicador explicador){
        this.explicadores.remove(explicador);
    }

}
