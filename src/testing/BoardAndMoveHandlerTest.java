package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Board;
import domain.ChanceCard;
import domain.CommunityCard;
import domain.Estate;
import domain.Player;
import domain.Square;
import domain.TransitStation;

/**
 * 
 * @author Ata
 *
 */
public class BoardAndMoveHandlerTest {

	static Board b;

	@Before
	public void getBoard(){
		b = Board.getInstance();
	}

	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	public void testNullBoard() {
		Board b = null;
		b.repOk();
		fail("Did not throw NullPointerException.");
	}

	@Test
	public void testGetInstance() {
		Board b = Board.getInstance();
		assertTrue("RepOk is false.", b.repOk());
	}

	@Test
	public void testSingularity() {
		Board b1 = Board.getInstance();
		Board b2 = Board.getInstance();

		assertEquals("Board.getInstance() does not return the same instance.", b1, b2);
	}

	@Test
	public void testAddAndPickChanceCards(){

		ChanceCard addedCard = new ChanceCard("TestCard", "TestCardDesc"){
			public void cardAction(){
				//Does nothing.
			}
		};

		b.addToChanceCards(addedCard);
		ChanceCard drawnCard;

		do{
			drawnCard = b.pickAChanceCard();
			assertTrue("Drawn Card is not a ChanceCard", drawnCard instanceof ChanceCard);
		}while(drawnCard != null && drawnCard != addedCard);

		assertEquals("The added card is not found", addedCard, drawnCard);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullChanceCard(){

		b.addToChanceCards(null);
		fail("Did not throw IllegalArgumentException.");
	}

	@Test
	public void testAddAndPickCommunityCards(){

		CommunityCard addedCard = new CommunityCard("TestCard", "TestCardDesc"){
			public void cardAction(){
				//Does nothing.
			}
		};

		b.addToCommunityCards(addedCard);
		CommunityCard drawnCard;

		do{
			drawnCard = b.pickACommunityCard();
			assertTrue("Drawn Card is not a CommunityCard", drawnCard instanceof CommunityCard);
		}while(drawnCard != null && drawnCard != addedCard);

		assertSame("The added card is not found", addedCard, drawnCard);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullCommunityCard(){

		b.addToCommunityCards(null);
		fail("Did not throw IllegalArgumentException.");
	}

	@Test
	public void testGetSquareById(){


		int id = 0;
		Square sq = b.getSquareById(id);
		assertEquals("Id of the returned Square is not equal to the desired id.", id, sq.getId());

		id = 62;
		sq = b.getSquareById(id);
		assertEquals("Id of the returned Square is not equal to the desired id.", id, sq.getId());

		id = -1;
		assertNull("Returned Square is not null.", b.getSquareById(id));

		id = Integer.MAX_VALUE;
		assertNull("Returned Square is not null.", b.getSquareById(id));

		id = Integer.MIN_VALUE;
		assertNull("Returned Square is not null.", b.getSquareById(id));
	}

	@Test
	public void testGetLayerNum(){


		int id = 40;
		int expectedLayer = 0;

		Square sq = b.getSquareById(id);
		assertEquals("Returned layer number is different from actual value.",
				expectedLayer, b.getLayerNum(sq));

		id = 0;
		expectedLayer = 1;
		sq = b.getSquareById(id);
		assertEquals("Returned layer number is different from expected value.",
				expectedLayer, b.getLayerNum(sq));

		id = 62;
		expectedLayer = 2;
		sq = b.getSquareById(id);
		assertEquals("Returned layer number is different from expected value.",
				expectedLayer, b.getLayerNum(sq));

		expectedLayer = -1;
		sq = null;
		assertEquals("Returned layer number is different from expected value.",
				expectedLayer, b.getLayerNum(sq));

		expectedLayer = -1;
		sq = new Square() {
			@Override
			public void squareAction(Player currentPlayer, Board board) {
				//Does Nothing.			
			}
		};
		assertEquals("Returned layer number is different from expected value.",
				expectedLayer, b.getLayerNum(sq));

	}

	@Test
	public void testGetPositionInLayer() {

		int layer = 1;
		int expectedPos = 5;
		Square sq = b.getSquareById(5); //sq = Transit Station which should be at both layer 1 and 2.
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));

		layer = 2;
		expectedPos = 7;
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));


		layer = 0;
		expectedPos = 0;
		sq = b.getSquareById(40);
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));

		layer = 1;
		expectedPos = -1;
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));

		layer = -1; //Testing with negative layer
		expectedPos = -1;
		sq = b.getSquareById(25);
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));

		layer = 0;
		expectedPos = -1;
		sq = null; // Testing with null Square
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));

		layer = 0;
		expectedPos = -1;
		sq = b.getSquareById(25); // Testing with a Square located in a different layer
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));

		layer = Integer.MAX_VALUE;
		expectedPos = -1;
		sq = b.getSquareById(25);
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));

		layer = Integer.MIN_VALUE;
		expectedPos = -1;
		sq = b.getSquareById(25);
		assertEquals("Returned layer number is different from expected.",
				expectedPos, b.getPositionInLayer(sq, layer));
	}

	@Test
	public void testGetSquares(){

		assertTrue("Result is not a Square[][]", b.getSquares() instanceof Square[][]);

		Square[][] sq1 = b.getSquares();
		Square[][] sq2 = b.getSquares();
		assertArrayEquals("Returned arrays are not equal to each other.", sq1, sq2);
		assertNotSame("Same array is returned .", sq1, sq2);
	}

	@Test
	public void testGetLayerLength(){
		
		assertEquals("Returned an unexpected length.", 24, b.getLayerLength(0));

		assertEquals("Returned an unexpected length.", 40, b.getLayerLength(1));

		assertEquals("Returned an unexpected length.", 56, b.getLayerLength(2));

		assertEquals("Returned an unexpected length.", 0, b.getLayerLength(-1));

		assertEquals("Returned an unexpected length.", 0, b.getLayerLength(3));

		assertEquals("Returned an unexpected length.", 0, b.getLayerLength(Integer.MAX_VALUE));

		assertEquals("Returned an unexpected length.", 0, b.getLayerLength(Integer.MIN_VALUE));
	}

	@Test
	public void testGetSquareAt(){
		
		// b.getSquares() is assumed to be working correctly for this test.
		Square[][] squares = b.getSquares();

		int layer = 0;
		int position = 0;
		assertEquals("Returned Square is not equal to the expected one at that location.",
				squares[layer][position], b.getSquareAt(layer, position));

		layer = 1;
		position = 5;
		assertEquals("Returned Square is not equal to the expected one at that location.",
				squares[layer][position], b.getSquareAt(layer, position));

		layer = 2;
		position = 55;
		assertEquals("Returned Square is not equal to the expected one at that location.",
				squares[layer][position], b.getSquareAt(layer, position));
	}

	@Test
	public void testGetColorArray(){

		String[] array = b.getColorArray();

		assertNotNull("Returned array is null.", array);

		Estate e = (Estate) b.getSquareById(3);
		boolean colorExists = false;
		for (int i = 0; i < array.length; i++) {
			if(e.getColor().equals(array[i]))
				colorExists = true;
		}
		assertTrue("Color Array does not contain color of an Estate in the Board.", colorExists);
	}

	@Test
	public void testGetColorAmount(){

		assertEquals("Returned color count is different from expected.",
				3, b.getColorAmount("yellow"));

		assertEquals("Returned color count is different from expected.",
				2, b.getColorAmount("blue"));

		assertEquals("Returned color count is different from expected.",
				4, b.getColorAmount("light green"));

		assertEquals("Returned color count is different from expected.",
				0, b.getColorAmount("not a color"));
	}

	@Test
	public void testAddToPool(){

		int amount = 10;
		int poolBefore = b.getPool();

		b.addToPool(amount);

		int poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				(poolBefore + amount), poolAfter);



		amount = 0;
		poolBefore = b.getPool();

		b.addToPool(amount);

		poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				(poolBefore + amount), poolAfter);



		amount = Integer.MIN_VALUE;
		poolBefore = b.getPool();

		b.addToPool(amount); // Pool should not change

		poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				poolBefore, poolAfter);


		assertTrue("RepOk is false.", b.repOk());
	}

	@Test(expected = ArithmeticException.class)
	public void testAddTooGreatAmountToPool(){

		if(b.getPool() == 0) // To ensure pool + amount > Integer.MAX_VALUE
			b.addToPool(1);

		int amount = Integer.MAX_VALUE;

		b.addToPool(amount);

		fail("Did not throw ArithmeticException.");
	}

	@Test
	public void testWithdrawFromPool(){

		b.addToPool(100); // To make sure that the pool has enough money initially.

		int amount = 10;
		int poolBefore = b.getPool();

		b.withdrawFromPool(amount);

		int poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				(poolBefore - amount), poolAfter);

		amount = 0;
		poolBefore = b.getPool();

		b.withdrawFromPool(amount); // Pool should not change

		poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				poolBefore, poolAfter);

		amount = Integer.MAX_VALUE;

		b.withdrawFromPool(amount);

		poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				0, poolAfter);
		
		amount = Integer.MIN_VALUE;
		poolBefore = b.getPool();  // Pool should not change

		b.withdrawFromPool(amount);

		poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				poolBefore, poolAfter);

		assertTrue("RepOk is false.", b.repOk());
	}

	@Test
	public void testCollectHalf(){

		b.withdrawFromPool(b.getPool());
		b.addToPool(100); // To make sure that the pool has enough money initially.

		int poolBefore = b.getPool();

		int collected = b.collectHalf();
		assertEquals("Amount collected from pool is calculated incorrectly.",
				poolBefore/2, collected);

		int poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				poolBefore - poolBefore/2, poolAfter);

		b.withdrawFromPool(b.getPool()); 
		b.addToPool(51); // To test pool containing odd amount.

		poolBefore = b.getPool();

		collected = b.collectHalf();
		assertEquals("Amount collected from pool is calculated incorrectly.",
				poolBefore/2, collected);

		poolAfter = b.getPool();
		assertEquals("Total amount in pool is calculated incorrectly.",
				poolBefore - poolBefore/2, poolAfter);

		assertTrue("RepOk is false.", b.repOk());
	}

	// I commented out the test below because this method is not implemented yet.
	/*

	@Test
	public void testFindUnownedProperty(){
		Property p = b.findUnownedProperty();
		assertTrue("Returned Property does not adhere to its specification.",
				p == null || p.getOwner() == null);
	}
	 */

	@Test
	public void testMove(){

		Player p = new Player(0, "Tester", 0);

		b.move(p, 1);

		int expectedLayer = 1;
		int expectedPosition = 1;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());

		b.move(p, 2);

		expectedLayer = 1;
		expectedPosition = 3;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
		
		/* The test in comments worked when I ran it.
		 * I put it as a comment because it takes about 30 seconds.
		 * You can uncomment the lines below to test it.
		 */
		/*
		
		b.move(p, Integer.MAX_VALUE);

		expectedLayer = 2;
		expectedPosition = 12;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
		
		*/
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveWithNullPlayer(){

		Player p = null;

		b.move(p, 1);
		
		fail("Did not throw IllegalArgumentException.");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveWithNegativeDiceValue(){

		Player p = new Player(0, "Tester", 0);

		b.move(p, -1);
		
		fail("Did not throw IllegalArgumentException.");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveWithZeroDiceValue(){

		Player p = new Player(0, "Tester", 0);

		b.move(p, 0);
		
		fail("Did not throw IllegalArgumentException.");
	}

	@Test
	public void testMoveDirectWithoutSquareAction(){

		Player p = new Player(0, "Tester", 0);
		b.moveDirectWithoutSquareAction(p, b.getSquareById(57)); //Id:57 -> HollandTunnel
		int expectedLayer = 0;
		int expectedPosition = 18;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveDirectWithoutSquareActionWithNullPlayer(){

		Player p = null;
		b.moveDirectWithoutSquareAction(p, b.getSquareById(0));
		fail("Did not throw IllegalArgumentException.");

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveDirectWithoutSquareActionWithNullSquare(){

		Player p = new Player(0, "Tester", 0);
		b.moveDirectWithoutSquareAction(p, null);
		fail("Did not throw IllegalArgumentException.");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveDirectWithoutSquareActionWithInvalidSquare(){

		Player p = new Player(0, "Tester", 0);
		Square sq = new Square() {
			@Override
			public void squareAction(Player currentPlayer, Board board) {
				//Does Nothing.			
			}
		};
		b.moveDirectWithoutSquareAction(p, sq); // sq is not contained in b
		fail("Did not throw IllegalArgumentException.");

	}

	@Test
	public void testMoveDirect(){

		Player p = new Player(0, "Tester", 0);
		b.moveDirect(p, b.getSquareById(57)); //Id:57 -> HollandTunnel
		int expectedLayer = 2;
		int expectedPosition = 14;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveDirectWithNullPlayer(){

		Player p = null;
		b.moveDirect(p, b.getSquareById(0));
		fail("Did not throw IllegalArgumentException.");

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveDirectWithNullSquare(){

		Player p = new Player(0, "Tester", 0);
		b.moveDirect(p, null);
		fail("Did not throw IllegalArgumentException.");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveDirectWithInvalidSquare(){

		Player p = new Player(0, "Tester", 0);
		Square sq = new Square() {
			@Override
			public void squareAction(Player currentPlayer, Board board) {
				//Does Nothing.			
			}
		};
		b.moveDirect(p, sq); // sq is not contained in b
		fail("Did not throw IllegalArgumentException.");
	}

	
	@Test
	public void testSwitchLayerByTransitStation(){

		Player p = new Player(0, "Tester", 0);

		b.moveDirectWithoutSquareAction(p, b.getSquareById(5));
		b.switchLayerByTransitStation(p);

		//Coordinates of other end of the corresponding TransitStation
		int expectedLayer = 2;
		int expectedPosition = 7;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
		assertTrue("Player is not at a TransitStation.", p.getLocation() instanceof TransitStation);


		b.moveDirectWithoutSquareAction(p, b.getSquareById(15)); //Id:15 -> TransitStation
		b.switchLayerByTransitStation(p);

		expectedLayer = 1;
		expectedPosition = 15;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
		assertTrue("Player is not at a TransitStation.", p.getLocation() instanceof TransitStation);

		b.moveDirectWithoutSquareAction(p, b.getSquareById(25)); //Id:25 -> TransitStation
		b.switchLayerByTransitStation(p);

		expectedLayer = 2;
		expectedPosition = 35;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
		assertTrue("Player is not at a TransitStation.", p.getLocation() instanceof TransitStation);


		b.moveDirectWithoutSquareAction(p, b.getSquareById(35)); //Id:35 -> TransitStation
		b.switchLayerByTransitStation(p);

		expectedLayer = 1;
		expectedPosition = 35;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
		assertTrue("Player is not at a TransitStation.", p.getLocation() instanceof TransitStation);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSwitchLayerByTransitStationWithInvalidArgument(){

		Player p = new Player(0, "Tester", 0);

		b.moveDirectWithoutSquareAction(p, b.getSquareById(0)); //Not a TransitStation
		b.switchLayerByTransitStation(p);
		fail("Did not throw IllegalArgumentException.");
	}

	@Test
	public void testGoToJail(){

		Player p = new Player(0, "Tester", 0);

		b.goToJail(p);

		//Coordinates of Jail Square
		int expectedLayer = 1;
		int expectedPosition = 10;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
		assertTrue("Player is not marked as in jail.", p.getJail());
	}

	@Test
	public void testMoveMrm(){

		Player p = new Player(0, "Tester", 0);

		b.moveMrm(p);

		int expectedLayer = 1;
		int expectedPosition = 1;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
		
		b.moveMrm(p);

		expectedLayer = 1;
		expectedPosition = 3;

		assertEquals("Player is not at expected layer.", expectedLayer, p.getLayer());
		assertEquals("Player is not at expected position.", expectedPosition, p.getPosition());
		assertEquals("Player is not at expected square.",
				b.getSquareAt(expectedLayer, expectedPosition), p.getLocation());
	}

}
