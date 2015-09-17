package controller;

import java.util.Iterator;

import model.Board;
import model.Colour;
import model.Pedine;
import model.Piece;

/**
 * Contains all the functions that check the movements.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Check {
	//all functions in this class are booleans
	//which return if the movement (from x, y to newX, newY) is allowed.
	
	/**
	 * Select the right function to call, depending on the piece type of the pedine.
	 * @param scacchiera Matrix on which working
	 * @param pedina Pedine to move
	 * @param newX New coordinate x of the pedine
	 * @param newY New coordinate y of the pedine
	 * @return Return the result of the proper function, associated with the pedine,
	 *  which says if the movement is allowed
	 */
	public static boolean selectCheck (Board scacchiera, Pedine pedina, int newX, int newY){
		
		//call the properly function depending on which pedine is moved
		switch(pedina.getPiece()){
		case P:
		case p:
			return pawn(scacchiera, pedina, newX, newY);
		case C:
		case c:
			return knight(scacchiera, pedina, newX, newY);
		case A:
		case a:
			return bishop(scacchiera, pedina, newX, newY);
		case Q:
		case q:
			return queen(scacchiera, pedina, newX, newY);
		case K:
		case k:
			return king(scacchiera, pedina, newX, newY);
		case T:
		case t:
			return rook(scacchiera, pedina, newX, newY);
		case V:
			//TODO
			//through GUI is impossible to select a Void pedine: never occurs.
			System.out.println("Errore: la casella che hai selezionato e' vuota.");
			return false;
		}
		
		return false;
	}
	
	/**
	 * Function that check if the pawn is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param pawn Pedine to move, in this function is always the pawn
	 * @param newX New coordinate x of the pedine
	 * @param newY New coordinate y of the pedine
	 * @return Return if the movement of that pawn is allowed
	 */
	public static boolean pawn(Board scacchiera, Pedine pawn, int newX, int newY){
		
		int x = pawn.getX();
		int y = pawn.getY();
		Colour colore = pawn.getColour();
		Pedine altraPedina = scacchiera.getPedine(newX, newY);

		if ( (colore == Colour.bianco && newX == x - 1) //if white can only go up
			|| (colore == Colour.nero && newX == x + 1)) //if black can only go down
		{
			if ( newY == y && (altraPedina.getPiece() == Piece.V || altraPedina.getGhost() == true) )//if ghost=true I ignore that pedine
				//if front box is free and i'm moving there
				return true;
			//diagonal pedines cannot be ghosts
			else if	( (colore == Colour.bianco && (newX == x - 1 && (newY == y + 1 || newY == y - 1)) && altraPedina.getColour() == Colour.nero)
					|| (colore == Colour.nero && (newX == x + 1 && (newY == y + 1 || newY == y - 1)) && altraPedina.getColour() == Colour.bianco) )//if new coord is diagonal, then there must be opposite color
				return true; //can capture
		}
		return false;
	}
	
	/**
	 * Function that check if the king is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param king Pedine to move, in this function is always the king
	 * @param newX New coordinate x of the pedine
	 * @param newY New coordinate y of the pedine
	 * @return Return if the movement of that king is allowed
	 */
	public static boolean king(Board scacchiera, Pedine king, int newX, int newY) {
		
		int x = king.getX();
		int y = king.getY();
		Colour c = king.getColour();
		Pedine altraPedina = scacchiera.getPedine(newX, newY);
		
		if (altraPedina.getPiece() == Piece.V || altraPedina.getColour() != c)//if destination is occupied by my ally, I exit and don't proceed in the if body
		{
			if (newX == x && newY == y) //That's my spot.
				return false;
			else if ( (newX == x || newX == x + 1 || newX == x - 1) && 
					  (newY == y || newY == y + 1 || newY == y - 1) )//if new coord are allowed
				return true;
		}
		return false;
		
	}

	/**
	 * Function that check if the bishop is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param bishop Pedine to move, in this function is always the bishop
	 * @param newX New coordinate x of the pedine
	 * @param newY New coordinate y of the pedine
	 * @return Return if the movement of that bishop is allowed
	 */
	public static boolean bishop(Board scacchiera, Pedine bishop, int newX, int newY) {

		int x = bishop.getX();
		int y = bishop.getY();
		Colour c = bishop.getColour();
		
		if (scacchiera.getPedine(newX, newY).getPiece() == Piece.V || scacchiera.getPedine(newX, newY).getColour() != c){
		
			//I check if movement on x is the same on y;
			//if it is, then the movement is diagonal.
			//I don't consider newX,newY are the same as x,y, my starting point
			if ( (Math.abs(x - newX) == Math.abs(y - newY)) && (newX != x && newY != y) ){
				if (newX < x){//go up
					if (newY > y){//go right
						int tmp = y + 1;
						for (int pos = x - 1; pos > newX; pos--){
							if (scacchiera.getPedine(pos, tmp).getPiece() != Piece.V && scacchiera.getPedine(pos, tmp).getGhost() == false)//encountering a piece (not ghost) on the path and terminating
								return false;
							tmp++;
						}
					}
					else{ //go left
						int tmp = y - 1;
						for (int pos= x - 1; pos > newX; pos--){
							if (scacchiera.getPedine(pos, tmp).getPiece() != Piece.V && scacchiera.getPedine(pos, tmp).getGhost() == false)
								return false;
							tmp--;
						}
					}
				}
				else {//go down
					if (newY > y){//go right
						int tmp = y + 1;
						for (int pos= x + 1; pos < newX; pos++){
							if (scacchiera.getPedine(pos, tmp).getPiece() != Piece.V && scacchiera.getPedine(pos, tmp).getGhost() == false)
								return false;
							tmp++;
						}
					}
					else{//go left
						int tmp = y - 1;
						for (int pos= x + 1; pos < newX; pos++){
							if (scacchiera.getPedine(pos, tmp).getPiece() != Piece.V && scacchiera.getPedine(pos,  tmp).getGhost() == false)
								return false;
							tmp--;
						}
					}		
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Function that check if the knight is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param knight Pedine to move, in this function is always the knight
	 * @param newX New coordinate x of the pedine
	 * @param newY New coordinate y of the pedine
	 * @return Return if the movement of that knight is allowed
	 */
	public static boolean knight(Board scacchiera, Pedine knight, int newX, int newY){
		
		int x = knight.getX();
		int y = knight.getY();
		Colour c = knight.getColour();
		
		if ( scacchiera.getPedine(newX, newY).getPiece() == Piece.V || scacchiera.getPedine(newX, newY).getColour() != c )
			if ( (x-newX==2 && (y-newY==-1 || y-newY==1)) ||
					 (x-newX==-2 && (y-newY==-1 || y-newY==1)) ||
					 (y-newY==2 && (x-newX==-1 || x-newX==1)) ||
					 (y-newY==-2 && (x-newX==-1 || x-newX==1)) )//checking if movement allowed
				return true;
		return false;
	}

	/**
	 * Function that check if the rook is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param rook Pedine to move, in this function is always the rook
	 * @param newX New coordinate x of the pedine
	 * @param newY New coordinate y of the pedine
	 * @return Return if the movement of that rook is allowed
	 */
	public static boolean rook(Board scacchiera, Pedine rook, int newX, int newY) {
		
		int x = rook.getX();
		int y = rook.getY();
		Colour c = rook.getColour();
		
		if (scacchiera.getPedine(newX, newY).getPiece() == Piece.V || scacchiera.getPedine(newX, newY).getColour() != c ){
			if ( (newX != x && newY != y) || (newX == x && newY == y) ){
				//it means that it didn't move in "cross"
				//and changed position
				return false;
			}
			//if in the new position there is something (that isn't ghost)
			else if (newY == y) {//vertical check, if there is something between rook and destination, exit
				if ( x > newX ){//I'm moving upwards
					for (int tmp = x - 1; tmp != newX; tmp--)
						if (scacchiera.getPedine(tmp, y).getPiece() != Piece.V && scacchiera.getPedine(tmp, y).getGhost() == false)
							return false;
				}
				else {//I'm moving backwards
					for (int tmp = x + 1; tmp != newX; tmp++)
						if (scacchiera.getPedine(tmp, y).getPiece() != Piece.V && scacchiera.getPedine(tmp, y).getGhost() == false)
							return false;
				}
			}
			else if (newX == x) {//horizontal check, if there is something between rook and destination, exit
				if ( y > newY ){//I'm moving sx
					for (int tmp = y - 1; tmp != newY; tmp--)
						if (scacchiera.getPedine(x, tmp).getPiece() != Piece.V && scacchiera.getPedine(x, tmp).getGhost() == false)
							return false;
				}
				else { //I'm moving dx
					for (int tmp = y + 1; tmp != newY; tmp++)
					if (scacchiera.getPedine(x, tmp).getPiece() != Piece.V && scacchiera.getPedine(x, tmp).getGhost() == false)
						return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Function that check if the queen is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param queen Pedine to move, in this function is always the queen
	 * @param newX New coordinate x of the pedine
	 * @param newY New coordinate y of the pedine
	 * @return Return if the movement of that queen is allowed
	 */
	public static boolean queen(Board scacchiera, Pedine queen, int newX, int newY) {
		
		//Queen moves as a rook or as a bishop
		if (bishop(scacchiera, queen, newX, newY) || rook(scacchiera, queen, newX, newY) )
			return true;
		else
			return false;		
	}
	
	/**
	 * Function that check if the movement of a pedine doesn't jeopardize the same side's king.
	 * @param scacchiera Matrix on which working
	 * @param pedinaMossa Pedine which movement has to be controlled
	 * @param newX Coordinate x t\et
	 * @param newY Coordinate y target
	 * @return Return if the move is allowed or not
	 */
	public static boolean check(Board scacchiera, Pedine pedinaMossa, int newX, int newY){
		//This function check if pedinaMossa jeopardize my king (pedinaMossa king)
		
		Iterator<Pedine> otherIter;
		Colour myColour = pedinaMossa.getColour();
		int xKingM = 0, yKingM = 0;//My king
		boolean moveAllowed = true;
		boolean isKing = (pedinaMossa.getPiece() == Piece.K || pedinaMossa.getPiece() == Piece.k) ? true : false;
		
		//setting iterator
		if (myColour == Colour.bianco){
			otherIter = scacchiera.neri.iterator();
		}
		else {
			otherIter = scacchiera.bianchi.iterator();
		}
		
		//The king is the first of the iterator and it's always present.
		//So, xKing and yKing will always be set
		if (myColour == Colour.bianco){
			xKingM = scacchiera.bianchi.get(0).getX();
			yKingM = scacchiera.bianchi.get(0).getY();
		}
		else{
			xKingM = scacchiera.neri.get(0).getX();
			yKingM = scacchiera.neri.get(0).getY();
		}
		
		//I check, for every opposite pedine,
		//if my king is in check
		while (otherIter.hasNext()) {
			
			Pedine candidato = otherIter.next();
			Piece pC = candidato.getPiece();//pC stands for pezzoCandidato
						
			if (candidato.getGhost() != true){
				if (!isKing){//if I am the king, the arraylist is not updated if I want to move; so the next code is for every other piece	
					if (Check.selectCheck(scacchiera, candidato, xKingM, yKingM)){
						if (pC==Piece.T || pC==Piece.t || pC==Piece.A || pC==Piece.a || pC==Piece.Q || pC==Piece.q){
						//TODO
							if(!checkPath(scacchiera, candidato, pedinaMossa, newX, newY)){
								moveAllowed = false;
								break;
							}
						}
						else{
							moveAllowed = false;
							break;
						}			
					}
				}
				else{//if I am the king, I have to check if every candidate can go on my future coordinates
					if (pC == Piece.p || pC == Piece.P){
						if (myColour == Colour.bianco){//black pawn case
							if(newY-1<0){
								if((scacchiera.getPedine(newX-1, newY+1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
									moveAllowed = false;
									break;
								}
							}
							else if(newY+1>7){
								if((scacchiera.getPedine(newX-1, newY-1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
									moveAllowed = false;
									break;
								}
							}
							else{
								if(newY-1<0){
									if((scacchiera.getPedine(newX-1, newY+1).getPiece() == Piece.P) || (scacchiera.getPedine(newX-1, newY-1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
										moveAllowed = false;
										break;
									}
								}
							}
						}
						else{
							if(newY-1<0){
								if((scacchiera.getPedine(newX+1, newY+1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
									moveAllowed = false;
									break;
								}
							}
							else if(newY+1>7){
								if((scacchiera.getPedine(newX+1, newY-1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
									moveAllowed = false;
									break;
								}
							}
							else{
								if(newY-1<0){
									if((scacchiera.getPedine(newX+1, newY+1).getPiece() == Piece.p) || (scacchiera.getPedine(newX+1, newY-1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
										moveAllowed = false;
										break;
									}
								}
							}							
						}
					}
					else if (Check.selectCheck(scacchiera, candidato, newX, newY)){
						moveAllowed = false;
						break;
					}
				}
			}//ignore pedine if ghost
		}
		
		return moveAllowed;
	}
	
	/**
	 * Function that check if the indicated pedine (pedinaMossa) blocks the path of the other pedine (candidato).
	 * @param scacchiera Matrix on which working
	 * @param candidato Pedine which movement has to be controlled
	 * @param pedinaMossa Pedine of reference
	 * @param newX Coordinate x target
	 * @param newY Coordinate y target
	 * @return Return if the pedine is on the path or not
	 */
	private static boolean checkPath(Board scacchiera, Pedine candidato, Pedine pedinaMossa, int newX, int newY) {
		
		int cx = candidato.getX();
		int cy = candidato.getY();
		int xKingM, yKingM;
		
		if (pedinaMossa.getColour() == Colour.bianco){
			xKingM = scacchiera.bianchi.get(0).getX();
			yKingM = scacchiera.bianchi.get(0).getY();
		}
		else{
			xKingM = scacchiera.neri.get(0).getX();
			yKingM = scacchiera.neri.get(0).getY();
		}			
		
		if (cx==xKingM){//Mangia in riga
			if (yKingM > cy){//Il re e' a destra
				for (int i=1; cy+i<yKingM; i++){
					if (newY==cy+i && newX==cx)//allora la pedinaMossa e' nel path
						return true;
				}
			}
			else{//il re e' a sinistra
				for (int i=-1; cy+i<yKingM; i--){
					if (newY==cy+i && newX==cx)//allora la pedinaMossa e' nel path
						return true;
				}
			}
		}
		else if (cy==yKingM){//Mangia in colonna
			if (xKingM > cx){//Il re e' sotto
				for (int i=1; cx+i<xKingM; i++){
					if (newX==cx+i && newY==cy)//allora la pedinaMossa e' nel path
						return true;
				}
			}
			else{//il re e' sopra
				for (int i=-1; cx+i<xKingM; i--){
					if (newX==cx+i && newY==cy)//allora la pedinaMossa e' nel path
						return true;
				}
			}
		}
		else{//mangia in diagonale
			for (int i=1; cx+i<xKingM && cy-i>yKingM; i++){//il re e' in basso a sinistra
				if (newX==cx+i && newY==cy-i)
					return true;
			}
			for (int i=1; cx+i<xKingM && cy+i<yKingM; i++){//il re e' in basso a destra
				if (newX==cx+i && newY==cy+i)
					return true;
			}
			for (int i=1; cx-i>xKingM && cy-i>yKingM; i++){//il re e' in alto a sinistra
				if (newX==cx-i && newY==cy-i)
					return true;
			}
			for (int i=1; cx-i>xKingM && cy+i<yKingM; i++){//il re e' in alto a destra
				if (newX==cx-i && newY==cy+i)
					return true;
			}
		}
		
		return false;
	}

	/**
	 * Set the check variable of board.
	 * @param scacchiera Matrix which I am working on
	 * @param pedinaMossa Pedine that just moved
	 */
	public static void setCheck(Board scacchiera, Pedine pedinaMossa){
		//Called after pedinaMossa is moved. So I control if the enemy king is in check
		
		Iterator<Pedine> mineIter;
		Colour myColour = pedinaMossa.getColour();
		int xKingO = 0, yKingO = 0;//Opposite king
		
		//setting iterator
		if (myColour == Colour.bianco){
			mineIter = scacchiera.bianchi.iterator();
		}
		else {
			mineIter = scacchiera.neri.iterator();
		}
		
		//The king is the first of the iterator and it's always present.
		//So, xKing and yKing will always be set
		if (myColour == Colour.bianco){
			xKingO = scacchiera.neri.get(0).getX();
			yKingO = scacchiera.neri.get(0).getY();
		}
		else{
			xKingO = scacchiera.bianchi.get(0).getX();
			yKingO = scacchiera.bianchi.get(0).getY();
		}
		
		//I browse the list looking for someone of mine that can go on king
		while (mineIter.hasNext()) {
			Pedine next = mineIter.next();
			if (next.getPiece() != Piece.k && next.getPiece() != Piece.K){//jumping king
				if (Check.selectCheck(scacchiera, next, xKingO, yKingO)){
					if (myColour == Colour.bianco){
						scacchiera.setCheck(1, true);
						break;
					}
					else{
						scacchiera.setCheck(0, true);
						break;
					}
				}
			}
		}
	}
		
/**
 * Set the checkmate variable of board.
 * @param scacchiera Matrix which I am working on
 * @param myColour I used the colour for having some references (me, enemy)
 */
	public static void checkmate(Board scacchiera, Pedine pedinaMossa, Colour myColour){
		
		Iterator<Pedine> mineIter, otherIter;
		Colour coloreOpposto;
		int xKingO=0, yKingO=0;
		boolean safeBox = false;//Reports if there is a safe box for the king
		scacchiera.setCheckmate(true); //I suppose it is true, then I put it false if I find an above case.
		
		if (myColour == Colour.bianco){
			mineIter = scacchiera.bianchi.iterator();
			otherIter = scacchiera.neri.iterator();
			coloreOpposto= Colour.nero;
			xKingO = scacchiera.neri.get(0).getX();
			yKingO = scacchiera.neri.get(0).getY();
		}
		else {
			mineIter = scacchiera.neri.iterator();
			otherIter = scacchiera.bianchi.iterator();
			coloreOpposto = Colour.bianco;
			xKingO = scacchiera.bianchi.get(0).getX();
			yKingO = scacchiera.bianchi.get(0).getY();
		}
		
		Pedine reAvversario = scacchiera.getPedine(xKingO, yKingO);
		
		for (int i=-1; i<2 && safeBox == false; i++){
			for(int j=-1; j<2 && safeBox == false; j++){ //-1, 0 or 1
				
				if (i==0 && j==0)
					continue;
				if ( (xKingO+i<0 || xKingO+i>7) || (yKingO+j<0 || yKingO+j>7) )//I avoid to check boxes out of matrix bounds
					continue;
				if (Check.selectCheck(scacchiera, reAvversario, xKingO+i, yKingO+j)){
					//king can go in that box
					safeBox = true;
					
					while (mineIter.hasNext()) {
						Pedine candidato = mineIter.next();
						if (candidato.getPiece() == Piece.k || candidato.getPiece() == Piece.K)
							continue;
						else if (candidato.getPiece() == Piece.p || candidato.getPiece() == Piece.P){
							if (myColour == Colour.bianco){ //black pawn case
								if( (yKingO+j)-1 <0){
									if((scacchiera.getPedine((xKingO+i)-1, (yKingO+j)+1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
										safeBox = false;
										break;
									}
								}
								else if( (yKingO+j)+1>7){
									if((scacchiera.getPedine((xKingO+i)-1, (yKingO+j)-1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
										safeBox = false;
										break;
									}
								}
								else{
									if((yKingO+j)-1<0){
										if((scacchiera.getPedine((xKingO+i)-1, (yKingO+j)+1).getPiece() == Piece.P) || (scacchiera.getPedine((xKingO+i)-1, (yKingO+j)-1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
											safeBox = false;
											break;
										}
									}
								}
							}
							else{
								if((yKingO+j)-1<0){
									if((scacchiera.getPedine((xKingO+i)+1, (yKingO+j)+1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
										safeBox = false;
										break;
									}
								}
								else if((yKingO+j)+1>7){
									if((scacchiera.getPedine((xKingO+i)+1, (yKingO+j)-1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
										safeBox = false;
										break;
									}
								}
								else{
									if((yKingO+j)-1<0){
										if((scacchiera.getPedine((xKingO+i)+1, (yKingO+j)+1).getPiece() == Piece.p) || (scacchiera.getPedine((xKingO+i)+1, (yKingO+j)-1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
											safeBox = false;
											break;
										}
									}
								}							
							}
						}
						else if (Check.selectCheck(scacchiera, candidato, xKingO+i, yKingO+j)){
							//One of my pedine can go in that box, so it's safe anymore for the king
							safeBox = false;
							break;
						}
					}	
				} //changing square
			}
		}
		
		if (safeBox)
			scacchiera.setCheckmate(false);
		else{
			//looking for one of enemy's pedine that can capture the pedine I moved
			int xTarget = pedinaMossa.getX();
			int yTarget = pedinaMossa.getY();
			boolean set=false;
			
			while (otherIter.hasNext()) {				
				Pedine candidato = otherIter.next();
				
				if (Check.selectCheck(scacchiera, candidato, xTarget, yTarget)){
					set = true;
					scacchiera.setCheckmate(false);
					break;
				}
			}
			
			/* The king can't go anywhere;
			* There aren't pedines that can capture my pedine.
			* The last case is when there is a pedine
			* that can avoid checkmate interfering
			* in the path of my piece that puts in checkmate the king.
			*/
			if (!set){
				//That case is possible only if my pedine is:
				//Queen, rook or bishop.
				
				Piece pezzoPedina = pedinaMossa.getPiece();
				int xPedina = pedinaMossa.getX();
				int yPedina = pedinaMossa.getY();
				boolean exist=false;
				
				if ((pezzoPedina==Piece.Q || pezzoPedina==Piece.q) ||
					(pezzoPedina==Piece.T || pezzoPedina==Piece.t) ||
					(pezzoPedina==Piece.A || pezzoPedina==Piece.a) ){
					
					if( (pezzoPedina != Piece.A) && (pezzoPedina != Piece.a) ){
					/*
					 * If I put in checkmate the opposite king with a rook,
					 * or with a queen who didn't start moving as a bishop,
					 * I check, for every box that separates me from the king,
					 * If some enemy piece can get in the way.
					 */
						
						if (yPedina==yKingO && xPedina < xKingO){//same column, pedine OVER king
							for (int i=1; xPedina+i < xKingO; i++){//Every box in the path
								if(coloreOpposto==Colour.nero){
									for(int x=1; x < scacchiera.neri.size(); x++){//Starting from x=1 so jumping the king
										if (Check.selectCheck(scacchiera, scacchiera.neri.get(x), xPedina+i, yPedina)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								else{
									for(int x=1; x < scacchiera.bianchi.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.bianchi.get(x), xPedina+i, yPedina)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								if (exist)
									break;
							}
						}
						else if (yPedina==yKingO && xPedina > xKingO){//same column, pedine UNDER king
							for (int i=1; xPedina-i < xKingO; i++){ //Every box in the path
								if(coloreOpposto==Colour.nero){
									for(int x=1; x < scacchiera.neri.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.neri.get(x), xPedina-i, yPedina)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								else{
									for(int x=1; x < scacchiera.bianchi.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.bianchi.get(x), xPedina-i, yPedina)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								if (exist)
									break;
							}
						}
						else if (xPedina==xKingO && yPedina < yKingO){//same row, pedine on the LEFT of king
							for (int i=1; yPedina+i < yKingO; i++){ //Every box in the path
								if(coloreOpposto==Colour.nero){
									for(int x=1; x < scacchiera.neri.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.neri.get(x), xPedina, yPedina+i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								else{
									for(int x=1; x < scacchiera.bianchi.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.bianchi.get(x), xPedina, yPedina+i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								if (exist)
									break;
							}	
						}
						else if (xPedina==xKingO && yPedina > yKingO){//same row, pedine to the RIGHT of the king
							for (int i=1; yPedina-i < yKingO; i++){ //Every box in the path
								if(coloreOpposto==Colour.nero){
									for(int x=1; x < scacchiera.neri.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.neri.get(x), xPedina, yPedina-i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								else{
									for(int x=1; x < scacchiera.bianchi.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.bianchi.get(x), xPedina, yPedina-i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								if (exist)
									break;
							}
						}						
					}
					
					if( (pezzoPedina != Piece.T) && (pezzoPedina != Piece.t) ){
						/*
						 * If I put in checkmate the opposite king with a bishop,
						 * or with a queen who didn't start moving as a rook,
						 * I check, for every box that separates me from the king,
						 * If some enemy piece can get in the way.
						 */			
						
						if ((xPedina<xKingO) && (yPedina>yKingO)){//pedine in the UPPER RIGHT of the king
							for (int i=1; xPedina+i < xKingO && yPedina-i > yKingO; i++){ //Every box in the path
								if(coloreOpposto==Colour.nero){
									for(int x=1; x < scacchiera.neri.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.neri.get(x), xPedina+i, yPedina-i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								else{
									for(int x=1; x < scacchiera.bianchi.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.bianchi.get(x), xPedina+i, yPedina-i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								if (exist)
									break;
							}
						}
						else if((xPedina<xKingO) && (yPedina<yKingO)){//pedine in the UPPER LEFT of the king
							for (int i=1; xPedina+i < xKingO && yPedina+i < yKingO; i++){ //Every box in the path
								if(coloreOpposto==Colour.nero){
									for(int x=1; x < scacchiera.neri.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.neri.get(x), xPedina+i, yPedina+i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								else{
									for(int x=1; x < scacchiera.bianchi.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.bianchi.get(x), xPedina+i, yPedina+i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								if (exist)
									break;
							}
						}
						else if((xPedina>xKingO) && (yPedina>yKingO)){//pedine in LOWER RIGHT of the king
							for (int i=1; xPedina-i > xKingO && yPedina-i > yKingO; i++){ //Every box in the path
								if(coloreOpposto==Colour.nero){
									for(int x=1; x < scacchiera.neri.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.neri.get(x), xPedina+i, yPedina-i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								else{
									for(int x=1; x < scacchiera.bianchi.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.bianchi.get(x), xPedina+i, yPedina-i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								if (exist)
									break;
							}
						}
						else if((xPedina>xKingO) && (yPedina<yKingO)){//pedine in LOWER LEFT of the king
							for (int i=1; xPedina-i > xKingO && yPedina+i < yKingO; i++){ //Every box in the path
								if(coloreOpposto==Colour.nero){
									for(int x=1; x < scacchiera.neri.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.neri.get(x), xPedina-i, yPedina+i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								else{
									for(int x=1; x < scacchiera.bianchi.size(); x++){
										if (Check.selectCheck(scacchiera, scacchiera.bianchi.get(x), xPedina-i, yPedina+i)){
											//Or, exist an opponent candidate which can go
											//in one of the boxes between me and his king
											exist = true;
											break;
										}
									}
								}
								if (exist)
									break;
							}
						}						
					}
					
					else{//Enemy can't interfere
						scacchiera.setCheckmate(true);
					}
				}
				
				else{//There isn't the possibility written above
					scacchiera.setCheckmate(true);
				}

				if (exist)//I found a pedine that can interfere, so there isn't checkmate
					scacchiera.setCheckmate(false);
			}
		}
		
	}
	
}//end class