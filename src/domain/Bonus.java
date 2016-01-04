package domain;


public class Bonus extends Square {
	
	public Bonus(){
		
	}

	public Bonus(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new BonusPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		currentPlayer.deposit(300);
	}

}
