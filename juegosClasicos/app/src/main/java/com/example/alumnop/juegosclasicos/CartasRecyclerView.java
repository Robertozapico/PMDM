package com.example.alumnop.juegosclasicos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class CartasRecyclerView extends RecyclerView.Adapter<CartasRecyclerView.ViewHolder> {
    private List<Carta> cartas;
    private View.OnClickListener onClickListener;
    private int reversoCarta;
    private Context context;


    public CartasRecyclerView(List<Carta> cartas, Context context) {
        this.cartas = cartas;
        this.context = context;
        obtenerReverso();

    }

    public CartasRecyclerView() {
        obtenerReverso();
    }


    public List<Carta> getCartas(Context context) {
        return cartas;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public CartasRecyclerView(List<Carta> cartas) {
        this.cartas = cartas;
        obtenerReverso();
    }

    private void obtenerReverso() {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(context);
        String reverso = preferencias.getString("prefk_cambiarreverso", "Rev3");
        if (reverso.equals("Rev1")) {
            reversoCarta = R.drawable.reverso_carta;
        } else if (reverso.equals("Rev2")) {
            reversoCarta = R.drawable.reverso_hearthstone;
        } else {
            reversoCarta = R.drawable.reverso_magic;
        }
    }



    @NonNull
    @Override
    public CartasRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_cartas, parent, false);

        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartasRecyclerView.ViewHolder holder, int position) {
        Carta carta = cartas.get(position);
        holder.setCarta(carta);
        holder.bindCarta();
        //holder.establecerImagenCarta();
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }
    public View.OnClickListener getMiadaptadorClick(){
    return this.onClickListener;
    }

    public void setMiAdaptadorClick(View.OnClickListener mListener) {
        this.onClickListener = mListener;
    }

    public int cambiarCarta(int pos, View view) {
        Carta carta = cartas.get(pos);
        ImageView ivCarta;
        ivCarta = view.findViewById(R.id.ivCarta);
        //if(carta.isCartaGirada()) {
            ivCarta.setImageDrawable(carta.getImagenCarta());
        //}
        return carta.getNumeroCarta();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCarta;
        private Carta carta;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCarta = itemView.findViewById(R.id.ivCarta);
        }

        public void bindCarta() {
            if (!carta.isCartaGirada()) {
                ivCarta.setImageResource(reversoCarta);
            } else {
                girarCarta();
            }
        }

        public void girarCarta() {
            ivCarta.setImageDrawable(carta.getImagenCarta());
        }
        public void setCarta(Carta carta) {
            this.carta = carta;
        }

        }

    }




