package pe.edu.pucp.proyecto_grupaso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.activities.MainActivity;
import pe.edu.pucp.proyecto_grupaso.adapters.DispositivoExtendidoAdapter;
import pe.edu.pucp.proyecto_grupaso.adapters.SolicitudAdapter;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;
import pe.edu.pucp.proyecto_grupaso.models.Solicitud;

public class GestionDispositivos extends AppCompatActivity {

    RecyclerView miRecyclerView;
    ArrayList<Dispositivo> dispositivoArrayList;
    DispositivoExtendidoAdapter miRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_dispositivos);
    }

    public void AgregarDispositivo(View view){
        startActivity(new Intent(this, AgregarDispositivo.class));
    }

}