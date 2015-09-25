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
	 * This function initializes the board.
	 * @param scacchiera Matrix to initialize
	 */
	public static void initialization(Board scacchiera){
		
		int i=0, j=0;
		
		//Setting boolean array for the check variable
		scacchiera.setCheck(0, false);//0=white
		scacchiera.setCheck(1, false);//1=black
		//scacchiera.checkmate = false; Already set when object was created
		
		//Call the setBoard for every piece of the chessboard
		
		//Setting Black side
		scacchiera.setBoard(0, 0, Piece.T, Colour.nero);
		scacchiera.setBoard(0, 7, Piece.T, Colour.nero);
		scacchiera.setBoard(0, 1, Piece.C, Colour.nero);
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
		scacchiera.setBoard(7, 1, Piece.c, Colour.bianco);
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
		
		//Setting lists: Black one
		scacchiera.neri.add(scacchiera.getPedine(0, 4)); //king first
		scacchiera.neri.add(scacchiera.getPedine(0, 0));
		scacchiera.neri.add(scacchiera.getPedine(0, 7));
		scacchiera.neri.add(scacchiera.getPedine(0, 1));
		scacchiera.neri.add(scacchiera.getPedine(0, 6));
		scacchiera.neri.add(scacchiera.getPedine(0, 2));
		scacchiera.neri.add(scacchiera.getPedine(0, 5));
		scacchiera.neri.add(scacchiera.getPedine(0, 3));
		for (i=1; i<2; i++)
			for (j=0; j<8; j++)
				scacchiera.neri.add(scacchiera.getPedine(i, j));
		
		//White one
		scacchiera.bianchi.add(scacchiera.getPedine(7, 4)); //king first
		scacchiera.bianchi.add(scacchiera.getPedine(7, 0));
		scacchiera.bianchi.add(scacchiera.getPedine(7, 7));
		scacchiera.bianchi.add(scacchiera.getPedine(7, 1));
		scacchiera.bianchi.add(scacchiera.getPedine(7, 6));
		scacchiera.bianchi.add(scacchiera.getPedine(7, 2));
		scacchiera.bianchi.add(scacchiera.getPedine(7, 5));
		scacchiera.bianchi.add(scacchiera.getPedine(7, 3));
		for (i=6; i<7; i++)
			for (j=0; j<8; j++)
				scacchiera.bianchi.add(scacchiera.getPedine(i, j));
	}

}