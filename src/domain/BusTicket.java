package domain;

import java.util.ArrayList;

public class BusTicket extends ChanceCard implements Keepable{

	BusTicket() {
		super("Bus Ticket", "The player moves to any square on his side of the board. \n"
				+ "Using this card expires all the other chance cards the player has.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keep() {
		// TODO Auto-generated method stub
		Player currentPlayer = Game.getInstance().getCurrentPlayer();
		currentPlayer.addCard(this);
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		Player currentPlayer = Game.getInstance().getCurrentPlayer();
		cardAction();
		currentPlayer.releaseAllCards();		
	}
	
	@Override
	public void cardAction() {
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
		Player currentPlayer = Game.getInstance().getCurrentPlayer();
	
		ArrayList<Square> side = Board.getInstance().getSideOfSquare(currentPlayer.getLayer(), currentPlayer.getPosition());
		
		Square[] sideArray = (Square[]) side.toArray(new Square[side.size()]);
		
		Square destination = GuiHandler.getInstance().askSelection("Choose your destination.", "Card Action", sideArray);
		Board.getInstance().moveDirect(currentPlayer, destination);
		currentPlayer.releaseAllCards();
	}


}