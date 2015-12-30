package domain;


public class CommunityChest extends Square {

	public CommunityChest(int id){
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
		setName("Community Chest");
	}
	

	@Override
	public void squareAction(Player currentPlayer, Board board) {
			Card c = board.pickACommunityCard();    
			c.cardAction();
	}

}
