package com.example.tictactoe;


import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;

public class PvPActivity extends AppCompatActivity {


    TextView textViewName1, textViewName2, textView11, textView12, textView13, textView21, textView22, textView23, textView31, textView32, textView33;
    Button btn;
    Boolean playerBool, nightModeOn;
    ArrayList<TextView> listTextView = new ArrayList<>();
    Player player;
    // String name1, name2;
    int save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
        setContentView(R.layout.activity_pv_pactivity);
        textViewName1 = findViewById(R.id.textViewName1);
        textViewName2 = findViewById(R.id.textViewName2);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        textView21 = findViewById(R.id.textView21);
        textView22 = findViewById(R.id.textView22);
        textView23 = findViewById(R.id.textView23);
        textView31 = findViewById(R.id.textView31);
        textView32 = findViewById(R.id.textView32);
        textView33 = findViewById(R.id.textView33);
        btn = findViewById(R.id.btn);
        listTextView.add(textView11);
        listTextView.add(textView12);
        listTextView.add(textView13);
        listTextView.add(textView21);
        listTextView.add(textView22);
        listTextView.add(textView23);
        listTextView.add(textView31);
        listTextView.add(textView32);
        listTextView.add(textView33);

        Player.getInstance().players.get(0).setTextView(textViewName1);
        Player.getInstance().players.get(1).setTextView(textViewName2);
        Player.getInstance().players.get(0).getTextView().setText(String.format("%s(%s) : %s", Player.getInstance().players.get(0).getName(), Player.getInstance().players.get(0).getCharacter(), Player.getInstance().players.get(0).getNumberVictory()));
        Player.getInstance().players.get(1).getTextView().setText(String.format("%s(%s) : %s", Player.getInstance().players.get(1).getName(), Player.getInstance().players.get(1).getCharacter(), Player.getInstance().players.get(1).getNumberVictory()));

