package pe.edu.pucp.proyecto_grupaso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class AgregarDispositivo extends AppCompatActivity {

    EditText etOtro;
    EditText etMarca;
    EditText etCaracteristicas;
    EditText etIncluye;
    EditText etCantidad;
    Spinner spinnerTipo;
    String linkImagen;


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
        Intent i = new Intent (this, AgregarFotoDispositivo.class);
        int request = 1000;
        startActivityForResult(i,request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            linkImagen = data.getStringExtra("imagenLink");
        }
    }

    public void AgregarDispositivo (View view){
        Dispositivo miDispositivo = new Dispositivo();
        String marca = etMarca.getText().toString();
        String caracteristicas = etCaracteristicas.getText().toString();
        String incluye = etIncluye.getText().toString();
        String tipo = spinnerTipo.getSelectedItem().toString();
        if (tipo.equals("Otros")){
            tipo = etOtro.getText().toString();
        }
        String cantidad = etCantidad.getText().toString();

        if (!marca.isEmpty() &&
                !caracteristicas.isEmpty() &&
                !incluye.isEmpty() &&
                !cantidad.isEmpty() &&
                !tipo.isEmpty() &&
                !linkImagen.isEmpty()){

            miDispositivo.setFoto(linkImagen);
            miDispositivo.setTipo(tipo);
            miDispositivo.setMarca(marca);
            miDispositivo.setCaracteristicas(caracteristicas);
            miDispositivo.setStock(Integer.parseInt(cantidad));
            miDispositivo.setIncluye(incluye);

            subirDispositivo(miDispositivo);
        }
        else{
            Toast.makeText(getApplicationContext(), "Complete todos los campos antes de agregar", Toast.LENGTH_SHORT).show();
        }
    }

    public void subirDispositivo(Dispositivo dispositivo){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Equipos").push().setValue(dispositivo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Dispositivo agregado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public  void Cancelar (View view){
        finish();
    }
}
