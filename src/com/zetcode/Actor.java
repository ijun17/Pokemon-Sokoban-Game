package com.zetcode;

import java.awt.Image;

public class Actor {

    private final int SPACE = 20;

    private int x;
    private int y;
    private Image image;

    public Actor(int x, int y) {
        
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        
        return x;
    }

    public int y() {
        
        return y;
    }

    public void setX(int x) {
        
        this.x = x;
    }

    public void setY(int y) {
        
        this.y = y;
    }
    
    public boolean isCollision(Actor actor, int dir) {
    	int [][]direction = {{SPACE, 0}, {-SPACE, 0}, {0, SPACE}, {0, -SPACE}};
    	return x() - direction[dir][0] == actor.x() && y() - direction[dir][1] == actor.y();
    }
}
