package com.zetcode;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Pokemon extends Actor{
	static String []pokemonName = {"ÇÇÄ«Ãò.png", "±¸±¸.png", "ÆÄÀÌ¸®.png", "Ä¥»öÁ¶.png", "¹ÂÃ÷.png"};
	static int []baseCatchRate = {50,50,50,95,95};
	
	int pokemonType;
	boolean isApeared=false;
	
	public Pokemon(int x, int y, int pokemonType) {
		super(x,y);
		this.pokemonType = pokemonType;
		ImageIcon iicon = new ImageIcon(Resource.pokemonDir+pokemonName[this.pokemonType]);
        Image image = iicon.getImage();
        setImage(image);
	}
	
	public boolean catchPokemon(Actor ball) {
		if(isApeared) {
			int catchPower=0;
			if(ball instanceof Ball) catchPower = (int)(Math.random()*100);
			else if(ball instanceof GoldenBall) catchPower=100;
			System.out.println(catchPower);
			if(catchPower>baseCatchRate[pokemonType]) {
				removePokemon();
				return true;
			}
		}
		ball.setImage(null);
		return false;
	}
	
	public Image getImage() {
        if(isApeared)return super.getImage();
        return null;
    }
	
	public void apearPokemon(Area area) {
		this.setX(area.x());
		this.setY(area.y());
		this.isApeared=true;
	}
	
	public void removePokemon() {
		this.isApeared=false;
	}
}
