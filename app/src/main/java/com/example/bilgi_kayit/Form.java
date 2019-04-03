package com.example.bilgi_kayit;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Form extends AppCompatActivity {

    private EditText name;
    private EditText surname;
    private EditText tcno;
    private Button kaydet;
    private ImageView resim;
    private Button yukle;
    private Button clear;
    private Uri selectedImage;
    private EditText telno;
    private EditText email;

    private int STORAGE_PERMISSION_CODE = 1;
    private static final int IMAGE_PICK = 1;

    public void set_find() {
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        tcno = findViewById(R.id.tcno);
        kaydet = findViewById(R.id.kaydet);
        resim = findViewById(R.id.imageView);
        yukle = findViewById(R.id.resimYukle);
        telno = findViewById(R.id.telno);
        clear = findViewById(R.id.clear);
        email = findViewById(R.id.email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        set_find();
        if (savedInstanceState != null)
        {
            selectedImage= savedInstanceState.getParcelable("outputFileUri");
            resim.setImageURI(selectedImage);
        }

        this.kaydet.setOnClickListener(new Save_form());
        this.yukle.setOnClickListener(new ImagePickListener());
        this.clear.setOnClickListener(new Clear_Form());



    }


    class Save_form implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (selectedImage == null) {
                Toast.makeText(Form.this, "İlerlemek için Fotoğraf yükleyin!", Toast.LENGTH_SHORT).show();

            } else {
                Intent intent = new Intent(Form.this, formDisplay.class);
                intent.putExtra("isim", name.getText().toString());
                intent.putExtra("soyad", surname.getText().toString());
                intent.putExtra("tcno", tcno.getText().toString());
                intent.putExtra("tel", telno.getText().toString());
                intent.putExtra("imageUri", selectedImage.toString());
                intent.putExtra("email", email.getText().toString());
                startActivity(intent);
            }

        }
    }


    class ImagePickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (ContextCompat.checkSelfPermission(Form.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                Intent intent1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent1.setType("image/*");
                startActivityForResult(Intent.createChooser(intent1, "Resim Yükle"), IMAGE_PICK);
            } else {
                requestStoragePermission();

            }

        }
    }


    class Clear_Form implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            name.setText("");
            surname.setText("");
            telno.setText("");
            tcno.setText("");
            email.setText("");
            resim.setImageResource(R.drawable.ic_launcher_foreground);
            selectedImage=null;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == Activity.RESULT_OK && data!=null) {
            switch (requestCode) {
                case IMAGE_PICK:
                    selectedImage = data.getData();
                    resim.setImageURI(selectedImage);
                    break;
                default:
                    break;
            }
        }
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putParcelable("outputFileUri", selectedImage);
    }

    private void requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){

            new AlertDialog.Builder(this).setTitle("İzin gerekli").setMessage("Fotoğraf yüklenmesi için bu izin gerekli").setPositiveButton("onayla", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(Form.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

                }
            }).setNegativeButton("iptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();

        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"İzin onaylandı",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"İzin reddedildi",Toast.LENGTH_LONG).show();

            }
        }
    }


}
