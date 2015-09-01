package model;

//PEDINE=PEDINA

/**
 * Constructor of a pedine.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Pedine {
	
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
	 * Colour of the pedine
	 */
	private Colour colore;
	/**
	 * Ghost variable; the pedines cannot see a ghost=true pedine
	 */
	private boolean ghost;
	
	/**
	 * Constructor of the pedine.
	 * Ghost is initialized to false.
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param pezzo Type of piece
	 * @param colore Colour of pedine
	 */
	public Pedine (int x, int y, Piece pezzo, Colour colore){
		
		this.x = x;
		this.y = y;
		this.pezzo = pezzo;
		this.colore = colore;
		this.ghost = false;
		
	}
	
	/**
	 * @param newX New value of x
	 */
	public void setX(int newX){
		this.x = newX;
	}
	
	/**
	 * @param newY New value of y
	 */
	public void setY(int newY){
		this.y = newY;
	}
	
	/**
	 * @return Return coordinate x
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * @return Return coordinate y
	 */
	public int getY(){
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
	public Piece getPiece(){
		return this.pezzo;
	}
	
	/**
	 * @param newColore The new pedine's colour
	 */
	public void setColour(Colour newColore){
		this.colore = newColore;
	}
	
	/**
	 * @return Return the pedine's colour
	 */
	public Colour getColour(){
		return this.colore;
	}
	
	/**
	 * @param status New status of pedine's ghost parameter
	 */
	public void setGhost(boolean status){
		this.ghost = status;
	}
	
	/**
	 * @return Return the status of pedine's ghost parameter
	 */
	public boolean getGhost(){
		return this.ghost;
	}

}