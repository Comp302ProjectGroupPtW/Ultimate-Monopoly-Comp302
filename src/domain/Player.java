package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *  Player class is a representation of a player of the monopoly game. It holds the players id for internal uses, has the player's name and the balance. 
 *
 */

public class Player {

	private String name;
	private int id;
	private int balance;
	private Game game;

	private ArrayList<PlayerLocationObserver> observers = new ArrayList<PlayerLocationObserver>();

	private boolean bankrupt;
	private boolean inJail;
	private boolean finished;

	private int layer;
	private int position;
	private Square location;

	private HashMap<String, Integer> estateHash = new HashMap<String, Integer>();
	private ArrayList<String> majorEstates = new ArrayList<String>();
	private ArrayList<String> monopolyEstates = new ArrayList<String>();

	private ArrayList<Estate> estates = new ArrayList<Estate>();
	private ArrayList<TransitStation> transitStations = new ArrayList<TransitStation>();
	private ArrayList<Utility> utilities = new ArrayList<Utility>();
	private ArrayList<CabCompany> cabCompanies = new ArrayList<CabCompany>();
	private ArrayList<Card> cardList = new ArrayList<Card>();

	public Player() {

	}

	/**
	 * Primary constructor for player. Initialises a player at the initial start of the game.
	 * It takes 3 arguments id-player's id, name-player's name, balance-player's initial balance.
	 * Also it sets the private fields of player inJail, finished, bankrupt booleans to false.
	 * And puts the player to the "Go" square.
	 * @param id the id of a player for display purposes
	 * @param name the name of the player for display purposes as a string
	 * @param balance the total money of the player as an int
	 */
	public Player(int id, String name, int balance) {
		this.id = id;
		this.name = name;
		layer = 1;
		position = 0;
		inJail = false;
		finished = false;
		bankrupt = false;
		location = Board.getInstance().getSquareById(0);
		this.balance = balance;
	}
	
	/**
	 * Returns the name of the player as a String.
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the player's name with the parameter name.
	 * @param name The new name of the player as a string 
	 * @modifies name
	 * @modifies the player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the finished attribute of the player.
	 * @return the whether the player is finished or not.
	 */
	public boolean getFinished() {
		return finished;
	}

	/**
	 * Sets the player's finished boolean with the parameter "b". If b is null, throws NullPointerException
	 * @param b the new value of finished
	 * @modifies finished
	 * @modifies the player
	 */
	public void setFinished(boolean b) {
		finished = b;
	}

	/**
	 * Assumes the board is 2 dimensional array(board[layer][position]), returns the layer where the player is standing on as an int. 
	 * @return layer one of the 3 possible layers of the monopoly board
	 */
	public int getLayer(){
		return layer;
	}

	/**
	 * Sets the player's layer with the parameter l.
	 * @param l an int with a range 1 &lt;= l &lt;= 3
	 * @modifies layer
	 * @modifies player
	 */
	public void setLayer(int l){
		layer = l;
	}

	/** 
	 * Attaches the player location observer to the list of observers.
	 * @requires a non-bankrupt player
	 * @param observer a player location observer
	 * @modifies observers the list containing the player location observers
	 * @modifies the player
	 * 
	 */	
	public void attach(PlayerLocationObserver observer){
		observers.add(observer);
	}

	/**  
	 * Removes the player location observer from the player's list of observers
	 * @requires a non-bankrupt player
	 * @param observer player location observer
	 * @modifies observers the list containing the player location observers
	 * @modifies the player
	 * 
	 */	
	public void detach(PlayerLocationObserver observer){
		observers.remove(observer);
	}

	/** 
	 * For each player location observer the player has, the method publishes a change for the observers to react.
	 * @requires a non-bankrupt player
	 * @modifies each player location observer in the list of observers
	 * @effects calls {@code observer.playerLocationUpdated(this)} for each observer in the observer list
	 */	
	public void publishToPlayerLocationObservers(){
		for(PlayerLocationObserver observer : observers){
			observer.playerLocationUpdated(this);
		}
	}

