package pe.edu.pucp.proyecto_grupaso.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.fragments.ClientListDispositivosFragment;
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
        String countText = "No disponible";
        final Dispositivo dispositivo = dispositivos.get(position);

        if (dispositivo.getStock() >= 1) countText = dispositivo.getStock() + " en stock";

        holder.getDeviceBrand().setText(dispositivo.getMarca());
        holder.getDeviceName().setText(dispositivo.getIncluye());
        holder.getDeviceType().setText(dispositivo.getTipo());
        holder.getDeviceCount().setText(countText);

        Picasso.get().load(dispositivo.getFoto()).into(holder.getDevicePhoto());
        Log.d("ABIS",""+dispositivo.getFoto());

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
