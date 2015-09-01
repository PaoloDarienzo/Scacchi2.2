package controller;

import model.Board;
import model.Colour;
import model.Piece;

/**
 * Configuration class.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Configuration {
	
	/**
	 * This function initialize the board.
	 * @param scacchiera Matrix to initialize
	 */
	public static void Initialization(Board scacchiera){
		
		int i=0, j=0;
		
		//Setting boolean array for the check variable
		scacchiera.check[0] = false; //0=white
		scacchiera.check[1] = false; //1=black
		//scacchiera.checkmate = false; Already set when object was created
		
		//Call the setBoard for every piece of the chessboard
		
		//Setting Black side
		scacchiera.setBoard(0, 0, Piece.T, Colour.Nero);
		scacchiera.setBoard(0, 7, Piece.T, Colour.Nero);
		scacchiera.setBoard(0, 1, Piece.C, Colour.Nero);
		scacchiera.setBoard(0, 6, Piece.C, Colour.Nero);
		scacchiera.setBoard(0, 2, Piece.A, Colour.Nero);
		scacchiera.setBoard(0, 5, Piece.A, Colour.Nero);
		scacchiera.setBoard(0, 4, Piece.K, Colour.Nero);
		scacchiera.setBoard(0, 3, Piece.Q, Colour.Nero);
		for (i=1; i<2; i++)
			for (j=0; j<8; j++)
				scacchiera.setBoard(i, j, Piece.P, Colour.Nero);
		
		//Setting White side
		scacchiera.setBoard(7, 0, Piece.t, Colour.Bianco);
		scacchiera.setBoard(7, 7, Piece.t, Colour.Bianco);
		scacchiera.setBoard(7, 1, Piece.c, Colour.Bianco);
		scacchiera.setBoard(7, 6, Piece.c, Colour.Bianco);
		scacchiera.setBoard(7, 2, Piece.a, Colour.Bianco);
		scacchiera.setBoard(7, 5, Piece.a, Colour.Bianco);
		scacchiera.setBoard(7, 4, Piece.k, Colour.Bianco);
		scacchiera.setBoard(7, 3, Piece.q, Colour.Bianco);
		for (i=6; i<7; i++)
			for (j=0; j<8; j++)
				scacchiera.setBoard(i, j, Piece.p, Colour.Bianco);
		
		//Setting blank spaces
		for (i=2; i<6 ; i++)
			for (j=0; j<8; j++)
				scacchiera.setBoard(i, j, Piece.V, Colour.Neutro);
		
		//Setting lists: Black one
		scacchiera.Neri.add(scacchiera.getPedine(0, 4)); //king first
		scacchiera.Neri.add(scacchiera.getPedine(0, 0));
		scacchiera.Neri.add(scacchiera.getPedine(0, 7));
		scacchiera.Neri.add(scacchiera.getPedine(0, 1));
		scacchiera.Neri.add(scacchiera.getPedine(0, 6));
		scacchiera.Neri.add(scacchiera.getPedine(0, 2));
		scacchiera.Neri.add(scacchiera.getPedine(0, 5));
		scacchiera.Neri.add(scacchiera.getPedine(0, 3));
		for (i=1; i<2; i++)
			for (j=0; j<8; j++)
				scacchiera.Neri.add(scacchiera.getPedine(i, j));
		
		//White one
		scacchiera.Bianchi.add(scacchiera.getPedine(7, 4)); //king first
		scacchiera.Bianchi.add(scacchiera.getPedine(7, 0));
		scacchiera.Bianchi.add(scacchiera.getPedine(7, 7));
		scacchiera.Bianchi.add(scacchiera.getPedine(7, 1));
		scacchiera.Bianchi.add(scacchiera.getPedine(7, 6));
		scacchiera.Bianchi.add(scacchiera.getPedine(7, 2));
		scacchiera.Bianchi.add(scacchiera.getPedine(7, 5));
		scacchiera.Bianchi.add(scacchiera.getPedine(7, 3));
		for (i=6; i<7; i++)
			for (j=0; j<8; j++)
				scacchiera.Bianchi.add(scacchiera.getPedine(i, j));
	}

}
