package view;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

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
	static int x;
	static int y;
	static int newX;
	static int newY;
	
	public static void main(String[] args){
		//The white pieces are at bottom

		int gioco = 1;
		//TODO
		//rimuovere con grafica
		Scanner scan = new Scanner(System.in);

		while (gioco == 1){
			
			new Window();
			
			//creating, initializing and first printing of the board
			final Board scacchiera = new Board();
			Configuration.initialization(scacchiera);
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
						//new TurnWindow();
						System.out.println("It's not your turn.");
					}
				}				

			}
			
			System.out.println("Checkmate!");
			System.out.print("Giocare un'altra partita? Inserire 1 per si, 0 per no. "); gioco = scan.nextInt();
			System.out.println();
			
		}

		System.out.println("Arrivederci!");
		
	}
	
}