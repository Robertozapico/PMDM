package com.example.alumnop.juegosclasicos;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
/*
public class Musica extends Service {
    MediaPlayer reproductor;

    public Musica() {
    }

    @Override
    public void onCreate() {
        reproductor = MediaPlayer.create(this, R.raw.musica);
    }

    @Override
    public void onStart(Intent intencion,
                        int idArranque) {
        reproductor.start();
    }

    @Override
    public void onDestroy() {
        reproductor.stop();
        reproductor.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}*/

public class Musica extends Service {
    public static final String PAUSA_MUSICA = "com.example.alumnop.juegosclasicos.PAUSA_MUSICA";
    private MediaPlayer reproductor;
    private int posMusica;


    public Musica() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        reproductor = MediaPlayer.create(this, R.raw.musica);
    }

    @Override
    public int onStartCommand(Intent intencion, int flags, int idArranque) {
        if (intencion.hasExtra("POS")) {
            posMusica = intencion.getIntExtra("POS",0);
        }
        reproductor.seekTo(posMusica);
        reproductor.setLooping(true);
        reproductor.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (reproductor != null && reproductor.isPlaying()) {
            reproductor.pause();
        }
        posMusica = reproductor.getCurrentPosition();
        //para guardar la posición le facilita al intent dos argumentos:
        Intent intencion = new Intent();
        //la acción concreta, gracias a la cual se realiza un filtrado de entre otras acciones en PantallaPrincipal
        intencion.setAction(PAUSA_MUSICA);
        //y el dato
        intencion.putExtra("POS",posMusica);
        //Ahora enviamos ese paquete a todos los receptores; la tomará el receptor de música de PantallaPrincipal, comproborá la acción y luego la key
        sendBroadcast(intencion);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
