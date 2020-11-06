package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Area extends Actor {

    public Area(int x, int y) {
        super(x, y);
        
        initArea();
    }
    
    private void initArea() {

        ImageIcon iicon = new ImageIcon(Resource.actorDir+"area.png");
        Image image = iicon.getImage();
        setImage(image);
    }
}
