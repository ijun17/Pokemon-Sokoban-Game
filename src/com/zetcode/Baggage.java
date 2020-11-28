package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Baggage extends Actor {
	int bagType;
    public Baggage(int x, int y, int bt) {
        super(x, y);
        bagType=bt;
        initBaggage();
    }
    
    private void initBaggage() {
        String fileName[]= {"pokeball3.png", "masterball3.png", "box.png"};
        ImageIcon iicon = new ImageIcon(Resource.actorDir+fileName[bagType]);
        Image image = iicon.getImage();
        setImage(image);
    }

    public void move(int dir) {
    	int SPACE = 20;
    	int [][]vectors = {{SPACE, 0}, {-SPACE, 0}, {0, SPACE}, {0, -SPACE}};
    	
        int dx = x() - vectors[dir][0];
        int dy = y() - vectors[dir][1];
        
        setX(dx);
        setY(dy);
    }
    
    public int getBagType() {
    	return bagType;
    }
}
