package com.example.mastermind;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashSet;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDerecha.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDerecha#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDerecha extends Fragment {
    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7, btPlayAgain;
    private OnFragmentInteractionListener mListener;
    private View fragmentVista;
    public FragmentDerecha() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDerecha.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDerecha newInstance(String param1, String param2) {
        FragmentDerecha fragment = new FragmentDerecha();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentVista = inflater.inflate(R.layout.fragment_fragment_derecha, container, false);
        bt1 = fragmentVista.findViewById(R.id.ficha1);
        bt2 = fragmentVista.findViewById(R.id.ficha2);
        bt3 = fragmentVista.findViewById(R.id.ficha3);
        bt4 = fragmentVista.findViewById(R.id.ficha4);
        bt5 = fragmentVista.findViewById(R.id.ficha5);
        bt6 = fragmentVista.findViewById(R.id.ficha6);
        bt7 = fragmentVista.findViewById(R.id.ficha7);
        btPlayAgain = fragmentVista.findViewById(R.id.btplayagain);
        btPlayAgain.setVisibility(View.INVISIBLE);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fichasListener(R.id.ficha1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fichasListener(R.id.ficha2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fichasListener(R.id.ficha3);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fichasListener(R.id.ficha4);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fichasListener(R.id.ficha5);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fichasListener(R.id.ficha6);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fichasListener(R.id.ficha7);
            }
        });
        btPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fichasListener(R.id.btplayagain);
            }
        });
                return fragmentVista;
    }

    public void actualizarScore(int score){
        TextView tvScore = fragmentVista.findViewById(R.id.tvScore);
        tvScore.setText("Score: "+score);
    }

    public void actualizarFila(int fila){
        if(fila==11){
            fila=fila-1;
        }
        TextView tvFila = fragmentVista.findViewById(R.id.tvFila);
        tvFila.setText("Fila: "+fila);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int idFicha) {
        if (mListener != null) {
            mListener.fichasListener(idFicha);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void fichasListener(int idFicha);
    }

    //Comprueba si la ficha escogida existe en la combinacion ganadora
    public boolean comprobarFichaExiste(List<Integer> combinacionGanadora, List<Integer> fichasJugadas) {
        int fichaCombinacionWin1=0,fichaCombinacionWin2=0,fichaCombinacionWin3=0,fichaCombinacionWin4=0;
        int contador=0;
        for (Integer fichaCombinacionGanadora : combinacionGanadora
        ) {
            switch(contador){
                case 0:
                    fichaCombinacionWin1 = fichaCombinacionGanadora;
                    contador++;
                    break;
                case 1:
                    fichaCombinacionWin2 = fichaCombinacionGanadora;
                    contador++;
                    break;
                case 2:
                    fichaCombinacionWin3 = fichaCombinacionGanadora;
                    contador++;
                    break;
                case 3:
                    fichaCombinacionWin4 = fichaCombinacionGanadora;
                    contador++;
                    break;
            }
        }
        if (fichaCombinacionWin1 == fichasJugadas.get(0) && fichaCombinacionWin2 == fichasJugadas.get(1) && fichaCombinacionWin3 == fichasJugadas.get(2) && fichaCombinacionWin4 == fichasJugadas.get(3)) {
            return true;
        } else {
            return false;
        }
    }

    public void mostrarPlayAgain(){
        btPlayAgain.setVisibility(View.VISIBLE);
    }

    public void desactivarFicha(int fichaUsada){
        Button fichaDescartada;
        switch (fichaUsada) {

            case R.id.ficha1:
                fichaDescartada = fragmentVista.findViewById(R.id.ficha1);
                fichaDescartada.setEnabled(false);
                break;
            case R.id.ficha2:
                fichaDescartada = fragmentVista.findViewById(R.id.ficha2);
                fichaDescartada.setEnabled(false);
                break;
            case R.id.ficha3:
                fichaDescartada = fragmentVista.findViewById(R.id.ficha3);
                fichaDescartada.setEnabled(false);
                break;
            case R.id.ficha4:
                fichaDescartada = fragmentVista.findViewById(R.id.ficha4);
                fichaDescartada.setEnabled(false);
                break;
            case R.id.ficha5:
                fichaDescartada = fragmentVista.findViewById(R.id.ficha5);
                fichaDescartada.setEnabled(false);
                break;
            case R.id.ficha6:
                fichaDescartada = fragmentVista.findViewById(R.id.ficha6);
                fichaDescartada.setEnabled(false);
                break;
        }
    }

public void activarFichas() {
    View fichaDescartada = fragmentVista.findViewById(R.id.ficha1);
    fichaDescartada.setEnabled(true);
    fichaDescartada = fragmentVista.findViewById(R.id.ficha2);
    fichaDescartada.setEnabled(true);
    fichaDescartada = fragmentVista.findViewById(R.id.ficha3);
    fichaDescartada.setEnabled(true);
    fichaDescartada = fragmentVista.findViewById(R.id.ficha4);
    fichaDescartada.setEnabled(true);
    fichaDescartada = fragmentVista.findViewById(R.id.ficha5);
    fichaDescartada.setEnabled(true);
    fichaDescartada = fragmentVista.findViewById(R.id.ficha6);
    fichaDescartada.setEnabled(true);
    }

}
