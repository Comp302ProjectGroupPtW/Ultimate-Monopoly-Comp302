package domain;

import java.util.LinkedList;


public class MardiGras extends ChanceCard {

	public MardiGras() {
		super("MARDI GRAS!", "Everyone has to the parade \n"
								+ "of Rex, King of Carnival. \n"
								+ "ALL players must move DIRECTLY to Canal Street.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
		Board br = Board.getInstance();
		Player[] players = Game.getInstance().getPlayers();
		Square sq = br.getSquareById(70);
		
		for (int i = 0; i < players.length; i++) {
			br.moveDirect(players[i], sq);
		}
		

	}

}
