package pe.edu.pucp.proyecto_grupaso.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.AgregarDispositivo;
import pe.edu.pucp.proyecto_grupaso.ModificarDispositivo;
import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.models.Dispositivo;

public class DispositivoExtendidoAdapter extends RecyclerView.Adapter<DispositivoExtendidoAdapter.DispositivoExtendidoHolder> {
    private ArrayList<Dispositivo> dispositivoArrayList;
    private Context context;

    public DispositivoExtendidoAdapter(ArrayList<Dispositivo> dispositivoArrayList, Context context) {
        this.dispositivoArrayList = dispositivoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DispositivoExtendidoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_dispositivo_extendido,parent,false);
        DispositivoExtendidoAdapter.DispositivoExtendidoHolder dispositivoExtendidoHolder =
                new DispositivoExtendidoAdapter.DispositivoExtendidoHolder(itemview);
        return dispositivoExtendidoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DispositivoExtendidoHolder holder, int position) {
        Dispositivo dispositivo  = dispositivoArrayList.get(position);
        holder.dispositivo = dispositivo;
        holder.marca.setText("Marca: "+ dispositivoArrayList.get(position).getMarca());
        holder.caracteristicas.setText("Características: "+ dispositivoArrayList.get(position).getCaracteristicas());
        holder.incluye.setText("Incluye: "+ dispositivoArrayList.get(position).getIncluye());
        holder.stock.setText("Stock: "+ dispositivoArrayList.get(position).getStock());
        holder.tipo.setText("Tipo: "+ dispositivoArrayList.get(position).getTipo());

        Picasso.get().load(dispositivoArrayList.get(position).getFoto()).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return dispositivoArrayList.size();
    }

    public static class DispositivoExtendidoHolder extends RecyclerView.ViewHolder{

        Dispositivo dispositivo;
        TextView marca, caracteristicas,  incluye, stock, tipo;
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
            tipo = itemView.findViewById(R.id.tipoDispositivoExt);
            btnEditar = itemView.findViewById(R.id.btnEditarDis);
            btnEliminar = itemView.findViewById(R.id.btnEliminarDis);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase.getInstance().getReference().child("Equipos")
                            .orderByChild("foto").equalTo(dispositivo.getFoto())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                                        String key = childSnapshot.getKey();
                                        FirebaseDatabase.getInstance().getReference().child("Equipos")
                                                .child(key).removeValue();
                                        Toast.makeText(contexto,"Borrado exitoso",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(contexto,"Error en el borrado",Toast.LENGTH_SHORT).show();
                                }
                    });
                }
            });

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase.getInstance().getReference().child("Equipos")
                            .orderByChild("foto").equalTo(dispositivo.getFoto())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                                        String key = childSnapshot.getKey();
                                        Intent i = new Intent(contexto, ModificarDispositivo.class);
                                        i.putExtra("key", key);
                                        contexto.startActivity(i);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(contexto,"Error en el borrado",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });


        }
    }
}
