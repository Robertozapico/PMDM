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
    private ImageView ivFoto;
    private SharedPreferences preferencias;
    private Button btSave, btCamera;
    private Uri rutaUri = null;
    private boolean tomadoFoto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        etNombre = findViewById(R.id.etUserPerfil);
        etPass = findViewById(R.id.etPassPerfil);
        etEmail = findViewById(R.id.etEmailPerfil);
        ivFoto = findViewById(R.id.ivUserPerfil);
        btSave = findViewById(R.id.btGuardarPerfil);
        btCamera = findViewById(R.id.btCameraPerfil);


        preferencias = PreferenceManager.getDefaultSharedPreferences(PerfilActivity.this);
        etNombre.setText(preferencias.getString("username", "*"));
        etPass.setText(preferencias.getString("userpasswd", "*"));
        etEmail.setText(preferencias.getString("usermail", "*"));
        if (preferencias.getString("userfoto", "") == null || preferencias.getString("userfoto", "").equals("")) {
            ivFoto.setImageResource(R.drawable.avatar);
        } else {
            rutaUri = Uri.fromFile(new File(preferencias.getString("userfoto", "")));
            ivFoto.setImageURI(rutaUri);
        }

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().isEmpty() || etNombre.getText().toString().isEmpty() || etPass.getText().toString().isEmpty()) {
                    Toast.makeText(PerfilActivity.this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editorPreferencias = preferencias.edit();
                    editorPreferencias.putString("username", etNombre.getText().toString());
                    editorPreferencias.putString("userpasswd", etPass.getText().toString());
                    editorPreferencias.putString("usermail", etEmail.getText().toString());
                    //
                    if (tomadoFoto) {
                        editorPreferencias.putString("userfoto", rutaUri.toString());
                    }

                    editorPreferencias.commit();
                    Toast.makeText(PerfilActivity.this, "Se han salvado los cambios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
                Toast.makeText(PerfilActivity.this, "Se hatomado la foto", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_perfil, menu);
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


    private void takePicture() {

        //creo intent que lanza la cámara del dispositivo
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //compruebo que hay app de cámara en el dispositivo
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //Obtengo la uri del fichero donde grabaré la foto
            rutaUri = Uri.fromFile(createOutputPictureFile());
            //añado al intent esa uri del fichero
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, rutaUri);
            //lanzo el intent para que me abra la cámara
            startActivityForResult(takePictureIntent, -1);
            tomadoFoto = true;
        }
    }

    /**
     * Método que crea el descriptor del fichero donde se guardará la foto
     *
     * @return el descriptor del fichero File creado
     */
    private File createOutputPictureFile() {
        //obtengo el directorio donde guardaré la foto:
// directorioFotosDelDispositivo/FotosAppExample
        File picturesDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "FotosJuegosClasicos");
        if (!picturesDirectory.exists()) {
            //lo creo
            if (!picturesDirectory.mkdirs())
                return null;
        }
//uso una marca de tiempo para el nombre del fichero
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(picturesDirectory.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        return file;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == -1 && resultCode == RESULT_OK) {
            ivFoto.setImageURI(rutaUri);
        }
    }
}



