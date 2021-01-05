package pe.edu.pucp.proyecto_grupaso.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.models.TokenClientes;
import pe.edu.pucp.proyecto_grupaso.fragments.ClientListDispositivosFragment;

public class ClienteActivity extends AppCompatActivity {

    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    ClientListDispositivosFragment clf = new ClientListDispositivosFragment();

    // ver si currentuser != null y ademas que no sea uno de los de IT y sino volver al main activity
    // FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial_cliente);
        actualizarToken();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.myClientFrameLayout, ClientListDispositivosFragment.newInstance()).commit();
        // fm.beginTransaction().add(clf,)

    }

    public void actualizarToken(){
        TokenClientes miTokenCliente = new TokenClientes();
        String miUsuarioUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        miTokenCliente.setUsuarioUID(miUsuarioUID);
        miTokenCliente.setToken(FirebaseInstanceId.getInstance().getToken());
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Tokens").child(miUsuarioUID).setValue(miTokenCliente);
    }

}