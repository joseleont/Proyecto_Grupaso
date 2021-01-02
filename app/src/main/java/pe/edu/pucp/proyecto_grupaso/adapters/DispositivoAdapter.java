package pe.edu.pucp.proyecto_grupaso.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class DispositivoAdapter extends RecyclerView.Adapter<DispositivoViewHolder> {

    ArrayList<Dispositivo> dispositivos;

    public DispositivoAdapter(ArrayList<Dispositivo> devices) {
        this.dispositivos = devices;
    }

    @NonNull
    @Override
    public DispositivoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dispositivo_unico, parent, false);
        return new DispositivoViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull DispositivoViewHolder holder, int position) {
        // TODO
    }

    @Override
    public int getItemCount() {
        if (this.getDispositivos() != null) {
            return this.getDispositivos().size();
        } else {
            return 0;
        }
    }

    public ArrayList<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(ArrayList<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }
}
