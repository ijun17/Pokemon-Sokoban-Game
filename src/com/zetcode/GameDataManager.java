package com.zetcode;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameDataManager {
	private Score score;
	private ActorManager actorManager;
	private Replay replay;

	private boolean isCompleted;
	private int levelNum;
	private boolean caughtPokemon;
	private int playerCount;

	private int w=0;
	private int h=0;

	public GameDataManager(int lv) {
		score = new Score(lv);
		actorManager = new ActorManager();
		replay = new Replay(lv);
		isCompleted = false;
		levelNum = lv;
		caughtPokemon=false;
		playerCount = 0;
		readMap(Level.getLevel(lv));
	}

	public void readMap(String map) {
		int OFFSET = 30;
		int SPACE = 20;
		int x=OFFSET;
		int y=OFFSET;
		actorManager.addActor(new Pokemon(0,0,3));

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
				playerCount++;
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
		int emptyArea = actorManager.getEmptyArea();
		if (emptyArea == 0) {
			if(actorManager.getCaughtPokemon()) {
				caughtPokemon=true;
				Sound.Play(Resource.musicDir+"Victory.wav");
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
		if (isCompleted()) {return;}

		//player move
		int []key = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
				KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S};

		for(int i=0; i<key.length; i++) {
			if(keyCode == key[i] && i/4<playerCount) {
				boolean isMoved = actorManager.movePlayer(i);
				if(isMoved) {//성공적으로 움직이면
					score.addStepCount(1);
					replay.addMovingKey(i);
				}
			}
		}
		
		if(keyCode == KeyEvent.VK_U) {
			int keyNum = replay.removeLast();
			if(keyNum>-1) {
				actorManager.undo(keyNum);
				score.addStepCount(-1);
			}
		}
		
		if(keyCode == KeyEvent.VK_R) {
			score = new Score(levelNum);
			actorManager = new ActorManager();
			replay = new Replay(levelNum);
			isCompleted = false;
			caughtPokemon=false;
			playerCount = 0;
			readMap(Level.getLevel(levelNum));
		}
		if(keyCode == KeyEvent.VK_M) {
			actorManager.getPlayers().get(0).setPlayerImage(1);
			actorManager.getPlayers().get(0).playerRestart();
		}
		if(keyCode == KeyEvent.VK_N && playerCount == 2) {
			actorManager.getPlayers().get(1).setPlayerImage(1);
			actorManager.getPlayers().get(1).playerRestart();
		}
	}

	public int getStepCount() {return score.getStepCount();}
	public ArrayList<Actor> getAllActor() {
		ArrayList<Actor> allActor = new ArrayList<Actor>();
		allActor.addAll(actorManager.getAreas());
		allActor.addAll(actorManager.getWalls());
		allActor.addAll(actorManager.getBaggs());
		allActor.addAll(actorManager.getPlayers());
		allActor.add(actorManager.getPokemon());
		return allActor;
	}
	public boolean isCaughtPokemon() {return caughtPokemon;}
	public int getBoardWidth() {return w;}
	public int getBoardHeight() {return h;}
}
