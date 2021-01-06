package pe.edu.pucp.proyecto_grupaso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModificarDispositivo extends AppCompatActivity {
    String key;
    String linkImagen = "";
    EditText etmarca, etcaracteristicas, etincluye, etcant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_dispositivo);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        etmarca = findViewById(R.id.etNuevoMarca);
        etcaracteristicas = findViewById(R.id.etNuevoCarac);
        etincluye = findViewById(R.id.etNuevoIncluye);
        etcant = findViewById(R.id.etNuevoCant);

    }

    public void ModificarFoto(View view){
        Intent i = new Intent (this, AgregarFotoDispositivo.class);
        int request = 5000;
        startActivityForResult(i,request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5000 && resultCode == RESULT_OK) {
            linkImagen = data.getStringExtra("imagenLink");
        }
    }

    public void ModificarDatos(View view){

        boolean flag = false;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Equipos").child(key);

        String marca = etmarca.getText().toString();
        String caracteristicas = etcaracteristicas.getText().toString();
        String incluye = etincluye.getText().toString();
        String cantidad = etcant.getText().toString();

        if (!marca.isEmpty()){
            databaseReference.child("marca").setValue(marca);
            flag=true;
        }
        if (!caracteristicas.isEmpty()){
            databaseReference.child("caracteristicas").setValue(caracteristicas);
            flag=true;
        }
        if (!incluye.isEmpty()){
            databaseReference.child("incluye").setValue(incluye);
            flag=true;
        }
        if (!cantidad.isEmpty()){
            databaseReference.child("stock").setValue(cantidad);
            flag=true;
        }
        if (!linkImagen.isEmpty()){
            databaseReference.child("foto").setValue(linkImagen);
            flag=true;
        }

        if (flag){
            Toast.makeText(this,"Modificaciones realizadas",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"No se realizaron modificaciones",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public  void Cancelar (View view){
        finish();
    }
}