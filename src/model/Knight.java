package model;

//KNIGHT = CAVALLO

/**
* Knight constructor.
* @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
*/
public class Knight extends Chessman {
	
	/**
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param pezzo Type of piece
	 * @param colore Colour of chessman
	 */
	public Knight (int x, int y, Piece pezzo, Colour colore){
		super (x, y, pezzo, colore);
	}

}