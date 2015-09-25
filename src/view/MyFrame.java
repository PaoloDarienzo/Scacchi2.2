package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
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
	private static JButton buttonMatrix[][] = new JButton[8][8];
	
	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static Dimension screenSize = kit.getScreenSize();
	private static int screenHeight = screenSize.height;
	private static int screenWidth = screenSize.width;
	
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
		
		setSize(((screenWidth/4)*3), ((screenHeight/4)*3));
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(8, 8));
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				
				final MyButton bottone = new MyButton(i, j);
				
				buttonMatrix[i][j]= bottone;
				
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
		buttonMatrix[0][0].setIcon(new ImageIcon(torreN));
		buttonMatrix[0][7].setIcon(new ImageIcon(torreN));
		
		buttonMatrix[0][1].setIcon(new ImageIcon(cavalloN));
		buttonMatrix[0][6].setIcon(new ImageIcon(cavalloN));
		
		buttonMatrix[0][2].setIcon(new ImageIcon(alfiereN));
		buttonMatrix[0][5].setIcon(new ImageIcon(alfiereN));
		
		buttonMatrix[0][3].setIcon(new ImageIcon(queenN));
		
		buttonMatrix[0][4].setIcon(new ImageIcon(kingN));
		
		for (int i=0; i<8; i++){
			buttonMatrix[1][i].setIcon(new ImageIcon(pedoneN));
		}
		
		//White pedines
		buttonMatrix[7][0].setIcon(new ImageIcon(torreB));
		buttonMatrix[7][7].setIcon(new ImageIcon(torreB));
		
		buttonMatrix[7][1].setIcon(new ImageIcon(cavalloB));
		buttonMatrix[7][6].setIcon(new ImageIcon(cavalloB));
		
		buttonMatrix[7][2].setIcon(new ImageIcon(alfiereB));
		buttonMatrix[7][5].setIcon(new ImageIcon(alfiereB));
		
		buttonMatrix[7][3].setIcon(new ImageIcon(queenB));

		buttonMatrix[7][4].setIcon(new ImageIcon(kingB));
		
		for (int i=0; i<8; i++){
			buttonMatrix[6][i].setIcon(new ImageIcon(pedoneB));
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
				
				buttonMatrix[i][j]= bottone; //Adding the button in the matrix
					
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
	        		buttonMatrix[i][j].setIcon(new ImageIcon(pedoneN));
	        		break;
	        	case p:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(pedoneB));
	        		break;
	        	case C:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(cavalloN));
	        		break;
	        	case c:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(cavalloB));
	        		break;
	        	case A:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(alfiereN));
	        		break;
	        	case a:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(alfiereB));
	        		break;
	        	case Q:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(queenN));
	        		break;
	        	case q:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(queenB));
	        		break;
	        	case K:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(kingN));
	        		break;
	        	case k:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(kingB));
	        		break;
	        	case T:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(torreN));
	        		break;
	        	case t:
	        		buttonMatrix[i][j].setIcon(new ImageIcon(torreB));
	        		break;
	        	case V:
	        		break;
	            }     
            }
        }
	//Validating everything on the panel so it can be displayed
	panel.revalidate();
	        
	}	
}