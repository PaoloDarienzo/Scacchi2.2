package model;

//PAWN = PEDONE

/**
* Pawn constructor.
* @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
*/
public class Pawn extends Pedine {
	
	/**
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param pezzo Type of piece
	 * @param colore Colour of pedine
	 */
	public Pawn (int x, int y, Piece pezzo, Colour colore){
		super (x, y, pezzo, colore);
	}

}