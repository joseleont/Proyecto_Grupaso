package pe.edu.pucp.proyecto_grupaso;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import pe.edu.pucp.proyecto_grupaso.models.TokenClientes;

public class FCM extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("tokenID", "mi token es: "+ s);

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from = remoteMessage.getFrom();
        Log.d("infoApp", "de: "+ from);

        if (remoteMessage.getNotification() != null){
            Log.d("infoApp", "titulo: "+ remoteMessage.getNotification().getTitle());
            Log.d("infoApp", "body: "+ remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0){
            Log.d("infoApp", "Titulo es: "+ remoteMessage.getData().get("title"));
            Log.d("infoApp", "Cuerpo es: "+ remoteMessage.getData().get("body"));
            String titulo = remoteMessage.getData().get("title");
            String cuerpo = remoteMessage.getData().get("body");
            lanzarNotificacion(titulo, cuerpo);
        }

    }

    public void lanzarNotificacion(String titulo, String cuerpo){
        String channelId = "chanel";
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(
                    channelId,
                    "Notificaciones generales",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("notificaciones generales para la aplicación");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        /*
        Intent i=  new Intent(this, ActivityLugar.class);
        i.putExtra("ubicacion", ubicacion);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
         */

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titulo)
                .setContentText(cuerpo)
                //.setStyle(new NotificationCompat.BigTextStyle()
                        //.bigText(cuerpo))
                //.setContentIntent(pendingIntent)
                .setAutoCancel(false);

        notificationManager.notify(0,notificacion.build());
    }

}
