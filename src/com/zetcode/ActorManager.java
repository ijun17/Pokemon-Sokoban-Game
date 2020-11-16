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
		//System.out.println("AM : movePlayer : moveKeyNum = "+moveKeyNum);
		
		int dir = moveKeyNum%4; // 0:����, 1:������ 2:���� 3:�Ʒ���
		int playerNum = moveKeyNum/4; //Ű ���ؽ��� 0~3�̸� �÷��̾�0, Ű ���ؽ��� 4~7�̸� �÷��̾�1
		
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
				lastMoveBag = bag;
				bag.move(-vectors[dir][0], -vectors[dir][1]);
			}
		}
		return false;
	}
	
//	public void undo(int moveKeyNum) {
//		int reversDir[] = {1,0,3,2};
//		int dir = moveKeyNum%4;
//		Player player = players.get(moveKeyNum/4);
//		Baggage bag = null;
//		
//		for(int i=0; i<baggs.size(); i++) {
//			if(player.isCollision(baggs.get(i), dir)){
//				bag =baggs.get(i);
//				break;
//			}
//		}
//		player.move(-vectors[reversDir[dir]][0],  
//				-vectors[reversDir[dir]][1]);
//		//�÷��̾� �̹��� �״�� �ϱ� ���ؼ�
//		player.move(-vectors[reversDir[dir]][0],  
//				-vectors[reversDir[dir]][1]);
//		player.move(-vectors[dir][0],  
//				-vectors[dir][1]);
//		//
//		if(bag != null) {
//			bag.move(-vectors[reversDir[dir]][0],  
//					-vectors[reversDir[dir]][1]);
//		}
//	}
	
	public ArrayList<Area> getAreas() {return areas;}
	public ArrayList<Baggage> getBaggs() {return baggs;}
	public ArrayList<Wall> getWalls() {return walls;}
	public ArrayList<Baggage> getBalls() {return balls;}
	public ArrayList<Player> getPlayers() {return players;}
	public Baggage getLastMoveBag() {return lastMoveBag;}
}
