package com.example.listamedicamentos.entities;

import android.content.ContentValues;

import com.example.listamedicamentos.DBContract.MedicamentosContract;

import java.io.Serializable;
import java.util.UUID;

/*
* Informacion de los medicamentos
* */
public class Medicamento implements Serializable {

    public String id;
    private String name;
    private String dosis;
    private Boolean notificaciones;
    private String observaciones;
    private HORARIO horario;

    /*
    * Contructor por defecto
    * */
    public Medicamento(String _name, String _dosis, HORARIO _horario, Boolean _notificaciones, String _obs){

        this.id =  UUID.randomUUID().toString();
        this.name = _name;
        this.dosis = _dosis;
        this.horario = _horario;
        this.notificaciones = _notificaciones;
        this.observaciones = _obs;

    }

    /*
     * Devuelve el nombre del medicamento
     * */
    public String getName(){
        return this.name;
    }

    /*
     * Seteas el nombre del medicamento
     * */
    public void setName(String _name){
        this.name = _name;
    }

    /*
     * Devuelve la dosis del medicamento
     * */
    public String getDosis(){
        return this.dosis;
    }

    /*
     * Seteas la dosis del medicamento
     * */
    public void setDosis(String _dosis){
        this.dosis = _dosis;
    }

    /*
     * Devuelve las observaciones del medicamento
     * */
    public String getObservaciones(){
        return this.observaciones;
    }

    /*
     * Seteas la dosis del medicamento
     * */
    public void setObservaciones(String _obs){
        this.observaciones = _obs;
    }

    /*
     * Devuelve las notificaciones del medicamento
     * */
    public String getNotificaciones(){
        return this.notificaciones ? "Activado" : "Desactivado";
    }

    /*
     * Setea las notificaciones del medicamento
     * */
    public void setNotificaciones(boolean _not){
        this.notificaciones = _not;
    }

    /*
     * Devuelve el horario del medicamento
     * */
    public String getHorario(){
        return this.horario.toString();
    }

    /*
     * Devuelve el horario del medicamento
     * */
    public int getHorarioEnum(){
        if(this.horario == HORARIO.MAÑANA)
            return 0;
        else if(this.horario == HORARIO.MEDIODIA)
            return 1;
        else
            return 2;
    }

    /*
     * Setea el horario del medicamento
     * */
    public void setHorario(HORARIO _horario){
        this.horario = _horario;
    }

    /*
    * Devuelve la informacion concreta para guardarlo en la bbdd
    * */
    public ContentValues toContentValues() {

        ContentValues row = new ContentValues();

        row.put(MedicamentosContract.MedicamentoEntry.ID, this.id);
        row.put(MedicamentosContract.MedicamentoEntry.NAME, this.name);
        row.put(MedicamentosContract.MedicamentoEntry.DOSIS, this.dosis);
        row.put(MedicamentosContract.MedicamentoEntry.HORARIO,this.horario.toString());
        row.put(MedicamentosContract.MedicamentoEntry.NOTIFICACIONES, this.notificaciones.toString());
        row.put(MedicamentosContract.MedicamentoEntry.OBSERVACIONES, this.observaciones);

        return row;

    }
}


