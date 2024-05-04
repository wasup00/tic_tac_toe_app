package com.example.tictactoe;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;

public class PvIAActivity extends AppCompatActivity {


    TextView textView11, textView12, textView13, textView21, textView22, textView23, textView31, textView32, textView33;
    Button btn;
    String player;
    //int x,y;
    //TextView[][] textTab = {{textView11,textView12,textView13},{textView21,textView22,textView23},{textView31,textView32,textView33}};

    ArrayList<TextView> listTextView = new ArrayList<>();
    private static final int BOARD_SIZE = 3;
    private static final String EMPTY = " ";
    private static final String AI_PLAYER = "X";
    private static final String HUMAN_PLAYER = "O";
    TextView[][] arrTextView = new TextView[BOARD_SIZE][BOARD_SIZE];
    String[][] tab = new String[][]{{EMPTY, EMPTY, EMPTY},{EMPTY, EMPTY, EMPTY},{EMPTY, EMPTY, EMPTY}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pv_iaactivity);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        textView21 = findViewById(R.id.textView21);
        textView22 = findViewById(R.id.textView22);
        textView23 = findViewById(R.id.textView23);
        textView31 = findViewById(R.id.textView31);
        textView32 = findViewById(R.id.textView32);
        textView33 = findViewById(R.id.textView33);
        listTextView.add(textView11);
        listTextView.add(textView12);
        listTextView.add(textView13);
        listTextView.add(textView21);
        listTextView.add(textView22);
        listTextView.add(textView23);
        listTextView.add(textView31);
        listTextView.add(textView32);
        listTextView.add(textView33);
        arrTextView[0][0] = textView11;
        arrTextView[0][1] = textView12;
        arrTextView[0][2] = textView13;
        arrTextView[1][0] = textView21;
        arrTextView[1][1] = textView22;
        arrTextView[1][2] = textView23;
        arrTextView[2][0] = textView31;
        arrTextView[2][1] = textView32;
        arrTextView[2][2] = textView33;
        btn = findViewById(R.id.btn);