	/**
	 * Returns the list of player location observers the player has.
	 * @return observers the list containing all player location observers
	 */
	public ArrayList<PlayerLocationObserver> getObservers(){
		return observers;
	}

	/**
	 * Returns the square where the player is standing on.
	 * @requires a non-bankrupt player
	 * @return location
	 */
	public Square getLocation(){
		return location;
	}

	/**
	 * Sets the player's location with the parameter sq. It also publishes this location change to all observers the player has.
	 * @requires a non-bankrupt and non-null player
	 * @modifies the player
	 * @modifies location
	 * @param sq a non-null square
	 */
	public void setLocation(Square sq){
		if(sq.equals(null)){
			throw new IllegalArgumentException();
		}

		location = sq;
		publishToPlayerLocationObservers();
	}

	/**
	 * Assuming the board is 2 dimensional array(board[layer][position]), returns the position where the player is standing on. 
	 * @return position
	 */
	public int getPosition(){
		return position;
	}

	/**
	 * Sets the player's position with the parameter p.
	 * @param p int
	 */
	public void setPosition(int p){
		position = p;
	}

	/**
	 * Returns whether if the player's balance is less than zero.
	 * @return {@code getBalance() <= 0}
	 */
	public boolean isBankrupt(){
		return getBalance() <= 0;
	}

	/**
	 * Returns the bankrupt attribute of a player.
	 * @return bankrupt boolean
	 */
	public boolean getBankrupt() {
		return bankrupt;
	}

	/**
	 * Sets the bankrupt attribute of a player with it's new boolean value.
	 * @param bool the new value for bankrupt
	 */
	public void setBankrupt(boolean bool) {
		bankrupt = bool;
	}

	/**
	 * Returns the attribute inJail of a player.
	 * @return inJail a boolean which symbolises whether a player is in jail or not.
	 */
	public boolean getJail() {
		return inJail;
	}

	/**
	 * Sets the attribute inJail with it's new boolean value, bool.
	 * @param bool the new value for inJail
	 */
	public void setJail(boolean bool) {
		inJail = bool;
	}

	/**
	 * Returns the total balance of the player as an int.
	 * @return balance the money of a player.
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Sets the player's balance with a new int value, bl.
	 * @param bl the new value for the player's balance
	 */
	public void setBalance(int bl) {
		balance = bl;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", id=" + id + ", balance=" + balance
				+ ", observers=" + observers + ", bankrupt=" + bankrupt
				+ ", inJail=" + inJail + ", finished=" + finished + ", layer="
				+ layer + ", position=" + position + ", location=" + location
				+ ", estateHash=" + estateHash + ", majorEstates="
				+ majorEstates + ", monopolyEstates=" + monopolyEstates
				+ ", estates=" + estates + ", transitStations="
				+ transitStations + ", utilities=" + utilities
				+ ", cabCompanies=" + cabCompanies + ", cardList=" + cardList
				+ "]";
	}

	/**  
	 * Deposits a non-negative integer to the player's total balance. If the user enters a negative amount, the method does nothing.
	 * @requires a non-bankrupt and non-null player
	 * @requires non-negative integer amount
	 * @modifies the player
	 * @modifies the player's balance
	 * @param amount the int value to be deposit
	 * @effects if a player is null, throws NullPointerException. If the amount is a negative integer, throwss IllegalArgumentException.
	 * Else sets player's balance to be {@code getBalance() + amount}.
	 * 
	 */
	public void deposit(int amount) {
		if(this.equals(null)){
			throw new NullPointerException();
		}
		if(amount < 0){
			throw new IllegalArgumentException();
		}
		setBalance(getBalance() + amount);
		GuiHandler.getInstance().updateBalances();
	}

