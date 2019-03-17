package com.example.alumnop.juegosclasicos;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
    private SoundPool efectosSonido = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
    private int efectoCartas;

    private CartasRecyclerView adapter;


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
        fragmentDerecha.setCartasFragment(cartas);


        fragmentIzquierda.setMiEscuchadorClick(new IzquierdaFragment.IzquierdaListener() {
            @Override
            public void onClickCartaIzq(int idCarta) {
            }
        });


        fragmentDerecha.setMiEscuchadorClick(new DerechaFragment.DerechaListener() {
            @Override
            public void onClickCarta(int numCarta) {
                //if (numerosCogidos.size()<36) {
                efectoSonido();
                fragmentDerecha.setCartaEscogida(numCarta);



                    if (fragmentIzquierda.isCartaRey() == false) {

                        if (cartas.get(fragmentIzquierda.getIntCartaEscogida()).getNumeroCarta() == numCarta) {
                            //estas dos lineas de score da error al recoger el string
                            //score += Integer.parseInt(preferencias.getString("prefk_puntosCartas", ""));



                            fragmentDerecha.getCartasFragment().get(numCarta).setCartaGirada(true);






                            Toast.makeText(PantallaJuego.this, "CORRECTO", Toast.LENGTH_SHORT).show();
                            fragmentIzquierda.getTvScore().setText("Score: " + score);
                            fragmentIzquierda.setCartaEscogida(false);
                            numerosCogidos.add(cartas.get(fragmentIzquierda.getIntCartaEscogida()).getNumeroCarta());
                            fragmentIzquierda.actualizarCarta5();



                        } else {
                            fragmentDerecha.getCartasFragment().get(numCarta).setCartaGirada(false);
                            //score -= Integer.parseInt(preferencias.getString("prefk_puntosCartaErronea", ""));
                            fragmentIzquierda.getTvScore().setText("Score: " + score);
                            Toast.makeText(PantallaJuego.this, "Fallaste", Toast.LENGTH_SHORT).show();
                        }

                    }

                //}
            }

        });
        efectoCartas = efectosSonido.load(this, R.raw.pick_sound, 1);
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
        int valorCarta = 0;
        for (int i = 0; i < objetos.length(); i++) {
            imagenes[i] = objetos.getDrawable(i);
        }
        //Para que no se dupliquen al volver a entrar en la activity
        if (cartas.size() != 0) {
            cartas.clear();
        }
        for (int i = 0; i < objetos.length(); i++) {
            Carta carta = new Carta(nombres[i], imagenes[i]);
            if (!nombres[i].toString().equals("rey_de_oros") && !nombres[i].toString().equals("rey_de_bastos") && !nombres[i].toString().equals("rey_de_copas")
                    && !nombres[i].toString().equals("rey_de_espadas" )) {
                carta.setNumeroCarta(valorCarta);
                valorCarta++;
                cartas.add(carta);
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


    private void efectoSonido() {
        boolean sonidoActivado= preferencias.getBoolean("prefk_sonidos", true);
        if (sonidoActivado) {
                efectosSonido.play(efectoCartas, 1, 1, 0, 0, 1);
        }
    }



    /*ESTE METODO SIRVE PARA CREAR MENU EN LA TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/
}
