package domain;

public class EntertainmentRocks extends ChanceCard {

	EntertainmentRocks() {
		super("Entertainment Rocks!", "Stakeholders in Motion Pictures \n "
				+ "and General Radio can immediately \n"
				+ "collect dividends");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cardAction() {
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
		for(Player p: Game.getInstance().getPlayers()){
			for(Company c: Board.getInstance().getCompanyArray()){
				if(c.getName().equals("Motion Pictures") || c.getName().equals("General Radio"))
					if(c.getPlayerShareAmount(p)>0){
						p.deposit(c.getPlayerMoneyReceiveAmount(p));
					}
			}
		}
		
		
	}
}
