package com.example.streaming;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import java.util.Map;
import io.flutter.plugin.platform.PlatformView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.app.AlertDialog;

class NativeView implements PlatformView {
   LinearLayout linear;

    NativeView(Context context, int id, Map<String, Object> creationParams) {
       linear = new LinearLayout(context);

       Button bt1 = new Button(context);
       bt1.setText("Hola,bebe");
    
       bt1.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View view) 
            { 
              /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Morfeo dice:");
        builder.setMessage("¿Qué píldora quieres Neo?\n\nLa azul te hará olvidar lo ocurrido y volverás a Matrix, mientras que la roja te llevará al mundo real.");        // add the buttons
        builder.setPositiveButton("La Roja", null);
        builder.setNeutralButton("Recordar más tarde", null);
        builder.setNegativeButton("La azul", null);        
        AlertDialog dialog = builder.create();
        dialog.show();*/
                Intent intent = new Intent(context.getApplicationContext(),BarActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            } 
        }); 
           linear.addView(bt1);

    }

    @Override
    public View getView() {
        return linear;
    }

    @Override
    public void dispose() {}
}
