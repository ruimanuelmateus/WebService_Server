package edu.ufp.esof.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek dia;
    private LocalTime horaIni;
    private LocalTime horaFim;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "explicador-disponibilidade")
    private Explicador explicador;

    public Disponibilidade(Long id, DayOfWeek dia, LocalTime horaIni, LocalTime horaFim, Explicador explicador){
        this.id=id;
        this.dia=dia;
        this.horaIni=horaIni;
        this.horaFim=horaFim;
        this.explicador=explicador;
    }

    public boolean contains(Explicacao explicacao){
        DayOfWeek dayOfWeek= explicacao.getDia();
        if(dayOfWeek.equals(this.dia)){
            LocalTime appointmentStart= explicacao.getInicio();
            LocalTime appointmentEnd= explicacao.getFim();
            return this.contains(appointmentStart,appointmentEnd);
        }
        return false;
    }

    private boolean contains(LocalTime start, LocalTime end){
        return (this.horaIni.isBefore(start) || this.horaIni.equals(start))
                &&
                (this.horaFim.isAfter(end) || this.horaFim.equals(end)) ;
    }
}
