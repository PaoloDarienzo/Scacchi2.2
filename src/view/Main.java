package view;

//TODO
//Rimuovere gli import inutili e lo scanner
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
//import javax.swing.SwingConstants;


import controller.Configuration;
import model.Board;
import model.Colour;

/**
 * Main class.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Main {
	
	static boolean sem = false;
	static boolean firstClick = true;
	private static Colour turn = Colour.bianco;
	static int x, y, newX, newY;
	
	public static void main(String[] args){
		//The white pieces are at bottom

		int gioco = 0;
		//TODO
		//rimuovere con grafica
		//Scanner scan = new Scanner(System.in);

		while (!(gioco == 1)){
			
			new Window();
			
			//creating, initializing and first printing of the board
			final Board scacchiera = new Board();
			Configuration.initialization(scacchiera);
			//TODO
			//Rimuovere?
			System.out.println(scacchiera.toString());
			
			while (!scacchiera.getCheckmate()){
				
				if (sem){
					sem = false;
					if(scacchiera.getPedine(x, y).getColour() == turn){
					
						if (scacchiera.move(scacchiera, scacchiera.getPedine(x, y), newX, newY)){
							turn = (turn == Colour.bianco) ? Colour.nero : Colour.bianco;
							MyFrame.generateBoard(scacchiera);
							
						}
						
						System.out.println(scacchiera.toString());
					}
					else{ //TODO
						JOptionPane.showMessageDialog(null, "It's not your turn");
						
						//System.out.println("It's not your turn.");
					}
				}				

			}
			
			//TODO
			//Aprire finestra per comunicare fine partita e chiederne un'altra
			JOptionPane option = new JOptionPane ("CheckMate!\n"
					+ "Giocare un'altra partita?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
			JDialog dialog = option.createDialog(null,"Hai Vinto!");
			dialog.pack();
			dialog.setVisible(true);
			gioco = ((Integer)option.getValue()).intValue(); //salvo la scelta: 0 : yes 1: no
			
			if(gioco == 1)
				System.exit(0);
			//TODO
			//else //se voglio fare un'altra partita devo prima chiudere la finestra della partita precendente.
	
			
			//System.out.println("Checkmate!");
			//System.out.print("Giocare un'altra partita? Inserire 1 per si, 0 per no. "); gioco = scan.nextInt();
			//System.out.println();
			
		}

		//TODO
		//Aggiungere finestra
		JOptionPane.showMessageDialog(null, "Arrivederci!");
		System.exit(0);
		//System.out.println("Arrivederci!");
		
	}
	
}