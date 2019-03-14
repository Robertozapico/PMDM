package com.example.alumnop.juegosclasicos;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Opciones extends AppCompatActivity {
    SharedPreferences preferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        //Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbarOpciones);
        setSupportActionBar(myToolbar);



        /*
        DE ESTA FORMA SE PONE ENCIMA DEL TOOLBAR, POR ESO LO HAGO EN EL ACTIVITY COMO FRAGMENT
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new OpcionesFragment())
                .commit();
*/

    }
}
