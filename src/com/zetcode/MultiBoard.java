package com.zetcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiBoard extends JPanel {
	private GameDataManager gdm;
	private JButton back = new JButton(new ImageIcon("src/resources/backimage.png"));
	private JLabel stepCountLabel;
	private int l;

	public MultiBoard(int lv) {
		gdm = new GameDataManager(lv);
		
		setLayout(null);
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

		l=lv;

		stepCountLabel = new JLabel("°ÉÀ½¼ö: "+gdm.getStepCount(), JLabel.RIGHT);
		stepCountLabel.setFont(new Font("¸¼Àº°íµñ",Font.BOLD, 14));
		add(stepCountLabel);
		stepCountLabel.setBounds(15, 10, 100, 20);
		initBoard(lv);
	}

	private void initBoard(int l) {

		addKeyListener(new TAdapter());
		setFocusable(true);
		//initWorld();
	}

	public int getBoardWidth() {
		return gdm.getBoardWidth();
	}

	public int getBoardHeight() {
		return gdm.getBoardHeight();
	}



	private void buildWorld(Graphics g) {
		g.setColor(new Color(217, 230, 165));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		stepCountLabel.setText("°ÉÀ½¼ö: "+gdm.getStepCount());
		
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
				g.setColor(new Color(0, 0, 0));

				if(gdm.getGoldenBall() == 0) {
					g.drawString("Failed", 150, 20);
				}
				else 
					g.drawString("Success", 150, 20);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		buildWorld(g);
	}

	private class TAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			gdm.updateGameData(e);
			repaint();
		}
	}
				
}