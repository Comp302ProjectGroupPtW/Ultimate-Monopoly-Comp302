package domain;


public class Misc extends Square {

	public Misc(){
		
	}
	
	public Misc(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		//Do nothing
	}

}
