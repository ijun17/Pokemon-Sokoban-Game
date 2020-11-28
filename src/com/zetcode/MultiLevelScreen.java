package com.zetcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiLevelScreen extends JPanel {
	private JButton back;
	private JLabel label;
	private Image menuImage;
	private Graphics menuGraphic;
	private ImageIcon background= new ImageIcon(Resource.backgroundDir+"gold2.jpg");  
	private int clearLevel=0;

	public void paint(Graphics g) {
		menuImage = createImage(450, 300);
		menuGraphic = menuImage.getGraphics();
		screenDraw(menuGraphic);
		g.drawImage(menuImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		paintComponents(g);
		this.repaint();
	}


	public MultiLevelScreen() {
		setLayout(null);

		//맨위 
		label =new JLabel("PLEASE SELECT THE LEVEL",JLabel.CENTER);
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("맑은고딕",Font.BOLD,15)); 
		label.setBounds(-95, 20, 620, 40);
		add(label);
		
		//levelButton
		for(int i=5;i<10;i++) {
			makeLevelButton(i);
		}
		//백버튼
		back = new JButton(new ImageIcon(Resource.buttonDir+"BACK.png"));
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setOpaque(false);
		back.setBounds(20, 20, 65, 20);
		add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenManager.setPanel("gameselectedscreen");
			}
		});

		
	}
	
	public void makeLevelButton(int lv) {
		Score score = new Score(lv);
		JButton replayBtn = new JButton();
		JButton levelBtn = new JButton();
		//levelButton
		levelBtn = new JButton(new ImageIcon(Resource.buttonDir+"Level"+(lv-4)+".png"));
		levelBtn.setBorderPainted(false);
		levelBtn.setContentAreaFilled(false);
		levelBtn.setOpaque(false);
		levelBtn.setBounds(50, 60+35*(lv-5), 120, 30);
		add(levelBtn);
		final int tmp = lv;
		if(clearLevel+1>lv-5)levelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenManager.setPanel("board-play", tmp);
			}
		});
		//score
		int stepCount = score.getScoreRecord();
		JLabel scoreLabel;
		if(stepCount == 99999999) {
			scoreLabel = new JLabel("not completed");
			scoreLabel.setForeground(Color.GRAY);
		}
		else { //score가 있으면 리플레이 버튼을 만든다
			clearLevel++;
			scoreLabel = new JLabel("Score: "+ stepCount);
			scoreLabel.setForeground(Color.ORANGE);
			replayBtn = new JButton("replay");
			replayBtn.setBorderPainted(false);
			replayBtn.setContentAreaFilled(false);
			//replayBtn[i].setOpaque(false);
			replayBtn.setBounds(300, 60+35*(lv-5), 120, 30);
			add(replayBtn);
			final int tmp2 = lv;
			replayBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					ScreenManager.setPanel("board-replay", tmp2);
				}
			});
		}
		scoreLabel.setFont(new Font("맑은고딕",Font.BOLD, 12));
		scoreLabel.setBounds(170, 60+35*(lv-5) , 120, 30);
		add(scoreLabel);
	}
}