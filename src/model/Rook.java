package model;

//ROOK = TORRE

/**
* Rook constructor.
* @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
*/
public class Rook extends Chessman {
	
	/**
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param pezzo Type of piece
	 * @param colore Colour of chessman
	 */
	public Rook (int x, int y, Piece pezzo, Colour colore){
		super (x, y, pezzo, colore);
	}

}