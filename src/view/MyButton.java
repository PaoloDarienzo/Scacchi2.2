package view;

import javax.swing.JButton;

/**
 * Button constructor.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class MyButton extends JButton {
	
	/**
	 * Auto-generated serialVersionUID
	 */
	private static final long serialVersionUID = -783386269101341805L;
	/**
	 * Coordinate x of the button
	 */
	private int x;
	/**
	 * Coordinate y of the button
	 */
	private int y;
	
	/**
	 * Constructor of the button.
	 * @param x Coordinate x of the button
	 * @param y Coordinate y of the button
	 */
	public MyButton(int x, int y) {
		
		super();
		this.x = x;
		this.y = y;
		
	}

	/**
	 * @return Return coordinate x of the button
	 */
	public int getbX() {
		
		return this.x;
	}
	
	/**
	 * @return Return coordinate y of the button
	 */
	public int getbY() {
		
		return this.y;
	}
	
}