        int nightModeFlags =
                this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                for (TextView textView: listTextView){
                    textView.setBackgroundResource(R.drawable.textview_border_night);
                }
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                for (TextView textView: listTextView){
                    textView.setBackgroundResource(R.drawable.textview_border);
                }
                break;
            default:
                break;
        }
        random();

        textView11.setOnClickListener(view -> {
            if(textView11.getText().length()==0){
                textView11.setText(player);
                tab[0][0] = player;
            }
            else{
                played();
            }
            Log.e(TAG, "onClick: " + textView11.getText());
            isFinal();
            Log.e(TAG,"isFinal");
            changePlayer();
            Log.e(TAG, "changePlayer");
        });

        textView12.setOnClickListener(view -> {
            if(textView12.getText().length()==0){
                textView12.setText(player);
                tab[0][1] = player;
            }
            else{
                played();
            }
            isFinal();
            changePlayer();
        });

        textView13.setOnClickListener(view -> {
            if(textView13.getText().length()==0){
                textView13.setText(player);
                tab[0][2] = player;
            }
            else{
                played();
            }
            isFinal();
            changePlayer();
        });

        textView21.setOnClickListener(view -> {
            if(textView21.getText().length()==0){
                textView21.setText(player);
                tab[1][0] = player;
            }
            else{
                played();
            }
            isFinal();
            changePlayer();
        });

        textView22.setOnClickListener(view -> {
            if(textView22.getText().length()==0){
                textView22.setText(player);
                tab[1][1] = player;
            }
            else{
                played();
            }
            isFinal();
            changePlayer();
        });

        textView23.setOnClickListener(view -> {
            if(textView23.getText().length()==0){
                textView23.setText(player);
                tab[1][2] = player;
            }
            else{
                played();
            }
            isFinal();
            changePlayer();
        });

        textView31.setOnClickListener(view -> {
            if(textView31.getText().length()==0){
                textView31.setText(player);
                tab[2][0] = player;
            }
            else{
                played();
            }
            isFinal();
            changePlayer();
        });

        textView32.setOnClickListener(view -> {
            if(textView32.getText().length()==0){
                textView32.setText(player);
                tab[2][1] = player;
            }
            else{
                played();
            }
            isFinal();
            changePlayer();
        });

        textView33.setOnClickListener(view -> {
            if(textView33.getText().length()==0){
                textView33.setText(player);
                tab[2][2] = player;
            }
            else{
                played();
            }
            isFinal();
            changePlayer();
        });

        btn.setOnClickListener(view -> {
            random();
            for(TextView textView : listTextView){
                textView.setText("");
                textView.setEnabled(true);
            }
        });
    }

    public void iaTurn(){
        Log.e(TAG, "iaTurn: Start");
        for(TextView textView : listTextView){
            textView.setEnabled(false);
        }

        int[] result = findBestMove(tab);
        Log.e(TAG, "iaTurn: " + result[0] + " " + result[1]);
        tab[result[0]][result[1]] = AI_PLAYER;
        arrTextView[result[0]][result[1]].setText(AI_PLAYER);

        for(TextView textView : listTextView){
            textView.setEnabled(true);
        }
        Log.e(TAG, "iaTurn: End");
        changePlayer();

    }

    public void changePlayer(){
        if (player.equals(HUMAN_PLAYER)){
            player = AI_PLAYER;
            iaTurn();
        }else {
            player = HUMAN_PLAYER;
        }
    }

    public void isFinal(){
        int save = 0;

        //Verification column 1
        if(listTextView.get(0).getText() == listTextView.get(3).getText() && listTextView.get(0).getText() == listTextView.get(6).getText() && listTextView.get(6).getText().length() != 0){
            save = 1;
        }
        //Verification column 2
        else if(listTextView.get(1).getText() == listTextView.get(4).getText() && listTextView.get(1).getText() == listTextView.get(7).getText() && listTextView.get(7).getText().length() != 0){
            save = 1;
        }
        //Verification column 3
        else if(listTextView.get(2).getText() == listTextView.get(5).getText() && listTextView.get(2).getText() == listTextView.get(8).getText() && listTextView.get(8).getText().length() != 0){
            save = 1;
        }
        //Verification row 1
        else if(listTextView.get(0).getText() == listTextView.get(1).getText() && listTextView.get(0).getText() == listTextView.get(2).getText() && listTextView.get(2).getText().length() != 0){
            save = 1;
        }
        //Verification row 2
        else if(listTextView.get(3).getText() == listTextView.get(4).getText() && listTextView.get(3).getText() == listTextView.get(5).getText() && listTextView.get(5).getText().length() != 0){
            save = 1;
        }
        //Verification row 3
        else if(listTextView.get(6).getText() == listTextView.get(7).getText() && listTextView.get(6).getText() == listTextView.get(8).getText() && listTextView.get(8).getText().length() != 0){
            save = 1;
        }
        //Verification column 1
        else if(listTextView.get(0).getText() == listTextView.get(4).getText() && listTextView.get(0).getText() == listTextView.get(8).getText() && listTextView.get(8).getText().length() != 0){
            save = 1;
        }
        //Verification column 2
        else if(listTextView.get(2).getText() == listTextView.get(4).getText() && listTextView.get(2).getText() == listTextView.get(6).getText() && listTextView.get(6).getText().length() != 0){
            save =  1;
        }
        else{
            for(TextView textView : listTextView){
                if(textView.getText().length() == 0){
                    save = 0;
                    break; //Pas fini
                }
                else{
                    save = 2;
                }
            }   //Draw
        }
        if(save == 1 || save == 2){
            for (TextView textView : listTextView) {
                textView.setEnabled(false);
            }
            if(save == 1){
                showMessage("Player: " + btn.getText() + " wins",1);
            }
            if (save == 2){
                showMessage("Draw",1);
            }
            btn.setEnabled(true);
            btn.setText("Reset");
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

    public void random(){
        Random random = new Random();
        if(random.nextInt(2)==0) {
            player = HUMAN_PLAYER;
            showMessage("The player starts",1);
            //btn.setText(player);
        }
        else{
            player = AI_PLAYER;
            showMessage("The AI  starts",1);
            //btn.setText(player);
            iaTurn();
        }
        btn.setEnabled(false);
        Log.e(TAG, "random: " + player);
    }

    public int[] findBestMove(String[][] board) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].equals(EMPTY)) {
                    board[i][j] = AI_PLAYER;
                    int score = minimax(board, 0, false);
                    board[i][j] = EMPTY;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(String[][] board, int depth, boolean isMaximizingPlayer) {
        String result = checkWinner(board);
        if (!result.equals(EMPTY)) {
            return score(result, depth);
        }

        int bestScore;
        if (isMaximizingPlayer) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j].equals(EMPTY)) {
                        board[i][j] = AI_PLAYER;
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = EMPTY;
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j].equals(EMPTY)) {
                        board[i][j] = HUMAN_PLAYER;
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = EMPTY;
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
        }
        return bestScore;
    }

    private String checkWinner(String[][] board) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals(EMPTY)) {
                return board[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals(EMPTY)) {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(EMPTY)) {
            return board[0][0];
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals(EMPTY)) {
            return board[0][2];
        }

        return EMPTY;
    }

    private int score(String winner, int depth) {
        if (winner.equals(AI_PLAYER)) {
            return 10 - depth;
        } else if (winner.equals(HUMAN_PLAYER)) {
            return depth - 10;
        } else {
            return 0;
        }
    }
}