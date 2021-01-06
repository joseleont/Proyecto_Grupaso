package pe.edu.pucp.proyecto_grupaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pe.edu.pucp.proyecto_grupaso.models.Usuario;


public class RegistroNuevoCliente extends AppCompatActivity {

    String textCodigo;
    String textCorreo;
    String textoSpiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nuevo_cliente);

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerRol);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Roles, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String textSpinner = adapterView.getItemAtPosition(position).toString();
                textoSpiner=textSpinner;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                String textSpinner ="Alumno";
                textoSpiner=textSpinner;
            }
        });



    }

FirebaseAuth firebaseAuth= FirebaseAuth.getInstance(); //obtneer referencia
    FirebaseUser user; //varibale con el usuario




    public void crearCuenta(View view){



        int error=0;

        EditText editTextCodigo=findViewById(R.id.editTextCodigoRegistroNuevoCliente);
        EditText editTextCorreo=findViewById(R.id.editTextCorreoRegistroNuevoCliente);
        EditText editTextContraseña=findViewById(R.id.editTextContraseñaRegistroNuevoCliente);

        textCodigo=editTextCodigo.getText().toString();
        textCorreo=editTextCorreo.getText().toString()+"";
        String textContraseña=editTextContraseña.getText().toString();

            Log.d("InfoAppM",textCodigo);
            Log.d("InfoAppM",textCorreo+"a");
            Log.d("InfoAppM",textContraseña+"b");

        if((textCodigo+"").equals("null")){
            error=1;
            editTextCodigo.setError("Ingrese un codigo");
        }else{
            if(textCodigo.length()!=8){
                Log.d("InfoApp3",""+textCodigo.length());
                error=1;
                editTextCodigo.setError("El codigo debe ser de 8 digitos");
            }
        }

        if((textCorreo+"").equals("")){
            error=1;
            editTextCorreo.setError("Ingrese un correo");
        }


        if((textContraseña+"").equals("")){
            error=1;
            editTextContraseña.setError("Ingrese una contraseña");
        }else{
            if(textContraseña.length()<6){

                error=1;
                editTextContraseña.setError("Contraseña muy debil,minimo 6 caracteres");
            }
        }



        if(error==0){
            firebaseAuth.createUserWithEmailAndPassword(textCorreo, textContraseña)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("cuenta", "createUserWithEmail:success");
                                user = firebaseAuth.getCurrentUser();
                                subirInfoUsuario(user.getUid());

                                enviarCorreoConfirmacion();
                                finish();
                            }


                        }
                    });


        } //fin error==0

    } //fin del metodo apra crear usuario

    public void subirInfoUsuario(String uid){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        Usuario usuario  = new Usuario();

        usuario.setCodigo(textCodigo);
        usuario.setCorreo(textCorreo);
        usuario.setTipo("cliente");
        usuario.setRol(textoSpiner);
        usuario.setUid(uid);

        databaseReference.child("Usuarios").child(uid).setValue(usuario);
    }

    public void enviarCorreoConfirmacion(){

        user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!user.isEmailVerified()) {
                    Toast.makeText(RegistroNuevoCliente.this, "Se le ha enviado un correo para verificar su cuenta", Toast.LENGTH_SHORT).show();
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("infoApp", "correo enviado");
                        }
                    });
                }
            }
        });
    }

    public void cancelar(View view){
        finish();
    }


}