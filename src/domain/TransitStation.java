package domain;


public class TransitStation extends Property implements Buildable {

	private int trainDepot;
	public TransitStation(String name, int id){
		this.setName(name);
		this.setId(id);
		this.setPrice(200);
		this.trainDepot=0;
		this.setBaseRent(25);
		this.setRent(25);
		this.pb= new TransitStationPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		if(this.getOwner()==null){
			boolean buy = GuiHandler.getInstance().askYesNo("Do you want to buy " + this.getName() +" for $" +this.getPrice(), "Transit Station");
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
	public void build(Player currentPlayer) {
		// TODO Auto-generated method stub
		currentPlayer.withdraw(150);
		this.trainDepot++;
		this.updateRent();
		GuiHandler.getInstance().updateBuilding(this);
	}

	@Override
	public void sellBack(Player currentPlayer) {
		// TODO Auto-generated method stub
		if(this.trainDepot>=1){
		currentPlayer.deposit(150);
		this.trainDepot--;
		this.updateRent();
		GuiHandler.getInstance().updateBuilding(this);
		}
	}

	@Override
	public void demolish() {
		// TODO Auto-generated method stub
		if(this.trainDepot>=1){
			this.trainDepot--;
			this.updateRent();
			GuiHandler.getInstance().updateBuilding(this);
			}
	}

	@Override
	public void updateRent() {
		// TODO Auto-generated method stub
		if(this.trainDepot>0){
			this.setRent(this.getBaseRent());
			for(int i=0;i<this.trainDepot; i++){
				this.setRent(this.getRent()*2);
			}
		}
	}

	@Override
	public int getBuildings() {
		return this.trainDepot;
	}

	@Override
	public void demolishAll() {
		// TODO Auto-generated method stub
		while(this.trainDepot>=1){
			this.trainDepot--;
			this.updateRent();
			}
		GuiHandler.getInstance().updateBuilding(this);
	}

}
