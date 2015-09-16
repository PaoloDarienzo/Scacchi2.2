package model;

//VOID = VUOTO

/**
 * Void constructor.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Void extends Pedine{
	
	/**
	 * Constructor of void; no need to specify colour and piece type.
	 * @param x Coordinate x
	 * @param y Coordinate y
	 */
	public Void (int x, int y){
		super (x, y, Piece.V, Colour.neutro);
	}

}