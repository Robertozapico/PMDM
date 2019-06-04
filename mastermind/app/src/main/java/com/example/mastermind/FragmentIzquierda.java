package com.example.mastermind;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentIzquierda.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentIzquierda#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentIzquierda extends Fragment/* implements View.OnClickListener  */{
    private List<Integer> combinacionGanadora = new ArrayList<>();
    private List<ImageView> coleccionCombinacion = new ArrayList<>();
    private ImageView ivCombinacion1, ivCombinacion2, ivCombinacion3, ivCombinacion4;
    private OnFragmentInteractionListener mListener;
    private List<Integer> lFilas = new ArrayList<>();
    private List<Integer> lCirculitos = new ArrayList<>();
    private List<Integer> lnumerosCombinacion = new ArrayList<>();
    View fragmentVista;
    public FragmentIzquierda() {
        // Required empty public constructor
    }

    public List<Integer> getlFilas() {
        return lFilas;
    }

    public List<Integer> getCombinacionGanadora(){
        return combinacionGanadora;
    }

    public static FragmentIzquierda newInstance(String param1, String param2) {
        FragmentIzquierda fragment = new FragmentIzquierda();
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
        fragmentVista = inflater.inflate(R.layout.fragment_fragment_izquierda, container, false);

        lCirculitos.add(R.id.ivCirculito1);
        lCirculitos.add(R.id.ivCirculito2);
        lCirculitos.add(R.id.ivCirculito3);
        lCirculitos.add(R.id.ivCirculito4);
        lFilas.add(R.id.fila1);
        lFilas.add(R.id.fila2);
        lFilas.add(R.id.fila3);
        lFilas.add(R.id.fila4);
        lFilas.add(R.id.fila5);
        lFilas.add(R.id.fila6);
        lFilas.add(R.id.fila7);
        lFilas.add(R.id.fila8);
        lFilas.add(R.id.fila9);
        lFilas.add(R.id.fila10);
        //Desactiva los botones de las filas y asigna el numero y los onclickslistener
        int contadorFilas=1;
        for (Integer filaCogida:lFilas) {
            View filaDesactiva = fragmentVista.findViewById(filaCogida);
            TextView numFila = filaDesactiva.findViewById(R.id.tvNumFila);
            numFila.setText(Integer.toString(contadorFilas));
            contadorFilas++;
            if(filaDesactiva!=fragmentVista.findViewById(R.id.fila1)) {
                Button boton1 = filaDesactiva.findViewById(R.id.bt1);
                Button boton2 = filaDesactiva.findViewById(R.id.bt2);
                Button boton3 = filaDesactiva.findViewById(R.id.bt3);
                Button boton4 = filaDesactiva.findViewById(R.id.bt4);
                boton1.setEnabled(false);
                boton2.setEnabled(false);
                boton3.setEnabled(false);
                boton4.setEnabled(false);
            }
        }
        Button boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9, boton10,boton11,boton12,boton13,boton14,boton15,boton16,boton17,boton18,boton19,boton20,boton21,boton22,boton23,boton24, boton25, boton26, boton27, boton28, boton29, boton30, boton31, boton32, boton33, boton34, boton35, boton36, boton37, boton38, boton39, boton40;

                //SetOnClickListener
        View filaAuxiliar = fragmentVista.findViewById(R.id.fila1);
        boton1=filaAuxiliar.findViewById(R.id.bt1);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton2=filaAuxiliar.findViewById(R.id.bt2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton3=filaAuxiliar.findViewById(R.id.bt3);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton4=filaAuxiliar.findViewById(R.id.bt4);
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila2);
        boton5=filaAuxiliar.findViewById(R.id.bt1);
        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton6=filaAuxiliar.findViewById(R.id.bt2);
        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton7=filaAuxiliar.findViewById(R.id.bt3);
        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton8=filaAuxiliar.findViewById(R.id.bt4);
        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila3);
        boton9=filaAuxiliar.findViewById(R.id.bt1);
        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton10=filaAuxiliar.findViewById(R.id.bt2);
        boton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton11=filaAuxiliar.findViewById(R.id.bt3);
        boton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton12=filaAuxiliar.findViewById(R.id.bt4);
        boton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila4);
        boton13=filaAuxiliar.findViewById(R.id.bt1);
        boton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton14=filaAuxiliar.findViewById(R.id.bt2);
        boton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton15=filaAuxiliar.findViewById(R.id.bt3);
        boton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton16=filaAuxiliar.findViewById(R.id.bt4);
        boton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila5);
        boton17=filaAuxiliar.findViewById(R.id.bt1);
        boton17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton18=filaAuxiliar.findViewById(R.id.bt2);
        boton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton19=filaAuxiliar.findViewById(R.id.bt3);
        boton19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton20=filaAuxiliar.findViewById(R.id.bt4);
        boton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila6);
        boton21=filaAuxiliar.findViewById(R.id.bt1);
        boton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton22=filaAuxiliar.findViewById(R.id.bt2);
        boton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton23=filaAuxiliar.findViewById(R.id.bt3);
        boton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton24=filaAuxiliar.findViewById(R.id.bt4);
        boton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila7);
        boton25=filaAuxiliar.findViewById(R.id.bt1);
        boton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton26=filaAuxiliar.findViewById(R.id.bt2);
        boton26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton27=filaAuxiliar.findViewById(R.id.bt3);
        boton27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton28=filaAuxiliar.findViewById(R.id.bt4);
        boton28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila8);
        boton29=filaAuxiliar.findViewById(R.id.bt1);
        boton29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton30=filaAuxiliar.findViewById(R.id.bt2);
        boton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton31=filaAuxiliar.findViewById(R.id.bt3);
        boton31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton32=filaAuxiliar.findViewById(R.id.bt4);
        boton32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila9);
        boton33=filaAuxiliar.findViewById(R.id.bt1);
        boton33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton34=filaAuxiliar.findViewById(R.id.bt2);
        boton34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton35=filaAuxiliar.findViewById(R.id.bt3);
        boton35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton36=filaAuxiliar.findViewById(R.id.bt4);
        boton36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        filaAuxiliar = fragmentVista.findViewById(R.id.fila10);
        boton37=filaAuxiliar.findViewById(R.id.bt1);
        boton37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt1);
            }
        });
        boton38=filaAuxiliar.findViewById(R.id.bt2);
        boton38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt2);
            }
        });
        boton39=filaAuxiliar.findViewById(R.id.bt3);
        boton39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt3);
            }
        });
        boton40=filaAuxiliar.findViewById(R.id.bt4);
        boton40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.tableroListener(R.id.bt4);
            }
        });
        //añade los iv a la coleccion
        ivCombinacion1 = fragmentVista.findViewById(R.id.ivcombinacion1);
        ivCombinacion2 = fragmentVista.findViewById(R.id.ivcombinacion2);
        ivCombinacion3 = fragmentVista.findViewById(R.id.ivcombinacion3);
        ivCombinacion4 = fragmentVista.findViewById(R.id.ivcombinacion4);
        coleccionCombinacion.add(ivCombinacion1);
        coleccionCombinacion.add(ivCombinacion2);
        coleccionCombinacion.add(ivCombinacion3);
        coleccionCombinacion.add(ivCombinacion4);
    //creacion combinacion ganadora
        int contadorCombinacionGanadora=0;
        while(combinacionGanadora.size()<4){
            int numero=0;
            boolean numeroSinRepetir=false;
            do{
                numero = (int) (Math.random()*6);
                    if(!lnumerosCombinacion.contains(numero)){
                        numeroSinRepetir=true;
                        lnumerosCombinacion.add(numero);
                    }
            }while(!numeroSinRepetir);
            ImageView fichaCambiar = coleccionCombinacion.get(contadorCombinacionGanadora);
            switch (numero) {
                case 0:
                    combinacionGanadora.add(R.id.ficha1);
                    contadorCombinacionGanadora++;
                    break;
                case 1:
                    combinacionGanadora.add(R.id.ficha2);
                    contadorCombinacionGanadora++;
                    break;
                case 2:
                    combinacionGanadora.add(R.id.ficha3);
                    contadorCombinacionGanadora++;
                    break;
                case 3:
                    combinacionGanadora.add(R.id.ficha4);
                    contadorCombinacionGanadora++;
                    break;
                case 4:
                    combinacionGanadora.add(R.id.ficha5);
                    contadorCombinacionGanadora++;
                    break;
                case 5:
                    combinacionGanadora.add(R.id.ficha6);
                    contadorCombinacionGanadora++;
                    break;
            }

            //System.out.println(combinacionGanadora.toString());
        }

        return fragmentVista;
    }
