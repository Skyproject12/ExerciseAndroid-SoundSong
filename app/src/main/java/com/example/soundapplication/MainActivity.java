package com.example.soundapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonSound;
    // inport from soundmedia
    SoundPool sp;
    int soundId;
    boolean spLoaded= false;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSound= findViewById(R.id.button_soundpool);
        // build sound
        sp= new SoundPool.Builder()
                .setMaxStreams(10)
                .build();

        // mengecek apakah success load sound
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if(status==0){
                    spLoaded= true;
                }
                else {
                    // jika sound gagal popup gagal load
                    Toast.makeText(MainActivity.this, "Gagal load", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // set soundid load music
        soundId= sp.load(this, R.raw.edsheerad, 1);
        buttonSound.setOnClickListener(myListener);
    }

    // set sound
    View.OnClickListener myListener= new View.OnClickListener() {
        @Override
        // make onclick button
        public void onClick(View v) {
            if(spLoaded){
                // play sound id, volume, loop
                sp.play(soundId,1,1,0,0,1);
            }
        }
    };
}
