package com.example.dam2a21.examen;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button toastbutton;
    Button countbutton;
    Button zerobutton;
    TextView counttextview;
    public int count;
    public static final String EXAM_PREFERENCES = "ExamPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counttextview = (TextView)findViewById(R.id.count);

        SharedPreferences settings =
                getSharedPreferences(EXAM_PREFERENCES, MODE_PRIVATE);
        if (settings.contains("LastContador") == true) {

            int contador = settings.getInt("Contador", 0);
            Log.i("time", "Last time " + String.valueOf(contador));
            counttextview.setText(String.valueOf(contador));
            count=contador;
        }




        toastbutton = (Button)findViewById(R.id.toast_button);

        toastbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacer_toast();
            }
        });

        countbutton = (Button)findViewById(R.id.count_button);

        countbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contar();
            }
        });

        zerobutton = (Button)findViewById(R.id.zero_button);
        zerobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                counttextview.setText(String.valueOf(count));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("time", "Destruido " + String.valueOf(count));
        SharedPreferences settings =
                getSharedPreferences(EXAM_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putInt("Contador", count);
        prefEditor.commit();
    }

    private void hacer_toast(){
        Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
    }

    private void contar(){
        count++;
        counttextview.setText(String.valueOf(count));
    }
}
