package com.example.alumnop.juegosclasicos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements RegistroFragment.RegistroListener, LoginFragment.LoginListener {
    private RegistroFragment fragmentRegistro;
    private LoginFragment fragmentLogin;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fragmentRegistro = (RegistroFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_registro);
        fragmentRegistro.setMiEscuchadorClick(new RegistroFragment.RegistroListener() {
            @Override
            public void botonesRegistro(int boton) {
                if (fragmentRegistro.getEtEmail().getText().toString().isEmpty() || fragmentRegistro.getEtPassword().getText().toString().isEmpty() || fragmentRegistro.getEtUsuario().getText().toString().isEmpty()) {
                    Toast.makeText(Registro.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(Registro.this);
                    SharedPreferences.Editor editorPreferencias = preferencias.edit();
                    editorPreferencias.putString("username", fragmentRegistro.getEtUsuario().getText().toString());
                    editorPreferencias.putString("userpasswd", fragmentRegistro.getEtPassword().getText().toString());
                    editorPreferencias.putString("usermail", fragmentRegistro.getEtEmail().getText().toString());
                    editorPreferencias.commit();
                    Toast.makeText(Registro.this, "Se ha creado el usuario", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        fragmentLogin = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_login);
        //da error al aplicar este escuchador
        fragmentLogin.setMiEscuchadorClick(new LoginFragment.LoginListener() {
            @Override
            public void botonesLogin(int boton) {
                boolean usuarioRegistrado;
                if (boton == R.id.btLoginLogin) {
                    if (fragmentLogin.getEtEmail().getText().toString().isEmpty() || fragmentLogin.getEtPassword().getText().toString().isEmpty()) {
                        Toast.makeText(Registro.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        email = fragmentLogin.getEtEmail().getText().toString();
                        password = fragmentLogin.getEtPassword().getText().toString();
                        usuarioRegistrado = comprobarExistenciaUsuario(email, password);
                        if (usuarioRegistrado) {
                            Intent i = new Intent(Registro.this, PantallaPrincipal.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Registro.this, "El usuario no existe.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void botonesRegistro(int boton) {

    }
    @Override
    public void botonesLogin(int boton) {

    }

    public boolean comprobarExistenciaUsuario(String email, String password) {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(Registro.this);
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
}
