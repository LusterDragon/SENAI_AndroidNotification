package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Tela2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        //Recuperar o valor de extra
        String extra = getIntent().getStringExtra("texteExtra");
        Toast.makeText( this, extra, Toast.LENGTH_SHORT).show();
    }
}