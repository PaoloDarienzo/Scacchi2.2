package view;

import javax.swing.JButton;

//TODO
//da fare javadoc su tutta la grafica

public class MyButton extends JButton {
	
	private int x;
	private int y;
	
	public MyButton(int x, int y) {
		
		super();
		this.x = x;
		this.y = y;
	}

	public int getbX() {
		
		return this.x;
	}
	
	public int getbY() {
		
		return this.y;
	}
	
}