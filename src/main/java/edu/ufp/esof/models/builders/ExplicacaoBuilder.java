package edu.ufp.esof.models.builders;

import edu.ufp.esof.models.Aluno;
import edu.ufp.esof.models.Cadeira;
import edu.ufp.esof.models.Explicacao;
import edu.ufp.esof.models.Explicador;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ExplicacaoBuilder {

    private Long id;
    private DayOfWeek dia;
    private LocalTime inicio;
    private LocalTime fim;
    private Cadeira cadeira;
    private Aluno aluno;
    private Explicador explicador;

    public ExplicacaoBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ExplicacaoBuilder setDia(DayOfWeek dia) {
        this.dia = dia;
        return this;
    }

    public ExplicacaoBuilder setHoraIni(LocalTime hora) {
        this.inicio = hora;
        return this;
    }

    public ExplicacaoBuilder setHoraFim(LocalTime hora) {
        this.fim = hora;
        return this;
    }

    public ExplicacaoBuilder setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
        return this;
    }

    public ExplicacaoBuilder setAluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public ExplicacaoBuilder setExplicador(Explicador explicador){
        this.explicador=explicador;
        return this;
    }

    public Explicacao build(){
        return new Explicacao(id, dia, inicio, fim, cadeira, aluno, explicador);
    }
}
