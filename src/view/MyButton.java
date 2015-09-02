package view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Piece;

//TODO
//da fare javadoc su tutta la grafica

public class MyButton extends JButton {
	
	private int x;
	private int y;
	private Image img;
	
	public MyButton(int x, int y, Piece pezzo) {
		
		super();
		this.x = x;
		this.y = y;
		switch(pezzo){
		case K:
			this.img = new ImageIcon("images/KingN.gif").getImage();
			break;
		case k:
			this.img = new ImageIcon("images/KingB.gif").getImage();
			break;
		case Q:
			this.img = new ImageIcon("images/QueenN.gif").getImage();
			break;
		case q:
			this.img = new ImageIcon("images/QueenB.gif").getImage();
			break;
		case T:
			this.img = new ImageIcon("images/TorreN.gif").getImage();
			break;
		case t:
			this.img = new ImageIcon("images/torreB.gif").getImage();
			break;
		case A:
			this.img = new ImageIcon("images/AlfiereN.gif").getImage();
			break;
		case a:
			this.img = new ImageIcon("images/AlfiereB.gif").getImage();
			break;
		case C:
			this.img = new ImageIcon("images/CavalloN.gif").getImage();
			break;
		case c:
			this.img = new ImageIcon("images/CavalloB.gif").getImage();
			break;
		case P:
			this.img = new ImageIcon("images/PedoneN.gif").getImage();
			break;
		case p:
			this.img = new ImageIcon("images/PedoneB.gif").getImage();
			break;
		case V:
			this.img = null;
			break;
		default:
			break;
		}
		
	}

	public int getbX() {
		
		return this.x;
	}
	
	public int getbY() {
		
		return this.y;
	}
	
}