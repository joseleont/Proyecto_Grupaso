package pe.edu.pucp.proyecto_grupaso;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pe.edu.pucp.proyecto_grupaso.models.Solicitud;

public class Reservar extends AppCompatActivity {

    private GoogleMap mMap;

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

        obtenerGPS();

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
                sol.setGps(gps.getText().toString());
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


    public void obtenerGPS(){


        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permiso == PackageManager.PERMISSION_GRANTED) {

            geolocalizarMapa();


        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


    }

     public void geolocalizarMapa(){

         FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
             // TODO: Consider calling
             //    ActivityCompat#requestPermissions
             // here to request the missing permissions, and then overriding
             //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
             //                                          int[] grantResults)
             // to handle the case where the user grants the permission. See the documentation
             // for ActivityCompat#requestPermissions for more details.
             return;
         }


         locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
             @Override
             public void onSuccess(Location location) {

                 //Se obtiene la ubicacion
                  LatLng ubicacion2 = new LatLng(location.getLatitude(), location.getLongitude());
                 //  Log.d("InfoApp",location.getLatitude()+"/"+location.getLongitude());
                 gps.setText(location.getLatitude()+"/"+location.getLongitude());



             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 e.printStackTrace();
             }
         });

     }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            geolocalizarMapa();
        } else {
            Log.d("infoApp", "no me dio permisos :(");
        }
    }
}