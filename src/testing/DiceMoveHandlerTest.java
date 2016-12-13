package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.Board;
import domain.Dice;
import domain.Game;
import domain.GuiHandler;
import domain.Player;
import domain.Square;
import domain.diceMoveHandler;

public class DiceMoveHandlerTest {
//These initialisations and constructions of the anciallary objects are necessary in order to be able to construct the tested class's instances.
	
private Game game = Game.getInstance();

	private GuiHandler gui = GuiHandler.getInstance();{{
		gui.setGame(game);
	}};
	private Dice dice = Dice.getInstance();
	private Player player = new Player(0, "Snoopy", 200);
	private Player player2 = new Player(0, "Lui", 200);
	private diceMoveHandler dmh = new diceMoveHandler(dice, player, gui);
	private Board board = Board.getInstance();
	@Test
	public void testStartMoveByRolling() { //This method tests if the move making player is the right player.
		dmh.startMoveByRolling(player2, board);
		assertTrue("DiceMoveHandler Successfully initiated the move of the next player.", (game.getCurrentPlayer()==player2));
	}
	@Test
	public void testMonopolyMove() {//This test specifically tests if the order of moving the player according to the monopolyMove has been transmitted/transferred to the executor objects such as MoveHandler and board.
		Square loc = Game.getInstance().getCurrentPlayer().getLocation();
		dmh.monopolyMove();
		assertTrue("Eventually The MoveHandler has been triggered correctly.", (Game.getInstance().getCurrentPlayer().getLocation()!=loc));
	}
	@Test
	public void testRoll() {//This method tests if the controller/handler succesfully ordered the concerning auxiallary methods/classes to roll the dice and determine the destination location.

		Square loc = Game.getInstance().getCurrentPlayer().getLocation();
		dmh.roll();
		assertTrue("The Roll method works correctly.And rolled the dice", (Game.getInstance().getCurrentPlayer().getLocation()!=loc)&&Dice.getInstance().getFaceValue1()!=-0);
	}

}
