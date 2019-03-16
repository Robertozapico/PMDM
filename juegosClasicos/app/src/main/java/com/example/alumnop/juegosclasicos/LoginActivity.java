package com.example.alumnop.juegosclasicos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginListener, RegistroFragment.RegistroListener {
    private LoginFragment fragmentLogin;
    private RegistroFragment fragmentRegistro;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragmentLogin = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_login);
        fragmentRegistro = (RegistroFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_registro);
        fragmentRegistro.setMiEscuchadorClick(new RegistroFragment.RegistroListener() {
            @Override
            public void botonesRegistro(int boton) {
                if (fragmentRegistro.getEtEmail().getText().toString().isEmpty() || fragmentRegistro.getEtPassword().getText().toString().isEmpty() || fragmentRegistro.getEtUsuario().getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor editorPreferencias = preferencias.edit();
                    editorPreferencias.putString("username", fragmentRegistro.getEtUsuario().getText().toString());
                    editorPreferencias.putString("userpasswd", fragmentRegistro.getEtPassword().getText().toString());
                    editorPreferencias.putString("usermail", fragmentRegistro.getEtEmail().getText().toString());
                    editorPreferencias.commit();
                    Toast.makeText(LoginActivity.this, "Se ha creado el usuario", Toast.LENGTH_SHORT).show();

                }
            }
        });
        if (nuevoUsuario()) {
            Intent i = new Intent(LoginActivity.this, Registro.class);
            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
            startActivity(i);
        }

        fragmentLogin.setMiEscuchadorClick(new LoginFragment.LoginListener() {
            @Override
            public void botonesLogin(int boton) {
                boolean usuarioRegistrado;
                if (boton == R.id.btLoginLogin) {
                    if (fragmentLogin.getEtEmail().getText().toString().isEmpty() || fragmentLogin.getEtPassword().getText().toString().isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        email = fragmentLogin.getEtEmail().getText().toString();
                        password = fragmentLogin.getEtPassword().getText().toString();
                        usuarioRegistrado = comprobarExistenciaUsuario(email, password);
                        if (usuarioRegistrado) {
                            Intent i = new Intent(LoginActivity.this, PantallaPrincipal.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "El usuario no existe.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (boton == R.id.btRegistroLogin) {
                    Intent i = new Intent(LoginActivity.this, Registro.class);
                    PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    startActivity(i);
                }
            }
        });

    }




    public boolean nuevoUsuario() {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        if (preferencias.getString("usermail", "").isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarExistenciaUsuario(String email, String password) {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        String correo = preferencias.getString("usermail", "");
        String passwd = preferencias.getString("userpasswd", "");
        if (correo.equals("") || passwd.equals("")) {
            return false;
        } else if (correo.equals(email) && passwd.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void botonesLogin(int boton) {

    }
    @Override
    public void botonesRegistro(int boton) {

    }

}