package pe.edu.pucp.proyecto_grupaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.activities.MainActivity;
import pe.edu.pucp.proyecto_grupaso.adapters.DispositivoExtendidoAdapter;
import pe.edu.pucp.proyecto_grupaso.adapters.SolicitudAdapter;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;
import pe.edu.pucp.proyecto_grupaso.models.Solicitud;

import static java.lang.Boolean.parseBoolean;

public class GestionDispositivos extends AppCompatActivity {

    RecyclerView miRecyclerView;
    ArrayList<Dispositivo> dispositivoArrayList;
    DispositivoExtendidoAdapter miRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_dispositivos);

        miRecyclerView = findViewById(R.id.recyclerViewGestion);
        LinearLayoutManager layoutRecyler = new LinearLayoutManager(this);
        miRecyclerView.setLayoutManager(layoutRecyler);
        miRecyclerView.setHasFixedSize(true);

        dispositivoArrayList = new ArrayList<>();
        limpiarArrayList();
        obtenerDispositivosFirebase();
    }

    //OBTENER DATOS DE DISPOSITIVO DEL FIREBASE
    public void obtenerDispositivosFirebase(){
        FirebaseDatabase.getInstance().getReference().child("Equipos")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        limpiarArrayList();
                        for (DataSnapshot misnapshot : snapshot.getChildren()){
                            Dispositivo dispositivo = new Dispositivo();
                            dispositivo.setMarca(misnapshot.child("marca").getValue().toString());
                            dispositivo.setTipo(misnapshot.child("tipo").getValue().toString());
                            dispositivo.setStock(Integer.parseInt(misnapshot.child("stock").getValue().toString()));
                            dispositivo.setIncluye(misnapshot.child("incluye").getValue().toString());
                            dispositivo.setFoto(misnapshot.child("foto").getValue().toString());
                            dispositivo.setCaracteristicas(misnapshot.child("caracteristicas").getValue().toString());

                            dispositivoArrayList.add(dispositivo);
                        }
                        miRecyclerAdapter = new DispositivoExtendidoAdapter(dispositivoArrayList, getApplicationContext());
                        miRecyclerView.setAdapter(miRecyclerAdapter);
                        miRecyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    public void AgregarDispositivo(View view){
        startActivity(new Intent(this, AgregarDispositivo.class));
    }

    public void limpiarArrayList(){
        if (dispositivoArrayList != null){
            dispositivoArrayList.clear();
            if (miRecyclerAdapter != null){
                miRecyclerAdapter.notifyDataSetChanged();
            }
        }
    }

}