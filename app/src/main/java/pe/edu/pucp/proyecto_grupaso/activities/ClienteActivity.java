package pe.edu.pucp.proyecto_grupaso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.edu.pucp.proyecto_grupaso.R;
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
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.myClientFrameLayout, ClientListDispositivosFragment.newInstance()).commit();
        // fm.beginTransaction().add(clf,)

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuSalir:
                AuthUI instance = AuthUI.getInstance();
                instance.signOut(this).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(ClienteActivity.this,MainActivity.class));
                        finish();
                    }
                });

                return true;

        }

        return super.onOptionsItemSelected(item);
    }




}