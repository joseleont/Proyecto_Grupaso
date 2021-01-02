package pe.edu.pucp.proyecto_grupaso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AgregarDispositivo extends AppCompatActivity {

    String textSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_dispositivo);







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


    }




    public void agregarNuevoDispositivo(View view){

        Button btnAgregarNuevoDipositivo=findViewById(R.id.btnAgregarNuevoDipositivo);
        EditText editMarca=findViewById(R.id.editTextMarca);
        String marca=editMarca.getText().toString();

    }








}