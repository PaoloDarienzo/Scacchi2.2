package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Board;
import model.Colour;
import model.Piece;

public class MyFrame extends JFrame {
	
	private JPanel panel;
	private JButton ButtonMatrix[][] = new JButton[8][8];
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	private static Image icon = null;
	private static Image img = null;
	
	public MyFrame(){
		
		icon = new ImageIcon("images/Chessboard.gif").getImage();
		setIconImage(icon);
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//setLayout(new FlowLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(8, 8));
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				
				//TODO
				//Inserire nel costruttore il colore?
				
				final MyButton bottone = new MyButton(i, j);
				
				ButtonMatrix[i][j]= bottone;
				
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
		            	
		            }
		         });
				
			}
		}
		
		//Black pedines
		img = new ImageIcon("images/TorreN.gif").getImage();
		ButtonMatrix[0][0].setIcon(new ImageIcon(img));
		ButtonMatrix[0][7].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/CavalloN.gif").getImage();
		ButtonMatrix[0][1].setIcon(new ImageIcon(img));
		ButtonMatrix[0][6].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/AlfiereN.gif").getImage();
		ButtonMatrix[0][2].setIcon(new ImageIcon(img));
		ButtonMatrix[0][5].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/QueenN.gif").getImage();
		ButtonMatrix[0][3].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/KingN.gif").getImage();
		ButtonMatrix[0][4].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/PedoneN.gif").getImage();
		for (int i=0; i<8; i++){
			ButtonMatrix[1][i].setIcon(new ImageIcon(img));
		}
		
		//White pedines
		img = new ImageIcon("images/TorreB.gif").getImage();
		ButtonMatrix[7][0].setIcon(new ImageIcon(img));
		ButtonMatrix[7][7].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/CavalloB.gif").getImage();
		ButtonMatrix[7][1].setIcon(new ImageIcon(img));
		ButtonMatrix[7][6].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/AlfiereB.gif").getImage();
		ButtonMatrix[7][2].setIcon(new ImageIcon(img));
		ButtonMatrix[7][5].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/KingB.gif").getImage();
		ButtonMatrix[7][3].setIcon(new ImageIcon(img));

		img = new ImageIcon("images/QueenB.gif").getImage();
		ButtonMatrix[7][4].setIcon(new ImageIcon(img));
		
		img = new ImageIcon("images/PedoneB.gif").getImage();
		for (int i=0; i<8; i++){
			ButtonMatrix[6][i].setIcon(new ImageIcon(img));
		}
		
		add(panel, BorderLayout.CENTER);
		
	}
}