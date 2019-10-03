package com.example.intentsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    /**
     *
     * PARA DIBUJAR EL MENÚ
     * 1) DEFINO UN XML EN RES/MENU QUE REPRESENTA EL MENÚ
     * 2) SOBREESCRIBIR EL MÉTODO ONCREATEOPTIONSMENU
     * 3) PARA DETECTAR LOS EVENTOS DE MENÚ DEBO SOBREESCRIBIR EL onOptionsItemSelected
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dibujarFlechaAtras();

        //Ejemplo de Intent implícito: digo que quiero VER una WEB
        //ANDROID BUSCA LAS ACTIVIDADES CANDIDATAS Y PUEDEN PASAR 3 COSAS
            //1 Que no encuentre ninguna app en el movil capaz de llevar a cabo la acción
            //2 Que encuentre una app candidata --> abrirlo
            //3 Que encuentre más de una app candidata --> ¿con qué quieres abrirlo?
        String url = "http://www.marca.com";//Compongo la cadena;
        Intent intent = null;
        Uri uri = null;
        uri = Uri.parse(url);//y Su URI "Paso de String a URI"
        intent = new Intent(Intent.ACTION_VIEW, uri); //creo el intent
        if (intent.resolveActivity(getPackageManager())!=null)
        {
            Log.d("MIAPP", "Hay al menos una app en el dispositivo " +
                    "que puede llevar a cabao esta acción");
            startActivity(intent);//lanzo la activida
        } else {
            Log.d("MIAPP", "NO una app en el dispositivo " +
                    "que puede llevar a cabao esta acción");
            Toast.makeText(this, "NO SE ENCUENTRA APP COMPATIBLE", Toast.LENGTH_SHORT).show();


        }


    }

    private void dibujarFlechaAtras()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dibujo la flecha

    }

    //ESTE MÉTODO SE INVOCA AL TOCAR UNA OPCIÓN DEL MENÚ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("MIAPP", "Se ha tocado un elemento de la barra/menú");

        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "Ha tocado la flecha de para atrás");
                finish();
                break;
            case R.id.salir:

                Log.d("MIAPP", "Ha tocado la opción de salir");
                break;
            case R.id.buscar:
                Log.d("MIAPP", "Ha tocado la opción de buscar");
                break;
        }

        return true;//super.onOptionsItemSelected(item);
    }

    //debo sobrrescribir este método para cargar mi menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();//este objeto será el encargado de cargar/inlfar mi menú
        mi.inflate(R.menu.menu_del_dia, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
