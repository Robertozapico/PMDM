package com.example.mastermind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Jugador> listaJugadoresGeneral = new ArrayList<>();
    private Button btJugar;
    public static ArrayList<Jugador> getListaJugadores() {
        return listaJugadoresGeneral;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btJugar = findViewById(R.id.btJugar);

        btJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ActivityJuego.class);
                startActivity(i);
            }
        });
    }
}
