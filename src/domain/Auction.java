package domain;

public class Auction extends Square {

	public Auction(int id){
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
		setName("Auction");
	}
	public Auction() {
		
	}
	
	@Override
	public void squareAction(Player currentPlayer, Board board) {
		Property p = chooseUnownedProperty();
		if(p==null){
			if(Dice.getInstance().isEven()){
				Board.getInstance().moveDirect(currentPlayer, Board.getInstance().getHighestRentInLayer(1));
			} else{
				Board.getInstance().moveDirect(currentPlayer, Board.getInstance().getHighestRentInLayer(0));
			}
		} else {
		AuctionHandler.getInstance().makeAuction(p);
		}
	}

	private Property chooseUnownedProperty() {
		// TODO Auto-generated method stub
		Property[] unowned = Board.getInstance().getUnownedProperties().toArray(new Property[0]);
		Property select = GuiHandler.getInstance().askSelection("Which property do you want to put up for auction?", "Auction", unowned);
		return select;
	}

}
