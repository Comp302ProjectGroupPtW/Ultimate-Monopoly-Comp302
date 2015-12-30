package domain;


public class BirthdayGift extends Square {

	public BirthdayGift(int id){
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		currentPlayer.deposit(200);
	}

}
