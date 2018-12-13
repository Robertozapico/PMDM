package com.example.alumnop.juegosclasicos;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

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
}
