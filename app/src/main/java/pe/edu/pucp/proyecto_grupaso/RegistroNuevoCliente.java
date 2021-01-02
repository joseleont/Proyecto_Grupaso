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

FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();





    public void crearCuenta(View view){



        int error=0;

        EditText editTextCodigo=findViewById(R.id.editTextCodigoRegistroNuevoCliente);
        EditText editTextCorreo=findViewById(R.id.editTextCorreoRegistroNuevoCliente);
        EditText editTextContraseña=findViewById(R.id.editTextContraseñaRegistroNuevoCliente);

        textCodigo=editTextCodigo.getText().toString();
        textCorreo=editTextCorreo.getText().toString()+"";
        String textContraseña=editTextContraseña.getText().toString();


        if((textCodigo+"").equals("null")){
            error=1;
            editTextCodigo.setError("Ingrese un codigo");
        }else{
            try {
                long codigo= Long.parseLong(textCodigo);

                   // error=1;
                   // editTextCodigo.setError("Codigo incorrecto");

            }catch(NumberFormatException e){
                error=1;
                editTextCodigo.setError("Codigo incorrecto");
                e.printStackTrace();
            }

        }

        if(textCorreo.equals("null")){

            error=1;
            editTextCorreo.setError("Ingrese un correo");
        }
        if(textContraseña.equals("null")){
            error=1;
            editTextContraseña.setError("Ingrese una contraseña");
        }

        if(error==0){
            firebaseAuth.createUserWithEmailAndPassword(textCorreo, textContraseña)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("cuenta", "createUserWithEmail:success");
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                subirInfoUsuario(user.getUid());
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

        databaseReference.child("Usuarios").child(uid).setValue(usuario);
    }

    public void cancelar(View view){
        finish();
    }


}