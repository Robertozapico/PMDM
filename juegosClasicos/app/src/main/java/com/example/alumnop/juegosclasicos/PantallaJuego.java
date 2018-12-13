package com.example.alumnop.juegosclasicos;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import static com.example.alumnop.juegosclasicos.IzquierdaFragment.numerosCogidos;

public class PantallaJuego extends AppCompatActivity implements DerechaFragment.DerechaListener, IzquierdaFragment.IzquierdaListener {
    private SharedPreferences preferencias;
    private IzquierdaFragment fragmentIzquierda;
    private ImageView cartaCoger;
    private DerechaFragment fragmentDerecha;
    private int carta1, carta2, carta3, carta4;
    public static ArrayList<Carta> cartas = new ArrayList<>();
    public static ArrayList<Carta> imagenesCartas = new ArrayList<>();
    private int score = 0;
    private ImageView imgFp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego);
        cargarDatos();
        asignarCartas();
        cartaCoger = findViewById(R.id.imgIzqCarta5);
        //Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbarJuego);
        setSupportActionBar(myToolbar);
        preferencias = PreferenceManager.getDefaultSharedPreferences(PantallaJuego.this);
        fragmentIzquierda = (IzquierdaFragment) getSupportFragmentManager().findFragmentById(R.id.juegoIzquierdaFragment);
        fragmentDerecha = (DerechaFragment) getSupportFragmentManager().findFragmentById(R.id.juegoDerechaFragment);
        fragmentIzquierda.getTvHighScore().setText("HighScore: " + preferencias.getString("highscore", "0"));
        fragmentIzquierda.getTvScore().setText("Score: " + 0);
        fragmentIzquierda.asignarCartasFragmentIzq(carta1, carta2, carta3, carta4);
        final View recyclerView = findViewById(R.id.recyclerCartas);
        fragmentIzquierda.setMiEscuchadorClick(new IzquierdaFragment.IzquierdaListener() {
            @Override
            public void onClickCartaIzq(int idCarta) {

            }
        });

        fragmentDerecha.setMiEscuchadorClick(new DerechaFragment.DerechaListener() {
            @Override
            public void onClickCarta(int numCarta) {
                //if (numerosCogidos.size()<36) {
                    if (fragmentIzquierda.isCartaRey() == false) {
                        if (cartas.get(fragmentIzquierda.getIntCartaEscogida()).getNumeroCarta() == numCarta) {
                            Toast.makeText(PantallaJuego.this, "CORRECTO", Toast.LENGTH_SHORT).show();
                            score += Integer.parseInt(preferencias.getString("prefk_puntosCartas", ""));
                            fragmentIzquierda.getTvScore().setText("Score: " + score);
                            fragmentDerecha.getCartasFragment().add(cartas.get(fragmentIzquierda.getIntCartaEscogida()));
                            fragmentIzquierda.setCartaEscogida(false);
                            numerosCogidos.add(cartas.get(fragmentIzquierda.getIntCartaEscogida()).getNumeroCarta());
                            fragmentIzquierda.actualizarCarta5();
                        } else {
                            score -= Integer.parseInt(preferencias.getString("prefk_puntosCartaErronea", ""));
                            fragmentIzquierda.getTvScore().setText("Score: " + score);
                            Toast.makeText(PantallaJuego.this, "Fallaste", Toast.LENGTH_SHORT).show();
                        }
                        fragmentDerecha.setCartaEscogida(numCarta);
                    }
                //}
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editorPreferencias = preferencias.edit();

    }

    private void cargarDatos() {
        String[] nombres = getResources().getStringArray(R.array.nombre_cartas);
        TypedArray objetos = getResources().obtainTypedArray(R.array.imagen_cartas);
        Drawable[] imagenes = new Drawable[objetos.length()];
        Drawable reverso = getDrawable(R.drawable.reverso_carta);
        int valorCarta = 0;
        for (int i = 0; i < objetos.length(); i++) {
            imagenes[i] = objetos.getDrawable(i);
        }
        for (int i = 0; i < nombres.length; i++) {
            Carta carta = new Carta(nombres[i], imagenes[i], reverso);
            if (!nombres[i].toString().equals("rey_de_oros") && !nombres[i].toString().equals("rey_de_bastos") && !nombres[i].toString().equals("rey_de_copas") && !nombres[i].toString().equals("rey_de_espadas")) {
                carta.setNumeroCarta(valorCarta);
                valorCarta++;
                //System.out.println(valorCarta);
                //System.out.println(carta.getNumeroCarta());
                cartas.add(carta);
                //cartas.add(new Carta(nombres[i], imagenes[i]));
            } else {
                carta.setNumeroCarta(-1);
            }
            imagenesCartas.add(carta);
        }
    }

    private void asignarCartas() {
        //asignarCartas();
        carta1 = (int) (Math.random() * PantallaJuego.cartas.size());
        while (carta1 == carta2 || carta1 == carta3 || carta1 == carta4 || carta2 == carta3 || carta2 == carta4 || carta3 == carta4) {
            carta2 = (int) (Math.random() * PantallaJuego.cartas.size());
            carta3 = (int) (Math.random() * PantallaJuego.cartas.size());
            carta4 = (int) (Math.random() * PantallaJuego.cartas.size());
        }
    }

    @Override
    public void onClickCarta(int numCarta) {

    }

    @Override
    public void onClickCartaIzq(int idCarta) {

    }

    /*ESTE METODO SIRVE PARA CREAR MENU EN LA TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/
}
