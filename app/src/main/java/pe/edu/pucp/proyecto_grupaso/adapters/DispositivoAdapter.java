package pe.edu.pucp.proyecto_grupaso.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.Reservar;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class DispositivoAdapter extends RecyclerView.Adapter<DispositivoViewHolder> {

    ArrayList<Dispositivo> dispositivos;
    Context ctx;

    public DispositivoAdapter(ArrayList<Dispositivo> devices) {
        this.dispositivos = devices;
    }

    @NonNull
    @Override
    public DispositivoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.ctx = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dispositivo_unico, parent, false);
        return new DispositivoViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull DispositivoViewHolder holder, int position) {
        String countText = "No disponible";
        final Dispositivo dispositivo = dispositivos.get(position);

        if (dispositivo.getStock() >= 1) countText = dispositivo.getStock() + " en stock";

        holder.getDeviceBrand().setText(dispositivo.getMarca());
        holder.getDeviceName().setText(dispositivo.getIncluye());
        holder.getDeviceType().setText(dispositivo.getTipo());
        holder.getDeviceCount().setText(countText);

        Picasso.get().load(dispositivo.getFoto()).into(holder.getDevicePhoto());
        Log.d("ABIS", "" + dispositivo.getFoto());

        holder.getDeviceReserva().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservarDispositivo(ctx, dispositivo);
            }
        });

    }

    private void reservarDispositivo(Context context, Dispositivo dispositivo) {

        Intent intent = new Intent(ctx, Reservar.class);
        intent.putExtra("devuid", dispositivo.getUid());
        intent.putExtra("usrmail", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        ctx.startActivity(intent);
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
