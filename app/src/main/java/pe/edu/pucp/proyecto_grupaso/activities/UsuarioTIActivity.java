package pe.edu.pucp.proyecto_grupaso.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.proyecto_grupaso.GestionDispositivos;
import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.VisualizarPedidos;

public class UsuarioTIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial_usuario_t_i);

    }

    public void GestionarDispositivos (View view){
        startActivity(new Intent(this, GestionDispositivos.class));
    }

    public void VisualizarPedidos (View view){
        startActivity(new Intent(this, VisualizarPedidos.class));
    }

}