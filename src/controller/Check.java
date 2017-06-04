package controller;

import java.util.Iterator;

import model.Board;
import model.Colour;
import model.Chessman;
import model.Piece;

/**
 * Contains all the functions that check the movements.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class Check {
	//all functions in this class are booleans
	//that return if the movement (from x, y to newX, newY) is allowed.
	
	/**
	 * Select the right function to call, depending on the piece type of the chessman.
	 * @param scacchiera Matrix on which working
	 * @param pedina Chessman to move
	 * @param newX New coordinate x of the chessman
	 * @param newY New coordinate y of the chessman
	 * @return Return the result of the proper function, associated with the chessman,
	 *  which says if the movement is allowed
	 */
	public static boolean selectCheck (Board scacchiera, Chessman pedina, int newX, int newY){
		
		//call the properly function depending on which chessman is moved
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
			//Never occurs, because V pieces are not white or black so this function is never called
			return false;
		}
		
		return false;
	}
	
	/**
	 * Function that checks if the pawn is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param pawn Chessman to move, in this function is always the pawn
	 * @param newX New coordinate x of the chessman
	 * @param newY New coordinate y of the chessman
	 * @return Return if the movement of that pawn is allowed
	 */
	public static boolean pawn(Board scacchiera, Chessman pawn, int newX, int newY){
		
		int x = pawn.getX();
		int y = pawn.getY();
		Colour colore = pawn.getColour();
		Chessman altraPedina = scacchiera.getChessman(newX, newY);

		if ( (colore == Colour.bianco && newX == x - 1) //if white can only go up
			|| (colore == Colour.nero && newX == x + 1)) //if black can only go down
		{
			if ( newY == y && (altraPedina.getPiece() == Piece.V || altraPedina.getGhost() == true) )//if ghost=true I ignore that chessman
				//if front box is free and i'm moving there
				return true;
			//diagonal chessmen cannot be ghosts
			else if	( (colore == Colour.bianco && (newX == x - 1 && (newY == y + 1 || newY == y - 1)) && altraPedina.getColour() == Colour.nero)
					|| (colore == Colour.nero && (newX == x + 1 && (newY == y + 1 || newY == y - 1)) && altraPedina.getColour() == Colour.bianco) )
				//if new coord is diagonal, then there must be opposite color
				return true; //can capture
		}
		return false;
	}
	
	/**
	 * Function that checks if the king is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param king Chessman to move, in this function is always the king
	 * @param newX New coordinate x of the chessman
	 * @param newY New coordinate y of the chessman
	 * @return Return if the movement of that king is allowed
	 */
	public static boolean king(Board scacchiera, Chessman king, int newX, int newY) {
		
		int x = king.getX();
		int y = king.getY();
		Chessman altraPedina = scacchiera.getChessman(newX, newY);
		
		if (altraPedina.getPiece() == Piece.V || altraPedina.getColour() != king.getColour())
			//if destination is occupied by my ally, I exit and don't proceed in the if body
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
	 * Function that checks if the bishop is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param bishop Chessman to move, in this function is always the bishop
	 * @param newX New coordinate x of the chessman
	 * @param newY New coordinate y of the chessman
	 * @return Return if the movement of that bishop is allowed
	 */
	public static boolean bishop(Board scacchiera, Chessman bishop, int newX, int newY) {

		int x = bishop.getX();
		int y = bishop.getY();
		
		if (scacchiera.getChessman(newX, newY).getPiece() == Piece.V || scacchiera.getChessman(newX, newY).getColour() != bishop.getColour()){
		
			//I check if movement on x is the same on y;
			//if it is, then the movement is diagonal.
			//I don't consider newX,newY are the same as x,y, my starting point
			if ( (Math.abs(x - newX) == Math.abs(y - newY)) && (newX != x && newY != y) ){
				if (newX < x){//go up
					if (newY > y){//go right
						int tmp = y + 1;
						for (int pos = x - 1; pos > newX; pos--){
							if (scacchiera.getChessman(pos, tmp).getPiece() != Piece.V && scacchiera.getChessman(pos, tmp).getGhost() == false)
							//encountering a piece (not ghost) on the path and terminating
								return false;
							tmp++;
						}
					}
					else{ //go left
						int tmp = y - 1;
						for (int pos= x - 1; pos > newX; pos--){
							if (scacchiera.getChessman(pos, tmp).getPiece() != Piece.V && scacchiera.getChessman(pos, tmp).getGhost() == false)
								return false;
							tmp--;
						}
					}
				}
				else {//go down
					if (newY > y){//go right
						int tmp = y + 1;
						for (int pos= x + 1; pos < newX; pos++){
							if (scacchiera.getChessman(pos, tmp).getPiece() != Piece.V && scacchiera.getChessman(pos, tmp).getGhost() == false)
								return false;
							tmp++;
						}
					}
					else{//go left
						int tmp = y - 1;
						for (int pos= x + 1; pos < newX; pos++){
							if (scacchiera.getChessman(pos, tmp).getPiece() != Piece.V && scacchiera.getChessman(pos,  tmp).getGhost() == false)
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
	 * Function that checks if the knight is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param knight Chessman to move, in this function is always the knight
	 * @param newX New coordinate x of the chessman
	 * @param newY New coordinate y of the chessman
	 * @return Return if the movement of that knight is allowed
	 */
	public static boolean knight(Board scacchiera, Chessman knight, int newX, int newY){
		
		int x = knight.getX();
		int y = knight.getY();
		
		if ( scacchiera.getChessman(newX, newY).getPiece() == Piece.V || scacchiera.getChessman(newX, newY).getColour() != knight.getColour() )
			if ( (x-newX==2 && (y-newY==-1 || y-newY==1)) ||
					 (x-newX==-2 && (y-newY==-1 || y-newY==1)) ||
					 (y-newY==2 && (x-newX==-1 || x-newX==1)) ||
					 (y-newY==-2 && (x-newX==-1 || x-newX==1)) )//checking if movement allowed
				return true;
		return false;
	}

	/**
	 * Function that checks if the rook is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param rook Chessman to move, in this function is always the rook
	 * @param newX New coordinate x of the chessman
	 * @param newY New coordinate y of the chessman
	 * @return Return if the movement of that rook is allowed
	 */
	public static boolean rook(Board scacchiera, Chessman rook, int newX, int newY) {
		
		int x = rook.getX();
		int y = rook.getY();
		
		if (scacchiera.getChessman(newX, newY).getPiece() == Piece.V || scacchiera.getChessman(newX, newY).getColour() != rook.getColour() ){
			if ( (newX != x && newY != y) || (newX == x && newY == y) ){
				//it means that it didn't move in "cross"
				//and changed position
				return false;
			}
			//if in the new position there is something (that isn't ghost)
			else if (newY == y) {//vertical check, if there is something between rook and destination, exit
				if ( x > newX ){//I'm moving upwards
					for (int tmp = x - 1; tmp != newX; tmp--)
						if (scacchiera.getChessman(tmp, y).getPiece() != Piece.V && scacchiera.getChessman(tmp, y).getGhost() == false)
							return false;
				}
				else {//I'm moving backwards
					for (int tmp = x + 1; tmp != newX; tmp++)
						if (scacchiera.getChessman(tmp, y).getPiece() != Piece.V && scacchiera.getChessman(tmp, y).getGhost() == false)
							return false;
				}
			}
			else if (newX == x) {//horizontal check, if there is something between rook and destination, exit
				if ( y > newY ){//I'm moving left
					for (int tmp = y - 1; tmp != newY; tmp--)
						if (scacchiera.getChessman(x, tmp).getPiece() != Piece.V && scacchiera.getChessman(x, tmp).getGhost() == false)
							return false;
				}
				else { //I'm moving right
					for (int tmp = y + 1; tmp != newY; tmp++)
					if (scacchiera.getChessman(x, tmp).getPiece() != Piece.V && scacchiera.getChessman(x, tmp).getGhost() == false)
						return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Function that checks if the queen is allowed to move.
	 * @param scacchiera Matrix on which working
	 * @param queen Chessman to move, in this function is always the queen
	 * @param newX New coordinate x of the chessman
	 * @param newY New coordinate y of the chessman
	 * @return Return if the movement of that queen is allowed
	 */
	public static boolean queen(Board scacchiera, Chessman queen, int newX, int newY) {
		
		//Queen moves as a rook or as a bishop
		if (bishop(scacchiera, queen, newX, newY) || rook(scacchiera, queen, newX, newY) )
			return true;
		else
			return false;		
	}
	
	/**
	 * Function that checks if the movement of a chessman doesn't jeopardize the same side's king.
	 * @param scacchiera Matrix on which working
	 * @param pedinaMossa Chessman which movement has to be controlled
	 * @param newX Coordinate x target
	 * @param newY Coordinate y target
	 * @return Return if the move is allowed or not
	 */
	public static boolean check(Board scacchiera, Chessman pedinaMossa, int newX, int newY){
		//This function check if pedinaMossa jeopardize my king (pedinaMossa king)
		
		Iterator<Chessman> otherIter;
		Colour myColour = pedinaMossa.getColour();
		int xKingM = 0, yKingM = 0;//My king
		boolean moveAllowed = true;
		boolean existPawn = false;
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
		
		//I check, for every opposite chessman,
		//if my king is in check
		while (otherIter.hasNext()) {
			
			Chessman candidato = otherIter.next();
			Piece pC = candidato.getPiece();//pC stands for pezzoCandidato
						
			if (candidato.getGhost() != true){
				if (!isKing){//if I am the king, the array list is not updated if I want to move; so the next code is for every other piece	
					if (Check.selectCheck(scacchiera, candidato, xKingM, yKingM)){
						if (pC==Piece.T || pC==Piece.t || pC==Piece.A || pC==Piece.a || pC==Piece.Q || pC==Piece.q){
							if(!checkPath(scacchiera, candidato, pedinaMossa, newX, newY)){//Checking if the piece pedinaMossa will block the path
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
						if (myColour == Colour.bianco){
							//my king is white, I have to check black pawns
							if (newY-1>=0){//there is space to the right, checking
								if (scacchiera.getChessman(newX-1, newY+1).getPiece() == Piece.P){//there is a pawn that can capture me if I go in the new position
									existPawn = true;
									break;
								}
							}
							if (newY+1<=7){//there is space to the left, checking
								if (scacchiera.getChessman(newX-1, newY-1).getPiece() == Piece.P){//there is a pawn that can capture me if I go in the new position
									existPawn = true;
									break;
								}
							}
						}
						else{
						//my king is black, I have to check white pawns
							if (newY-1>=0){//there is space to the right, checking
								if (scacchiera.getChessman(newX+1, newY+1).getPiece() == Piece.p){//there is a pawn that can capture me if I go in the new position
									existPawn = true;
									break;
								}
							}
							if (newY+1<=7){//there is space to the left, checking
								if (scacchiera.getChessman(newX+1, newY-1).getPiece() == Piece.p){//there is a pawn that can capture me if I go in the new position
									existPawn = true;
									break;
								}
							}							
						}
					}
					else if (Check.selectCheck(scacchiera, candidato, newX, newY)){
						moveAllowed = false;
						break;
					}
				}
			}//ignore chessman if ghost
		}
		
		if (existPawn){
			return false;
		}
		else
			return moveAllowed;
	}
	
	/**
	 * Function that checks if the indicated chessman (pedinaMossa) blocks the path of the other chessman (candidato).
	 * @param scacchiera Matrix on which working
	 * @param candidato Chessman which movement has to be controlled
	 * @param pedinaMossa Chessman of reference
	 * @param newX Coordinate x target
	 * @param newY Coordinate y target
	 * @return Return if the chessman is on the path or not
	 */
	private static boolean checkPath(Board scacchiera, Chessman candidato, Chessman pedinaMossa, int newX, int newY) {
		
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
		
		if (cx==xKingM){//Capture in row
			if (yKingM > cy){//king is at right
				for (int i=1; cy+i<yKingM; i++){
					if (newY==cy+i && newX==cx)//then pedinaMossa is on path
						return true;
				}
			}
			else{//king is at left
				for (int i=-1; cy+i<yKingM; i--){
					if (newY==cy+i && newX==cx)//then pedinaMossa is on path
						return true;
				}
			}
		}
		else if (cy==yKingM){//Capture in column
			if (xKingM > cx){//king is below
				for (int i=1; cx+i<xKingM; i++){
					if (newX==cx+i && newY==cy)//then pedinaMossa is on path
						return true;
				}
			}
			else{//king is above
				for (int i=-1; cx+i>xKingM; i--){
					if (newX==cx+i && newY==cy)//then pedinaMossa is on path
						return true;
				}
			}
		}
		else{//Capture diagonally
			for (int i=1; cx+i<xKingM && cy-i>yKingM; i++){//king is in the lower left
				if (newX==cx+i && newY==cy-i)
					return true;
			}
			for (int i=1; cx+i<xKingM && cy+i<yKingM; i++){//king is in the lower right
				if (newX==cx+i && newY==cy+i)
					return true;
			}
			for (int i=1; cx-i>xKingM && cy-i>yKingM; i++){//king is in the upper left
				if (newX==cx-i && newY==cy-i)
					return true;
			}
			for (int i=1; cx-i>xKingM && cy+i<yKingM; i++){//king is in the upper right
				if (newX==cx-i && newY==cy+i)
					return true;
			}
		}
		
		return false;
	}

	/**
	 * Set the check variable of board.
	 * @param scacchiera Matrix which I am working on
	 * @param pedinaMossa Chessman that just moved
	 */
	public static void setCheck(Board scacchiera, Chessman pedinaMossa){
		//Called after pedinaMossa is moved. So I control if the enemy king is in check
		
		Iterator<Chessman> mineIter;
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
			Chessman next = mineIter.next();
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
 * @param pedinaMossa Piece moved
 * @param pedinaMossa chessman moved
 * @param myColour I used the colour for having some references (me, enemy)
 */
	public static void checkmate(Board scacchiera, Chessman pedinaMossa, Colour myColour){
		
		Iterator<Chessman> mineIter, otherIter;
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
		
		Chessman reAvversario = scacchiera.getChessman(xKingO, yKingO);
		
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
						Chessman candidato = mineIter.next();
						if (candidato.getPiece() == Piece.k || candidato.getPiece() == Piece.K)
							continue;
						else if (candidato.getPiece() == Piece.p || candidato.getPiece() == Piece.P){
							if (myColour == Colour.bianco){ //black pawn case
								if( (yKingO+j)-1 <0){
									if((scacchiera.getChessman((xKingO+i)-1, (yKingO+j)+1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
										safeBox = false;
										break;
									}
								}
								else if( (yKingO+j)+1>7){
									if((scacchiera.getChessman((xKingO+i)-1, (yKingO+j)-1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
										safeBox = false;
										break;
									}
								}
								else{
									if((yKingO+j)-1<0){
										if((scacchiera.getChessman((xKingO+i)-1, (yKingO+j)+1).getPiece() == Piece.P) || (scacchiera.getChessman((xKingO+i)-1, (yKingO+j)-1).getPiece() == Piece.P)){//there is a pawn that can capture me if I go in the new position
											safeBox = false;
											break;
										}
									}
								}
							}
							else{
								if((yKingO+j)-1<0){
									if((scacchiera.getChessman((xKingO+i)+1, (yKingO+j)+1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
										safeBox = false;
										break;
									}
								}
								else if((yKingO+j)+1>7){
									if((scacchiera.getChessman((xKingO+i)+1, (yKingO+j)-1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
										safeBox = false;
										break;
									}
								}
								else{
									if((yKingO+j)-1<0){
										if((scacchiera.getChessman((xKingO+i)+1, (yKingO+j)+1).getPiece() == Piece.p) || (scacchiera.getChessman((xKingO+i)+1, (yKingO+j)-1).getPiece() == Piece.p)){//there is a pawn that can capture me if I go in the new position
											safeBox = false;
											break;
										}
									}
								}							
							}
						}
						else if (Check.selectCheck(scacchiera, candidato, xKingO+i, yKingO+j)){
							//One of my chessman can go in that box, so it's not safe anymore for the king
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
			//looking for one of enemy's chessman that can capture the chessman I moved
			int xTarget = pedinaMossa.getX();
			int yTarget = pedinaMossa.getY();
			boolean set=false;
			
			while (otherIter.hasNext()) {				
				Chessman candidato = otherIter.next();
				
				if (Check.selectCheck(scacchiera, candidato, xTarget, yTarget)){
					set = true;
					scacchiera.setCheckmate(false);
					break;
				}
			}
			
			/* The king can't go anywhere;
			* There aren't chessmen that can capture my chessman.
			* The last case is when there is a chessman
			* that can avoid checkmate interfering
			* in the path of my piece that puts in checkmate the king.
			*/
			if (!set){
				//That case is possible only if my chessman is:
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
						
						if (yPedina==yKingO && xPedina < xKingO){//same column, chessman OVER king
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
						else if (yPedina==yKingO && xPedina > xKingO){//same column, chessman UNDER king
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
						else if (xPedina==xKingO && yPedina < yKingO){//same row, chessman on the LEFT of king
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
						else if (xPedina==xKingO && yPedina > yKingO){//same row, chessman to the RIGHT of the king
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
						
						if ((xPedina<xKingO) && (yPedina>yKingO)){//chessman in the UPPER RIGHT of the king
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
						else if((xPedina<xKingO) && (yPedina<yKingO)){//chessman in the UPPER LEFT of the king
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
						else if((xPedina>xKingO) && (yPedina>yKingO)){//chessman in LOWER RIGHT of the king
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
						else if((xPedina>xKingO) && (yPedina<yKingO)){//chessman in LOWER LEFT of the king
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

				if (exist)//I found a chessman that can interfere, so there isn't checkmate
					scacchiera.setCheckmate(false);
			}
		}
		
	}
	
}//end class