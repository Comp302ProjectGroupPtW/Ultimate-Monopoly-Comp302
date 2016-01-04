package domain;


public class Jail extends Square {

	public Jail(){
		
	}
	
	public Jail(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		currentPlayer.setJail(true);
	}

}
