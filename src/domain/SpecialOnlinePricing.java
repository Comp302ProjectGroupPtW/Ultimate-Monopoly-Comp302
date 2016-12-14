package domain;


public class SpecialOnlinePricing extends CommunityCard implements Keepable, PlayerLocationObserver {

	public SpecialOnlinePricing() {
		super("Special Online Pricing", "The next time you land on anyone \n"
				+ "else's raildroad, only pay 1/2 the rent.");
		setKeeping(true);
	}

	@Override
	public void keep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void cardAction() {
		GuiHandler.getInstance().showMessage(getDescription(), "Card");
		Player currentPlayer = Game.getInstance().getCurrentPlayer();
		currentPlayer.attach(this);
		currentPlayer.detach(this);
		
	}

	@Override
	public void playerLocationUpdated(Player player) {
		if(player.getLocation() instanceof TransitStation){
			Property p = (Property) player.getLocation();
			if(p.getOwner() != player){
				p.getOwner().deposit(p.getRent()/2);
				player.detach(this);
			}
		}
		GuiHandler.getInstance().showMessage(getName() + " Activated:" + getDescription(), "Card");
	}


}
