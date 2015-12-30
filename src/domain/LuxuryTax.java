package domain;


public class LuxuryTax extends Square {

	public LuxuryTax(String name, int id){
	this.setName(name);
	this.setId(id);
	this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		currentPlayer.withdraw(75);
		board.addToPool(75);
	}

}
