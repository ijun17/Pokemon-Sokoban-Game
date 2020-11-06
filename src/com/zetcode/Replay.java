package com.zetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Replay {
	private String movingKey;
	private int nowMove;
	private int level;
	
	public Replay(int level) {
		movingKey="";
		nowMove=0;
		this.level=level;
	}
	
	public int getMovingKeyLength() {
		return movingKey.length();
	}
	
	public void addMovingKey(int key) {
		movingKey += Integer.toString(key);
	}
	public int getNowMove() {return nowMove;}
	
	public int getNextMove() {
		String temp = "";
		temp = temp+movingKey.charAt(nowMove++);
		return Integer.parseInt(temp);
	}
	
	public void saveReplay() {
		
        String fileName = "replay"+(this.level)+".txt";
        File file = new File(fileName);
        try{
            FileWriter fw = new FileWriter(file, false);
            fw.write(movingKey);
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
		        movingKey = reader.readLine();
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
