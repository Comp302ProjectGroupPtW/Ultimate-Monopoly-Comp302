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
			} else{
				AuctionHandler.getInstance().makeAuction(this);
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
		this.setRent(diceValue*getUtilityCoefficient());
	}
	
	public int getUtilityCoefficient(){
		int count=this.getOwner().getUtilityCount();
		if(count==1){
			return 4;
		}else if(count==2){
			return 10;
		}else if(count==3){
			return 20;
		}else if(count==4){
			return 40;
		}else if(count==5){
			return 80;
		}else if(count==6){
			return 100;
		}
		return 0;
	}

}
