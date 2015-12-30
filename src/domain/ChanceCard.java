package domain;


public abstract class ChanceCard extends Card {
	
	public ChanceCard(String name, String description) {
		super(name, description);
		
	}

	@Override
	public abstract void cardAction();

}
