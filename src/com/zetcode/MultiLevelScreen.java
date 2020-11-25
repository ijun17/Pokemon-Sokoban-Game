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
	private JButton[] levelBtn;
	private JButton[] replayBtn;
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
		
		
		//Score
		for(int i=5; i<10; i++) {
			Score score = new Score(i);
			int stepCount = score.getScoreRecord();
			JLabel scoreLabel;
			if(stepCount == 99999999) {
				scoreLabel = new JLabel("not completed");
				scoreLabel.setForeground(Color.GRAY);
			}else {
				scoreLabel = new JLabel("Score: "+ stepCount);
				scoreLabel.setForeground(Color.ORANGE);
				clearLevel++;
			}
			scoreLabel.setFont(new Font("맑은고딕",Font.BOLD, 12));
			scoreLabel.setBounds(170, 60+35*(i-5) , 120, 30);
			add(scoreLabel);
		}
		
		
		//버튼들
		levelBtn = new JButton[5];
		for(int i=0; i<5; i++) {
			levelBtn[i] = new JButton(new ImageIcon(Resource.buttonDir+"Level"+(i+1)+".png"));
			levelBtn[i].setBorderPainted(false);
			levelBtn[i].setContentAreaFilled(false);
			levelBtn[i].setOpaque(false);
			levelBtn[i].setBounds(50, 60+35*i, 120, 30);
			add(levelBtn[i]);
			final int tmp = i;
			levelBtn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					ScreenManager.setPanel("board-play", tmp+5);
				}
			});
		}

		//다시보기 버튼들
		replayBtn = new JButton[clearLevel];
		for(int i=0; i<clearLevel; i++) {
			replayBtn[i] = new JButton("replay");
			replayBtn[i].setBorderPainted(false);
			replayBtn[i].setContentAreaFilled(false);
			//replayBtn[i].setOpaque(false);
			replayBtn[i].setBounds(300, 60+35*i, 120, 30);
			add(replayBtn[i]);
			final int tmp = i;
			replayBtn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					ScreenManager.setPanel("board-replay", tmp+5);
				}
			});
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
}