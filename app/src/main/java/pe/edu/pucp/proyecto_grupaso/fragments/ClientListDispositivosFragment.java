package pe.edu.pucp.proyecto_grupaso.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.adapters.DispositivoAdapter;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class ClientListDispositivosFragment extends Fragment {

    private RecyclerView myDeviceRv;
    // TODO anadir un edittext o algo para filtrar (un search view)
    // too tired for this


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String filter = ""; // TODO change this string to the value from the search view

        View view = inflater.inflate(R.layout.fragment_client_list_dispositivos, container, false);
        myDeviceRv = view.findViewById(R.id.mydevicelistrv);
        myDeviceRv.setLayoutManager(new LinearLayoutManager(view.getContext()));

        myDeviceRv.setAdapter(new DispositivoAdapter(filteredDeviceList(filter)));

        return view;
    }

    ArrayList<Dispositivo> filteredDeviceList(String filter) {
        // TODO filter puede ser marca o type XD
        return null;
    }
}