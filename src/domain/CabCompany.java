package domain;

import javax.xml.bind.annotation.XmlElement;


/**
 * 
 */
public class CabCompany extends Property implements Buildable {
	@XmlElement
	private int cabStands;
	
	public CabCompany() {
		// TODO Auto-generated constructor stub
	}

	public CabCompany(String name, int id){
		this.setName(name);
		this.setId(id);
		this.setPrice(200);
		this.cabStands=0;
		this.setBaseRent(25);
		this.setRent(25);
		this.pb= new DoNothingPassBehavior();
	}
	
	public CabCompany(Player owner, int rent, int price, int mortgageValue,
			boolean isMortgaged) {
		super(owner, rent, price, mortgageValue, isMortgaged);
		this.cabStands=0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		if(this.getOwner()==null){
			boolean buy = GuiHandler.getInstance().askYesNo("Do you want to buy " + this.getName() +" for $" +this.getPrice(), "Cab Company");
			if(buy){
				currentPlayer.buyProperty(this);
				this.updateRent();
			} else{
				AuctionHandler.getInstance().makeAuction(this);
			}
		}
		
		else {
			currentPlayer.payRent(this);
		}
		boolean transport = GuiHandler.getInstance().askYesNo("Do you want to get transported to another cab company square for $20?", "Cab Company");
		if(transport){
			if(this.getOwner()==currentPlayer){
				currentPlayer.withdraw(20);
			} else {
				currentPlayer.withdraw(50);
			}
			Square [] port = new Square[9];
			port[0]=board.getSquareById(5);
			port[1]=board.getSquareById(15);
			port[2]=board.getSquareById(20);
			port[3]=board.getSquareById(25);
			port[4]=board.getSquareById(35);
			port[5]=board.getSquareById(68);
			port[6]=board.getSquareById(83);
			port[7]=board.getSquareById(95);
			port[8]=board.getSquareById(110);
			Square s = GuiHandler.getInstance().askSelection("Where do you want to travel?","",port);   
			board.moveDirect(currentPlayer, s);
		}
	}

	@Override
	public void build(Player currentPlayer) {
		// TODO Auto-generated method stub
		currentPlayer.withdraw(150);
		this.cabStands++;
		this.updateRent();
		GuiHandler.getInstance().updateBuilding(this);
	}

	@Override
	public void sellBack(Player currentPlayer) {
		// TODO Auto-generated method stub
		if(this.cabStands>=1){
		currentPlayer.deposit(75);
		this.cabStands--;
		this.updateRent();
		GuiHandler.getInstance().updateBuilding(this);
		}
	}

	@Override
	public void demolish() {
		// TODO Auto-generated method stub
		if(this.cabStands>=1){
			this.cabStands--;
			this.updateRent();
			GuiHandler.getInstance().updateBuilding(this);
		}
	}

	@Override
	public void updateRent() {
		// TODO Auto-generated method stub
		if(this.getOwner().getCabCompanyCount()==1){
			this.setRent(25);
		} else if (this.getOwner().getCabCompanyCount()==2){
			this.setRent(50);
		} else if (this.getOwner().getCabCompanyCount()==3){
			this.setRent(75);
		} else if (this.getOwner().getCabCompanyCount()==4){
			this.setRent(100);
		}
		if(this.cabStands>0){
			for(int i=0;i<this.cabStands; i++){
				this.setRent(this.getRent()*2);
			}
		}
	}

	@Override
	public int getBuildings() {
		// TODO Auto-generated method stub
		return this.cabStands;
	}

	@Override
	public void demolishAll() {
		// TODO Auto-generated method stub
		while(this.cabStands>=1){
			this.cabStands--;
			this.updateRent();
		}
		GuiHandler.getInstance().updateBuilding(this);
	}


	public void setBuildings(int buildings) {
		// TODO Auto-generated method stub
		this.cabStands=buildings;
		this.updateRent();
	}

}

