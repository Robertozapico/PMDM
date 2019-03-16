package com.example.alumnop.juegosclasicos;

import android.graphics.drawable.Drawable;

public class Carta {
    private String nombreCarta;
    private Drawable imagenCarta;
    private boolean cartaGirada;

    private int numeroCarta;

    public Carta(String nombreCarta, Drawable imagenCarta) {
        this.nombreCarta = nombreCarta;
        this.imagenCarta = imagenCarta;
    }

    public int getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(int numeroCarta) {
        this.numeroCarta = numeroCarta;
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

    public boolean isCartaGirada() {
        return cartaGirada;
    }

    public void setCartaGirada(boolean cartaGirada) {
        this.cartaGirada = cartaGirada;
    }
}
