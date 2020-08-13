package edu.ufp.esof.services.filters.explicacao;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Data
public class FilterExplicacaoObject {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
/*
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

 */
    private DayOfWeek dia;
    private LocalTime startHour;
    private LocalTime endHour;
    private String explicadorName;
    private String alunoName;


    public FilterExplicacaoObject(String explicadorName, String alunoName, LocalTime startHour, LocalTime endHour, DayOfWeek dia) {
        this.startHour=startHour;
        this.endHour = endHour;
        this.explicadorName = explicadorName;
        this.alunoName = alunoName;
    }

    public FilterExplicacaoObject(Map<String, String> searchParams) {
        this.alunoName=searchParams.get("aluno");
        this.explicadorName=searchParams.get("explicador");

        String dia=searchParams.get("dia");
        //String endDate=searchParams.get("endDate");
        DayOfWeek dia1=null;
        String horaI=searchParams.get("startHour");
        String horaF=searchParams.get("endHour");
        LocalTime horaIni=null;
        LocalTime horaFim=null;

        try{
            dia1=DayOfWeek.valueOf(dia);
            horaIni=LocalTime.parse(horaI);
            horaFim=LocalTime.parse(horaF);
        }catch (Exception e){
            //e.printStackTrace();
            this.logger.error(e.getMessage());
        }

        this.dia=dia1;
        this.startHour=horaIni;
        this.endHour=horaFim;
    }
}
