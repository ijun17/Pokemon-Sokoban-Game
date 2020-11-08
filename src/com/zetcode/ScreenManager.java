package com.zetcode;

//import java.awt.Window;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenManager{
	static private JFrame screen = new JFrame();  //singleton
	static private final int OFFSET = 30;   //is not static
	static private String panelName;
	static private JPanel currentPanel;
	static private int levelNum;
//	static private ImageIcon icon;
    
	public ScreenManager() {}
	
	static public void setPanel(String pn) {
       panelName= pn;
       updateUI();
    }
    
	static public void setPanel(String pn, int ln) {
       panelName = pn;
       levelNum = ln;
       updateUI();
    }
    
    static private void updateUI() {
    	switch(panelName) {
    	case "menuscreen":
    		currentPanel = new MenuScreen();
    		break;
    		
    	case "gameselectedscreen":
    		currentPanel = new GameSelectedScreen();
    		break;
    		
    	case "singlelevelscreen":
    		currentPanel = new SingleLevelScreen();
    		break;
    		
    	case "multilevelscreen":
    		currentPanel = new MultiLevelScreen();
    		break;
    		
    	case "board-play":
    		currentPanel = new Board(levelNum);
    		break;
    		
    	case "board-replay":
    		currentPanel = new Board(levelNum);
    		((Board)currentPanel).replay();
    		break;
    		
    	default:
    		System.out.println("화면 전환 실패");
    		break;
    	}
    	
    	screen.setVisible(true);
    	screen.add(currentPanel);
        currentPanel.requestFocusInWindow();
        screen.setTitle("Sokoban");
        if(currentPanel instanceof Board) {
        	screen.setSize(((Board)currentPanel).getBoardWidth() + OFFSET,
        		   ((Board)currentPanel).getBoardHeight()+ 2*OFFSET);
        }else screen.setSize(455,300);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
    }
}
