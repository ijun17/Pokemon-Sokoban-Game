package com.zetcode;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameDataManager {
	private Level level;
	private Score score;
	private ActorManager actorManager;
	private Replay replay;

	private boolean isCompleted;
	private int levelNum;
	private boolean goldenBall;

	private int w=0;
	private int h=0;

	public GameDataManager(int lv) {
		level = new Level();
		score = new Score(lv);
		actorManager = new ActorManager();
		replay = new Replay(lv);

		isCompleted = false;
		levelNum = lv;
		goldenBall=false;

		readMap(level.getLevel(lv));
	}

	public void readMap(String map) {
		int OFFSET = 30;
		int SPACE = 20;
		int x=OFFSET;
		int y=OFFSET;

		for (int i = 0; i < map.length(); i++) {
			char item = map.charAt(i);
			switch (item) {
			case '#':
				actorManager.addActor(new Wall(x, y));
				break;

			case '*':
				actorManager.addActor(new Box(x, y));
				break;

			case '$':
				actorManager.addActor(new Ball(x, y));
				break;

			case 'l':
				actorManager.addActor(new GoldenBall(x, y));
				break;

			case '.':
				actorManager.addActor(new Area(x, y));
				break;

			case '@':
				actorManager.addActor(new Player(x, y));
				break;

			default:
				break;
			}
			
			if(item == '\n') {
				y += SPACE;
				if (this.w < x) {
					this.w = x;
				}
				x = OFFSET;
			}else x+=SPACE;
			h = y;
		}
	}

	public boolean isCompleted() {
		if(isCompleted)return true;	
		
		ArrayList<Baggage> balls = actorManager.getBalls();
		ArrayList<Area> areas = actorManager.getAreas();
		//System.out.println(balls.size()+" "+areas.size());
		
		int nOfBalls = balls.size();
		int finishedBags = 0;
		for (int i = 0; i < nOfBalls; i++) {
			Baggage bag = balls.get(i);
			for (int j = 0; j < nOfBalls; j++) {
				Area area =  areas.get(j);
				if (bag.x() == area.x() && bag.y() == area.y()) {
					finishedBags += 1;
				}
			}
		}
		if (finishedBags == nOfBalls) {
			if(actorManager.getLastMoveBag() instanceof GoldenBall) {
				goldenBall=true;
				Sound.Play("src/resources/Victory.wav");
				if(score.getScoreRecord()>score.getStepCount()) {
					score.updateScore();
					replay.saveReplay();
				}
			}
			isCompleted = true;
			return true;
		}
		else return false;
	}
	public void updateGameData(int keyCode) {

		if (isCompleted()) {
			return;
		}

		//player move
		int []key = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
				KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S};

		for(int i=0; i<key.length; i++) {
			if(keyCode == key[i] && i/4<=levelNum/5) {
				boolean isMoved = actorManager.movePlayer(i);
				if(isMoved) {//성공적으로 움직이면
					score.addStepCount();
					replay.addMovingKey(i);
				}
			}
		}

		if(keyCode == KeyEvent.VK_R) {}
		if(keyCode == KeyEvent.VK_N) {}
		if(keyCode == KeyEvent.VK_M) {}
	}

	public int getStepCount() {return score.getStepCount();}
	public ArrayList<Actor> getAllActor() {
		ArrayList<Actor> allActor = new ArrayList<Actor>();
		allActor.addAll(actorManager.getAreas());
		allActor.addAll(actorManager.getWalls());
		allActor.addAll(actorManager.getBaggs());
		allActor.addAll(actorManager.getPlayers());
		return allActor;
	}
	public boolean getGoldenBall() {return goldenBall;}
	public int getBoardWidth() {return w;}
	public int getBoardHeight() {return h;}
}
