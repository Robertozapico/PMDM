package com.example.alumnop.juegosclasicos;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerfilActivity extends AppCompatActivity {
    private EditText etNombre, etPass, etEmail;
    private ImageView ivFoto, ivCamera;
    private SharedPreferences preferencias;
    private Button btSave;
    private Uri rutaUri=null;
    private boolean tomadoFoto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        etNombre = findViewById(R.id.etUserPerfil);
        etPass = findViewById(R.id.etPassPerfil);
        etEmail = findViewById(R.id.etEmailPerfil);
        ivFoto = findViewById(R.id.ivUserPerfil);
        btSave = findViewById(R.id.btGuardarPerfil);
        ivCamera = findViewById(R.id.ivCameraPerfil);
        ivCamera.setClickable(true);

        preferencias = PreferenceManager.getDefaultSharedPreferences(PerfilActivity.this);
        etNombre.setText(preferencias.getString("username", "*"));
        etPass.setText(preferencias.getString("userpasswd", "*"));
        etEmail.setText(preferencias.getString("usermail", "*"));
        if(preferencias.getString("userfoto","")==null || preferencias.getString("userfoto","").equals("")){
            ivFoto.setImageResource(R.drawable.avatar);
        }else {
            rutaUri = Uri.fromFile(new File(preferencias.getString("userfoto","")));
            ivFoto.setImageURI(rutaUri);
        }

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(etEmail.getText().toString().isEmpty()||etNombre.getText().toString().isEmpty()||etPass.getText().toString().isEmpty()){
                Toast.makeText(PerfilActivity.this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
            }else{
                SharedPreferences.Editor editorPreferencias = preferencias.edit();
                editorPreferencias.putString("username", etNombre.getText().toString());
                editorPreferencias.putString("userpasswd", etPass.getText().toString());
                editorPreferencias.putString("usermail", etEmail.getText().toString());
                //
                if(tomadoFoto){
                editorPreferencias.putString("userfoto", rutaUri.toString());
                }

                editorPreferencias.commit();
                Toast.makeText(PerfilActivity.this, "Se han salvado los cambios", Toast.LENGTH_SHORT).show();
            }
            }
        });

        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File foto = gestionarFoto();
                if (camara.resolveActivity(getPackageManager()) != null) {
                    if (foto != null) {
                        rutaUri = Uri.fromFile(foto);
                        camara.putExtra(MediaStore.EXTRA_OUTPUT, rutaUri);
                        startActivityForResult(camara, -1);
                    }
                }
                tomadoFoto=true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_perfil,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_logout:
                i = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private File gestionarFoto() {
        File directorio = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "FotosJuegosClasicos");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                return null;
            }
        }
        String fechaFoto = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File fileFoto = new File(directorio.getPath() + File.separator + "Imagen_" + fechaFoto + ".jpg");
        rutaUri = Uri.fromFile(fileFoto);
        return fileFoto;
    }

}
