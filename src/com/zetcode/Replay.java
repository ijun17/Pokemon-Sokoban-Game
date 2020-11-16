package com.zetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Replay {
	private String moveKeys;
	private int nowMove;
	private int level;
	
	public Replay(int level) {
		moveKeys="";
		nowMove=0;
		this.level=level;
	}
	
	public int removeLast() {
		if(moveKeys.isEmpty())return -1;
		int returnKey = Character.getNumericValue(moveKeys.charAt(moveKeys.length()-1));
		moveKeys = moveKeys.substring(0, moveKeys.length()-1);
		return returnKey;
	}
	
	public int getMovingKeyLength() {
		return moveKeys.length();
	}
	
	public void addMovingKey(int key) {
		moveKeys += Integer.toString(key);
	}
	public int getNowMove() {return nowMove;}
	
	public int getNextMove() {
		return Character.getNumericValue(moveKeys.charAt(nowMove++));
	}
	
	public void saveReplay() {
		
        String fileName = "replay"+(this.level)+".txt";
        File file = new File(fileName);
        try{
            FileWriter fw = new FileWriter(file, false);
            fw.write(moveKeys);
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public boolean readReplay() {
		File file = new File("replay"+(this.level)+".txt");
		BufferedReader reader = null;
		try {
		        reader = new BufferedReader(new FileReader(file));
		        moveKeys = reader.readLine();
		       	return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (reader != null) {
					reader.close();
		        }
			} catch (IOException e) {
				return false;
			}
		}
	}
}
