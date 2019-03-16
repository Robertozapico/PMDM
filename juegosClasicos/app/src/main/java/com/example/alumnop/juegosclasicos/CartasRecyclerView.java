package com.example.alumnop.juegosclasicos;

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

    public CartasRecyclerView(List<Carta> cartas) {
        this.cartas = cartas;
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
        holder.bindCarta(carta);
    }

    @Override
    public int getItemCount() {
        return cartas.size();
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

        public ViewHolder(View itemView) {
            super(itemView);
            ivCarta = itemView.findViewById(R.id.ivCarta);
        }

        public void bindCarta(Carta carta) {
            //PARA PONER REVERSO PONER EL GETREVERSO
            //ivCarta.setImageDrawable(carta.getImagenCarta());
            //ivCarta.setImageDrawable(carta.getReverso());
        }

    }



}

