package edu.ufp.esof.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor

public class Explicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime inicio;
    private LocalTime fim;
    private DayOfWeek dia;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Cadeira cadeira;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "aluno-explicacao")
    private Aluno aluno;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "explicador-explicacao")
    private Explicador explicador;

    public Explicacao(Long id, DayOfWeek dia, LocalTime inicio, LocalTime end, Cadeira cadeira, Aluno aluno, Explicador explicador){
        this.id=id;
        this.dia=dia;
        this.inicio = inicio;
        this.fim =end;
        this.cadeira=cadeira;
        this.aluno=aluno;
        this.explicador=explicador;
    }


    public boolean overlaps(Explicacao other) {
        return this.isBetween(other) || other.isBetween(this) ||
                (this.inicio.equals(other.inicio) && this.fim.equals(other.fim));
    }


    private boolean isBetween(Explicacao other){
        LocalTime appointmentStartTime=other.getInicio();
        LocalTime appointmentEndTime=other.getFim();
        return this.isBetween(appointmentStartTime) || this.isBetween(appointmentEndTime);
    }
    private boolean isBetween(LocalTime timeToCheck){
        return this.inicio.isBefore(timeToCheck) && this.fim.isAfter(timeToCheck);
    }

}
