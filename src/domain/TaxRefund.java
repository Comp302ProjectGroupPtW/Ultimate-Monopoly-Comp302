package domain;


public class TaxRefund extends Square {

	public TaxRefund(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		int amount = board.collectHalf();
		currentPlayer.deposit(amount);
	}

	public TaxRefund() {
		super();
		// TODO Auto-generated constructor stub
	}

}
