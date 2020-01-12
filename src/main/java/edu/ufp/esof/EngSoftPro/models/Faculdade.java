package edu.ufp.esof.EngSoftPro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Faculdade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "faculdade",cascade = CascadeType.PERSIST)
    @JsonManagedReference (value = "uni-curso")
    private Set<Curso> cursos=new HashSet<>();

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.addFaculdade(this);
    }

    public Faculdade(String name){
        this.name=name;
    }


}
