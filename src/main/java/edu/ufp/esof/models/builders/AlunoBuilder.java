package edu.ufp.esof.models.builders;

import edu.ufp.esof.models.Aluno;
import edu.ufp.esof.models.Curso;
import edu.ufp.esof.models.Explicacao;

import java.util.HashSet;
import java.util.Set;

public class AlunoBuilder {

    private Long id;
    private String name;
    //private String address;
    private String password;
    //private String phone;
    private Curso curso;
    private Set<Explicacao> explicacoes=new HashSet<>();

    public AlunoBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public AlunoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AlunoBuilder setPassword(String password){
        this.password=password;
        return this;
    }


    public AlunoBuilder setCurso(Curso curso) {
        this.curso = curso;
        return this;
    }


    public AlunoBuilder setExplicacoes(Set<Explicacao> explicacoes) {
        this.explicacoes = explicacoes;
        return this;
    }

    public AlunoBuilder addExplicacao(Explicacao explicacao){
        this.explicacoes.add(explicacao);
        return this;
    }



    public Aluno build() {
        return new Aluno(id, name, password, curso, explicacoes);
    }
}
