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

    public void move(int x, int y) {
        
        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
    
    public int getBagType() {
    	return bagType;
    }
}
