package domain;


public abstract class ChanceCard extends Card {
	
	public ChanceCard() {
		// TODO Auto-generated constructor stub
	}
	
	ChanceCard(String name, String description) {
		super(name, description);
		
	}

	@Override
	public abstract void cardAction();

}
