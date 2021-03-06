package com.example.alumnop.juegosclasicos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DerechaFragment.DerechaListener} interface
 * to handle interaction events.
 * Use the {@link DerechaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DerechaFragment extends Fragment {

    private DerechaListener mListener;
    public static List<Carta> cartasFragment;
    private RecyclerView recycler;
    private int cartaEscogida;
    private CartasRecyclerView adapter;

    public DerechaFragment() {
        // Required empty public constructor
    }



    public void setCartasFragment(List<Carta> baraja) {
        this.cartasFragment = baraja;
    }

    public CartasRecyclerView getAdapter() {
        return adapter;
    }

    public DerechaListener getmListener() {
        return mListener;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public void setCartaEscogida(int cartaEscogida) {
        this.cartaEscogida = cartaEscogida;
    }

    public int getCartaEscogida() {
        return cartaEscogida;
    }

    public static DerechaFragment newInstance(String param1, String param2) {
        DerechaFragment fragment = new DerechaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static List<Carta> getCartasFragment() {
        return cartasFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View miVista = inflater.inflate(R.layout.fragment_derecha, container, false);
        return miVista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = view.getContext();
        //recycler = (RecyclerView) view;

        recycler = view.findViewById(R.id.recyclerCartas);
        recycler.setLayoutManager(new GridLayoutManager(context, 9));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new CartasRecyclerView(cartasFragment, getContext());
        //recycler.setAdapter(adapter);
        adapter.setMiAdaptadorClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    mListener.onClickCarta(recycler.getChildAdapterPosition(v));
                    if(cartasFragment.get(cartaEscogida).isCartaGirada()) {
                        //adapter.cambiarCarta(recycler.getChildAdapterPosition(v), v);
                        Carta carta = adapter.getCartas().get(recycler.getChildAdapterPosition(v));
                        ImageView ivCarta;
                        ivCarta = v.findViewById(R.id.ivCarta);
                        ivCarta.setImageDrawable(carta.getImagenCarta());
                    }
                }
            }
        });
        recycler.setAdapter(adapter);
    }


    public void setMiEscuchadorClick(DerechaListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DerechaListener) {
            mListener = (DerechaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface DerechaListener {
        // TODO: Update argument type and name
        void onClickCarta(int numCarta);
    }


}
