package domain;

import domain.Property;

public class AuctionHandler {

	private static AuctionHandler instance;
	private int highestBid;
	private Player highestBidder;

	public static AuctionHandler getInstance(){
		if(instance == null)
			instance = new AuctionHandler();
		return instance;
	}

	public void makeAuction(Property pr){
		highestBid=pr.getPrice()/2;
		int players = Game.getInstance().getPlayers().length;
		boolean[] playersBidding = new boolean[players];
		for (int i=0;i<players;i++){
			playersBidding[i]=true;
		}
		for(int counter=0; counter<players;counter++){
			int bid = bid(Game.getInstance().getPlayers()[counter]);
			if(bid==0){
				playersBidding[counter]=false;
			} else if (bid>highestBid){
				highestBid = bid;
				highestBidder = Game.getInstance().getPlayers()[counter];
			} else{
				playersBidding[counter]=false;
			}
		}
		if(highestBidder!=null){
			highestBidder.buyPropertyWithBid(pr, highestBid);
		}
	}
	
	public void makeAuctionShare(Company pr){
		if(pr.hasBuyableShare()){
		highestBid=pr.getPriceOfShare()/2;
		int players = Game.getInstance().getPlayers().length;
		boolean[] playersBidding = new boolean[players];
		for (int i=0;i<players;i++){
			playersBidding[i]=true;
		}
		for(int counter=0; counter<players;counter++){
			int bid = bid(Game.getInstance().getPlayers()[counter]);
			if(bid==0){
				playersBidding[counter]=false;
			} else if (bid>highestBid){
				highestBid = bid;
				highestBidder = Game.getInstance().getPlayers()[counter];
			} else{
				playersBidding[counter]=false;
			}
		}
		if(highestBidder!=null){
			highestBidder.buyShareWithBid(pr, highestBid);
		}
	}
		}

	private int bid(Player player) {
		int bid = 0;
		bid =Integer.parseInt(GuiHandler.getInstance().askForInput("Enter the bid amount", "Enter Bid")); 
		if(bid<=player.getBalance()){
			return bid;
		}
		return 0;
	}
}
