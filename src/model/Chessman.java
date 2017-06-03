package model;
	//CHESSMAN=PEDINA

/**
* Constructor of a chessman.
* @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
*/
public class Chessman {
	
	/**
	 * Coordinate x
	 */
	private int x;
	/**
	 * Coordinate y
	 */
	private int y;
	/**
	 * Piece type
	 */
	private Piece pezzo;
	/**
	 * Colour of the chessman
	 */
	private Colour colore;
	/**
	 * Ghost variable; the Chessmen cannot see a ghost=true chessman
	 */
	private boolean ghost;
	
	/**
	 * Constructor of the chessman.
	 * Ghost is initialized to false.
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param pezzo Type of piece
	 * @param colore Colour of chessman
	 */
	public Chessman (int x, int y, Piece pezzo, Colour colore){
		
		this.x = x;
		this.y = y;
		this.pezzo = pezzo;
		this.colore = colore;
		this.ghost = false;
		
	}
	
	/**
	 * @param newX New value of x
	 */
	public final void setX(int newX){
		this.x = newX;
	}
	
	/**
	 * @param newY New value of y
	 */
	public final void setY(int newY){
		this.y = newY;
	}
	
	/**
	 * @return Return coordinate x
	 */
	public final int getX(){
		return this.x;
	}
	
	/**
	 * @return Return coordinate y
	 */
	public final int getY(){
		return this.y;
	}
	
	/**
	 * @param newPezzo The new piece type
	 */
	public void setPiece(Piece newPezzo){
		this.pezzo = newPezzo;
	}
	
	/**
	 * @return Return piece type
	 */
	public final Piece getPiece(){
		return this.pezzo;
	}
	
	/**
	 * @param newColore The new chessman's colour
	 */
	public void setColour(Colour newColore){
		this.colore = newColore;
	}
	
	/**
	 * @return Return the chessman's colour
	 */
	public final Colour getColour(){
		return this.colore;
	}
	
	/**
	 * @param status New status of chessman's ghost parameter
	 */
	public final void setGhost(boolean status){
		this.ghost = status;
	}
	
	/**
	 * @return Return the status of chessman's ghost parameter
	 */
	public final boolean getGhost(){
		return this.ghost;
	}

}