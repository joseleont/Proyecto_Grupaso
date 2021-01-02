package pe.edu.pucp.proyecto_grupaso.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class DispositivoAdapter extends RecyclerView.Adapter<DispositivoViewHolder> {

    ArrayList<Dispositivo> dispositivos;

    public DispositivoAdapter(ArrayList<Dispositivo> devices) {
        this.dispositivos = devices;
    }

    @NonNull
    @Override
    public DispositivoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DispositivoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
