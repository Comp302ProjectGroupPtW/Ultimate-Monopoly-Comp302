package domain;


public class Utility extends Property {

	public Utility(String name, int id){
		this.setName(name);
		this.setId(id);
		this.setPrice(150);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		this.updateRent();
		if(this.getOwner()==null){
			boolean buy = GuiHandler.getInstance().askYesNo("Do you want to buy " + this.getName() +" for $" +this.getPrice(), "Utility");
			if(buy){
				currentPlayer.buyProperty(this);
			} 
		}
		else {
			currentPlayer.payRent(this);
		}
	}

	@Override
	public void updateRent() {
		int diceValue=Dice.getInstance().getFaceValue1();
		diceValue+=Dice.getInstance().getFaceValue2();
		diceValue+=Dice.getInstance().getFaceValueSpeed();
		this.setRent(diceValue*this.getOwner().getUtilityCount());
	}

}
