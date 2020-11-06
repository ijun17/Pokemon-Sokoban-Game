package com.zetcode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class GameSelectedScreen extends JPanel {
	private JButton single;
	private JButton multi;
	private JLabel ll;
	private JButton back;
	private Image menuImage;
	private Graphics menuGraphic;
	private ImageIcon background= new ImageIcon(Resource.backgroundDir+"gold2.jpg");  

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

	public GameSelectedScreen() {
		setBackground(new Color(233,233,233,233));
		ll=new JLabel("PLEASE SELECT THE GAME",JLabel.CENTER);
		single =new JButton(new ImageIcon(Resource.buttonDir+"ΩÃ±€¿Ãæﬂ.png"));
		multi =new JButton(new ImageIcon(Resource.buttonDir+"∫°±€¿Ãæﬂ.png"));
		back = new JButton(new ImageIcon(Resource.buttonDir+"BACK.png"));

		single.setBorderPainted(false);
		single.setContentAreaFilled(false);
		multi.setBorderPainted(false);
		multi.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);

		single.setOpaque(false);
		multi.setOpaque(false);
		back.setOpaque(false);

		ll.setFont(new Font("∏º¿∫∞ÌµÒ",Font.BOLD,20));

		setLayout(null);
		ll.setForeground(Color.ORANGE);
		ll.setBounds(-95, 40, 620, 40);
		single.setBounds(95, 90, 250, 50);
		multi.setBounds(95, 165, 250, 50);
		back.setBounds(20, 20, 65, 20);

		add(ll);
		add(single);
		add(multi);
		add(back);

		single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenManager.setPanel("singlelevelscreen");
			}
		});
		multi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenManager.setPanel("multilevelscreen");
			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenManager.setPanel("menuscreen");
			}
		});
	}
}