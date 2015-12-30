package domain;



public class StockExchange extends Square {

	public StockExchange(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		for(Player p: Game.getInstance().getPlayers()){
			for(Company c: Board.getInstance().getCompanyArray()){
				if(c.getPlayerShareAmount(p)>0){
					p.deposit(c.getPlayerMoneyReceiveAmount(p));
				}
			}
		}
		boolean buy = GuiHandler.getInstance().askYesNo("Do you want to buy a share?", "Buy Share");
		if(buy){
			Company c = GuiHandler.getInstance().askSelection("Which company share do you want to buy?", "Buy Share", Board.getInstance().getCompanyArray());
			currentPlayer.buyShare(c);
		} else{
			Company c = GuiHandler.getInstance().askSelection("Which company share do you want to auction?", "Auction Share", Board.getInstance().getCompanyArray())
			AuctionHandler.getInstance().makeAuctionShare(c);
		}
		
	}

}
