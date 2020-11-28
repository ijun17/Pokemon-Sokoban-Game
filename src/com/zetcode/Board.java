package com.zetcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {
	private GameDataManager gdm;
	private JButton back = new JButton(new ImageIcon(Resource.buttonDir+"backimage.png"));
	private JLabel stepCountLabel;
	private int l;
	private String mode = "play";

	public Board(int lv) {
		gdm = new GameDataManager(lv);
		setLayout(null);
		l=lv;
		
		//백 버튼
		
		back.setOpaque(false);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setBounds(5, 5, 30, 30);
		add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				if(lv<5) {
					ScreenManager.setPanel("singlelevelscreen");
				}else {
					ScreenManager.setPanel("multilevelscreen");
				}
			}
		});
		back.setFocusable(false);
		
		//걸음수 표시
		stepCountLabel = new JLabel("걸음수: "+gdm.getStepCount(), JLabel.RIGHT);
		stepCountLabel.setFont(new Font("맑은고딕",Font.BOLD, 14));
		add(stepCountLabel);
		stepCountLabel.setBounds(15, 10, 100, 20);
		
		//보드 페인트
		initBoard(lv);
	}

	private void initBoard(int l) {
		addKeyListener(new TAdapter());
		setFocusable(true);
	}

	public int getBoardWidth() {
		return gdm.getBoardWidth();
	}

	public int getBoardHeight() {
		return gdm.getBoardHeight();
	}



	private void buildWorld(Graphics g) {
		g.setColor(new Color(206, 226, 122));//
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		stepCountLabel.setText("걸음수: "+gdm.getStepCount());
		ArrayList<Actor> world = new ArrayList<>();
		world = gdm.getAllActor();
		for (int i = 0; i < world.size(); i++) {
			Actor item = world.get(i);
			if (item instanceof Player || item instanceof Baggage) {                
				g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
			} else {
				g.drawImage(item.getImage(), item.x(), item.y(), this);
			}
			if (gdm.isCompleted()) {
				g.setFont(new Font("Sans-serif", Font.BOLD, 18));
				if(gdm.isCaughtPokemon()) {
					g.setColor(Color.ORANGE);
					g.drawString("Success", 120, 25);
				}
				else {
					g.setColor(Color.RED);
					g.drawString("Failed", 120, 25);
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		buildWorld(g);
	}
	
	public void replay() {
		this.mode="replay";
		int []key = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
				KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S};
		Replay r = new Replay(l);
		r.readReplay();
		int moveLength = r.getMovingKeyLength();
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			public void run() {
				if(moveLength-1==r.getNowMove())timer.cancel();
				int temp = r.getNextMove();
				gdm.updateGameData(key[temp]);
				repaint();
			}
		};
		timer.schedule(task, 1000, 200);
	}

	private class TAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(mode.equals("play")) {
				gdm.updateGameData(e.getKeyCode());
				repaint();
			}
		}
	}
}