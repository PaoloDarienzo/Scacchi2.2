package test;

import static org.junit.Assert.*;
import org.junit.Test;

import controller.Configuration;
import model.*;

/**
 * Class which tests the check and checkmate variables.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class CheckmateTest {

	/**
	 * Testing checkmate with Fool's mate scheme.
	 */
	@Test
	public void FoolsMate() {
		/*
		 * Moving the pieces according to the scheme " fool's mate",
		 * and checking if check and checkmate are true.
		 * White king in checkmate
		 */
		
		Board scacchiera = new Board();
		Configuration.Initialization(scacchiera);
		
		//Moving the pedines
		scacchiera.Move(scacchiera, scacchiera.getPedine(6, 5), 5, 5);
		scacchiera.Move(scacchiera, scacchiera.getPedine(1, 4), 2, 4);
		scacchiera.Move(scacchiera, scacchiera.getPedine(6, 6), 5, 6);
		scacchiera.Move(scacchiera, scacchiera.getPedine(2, 4), 3, 4);
		scacchiera.Move(scacchiera, scacchiera.getPedine(5, 6), 4, 6);
		scacchiera.Move(scacchiera, scacchiera.getPedine(0, 3), 4, 7);
		
		assertFalse(scacchiera.getCheck(1));
		assertTrue(scacchiera.getCheck(0));
		assertTrue(scacchiera.getCheckmate());
		
	}
	
	/**
	 * Testing check variable with a simple scheme where the king is in check and can be avoided by interrupting the path of the enemy.
	 */
	@Test
	public void simpleCheck(){
		
		Board scacchiera = new Board();
		Configuration.Initialization(scacchiera);
		
		
		scacchiera.Move(scacchiera, scacchiera.getPedine(6, 4), 5, 4);
		scacchiera.Move(scacchiera, scacchiera.getPedine(5, 4), 4, 4);
		scacchiera.Move(scacchiera, scacchiera.getPedine(4, 4), 3, 4);
		scacchiera.Move(scacchiera, scacchiera.getPedine(3, 4), 2, 4);
		scacchiera.Move(scacchiera, scacchiera.getPedine(1, 3), 2, 4);
		scacchiera.Move(scacchiera, scacchiera.getPedine(0, 3), 3, 3);
		scacchiera.Move(scacchiera, scacchiera.getPedine(3, 3), 3, 4);
		
		assertFalse(scacchiera.getCheck(1));
		assertTrue(scacchiera.getCheck(0));
		assertFalse(scacchiera.getCheckmate());
		
	}

}
