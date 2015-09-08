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
	
	private static Image icon = null;
	/**
	 * Loading white bishop image
	 */
	private static Image AlfiereB = new ImageIcon("images/AlfiereB.gif").getImage();
	/**
	 * Loading black bishop image
	 */
	private static Image AlfiereN = new ImageIcon("images/AlfiereN.gif").getImage();
	/**
	 * Loading white knight image
	 */
	private static Image CavalloB = new ImageIcon("images/CavalloB.gif").getImage();
	/**
	 * Loading black knight image
	 */
	private static Image CavalloN = new ImageIcon("images/CavalloN.gif").getImage();
	/**
	 * Loading white king image
	 */
	private static Image KingB = new ImageIcon("images/KingB.gif").getImage();
	/**
	 * Loading black king image
	 */
	private static Image KingN = new ImageIcon("images/KingN.gif").getImage();
	/**
	 * Loading white pawn image
	 */
	private static Image PedoneB = new ImageIcon("images/PedoneB.gif").getImage();
	/**
	 * Loading black pawn image
	 */
	private static Image PedoneN = new ImageIcon("images/PedoneN.gif").getImage();
	/**
	 * Loading white queen image
	 */
	private static Image QueenB = new ImageIcon("images/QueenB.gif").getImage();
	/**
	 * Loading black queen image
	 */
	private static Image QueenN = new ImageIcon("images/QueenN.gif").getImage();
	/**
	 * Loading white rook image
	 */
	private static Image TorreB = new ImageIcon("images/TorreB.gif").getImage();
	/**
	 * Loading black rook image
	 */
	private static Image TorreN = new ImageIcon("images/TorreN.gif").getImage();
	
	public MyFrame(){
		
		//Setting the window icon
		icon = new ImageIcon("images/Chessboard.gif").getImage();
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
		ButtonMatrix[0][0].setIcon(new ImageIcon(TorreN));
		ButtonMatrix[0][7].setIcon(new ImageIcon(TorreN));
		
		ButtonMatrix[0][1].setIcon(new ImageIcon(CavalloN));
		ButtonMatrix[0][6].setIcon(new ImageIcon(CavalloN));
		
		ButtonMatrix[0][2].setIcon(new ImageIcon(AlfiereN));
		ButtonMatrix[0][5].setIcon(new ImageIcon(AlfiereN));
		
		ButtonMatrix[0][3].setIcon(new ImageIcon(QueenN));
		
		ButtonMatrix[0][4].setIcon(new ImageIcon(KingN));
		
		for (int i=0; i<8; i++){
			ButtonMatrix[1][i].setIcon(new ImageIcon(PedoneN));
		}
		
		//White pedines
		ButtonMatrix[7][0].setIcon(new ImageIcon(TorreB));
		ButtonMatrix[7][7].setIcon(new ImageIcon(TorreB));
		
		ButtonMatrix[7][1].setIcon(new ImageIcon(CavalloB));
		ButtonMatrix[7][6].setIcon(new ImageIcon(CavalloB));
		
		ButtonMatrix[7][2].setIcon(new ImageIcon(AlfiereB));
		ButtonMatrix[7][5].setIcon(new ImageIcon(AlfiereB));
		
		ButtonMatrix[7][3].setIcon(new ImageIcon(KingB));

		ButtonMatrix[7][4].setIcon(new ImageIcon(QueenB));
		
		for (int i=0; i<8; i++){
			ButtonMatrix[6][i].setIcon(new ImageIcon(PedoneB));
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
			            	
			            //Setting the first click boolean
			            Main.FirstClick = !Main.FirstClick;		            	
			            	
			         }
				});
					
	            Piece pezzo = scacchiera.getPedine(i, j).getPiece();
	                
	            //Assigning the right icon on the button
	            switch(pezzo){
	        	case P:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(PedoneN));
	        		break;
	        	case p:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(PedoneB));
	        		break;
	        	case C:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(CavalloN));
	        		break;
	        	case c:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(CavalloB));
	        		break;
	        	case A:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(AlfiereN));
	        		break;
	        	case a:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(AlfiereB));
	        		break;
	        	case Q:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(QueenN));
	        		break;
	        	case q:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(QueenB));
	        		break;
	        	case K:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(KingN));
	        		break;
	        	case k:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(KingB));
	        		break;
	        	case T:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(TorreN));
	        		break;
	        	case t:
	        		ButtonMatrix[i][j].setIcon(new ImageIcon(TorreB));
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