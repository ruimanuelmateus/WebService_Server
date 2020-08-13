package edu.ufp.esof.models;

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
    //private String address;
    private String password;
    //private String phone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "aluno-curso")
    private Curso curso;

    @OneToMany(mappedBy = "aluno",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "aluno-explicacao")
    private Set<Explicacao> explicacoes=new HashSet<>();

    public Aluno(Long id, String name, String password, Curso curso, Set<Explicacao> explicacoes) {
        this.id=id;
        this.name=name;
        this.password=password;
        this.curso=curso;
        this.explicacoes=explicacoes;
    }

    public Aluno(String name){
        this.name=name;
    }


    public void addExplicacao(Explicacao explicacao){
        this.explicacoes.add(explicacao);
        explicacao.setAluno(this);
    }
}
