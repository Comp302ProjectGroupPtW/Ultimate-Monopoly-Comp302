package domain;

import java.util.ArrayList;


public class PropertyTaxes extends ChanceCard {

	public PropertyTaxes() {
		super("Property Taxes", "Pay $25 to the Pool for each \n"
										+ "unmortgaged property you own.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
		Player currentPl = Game.getInstance().getCurrentPlayer();
		Board board = Board.getInstance();
		int count = 0;
		ArrayList<Estate> estates = currentPl.getEstates();
		ArrayList<TransitStation> tstations = currentPl.getTransitStations();
		ArrayList<CabCompany> cabs = currentPl.getCabCompanies();
		ArrayList<Utility> utilities = currentPl.getUtilities();
		for (int i = 0; i < estates.size() ; i++) {
			if(estates.get(i).isMortgaged()) count++;
		}
		for (int i = 0; i < tstations.size() ; i++) {
			if(tstations.get(i).isMortgaged()) count++;
		}
		for (int i = 0; i < cabs.size() ; i++) {
			if(cabs.get(i).isMortgaged()) count++;
		}
		for (int i = 0; i < utilities.size() ; i++) {
			if(utilities.get(i).isMortgaged()) count++;
		}
		
		count*=25;
		currentPl.withdraw(count);
		board.addToPool(count);
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
	}

}
