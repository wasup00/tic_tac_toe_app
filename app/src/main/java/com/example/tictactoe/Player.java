package com.example.tictactoe;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Player {
    private String name;
    private String character;
    private int numberVictory;
    private TextView textView;
    public ArrayList<Player> players= new ArrayList<>();
    private static final Player instance = new Player();

    private Player(){}

    public static Player getInstance() {
        return instance;
    }
    public Player(String name, String character, int numberVictory) {
        this.name = name;
        this.character = character;
        this.numberVictory = numberVictory;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public int getNumberVictory() {
        return numberVictory;
    }

    public void playerAdd(Player player){
        if (players.size() < 2){
            players.add(player);
        }else {
            Log.e("Player","Too much player");
        }
        Log.e("PlayerAdd", player.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    /*public void setCharacter(String character) {
        this.character = character;
    }*/

    public void isWin(){
        numberVictory++;
    }

    @Override
    public String toString() {
        return "Player{" +
                "players=" + players.toString() +
                '}';
    }
}
