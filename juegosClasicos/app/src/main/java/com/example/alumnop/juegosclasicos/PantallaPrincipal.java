package com.example.alumnop.juegosclasicos;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
//import android.view.Menu;

public class PantallaPrincipal extends AppCompatActivity /*implements View.OnClickListener*/ {
    private Button btJugar, btOpciones, btCreditos, btSalir;
    private SharedPreferences preferencias;

    private GestionPosicionMusica gestionMusica;
    private int posMusica;
    Intent servicioMusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        //Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbarInicio);
        setSupportActionBar(myToolbar);

        //botones
        btJugar = findViewById(R.id.btJugar);
        btOpciones = findViewById(R.id.btOpciones);
        btCreditos = findViewById(R.id.btCreditos);
        btSalir = findViewById(R.id.btSalir);



        preferencias = PreferenceManager.getDefaultSharedPreferences(PantallaPrincipal.this);

        btJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaPrincipal.this, PantallaJuego.class);
                startActivity(i);
            }
        });

        btOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaPrincipal.this, Opciones.class);
                startActivity(i);
            }
        });
        btCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PantallaPrincipal.this, R.style.AlertDialogCustom);
                builder.setTitle("Créditos");
                builder.setMessage("Esta aplicación ha sido creada por Roberto Zapico Blanco alumno de DAM2");
                builder.setCancelable(false);
                builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        reproductorMusica();
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        gestionMusica = new GestionPosicionMusica();
        IntentFilter pausaMusica = new IntentFilter();
        pausaMusica.addAction(Musica.PAUSA_MUSICA);
        registerReceiver(gestionMusica, pausaMusica);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(gestionMusica);
    }

    private void reproductorMusica() {
        preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        boolean activado =  preferencias.getBoolean("prefk_musica", true);
        if (activado==true) {
            if (servicioMusica == null) {
                servicioMusica = new Intent(PantallaPrincipal.this, Musica.class);
                //le manda a MusicaService la posición
                servicioMusica.putExtra("POS", posMusica);
                startService(servicioMusica);
            }
        }
        if (activado==false && servicioMusica != null) {
            stopService(servicioMusica);
            servicioMusica = null;
        }
    }





/*
    @Override
    public void onClick(View v) {
        Button botonPulsado = (Button) v;
        Intent i = null;
        switch (botonPulsado.getId()) {
            case R.id.btPrincipalJugar:
                i = new Intent(PantallaPrincipalActivity.this, JuegoActivity.class);
                startActivity(i);
                break;
            case R.id.btPrincipalOpciones:
                i = new Intent(PantallaPrincipalActivity.this, ConfiguracionActivity.class);
                startActivity(i);
                break;
            case R.id.btPrincipalCreditos:
                i = new Intent(PantallaPrincipalActivity.this,CreditosActivity.class);
                startActivity(i);
                break;
            case R.id.btPrincipalSalir:
                if (servicioDeMusica != null) {
                    stopService(servicioDeMusica);
                }
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = null;
        switch (item.getItemId()) {
            case R.id.toolbar_perfil:
                i = new Intent(PantallaPrincipalActivity.this, PerfilActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_principal:
                Toast.makeText(PantallaPrincipalActivity.this, "Ya estás en la pantalla principal", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_ranking:
                i = new Intent(PantallaPrincipalActivity.this, RankingActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_configuracion:
                i = new Intent(PantallaPrincipalActivity.this, ConfiguracionActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_configuracion2:
                i = new Intent(PantallaPrincipalActivity.this, ConfiguracionActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_cambiarJuego:
                Toast.makeText(PantallaPrincipalActivity.this, "Cambiamos de juego...", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
*/


    //guardo el propio servicio y lo recupero de nuevo para evitar que se tengan que realizar demasiadas consultas
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("SERVICIOMUSIC", servicioMusica);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("SERVICIOMUSIC")) {
                servicioMusica = savedInstanceState.getParcelable("SERVICIOMUSIC");
            }
        }
    }

    //
    public class GestionPosicionMusica extends BroadcastReceiver {

        public GestionPosicionMusica() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Musica.PAUSA_MUSICA)) { //de entre todas las acciones que se reciben, primer filtrado por el tipo de acción
                if (intent.hasExtra("POS")) {  //segundo filtrado por key
                    posMusica = intent.getIntExtra("POS", 0);
                } else {
                    posMusica = 0;
                }
            }
        }
    }

/* @Override
    protected void onStart() {
        super.onStart();
        receptorMusica = new ReceptorPosicionMusica();
        IntentFilter miFiltro = new IntentFilter();
        miFiltro.addAction(MusicaService.ACCION_PAUSA_MUSICA);
        registerReceiver(receptorMusica, miFiltro);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receptorMusica);

    }


    @Override
    public void onClick(View v) {
        Button botonPulsado = (Button) v;
        Intent i = null;
        switch (botonPulsado.getId()) {
            case R.id.btPrincipalJugar:
                i = new Intent(PantallaPrincipalActivity.this, JuegoActivity.class);
                startActivity(i);
                break;
            case R.id.btPrincipalOpciones:
                i = new Intent(PantallaPrincipalActivity.this, ConfiguracionActivity.class);
                startActivity(i);
                break;
            case R.id.btPrincipalCreditos:
                i = new Intent(PantallaPrincipalActivity.this,CreditosActivity.class);
                startActivity(i);
                break;
            case R.id.btPrincipalSalir:
                if (servicioDeMusica != null) {
                    stopService(servicioDeMusica);
                }
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = null;
        switch (item.getItemId()) {
            case R.id.toolbar_perfil:
                i = new Intent(PantallaPrincipalActivity.this, PerfilActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_principal:
                Toast.makeText(PantallaPrincipalActivity.this, "Ya estás en la pantalla principal", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_ranking:
                i = new Intent(PantallaPrincipalActivity.this, RankingActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_configuracion:
                i = new Intent(PantallaPrincipalActivity.this, ConfiguracionActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_configuracion2:
                i = new Intent(PantallaPrincipalActivity.this, ConfiguracionActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_cambiarJuego:
                Toast.makeText(PantallaPrincipalActivity.this, "Cambiamos de juego...", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //guardo el propio servicio y lo recupero de nuevo para evitar que se tengan que realizar demasiadas consultas
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("SERVICIO", servicioDeMusica);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("SERVICIO")) {
                servicioDeMusica = savedInstanceState.getParcelable("SERVICIO");
            }
        }
    }

    public class ReceptorPosicionMusica extends BroadcastReceiver {

        public ReceptorPosicionMusica() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MusicaService.ACCION_PAUSA_MUSICA)) { //de entre todas las acciones que se reciben, primer filtrado por el tipo de acción
                if (intent.hasExtra("POSICION")) {  //segundo filtrado por key
                    posicionPista = intent.getIntExtra("POSICION", 0);
                } else {
                    posicionPista = 0;
                }
            }
        }
    }*/

}