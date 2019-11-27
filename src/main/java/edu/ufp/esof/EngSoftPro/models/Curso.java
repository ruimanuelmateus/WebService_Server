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
    @JsonBackReference
    private Faculdade faculdade;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Cadeira> cadeiras=new HashSet<>();

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "aluno-curso")
    private Set<Aluno> alunos=new HashSet<>();



}
