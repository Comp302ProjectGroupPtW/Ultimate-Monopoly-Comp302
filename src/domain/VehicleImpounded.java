package domain;

public class VehicleImpounded extends CommunityCard {

	VehicleImpounded() {
		super("VEHICLE IMPOUNDED", "Pay $50 to Pool, move DRIECTLY to \n"
				+  "\"Just Visiting\"" + " to pick your car. \n"
						+ "Lose 1 turn.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
		Player currentPlayer = Game.getInstance().getCurrentPlayer();
		
		currentPlayer.withdraw(50);
		Square destination =  Board.getInstance().getSquareAt(1, 18);
		Board.getInstance().addToPool(50);
		Board.getInstance().moveDirectWithoutSquareAction(currentPlayer, destination);
		
		Game.getInstance().getCurrentPlayer().setControl(true);
		
		
	}

}
