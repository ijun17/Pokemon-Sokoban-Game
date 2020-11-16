package com.zetcode;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;

import java.io.IOException;
import java.io.FileReader;


public class Score {
	
	private int level;
	private int stepCount;
	
	private int recordStepCount;
	
	public Score(int level) {
		this.level = level;
		stepCount=0;
		if(!readFile()) {
			recordStepCount = 99999999;
		}
	}
	
	private void makeFile() {
		String txt = stepCount+"\n";
        String fileName = "score"+(this.level)+".txt";
        File file = new File(fileName);
        try{
            FileWriter fw = new FileWriter(file, false);
            fw.write(txt);
            
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	private boolean readFile() {
		File file = new File("score"+(this.level)+".txt");
		BufferedReader reader = null;
		try {
		       reader = new BufferedReader(new FileReader(file));
		       	recordStepCount = Integer.parseInt(reader.readLine());
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

	public void updateScore() {
		if(recordStepCount>stepCount) {
			makeFile();
		}
	}
	
	public void addStepCount(int num) {stepCount+=num;}
	
	public int getScoreRecord() {return recordStepCount;}
	public int getStepCount() {return stepCount;}
}
