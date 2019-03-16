package com.example.alumnop.juegosclasicos;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.alumnop.juegosclasicos.DerechaFragment.cartasFragment;
import static com.example.alumnop.juegosclasicos.PantallaJuego.cartas;
import static com.example.alumnop.juegosclasicos.PantallaJuego.imagenesCartas;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IzquierdaFragment.IzquierdaListener} interface
 * to handle interaction events.
 * Use the {@link IzquierdaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IzquierdaFragment extends Fragment {
    private TextView tvHighScore, tvScore;
    private ImageView imgCarta1, imgCarta2, imgCarta3, imgCarta4, imgCarta5;
    private int carta1, carta2, carta3, carta4, carta5, intCartaEscogida, cantidadReyes, cantidadCartasPuestas;
    private IzquierdaListener mListener;
    private boolean cartaEscogida, cartaRey;
    public static List<Integer> numerosCogidos = new ArrayList<>();
    private boolean activado1,activado2,activado3,activado4;

    SoundPool efectosSonido = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
    int efectoCartas;
    private SharedPreferences preferencias;
    // Required empty public constructor
    // Inflate the layout for this fragment


    public IzquierdaFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IzquierdaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IzquierdaFragment newInstance(String param1, String param2) {
        IzquierdaFragment fragment = new IzquierdaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    public TextView getTvHighScore() {
        return tvHighScore;
    }

    public TextView getTvScore() {
        return tvScore;
    }

    public int getCarta1() {
        return carta1;
    }

    public void setCarta1(int carta1) {
        this.carta1 = carta1;
    }

    public int getCarta2() {
        return carta2;
    }

    public void setCarta2(int carta2) {
        this.carta2 = carta2;
    }

    public int getCarta3() {
        return carta3;
    }

    public void setCarta3(int carta3) {
        this.carta3 = carta3;
    }

    public int getCarta4() {
        return carta4;
    }

    public int getIntCartaEscogida() {
        return intCartaEscogida;
    }

    public void setCarta4(int carta4) {
        this.carta4 = carta4;
    }

    public int getCarta5() {
        return carta5;
    }

    public void setCarta5(int carta5) {
        this.carta5 = carta5;
    }

    public void setCartaEscogida(boolean cartaEscogida) {
        this.cartaEscogida = cartaEscogida;
    }

    public boolean isCartaRey() {
        return cartaRey;
    }

    public void setCartaRey(boolean cartaRey) {
        this.cartaRey = cartaRey;
    }

    public int getCantidadCartasPuestas() {
        return cantidadCartasPuestas;
    }

    public void asignarCartasFragmentIzq(int carta1, int cart2, int carta3, int carta4) {
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = carta3;
        this.carta4 = carta4;
        numerosCogidos.add(carta1);
        numerosCogidos.add(carta2);
        numerosCogidos.add(carta3);
        numerosCogidos.add(carta4);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaFragmentIzquierda = inflater.inflate(R.layout.fragment_izquierda, container, false);
        tvHighScore = vistaFragmentIzquierda.findViewById(R.id.tvHighScore);
        tvScore = vistaFragmentIzquierda.findViewById(R.id.tvScore);
        imgCarta1 = vistaFragmentIzquierda.findViewById(R.id.imgIzqCarta1);
        imgCarta2 = vistaFragmentIzquierda.findViewById(R.id.imgIzqCarta2);
        imgCarta3 = vistaFragmentIzquierda.findViewById(R.id.imgIzqCarta3);
        imgCarta4 = vistaFragmentIzquierda.findViewById(R.id.imgIzqCarta4);
        imgCarta5 = vistaFragmentIzquierda.findViewById(R.id.imgIzqCarta5);
        if(cantidadReyes<4 || cantidadCartasPuestas<cartas.size()) {
            if (activado1 == false) {
                imgCarta1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        efectoSonido();
                        if (cartaEscogida == false || !cartasFragment.contains(carta1)) {
                            Drawable paloCarta = cartas.get(carta1).getImagenCarta();
                            imgCarta1.setImageDrawable(paloCarta);
                            Toast.makeText(getActivity(), cartas.get(carta1).getNombreCarta(), Toast.LENGTH_SHORT).show();
                            cartaEscogida = true;
                            intCartaEscogida = cartas.get(carta1).getNumeroCarta();
                            cartaRey = false;
                            activado1 = true;
                            cantidadCartasPuestas++;

                        } else if (cartaEscogida == true) {
                            Toast.makeText(getActivity(), "Usa la carta ya escogida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            if (activado2 == false) {
                imgCarta2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        efectoSonido();
                        if (cartaEscogida == false || !cartasFragment.contains(carta2)) {
                            Drawable paloCarta = cartas.get(carta2).getImagenCarta();
                            imgCarta2.setImageDrawable(paloCarta);
                            Toast.makeText(getActivity(), cartas.get(carta2).getNombreCarta(), Toast.LENGTH_SHORT).show();
                            cartaEscogida = true;
                            cartaRey = false;
                            activado2 = true;
                            intCartaEscogida = cartas.get(carta2).getNumeroCarta();
                            cantidadCartasPuestas++;
                        } else if (cartaEscogida == true) {
                            Toast.makeText(getActivity(), "Usa la carta ya escogida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            if (activado3 == false) {
                imgCarta3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        efectoSonido();
                        if (cartaEscogida == false || !cartasFragment.contains(carta3)) {
                            Drawable paloCarta = cartas.get(carta3).getImagenCarta();
                            imgCarta3.setImageDrawable(paloCarta);
                            Toast.makeText(getActivity(), cartas.get(carta3).getNombreCarta(), Toast.LENGTH_SHORT).show();
                            cartaEscogida = true;
                            cartaRey = false;
                            activado3 = true;
                            intCartaEscogida = cartas.get(carta3).getNumeroCarta();
                            cantidadCartasPuestas++;
                        } else if (cartaEscogida == true) {
                            Toast.makeText(getActivity(), "Usa la carta ya escogida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            if (activado4 == false) {
                efectoSonido();
                imgCarta4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cartaEscogida == false || !cartasFragment.contains(carta1)) {
                            Drawable paloCarta = cartas.get(carta4).getImagenCarta();
                            imgCarta4.setImageDrawable(paloCarta);
                            Toast.makeText(getActivity(), cartas.get(carta4).getNombreCarta(), Toast.LENGTH_SHORT).show();
                            cartaEscogida = true;
                            cartaRey = false;
                            activado4 = true;
                            intCartaEscogida = cartas.get(carta4).getNumeroCarta();
                            cantidadCartasPuestas++;
                        } else if (cartaEscogida == true) {
                            Toast.makeText(getActivity(), "Usa la carta ya escogida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            imgCarta5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    efectoSonido();
                    if (cartaEscogida == false) {
                        while (numerosCogidos.contains(carta5)) {
                            carta5 = (int) (Math.random() * imagenesCartas.size());
                        }
                        numerosCogidos.add(carta5);

                        Drawable paloCarta = PantallaJuego.imagenesCartas.get(carta5).getImagenCarta();
                        imgCarta5.setImageDrawable(paloCarta);
                        Toast.makeText(getActivity(), imagenesCartas.get(carta5).getNombreCarta(), Toast.LENGTH_SHORT).show();
                        cartaEscogida = true;
                        cartaRey = false;
                        intCartaEscogida = imagenesCartas.get(carta5).getNumeroCarta();
                        if (imagenesCartas.get(carta5).getNumeroCarta() == -1) {
                            cartaEscogida = true;
                            cartaRey = true;
                            Toast.makeText(getActivity(), "No puede usar el rey", Toast.LENGTH_SHORT).show();
                            cantidadReyes++;
                        }else{
                            cantidadCartasPuestas++;
                        }
                    } else {
                        Toast.makeText(getActivity(), "Usa la carta ya escogida", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(cantidadReyes==4){
            Toast.makeText(getActivity(), "Has perdido", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Has ganado", Toast.LENGTH_SHORT).show();
        }

        return vistaFragmentIzquierda;
    }

    public void actualizarCarta5() {
        cartaRey = false;
        while (numerosCogidos.contains(carta5)) {
            carta5 = (int) (Math.random() * imagenesCartas.size());
        }
        numerosCogidos.add(carta5);

        Drawable paloCarta = PantallaJuego.imagenesCartas.get(carta5).getImagenCarta();
        imgCarta5.setImageDrawable(paloCarta);
        Toast.makeText(getActivity(), imagenesCartas.get(carta5).getNombreCarta(), Toast.LENGTH_SHORT).show();
        intCartaEscogida = imagenesCartas.get(carta5).getNumeroCarta();
        if (imagenesCartas.get(carta5).getNumeroCarta() == -1) {
            cartaRey = true;
            cantidadReyes++;
            Toast.makeText(getActivity(), "No puede usar el rey", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setMiEscuchadorClick(IzquierdaListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        preferencias = PreferenceManager.getDefaultSharedPreferences(context);
        efectoCartas = efectosSonido.load(context, R.raw.pick_sound, 1);
        if (context instanceof IzquierdaListener) {
            mListener = (IzquierdaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IzquierdaListener");
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
    public interface IzquierdaListener {
        // TODO: Update argument type and name
        void onClickCartaIzq(int idCarta);
    }

    private void efectoSonido() {
        boolean sonidoActivado= preferencias.getBoolean("prefk_sonidos", true);
        if (sonidoActivado) {
            efectosSonido.play(efectoCartas, 1, 1, 0, 0, 1);
        }
    }
}
