package domain;


public class Go extends Square {

	public Go(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new GoPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		//Pass olduğu için boş
	}

}
