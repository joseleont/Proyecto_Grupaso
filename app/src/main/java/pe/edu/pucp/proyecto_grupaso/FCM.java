package pe.edu.pucp.proyecto_grupaso;

import android.util.Log;

import androidx.annotation.NonNull;

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
    }

    public void guardarToken(String token){
        TokenClientes miTokenCliente = new TokenClientes();
        String miUsuarioUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        miTokenCliente.setUsuarioUID(miUsuarioUID);
        miTokenCliente.setToken(token);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Tokens").child(miUsuarioUID).setValue(miTokenCliente);
        FirebaseInstanceId.getInstance().getToken();
    }

}
