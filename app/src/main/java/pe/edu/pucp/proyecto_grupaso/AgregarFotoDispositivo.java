package pe.edu.pucp.proyecto_grupaso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class AgregarFotoDispositivo extends AppCompatActivity {

    ImageView imageViewFoto;
    Bitmap imagen;
    Button btnSubirFoto;
    Button btnSubirCarrete;
    Button btnTomarFoto;
    Button btnCarrete;
    EditText etnombreFoto;
    StorageReference referenciaFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_foto_dispositivo);

        imageViewFoto = findViewById(R.id.imageViewFoto);
        btnSubirFoto = findViewById(R.id.btnSubirFoto);
        btnSubirCarrete = findViewById(R.id.btnSubirCarrete);
        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        btnCarrete = findViewById(R.id.btnCarrete);
        etnombreFoto = findViewById(R.id.etNombreFoto);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void permisoFoto(View view) {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1000);
        }else{
            tomarFoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tomarFoto();
            } else {
                Toast.makeText(this, "No hay permisos de cámara", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void tomarFoto(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            imagen = (Bitmap) data.getExtras().get("data");
            imageViewFoto.setImageBitmap(imagen);
            btnSubirFoto.setVisibility(View.VISIBLE);
        }
    }

    public void subirFotoFirebase(View view){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        ByteArrayOutputStream fotoJPG = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 100, fotoJPG);
        byte cadenabytes[] = fotoJPG.toByteArray();

        String nombreArchivo = etnombreFoto.getText().toString();
        referenciaFinal = storageRef.child("FotosEquipos/" + nombreArchivo);

        referenciaFinal.putBytes(cadenabytes)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Foto subida exitosamente", Toast.LENGTH_SHORT).show();
                        referenciaFinal.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String miURL = task.getResult().toString();
                                Intent i = new Intent ();
                                i.putExtra("imagenLink", miURL);
                                setResult(RESULT_OK, i);
                                finish();
                            }
                        });

                    }
                });
    }

    public void elegirCarrete(View view){
        //Seleccionar de carrete
    }


}