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
//import android.view.Menu;

public class PantallaPrincipal extends AppCompatActivity {
    Button btJugar, btOpciones, btCreditos, btSalir;
    SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        //Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbarInicio);
        setSupportActionBar(myToolbar);
        btJugar = findViewById(R.id.btJugar);
        btOpciones = findViewById(R.id.btOpciones);
        btCreditos = findViewById(R.id.btCreditos);
        btSalir = findViewById(R.id.btSalir);
        preferencias = PreferenceManager.getDefaultSharedPreferences(PantallaPrincipal.this);
        if(preferencias.getBoolean("prefk_musica", true)){

           startService(new Intent(PantallaPrincipal.this, Musica.class));
        }else{
            stopService(new Intent(PantallaPrincipal.this, Musica.class));
        }
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


}