package com.zetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.KeyEvent;

public class ActorManager {
	public static int SPACE = 20;
	public static int [][]direction = {{SPACE, 0}, {-SPACE, 0}, {0, SPACE}, {0, -SPACE}};


	private ArrayList<Wall> walls;
	private ArrayList<Baggage> baggs;
	private ArrayList<Area> areas;
	private ArrayList<Baggage> balls;
	private ArrayList<Player> players;

	private Baggage lastMoveBag;

	public ActorManager() {
		walls = new ArrayList<>();
		baggs = new ArrayList<>();
		areas = new ArrayList<>();
		balls = new ArrayList<>();
		players = new ArrayList<>();
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
		}
	}

	public boolean movePlayer(int moveKeyNum){
		System.out.println("AM : movePlayer : moveKeyNum = "+moveKeyNum);
		
		int dir = moveKeyNum%4; // 0:왼쪽, 1:오른쪽 2:위쪽 3:아래쪽
		int playerNum = moveKeyNum/4; //키 인텍스가 0~3이면 플레이어0, 키 인텍스가 4~7이면 플레이어1
		
		if(checkWallCollision(players.get(playerNum), dir))return false;
		if(checkBagCollision(players.get(playerNum), dir))return false;

		Player player = players.get(moveKeyNum/4);
		player.move(-direction[dir][0], -direction[dir][1]);
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
				lastMoveBag = bag;
				bag.move(-direction[dir][0], -direction[dir][1]);
			}
		}
		return false;
	}
	public ArrayList<Area> getAreas() {return areas;}
	public ArrayList<Baggage> getBaggs() {return baggs;}
	public ArrayList<Wall> getWalls() {return walls;}
	public ArrayList<Baggage> getBalls() {return balls;}
	public ArrayList<Player> getPlayers() {return players;}
	public Baggage getLastMoveBag() {return lastMoveBag;}
}
