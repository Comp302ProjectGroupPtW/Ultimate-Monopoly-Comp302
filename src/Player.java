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
	private Square location;
	private ArrayList<Property> property;
	
	public boolean bankrupt;
	public boolean keeping;
	
	

	property = new ArrayList<Property>();
	
	
	
	public Player() {
		
	}
	
	public Player(int id, String name, int money, Square location, ArrayList<Property> property) {
		this.id = id;
		this.name = name;
		this.money = money;
		
		bankrupt = false;
		keeping = false;
		
		this.location = location;
		this.property = property;
		
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
	
	public Square getLocation(){
		return location;
	}
	
	public void setLocation(Square s){
		this.location = s;
	}
	
	public boolean isBankrupt() {
		return bankrupt;
	}
	
	public boolean isKeeping(){
		return keeping;
	}
	
	public void setKeeping(boolean bool){
		keeping = bool;
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
		return property;
	}
	
	public void addProperty(Property p) {
		property.add(p);
	}

	public Square getFirstProperty() {
		return property.get(1);
	}
	
	public Square getSpecificProperty(int i) {
		return property.get(i);
	}
	
	public int getPropertyNum() {
		return property.length();
	}
	
	public void removeSpecificProperty(int i) {
		return property.remove(i);
	}
	
	public boolean noProperty() {
		return property.isEmpty();
	}
	
}
