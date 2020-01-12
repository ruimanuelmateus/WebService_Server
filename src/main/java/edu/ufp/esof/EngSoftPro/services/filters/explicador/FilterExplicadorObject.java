package edu.ufp.esof.EngSoftPro.services.filters.explicador;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;


@Data
public class FilterExplicadorObject {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private DayOfWeek dia;
    private String cursoName;
    //private String explicadorName;
    private LocalTime startHour;
    private LocalTime endHour;
    private String idioma;

    public FilterExplicadorObject(DayOfWeek dia, String cursoName, LocalTime startHour, LocalTime endHour, String idioma) {
        this.dia = dia;
        this.cursoName = cursoName;
        //this.explicadorName = explicadorName;
        this.startHour=startHour;
        this.endHour=endHour;
        this.idioma=idioma;
    }

    public FilterExplicadorObject(Map<String, String> searchParams) {
        //this();
        this.cursoName=searchParams.get("curso");
        this.idioma=searchParams.get("idioma");
        String dia=searchParams.get("dia");
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
