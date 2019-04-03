package com.example.bilgi_kayit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseDisplay extends AppCompatActivity {

    private TextView baslik,sayi,ortalama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_display);

        baslik = findViewById(R.id.baslik);
        sayi = findViewById(R.id.OgrSayi);
        ortalama = findViewById(R.id.OrtNot);


        Intent intent = getIntent();
        baslik.setText(intent.getStringExtra("course_name"));
        sayi.setText(intent.getStringExtra("course_size"));
        ortalama.setText(intent.getStringExtra("course_avg"));
    }
}
