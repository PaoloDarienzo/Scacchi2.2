package model;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Check;


/**
 * Board costructor.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Board {
	
	/**
	 * Matrix of pedines; it's our board
	 */
	private Pedine[][] board;
	/**
	 * Boolean array that tells which king is in check. 0 for white, 1 for black
	 */
	public boolean[] check; //0=white, 1=black
	/**
	 * If true, the game is over
	 */
	public boolean checkmate;
	//creating 2 lists for all the pieces; Neri=black, Bianchi=White
	/**
	 * ArrayList that contains all black pieces
	 */
	public ArrayList<Pedine> Neri;
	/**
	 * ArrayList that contains all white pieces
	 */
	public ArrayList<Pedine> Bianchi;
	
	/**
	 * Constructor of the board.
	 * Board is a 8x8 matrix.
	 * Check and checkmate are initialized to false.
	 */
	public Board (){
		
		this.board = new Pedine [8][8];
		this.check = new boolean[2];
		this.checkmate = false;
		this.Neri = new ArrayList<Pedine>();
		this.Bianchi = new ArrayList<Pedine>();
		
	}
	
	/**
	 * @return Return the matrix
	 */
	public Pedine[][] getBoard(){
		return this.board;
	}
	
	/**
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param pezzo Type of piece
	 * @param colore Colour of pedine
	 */
	public void setBoard(int x, int y, Piece pezzo, Colour colore) {
		
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
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @return Return the pedine in position[x][y]
	 */
	public Pedine getPedine(int x, int y){
		return this.board[x][y];
	}
	
	/**
	 * Set a new pedine in the board. If the pedine is a pawn and reach the end, provides to change pedine
	 * @param pedina Pedine to set
	 * @param newX New coordinate x of the pedine
	 * @param newY new coordinate y of the pedine
	 */
	public void setPedine(Pedine pedina, int newX, int newY){

		int x = pedina.getX();
		int y = pedina.getY();
		Colour colorePedina = pedina.getColour();
		
		//Removing captured pedine from the right list
		if (board[newX][newY].getPiece() != Piece.V){
			if (colorePedina == Colour.Bianco)
				this.Bianchi.remove(board[newX][newY]);
			else
				this.Neri.remove(board[newX][newY]);
		}
		
		//no need to check the color; the only pawn
		//who reach x=0 is white and
		//the only one that reach x=7 is black
		if ( (pedina.getPiece() == Piece.p || pedina.getPiece() == Piece.P) &&
			 (newX == 0 || newX == 7) ) {
			Scanner scan = new Scanner(System.in);
			boolean scelto=false;
			//the output is out of the while cycle because there are 2 cases
			//where the request is made, so I don't repeat it.
			System.out.print("Il pedone ha raggiunto il bordo! Con quale pedina si desidera sostituirla? Inserire nome: ");
			
			while(!scelto){
				
				String scelta = scan.nextLine();
				
				switch (scelta.toUpperCase()){ //I transpose all the letters of the string in uppercase to match every input
				case("REGINA"):
					if (colorePedina == Colour.Bianco){
						setBoard(newX, newY, Piece.q, colorePedina);
						this.Bianchi.add(this.getPedine(newX, newY));
						this.Bianchi.remove(pedina);
					}
					else{
						setBoard(newX, newY, Piece.Q, colorePedina);
						this.Neri.add(this.getPedine(newX, newY));
						this.Neri.remove(pedina);
					}
					scelto=true;
					break;
				case("TORRE"):
					if (colorePedina == Colour.Bianco){
						setBoard(newX, newY, Piece.t, colorePedina);
						this.Bianchi.add(this.getPedine(newX, newY));
						this.Bianchi.remove(pedina);
					}
					else{
						setBoard(newX, newY, Piece.T, colorePedina);
						this.Neri.add(this.getPedine(newX, newY));
						this.Neri.remove(pedina);
					}
					scelto=true;
					break;
				case("ALFIERE"):
					if (colorePedina == Colour.Bianco){
						setBoard(newX, newY, Piece.a, colorePedina);
						this.Bianchi.add(this.getPedine(newX, newY));
						this.Bianchi.remove(pedina);
					}
					else{
						setBoard(newX, newY, Piece.A, colorePedina);
						this.Neri.add(this.getPedine(newX, newY));
						this.Neri.remove(pedina);
					}
					scelto=true;
					break;
				case("CAVALLO"):
					if (colorePedina == Colour.Bianco){
						setBoard(newX, newY, Piece.c, colorePedina);
						this.Bianchi.add(this.getPedine(newX, newY));
						this.Bianchi.remove(pedina);
					}
					else{
						setBoard(newX, newY, Piece.C, colorePedina);
						this.Neri.add(this.getPedine(newX, newY));
						this.Neri.remove(pedina);
					}
					scelto=true;
					break;
				case("RE"):
					System.out.println("Un nuovo re e' sceso in campo! Colpo di Stato! AARGH, il nuovo pretendente viene brutalmente massacrato! Un suo vassallo cambia bandiera! Che ruolo vuoi che ricopra? ");
					break;
				default:
					System.out.println("Non ho capito che pedina hai richiesto");
					System.out.print("Per favore riprova (scelte possibili: alfiere, cavallo, torre, regina): ");
					
				}
			}
		}
		
		else{
			//Setting x & y of the piece
			pedina.setX(newX);
			pedina.setY(newY);
			//updating board
			this.board[newX][newY] = pedina;
		}

		this.board[x][y] = new Void(x, y); //Free old pos
		
	}
	
	/**
	 * Function that checks if the move is allowed. if it is, it set the movement and set check and checkmate.
	 * @param scacchiera Matrix on which I am working
	 * @param pedinaMossa Pedine that has to be moved
	 * @param newX Coordinate x where the pedine has to be moved
	 * @param newY Coordinate y where the pedine has to be moved
	 * @return Returns if the move was done or not
	 */
	public boolean Move(Board scacchiera, Pedine pedinaMossa, int newX, int newY){
		
		boolean set = false; //Need to know if setting is done for restoring ghost boolean
		Colour colorePedina = pedinaMossa.getColour();
		//Restoring check variable of board. If the move isn't allowed, the turn doesn't change so the check variable is restored
		if (colorePedina == Colour.Bianco)
			scacchiera.check[0] = false;
		else
			scacchiera.check[1] = false;
		
		//Check if movement for that pedine is allowed (rook move as rook (etc), and the destination isn't occupied by me)
		if ( Check.selectCheck(scacchiera, pedinaMossa, newX, newY) ){
			
			//AT THIS POINT I KNOW THAT THE PEDINE IS ALLOWED TO DO THAT MOVE;
			//I HAVE TO CHECK IF THIS BEHAVIOR ENDANGERS MY KING
			//AND I HAVE TO SET CHECK OR CHECKMATE
			
			Piece pezzo = pedinaMossa.getPiece();
			
			if (pezzo != Piece.k && pezzo != Piece.K){
				pedinaMossa.setGhost(true); //My pedine is now a ghost, no one can see it (king excluded)
			}
			
			if (scacchiera.getPedine(newX,  newY).getPiece() != Piece.V) //on the target there is a pedine (obviously is not allied)
				scacchiera.getPedine(newX,  newY).setGhost(true); //setting ghost true of pedine's target
			
			//I control if I don't put my king in check
			if ( Check.check(scacchiera, pedinaMossa, newX, newY) ){
				this.setPedine(pedinaMossa, newX, newY);
				set = true;
			}
			else{
				System.out.println("Errore, mossa non valida. Il tuo re non puo' essere in scacco!");
				return false;
			}
			
			//I restore the ghost boolean
			pedinaMossa.setGhost(false); //If is king, nothing change
			if (!set) //if set=true, I don't have to set the ghost bool of pedine target because i ate that pedine
				scacchiera.getPedine(newX, newY).setGhost(false); //restoring ghost boolean of target pedine
			
			//looking for checkmate
			//First, I set the check. If check==true, I look for checkmate
			Check.setCheck(scacchiera, pedinaMossa);
			
			if (colorePedina == Colour.Bianco){
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
			System.out.println("Errore, mossa non valida.");
		
		return false;
		
	}	
	
	@Override
	public String toString(){
		
		//TODO
		//funzione che serve per stampare la scacchiera
		//INUTILE UNA VOLTA SVILUPPATA GUI
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
	}	

}
