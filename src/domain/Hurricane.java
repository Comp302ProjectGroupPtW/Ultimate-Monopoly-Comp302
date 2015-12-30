package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class Hurricane extends ChanceCard {

	Hurricane() {
		super("Hurricane makes landfall", "Remove 1 House from each \n"
				+ "property in any player's 1 color \n"
				+ "group. (Downgrade Skyscapers to \n"
				+ "Hotels, Hotels to 4 houses.");
	}

	@Override
	public void cardAction() { 
		String[] str = Board.getInstance().getColorArray();
		Player[] players = Game.getInstance().getPlayers();
		GuiHandler hand = GuiHandler.getInstance();
		String chosenColor = hand.askSelection("Choose an unfortunate color to be hurricaned",
				"Hurricane makes LANDFALL!!!", str);

		for (int i = 0; i < players.length; i++) {
			Player pl = players[i];
			ArrayList<Estate> estate = pl.getEstates();
			for (int j = 0; j < estate.size(); j++) {
				if(estate.get(j).getColor().equals(chosenColor)){
					estate.get(j).demolish();
				}
			}
		}
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
	}
}
