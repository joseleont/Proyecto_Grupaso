package pe.edu.pucp.proyecto_grupaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pe.edu.pucp.proyecto_grupaso.activities.ClienteActivity;
import pe.edu.pucp.proyecto_grupaso.activities.MainActivity;
import pe.edu.pucp.proyecto_grupaso.activities.UsuarioTIActivity;

public class IniciarSesion extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inciar_sesion);

    }


    private FirebaseAuth mAuth=FirebaseAuth.getInstance();

    //Listeneer de tipo
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    ListenerFb listenerFb = new ListenerFb();
    String tipo;

    //SE PRESIONA EL BOTON PARA INGRESAR
    public void ingresar(View view){
        //LEER LOS DATOS
        EditText editTextCorreo=findViewById(R.id.editTextCorreoIniciarSesion);
        EditText editTextContraseña=findViewById(R.id.editTextContraseñaIniciarSesion);

        String correo=editTextCorreo.getText().toString();
        String contraseña=editTextContraseña.getText().toString();

        //INGRESAR
        validarUsuario(correo,contraseña);
    }

    public void abrirCliente_Usuario(){

        if(tipo.equals("cliente")){
            startActivity(new Intent(IniciarSesion.this, ClienteActivity.class));
        }else{
            startActivity(new Intent(IniciarSesion.this, UsuarioTIActivity.class));
        }

        databaseReference.removeEventListener(listenerFb);
        finish();

    }



    public void validarUsuario(String correo,String contraseña){


       //INGRESAR CUENTA
        mAuth.signInWithEmailAndPassword(correo,contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //se ingreso el correo y contraseña correctas
                            final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                    if (currentUser.isEmailVerified()) {
                                        //SE HA VERIFICADO Y ES EL USUARIO CORRECTO
                                        databaseReference.child("Usuarios").child(currentUser.getUid()).child("tipo").addValueEventListener(listenerFb);
                                    } else {
                                        Toast.makeText(IniciarSesion.this, "Se le ha enviado un correo para verificar su cuenta", Toast.LENGTH_SHORT).show();
                                        currentUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Log.d("infoApp", "correo enviado");
                                            }
                                        });
                                    }



                        } else {

                            Log.d("error", "no se inicio sesion", task.getException());
                            Toast.makeText(IniciarSesion.this,"Datos incorrectos",Toast.LENGTH_SHORT).show();


                        }


                    }
                });


        }//fin de validar usuario

    public void cancelar(View view){
        startActivity(new Intent(IniciarSesion.this, MainActivity.class));
        finish();
    }



    //se obtiene la cantidad de deudas
    private class ListenerFb implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.getValue()!=null){

                if(!(snapshot.getValue().toString()+"").equals("null")){

                    tipo=snapshot.getValue().toString();
                    abrirCliente_Usuario();
                }
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }


}