package edu.ufp.esof.models.builders;

import edu.ufp.esof.models.Disponibilidade;
import edu.ufp.esof.models.Explicador;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DisponibilidadeBuilder {

    private Long id;
    private DayOfWeek dia;
    private LocalTime horaIni;
    private LocalTime horaFim;
    private Explicador explicador;

    public DisponibilidadeBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public DisponibilidadeBuilder setDia(DayOfWeek dia) {
        this.dia = dia;
        return this;
    }

    public DisponibilidadeBuilder setHoraIni(LocalTime hora) {
        this.horaIni = hora;
        return this;
    }

    public DisponibilidadeBuilder setHoraFim(LocalTime hora) {
        this.horaFim = hora;
        return this;
    }

    public DisponibilidadeBuilder setExplicador(Explicador explicador){
        this.explicador=explicador;
        return this;
    }

    public Disponibilidade build(){
        return new Disponibilidade(id, dia, horaIni, horaFim, explicador);
    }
}
