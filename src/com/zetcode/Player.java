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
	private static final String []playerImageDir = {"_left", "_right", "_back", ""};
	private int playerImageNum=1;
	private int playerDir=3;

    public Player(int x, int y) {
        super(x, y);
        initPlayer();
    }

    private void initPlayer() {
        ImageIcon iicon = new ImageIcon(Resource.actorDir + "sokoban"+playerImageNum+playerImageDir[playerDir]+".png");
        Image image = iicon.getImage();
        setImage(image);
    }
    
    public void changePlayerImage() {
    	playerImageNum ++;
    	if( playerImageNum > 5)
    		playerImageNum = 1;
    	initPlayer();
    }
    
    public void setPlayerDir(int dir) {
    	//dir 0:왼쪽, 1:오른쪽 2:위쪽 3:아래쪽
    	playerDir = dir;
    	initPlayer();
    }

    public void move(int dir) {
    	int SPACE = 20;
    	int [][]vectors = {{SPACE, 0}, {-SPACE, 0}, {0, SPACE}, {0, -SPACE}};
    	
        int dx = x() - vectors[dir][0];
        int dy = y() - vectors[dir][1];
        
        setX(dx);
        setY(dy);
        
        setPlayerDir(dir);
    }
}
