package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	
	private JPanel panel;
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	private static Image img = null;
	
	public MyFrame(){
		
		//TODO
		//Mettere un water
		img = new ImageIcon("src/view/images/KingN.gif").getImage();
		setIconImage(img);
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//setLayout(new FlowLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(8, 8));
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				
				final MyButton bottone = new MyButton(i, j);
				panel.add(bottone);

				bottone.addActionListener(new ActionListener() {

		            public void actionPerformed(ActionEvent e) {
		            	
		            	if (Main.FirstClick){
		            		Main.x = bottone.getbX();
		            		Main.y = bottone.getbY();
		            	}
		            	else{
		            		Main.newX = bottone.getbX();
		            		Main.newY = bottone.getbY();
		            		Main.sem = true;
		            	}
		            	
		            	Main.FirstClick = !Main.FirstClick;
		            	
		            	/*
		            	if (Main.sem)
		            		Main.sem = false;
		            	else
		            		Main.sem = true;*/
		            	
		            	//TODO
		            	//mettiamo move
		            	
		            	
		            	
		            }
		         });
				
			}
		}
		
		add(panel, BorderLayout.CENTER);
		
	}
}