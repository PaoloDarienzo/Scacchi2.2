package view;

import java.util.Scanner;
import java.applet.*;
import java.awt.*;
import javax.swing.*;
import controller.*;
import model.Board;
import model.Colour;

/**
 * Main class.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Main {
	
	static boolean sem = false;
	static boolean FirstClick = true;
	private static Colour turn = Colour.Bianco;
	static int x;
	static int y;
	static int newX;
	static int newY;
	
	public static void main(String[] args){
		//The white pieces are at bottom

		int gioco = 1;
		Scanner scan = new Scanner(System.in);

		while (gioco == 1){
			
			//TODO
			//chiamo la grafica
			//Window game = new Window();
			
			//creating, initializing and first printing of the board
			Board scacchiera = new Board();
			Configuration.Initialization(scacchiera);
			Window game = new Window(scacchiera);
			System.out.println(scacchiera.toString());
			
			
			while (!scacchiera.checkmate){
				
				if (sem){
					sem = false;
					if(scacchiera.getPedine(x, y).getColour() == turn){
					
						if (scacchiera.Move(scacchiera, scacchiera.getPedine(x, y), newX, newY))
							turn = (turn == Colour.Bianco) ? Colour.Nero : Colour.Bianco;
						
						System.out.println(scacchiera.toString());
					}
					else
						System.out.println("It's not your turn.");
					
					//game.repaint();
				}				

			}
		
			System.out.println("Checkmate!");
			System.out.print("Giocare un'altra partita? Inserire 1 per si, 0 per no. "); gioco = scan.nextInt();
			System.out.println();
			
		}

		System.out.println("Arrivederci!");
		
	}
	
}