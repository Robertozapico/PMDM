package com.example.alumnop.juegosclasicos;

import android.graphics.drawable.Drawable;

public class Carta {
    private String nombreCarta;
    private Drawable imagenCarta;
    private Drawable reverso;
    private int numeroCarta;

    public Carta(String nombreCarta, Drawable imagenCarta, Drawable reverso) {
        this.nombreCarta = nombreCarta;
        this.imagenCarta = imagenCarta;
        this.reverso = reverso;
    }

    public int getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(int numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public Drawable getReverso() {
        return reverso;
    }

    public void setReverso(Drawable reverso) {
        this.reverso = reverso;
    }

    public String getNombreCarta() {
        return nombreCarta;
    }

    public void setNombreCarta(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    public Drawable getImagenCarta() {
        return imagenCarta;
    }

    public void setImagenCarta(Drawable imagenCarta) {
        this.imagenCarta = imagenCarta;
    }
}
