package com.example.mastermind;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdaptadorRanking extends RecyclerView.Adapter<AdaptadorRanking.ViewHolderScore> {
    private List<Jugador> listaScores;

    public AdaptadorRanking(List<Jugador> listaScores) {
        this.listaScores = listaScores;
    }

    @NonNull
    @Override
    public ViewHolderScore onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_score,null, false);
        return new ViewHolderScore(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderScore viewHolderScore, int i) {
        if(listaScores.size()>=0) {
            viewHolderScore.tvIntentos.setText("Intentos: " + listaScores.get(i).getIntentos());
            viewHolderScore.tvScore.setText("Score: " + listaScores.get(i).getScore());
            Date fechaOriginal = listaScores.get(i).getFechaPartida();
            String fechaTexto = Integer.toString(fechaOriginal.getDate()) + "/" + Integer.toString((fechaOriginal.getMonth())+1) + "/" + Integer.toString((fechaOriginal.getYear())+1900);
            String horaTexto = Integer.toString((fechaOriginal.getHours())) + ":" + Integer.toString(fechaOriginal.getMinutes());
            viewHolderScore.tvFecha.setText("Fecha: " + fechaTexto);
            viewHolderScore.tvHora.setText("Hora: " + horaTexto);
        }
    }

    @Override
    public int getItemCount() {
        return listaScores.size();
    }

    public class ViewHolderScore extends RecyclerView.ViewHolder {
        TextView tvScore, tvIntentos, tvFecha, tvHora;

        public ViewHolderScore(@NonNull View itemView) {
            super(itemView);
            tvScore = (TextView) itemView.findViewById(R.id.tvScoreRanking);
            tvFecha = (TextView) itemView.findViewById(R.id.tvFechaRanking);
            tvHora = (TextView) itemView.findViewById(R.id.tvHoraRanking);
            tvIntentos = (TextView) itemView.findViewById(R.id.tvIntentosRanking);
        }
    }
}
