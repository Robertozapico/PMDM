package com.example.alumnop.juegosclasicos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class RegistroFragment extends Fragment implements View.OnClickListener {

    private EditText etNombre, etPassword, etEmail;
    private Button btRegistrarse, btLogin;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etNombre = getView().findViewById(R.id.etUsuarioRegistro);
        etPassword = getView().findViewById(R.id.etPasswordRegistro);
        etEmail = getView().findViewById(R.id.etEmailRegistro);
        btRegistrarse = getView().findViewById(R.id.btRegistroRegistro);
        btLogin = getView().findViewById(R.id.btLoginLogin);
        btRegistrarse.setOnClickListener(this);
        btLogin.setOnClickListener(this);
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
