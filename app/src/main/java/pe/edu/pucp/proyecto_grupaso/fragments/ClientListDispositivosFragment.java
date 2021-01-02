package pe.edu.pucp.proyecto_grupaso.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.adapters.DispositivoAdapter;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class ClientListDispositivosFragment extends Fragment {

    private RecyclerView myDeviceRv;
    private String marca, type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_list_dispositivos, container, false);
        myDeviceRv = view.findViewById(R.id.mydevicelistrv);
        myDeviceRv.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // TODO aqui creo que debo ponerle la lista total de dispositivos como parametro
        myDeviceRv.setAdapter(new DispositivoAdapter(filteredDeviceList(marca, type)));

        return view;
    }

    ArrayList<Dispositivo> filteredDeviceList(String marca, String type){
        return null;
    }
}