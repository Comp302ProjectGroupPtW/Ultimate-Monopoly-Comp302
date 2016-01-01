package domain;

import java.util.ArrayList;

public class GeneralRepairs extends CommunityCard {

	
	GeneralRepairs() {
		super("General Repairs", "Make general repairs to all your properties \n"
				+ "$25 per House, Cab Stand and Transit Station, $100 per Hotel \n"
				+ "and Skyscraper.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
		// TODO Auto-generated method stub
		Player currentPl = Game.getInstance().getCurrentPlayer();
		int house = 0;
		int hotel = 0;
		int skyscraper = 0;
		int others = 0;
		ArrayList<Estate> estates = currentPl.getEstates();
		ArrayList<TransitStation> tstations = currentPl.getTransitStations();
		ArrayList<CabCompany> cabs = currentPl.getCabCompanies();
		for (int i = 0; i < estates.size() ; i++) {
			int x = estates.get(i).getBuildings();
			if(x == 6){
				skyscraper++;				
			}
			else if(x == 5){
				hotel++;
			} else house += x;
		}
		
		others+= tstations.size();
		for (int i = 0; i < cabs.size() ; i++) {
			others+= cabs.get(i).getBuildings();
		}
		
		int withdrawAmount = 25*(others + house) + 100*(hotel + skyscraper);
		currentPl.withdraw(withdrawAmount);
		
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
	}

}
