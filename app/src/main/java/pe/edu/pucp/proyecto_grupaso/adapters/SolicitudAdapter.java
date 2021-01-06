package pe.edu.pucp.proyecto_grupaso.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.pucp.proyecto_grupaso.Mapa.MapsActivity;
import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.models.Solicitud;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


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
        holder.solicitud = solicitud;
        holder.tvDireccion.setText("Direccion: " + solicitudArrayList.get(position).getDireccion());
        holder.tvCorreo.setText("Correo: " + solicitudArrayList.get(position).getCorreoUsuario());
        holder.tvUIDdispositivo.setText(solicitudArrayList.get(position).getUidDispositivo());
        holder.tvMotivo.setText("Motivo: " + solicitudArrayList.get(position).getMotivo());
        holder.tvEstado.setText("Estado solicitud: "+ solicitudArrayList.get(position).getEstado());
    }

    @Override
    public int getItemCount() {
        return solicitudArrayList.size();
    }

    public static class SolicitudHolder extends RecyclerView.ViewHolder{

        TextView tvUIDdispositivo, tvCorreo, tvDireccion, tvMotivo, tvEstado;
        Button btnAceptar,  btnRechazar;
        Context contexto;
        Solicitud solicitud;
        String respuestaAfirmativa = "Felicidades, su solicitud fue aceptada.";
        String respuestaNegativa = "Lo sentimos, su solicitud fue rechazada.";

        ImageView mostrarMapa;

        public SolicitudHolder(@NonNull View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            tvUIDdispositivo = itemView.findViewById(R.id.tvdispositivo);
            tvCorreo = itemView.findViewById(R.id.tvcorreo);
            tvDireccion = itemView.findViewById(R.id.tvdireccion);
            tvMotivo = itemView.findViewById(R.id.tvmotivo);
            tvEstado = itemView.findViewById(R.id.tvestado);
            btnAceptar = itemView.findViewById(R.id.btnAceptarSolicitud);
            btnRechazar = itemView.findViewById(R.id.btnRechazarSolicitud);

            mostrarMapa=itemView.findViewById(R.id.imageMapa);
            mostrarMapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //ABRIR MAPA
                    String gps=solicitud.getGps();


                    Intent intent = new Intent(contexto, MapsActivity.class);

                    intent.putExtra("gps",gps);

                    contexto.startActivity(intent);
                }
            });


            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (solicitud.isMandarCorreo()){
                        mandarCorreo(solicitud.getCorreoUsuario(), respuestaAfirmativa, contexto);
                    }
                    mandarNotificacion(solicitud.getUidUsuario(), respuestaAfirmativa, contexto);
                    subirHistorial(solicitud, solicitud.getUidUsuario(), "Aceptada", contexto);
                    eliminarPendiente(solicitud, "Aceptada");

                }
            });

            btnRechazar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (solicitud.isMandarCorreo()){
                        mandarCorreo(solicitud.getCorreoUsuario(), respuestaNegativa, contexto);
                    }
                    mandarNotificacion(solicitud.getUidUsuario(), respuestaNegativa, contexto);
                    subirHistorial(solicitud, solicitud.getUidUsuario(), "Rechazada", contexto);
                    eliminarPendiente(solicitud, "Rechazada");
                }
            });

        }
    }

    public static void mandarCorreo(String correo,String respuesta, Context context){
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL, new String[] {correo});
        i.putExtra(Intent.EXTRA_SUBJECT, "Respuesta a solicitud de reserva de recursos informáticos");
        i.putExtra(Intent.EXTRA_TEXT, respuesta);

        context.startActivity(Intent.createChooser(i, "Elija con qué plataforma enviar correo"));
    }


    public static void mandarNotificacion(String uid, final String mensaje, final Context context){

        FirebaseDatabase.getInstance().getReference().child("Tokens").child(uid).child("token")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String token;
                        token = snapshot.getValue(String.class);
                        Log.d("infoApp",token);
                        notificarUsuario(token, mensaje, context);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    public static void notificarUsuario(String token, String mensaje, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject json = new JSONObject();

        try{
            json.put("to", token);
            JSONObject miNotificacion = new JSONObject();
            miNotificacion.put("title", "Respuesta a Solicitud");
            miNotificacion.put("body", mensaje);
            json.put("data", miNotificacion);

            String URL = "https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, json, null, null){
                @Override
                public Map<String, String> getHeaders(){
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=AAAAzRHUOuQ:APA91bGjEIFtLYSsMpbT29HnPTjxyvSBMVusJroccMPTCX7Mq3sDwt_oBbyiSBvovBfceGC5INs68m3xPsrh9hVMVxZusmesZa2ieMbfYJBl-qh4OmntP_L6LmMioIj7Nk4KI-voDFjl");
                    return header;
                }
            };
            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void subirHistorial(Solicitud miSolicitud, String uuid, String respuesta, final Context context){
        miSolicitud.setEstado(respuesta);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Solicitudes").child("Usuarios").child(uuid).push().setValue(miSolicitud)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Solicitud Aceptada", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error: No se pudo aceptar solicitud", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void eliminarPendiente(final Solicitud solicitud, final String mensaje){

        FirebaseDatabase.getInstance().getReference().child("Solicitudes").child("Admin")
                .orderByChild("motivo").equalTo(solicitud.getMotivo())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                            final String key = childSnapshot.getKey();
                            if (mensaje.equals("Aceptada")){
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Equipos").child(solicitud.getUidDispositivo()).child("stock")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                int cant;
                                                cant = snapshot.getValue(int.class);
                                                cant = cant - 1;
                                                FirebaseDatabase.getInstance().getReference()
                                                        .child("Equipos").child(solicitud.getUidDispositivo()).child("stock")
                                                        .setValue(cant);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                            }
                            FirebaseDatabase.getInstance().getReference().child("Solicitudes").child("Admin")
                                    .child(key).removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }
}
