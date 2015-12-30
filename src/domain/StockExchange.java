package domain;

import java.util.ArrayList;


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
			ArrayList<Company> available=new ArrayList<Company>();
			int i=0;
			for(Company c: Board.getInstance().getCompanyArray()){
				if(c.hasBuyableShare()){
					available.add(c);
					i++;
				}
			}
			Company[] fin = available.toArray(fin);
			Company c = GuiHandler.getInstance().askSelection("Which company share do you want to buy?", "Buy Share", fin);
			currentPlayer.buyShare(c);
		} else{
			Company c = GuiHandler.getInstance().askSelection("Which company share do you want to auction?", "Auction Share", fin);
			AuctionHandler.getInstance().makeAuctionShare(c);
		}
		
	}

}
