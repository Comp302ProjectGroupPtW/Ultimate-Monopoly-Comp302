
public class Property extends Square {

	private String name;
	private int basePrice;
	private int price;
	private int baseRent;
	private int rent;
	private String color;
	private Player owner;

	public Property() {
		this.name=null;
		this.basePrice=0;
		this.baseRent=0;
		this.color=null;
		this.owner=null;
		this.price=0;
		this.rent=0;
	}

	public Property(String name, int basePrice, int price, int baseRent,
			int rent, String color, Player owner) {
		super();
		this.name = name;
		this.basePrice = basePrice;
		this.price = price;
		this.baseRent = baseRent;
		this.rent = rent;
		this.color = color;
		this.owner = owner;
	}

	public Property(String name, int basePrice, int baseRent, String color) {
		super();
		this.name = name;
		this.basePrice = basePrice;
		this.price = basePrice;
		this.baseRent = baseRent;
		this.rent=baseRent;
		this.color = color;
		this.owner = null;
	}

	public String getName() {
		return name;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public int getPrice() {
		return price;
	}

	public int getBaseRent() {
		return baseRent;
	}

	public int getRent() {
		return rent;
	}

	public String getColor() {
		return color;
	}

	public Player getOwner() {
		return owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void payRent(Player p){
		p.transfer(p,this.owner,this.rent);
	}

	public void buyProperty(Player p){
		p.withdraw(price);
		this.setOwner(p);
	}

	public void sellProperty(Player p){
		p.deposit(price);
		this.setOwner(null);
	}

	public void buyWithCommonCard(Player p){
		p.deposit(100);
		this.setOwner(null);
	}

	@Override
	public void squareAction(Player currentPlayer, Player[] players, Board board) {
		if(this.getOwner()==null){
			boolean a = askToBuy();    //GUI ile oyuncuya almak isteyip istemediği sorulacak. Boolean döndürecek.
			if(a){
				if(currentPlayer.isKeeping()){     //Arhan player a bir keeping booleanı ekle bi de isKeeping
					boolean b = askToUseKeeping();   //GUI ile oyuncuya kartını kullanmak isteyip istemediği sorulacak. Boolean döndürecek.
					if(b){
						currentPlayer.setKeeping(false);  //Arhan playera bir setKeeping metodu ekle
						this.buyWithCommonCard(currentPlayer);
					}
				}
				this.buyProperty(currentPlayer);
			}
		} else {
			if(this.owner!=currentPlayer){
				this.payRent(currentPlayer);
			}
		}

	}

	private boolean askToUseKeeping() {
		return false;
	}

	public boolean askToBuy(){     //GUI olarak implement edilecek
		return true;
	}

}
