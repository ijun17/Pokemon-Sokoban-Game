package com.zetcode;


import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Sokoban{

    public Sokoban() {
    	startGame();
    }
	
	public void startGame() {
		Sound.Loop("src/resources/poketmon.wav");
		ScreenManager sm = new ScreenManager();
		ScreenManager.setPanel("menuscreen");
	}
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        	Sokoban sokoban = new Sokoban();
        });
    }
}