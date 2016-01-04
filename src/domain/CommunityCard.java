package domain;


public abstract class CommunityCard extends Card {
	
	public CommunityCard() {
		// TODO Auto-generated constructor stub
	}

	CommunityCard(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void cardAction();

}
