package com.hu.testmusic;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by chunchun.hu on 2017/12/29.
 */

public class MusicActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    MediaPlayer m1 = null;
    MediaPlayer m2 = null;
    boolean m1Click = true;
    boolean m2Click = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        m1 = MediaPlayer.create(this, R.raw.guofucheng);

        final Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m1Click){
                    start.setText("暂停");
                    m1.start();
                }else{
                    start.setText("开始");
                    m1.pause();
                }
                m1Click = !m1Click;
            }
        });

        AssetManager am = getAssets();//获得该应用的AssetManager
        try{
            AssetFileDescriptor afd = am.openFd("guofucheng.mp3");
            m2 = new MediaPlayer();
            m2.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            m2.prepare(); //准备

        } catch(IOException e){
            e.printStackTrace();
        }

        final Button stop = findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (m2Click && m2 != null){
                   m2.start();
                   stop.setText("暂停");
               }else {
                   m2.pause();
                   stop.setText("开始");
               }
                m2Click = !m2Click;
            }
        });
    }
}
