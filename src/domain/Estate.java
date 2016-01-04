package domain;
import java.util.ArrayList;

/**
 * 
 * @author Elif
 * The Estate class holds the necessary information of an estate on the board, which are
 * buyable, buildable and auctionable objects. The rent, house number and prices are hold 
 * by the object instances. The square actions that are executed when a player lands on an
 * estate is also called by this class.
 *
 */
public class Estate extends Property implements Buildable{

	private String color;
	private boolean monopoly;
	private boolean majority;
	private int building;
	private int housePrice;
	private int houses;
	
	/**
	 * Creates an Estate object with a name, id, rent, price, housePrice, color
	 * @param name name of the estate as a string
	 * @param id id of the estate as an integer
	 * @param rent rent of the estate as an integer
	 * @param price price of the estate as an integer
	 * @param housePrice price of the house as an integer
	 * @param color color of the estate as a string
	 */
	public Estate(String name, int id, int rent, int price, int housePrice, String color){
		this.setName(name);
		this.setId(id);
		this.setBaseRent(rent);
		this.setRent(rent);
		this.setPrice(price);
		this.housePrice=housePrice;
		this.color=color;
		this.houses=0;
		this.monopoly=false;
		this.majority=false;
		this.pb= new DoNothingPassBehavior();
	}
	
	/**
	 * Creates an Estate object using the constructors of the Property class
	 * @param color color of the estate as a string
	 * @param monopoly monopoly condition as an integer
	 * @param building building number of the estate as an integer
	 */
	public Estate(String color, boolean monopoly, int building) {
		super();
		this.color = color;
		this.monopoly = monopoly;
		this.building = building;
		this.pb= new DoNothingPassBehavior();
	}
	
	/**
	 * @requires currentPlayer not to be null, board to be initialized before 
	 * @modifies this, currentPlayer, board
	 * @param currentPlayer, players, board
	 * @effects when the current player lands on the estate it changes the owner of the
	 *  estate if the owner of the property is null and user wants to buy the estate 
	 *  and updates the monopoly and majority conditions or prompts the current player to 
	 *  pay rent to the owner of the estate. 
	 */
	@Override
	public void squareAction(Player currentPlayer, Board board) {
		if(this.getOwner()==null){
			boolean buy = GuiHandler.getInstance().askYesNo("Do you want to buy " + this.getName() +" for $" +this.getPrice(),"Buy Property");
			if(buy){
				currentPlayer.buyProperty(this);
				this.setMonopoly(this.findMonopoly());
				this.setMajority(this.findMajority());
				this.updateRent();
			} 
		}
		else {
			currentPlayer.payRent(this);
		}
	}
	
	/**
	 * @effects returns the color of the estate
	 * @return String this.color
	 */
	
	public String getColor() {
		return color;
	}

	/**
	 * @effects returns the monopoly state of the estate
	 * @return monopoly - monopoly state as a boolean
	 */
	
	public boolean isMonopoly() {
		return monopoly;
	}

	/**
	 * @effects returns the number of the buildings of the estate
	 * @return building - building number of the estate as an integer
	 */
	
	public int getBuildings() {
		return building;
	}

	/**
	 * @modifies this
	 * @param color color of the estate as a string
	 * @effects sets the color the estate to a given string, does nothing if the string
	 * is not a valid color
	 */
	
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @modifies this
	 * @param monopoly monopoly state of the estate as a boolean
	 * @effects sets the monopoly state of the estate as a boolean
	 */
	
	public void setMonopoly(boolean monopoly) {
		this.monopoly = monopoly;
	}
	
	/**
	 * @modifies this
	 * @param building the number of buildings of the property as an integer
	 * @effects sets the number of buildings of the estate as an integer
	 */

	public void setBuilding(int building) {
		this.building = building;
	}
	
	/**
	 * @modifies this
	 * @return majority 
	 * @effects sets the number of buildings of the estate as an integer
	 */
	
	public boolean isMajority() {
		return majority;
	}
	
	/**
	 * @effects returns the price of the buildings of the estate
	 * @return housePrice - price of a building of the estate as an integer
	 */

	public int getHousePrice() {
		return housePrice;
	}

	/**
	 * @effects returns the number of the houses of the estate
	 * @return houses - house number of the estate as an integer
	 */
	
	public int getHouses() {
		return houses;
	}
	
	/**
	 * @modifies this
	 * @param majority the majority ownership condition as a boolean
	 * @effects sets the majority ownership condition of the estate as a boolean
	 */

