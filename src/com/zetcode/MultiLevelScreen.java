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
	private JButton back;
	private JLabel label;
	private Image menuImage;
	private Graphics menuGraphic;
	private ImageIcon background= new ImageIcon("src/resources/gold2.jpg");  

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

		//¸ÇÀ§ 
		label =new JLabel("PLEASE SELECT THE LEVEL",JLabel.CENTER);
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("¸¼Àº°íµñ",Font.BOLD,15)); 
		label.setBounds(-95, 20, 620, 40);
		add(label);

		//¹öÆ°µé
		levelBtn = new JButton[5];
		for(int i=0; i<5; i++) {
			levelBtn[i] = new JButton(new ImageIcon("src/resources/Level"+(i+1)+".png"));
			levelBtn[i].setBorderPainted(false);
			levelBtn[i].setContentAreaFilled(false);
			levelBtn[i].setOpaque(false);
			levelBtn[i].setBounds(157, 60+35*i, 120, 30);
			add(levelBtn[i]);
			final int tmp = i;
			levelBtn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					ScreenManager.setPanel("multiboard", tmp+5);
				}
			});
		}

		//¹é¹öÆ°
		back = new JButton(new ImageIcon("src/resources/BACK.png"));
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
			}
			scoreLabel.setFont(new Font("¸¼Àº°íµñ",Font.BOLD, 12));
			scoreLabel.setBounds(300, 60+35*(i-5) , 120, 30);
			add(scoreLabel);
		}
	}
}