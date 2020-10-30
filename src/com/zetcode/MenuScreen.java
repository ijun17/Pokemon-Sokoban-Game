package com.zetcode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MenuScreen extends JPanel  {

	private JLabel la;
	private JButton start;
	private JButton exit;
	private JButton back;
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

	public MenuScreen() {
		setBackground(new Color(233,233,233,233));
		la=new JLabel(new ImageIcon("src/resources/소코반로고.png"),JLabel.CENTER);
		exit=new JButton(new ImageIcon("src/resources/exit버튼2.png"));
		start=new JButton(new ImageIcon("src/resources/start버튼.png"));

		start.setBorderPainted(false);
		start.setContentAreaFilled(false);

		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);

		start.setOpaque(false);
		exit.setOpaque(false);
		la.setForeground(Color.ORANGE);
		la.setFont(new Font("Pokemon Solid",Font.PLAIN, 45));

		setLayout(null);
		la.setBounds(-103, 20, 650, 100);
		start.setBounds(157, 110, 120, 30);
		exit.setBounds(157, 165, 120, 30);

		setVisible(true);
		add(la);
		add(start);
		add(exit);
		requestFocus(true);  
		start.addActionListener(new MyActionListener());
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	class MyActionListener implements ActionListener { 
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			ScreenManager.setPanel("gameselectedscreen");
		}
	}
}