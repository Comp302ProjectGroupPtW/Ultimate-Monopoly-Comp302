import java.util.ArrayList;

//Dice sonucu target için square fieldı.
//notFinished() -> booleanı false, isFinished() false
//givePropertyTo()
//releaseProperty()
//releaseAllProperties()
//move() olsun ve gidilen yerin executorunı çağırsın

public class Player {
	
	private int id;
	private int money;
	private String name;
	public boolean bankrupt;
	
	public boolean keeping;
	private Square location;
	
	private ArrayList<Property> propertyList = new ArrayList<Property>();

	
	public Player() {
		
	}
	
	public Player(int id, String name, int money, Square location, ArrayList<Property> propertyList) {
		this.id = id;
		this.name = name;
		this.money = money;
		
		bankrupt = false;
		keeping = false;
		
		this.location = location;
		this.propertyList = propertyList;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isKeeping(){
		return keeping;
	}
	
	public void setKeeping(boolean bool){
		keeping = bool;
	}
	
	public Square getLocation(){
		return location;
	}
	
	public void setLocation(Square s){
		this.location = s;
	}
	
	public boolean isBankrupt() {
		return bankrupt;
	}
	

	public int getMoney() {
		return money;
	}

	public void deposit(int money) {
		this.money = this.money + money;
	}
	
	public void withdraw(int money) {
		if (!bankrupt){
			if(this.money - money > 0){
				this.money = this.money - money;
			}else{
				bankrupt = true;
			}
		}
	}
	
	public void transfer(Player a, Player b, int amount) {
		if (!a.isBankrupt()){
			a.withdraw(amount);
			b.deposit(amount);
		}
	}

	
	public ArrayList<Property> getPropertyList() {
		return propertyList;
	}
	
	public void addProperty(Property s) {
		propertyList.add(s);
	}

	public Square getFirstProperty() {
		return propertyList.get(1);
	}
	
	public Square getSpecificProperty(int i) {
		return propertyList.get(i);
	}
	
	public int getPropertyNum() {
		return propertyList.size();
	}
	
	public void removeSpecificProperty(int i) {
		propertyList.remove(i);
	}
	
	public boolean noProperty() {
		return propertyList.isEmpty();
	}
	
}