	/**  
	 * Withdraws a non-negative integer from the player's total balance.
	 * @requires a non-bankrupt and non-null player
	 * @requires non-negative integer amount
	 * @modifies the player
	 * @modifies the player's balance 
	 * @param amount the int value to be withdraw
	 * @effects If a player is null, throws NullPointerException. If the argument is a negative value, throws IllegalArgument Eception. Else if player's balance is {@code (getBalance() - amount > 0)}, the method sets the player's balance with {@code setBalance(getBalance() - amount)}.
	 * else, the method sets the balance to 0 and flags player bankrupt.
	 * 
	 */
	public void withdraw(int amount) {
		if(this.equals(null)){
			throw new NullPointerException();
		}

		if(amount < 0){
			throw new IllegalArgumentException();
		}

		if (getBankrupt()){
			throw new IllegalArgumentException();
		}

		if(getBalance() - amount > 0){
			setBalance(getBalance() - amount);
			GuiHandler.getInstance().updateBalances();
		}else{
			setBankrupt(true);
			setBalance(0);
			GuiHandler.getInstance().updateBalances();
		}

	}

	/**  
	 * Transfers a specific amount from the player to the target player. If the transfer amount is a negative number, the method does nothing.
	 * @requires an amount to transfer 
	 * @requires source-player: a non-bankrupt and non-null player with a balance which is greater than or equal to the amount to transfer.
	 * @requires target-player: a non-bankrupt and non-null player
	 * @modifies source-player
	 * @modifies target-player
	 * @param targetPlayer the player which the money would be transferred to 
	 * @param amount the int amount to transfer
	 * @effects If either player is null, throws NullPointerException. If either player is bankrupt, throws IllegalArgumentException. Else withdraws the amount from the source-player and then deposits the amount to the target-player.
	 * If the source-player's balance is smaller than the amount to transfer, deposits the total balance of the source-player to the target-player and 
	 * sets the source-players balance to zero and sets his bankrupt field to true.
	 *  
	 */
	public void transfer(Player targetPlayer, int amount) {
		if(this.equals(null)){
			throw new NullPointerException();
		}

		if(targetPlayer.equals(null)){
			throw new NullPointerException();
		}

		if(this.getBankrupt() || targetPlayer.getBankrupt()){
			throw new IllegalArgumentException();
		}

		if(amount < 0){
			throw new IllegalArgumentException();
		}

		if(getBalance() - amount > 0){
			withdraw(amount);
			targetPlayer.deposit(amount);
			GuiHandler.getInstance().updateBalances();
		}else{
			targetPlayer.deposit(getBalance());
			setBankrupt(true);
			setBalance(0);
			GuiHandler.getInstance().updateBalances();
		}

	}


	/**  
	 * Pays the rent of the property which the player is on to it's respective owner.
	 * 
	 * @requires a non-bankrupt and non-pull player to be on an owned property which he/she is not the owner of the property.
	 * @modifies the player
	 * @modifies owner
	 * @param p the property the player is currently on
	 * @effects If the property is null or the player is null throws NullPointerException. If the property's owner is null or either player is bankrupt, throws IllegalArgumentException. Else  
	 * withdraws the rent of the Property p, from the source-player and then deposits the amount to the target-player. If the source-player's balance is smaller than rent, 
	 * deposits the total balance of the source-player to the target-player and sets the source-player bankrupt.
	 * 
	 */
	public void payRent(Property p) {
		if(this.equals(null) || p.equals(null)){
			throw new NullPointerException();
		}
		if(p.getOwner().equals(null)){
			throw new NullPointerException();
		}
		if(this.getBankrupt() || p.getOwner().getBankrupt()){
			throw new IllegalArgumentException();
		}
		
		if(balance < p.getRent()) {
			p.getOwner().setBalance(getBalance());
			setBankrupt(true);
			setBalance(0);
		}else{
			setBalance(getBalance() - p.getRent());
			p.getOwner().deposit(p.getRent());
		}
	}

	/**
	 * Returns the estates the player has in an arraylist.
	 * @return an arraylist containing the estates
	 */
	public ArrayList<Estate> getEstates() {
		return estates;
	}

	/**
	 * Returns the transit stations the player has in an arraylist.
	 * @return an arraylist containing the transit stations
	 */
	public ArrayList<TransitStation> getTransitStations() {
		return transitStations;
	}

	/**
	 * Returns the cab companies the player has in an arraylist.
	 * @return an arraylist containing the cab companies
	 */
	public ArrayList<CabCompany> getCabCompanies() {
		return cabCompanies;
	}

