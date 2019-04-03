package com.example.bilgi_kayit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user_name;
    private EditText passoword;
    private Button giris;

    public void find(){
        user_name=findViewById(R.id.usertxt);
        passoword= findViewById(R.id.sifretxt);
        giris = findViewById(R.id.button);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find();
    }

    public void girisYap(View view) {
        if (user_name.getText().toString().equals("admin")&&(passoword.getText().toString().equals("password"))){
            Intent intent = new Intent(MainActivity.this, Form.class);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Yanlış kullanıcı adı ve ya şifre girildi.", Toast.LENGTH_SHORT).show();
        }
    }
}
