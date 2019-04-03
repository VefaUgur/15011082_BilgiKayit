package com.example.bilgi_kayit;

import android.Manifest;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class formDisplay extends AppCompatActivity {

    private int CALL_PERMISSION_CODE = 1;
    private static final int IMAGE_PICK = 1;
    private TextView name;
    private TextView surname;
    private TextView tc;
    private TextView email;
    private ImageView resim1;
    private TextView telno;
    private ImageButton arama;
    private RecyclerView recycleView;
    private Button dersListele;

    private CourseAdapter adapter;
    private List<Course> courseList;


    public void FindId() {
        recycleView = findViewById(R.id.recyclerView);
        recycleView.setHasFixedSize(true);
        name = findViewById(R.id.nameD);
        surname = findViewById(R.id.surnameD);
        tc = findViewById(R.id.tcnoD);
        email = findViewById(R.id.emailD);
        resim1 = findViewById(R.id.resimD);
        telno = findViewById(R.id.telnoD);
        arama = findViewById(R.id.ara);
        dersListele = findViewById(R.id.dersListele);
    }

    public void addItem() {
        courseList.add(new Course("Nesneye D.K", "BA","85","BB"));
        courseList.add(new Course("BBG", "BB","120","CC"));
        courseList.add(new Course("Sayısal Analiz", "AA","65","BA"));
        courseList.add(new Course("Assembly", "CC","77","BB"));
        courseList.add(new Course("Fizik", "DC","45","DC"));
        courseList.add(new Course("Matematik I", "BB","80","CB"));
        courseList.add(new Course("Fizik II", "AA","70","BB"));
        courseList.add(new Course("Bilgisayar Donanımı", "BA","45","BA"));
        courseList.add(new Course("Sistem Analizi", "BB","60","CB"));
        courseList.add(new Course("Veri Yapıları", "CB","100","CC"));
        courseList.add(new Course("Ara Proje", "BA","150","BB"));
        courseList.add(new Course("Hukuk", "AA","80","CB"));
        courseList.add(new Course("Matematik II", "BA","90","CC"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_display);

        courseList =new ArrayList<>();
        addItem();
        FindId();
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter =new CourseAdapter(this,courseList);
        recycleView.setAdapter(adapter);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("isim"));
        surname.setText(intent.getStringExtra("soyad"));
        tc.setText(intent.getStringExtra("tcno"));
        email.setText(intent.getStringExtra("email"));
        telno.setText(intent.getStringExtra("tel"));
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            Uri myUri = Uri.parse(extras.getString("imageUri"));
            resim1.setImageURI(myUri);
        }

        arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(formDisplay.this,
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String phoneNo = telno.getText().toString();
                    if(!TextUtils.isEmpty(phoneNo)) {
                        String dial = "tel:" + phoneNo;
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }else {
                        Toast.makeText(formDisplay.this, "Telefon no bulunmamaktadır.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    requestStoragePermission();

                }
            }

        });


    }
    private void requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){

            new AlertDialog.Builder(this).setTitle("İzin gerekli").setMessage("Arama yapılabilmesi için bu izin gerekli").setPositiveButton("tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(formDisplay.this,new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION_CODE);

                }
            }).setNegativeButton("iptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();

        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CALL_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"İzin onaylandı",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"İzin reddedildi",Toast.LENGTH_LONG).show();

            }
        }
    }


    public void DersListele(View view) {
        recycleView.setVisibility(View.VISIBLE);
    }

    public void MailSend(View view) {
        if(email.getText().toString().equals("")) {
            Toast.makeText(formDisplay.this, "E-mail adresi bulunmamaktadır.", Toast.LENGTH_SHORT).show();
        }else {
            sendMail();
        }

    }
    private void sendMail(){
        String receiver = email.getText().toString();
        Intent intentS = new Intent(Intent.ACTION_SEND);
        intentS.putExtra(Intent.EXTRA_EMAIL,new String[]{receiver});
        intentS.setType("message/rfc822");
        startActivity(Intent.createChooser(intentS,"Uygulamayı seçin"));

    }


}
