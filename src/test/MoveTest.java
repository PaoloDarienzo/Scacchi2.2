package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import controller.Configuration;
import model.Board;

/**
 * Class that will test the movements of knights and queens.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class MoveTest {

	/**
	 * Testing the movements of the knights.
	 */
	@Test
	public void knight() {
		/* Testing a set of 3 moves on Black and White knights:
		* Moving Black knight:
		* 0,1 to 2,2
		* 2,2 to 4,1
		* 4,1 to 6,0
		* Moving White knight:
		* 7,1 to 5,2
		* 5,2 to 4,4
		* 4,4 to 5,6
		*
		* scacchiera2 will have knights in their final positions (6,0 and 5,6)
		* 
		*/
		
		Board scacchiera1 = new Board();
		Board scacchiera2 = new Board();
		Configuration.initialization(scacchiera1);
		InitTest.knight(scacchiera2);
		
		//black
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(0, 1), 2, 2);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(2, 2), 4, 1);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(4, 1), 6, 0);
		//white
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(7, 1), 5, 2);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(5, 2), 4, 4);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(4, 4), 5, 6);
		
		//Checking x, y, piece type and colour of black knight
		assertEquals(scacchiera2.getPedine(6, 0).getX(), scacchiera1.getPedine(6, 0).getX());
		assertEquals(scacchiera2.getPedine(6, 0).getY(), scacchiera1.getPedine(6, 0).getY());
		assertEquals(scacchiera2.getPedine(6, 0).getPiece(), scacchiera1.getPedine(6, 0).getPiece());
		assertEquals(scacchiera2.getPedine(6, 0).getColour(), scacchiera1.getPedine(6, 0).getColour());
		
		//Checking x, y, piece type and colour of white knight
		assertEquals(scacchiera2.getPedine(5, 6).getX(), scacchiera1.getPedine(5, 6).getX());
		assertEquals(scacchiera2.getPedine(5, 6).getY(), scacchiera1.getPedine(5, 6).getY());
		assertEquals(scacchiera2.getPedine(5, 6).getPiece(), scacchiera1.getPedine(5, 6).getPiece());
		assertEquals(scacchiera2.getPedine(5, 6).getColour(), scacchiera1.getPedine(5, 6).getColour());
		
		//Checking the void pedine left behind from starting point, black side
		assertEquals(scacchiera2.getPedine(0, 1).getX(), scacchiera1.getPedine(0, 1).getX());
		assertEquals(scacchiera2.getPedine(0, 1).getY(), scacchiera1.getPedine(0, 1).getY());
		assertEquals(scacchiera2.getPedine(0, 1).getPiece(), scacchiera1.getPedine(0, 1).getPiece());
		assertEquals(scacchiera2.getPedine(0, 1).getColour(), scacchiera1.getPedine(0, 1).getColour());
		
		//Checking the void pedine left behind from starting point, black side
		assertEquals(scacchiera2.getPedine(7, 1).getX(), scacchiera1.getPedine(7, 1).getX());
		assertEquals(scacchiera2.getPedine(7, 1).getY(), scacchiera1.getPedine(7, 1).getY());
		assertEquals(scacchiera2.getPedine(7, 1).getPiece(), scacchiera1.getPedine(7, 1).getPiece());
		assertEquals(scacchiera2.getPedine(7, 1).getColour(), scacchiera1.getPedine(7, 1).getColour());
		
	}

	/**
	 * Testing the movements of the queens.
	 */
	@Test
	public void queen(){
		/* The Queen uses both rook and bishop movement.
		* I free the road of the queens,
		* then I move both queens forward one square, diagonally three squares,
		* and back two squares:
		* Black side:
		* 1,3 to 2,3 (pawn)
		* 0,3 to 1,3
		* 1,3 to 4,6
		* 4,6 to 2,6
		* White side:
		* 6,3 to 5,3 (pawn)
		* 7,3 to 6,3
		* 6,3 to 3,0
		* 3,0 to 5,0
		* 
		* Scacchiera2 will have final positions
		* 
		*/
		
		Board scacchiera1 = new Board();
		Board scacchiera2 = new Board();
		Configuration.initialization(scacchiera1);
		InitTest.queen(scacchiera2);
		
		//black
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(1, 3), 2, 3);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(0, 3), 1, 3);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(1, 3), 4, 6);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(4, 6), 2, 6);
		//white
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(6, 3), 5, 3);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(7, 3), 6, 3);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(6, 3), 3, 0);
		scacchiera1.move(scacchiera1, scacchiera1.getPedine(3, 0), 5, 0);
		
		//Checking x, y, piece type and colour of black queen
		assertEquals(scacchiera2.getPedine(2, 6).getX(), scacchiera1.getPedine(2, 6).getX());
		assertEquals(scacchiera2.getPedine(2, 6).getY(), scacchiera1.getPedine(2, 6).getY());
		assertEquals(scacchiera2.getPedine(2, 6).getPiece(), scacchiera1.getPedine(2, 6).getPiece());
		assertEquals(scacchiera2.getPedine(2, 6).getColour(), scacchiera1.getPedine(2, 6).getColour());
				
		//Checking x, y, piece type and colour of white queen
		assertEquals(scacchiera2.getPedine(5, 0).getX(), scacchiera1.getPedine(5, 0).getX());
		assertEquals(scacchiera2.getPedine(5, 0).getY(), scacchiera1.getPedine(5, 0).getY());
		assertEquals(scacchiera2.getPedine(5, 0).getPiece(), scacchiera1.getPedine(5, 0).getPiece());
		assertEquals(scacchiera2.getPedine(5, 0).getColour(), scacchiera1.getPedine(5, 0).getColour());
		
		//Checking the void pedine left behind from starting point, black side
		assertEquals(scacchiera2.getPedine(0, 3).getX(), scacchiera1.getPedine(0, 3).getX());
		assertEquals(scacchiera2.getPedine(0, 3).getY(), scacchiera1.getPedine(0, 3).getY());
		assertEquals(scacchiera2.getPedine(0, 3).getPiece(), scacchiera1.getPedine(0, 3).getPiece());
		assertEquals(scacchiera2.getPedine(0, 3).getColour(), scacchiera1.getPedine(0, 3).getColour());
				
		//Checking the void pedine left behind from starting point, black side
		assertEquals(scacchiera2.getPedine(7, 3).getX(), scacchiera1.getPedine(7, 3).getX());
		assertEquals(scacchiera2.getPedine(7, 3).getY(), scacchiera1.getPedine(7, 3).getY());
		assertEquals(scacchiera2.getPedine(7, 3).getPiece(), scacchiera1.getPedine(7, 3).getPiece());
		assertEquals(scacchiera2.getPedine(7, 3).getColour(), scacchiera1.getPedine(7, 3).getColour());
		
	}
	
}