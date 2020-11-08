package com.zetcode;


import java.awt.EventQueue;
public class Sokoban{

    public Sokoban() {
    	startGame();
    }
	
	public void startGame() {
		Sound.Loop(Resource.musicDir+"poketmon.wav");
		ScreenManager sm = new ScreenManager();
		ScreenManager.setPanel("menuscreen");
	}
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        	Sokoban sokoban = new Sokoban();
        });
    }
}