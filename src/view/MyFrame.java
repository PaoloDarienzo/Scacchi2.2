package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Board;
import model.Piece;

/**
 * Frame that display the current match.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 *
 */
public class MyFrame extends JFrame {
	
	/**
	 * Auto-generated serialVersionUID
	 */
	private static final long serialVersionUID = 2339646745849754800L;
	private static JPanel panel;
	private static JButton ButtonMatrix[][] = new JButton[8][8];
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	
	/**
	 * Loading white bishop image
	 */
	private static Image alfiereB = new ImageIcon("images/AlfiereB.gif").getImage();
	/**
	 * Loading black bishop image
	 */
	private static Image alfiereN = new ImageIcon("images/AlfiereN.gif").getImage();
	/**
	 * Loading white knight image
	 */
	private static Image cavalloB = new ImageIcon("images/CavalloB.gif").getImage();
	/**
	 * Loading black knight image
	 */
	private static Image cavalloN = new ImageIcon("images/CavalloN.gif").getImage();
	/**
	 * Loading white king image
	 */
	private static Image kingB = new ImageIcon("images/KingB.gif").getImage();
	/**
	 * Loading black king image
	 */
	private static Image kingN = new ImageIcon("images/KingN.gif").getImage();
	/**
	 * Loading white pawn image
	 */
	private static Image pedoneB = new ImageIcon("images/PedoneB.gif").getImage();
	/**
	 * Loading black pawn image
	 */
	private static Image pedoneN = new ImageIcon("images/PedoneN.gif").getImage();
	/**
	 * Loading white queen image
	 */
	private static Image queenB = new ImageIcon("images/QueenB.gif").getImage();
	/**
	 * Loading black queen image
	 */
	private static Image queenN = new ImageIcon("images/QueenN.gif").getImage();
	/**
	 * Loading white rook image
	 */
	private static Image torreB = new ImageIcon("images/TorreB.gif").getImage();
	/**
	 * Loading black rook image
	 */
	private static Image torreN = new ImageIcon("images/TorreN.gif").getImage();
	
	public MyFrame(){
		
		//Setting the window icon
		Image icon = new ImageIcon("images/Chessboard.gif").getImage();
		setIconImage(icon);
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//setLayout(new FlowLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(8, 8));
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				
				final MyButton bottone = new MyButton(i, j);
				
				ButtonMatrix[i][j]= bottone;
				
				if ((i+j)%2 == 0)
					bottone.setBackground(Color.WHITE);
				else
					bottone.setBackground(Color.BLACK);
				
				panel.add(bottone);

				bottone.addActionListener(new ActionListener() {

		            public final void actionPerformed(ActionEvent e) {
		            	
		            	if (Main.firstClick){
		            		Main.x = bottone.getbX();
		            		Main.y = bottone.getbY();
		            	}
		            	else{
		            		Main.newX = bottone.getbX();
		            		Main.newY = bottone.getbY();
		            		Main.sem = true;
		            	}
		            	
		            	Main.firstClick = !Main.firstClick;		            	
		            	
		            }
		         });
				
			}
		}
		
		//Black pedines
		ButtonMatrix[0][0].setIcon(new ImageIcon(torreN));
		ButtonMatrix[0][7].setIcon(new ImageIcon(torreN));
		
		ButtonMatrix[0][1].setIcon(new ImageIcon(cavalloN));
		ButtonMatrix[0][6].setIcon(new ImageIcon(cavalloN));
		
		ButtonMatrix[0][2].setIcon(new ImageIcon(alfiereN));
		ButtonMatrix[0][5].setIcon(new ImageIcon(alfiereN));
		
		ButtonMatrix[0][3].setIcon(new ImageIcon(queenN));
		
		ButtonMatrix[0][4].setIcon(new ImageIcon(kingN));
		
		for (int i=0; i<8; i++){
			ButtonMatrix[1][i].setIcon(new ImageIcon(pedoneN));
		}
		
		//White pedines
		ButtonMatrix[7][0].setIcon(new ImageIcon(torreB));
		ButtonMatrix[7][7].setIcon(new ImageIcon(torreB));
		
		ButtonMatrix[7][1].setIcon(new ImageIcon(cavalloB));
		ButtonMatrix[7][6].setIcon(new ImageIcon(cavalloB));
		
		ButtonMatrix[7][2].setIcon(new ImageIcon(alfiereB));
		ButtonMatrix[7][5].setIcon(new ImageIcon(alfiereB));
		
		ButtonMatrix[7][3].setIcon(new ImageIcon(queenB));

		ButtonMatrix[7][4].setIcon(new ImageIcon(kingB));
		
		for (int i=0; i<8; i++){
			ButtonMatrix[6][i].setIcon(new ImageIcon(pedoneB));
		}
		
		add(panel, BorderLayout.CENTER);
		
	}

	/**
	 * Method that refresh the game window.
	 * @param scacchiera Used to retrieve the pieces
	 */
	 public static void generateBoard(Board scacchiera) {

		 //Removing everything on the panel
	     panel.removeAll();
	       
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	
            	final MyButton bottone = new MyButton(i, j); //Creating the button
				
				ButtonMatrix[i][j]= bottone; //Adding the button in the matrix
					
				//Setting background colour of button
				if ((i+j)%2 == 0)
					bottone.setBackground(Color.WHITE);
				else
					bottone.setBackground(Color.BLACK);
				
				panel.add(bottone); //Adding the button

				//Adding the actionListener to the button
				bottone.addActionListener(new ActionListener() {

					public final void actionPerformed(ActionEvent e) {
			            	
						if (Main.firstClick){
			            	Main.x = bottone.getbX();
			            	Main.y = bottone.getbY();
			            }
			            else{
			            	Main.newX = bottone.getbX();
			            	Main.newY = bottone.getbY();
			            	Main.sem = true;
			            }
			            	
			            //Setting the first click boolean
			            Main.firstClick = !Main.firstClick;		            	
			            	
			         }
				});
					
	            Piece pezzo = scacchiera.getPedine(i, j).getPiece();
	                
	            //Assigning the right icon on the button
	            switch(pezzo){
	        	case P:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(pedoneN));
	        		break;
	        	case p:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(pedoneB));
	        		break;
	        	case C:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(cavalloN));
	        		break;
	        	case c:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(cavalloB));
	        		break;
	        	case A:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(alfiereN));
	        		break;
	        	case a:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(alfiereB));
	        		break;
	        	case Q:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(queenN));
	        		break;
	        	case q:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(queenB));
	        		break;
	        	case K:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(kingN));
	        		break;
	        	case k:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(kingB));
	        		break;
	        	case T:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(torreN));
	        		break;
	        	case t:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(torreB));
	        		break;
	        	case V:
	        		break;
	            }     
            }
        }
	//Validating everything on the panel so it can be desplayed
	panel.revalidate();
	        
	}	
}