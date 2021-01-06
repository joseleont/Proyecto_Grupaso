package pe.edu.pucp.proyecto_grupaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.proyecto_grupaso.activities.MainActivity;

public class GestionDispositivos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_dispositivos);
    }


    public void agregarDispositivo(View view){
        startActivity(new Intent(GestionDispositivos.this, AgregarDispositivo.class));

    }
}