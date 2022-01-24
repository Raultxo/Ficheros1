package com.example.ficheros1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ejercicio1(View view) {
        Intent intent = new Intent(MainActivity.this, Ejercicio1.class);
        startActivity(intent);
    }

    public void ejercicio2(View view) {
        Intent intent = new Intent(MainActivity.this, Ejercicio2.class);
        startActivity(intent);
    }

    public void ejercicio3(View view) {
        Intent intent = new Intent(MainActivity.this, Ejercicio3.class);
        startActivity(intent);
    }

}