package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button pvpBtn, pvIaBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pvpBtn  =   findViewById(R.id.pvpBtn);
        pvIaBtn =   findViewById(R.id.pviaBtn);

        pvpBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,NameActivity.class);
            intent.putExtra("name", true);
            startActivity(intent);
        });

        pvIaBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,NameActivity.class);
            intent.putExtra("name", false);
            startActivity(intent);
        });
    }
}