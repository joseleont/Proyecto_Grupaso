package pe.edu.pucp.proyecto_grupaso;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pe.edu.pucp.proyecto_grupaso.models.Solicitud;

public class Reservar extends AppCompatActivity {


    EditText etDireccion;
    EditText etMotivo;
    Button reservar;
    Switch correoSwitch;
    TextView gps;

    String devuid;
    String usrmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        Intent intent = getIntent();
        devuid = intent.getExtras().getString("devuid");
        usrmail = intent.getExtras().getString("usrmail");

        Log.d("myapp", "onCreate: " + devuid + " " + usrmail);


        etDireccion = findViewById(R.id.etDireccionR);
        etMotivo = findViewById(R.id.etMotivoR);
        correoSwitch = findViewById(R.id.switcEmail);
        gps = findViewById(R.id.etGPS);

        gps.setText("aqui debe ir la ubicacion gps");


        reservar = findViewById(R.id.btnReserva);

        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Solicitud sol = new Solicitud();
                // sol.setMandarCorreo(correoSwitch.);
                sol.setUidDispositivo(devuid);
                sol.setCorreoUsuario(usrmail);
                sol.setDireccion(etDireccion.getText().toString());
                sol.setUidUsuario(FirebaseAuth.getInstance().getCurrentUser().getUid());
                sol.setMotivo(etMotivo.getText().toString());
                sol.setMandarCorreo(true);
                sol.setEstado("Pendiente");
                sol.setGps("gps");
                agregarReservaFirebase(sol);


            }
        });
    }

    private void agregarReservaFirebase(Solicitud sol) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Solicitudes/Admin").push().setValue(sol).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Reserva creada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}