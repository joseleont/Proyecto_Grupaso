package pe.edu.pucp.proyecto_grupaso.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.edu.pucp.proyecto_grupaso.IniciarSesion;
import pe.edu.pucp.proyecto_grupaso.R;
import pe.edu.pucp.proyecto_grupaso.RegistroNuevoCliente;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {

               irActivityUsuarioClientre(currentUser);

        }


    }

    public void irActivityUsuarioClientre(FirebaseUser currentUser){
        if((currentUser.getEmail().equals("jose.leont@pucp.pe"))||(currentUser.getEmail().equals("a20151164@pucp.pe"))){
            startActivity(new Intent(MainActivity.this, ClienteActivity.class));

        }else{
            startActivity(new Intent(MainActivity.this, UsuarioTIActivity.class));

        }
        finish();
    }

    public void registrar(View view){

        startActivity(new Intent(MainActivity.this, RegistroNuevoCliente.class));

    }

    public void ingresar(View view){
        startActivity(new Intent(MainActivity.this, IniciarSesion.class));
        finish();
    }


}