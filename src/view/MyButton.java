package view;

import javax.swing.JButton;

/**
 * Button constructor.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class MyButton extends JButton {
	
	/**
	 * Auto-generated serialVersionUID
	 *///TODO
	//private static final long serialVersionUID = -783386269101341805L;
	/**
	 * Coordinate x of the button
	 */
	private int bx;
	/**
	 * Coordinate y of the button
	 */
	private int by;
	
	/**
	 * Constructor of the button.
	 * @param x Coordinate x of the button
	 * @param y Coordinate y of the button
	 */
	public MyButton(int x, int y) {
		
		super();
		this.bx = x;
		this.by = y;
		
	}

	/**
	 * @return Return coordinate x of the button
	 */
	public final int getbX() {
		
		return this.bx;
	}
	
	/**
	 * @return Return coordinate y of the button
	 */
	public final int getbY() {
		
		return this.by;
	}
	
}