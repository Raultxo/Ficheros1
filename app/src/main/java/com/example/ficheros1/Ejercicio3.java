package com.example.ficheros1;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Ejercicio3 extends AppCompatActivity {
    private ListView lvWebFav;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio3);

        lvWebFav = findViewById(R.id.lvWebFav);
        lvWebFav.setOnItemClickListener((parent, view, position, id) -> {
            Web web = (Web) parent.getItemAtPosition(position);
            String urlWeb = web.getUrlWeb().trim();

            Uri uri = Uri.parse(urlWeb);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        llenarListaWebs();
    }

    private void llenarListaWebs() {

        ArrayList<Web>listaWebFav = conseguirWebsDeRecurso();
        Web[] datos = new Web[listaWebFav.size()];
        for(int contDatos = 0; contDatos < datos.length; contDatos++)
            datos[contDatos] = listaWebFav.get(contDatos);

        AdaptadorTitulares adaptadorTitulares = new AdaptadorTitulares(this, datos);
        lvWebFav.setAdapter(adaptadorTitulares);
    }

    private ArrayList<Web> conseguirWebsDeRecurso() {
        ArrayList<Web>listaWebFav = new ArrayList<>();
        try {
            InputStream is = getResources().openRawResource(R.raw.webs);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linea = br.readLine();
            for (int contLinea = 0; linea != null; contLinea++) {
                try {
                    String[] lineaSeccionada = linea.split(";");
                    if(lineaSeccionada.length != 4)
                        throw new Exception("La línea número " + contLinea + " del archivo ej3_webs_fav.txt ha sido mal formateada.");
                    String nomWeb = lineaSeccionada[0];
                    String urlWeb = lineaSeccionada[1];
                    String logoWeb = lineaSeccionada[2];
                    String idWeb = lineaSeccionada[3];

                    Web webFavorita = new Web(nomWeb,urlWeb,logoWeb,idWeb);
                    listaWebFav.add(webFavorita);
                }
                catch(Exception e)
                {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG ).show();
                }
                linea = br.readLine();
            }
            br.close();
            return listaWebFav;
        }
        catch(Exception e) {
            Toast.makeText(this,"No se ha conseguido acceder al archivo contenedor de Webs Favoritas.",Toast.LENGTH_LONG ).show();
        }
        return new ArrayList<>();
    }

    private static class AdaptadorTitulares extends ArrayAdapter<Web> {
        private final Web[] datos;

        public AdaptadorTitulares(Context context, Web[] datos) {
            super(context, R.layout.listitem_web, datos);
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            @SuppressLint({"InflateParams", "ViewHolder"}) View item = inflater.inflate(R.layout.listitem_web, null);

            String idLogoWeb = datos[position].getLogoWeb().trim();
            int resLogoWebID = Ejercicio3.getResID(idLogoWeb);
            ImageView imgWeb = item.findViewById(R.id.imgWeb);
            if(resLogoWebID != -1)
                imgWeb.setImageResource(resLogoWebID);

            TextView tvNomWeb = item.findViewById(R.id.tvNomWeb);
            tvNomWeb.setText(datos[position].toString());

            TextView tvUrlWeb = item.findViewById(R.id.tvUrlWeb);
            tvUrlWeb.setText(datos[position].getUrlWeb());

            TextView tvIdWeb = item.findViewById(R.id.tvIdWeb);
            tvIdWeb.setText(datos[position].getIdWeb());

            return item;
        }
    }

    private static int getResID(String resName) {
        try {
            Field idField = R.drawable.class.getDeclaredField(resName);
            return idField.getInt(idField);
        }
        catch (Exception e) {
            return -1;
        }
    }
}