package pe.edu.pucp.proyecto_grupaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.adapters.SolicitudAdapter;
import pe.edu.pucp.proyecto_grupaso.models.Solicitud;

import static java.lang.Boolean.parseBoolean;

public class HistorialSolicitudes extends AppCompatActivity {
    String correo;
    RecyclerView miRecyclerView;
    ArrayList<Solicitud> solicitudArrayList;
    SolicitudAdapter miRecyclerAdapter;
    String usuarioUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_solicitudes);

        miRecyclerView = findViewById(R.id.recyclerViewHistorial);
        LinearLayoutManager layoutRecyler = new LinearLayoutManager(this);
        miRecyclerView.setLayoutManager(layoutRecyler);
        miRecyclerView.setHasFixedSize(true);

        usuarioUUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        solicitudArrayList = new ArrayList<>();
        limpiarArrayList();
        obtenerSolicitudesFirebase(correo);
    }

    public void obtenerSolicitudesFirebase(String email){
        FirebaseDatabase.getInstance().getReference().child("Solicitudes").child("Usuarios").child(usuarioUUID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        limpiarArrayList();
                        for (DataSnapshot misnapshot : snapshot.getChildren()){
                            Solicitud solicitud = new Solicitud();
                            solicitud.setGps(misnapshot.child("gps").getValue().toString());
                            solicitud.setCorreoUsuario(misnapshot.child("correoUsuario").getValue().toString());
                            solicitud.setDireccion(misnapshot.child("direccion").getValue().toString());
                            solicitud.setEstado(misnapshot.child("estado").getValue().toString());
                            solicitud.setMandarCorreo(parseBoolean(misnapshot.child("mandarCorreo").getValue().toString()));
                            solicitud.setMotivo(misnapshot.child("motivo").getValue().toString());
                            solicitud.setUidDispositivo(misnapshot.child("uidDispositivo").getValue().toString());
                            solicitud.setUidUsuario(misnapshot.child("uidUsuario").getValue().toString());
                            solicitudArrayList.add(solicitud);
                        }

                        miRecyclerAdapter = new SolicitudAdapter(solicitudArrayList, getApplicationContext(),"cliente");
                        miRecyclerView.setAdapter(miRecyclerAdapter);
                        miRecyclerAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void limpiarArrayList(){
        if (solicitudArrayList != null){
            solicitudArrayList.clear();
            if (miRecyclerAdapter != null){
                miRecyclerAdapter.notifyDataSetChanged();
            }
        }
    }



}