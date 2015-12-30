package domain;


public class Chance extends Square {

	public Chance(int id){
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
		setName("Chance");
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		Card c = board.pickAChanceCard();    
		c.cardAction();
	}

}
