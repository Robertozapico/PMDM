package com.example.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class ActivityJuego extends AppCompatActivity implements FragmentDerecha.OnFragmentInteractionListener, FragmentIzquierda.OnFragmentInteractionListener{
    private int contadorIntentos=0;
    private FragmentDerecha fragmentDerecha;
    private FragmentIzquierda fragmentIzquierda;
    private int fichaUsada=-1, colorUsado;
    private Drawable imagenFichaUsada;
    private List<Integer> fichasEscogidas = new ArrayList<>();
    private boolean resultado=false;
    private int score = 100;
    private List<Jugador> listaJugadores;
    private Jugador jugadorActual = new Jugador();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        this.listaJugadores=MainActivity.getListaJugadores();
        fragmentDerecha = (FragmentDerecha) getSupportFragmentManager().findFragmentById(R.id.fragmentFichas);
        fragmentIzquierda = (FragmentIzquierda) getSupportFragmentManager().findFragmentById(R.id.fragmentTablero);
        fichasEscogidas.add(null);
        fichasEscogidas.add(null);
        fichasEscogidas.add(null);
        fichasEscogidas.add(null);
        fragmentDerecha.actualizarScore(score);
        fragmentDerecha.actualizarFila(contadorIntentos+1);

    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_juego,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.menu_home:
                i = new Intent(ActivityJuego.this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_ranking:
                i = new Intent(ActivityJuego.this, Ranking.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Listener Fragments
    //Fragment Izquierdo
    @Override
    public void tableroListener(int idBoton) {
            boolean bfinalJuego=fragmentIzquierda.comprobarFinJuego(contadorIntentos,resultado,fichaUsada);
        if(!bfinalJuego) {
            if (fichaUsada == -1) {
                Toast.makeText(this, "Escoja una ficha", Toast.LENGTH_SHORT).show();
            } else {
                fragmentDerecha.desactivarFicha(fichaUsada);
                fragmentIzquierda.gestionBotonFila(idBoton,contadorIntentos,fichasEscogidas,fichaUsada,colorUsado);
                fichaUsada=-1;
            }
        }
    }
    //Fragment Derecho
    @Override
    public void fichasListener(int idFicha) {
        if (contadorIntentos < 10 && !resultado) {
            if(idFicha == R.id.ficha1 && R.id.ficha1!=fichaUsada){
                Toast.makeText(this, "Ficha verde", Toast.LENGTH_SHORT).show();
                fichaUsada=idFicha;
                colorUsado=R.drawable.ficha_verde;
                imagenFichaUsada = getResources().getDrawable(R.drawable.ficha_verde);
            }else if(idFicha == R.id.ficha2 && R.id.ficha2!=fichaUsada){
                Toast.makeText(this, "Ficha amarilla", Toast.LENGTH_SHORT).show();
                fichaUsada=idFicha;
                colorUsado=R.drawable.ficha_amarillo;
            }else if(idFicha == R.id.ficha3 && R.id.ficha3!=fichaUsada){
                Toast.makeText(this, "Ficha azul", Toast.LENGTH_SHORT).show();
                fichaUsada=idFicha;
                colorUsado=R.drawable.ficha_azul;
            }else if(idFicha == R.id.ficha4 && R.id.ficha4!=fichaUsada){
                Toast.makeText(this, "Ficha roja", Toast.LENGTH_SHORT).show();
                fichaUsada=idFicha;
                colorUsado=R.drawable.ficha_rojo;
            }else if(idFicha == R.id.ficha5 && R.id.ficha5!=fichaUsada){
                Toast.makeText(this, "Ficha rosa", Toast.LENGTH_SHORT).show();
                fichaUsada=idFicha;
                colorUsado=R.drawable.ficha_rosa;
            }else if(idFicha == R.id.ficha6 && R.id.ficha6!=fichaUsada){
                Toast.makeText(this, "Ficha naranja", Toast.LENGTH_SHORT).show();
                fichaUsada=idFicha;
                colorUsado=R.drawable.ficha_naranja;
            }else if(idFicha == R.id.ficha7){
                if(fichasEscogidas.get(0)!=null&&fichasEscogidas.get(1)!=null&&fichasEscogidas.get(2)!=null&&fichasEscogidas.get(3)!=null){
                    fragmentIzquierda.comprobarAciertoFallo(fichasEscogidas, contadorIntentos);
                    Toast.makeText(this, "Comprobado, intento:" +(contadorIntentos+1), Toast.LENGTH_SHORT).show();
                    fragmentIzquierda.desactivarFila(contadorIntentos);
                    fichaUsada=-1;
                    contadorIntentos++;
                    fragmentIzquierda.activarFila(contadorIntentos);
                    fragmentDerecha.activarFichas();
                    resultado = fragmentDerecha.comprobarFichaExiste(fragmentIzquierda.getCombinacionGanadora(), fichasEscogidas);
                    fichasEscogidas.clear();
                    fichasEscogidas.add(null);
                    fichasEscogidas.add(null);
                    fichasEscogidas.add(null);
                    fichasEscogidas.add(null);
                    if(resultado){
                        Toast.makeText(this, "VICTORIA", Toast.LENGTH_SHORT).show();
                        fragmentIzquierda.mostrarCombinacion();
                        fragmentDerecha.mostrarPlayAgain();
                        jugadorActual.actualizarDatos(new Date(),contadorIntentos,score);
                        listaJugadores.add(jugadorActual);
                    }else if(!resultado&&contadorIntentos==10){
                        fragmentIzquierda.mostrarCombinacion();
                        score=score-10;
                        Toast.makeText(this, "DERROTA", Toast.LENGTH_SHORT).show();
                        fragmentDerecha.mostrarPlayAgain();
                        Date fecha = new Date(System.currentTimeMillis());
                        jugadorActual.actualizarDatos(fecha,contadorIntentos,score);
                        listaJugadores.add(jugadorActual);
                    }else{
                        score=score-10;
                    }
                    fragmentDerecha.actualizarFila(contadorIntentos+1);
                    fragmentDerecha.actualizarScore(score);
                }else {
                    Toast.makeText(this, "Faltan fichas por poner", Toast.LENGTH_SHORT).show();
                }
            }else{
                System.out.println("Escoge otra ficha");
            }
        }else if(contadorIntentos==10){
            if (idFicha == R.id.btplayagain){
                finish();
                Intent i = new Intent(ActivityJuego.this, ActivityJuego.class);
                startActivity(i);
            }else {
            Toast.makeText(this, "Numero máximo de intentos alcanzado", Toast.LENGTH_SHORT).show();
            fragmentIzquierda.mostrarCombinacion();
            }
        }else if(resultado){
            if (idFicha == R.id.btplayagain){
                finish();
                Intent i = new Intent(ActivityJuego.this, ActivityJuego.class);
                startActivity(i);
            }else {
                Toast.makeText(this, "VICTORIA", Toast.LENGTH_SHORT).show();
                fragmentIzquierda.mostrarCombinacion();
                jugadorActual.actualizarDatos(new Date(),contadorIntentos,score);
            }

        }
    }


}