public void desactivarFila(int numFila){
    Integer filaDesactivar = lFilas.get(numFila);
        View filaDesactiva = fragmentVista.findViewById(filaDesactivar);
            Button boton1 = filaDesactiva.findViewById(R.id.bt1);
            Button boton2 = filaDesactiva.findViewById(R.id.bt2);
            Button boton3 = filaDesactiva.findViewById(R.id.bt3);
            Button boton4 = filaDesactiva.findViewById(R.id.bt4);
            boton1.setEnabled(false);
            boton2.setEnabled(false);
            boton3.setEnabled(false);
            boton4.setEnabled(false);
}

public void activarFila(int numFila){
        if(numFila<10) {
            Integer filaDesactivar = lFilas.get(numFila);
            View filaDesactiva = fragmentVista.findViewById(filaDesactivar);
            Button boton1 = filaDesactiva.findViewById(R.id.bt1);
            Button boton2 = filaDesactiva.findViewById(R.id.bt2);
            Button boton3 = filaDesactiva.findViewById(R.id.bt3);
            Button boton4 = filaDesactiva.findViewById(R.id.bt4);
            boton1.setEnabled(true);
            boton2.setEnabled(true);
            boton3.setEnabled(true);
            boton4.setEnabled(true);
        }
}
public void mostrarCombinacion(){
        int contadorCombinacionGanadora=0;
    for (int ficha:combinacionGanadora) {
        ImageView fichaCambiar = coleccionCombinacion.get(contadorCombinacionGanadora);
        switch (ficha) {
            case R.id.ficha1:
                fichaCambiar.setImageDrawable(getResources().getDrawable(R.drawable.ficha_verde));
                contadorCombinacionGanadora++;
                break;
            case R.id.ficha2:
                fichaCambiar.setImageDrawable(getResources().getDrawable(R.drawable.ficha_amarillo));
                contadorCombinacionGanadora++;
                break;
            case R.id.ficha3:
                fichaCambiar.setImageDrawable(getResources().getDrawable(R.drawable.ficha_azul));
                contadorCombinacionGanadora++;
                break;
            case R.id.ficha4:
                fichaCambiar.setImageDrawable(getResources().getDrawable(R.drawable.ficha_rojo));
                contadorCombinacionGanadora++;
                break;
            case R.id.ficha5:
                fichaCambiar.setImageDrawable(getResources().getDrawable(R.drawable.ficha_rosa));
                contadorCombinacionGanadora++;
                break;
            case R.id.ficha6:
                fichaCambiar.setImageDrawable(getResources().getDrawable(R.drawable.ficha_naranja));
                contadorCombinacionGanadora++;
                break;
        }
    }

}
    /**
     * Comprueba si acierta o falla y lo muestra
     */
    public void comprobarAciertoFallo(List<Integer> fichasJugadas, int fila){
        int contadorCompruebaAciertos=0;
        int colorPosicion=0, color=0, fallo=0, contadorJugada=0;
        ImageView circulito;
        View filaUsada = fragmentVista.findViewById(lFilas.get(fila));
        for (Integer ficha:fichasJugadas) {
            if(combinacionGanadora.get(contadorCompruebaAciertos).intValue()==ficha){
                colorPosicion++;
            } else if(combinacionGanadora.contains(ficha)){
                color++;
            }else{
                fallo++;
            }
            contadorCompruebaAciertos++;
        }
        for (int i = 0; i < colorPosicion ; i++) {
            circulito = filaUsada.findViewById(lCirculitos.get(contadorJugada));
            circulito.setImageResource(R.drawable.ficha_negropressed);
            contadorJugada++;
        }
        for (int i = 0; i < color ; i++) {
            circulito = filaUsada.findViewById(lCirculitos.get(contadorJugada));
            circulito.setImageResource(R.drawable.ficha_blanca);
            contadorJugada++;
        }
        for (int i = 0; i < fallo ; i++) {
            circulito = filaUsada.findViewById(lCirculitos.get(contadorJugada));
            circulito.setImageResource(R.drawable.ficha_negra);
            contadorJugada++;
        }
}
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int idBoton) {
        if (mListener != null) {
            mListener.tableroListener(idBoton);
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
        void tableroListener(int idBoton);
    }

    public boolean comprobarFinJuego(int contadorIntentos, boolean resultado, int fichaUsada){

        if(resultado || contadorIntentos==10){
            Toast.makeText(getActivity(), "El juego ha terminado", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return false;
        }
    }

    public void gestionBotonFila(int idBoton, int contadorIntentos, List<Integer> fichasEscogidas, int fichaUsada, int colorUsado){
        Button botonUsado;
        Integer filaCogida = lFilas.get(contadorIntentos);
        View filaCogidaView = fragmentVista.findViewById(filaCogida);
        switch (idBoton) {
            case R.id.bt1:
                fichasEscogidas.remove(0);
                fichasEscogidas.add(0, fichaUsada);
                botonUsado = filaCogidaView.findViewById(idBoton);
                botonUsado.setBackgroundResource(colorUsado);
                botonUsado.setEnabled(false);
                break;
            case R.id.bt2:
                fichasEscogidas.remove(1);
                fichasEscogidas.add(1, fichaUsada);
                botonUsado = filaCogidaView.findViewById(idBoton);
                botonUsado.setBackgroundResource(colorUsado);
                botonUsado.setEnabled(false);
                break;
            case R.id.bt3:
                fichasEscogidas.remove(2);
                fichasEscogidas.add(2, fichaUsada);
                botonUsado = filaCogidaView.findViewById(idBoton);
                botonUsado.setBackgroundResource(colorUsado);
                botonUsado.setEnabled(false);
                break;
            case R.id.bt4:
                fichasEscogidas.remove(3);
                fichasEscogidas.add(3, fichaUsada);
                botonUsado = filaCogidaView.findViewById(idBoton);
                botonUsado.setBackgroundResource(colorUsado);
                botonUsado.setEnabled(false);
                break;
        }
    }


}
