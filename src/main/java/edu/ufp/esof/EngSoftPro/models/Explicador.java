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
public class Explicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    //private String address;
    //private String phone;

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "explicador-disponibilidade")
    private Set<Disponibilidade> disponibilidades=new HashSet<>();

    //@OneToMany(mappedBy = "cadeira", cascade = CascadeType.PERSIST)
    //@JsonManagedReference(value = "explicador-cadeira")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    //@JsonBackReference (value = "explicador-cadeira")
    @ManyToMany(mappedBy = "explicadores", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Set<Cadeira> cadeiras=new HashSet<>();

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "explicador-explicacao")
    private Set<Explicacao> explicacoes=new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.ALL})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference (value = "exp-curso")
    private Curso curso;

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "explicador-idioma")
    private Set<Idioma> idiomas=new HashSet<>();

    public Explicador(String name){
        this.setName(name);
    }

    public Explicador(Long id, String name, String password, Set<Disponibilidade> disponibilidades, Set<Cadeira> cadeiras, Set<Explicacao> explicacoes,
                      Curso curso, Set<Idioma> idiomas) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
        this.setDisponibilidades(disponibilidades);
        this.setCadeiras(cadeiras);
        this.setExplicacoes(explicacoes);
        this.setCurso(curso);
        this.setIdiomas(idiomas);
    }

    public void addCadeira(Cadeira cadeira){
        this.cadeiras.add(cadeira);
        cadeira.addExplicador(this);
    }

    public void addCurso(Curso curso){
        this.setCurso(curso);
        curso.addExplicador(this);
    }

    public void removeDisponibilidade(){
        this.disponibilidades.clear();
    }


    public void addDisponibilidade(Disponibilidade disponibilidade){
        this.disponibilidades.add(disponibilidade);
        disponibilidade.setExplicador(this);
    }



    public void addExplicacao(Explicacao explicacao){
        this.explicacoes.add(explicacao);
        explicacao.setExplicador(this);
        Aluno aluno = explicacao.getAluno();
        if(aluno !=null && !aluno.getExplicacoes().contains(explicacao)){
            aluno.addExplicacao(explicacao);
        }
    }

    public boolean canSchedule(Explicacao explicacao) {
        for(Disponibilidade disponibilidade:this.disponibilidades){
            if(disponibilidade.contains(explicacao)){
                return true;
            }
        }
        return false;
    }


}
