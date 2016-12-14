package domain;

public class ChangingLanes extends ChanceCard {

	ChangingLanes() {
		super("Changing Lanes", "Move DIRECTLY to the space \n"
				+ "that is 1 Track ABOVE this one. If you are on \n"
				+ "the Inner Track, do nothing");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
		
		Player currentPlayer = Game.getInstance().getCurrentPlayer();
		Square playerLocation = currentPlayer.getLocation();
		
		if(playerLocation.equals((Chance)Board.getInstance().getSquareAt(1, 7))){
			GuiHandler.getInstance().showMessage("Your destination is Beacon Street.", "Changing Lanes");
			Square destination = Board.getInstance().getSquareAt(0, 5);
			Board.getInstance().moveDirect(currentPlayer, destination);
		}else if(playerLocation.equals((Chance)Board.getInstance().getSquareAt(1, 22))){
			GuiHandler.getInstance().showMessage("Your destination is Stock Exchange.", "Changing Lanes");
			Square destination = Board.getInstance().getSquareAt(0, 12);
			Board.getInstance().moveDirect(currentPlayer, destination);
		}else if(playerLocation.equals((Chance)Board.getInstance().getSquareAt(1, 36))){
			GuiHandler.getInstance().showMessage("Your destination is Reverse Direction Square.", "Changing Lanes");
			Square destination = Board.getInstance().getSquareAt(0, 22);
			Board.getInstance().moveDirect(currentPlayer, destination);
		}else if(playerLocation.equals((Chance)Board.getInstance().getSquareAt(0, 16))){
			GuiHandler.getInstance().showMessage("You are in the Inner Track. There are no squares \n"
					+ "above your position.", "Oops");
		}else if(playerLocation.equals((Chance)Board.getInstance().getSquareAt(2, 10))){
			GuiHandler.getInstance().showMessage("Your destination is Vermont Avenue.", "Changing Lanes");
			Square destination = Board.getInstance().getSquareAt(1, 8);
			Board.getInstance().moveDirect(currentPlayer, destination);
		}else if(playerLocation.equals((Chance)Board.getInstance().getSquareAt(2, 21))){
			GuiHandler.getInstance().showMessage("Your destination is Pennsylvania Railroad.", "Changing Lanes");
			Square destination = Board.getInstance().getSquareAt(1, 15);
			Board.getInstance().moveDirect(currentPlayer, destination);
		}else if(playerLocation.equals((Chance)Board.getInstance().getSquareAt(2, 30))){
			GuiHandler.getInstance().showMessage("Your destination is Free Parking Square.", "Changing Lanes");
			Square destination = Board.getInstance().getSquareAt(1, 20);
			Board.getInstance().moveDirect(currentPlayer, destination);
		}else{
			GuiHandler.getInstance().showMessage("Your destination is Go Square.", "Changing Lanes");
			Square destination = Board.getInstance().getSquareAt(1, 0);
			Board.getInstance().moveDirect(currentPlayer, destination);
		}
		
	}

}
