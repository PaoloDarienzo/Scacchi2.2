package model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import controller.Check;

/**
 * Board constructor.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Board {
	
	/**
	 * Matrix of chessmen; it's our board
	 */
	private Chessman[][] board;
	/**
	 * Boolean array that tells which king is in check. 0 for white, 1 for black
	 */
	private boolean[] check; //0=white, 1=black
	/**
	 * If true, the game is over
	 */
	private boolean checkmate;
	/**
	 * Choice for chessman
	 */
	private int choice=0;
	//creating 2 lists for all the pieces; neri=black, bianchi=white
	/**
	 * ArrayList that contains all black pieces
	 */
	public ArrayList<Chessman> neri;
	/**
	 * ArrayList that contains all white pieces
	 */
	public ArrayList<Chessman> bianchi;
	
	/**
	 * Constructor of the board.
	 * Board is a 8x8 matrix.
	 * Check and checkmate are initialized to false.
	 */
	public Board (){
		
		this.board = new Chessman [8][8];
		this.check = new boolean[2];
		this.checkmate = false;
		this.neri = new ArrayList<Chessman>();
		this.bianchi = new ArrayList<Chessman>();
		
	}
	
	/**
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param pezzo Type of piece
	 * @param colore Colour of chessman
	 */
	public final void setBoard(int x, int y, Piece pezzo, Colour colore) {
		
		//initialize a new board
		switch(pezzo){
		case P:
		case p:
			this.board[x][y] = new Pawn(x, y, pezzo, colore);
			break;
		case C:
		case c:
			this.board[x][y] = new Knight(x, y, pezzo, colore);
			break;
		case A:
		case a:
			this.board[x][y] = new Bishop(x, y, pezzo, colore);
			break;
		case Q:
		case q:
			this.board[x][y] = new Queen(x, y, pezzo, colore);
			break;
		case K:
		case k:
			this.board[x][y] = new King(x, y, pezzo, colore);
			break;
		case T:
		case t:
			this.board[x][y] = new Rook(x, y, pezzo, colore);
			break;
		case V:
			this.board[x][y] = new Void(x, y);
			break;
		}
	}
	
	/**
	 * @param x Indicates which value to return 
	 * @return Return the value of the variable check indicated
	 */
	public final boolean getCheck(int x){
		return this.check[x];
	}
	
	/**
	 * @param x Indicates which value to set
	 * @param value The new value of the variable
	 */
	public final void setCheck(int x, boolean value){
		this.check[x] = value;
	}
	
	/**
	 * @return Return the value of checkmate
	 */
	public final boolean getCheckmate(){
		return this.checkmate;	
	}
	
	/**
	 * @param checkmate Indicates the new value of checkmate
	 */
	public final void setCheckmate(boolean checkmate){
		this.checkmate = checkmate;
	}
	
	/**
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @return Return the chessman in position[x][y]
	 */
	public final Chessman getChessman(int x, int y){
		return this.board[x][y];
	}
	
	/**
	 * Set a new chessman in the board. If the chessman is a pawn and reaches the end, provides to change chessman.
	 * @param pedina Chessman to set
	 * @param newX New coordinate x of the chessman
	 * @param newY new coordinate y of the chessman
	 */
	public final void setChessman(Chessman pedina, int newX, int newY){

		int x = pedina.getX();
		int y = pedina.getY();
		Colour colorePedina = pedina.getColour();
		
		//Removing captured chessman from the right list
		if (board[newX][newY].getPiece() != Piece.V){
			if (colorePedina == Colour.bianco)
				this.bianchi.remove(board[newX][newY]);
			else
				this.neri.remove(board[newX][newY]);
		}
		
		//no need to check the color; the only pawn
		//who reach x=0 is white and
		//the only one that reach x=7 is black
		if ( (pedina.getPiece() == Piece.p || pedina.getPiece() == Piece.P) &&
			 (newX == 0 || newX == 7) ) {
			//the output is out of the while cycle because there are 2 cases
			//where the request is made, so I don't repeat it.
			
			chooseChessman();
				
				switch (choice){
				case(0):
					if (colorePedina == Colour.bianco){
						setBoard(newX, newY, Piece.a, colorePedina);
						this.bianchi.add(this.getChessman(newX, newY));
						this.bianchi.remove(pedina);
					}
					else{
						setBoard(newX, newY, Piece.A, colorePedina);
						this.neri.add(this.getChessman(newX, newY));
						this.neri.remove(pedina);
					}
					break;
				case(1):
					if (colorePedina == Colour.bianco){
						setBoard(newX, newY, Piece.q, colorePedina);
						this.bianchi.add(this.getChessman(newX, newY));
						this.bianchi.remove(pedina);
					}
					else{
						setBoard(newX, newY, Piece.Q, colorePedina);
						this.neri.add(this.getChessman(newX, newY));
						this.neri.remove(pedina);
					}
					break;
				case(2):
					if (colorePedina == Colour.bianco){
						setBoard(newX, newY, Piece.c, colorePedina);
						this.bianchi.add(this.getChessman(newX, newY));
						this.bianchi.remove(pedina);
					}
					else{
						setBoard(newX, newY, Piece.C, colorePedina);
						this.neri.add(this.getChessman(newX, newY));
						this.neri.remove(pedina);
					}
					break;
				case(3):
					if (colorePedina == Colour.bianco){
						setBoard(newX, newY, Piece.t, colorePedina);
						this.bianchi.add(this.getChessman(newX, newY));
						this.bianchi.remove(pedina);
					}
					else{
						setBoard(newX, newY, Piece.T, colorePedina);
						this.neri.add(this.getChessman(newX, newY));
						this.neri.remove(pedina);
					}
					break;
				}
		}
		
		else{
			//Setting x & y of the piece
			pedina.setX(newX);
			pedina.setY(newY);
			//updating board
			this.board[newX][newY] = pedina;
		}

		this.board[x][y] = new Void(x, y); //Free old position
		
	}
	
	/**
	 * Function that allows user to choose the new piece for the promotion of the pawn.
	 */
	private void chooseChessman() {
		
		Object[] chessman = {"Bishop", "Queen", "Knight", "Rook"};
		String s = (String) JOptionPane.showInputDialog(
		           null,
	               "Choose your new piece!",
	               "Promotion",
	               JOptionPane.PLAIN_MESSAGE,
	               null,//icon
	               chessman,
	               null);
		
		 for (int i = 0; i < chessman.length; i++) {
	            if (s != null && s.compareTo(chessman[i].toString()) == 0) {
	                choice = i;
	                break;
	            } else if (s == null) {//wasn't choose pieces
	                System.exit(0);
	            }
	        }

	}
	
	/**
	 * Function that checks if the move is allowed. if it is, it sets the movement and sets check and checkmate.
	 * @param scacchiera Matrix on which I am working
	 * @param pedinaMossa Chessman that has to be moved
	 * @param newX Coordinate x where the chessman has to be moved
	 * @param newY Coordinate y where the chessman has to be moved
	 * @return Returns if the move was done or not
	 */
	public final boolean move(Board scacchiera, Chessman pedinaMossa, int newX, int newY){
		
		boolean set = false; //Need to know if setting is done for restoring ghost boolean
		Colour colorePedina = pedinaMossa.getColour();
		//Restoring check variable of board. If the move isn't allowed, the turn doesn't change so the check variable is restored
		if (colorePedina == Colour.bianco)
			scacchiera.check[0] = false;
		else
			scacchiera.check[1] = false;
		
		//Check if movement for that chessman is allowed (rook move as rook (etc), and the destination isn't occupied by me)
		if ( Check.selectCheck(scacchiera, pedinaMossa, newX, newY) ){
			
			//AT THIS POINT I KNOW THAT THE CHESSMAN IS ALLOWED TO DO THAT MOVE;
			//I HAVE TO CHECK IF THIS BEHAVIOR ENDANGERS MY KING
			//AND I HAVE TO SET CHECK OR CHECKMATE
			
				pedinaMossa.setGhost(true); //My chessman is now a ghost, no one can see it
			
			if (scacchiera.getChessman(newX,  newY).getPiece() != Piece.V) //on the target there is a chessman (obviously is not allied)
				scacchiera.getChessman(newX,  newY).setGhost(true); //setting ghost true of chessman's target
			
			//I control if I don't put my king in check
			if ( Check.check(scacchiera, pedinaMossa, newX, newY) ){
				this.setChessman(pedinaMossa, newX, newY);
				set = true;
			}
			else{
				JOptionPane.showMessageDialog(null, "Error, move is not allowed. Your king can't be in check!", "Check", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			//I restore the ghost boolean
			pedinaMossa.setGhost(false); //If is king, nothing change
			if (!set) //if set=true, I don't have to set the ghost bool of chessman target because i ate that chessman
				scacchiera.getChessman(newX, newY).setGhost(false); //restoring ghost boolean of target chessman
			
			//looking for checkmate
			//First, I set the check. If check=true, I look for checkmate
			Check.setCheck(scacchiera, pedinaMossa);
			
			if (colorePedina == Colour.bianco){
				if (scacchiera.check[1])
					Check.checkmate(scacchiera, pedinaMossa, pedinaMossa.getColour()); //set the checkmate var
			}
			else{
				if (scacchiera.check[0])
					Check.checkmate(scacchiera, pedinaMossa,  pedinaMossa.getColour()); //set the checkmate var
			}

			//returning true for switching turn
			return true;
		}
		else
			JOptionPane.showMessageDialog(null, "Error, that piece can't do that.", "Error, invalid move", JOptionPane.ERROR_MESSAGE);
		
		return false;
		
	}	
	
	/*
	@Override
	public String toString(){
		
		//funzione che serve per stampare la scacchiera
		int i,j;
		String res="";
		res+=" ";

		//stampa coordinate y
		for (j=0; j<8; j++)
			res+="  " + j + " ";
		res+="\n";

		res+=" ";

		for (j=0; j<8; j++)			
			res+=" ___";
		res+="\n";

		for (i=0; i<8; i++){
			res+=i;
			for (j=0; j<8; j++)	{
				if (this.board[i][j].getPiece() == Piece.V)
					res+="|___";
				else
					res+="|_"+this.board[i][j].getPiece()+"_";
			}
			res+="|\n";
		}

		return res;
	}*/

}