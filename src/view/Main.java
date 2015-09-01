package view;

import java.util.Scanner;
import controller.*;
import model.Board;
import model.Colour;

/**
 * Main class.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Main {
	
	private static Colour turn = Colour.Bianco;
	
	public static void main(String[] args){
		//The white pieces are at bottom

		int gioco = 1;
		Scanner scan = new Scanner(System.in);

		while (gioco == 1){
			
			int x, y, newX, newY;
			
			//creating, initializing and first printing of the board
			Board scacchiera = new Board();
			Configuration.Initialization(scacchiera);
			System.out.println(scacchiera.toString());
			
			while (!scacchiera.checkmate){
			 
				System.out.print("Inserire x: "); x = scan.nextInt();
				System.out.print("Inserire y: "); y = scan.nextInt();
				System.out.print("Inserire newX: "); newX = scan.nextInt();
				System.out.print("Inserire newY: "); newY = scan.nextInt();
				System.out.println();
				
				//TODO
				//da togliere?
				if ((x < 0 || x > 7) || (y < 0 ||y > 7) || (newX < 0 || newX > 7) || (newY < 0 || newY > 7)){
					System.out.println("Le coordinate inserite eccedono la grandezza della matrice.");
					continue;
				}
				
				if(scacchiera.getPedine(x, y).getColour() == turn){
					//TODO
					//la pedina selezionata dalla grafica
					//viene passata nel move
					if (scacchiera.Move(scacchiera, scacchiera.getPedine(x, y), newX, newY))
						turn = (turn == Colour.Bianco) ? Colour.Nero : Colour.Bianco;
					System.out.println(scacchiera.toString());
				}
				else
					System.out.println("It's not your turn.");

			}
			
			System.out.println("Checkmate!");
			System.out.print("Giocare un'altra partita? Inserire 1 per si, 0 per no. "); gioco = scan.nextInt();
			System.out.println();
			
		}

		System.out.println("Arrivederci!");
		
	}
	
}