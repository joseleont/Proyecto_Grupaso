package pe.edu.pucp.proyecto_grupaso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

public class AgregarDispositivo extends AppCompatActivity {

    EditText etOtro;
    EditText etMarca;
    EditText etCaracteristicas;
    EditText etIncluye;
    EditText etCantidad;
    Spinner spinnerTipo;
    String tipo;
    int cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_dispositivo);

        etOtro = findViewById(R.id.etOtros);
        etMarca = findViewById(R.id.etMarca);
        etCaracteristicas = findViewById(R.id.etCaracteristicas);
        etCantidad = findViewById(R.id.etCantidad);
        etIncluye = findViewById(R.id.etIncluye);
        spinnerTipo = findViewById(R.id.spinnerTipo);

    }

    public void AgregarFoto (View view){

    }

    public void AgregarDispositivo (View view){

    }


    public  void Cancelar (View view){
        finish();
    }
}