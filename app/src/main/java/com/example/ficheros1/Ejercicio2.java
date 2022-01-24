package com.example.ficheros1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinProvincias;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio2);

        spinProvincias = findViewById(R.id.spinProvincias);
        spinProvincias.setOnItemSelectedListener(this);

        llenarSpinnerDeProvincias();
    }

    private void llenarSpinnerDeProvincias() {
        try {
            ArrayList<String>datos = new ArrayList<>();

            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.provincias)));

            String linea = br.readLine();
            while (linea != null) {
                datos.add(linea);
                linea = br.readLine();
            }
            br.close();

            ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinProvincias.setAdapter(adaptador);
        }
        catch(Exception e) {
            Log.e("Ejercicio2", "Error al cargar el recurso");
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView txtView = (TextView) view;
        Toast.makeText(this, "Provincia " + txtView.getText() + " seleccionada.", Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {}
}