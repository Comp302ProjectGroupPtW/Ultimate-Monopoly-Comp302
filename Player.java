

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Player {
	
	private int id;
	private int money;
	private String name;
	
	public boolean bankrupt;
	public boolean keeping;
	public boolean finished;
	
	private GuiHandler theHand;
	private Square location;
	
	private HashMap<String, Integer> propertyHash = new HashMap<String, Integer>();
	
	private ArrayList<Property> propertyList = new ArrayList<Property>();

	
	public Player() {
		
	}
	
	public Player(int id, String name, int money, Square location) {
		this.id = id;
		this.name = name;
		this.money = money;
		
		finished = false;
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
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean b) {
		finished = b;
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
		theHand.updateBalances();
	}
	
	public void withdraw(int money) {
		if (!bankrupt){
			if(this.money - money > 0){
				this.money = this.money - money;
				theHand.updateBalances();
			}else{
				bankrupt = true;
			}
		}
	}
	
	public void transfer(Player b, int amount) {
		if (!this.isBankrupt()){
			this.withdraw(amount);
			b.deposit(amount);
			theHand.updateBalances();
		}
	}

	public void givePropertyTo(Player b, Property p) {
		this.removeProperty(p);
		b.addProperty(p);
		p.setOwner(b);
		this.deposit(p.getBasePrice());
		b.deposit(p.getBasePrice());
		//burada evler olunca onun için check edilmesi lazım
	}
	
	public void releaseProperty(Property p) {
		this.removeProperty(p);
		this.deposit(p.getBasePrice());
		p.setOwner(null);
		p.setRent(p.getBaseRent());
	}
	
	public void releaseAllProperty() {
		int size = this.getPropertyNum();
		for (int i = 0; i < size; i++) {
			this.releaseProperty(this.getPropertyList().get(i));
		}
	}
	
	public boolean passGo(Board b, int i) {
		int curr = b.getSquareId(this.location);
		if(curr>i){
			return true;
		} 
		return false;
	}
	
	public void moveTo(Board b, Player[] players,int i){
		if(this.passGo(b, b.getSquareId(this.getLocation()))){
			this.deposit(200);
		}
		this.setLocation(b.getSquareById(i));
		this.getLocation().squareAction(this, players, b);
		
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
	
	public void addProperty(Property p) {
		propertyList.add(p);
	}
	
	public int getPropertyNum() {
		return propertyList.size();
	}
	
	public void removeProperty(Property p) {
		propertyList.remove(p);
	}
	
	public boolean noProperty() {
		return propertyList.isEmpty();
	}
	
}
