package pe.edu.pucp.proyecto_grupaso.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.RegistroNuevoCliente;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registrar(View view){

        startActivity(new Intent(MainActivity.this, RegistroNuevoCliente.class));

    }


}