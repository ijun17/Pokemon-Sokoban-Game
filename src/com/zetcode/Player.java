package com.zetcode;

//import java.util.LinkedList;

import java.awt.Image;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Player extends Actor {
	private int playerImageNum=1;

    public Player(int x, int y) {
        super(x, y);
        initPlayer();
    }

    private void initPlayer() {
        ImageIcon iicon = new ImageIcon(Resource.actorDir + "sokoban"+playerImageNum+".png");
        Image image = iicon.getImage();
        setImage(image);
        //
        
        //
    }
    
    public void setPlayerImage(int num) {
    	playerImageNum += num;
    	if( playerImageNum > 5)
    		playerImageNum = 1;
    }
    
    public String getPlayerImageName() {
    	return "sokoban"+playerImageNum+".png";
    }
    
    public void playerRestart() {
    	initPlayer();
    }


    public void move(int x, int y) {
    	
    	
        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
        
        if(x < 0) {
            ImageIcon iicon = new ImageIcon(Resource.actorDir + "sokoban"+playerImageNum+"_left.png");
             Image image = iicon.getImage();
             setImage(image);
         }
         if(x > 0) {
            ImageIcon iicon = new ImageIcon(Resource.actorDir + "sokoban"+playerImageNum+"_right.png");
             Image image = iicon.getImage();
             setImage(image);
         }
         if(y < 0) {
            ImageIcon iicon = new ImageIcon(Resource.actorDir + "sokoban"+playerImageNum+"_back.png");
             Image image = iicon.getImage();
             setImage(image);
         }
         if(y > 0) {
            ImageIcon iicon = new ImageIcon(Resource.actorDir + "sokoban"+playerImageNum+".png");
             Image image = iicon.getImage();
             setImage(image);
         }

    }
}