	/**
	 * Returns the utilities the player has in an arraylist.
	 * @return an arraylist containing the utilities
	 */
	public ArrayList<Utility> getUtilities() {
		return utilities;
	}

	/** 
	 * Buys the unowned property that the player has landed on if the player can afford it. Returns true if the player has completed the process or else false.
	 * @requires a non-bankrupt and non-null player
	 * @requires an unowned property which is the location of the player
	 * @modifies the player, the property the player has landed on, the Player's estates or transitStations or utilities or cabCompanies depending on the property's type.
	 * @param p the location of the player
	 * @effects If the property is owned or the player is bankrupt throws IllegalArgumentException. Else withdraws the base price of the Property p, from the source-player. Sets {p.setOwner(this)} and adds the Property p to it's respective lists.
	 * Checks whether the player has any monopoly or majority ownership on any estate kind.
	 * @return true, if player can buy the Property p, else false.
	 */
	public boolean buyProperty(Property p) {
		if(getBankrupt()){
			throw new IllegalArgumentException();
		}

		if(this.equals(null)){
			throw new NullPointerException();
		}
		if(this.getBankrupt()){
			throw new IllegalArgumentException();
		}
		
		if(getBalance() - p.getPrice() > 0){
			if(p instanceof Estate) {
				estates.add((Estate) p);
				setUniqueEstates(((Estate) p).getColor());

			}
			if(p instanceof TransitStation) {
				transitStations.add((TransitStation) p);
			}
			if(p instanceof Utility) {
				utilities.add((Utility) p);
			}
			if(p instanceof CabCompany) {
				cabCompanies.add((CabCompany) p);
			}
			p.setOwner(this);
			withdraw(p.getPrice());

			return true;
		}else{
			return false;
		}
	}

	/*
	/**  @requires: the player has a property.
	 *   @modifies: this, Property p, targetPlayer
	 *   @param player, targetPlayer, Property p
	 *   @effects: Prompts the targetPlayer whether he accepts the player's property with the offered amount.
	 *   		  If he accepts, transfers the said amount from targetPlayer to player and sets the p's owner
	 *   		  to targetPlayer.
	 *   		  Finally checks if the targetPlayer is now bankrupt after the transaction.
	 *   
	 */
	/*
	public void makeOfferTo(Player targetPlayer, int amount, Property p){

		if(targetPlayer.getBalance() < amount){
			GuiHandler.getInstance().showMessage(targetPlayer.getName() + " cannot afford to pay this amount", "Player");
		}else{
			targetPlayer.transfer(this, amount);
			targetPlayer.givePropertyTo(this, p);
		}
		if(targetPlayer.isBankrupt()){
			targetPlayer.setBalance(0);
			targetPlayer.setBankrupt(true);
		}
	}
	 */
	/**  
	 * Gives the specific property from the player to the target player.
	 * @requires a non-bankrupt and non-null player who owns has a property
	 * @requires a non-bankrupt and non-null target player
	 * @modifies the player, the specific property, targetPlayer, targetPlayer's estates or transitStations or utilities or cabCompanies depending on Property p's type.
	 * @param targetPlayer the player which will receive the property
	 * @param p the property which is being transferred
	 * @effects If either player is null throws NullPointerException. If the property which is being transferred is null, throws IllegalArgumentException. 
	 * Else removes the property from the player's respective property lists. Set the owner of the property to targetPlayer and 
	 * adds it to targetPlayer's respective lists. Finally updates the majority and monopoly estates of both the player and targetPlayer.
	 */
	public void givePropertyTo(Player targetPlayer, Property p) {

		if(this.equals(null) || targetPlayer.equals(null) || p.equals(null)){
			throw new NullPointerException();
		}

		if(p.getOwner().equals(null)){
			throw new NullPointerException();
		}

		if(p instanceof Estate) {
			estates.remove(p);
			targetPlayer.getEstates().add((Estate) p);
			setUniqueEstates(((Estate) p).getColor());
			
			targetPlayer.setUniqueEstates(((Estate) p).getColor());

		}
		if(p instanceof TransitStation) {
			transitStations.remove(p);
			targetPlayer.getTransitStations().add((TransitStation) p);
		}
		if(p instanceof Utility) {
			utilities.remove(p);
			targetPlayer.getUtilities().add((Utility) p);
		}
		if(p instanceof CabCompany) {
			cabCompanies.remove(p);
			targetPlayer.getCabCompanies().add((CabCompany)p);
		}
		p.setOwner(targetPlayer);
	}

	
	public void buyShare(Company co){
		if(getBalance() - co.getPriceOfShare() > 0){
			if(co.buyShare(this)){
				withdraw(co.getPriceOfShare());
			}
		}else{
			setBankrupt(true);
		}
	}
	
