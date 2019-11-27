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
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "aluno-curso")
    private Curso curso;

    @OneToMany(mappedBy = "aluno",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "aluno-explicacao")
    private Set<Explicacao> explicacoes=new HashSet<>();

    public Aluno(String name) {
        this.setName(name);
    }
}
