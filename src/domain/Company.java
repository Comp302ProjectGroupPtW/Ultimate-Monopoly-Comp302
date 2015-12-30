package domain;
import java.util.ArrayList;

public class Company {
	private String name;
	private int parValue;
	private int[] dividendValue;
	private int numOfSoldShares;
	private ArrayList<Share> shareList;
	
	
	public Company(String name,int parValue, int[] dividendValue){
		this.name = name;
		this.parValue = parValue;
		this.dividendValue = dividendValue;	
		fillShareList();
		numOfSoldShares = 0;
	}

	private void fillShareList(){
		for (int i = 0; i < 5; i++) {
			shareList.add(new Share(this));	
		}
	}

	public int getPlayerShareAmount(Player player){ //Player'ýn bu companyde kaç tane share'i olduđunu döndürüyor.
		int count = 0;
		for (int i = 0; i < numOfSoldShares; i++) {
			Player owner = shareList.get(i).getOwner();
			if(owner == player && !shareList.get(i).isMortgaged()) count++;
		}
		return count;
	}
	public int getPlayerMoneyReceiveAmount(Player player){ //Player'ýn kaç para alacađýný döndürüyor. Eđer -1 dönerse iţlem yapmayýp playerýn bu companyde share'i yok dersin.
		int count = getPlayerShareAmount(player);
		if(count>0) return dividendValue[count-1];
		return -1; 		
	}
	
	public int getPriceOfShare(){
		return parValue;
	}
	public int getRemainingShareAmount(){
		return 5 - numOfSoldShares;
	}
	public boolean hasBuyableShare() {
		return getRemainingShareAmount() > 0;
	}
	
	public boolean buyShare(Player player) { //player will use this method in its own buy method and make transactions.
		if(hasBuyableShare()){
			shareList.get(numOfSoldShares).setOwner(player);
			numOfSoldShares++;
			return true;
		}
		return false;
	}
	
	public boolean sellShare(Player player, Player targetPlayer){ //player will use this method in its own buy method and make transactions.
		for (int i = 0; i < numOfSoldShares; i++) {
			Share share = shareList.get(i);
			Player owner = share.getOwner();
			if(owner == player){
				share.setOwner(targetPlayer);
				numOfSoldShares--;
				return true;
			}
		}
		return false;
	}
	
	public boolean mortgageShare(Player player){ //player will use this method in its own buy method and make transactions.
		for (int i = 0; i < numOfSoldShares; i++) {
			Share share = shareList.get(i);
			Player owner = share.getOwner();
			if(owner == player && !share.isMortgaged()){
				share.setMortgaged(true);
				return true;
			}
		}
		return false;
	}
	

	/**
	 * @return the dividendValue
	 */
	public int[] getDividendValue() {
		return dividendValue;
	}

	/**
	 * @return the loanValue
	 */
	public int getLoanValue() {
		return parValue/2;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [Name=" + name + ", Par Value=" + parValue + ", Loan Value=" + parValue/2 + ", Number of Sold Shares="
				+ numOfSoldShares + ", Has Buyable Share ?:" + hasBuyableShare() + "]";
	}
	// EFFECTS: Returns true if the rep invariant holds 	
	// for this; otherwise returns false. 
	/**
	 * @effects Returns true if the rep invariant holds for this; otherwise returns false. 
	 * @return boolen true or false
	 */
	public boolean repOk(){
		if(parValue<0)
			return false;
		if(numOfSoldShares>5)
			return false;

		return true;
	}

}
