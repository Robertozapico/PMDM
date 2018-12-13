package com.example.alumnop.juegosclasicos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.LoginListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText etPassword, etEmail;
    private Button btRegistrarse, btLogin;
    private LoginListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaFragmentLogin = inflater.inflate(R.layout.fragment_login, container, false);
        etPassword = vistaFragmentLogin.findViewById(R.id.etPasswordLogin);
        etEmail = vistaFragmentLogin.findViewById(R.id.etEmailLogin);
        btRegistrarse = vistaFragmentLogin.findViewById(R.id.btRegistroLogin);
        btLogin = vistaFragmentLogin.findViewById(R.id.btLoginLogin);
        btRegistrarse.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        return vistaFragmentLogin;
    }


    public EditText getEtPassword() {
        return etPassword;
    }

    public EditText getEtEmail() {
        return etEmail;
    }

    public void setMiEscuchadorClick(LoginListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginListener) {
            mListener = (LoginListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LoginListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.botonesLogin(v.getId());
        }
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

    public interface LoginListener {
        public void botonesLogin(int boton);
    }
}
