package edu.ufp.esof.models.builders;

import edu.ufp.esof.models.*;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorBuilder {

    private Long id;
    private String name;
    private String password;
    private Set<Disponibilidade> disponibilidades=new HashSet<>();
    private Set<Cadeira> cadeiras=new HashSet<>();
    private Set<Explicacao> explicacoes=new HashSet<>();
    private Curso curso;
    private Set<Idioma> idiomas=new HashSet<>();

    public ExplicadorBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ExplicadorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ExplicadorBuilder setPassword(String password){
        this.password=password;
        return this;
    }

    public ExplicadorBuilder setDisponibilidades(Set<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
        return this;
    }

    public ExplicadorBuilder addDisponibilidade(Disponibilidade disponibilidade){
        this.disponibilidades.add(disponibilidade);
        return this;
    }

    public ExplicadorBuilder setCadeiras(Set<Cadeira> cadeiras) {
        this.cadeiras = cadeiras;
        return this;
    }

    public ExplicadorBuilder addCadeira(Cadeira cadeira){
        this.cadeiras.add(cadeira);
        return this;
    }

    public ExplicadorBuilder setExplicacoes(Set<Explicacao> explicacoes) {
        this.explicacoes = explicacoes;
        return this;
    }

    public ExplicadorBuilder addExplicacao(Explicacao explicacao){
        this.explicacoes.add(explicacao);
        return this;
    }

    public ExplicadorBuilder setCurso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public ExplicadorBuilder setIdiomas(Set<Idioma> idiomas) {
        this.idiomas = idiomas;
        return this;
    }

    public ExplicadorBuilder addIdioma(Idioma idioma){
        this.idiomas.add(idioma);
        return this;
    }

    public Explicador build(){
        return new Explicador(id, name, password, disponibilidades, cadeiras, explicacoes, curso, idiomas);
    }

}
