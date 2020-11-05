package com.zetcode;

//import java.awt.Window;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenManager extends JFrame{
	static private ScreenManager singleton = new ScreenManager();
	static private final int OFFSET = 30;   //is not static
	static private String panelName;
	static private JPanel currentPanel;
	static private int levelNum;
//	static private ImageIcon icon;
    
	public ScreenManager() {}
	
	static public void setPanel(String pn) {
       panelName= pn;
       singleton.updateUI();
    }
    
	static public void setPanel(String pn, int ln) {
       panelName = pn;
       levelNum = ln;
       singleton.updateUI();
    }
    
    private void updateUI() {
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
    	
    	this.setVisible(true);
        add(currentPanel);
        currentPanel.requestFocusInWindow();
        setTitle("Sokoban");
        if(currentPanel instanceof Board) {
           setSize(((Board)currentPanel).getBoardWidth() + OFFSET,
        		   ((Board)currentPanel).getBoardHeight()+ 2*OFFSET);
        }else setSize(455,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