        int nightModeFlags =
                this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                for (TextView textView: listTextView){
                    textView.setBackgroundResource(R.drawable.textview_border_night);
                    textView.setTextColor(Color.WHITE);
                }
                textViewName1.setTextColor(Color.WHITE);
                textViewName2.setTextColor(Color.WHITE);
                nightModeOn = true;
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                for (TextView textView: listTextView){
                    textView.setBackgroundResource(R.drawable.textview_border);
                    textView.setTextColor(Color.BLACK);
                }
                textViewName1.setTextColor(Color.BLACK);
                textViewName2.setTextColor(Color.BLACK);
                nightModeOn = false;
                break;
        }
        random();

        textView11.setOnClickListener(view -> {
            if(textView11.getText().length()==0){
                textView11.setText(player.getCharacter());
                isFinal();
                changePlayer();
            }
            else{
                played();
            }
        });

        textView12.setOnClickListener(view -> {
            if(textView12.getText().length()==0){
                textView12.setText(player.getCharacter());
                isFinal();
                changePlayer();
            }
            else{
                played();
            }
        });

        textView13.setOnClickListener(view -> {
            if(textView13.getText().length()==0){
                textView13.setText(player.getCharacter());
                isFinal();
                changePlayer();

            }
            else{
                played();
            }
        });

        textView21.setOnClickListener(view -> {
            if(textView21.getText().length()==0){
                textView21.setText(player.getCharacter());
                isFinal();
                changePlayer();

            }
            else{
                played();
            }
        });

        textView22.setOnClickListener(view -> {
            if(textView22.getText().length()==0){
                textView22.setText(player.getCharacter());
                isFinal();
                changePlayer();

            }
            else{
                played();
            }
        });

        textView23.setOnClickListener(view -> {
            if(textView23.getText().length()==0){
                textView23.setText(player.getCharacter());
                isFinal();
                changePlayer();

            }
            else{
                played();
            }
        });

        textView31.setOnClickListener(view -> {
            if(textView31.getText().length()==0){
                textView31.setText(player.getCharacter());
                isFinal();
                changePlayer();
            }
            else{
                played();
            }
        });

        textView32.setOnClickListener(view -> {
            if(textView32.getText().length()==0){
                textView32.setText(player.getCharacter());
                isFinal();
                changePlayer();
            }
            else{
                played();
            }
        });

        textView33.setOnClickListener(view -> {
            if(textView33.getText().length()==0){
                textView33.setText(player.getCharacter());
                isFinal();
                changePlayer();
            }
            else{
                played();
            }
        });

        btn.setOnClickListener(view -> {
            random();
            for(TextView textView : listTextView){
                textView.setText("");
                textView.setEnabled(true);
                if (nightModeOn){
                    textView.setTextColor(Color.WHITE);
                }else {
                    textView.setTextColor(Color.BLACK);
                }
            }
        });
    }
    public void isFinal(){
        save = 10;
        int k, l, col1 = 0, col2 = 0, col3 = 0, l1 = 0, l2 = 0, l3 = 0, d1 = 0, d2 = 0;
        ArrayList<Integer> list = new ArrayList<>();


        //Verification columns
        for (int i = 0; i <= 6 ; i+=3) {
            k = i + 1;
            l = i + 2;
            if (listTextView.get(i).getText().equals(player.getCharacter())){
                col1++;
            }
            if (listTextView.get(k).getText().equals(player.getCharacter())){
                col2++;
            }
            if (listTextView.get(l).getText().equals(player.getCharacter())){
                col3++;
            }
        }
        //Verification rows
        for (int i = 0; i <= 2 ; i++) {
            k = i + 3;
            l = i + 6;
            if (listTextView.get(i).getText().equals(player.getCharacter())){
                l1++;
            }
            if (listTextView.get(k).getText().equals(player.getCharacter())){
                l2++;
            }
            if (listTextView.get(l).getText().equals(player.getCharacter())) {
                l3++;
            }
        }
        //Verification diagonal 1
        for (int i = 0; i <= 8; i+=4) {
            if (listTextView.get(i).getText().equals(player.getCharacter())){
                d1++;
            }
        }
        for (int i = 2; i <= 6; i+=2) {
            if (listTextView.get(i).getText().equals(player.getCharacter())){
                d2++;
            }
        }
        list.add(l1);
        list.add(l2);
        list.add(l3);
        list.add(col1);
        list.add(col2);
        list.add(col3);
        list.add(d1);
        list.add(d2);
        for(TextView textView : listTextView){
            if(textView.getText().length() == 0){
                save = 9;
                break; //Not done
            }
            else{
                save = 8;
            }
        }
        for (int i = 0; i < list.size(); i++){
            if (list.get(i) == 3) {
                save = i;
                break;
            }
        }
        Log.e("Activity", list.toString());
        if (save < 9){
            gagne();
        }
    }

    public void played(){
        showMessage("Place already played",0);
    }

    public void showMessage(String str, int num){
        if (num == 0){
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,str,Toast.LENGTH_LONG).show();
        }
    }

    public void changePlayer(){
        if (!btn.isEnabled()) {
            if (playerBool) {
                player = Player.getInstance().players.get(1);
                playerBool = false;
            } else {
                player = Player.getInstance().players.get(0);
                playerBool = true;
            }
            btn.setText(player.getName());
        }
    }

    public void random(){
        Random random = new Random();
        if(random.nextInt(2)==0) {
            player = Player.getInstance().players.get(0);
            playerBool = true;
        }
        else{
            player = Player.getInstance().players.get(1);
            playerBool = false;
        }
        showMessage(player.getName() + " starts",0);
        btn.setText(player.getName());
        btn.setEnabled(false);
    }

    public void gagne(){
        Log.e("Activity", String.valueOf(save));
        for (TextView textView : listTextView) {
            textView.setEnabled(false);
            if (nightModeOn){
                textView.setTextColor(Color.WHITE);
            }else {
                textView.setTextColor(Color.BLACK);
            }
            Log.e("Activity", String.valueOf(textView.isEnabled()));
        }
        if(save < 8){
            showMessage(player.getName() + " gagne",1);
            player.isWin();
            player.getTextView().setText(String.format("%s(%s) : %s", player.getName(), player.getCharacter(), player.getNumberVictory()));
            switch (save){
                case 0:
                    for (int i = 0; i < 3; i++) {
                        listTextView.get(i).setEnabled(true);
                        listTextView.get(i).setTextColor(Color.BLUE);
                    }
                    break;
                case 1:
                    for (int i = 3; i < 6; i++) {
                        listTextView.get(i).setEnabled(true);
                        listTextView.get(i).setTextColor(Color.BLUE);

                    }
                    break;
                case 2:
                    for (int i = 6; i < 9; i++) {
                        listTextView.get(i).setEnabled(true);
                        listTextView.get(i).setTextColor(Color.BLUE);
                    }
                    break;
                case 3:
                    for (int i = 0; i < 7; i+=3) {
                        listTextView.get(i).setEnabled(true);
                        listTextView.get(i).setTextColor(Color.BLUE);
                    }
                    break;
                case 4:
                    for (int i = 1; i < 8; i+=3) {
                        listTextView.get(i).setEnabled(true);
                        listTextView.get(i).setTextColor(Color.BLUE);
                    }
                    break;
                case 5:
                    for (int i = 2; i < 9; i+=3) {
                        listTextView.get(i).setEnabled(true);
                        listTextView.get(i).setTextColor(Color.BLUE);
                    }
                    break;
                case 6:
                    for (int i = 0; i < 9; i+=4) {
                        listTextView.get(i).setEnabled(true);
                        listTextView.get(i).setTextColor(Color.BLUE);
                    }
                    break;
                case 7:
                    for (int i = 2; i < 7; i+=2) {
                        listTextView.get(i).setEnabled(true);
                        listTextView.get(i).setTextColor(Color.BLUE);
                    }
                    break;
                default:
                    break;
            }
        }
        else {
            showMessage("Tie",1);
        }
        btn.setEnabled(true);
        btn.setText(R.string.recommencer);
        for (TextView textView : listTextView) {
            Log.e("Activity", String.valueOf(textView.isEnabled()));
        }
    }

    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            showDialog();
        }
    };

    private void showDialog(){
        AlertDialog.Builder myPopUp = new AlertDialog.Builder(this);
        myPopUp.setTitle("Quit?");
        myPopUp.setMessage("Do you want to leave the game? All data will be lost.");
        myPopUp.setPositiveButton("Yes", (dialogInterface, i) -> {
            if (Player.getInstance().players.get(0).getNumberVictory() > Player.getInstance().players.get(1).getNumberVictory()){
                showMessage("The player " + Player.getInstance().players.get(0).getName() + " won: " + Player.getInstance().players.get(0).getNumberVictory() + " - " + Player.getInstance().players.get(1).getNumberVictory(),1);
            }
            else if (Player.getInstance().players.get(0).getNumberVictory() < Player.getInstance().players.get(1).getNumberVictory()){
                showMessage("The player " + Player.getInstance().players.get(1).getName() + " won: " + Player.getInstance().players.get(1).getNumberVictory() + " - " + Player.getInstance().players.get(0).getNumberVictory(),1);
            }
            else{
                showMessage("Tie: " + Player.getInstance().players.get(0).getNumberVictory() + " - " + Player.getInstance().players.get(1).getNumberVictory(),1);
            }
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
        });
        myPopUp.setNegativeButton("No", (dialogInterface, i) -> {
        });
        myPopUp.show();
    }
}