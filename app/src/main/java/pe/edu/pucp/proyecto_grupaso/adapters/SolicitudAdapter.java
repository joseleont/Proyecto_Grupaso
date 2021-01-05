package pe.edu.pucp.proyecto_grupaso.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.models.Solicitud;

public class SolicitudAdapter extends RecyclerView.Adapter<SolicitudAdapter.SolicitudHolder> {
    private ArrayList<Solicitud> solicitudArrayList;
    private Context context;

    public SolicitudAdapter(ArrayList<Solicitud> solicitudArrayList, Context context) {
        this.solicitudArrayList = solicitudArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SolicitudHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_solicitud_pendiente,parent,false);
        SolicitudHolder solicitudHolder = new SolicitudHolder(itemview);
        return solicitudHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudHolder holder, int position) {
        Solicitud solicitud = solicitudArrayList.get(position);
        holder.tvDireccion.setText(solicitudArrayList.get(position).getDireccion());
        holder.tvCorreo.setText(solicitudArrayList.get(position).getCorreoUsuario());
        holder.tvUIDdispositivo.setText(solicitudArrayList.get(position).getUidDispositivo());

    }

    @Override
    public int getItemCount() {
        return solicitudArrayList.size();
    }

    public static class SolicitudHolder extends RecyclerView.ViewHolder{

        TextView tvUIDdispositivo;
        TextView tvCorreo;
        TextView tvDireccion;
        Button btnAceptar;
        Button btnRechazar;
        Context contexto;
        Solicitud solicitud;

        public SolicitudHolder(@NonNull View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            tvUIDdispositivo = itemView.findViewById(R.id.tvdispositivo);
            tvCorreo = itemView.findViewById(R.id.tvcorreo);
            tvDireccion = itemView.findViewById(R.id.tvdireccion);
            btnAceptar = itemView.findViewById(R.id.btnAceptarSolicitud);
            btnRechazar = itemView.findViewById(R.id.btnRechazarSolicitud);

            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (solicitud.isMandarCorreo()){

                    }
                }
            });


            btnRechazar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }


}
