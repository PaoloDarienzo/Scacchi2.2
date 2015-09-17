package test;

import model.Board;
import model.Colour;
import model.Piece;

/**
 * Class that configures the final configuration of the board for testing.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class InitTest {
	
	/**
	 * Setting final configuration for testing knight's movements.
	 * @param scacchiera Board to set
	 */
	public static void knight(Board scacchiera){
		
		int i=0, j=0;
		
		//Call the setBoard for every piece of the chessboard
		
		//Setting Black side
		scacchiera.setBoard(0, 0, Piece.T, Colour.nero);
		scacchiera.setBoard(0, 7, Piece.T, Colour.nero);
		scacchiera.setBoard(0, 1, Piece.V, Colour.neutro); //old position
		scacchiera.setBoard(0, 6, Piece.C, Colour.nero);
		scacchiera.setBoard(0, 2, Piece.A, Colour.nero);
		scacchiera.setBoard(0, 5, Piece.A, Colour.nero);
		scacchiera.setBoard(0, 4, Piece.K, Colour.nero);
		scacchiera.setBoard(0, 3, Piece.Q, Colour.nero);
		for (i=1; i<2; i++)
			for (j=0; j<8; j++)
				scacchiera.setBoard(i, j, Piece.P, Colour.nero);
		
		//Setting White side
		scacchiera.setBoard(7, 0, Piece.t, Colour.bianco);
		scacchiera.setBoard(7, 7, Piece.t, Colour.bianco);
		scacchiera.setBoard(7, 1, Piece.V, Colour.neutro); //old position
		scacchiera.setBoard(7, 6, Piece.c, Colour.bianco);
		scacchiera.setBoard(7, 2, Piece.a, Colour.bianco);
		scacchiera.setBoard(7, 5, Piece.a, Colour.bianco);
		scacchiera.setBoard(7, 4, Piece.k, Colour.bianco);
		scacchiera.setBoard(7, 3, Piece.q, Colour.bianco);
		for (i=6; i<7; i++)
			for (j=0; j<8; j++)
				scacchiera.setBoard(i, j, Piece.p, Colour.bianco);
		
		//Setting blank spaces
		for (i=2; i<6 ; i++)
			for (j=0; j<8; j++)
				scacchiera.setBoard(i, j, Piece.V, Colour.neutro);
		
		//new positions
		scacchiera.setBoard(5, 6, Piece.c, Colour.bianco);
		scacchiera.setBoard(6, 0, Piece.C, Colour.nero); 
	}

	/**
	 * Setting final configuration for testing queen's movements.
	 * @param scacchiera Board to set
	 */
	public static void queen(Board scacchiera){
		
		int i=0, j=0;
		
		//Call the setBoard for every piece of the chessboard
		
		//Setting Black side
		scacchiera.setBoard(0, 0, Piece.T, Colour.nero);
		scacchiera.setBoard(0, 7, Piece.T, Colour.nero);
		scacchiera.setBoard(0, 1, Piece.C, Colour.nero);
		scacchiera.setBoard(0, 6, Piece.C, Colour.nero);
		scacchiera.setBoard(0, 2, Piece.A, Colour.nero);
		scacchiera.setBoard(0, 5, Piece.A, Colour.nero);
		scacchiera.setBoard(0, 4, Piece.K, Colour.nero);
		scacchiera.setBoard(0, 3, Piece.V, Colour.neutro); //old position
		for (i=1; i<2; i++)
			for (j=0; j<8; j++){
				if (i==1 && j==3)
					scacchiera.setBoard(i, j, Piece.V, Colour.neutro);
				else
					scacchiera.setBoard(i, j, Piece.P, Colour.nero);
			}
		
		//Setting White side
		scacchiera.setBoard(7, 0, Piece.t, Colour.bianco);
		scacchiera.setBoard(7, 7, Piece.t, Colour.bianco);
		scacchiera.setBoard(7, 1, Piece.c, Colour.bianco);
		scacchiera.setBoard(7, 6, Piece.c, Colour.bianco);
		scacchiera.setBoard(7, 2, Piece.a, Colour.bianco);
		scacchiera.setBoard(7, 5, Piece.a, Colour.bianco);
		scacchiera.setBoard(7, 4, Piece.k, Colour.bianco);
		scacchiera.setBoard(7, 3, Piece.V, Colour.neutro); //old position
		for (i=6; i<7; i++)
			for (j=0; j<8; j++){
				if(i==6 && j==3)
					scacchiera.setBoard(i, j, Piece.V, Colour.neutro);
				else
					scacchiera.setBoard(i, j, Piece.p, Colour.bianco);
			}
		
		//Setting blank spaces
		for (i=2; i<6 ; i++)
			for (j=0; j<8; j++)
				scacchiera.setBoard(i, j, Piece.V, Colour.neutro);
		
		//new positions
		scacchiera.setBoard(5, 3, Piece.p, Colour.bianco);
		scacchiera.setBoard(2, 3, Piece.P, Colour.nero); 
		scacchiera.setBoard(5, 0, Piece.q, Colour.bianco);
		scacchiera.setBoard(2, 6, Piece.Q, Colour.nero);
	}

}
