package com.example.mastermind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking extends AppCompatActivity {
    private List<Jugador> listaJugadores;
    private RecyclerView recyclerScores;
    private TextView tvAlerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        tvAlerta = findViewById(R.id.tvAlerta);
        this.listaJugadores = MainActivity.getListaJugadores();
        if(listaJugadores.size()>0){
            tvAlerta.setVisibility(View.INVISIBLE);
            ordenarRanking();
        }else{
            tvAlerta.setVisibility(View.VISIBLE);
        }
        recyclerScores = (RecyclerView) findViewById(R.id.RecyclerId);
        recyclerScores.setLayoutManager(new LinearLayoutManager(this));

        AdaptadorRanking adapter = new AdaptadorRanking(listaJugadores);
        recyclerScores.setAdapter(adapter);


    }

    public void ordenarRanking(){
        Collections.sort(listaJugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador o1, Jugador o2) {
                return o2.getScore()-(o1.getScore());
            }
        });
    }

}
