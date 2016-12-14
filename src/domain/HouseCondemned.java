package domain;

import java.util.ArrayList;
import java.util.Random;


public class HouseCondemned extends CommunityCard {

	public HouseCondemned() {
		super("HOUSE CONDEMNED", "The city condemns one of your houses. Sell \n"
								+ "one house back to the Bank at 1/2 the price \n"
								+ "you paid for it. (Houses only. If you don't own \n"
								+ "any houses, do nothing.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
		Player currentPl = Game.getInstance().getCurrentPlayer();
		ArrayList<Estate> estates = currentPl.getEstates();
		ArrayList<Integer> estateHouses = new ArrayList<Integer>();
		Random rd = new Random();
		GuiHandler hand = GuiHandler.getInstance();
		
		for(int i = 0; i < estates.size(); i++){
			if(estates.get(i).getBuildings() != 0){
				estateHouses.add(estates.get(i).getId());
			}	
		}		
			int rand = rd.nextInt(estateHouses.size());
			estates.get(rand).sellBack(currentPl);
			hand.showMessage("From " + estates.get(rand).getOwner().getName() + "'s "
					+ estates.get(rand).getName() + "property a house has been deleted", "House Condemned");
	
	}

}