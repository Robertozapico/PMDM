package com.example.mastermind;

import java.util.Date;

public class Jugador {
    private int score;
    private int intentos;
    private Date fechaPartida;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public Date getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(Date fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public void actualizarDatos(Date fecha, int intentos, int score){
        if(intentos==11){
            intentos=intentos-1;
        }
        this.score=score;
        this.intentos=intentos;
        this.fechaPartida=fecha;
    }
}
