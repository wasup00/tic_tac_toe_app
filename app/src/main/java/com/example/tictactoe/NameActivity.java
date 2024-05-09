package com.example.tictactoe;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {

    Button nameBtn;
    EditText editName1, editName2;
    String name1, name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

        editName1   =   findViewById(R.id.nomJoueur1);
        editName2   =   findViewById(R.id.nomJoueur2);
        nameBtn     =   findViewById(R.id.nameBtn);

        Bundle extras = getIntent().getExtras();
        boolean name;
        if (extras != null) {
            name = extras.getBoolean("name");
        } else {
            name = false;
        }
        editName1.setEnabled(true);
        if (name){
            editName2.setEnabled(true);
        }
        else{
            editName2.setEnabled(false);
            editName2.setHint("IA");
        }

        if (!Player.getInstance().players.isEmpty()){
            editName1.setText(Player.getInstance().players.get(0).getName());
            if (name){
                editName2.setText(Player.getInstance().players.get(1).getName());
            }
        }

        nameBtn.setOnClickListener(view -> {
            Log.e("TAG", String.valueOf(name));
            if (editName1.getText().length() == 0 && editName2.getText().length() == 0){
                Toast.makeText(this,"Please give a name for players 1 and 2",Toast.LENGTH_LONG).show();
            }
            else if (editName1.getText().length() == 0){
                Toast.makeText(this,"Please give a name for player 1",Toast.LENGTH_LONG).show();
            }
            else if (editName2.getText().length() == 0 && name){
                Toast.makeText(this,"Please give a name for player 2",Toast.LENGTH_LONG).show();
            }
            else{
                name1 = editName1.getText().toString();
                name2 = editName2.getText().toString();
                Intent intent;
                if (name){
                    intent = new Intent(this, PvPActivity.class);
                    if (Player.getInstance().players.isEmpty()){
                        Player.getInstance().playerAdd(new Player(name1,"X", 0));
                        Player.getInstance().playerAdd(new Player(name2,"O", 0));
                    }
                    else {
                        if (!Player.getInstance().players.get(0).getName().equals(name1)){
                            Player.getInstance().players.get(0).setName(name1);
                        }
                        if (!Player.getInstance().players.get(1).getName().equals(name2)){
                            Player.getInstance().players.get(1).setName(name2);
                        }
                    }
                }
                else{
                    intent = new Intent(this, PvIAActivity.class);
                    intent.putExtra("name1",name1);
                }
                startActivity(intent);
            }
        });
    }
    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Intent intent = new Intent(NameActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}