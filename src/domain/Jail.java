package domain;


public class Jail extends Square {

	public Jail(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		Board.getInstance().goToJail(currentPlayer);
	}

	public Jail() {
		super();
	}

}
