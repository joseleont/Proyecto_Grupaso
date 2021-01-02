package pe.edu.pucp.proyecto_grupaso.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.adapters.DispositivoAdapter;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class ClientListDispositivosFragment extends Fragment {

    private RecyclerView myDeviceRv;
    private EditText etSearch;
    private Button filterBtn;

    String filter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Equipos");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_client_list_dispositivos, container, false);

        myDeviceRv = view.findViewById(R.id.mydevicelistrv);
        myDeviceRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        myDeviceRv.setAdapter(new DispositivoAdapter(filteredDeviceList(filter)));

        etSearch = view.findViewById(R.id.editTextSearch);

        filterBtn = view.findViewById(R.id.btnSearch);

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter = etSearch.getText().toString();
                if (!filter.equals("") && !filter.equals(null)) {
                    // cambiar el adapter de myDeviceRv por el nuevo adapter (?)
                    // no estoy seguro si eso va a funcionar jzjzjzj
                    myDeviceRv.setAdapter(null);
                    myDeviceRv.setAdapter(new DispositivoAdapter(filteredDeviceList(filter)));
                }
            }
        });

        return view;
    }

    ArrayList<Dispositivo> filteredDeviceList(String filter) {

        final ArrayList<Dispositivo> losqueconcuerdan = new ArrayList<>();

        if (!filter.equals("") && !filter.equals(null)) {

            // obtener los equipos que concuerdan con el filtro
            // y mandarlos a los que concuerdan

            myRef.orderByChild("marca").equalTo(filter).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Dispositivo mydevice = snapshot.getValue(Dispositivo.class);
                    losqueconcuerdan.add(mydevice);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.orderByChild("tipo").equalTo(filter).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Dispositivo mydevice = snapshot.getValue(Dispositivo.class);
                    losqueconcuerdan.add(mydevice);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {

            // obtener todos los equipos registrados
            // y mandarlos al arraylist

            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Dispositivo mydevice = snapshot.getValue(Dispositivo.class);
                    losqueconcuerdan.add(mydevice);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

        return new ArrayList<>(new HashSet<>(losqueconcuerdan));
    }
}