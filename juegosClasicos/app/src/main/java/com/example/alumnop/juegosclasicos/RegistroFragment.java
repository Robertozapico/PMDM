package com.example.alumnop.juegosclasicos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class RegistroFragment extends Fragment implements View.OnClickListener {

    private EditText etNombre, etPassword, etEmail;
    private Button btRegistrarse;
    private RegistroListener mListener;

    public RegistroFragment() {
        // Required empty public constructor
    }

    public static RegistroFragment newInstance() {
        RegistroFragment fragment = new RegistroFragment();
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
        View vistaFragment = inflater.inflate(R.layout.fragment_registro, container, false);
        etNombre = vistaFragment.findViewById(R.id.etUsuarioRegistro);
        etPassword = vistaFragment.findViewById(R.id.etPasswordRegistro);
        etEmail = vistaFragment.findViewById(R.id.etEmailRegistro);
        btRegistrarse = vistaFragment.findViewById(R.id.btRegistroRegistro);
        btRegistrarse.setOnClickListener(this);
        return vistaFragment;

    }

    public EditText getEtPassword() {
        return etPassword;
    }

    public EditText getEtEmail() {
        return etEmail;
    }

    public EditText getEtUsuario() {
        return etNombre;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.botonesRegistro(v.getId());
        }
    }

    public void setMiEscuchadorClick(RegistroListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegistroListener) {
            mListener = (RegistroListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement registroListener");
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
    public interface RegistroListener {
        public void botonesRegistro(int boton);
    }
}
