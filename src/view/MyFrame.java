package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Board;
import model.Colour;
import model.Piece;

public class MyFrame extends JFrame {
	
	private JPanel panel;
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	private static Image icon = null;
	private static Image img2 = null;
	
	public MyFrame(Board scacchiera){
		
		//TODO
		//Mettere un water
		icon = new ImageIcon("images/chess.gif").getImage();
		setIconImage(icon);
		
		img2 = new ImageIcon("images/CavalloB.gif").getImage();
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//setLayout(new FlowLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(8, 8));
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				
				//TODO
				//Inserire nel costruttore il colore?
				
				Piece pezzo = scacchiera.getPedine(i, j).getPiece();
				
				final MyButton bottone = new MyButton(i, j, pezzo);
				
				if ((i+j)%2 == 0)
					bottone.setBackground(Color.WHITE);
				else
					bottone.setBackground(Color.BLACK);
				
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