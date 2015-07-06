package com.egm.alejandro.tiempo;

/**
 * Created by Alejandro on 05/06/2015.
 */
public class List {

    private String dia;
    private String estado;
    private String temperatura;
    private int image;

    public List(String dia, String estado, String temperatura, int image) {
        this.dia = dia;
        this.estado = estado;
        this.temperatura = temperatura;
        this.image = image;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }



}