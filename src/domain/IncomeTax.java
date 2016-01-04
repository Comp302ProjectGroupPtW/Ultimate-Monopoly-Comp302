package domain;


public class IncomeTax extends Square {
	
	public IncomeTax(){
		
	}

	public IncomeTax(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		boolean pay = GuiHandler.getInstance().askYesNo("Do you want to pay 10% of your money? (Or you will pay $200)", "Income Tax");
		if(pay){
			currentPlayer.withdraw(currentPlayer.getBalance()/10);
			board.addToPool(currentPlayer.getBalance()/10);
		}
		else {
			currentPlayer.withdraw(200);
			board.addToPool(200);
		}

	}

}
