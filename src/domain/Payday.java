package domain;



public class Payday extends Square {

	public Payday(int id){
		this.setId(id);
		this.pb= new PayDayPassBehavior();
		setName("Pay Day");
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		//Do nothing
	}

	public Payday() {
		super();
		this.pb= new PayDayPassBehavior();
	}

}

