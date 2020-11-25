package com.zetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.KeyEvent;

public class ActorManager {
	final public int SPACE = 20;
	final public int [][]vectors = {{SPACE, 0}, {-SPACE, 0}, {0, SPACE}, {0, -SPACE}};


	private ArrayList<Wall> walls;
	private ArrayList<Baggage> baggs;
	private ArrayList<Area> areas;
	private ArrayList<Baggage> balls;
	private ArrayList<Player> players;
	private Pokemon pokemon;
	
	private ArrayList<Baggage> recordMovedBaggage = new ArrayList<Baggage>();
	private boolean caughtPokemon=false;

	public ActorManager() {
		walls = new ArrayList<>();
		baggs = new ArrayList<>();
		areas = new ArrayList<>();
		balls = new ArrayList<>();
		players = new ArrayList<>();
		pokemon = null; //피카츄
	}

	public void addActor(Actor actor) {
		if(actor instanceof Area) {
			areas.add((Area)actor);
		}else if(actor instanceof Ball) {
			baggs.add((Baggage)actor);
			balls.add((Ball)actor);
		}else if(actor instanceof Box) {
			baggs.add((Baggage)actor);
		}else if(actor instanceof GoldenBall) {
			baggs.add((Baggage)actor);
			balls.add((GoldenBall)actor);
		}else if(actor instanceof Player) {
			players.add((Player)actor);
		}else if(actor instanceof Wall) {
			walls.add((Wall)actor);
		}else if(actor instanceof Pokemon) {
			pokemon=(Pokemon)actor;
		}
	}

	public boolean movePlayer(int moveKeyNum){
		//System.out.println("AM : movePlayer : moveKeyNum = "+moveKeyNum);
		
		int dir = moveKeyNum%4; // 0:왼쪽, 1:오른쪽 2:위쪽 3:아래쪽
		int playerNum = moveKeyNum/4; //키 인텍스가 0~3이면 플레이어0, 키 인텍스가 4~7이면 플레이어1
		
		if(checkWallCollision(players.get(playerNum), dir))return false;
		if(checkBagCollision(players.get(playerNum), dir))return false;

		Player player = players.get(moveKeyNum/4);
		player.move(-vectors[dir][0], -vectors[dir][1]);
		return true;
	}

	private boolean checkWallCollision(Actor actor, int dir) {
		for(int i = 0; i<walls.size(); i++) {
			Wall wall = walls.get(i);
			if(actor.isCollision(wall, dir))return true;
		}
		return false;
	}

	private boolean checkBagCollision(Player player, int dir) {
		Player otherPlayer;
		if(players.size()==1)otherPlayer=null;
		else if(player.equals(players.get(0)))otherPlayer = players.get(1);
		else otherPlayer = players.get(0);

		for(int i=0; i<baggs.size(); i++) {
			Baggage bag = baggs.get(i);
			if(player.isCollision(bag, dir)) {
				for(int j=0; j<baggs.size(); j++) {
					if(players.size()==2&&otherPlayer.isCollision(bag, dir))return true;
					Baggage bag2 = baggs.get(j);
					if(!bag.equals(bag2)) { 
						if(bag.isCollision(bag2, dir))return true;
					}
					if(checkWallCollision(bag, dir))return true;
				}
				bag.move(-vectors[dir][0], -vectors[dir][1]);
				recordMovedBaggage.add(0, bag);
				return false;
			}
		}
		recordMovedBaggage.add(0, null);
		return false;
	}
	
	public int getEmptyArea() {
		int nOfAreas = areas.size();
		int nOfEmptyAreas = nOfAreas;
		Area emptyArea=null;
		for (int i = 0; i < nOfAreas; i++) {
			Area area =  areas.get(i);
			for (int j = 0; j < nOfAreas; j++) {
				Baggage bag = balls.get(j);
				if (bag.x() == area.x() && bag.y() == area.y()) {
					nOfEmptyAreas --;
					j=nOfAreas;//탈출
				}else if(j == nOfAreas-1)emptyArea=area;
			}
		}
		if(nOfEmptyAreas==0) {
			caughtPokemon = pokemon.catchPokemon(getLastMoveBag());
		}else if(nOfEmptyAreas==1) {
			pokemon.apearPokemon(emptyArea);
		}else pokemon.removePokemon();
		return nOfEmptyAreas;
	}
	
	public void undo(int moveKeyNum) {
		int reversDir[] = {1,0,3,2};
		int dir = moveKeyNum%4;
		Player player = players.get(moveKeyNum/4);
		if(recordMovedBaggage.get(0)!=null) {
			recordMovedBaggage.get(0).move(-vectors[reversDir[dir]][0],  
					-vectors[reversDir[dir]][1]);
		}
		recordMovedBaggage.remove(0);
		player.move(-vectors[reversDir[dir]][0], -vectors[reversDir[dir]][1]);
		player.move(-vectors[reversDir[dir]][0], -vectors[reversDir[dir]][1]);
		player.move(-vectors[dir][0], -vectors[dir][1]);
	}
	
	public ArrayList<Area> getAreas() {return areas;}
	public ArrayList<Baggage> getBaggs() {return baggs;}
	public ArrayList<Wall> getWalls() {return walls;}
	public ArrayList<Baggage> getBalls() {return balls;}
	public ArrayList<Player> getPlayers() {return players;}
	public Baggage getLastMoveBag() {return recordMovedBaggage.get(0);}
	public Pokemon getPokemon() {return pokemon;}
	public boolean getCaughtPokemon() {return caughtPokemon;}
}
