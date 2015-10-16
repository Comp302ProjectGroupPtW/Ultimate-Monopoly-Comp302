import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	
	private HashMap<String, Integer> propertyHash = new HashMap<String, Integer>();
	
	private ArrayList<Property> propertyList = new ArrayList<Property>();

	
	public Player() {
		
	}
	
	public Player(int id, String name, int money, Square location) {
		this.id = id;
		this.name = name;
		this.money = money;
		
		bankrupt = false;
		keeping = false;
		
		this.location = location;
		
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
	
	public void moveTo(Square s){
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
	
	public void transfer(Player b, int amount) {
		if (!this.isBankrupt()){
			this.withdraw(amount);
			b.deposit(amount);
		}
	}

	public HashMap<String, Integer> fillHash() {
		for (int i = 0; i < getPropertyNum(); i++){
			String color = propertyList.get(i).getColor();
			propertyHash.put(color, 1);
				if (propertyHash.containsKey(color)){
					propertyHash.put(color, propertyHash.get(color) + 1);
				}
		}
		return propertyHash;
	}
	
	public String[] getMonopolies() {
		ArrayList monopolies = new ArrayList();
		propertyHash = fillHash();

		Iterator colors = propertyHash.keySet().iterator();
		
		while(colors.hasNext()){
			String color = (String)colors.next();
			
			if(3 == propertyHash.get(color)){
				monopolies.add(color);
			}
		}
		
		return (String[])monopolies.toArray(new String[monopolies.size()]);
	}
	
	
	public ArrayList<Property> getPropertyList() {
		return propertyList;
	}
	
	public void addProperty(Property P) {
		propertyList.add(P);
	}
	
	public int getPropertyNum() {
		return propertyList.size();
	}
	
	public void removeProperty(Property P) {
		propertyList.remove(P);
	}
	
	public boolean noProperty() {
		return propertyList.isEmpty();
	}
	
}
