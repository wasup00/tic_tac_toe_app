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


    TextView textViewName1, textViewName2;
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

        // Initialize views
        initViews();

        // Set players' TextViews
        setPlayersTextView();

        // Set night mode
        setNightMode();

        // Set click listeners
        setClickListeners();

        // Set random player turn
        random();
    }

    private void initViews(){
        textViewName1 = findViewById(R.id.textViewName1);
        textViewName2 = findViewById(R.id.textViewName2);
        btn = findViewById(R.id.btn);
        listTextView.add(findViewById(R.id.textView11));
        listTextView.add(findViewById(R.id.textView12));
        listTextView.add(findViewById(R.id.textView13));
        listTextView.add(findViewById(R.id.textView21));
        listTextView.add(findViewById(R.id.textView22));
        listTextView.add(findViewById(R.id.textView23));
        listTextView.add(findViewById(R.id.textView31));
        listTextView.add(findViewById(R.id.textView32));
        listTextView.add(findViewById(R.id.textView33));
    }

    private void setPlayersTextView() {
        Player.getInstance().players.get(0).setTextView(textViewName1);
        Player.getInstance().players.get(1).setTextView(textViewName2);
        textViewName1.setText(String.format("%s(%s) : %s", Player.getInstance().players.get(0).getName(), Player.getInstance().players.get(0).getCharacter(), Player.getInstance().players.get(0).getNumberVictory()));
        textViewName2.setText(String.format("%s(%s) : %s", Player.getInstance().players.get(1).getName(), Player.getInstance().players.get(1).getCharacter(), Player.getInstance().players.get(1).getNumberVictory()));
    }

    private void setNightMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                setNightMode(true);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                setNightMode(false);
                break;
        }
    }

    private void setNightMode(boolean isNightMode) {
        for (TextView textView : listTextView) {
            textView.setBackgroundResource(isNightMode ? R.drawable.textview_border_night : R.drawable.textview_border);
            textView.setTextColor(isNightMode ? Color.WHITE : Color.BLACK);
        }
        textViewName1.setTextColor(isNightMode ? Color.WHITE : Color.BLACK);
        textViewName2.setTextColor(isNightMode ? Color.WHITE : Color.BLACK);
        nightModeOn = isNightMode;
    }

    private void setClickListeners() {
        for (TextView textView : listTextView) {
            textView.setOnClickListener(view -> {
                if (textView.getText().length() == 0) {
                    textView.setText(player.getCharacter());
                    isFinal();
                    changePlayer();
                } else {
                    played();
                }
            });
        }

        btn.setOnClickListener(view -> {
            random();
            for (TextView textView : listTextView) {
                textView.setText("");
                textView.setEnabled(true);
                textView.setTextColor(nightModeOn ? Color.WHITE : Color.BLACK);
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
            player = playerBool ? Player.getInstance().players.get(1) : Player.getInstance().players.get(0);
            playerBool = !playerBool;
            btn.setText(player.getName());
        }
    }

    public void random(){
        Random random = new Random();
        player = Player.getInstance().players.get(random.nextInt(2));
        playerBool = random.nextBoolean();
        showMessage(player.getName() + " starts", 0);
        btn.setText(player.getName());
        btn.setEnabled(false);
    }

    private void gagne(){
        Log.e("Activity", String.valueOf(save));
        for (TextView textView : listTextView) {
            textView.setEnabled(false);
            textView.setTextColor(nightModeOn ? Color.WHITE : Color.BLACK);
            Log.e("Activity", String.valueOf(textView.isEnabled()));
        }
        if(save < 8){
            showMessage(player.getName() + " gagne", 1);
            player.isWin();
            player.getTextView().setText(String.format("%s(%s) : %s", player.getName(), player.getCharacter(), player.getNumberVictory()));
            setWinningTextViews(save);
        }
        else {
            showMessage("Tie", 1);
        }
        btn.setEnabled(true);
        btn.setText(R.string.restart);
        for (TextView textView : listTextView) {
            Log.e("Activity", String.valueOf(textView.isEnabled()));
        }
    }

    private void setWinningTextViews(int save) {
        int start, increment, end;
        switch (save) {
            case 0:
                start = 0; increment = 1; end = 3; break;
            case 1:
                start = 3; increment = 1; end = 6; break;
            case 2:
                start = 6; increment = 1; end = 9; break;
            case 3:
                start = 0; increment = 3; end = 7; break;
            case 4:
                start = 1; increment = 3; end = 8; break;
            case 5:
                start = 2; increment = 3; end = 9; break;
            case 6:
                start = 0; increment = 4; end = 9; break;
            case 7:
                start = 2; increment = 2; end = 7; break;
            default:
                return;
        }
        for (int i = start; i < end; i += increment) {
            listTextView.get(i).setEnabled(true);
            listTextView.get(i).setTextColor(Color.BLUE);
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
            startActivity(new Intent(this, MainActivity.class));
        });
        myPopUp.setNegativeButton("No", (dialogInterface, i) -> {
        });
        myPopUp.show();
    }
}