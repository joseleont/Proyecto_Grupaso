package pe.edu.pucp.proyecto_grupaso.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class DispositivoExtendidoAdapter extends RecyclerView.Adapter<DispositivoExtendidoAdapter.DispositivoExtendidoHolder> {
    private ArrayList<Dispositivo> dispositivoArrayList;
    private Context context;

    public DispositivoExtendidoAdapter(ArrayList<Dispositivo> dispositivoArrayList) {
        this.dispositivoArrayList = dispositivoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DispositivoExtendidoAdapter.DispositivoExtendidoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_dispositivo_extendido,parent,false);
        DispositivoExtendidoAdapter.DispositivoExtendidoHolder dispositivoExtendidoHolder =
                new DispositivoExtendidoAdapter.DispositivoExtendidoHolder(itemview);
        return dispositivoExtendidoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DispositivoExtendidoAdapter.DispositivoExtendidoHolder holder, int position) {
        Dispositivo dispositivo  = dispositivoArrayList.get(position);
        holder.dispositivo = dispositivo;
        holder.marca.setText("Marca: "+ dispositivoArrayList.get(position).getMarca());
        holder.caracteristicas.setText("Características: "+ dispositivoArrayList.get(position).getCaracteristicas());
        holder.incluye.setText("Incluye: "+ dispositivoArrayList.get(position).getIncluye());
        holder.stock.setText("Stock: "+ dispositivoArrayList.get(position).getStock());

        Picasso.get().load(dispositivoArrayList.get(position).getFoto()).into(holder.foto);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DispositivoExtendidoHolder extends RecyclerView.ViewHolder{

        Dispositivo dispositivo;
        TextView marca, caracteristicas,  incluye, stock;
        Button btnEditar, btnEliminar;
        ImageView foto;
        Context contexto;

        public DispositivoExtendidoHolder(@NonNull View itemView) {
            super(itemView);

            contexto = itemView.getContext();
            marca = itemView.findViewById(R.id.marcaDispositivo);
            caracteristicas = itemView.findViewById(R.id.caracDispositivo);
            foto = itemView.findViewById(R.id.imagenDispositivo);
            incluye = itemView.findViewById(R.id.incluyeDispositivo);
            stock = itemView.findViewById(R.id.cantidadDispositivo);
            btnEditar = itemView.findViewById(R.id.btnEditarDis);
            btnEliminar = itemView.findViewById(R.id.btnEliminarDis);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


        }
    }
}
