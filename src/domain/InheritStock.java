package domain;

import java.util.ArrayList;

public class InheritStock extends CommunityCard {

	InheritStock() {
		super("Inherit Stock", "You may choose any 1 share of any \n"
				+ "unpurchased stock to add to your porfolio.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
		// TODO Auto-generated method stub
		ArrayList<Company> available=new ArrayList<Company>();
		Player currentPlayer = Game.getInstance().getCurrentPlayer();

		for(Company c: Board.getInstance().getCompanyArray()){
			if(c.getPlayerShareAmount(currentPlayer) == 0){
				available.add(c);
			}
		}
		Company[] fin = (Company[]) available.toArray(new Company[available.size()]);

		Company c = GuiHandler.getInstance().askSelection("Which company share do you want to buy?", "Buy Share", fin);
		currentPlayer.buyShare(c);
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
	}
}