	public void setMajority(boolean majority) {
		this.majority = majority;
	}
	
	/**
	 * @modifies this
	 * @param housePrice the price of building a house on the estate as an integer
	 * @effects sets the price of houses of the estate as an integer
	 */

	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	/**
	 * @modifies this
	 * @param houses number of houses on the estate
	 * @effects sets the number of houses of the estate as an integer
	 */
	
	public void setHouses(int houses) {
		this.houses = houses;
	}
	
	/**
	 * @effects checks the representation invariant of the estate
	 * @return boolean that holds the repOk condition
	 */
	
	public boolean repOk(){
		if(this.getBaseRent()<0 || this.getBuildings()<0 || this.getHousePrice()<0 || this.getId()<0 || this.getMortgageValue()<0 || this.getRent()<0 || this.getName().equals(null) || this.getColor().equals(null)){
			return false;
		}
		return true;
	}

	@Override
	/**
	 *  @requires balance of currentPlayer must be greater than the housePrice, currentPlayer
	 *  must be the owner of the property
	 *  @modifies this, currentPlayer
	 *  @param currentPlayer
	 *  @effects increases the houses of the property by one and decreases the housePrice
	 *  from the balance of the currentPlayer  
	 */
	public void build(Player currentPlayer) {
		// TODO Auto-generated method stub
		if(currentPlayer==this.getOwner()){
		currentPlayer.withdraw(housePrice);
		this.houses++;
		this.updateRent();
		GuiHandler.getInstance().updateBuilding(this);
		}
	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	/**
	 * @requires houseCount of the property must be greater than 0
	 * @modifies this, currentPlayer
	 * @param currentPlayer
	 * @effects decreases the houses of the property by one and increases the balance of 
	 * the currentPlayer by the housePrice
	 */
	public void sellBack(Player currentPlayer) {
		if(currentPlayer == this.getOwner() && this.houses>=1){
			currentPlayer.deposit(housePrice/2);
			this.houses--;
			this.updateRent();
			GuiHandler.getInstance().updateBuilding(this);
		}
		
	}
	/**
	 * @requires houseCount of the property must be greater than 0
	 * @modifies this
	 * @effects decreases the houses of the property by one 
	 */
	@Override
	public void demolish() {
		if(this.houses>=1){
			this.houses--;
			this.updateRent();
			GuiHandler.getInstance().updateBuilding(this);
		}
	}
	
	/**
	 * @effects checks if the current estate is a part of a monopoly
	 * @return  the monopoly condition as a boolean
	 */
	
	private boolean findMonopoly(){
		ArrayList<String> monopolies = new ArrayList<String>();
		monopolies = this.getOwner().getMonopolyEstates();
		for(String s: monopolies){
			if(s.equals(this.color)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @effects checks if the current estate is a part of a majority ownership
	 * @return  the majority ownership condition as a boolean
	 */
	
	private boolean findMajority(){
		ArrayList<String> majorities = new ArrayList<String>();
		majorities = this.getOwner().getMajorEstates();
		for(String s: majorities){
			if(s.equals(this.color)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	/**
	 * @modifies this
	 * @effects sets the rent of the property according to majority, monopoly and 
	 * different house numbers
	 */
	
	public void updateRent() {
		if(this.isMajority() && this.houses==0){
			this.setRent(this.getBaseRent()*2);
		} else if(this.isMonopoly() && this.houses==0){
			this.setRent(this.getBaseRent()*3);
		} else if(this.houses==1){
			this.setRent((int) Math.round(this.getBaseRent()*1.55));
		} else if(this.houses==2){
			this.setRent((int) Math.round(this.getBaseRent()*1.85));
		} else if(this.houses==3){
			this.setRent((int) Math.round(this.getBaseRent()*2.05));
		} else if(this.houses==4){
			this.setRent((int) Math.round(this.getBaseRent()*2.20));
		} else if(this.houses==5){
			this.setRent((int) Math.round(this.getBaseRent()*2.30));
		} else if(this.houses==6){
			this.setRent((int) Math.round(this.getBaseRent()*3.5));
		}
	}

	@Override
	/**
	 * @requires houseCount of the property must be greater than 0
	 * @modifies this
	 * @effects decreases the houses of the property to zero
	 */
	public void demolishAll() {
		while(this.houses>=1){
			this.houses--;
			this.updateRent();
		}
		GuiHandler.getInstance().updateBuilding(this);
	}

}
