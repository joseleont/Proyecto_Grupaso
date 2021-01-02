package pe.edu.pucp.proyecto_grupaso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AgregarDispositivo extends AppCompatActivity {

    String textSpinner;
=======
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
>>>>>>> f1431e9f327e8b56655ea3fc715e9db8d668371a

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_dispositivo);

<<<<<<< HEAD






        final Spinner spinner = (Spinner) findViewById(R.id.spinnerDispositivosAgregar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Dispositivos, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                EditText textOtro=findViewById(R.id.editTextOtro);
                if(adapterView.getItemAtPosition(position).toString().equals("Otro")){
                    //poner viisble el editText para que se ingrese el nuevo dispositivo
                    textOtro.setVisibility(View.VISIBLE);
                }else{
                    textOtro.setVisibility(View.GONE);
                }
                textSpinner=adapterView.getItemAtPosition(position).toString();

            }
        });


=======
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
>>>>>>> f1431e9f327e8b56655ea3fc715e9db8d668371a
    }




    public void agregarNuevoDispositivo(View view){

        Button btnAgregarNuevoDipositivo=findViewById(R.id.btnAgregarNuevoDipositivo);
        EditText editMarca=findViewById(R.id.editTextMarca);
        String marca=editMarca.getText().toString();

    }








}