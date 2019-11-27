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
    //private String lastName;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference/*(value = "explicador-disponibilidade")*/
    //@JsonBackReference
    private Set<Disponibilidade> disponibilidades=new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "explicador-cadeira")
    private Cadeira cadeira;

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "explicador-explicacao")
    private Set<Explicacao> explicacoes=new HashSet<>();

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "explicador-idioma")
    private Set<Idioma> idiomas=new HashSet<>();

    public Explicador(String nome) {
        this.setName(nome);
    }

    public void addDisponibilidade(Disponibilidade disponibilidade){
        this.disponibilidades.add(disponibilidade);
        disponibilidade.setExplicador(this);
    }



}
