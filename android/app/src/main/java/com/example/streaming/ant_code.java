package com.example.streaming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.flutter.embedding.android.FlutterActivity;
import com.red5pro.streaming.R5Connection;
import com.red5pro.streaming.R5Stream;
import com.red5pro.streaming.R5StreamProtocol;
import com.red5pro.streaming.config.R5Configuration;
import com.red5pro.streaming.event.R5ConnectionEvent;
import com.red5pro.streaming.event.R5ConnectionListener;
import com.red5pro.streaming.media.R5AudioController;
import com.red5pro.streaming.view.R5VideoView;


public class ant_code extends AppCompatActivity implements R5ConnectionListener {
    protected R5VideoView display;
    protected R5Stream subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

setContentView(R.layout.activity_ant_code);
        final Button button = findViewById(R.id.button);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Subasta");
        //find the view and attach the stream
        button.setText("Regresar a flutter");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        FlutterActivity
                                .withNewEngine()
                                .initialRoute("/second")
                                .build(getApplicationContext())
                );
            }
        });

        Subscribe();
    }

    @Override
    public void onConnectionEvent(R5ConnectionEvent event) {
        Log.d("Subscriber", ":onConnectionEvent " + event.name());
        if (event.name() == R5ConnectionEvent.LICENSE_ERROR.name()) {
            Handler h = new Handler(Looper.getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
        else if (event.name() == R5ConnectionEvent.START_STREAMING.name()){

//            subscribe.setFrameListener(new R5FrameListener() {
//                @Override
//                public void onFrameReceived(Object o, R5StreamFormat r5StreamFormat, int w, int h) {
//                    int format = r5StreamFormat.value(); // 2 - YUV_PLANAR
//                    if (r5StreamFormat.equals(R5StreamFormat.YUV_PLANAR)) {
//                        byte[][] yuv_frames = (byte[][]) o; // Cast and access data in 3 planes as byte array. (byte[3][])
//                    }
//                }
//            });
        }
    }

    public void Subscribe(){

        //Create the configuration from the tests.xml
        R5Configuration config = new R5Configuration(R5StreamProtocol.RTSP,
                "192.168.1.9",
               8554,
              "live",
                0.5f);
        config.setLicenseKey("AFW0-B02S-E3Y4-XQMC");
        config.setBundleID(this.getPackageName());

        R5Connection connection = new R5Connection(config);

        //setup a new stream using the connection
        subscribe = new R5Stream(connection);

        //Some devices can't handle rapid reuse of the audio controller, and will crash
        //Recreation of the controller assures that the example will always be stable
        subscribe.audioController = new R5AudioController();
        subscribe.audioController.sampleRate = 44100;


        subscribe.setListener(this);

        //show all logging
        subscribe.setLogLevel(R5Stream.LOG_LEVEL_DEBUG);

        //display.setZOrderOnTop(true);


        subscribe.play("stream", true);

    }
    @Override
    public void onStop() {
        if(subscribe != null) {
            subscribe.stop();
        }

        super.onStop();
    }
}