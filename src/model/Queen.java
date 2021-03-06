package model;

//QUEEN = REGINA

/**
* Queen constructor.
* @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
*/
public class Queen extends Chessman {
	
	/**
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param pezzo Type of piece
	 * @param colore Colour of chessman
	 */
	public Queen (int x, int y, Piece pezzo, Colour colore){
		super (x, y, pezzo, colore);
	}

}