	public void buyShareWithBid(Company co, int highestBid){
		if(getBalance() - highestBid > 0){
			if(co.buyShare(this)){
				withdraw(highestBid);
			}
		}else{
			setBankrupt(true);
		}
	}
	
	public void transferShare(Company co, Player targetPlayer){
		co.sellShare(this);
		co.buyShare(targetPlayer);
	}
	
	public void mortgagedShare(Company co){
		if(co.mortgageShare(this)){
			deposit(co.getLoanValue());
		}
	}
	
	public boolean buyPropertyWithBid(Property p, int highestBid){
		if(getBankrupt()){
			throw new IllegalArgumentException();
		}

		if(this.equals(null)){
			throw new NullPointerException();
		}
		if(this.getBankrupt()){
			throw new IllegalArgumentException();
		}
		
		if(getBalance() - highestBid > 0){
			if(p instanceof Estate) {
				estates.add((Estate) p);
				setUniqueEstates(((Estate) p).getColor());
			}
			if(p instanceof TransitStation) {
				transitStations.add((TransitStation) p);
			}
			if(p instanceof Utility) {
				utilities.add((Utility) p);
			}
			if(p instanceof CabCompany) {
				cabCompanies.add((CabCompany) p);
			}
			p.setOwner(this);
			withdraw(highestBid);

			return true;
		}else{
			return false;
		}
	}
	
	private void releaseEstate(Estate p) {
		getEstates().remove(p);
		deposit(p.getPrice());
		p.setOwner(null);
		p.setRent(p.getBaseRent());
		p.demolishAll();
	}

	private void releaseAllEstates() {
		int size = getEstates().size();
		for (int i = 0; i < size; i++) {
			releaseEstate(getEstates().get(i));
		}
	}

	private void releaseTransitStation(TransitStation p) {
		getTransitStations().remove(p);
		deposit(p.getPrice());
		p.setOwner(null);
		p.setRent(p.getBaseRent());
		p.demolishAll();
	}

	private void releaseAllTransitStations() {
		int size = getTransitStations().size();
		for (int i = 0; i < size; i++) {
			releaseTransitStation(getTransitStations().get(i));
		}
	}

	private void releaseCabCompany(CabCompany p) {
		getCabCompanies().remove(p);
		deposit(p.getPrice());
		p.setOwner(null);
		p.setRent(p.getBaseRent());
		p.demolishAll();
	}

	private void releaseAllCabCompanies() {
		int size = getCabCompanies().size();
		for (int i = 0; i < size; i++) {
			releaseCabCompany(getCabCompanies().get(i));
		}
	}

	private void releaseUtility(Utility p) {
		getUtilities().remove(p);
		deposit(p.getPrice());
		p.setOwner(null);
		p.setRent(p.getBaseRent());
	}

	private void releaseAllUtilities() {
		int size = getUtilities().size();
		for (int i = 0; i < size; i++) {
			releaseUtility(getUtilities().get(i));
		}
	}

	/** 
	 * Releases all the properties a player has.
	 * @requires the player has at least 1 property in at least 1 property-list type.
	 * @modifies this, player's estates or transitStations or utilities or cabCompanies depending on Property p's type.
	 * @effects Removes all the properties that the player's has from their respective list.
	 */
	public void releaseAllProperties(){
		if(estates.size() != 0){
			releaseAllEstates();
		}
		if(cabCompanies.size() != 0){
			releaseAllCabCompanies();
		}
		if(transitStations.size() != 0){
			releaseAllTransitStations();
		}
		if(utilities.size() != 0){
			releaseAllUtilities();
		}
		if(cardList.size() != 0){
			releaseAllCards();
		}
	}

