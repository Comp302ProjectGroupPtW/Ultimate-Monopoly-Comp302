package domain;


public class StockExchange extends Square {

	public StockExchange(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub

	}

}
