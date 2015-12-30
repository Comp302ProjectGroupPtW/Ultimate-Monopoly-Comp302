package domain;

public abstract class Property extends Square {
	private Player owner;
	private int rent;
	private int price;
	private int mortgageValue;
	private boolean isMortgaged;
	private int baseRent;
	
	public Property() {
		super();
	}

	public Property(Player owner, int baseRent, int price, int mortgageValue,
			boolean isMortgaged) {
		super();
		this.owner = owner;
		this.baseRent = baseRent;
		this.price = price;
		this.mortgageValue = mortgageValue;
		this.isMortgaged = isMortgaged;
	}
	
	public Player getOwner() {
		return owner;
	}

	public int getRent() {
		return rent;
	}

	public int getPrice() {
		return price;
	}

	public int getMortgageValue() {
		return mortgageValue;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		GuiHandler.getInstance().setOwner(this, owner);
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setMortgageValue(int mortgageValue) {
		this.mortgageValue = mortgageValue;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}
	
	public int getBaseRent() {
		return baseRent;
	}

	public void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}

	@Override
	public abstract void squareAction(Player currentPlayer, Board board); 
	
	public abstract void updateRent();


}