	/**
	 * Returns the number of utilities the player has.
	 * @return the size of the utilities list.
	 */
	public int getUtilityCount(){
		return utilities.size();
	}

	/**
	 * Returns the number of cab companies the player has.
	 * @return the size of the cab companies list.
	 */
	public int getCabCompanyCount(){
		return cabCompanies.size();
	}


	/**  
	 * Returns the list containing the monopoly estates of a player.
	 * @requires a non-bankrupt and non-null player 
	 * @return  a list of string containing the colour group names of the estates which the player has a monopoly on.
	 * @effects If the player has no monopoly, returns an empty an empty list.
	 */	
	public ArrayList<String> getMonopolyEstates(){
		return monopolyEstates;
	}

	/**  
	 * Returns the list containing the majority estates of a player.
	 * @requires a non-bankrupt and non-null player 
	 * @return  a list of string containing the colour group names of the estates which the player has a majority ownership on.
	 * @effects If the player has no majority ownership, returns an empty an empty list.
	 */	
	public ArrayList<String> getMajorEstates(){
		return majorEstates;
	}

	private void fillEstateHash(String color) {
			if (estateHash.containsKey(color)){
				estateHash.put(color, estateHash.get(color) + 1);
			}else{
				estateHash.put(color, 1);
			}
	}
	
	private void setUniqueEstates(String colour) {

		fillEstateHash(colour);
		Board gameBoard = Board.getInstance();
		
		for(String color : estateHash.keySet()){
			if(gameBoard.getColorAmount(color) == estateHash.get(color)){
				getMonopolyEstates().add(color);
			}
			if(gameBoard.getColorAmount(color) == 3){
				if(estateHash.get(color) == 2){
					getMajorEstates().add(color);
				}
			}
			if(gameBoard.getColorAmount(color) == 4){
				if(estateHash.get(color) == 3){
					getMajorEstates().add(color);
				}
			}
		}
	}

	/**
	 * Returns the list containing the cards of the player.
	 * @return the card list
	 */
	public ArrayList<Card> getCardList() {
		return cardList;
	}

	/**
	 * Returns the list containing the keepable card list.
	 * @return the keepable card list
	 */
	public Card[] getKeepableCardList() {
		ArrayList keepList = new ArrayList();

		for(int i = 0; i < cardList.size(); i ++){

			Card temp = getCardList().get(i);

			if(temp.isKeeping()){
				keepList.add(temp);
			}
		}

		return (Card[])keepList.toArray(new Card[keepList.size()]);
	}
	/*
	public void setCardList(ArrayList<Card> cardl) {
		cardList = cardl;
	}
	 */

	/**
	 * Adds the card to the player's list of cards
	 * @param card a card that a player can possess
	 */
	public void addCard(Card card){
		cardList.add(card);
	}

	/**
	 * Removes the card from the player's list of cards
	 * @param card a card that a player can remove
	 */
	public void removeCard(Card card){
		cardList.remove(card);
	}

	/**
	 * Releases all the cards the player holds.
	 */
	public void releaseAllCards() {
		int size = getCardList().size();
		for (int i = 0; i < size; i++) {
			removeCard(getCardList().get(i));
		}
	}

	/**
	 * Gives the specific car to the target-player.
	 * @requires both player's to be non-null and non-bankrupt
	 * @param p the target-player
	 * @param card the specific card to be transfered
	 * @effects if the card is null, throws IllegalArgumentException, if either player is null, throws NullPointException
	 */
	public void giveCardTo(Player p,Card card){
		if(p.equals(null) || this.equals(null)){
			throw new NullPointerException();
		}
		if(card.equals(null)){
			throw new IllegalArgumentException();
		}
		removeCard(card);
		p.addCard(card);
	}

	/**
	 * Checks whether or not a created or modified player's representation is not broken.
	 * @return true if the representation of a player is not broken
	 */
	public boolean repOK(){
		if(name == null || id == 0 || balance < 0 || location == null){
			return false;
		}
		if(this.equals(null)){
			return false;
		}

		return true;
	}

}
