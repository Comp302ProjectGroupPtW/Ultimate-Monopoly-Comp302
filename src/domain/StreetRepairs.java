package domain;

import java.util.ArrayList;

public class StreetRepairs extends CommunityCard {

	public StreetRepairs() {
		super("Assessed for Street Repairs", "$25 per Cab Stand & Transit Station \n"
												+ "$40 per House, $115 per Hotel, and \n"
												+ "$100 per Skyscraper.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
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
		
		int withdrawAmount = 25*others + 40*house + 115*hotel + 100*skyscraper;
		currentPl.withdraw(withdrawAmount);
		
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
	}

}
