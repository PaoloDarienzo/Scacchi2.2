package view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

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

		while (!(gioco == 1)){
			
			new Window();
			
			//creating, initializing and first printing of the board
			final Board scacchiera = new Board();
			Configuration.initialization(scacchiera);
			//System.out.println(scacchiera.toString());
			
			while (!scacchiera.getCheckmate()){
				
				if (sem){//Waiting the inputs from the window
					sem = false;
					if(scacchiera.getPedine(x, y).getColour() == turn){
					
						if (scacchiera.move(scacchiera, scacchiera.getPedine(x, y), newX, newY)){
							turn = (turn == Colour.bianco) ? Colour.nero : Colour.bianco;
							MyFrame.generateBoard(scacchiera);
						}
						//System.out.println(scacchiera.toString());
					}
					else{
						//TODO
						//Modificare funzione, guardare le API
						JOptionPane.showMessageDialog(null, "It's not your turn.");
					}
				}
			}
			
			//TODO
			//Sistemare icona e inserire percorso al posto di null
			JOptionPane option = new JOptionPane ("CheckMate!\n"
					+ "Do you want to play another game?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null);
			
			if (turn == Colour.bianco){
				JDialog dialog = option.createDialog(null,"Black side won!");
				dialog.pack();
				dialog.setVisible(true);
				gioco = ((Integer)option.getValue()).intValue(); //saving the choice: 0: yes 1: no
			}
			else{
				JDialog dialog = option.createDialog(null,"White side won!");
				dialog.pack();
				dialog.setVisible(true);
				gioco = ((Integer)option.getValue()).intValue(); //saving the choice: 0: yes 1: no
			}
			
			if(gioco == 1){
				JOptionPane.showMessageDialog(null, "See you soon!");
				System.exit(0);
			}
			//TODO
			//se voglio fare un'altra partita devo prima chiudere la finestra della partita precedente.
			
		}	
	}
}