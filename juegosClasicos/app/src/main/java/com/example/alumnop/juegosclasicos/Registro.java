package com.example.alumnop.juegosclasicos;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements RegistroFragment.RegistroListener {
    private RegistroFragment fragmentRegistro;

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
    }

    @Override
    public void botonesRegistro(int boton) {

    }
}
