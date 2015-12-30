package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.Board;
import domain.HollandTunnel;
import domain.Player;

public class HollandTunnelTest {

	@Test
	public void squareActionTest() {
		//This method tests if the representation invariant of the HollandTunnel
		//class holds, then sets the destination of the HollanTunnel to another 
		//and checks if the user is correctly transported to the destination square.
		HollandTunnel start = (HollandTunnel) Board.getInstance().getSquareById(57); // Id 57 = HollandTunnel
		HollandTunnel end = (HollandTunnel) Board.getInstance().getSquareById(75);// Id 75 = HollandTunnel
		assertTrue("Representation doesn't hold",start.repOk());
		assertTrue("Representation doesn't hold",end.repOk());
		Player testPlayer1 = new Player(0, "TestUser1", 100);
		start.squareAction(testPlayer1, Board.getInstance());
		assertTrue("Player didn't move to target location",testPlayer1.getLocation()==end);
	}

}
