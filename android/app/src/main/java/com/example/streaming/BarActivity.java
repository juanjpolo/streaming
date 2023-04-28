package com.example.streaming;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;

public class BarActivity extends AppCompatActivity {
    VideoView videoView;

    private void test_2(){
        String httpLiveUrl = "http://localhost:5080/live/stream.m3u8";
        videoView = (VideoView) findViewById(R.id.VideoView);
        videoView.setVideoURI(Uri.parse(httpLiveUrl));
        MediaController mediaController = new MediaController(this);

        videoView.requestFocus();
        videoView.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_bar);
        Vitamio.isInitialized(getApplicationContext());
        test_2();
    }

}