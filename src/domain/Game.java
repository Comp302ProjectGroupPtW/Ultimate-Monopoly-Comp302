package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Game {
	private static Game instance = new Game();
	private Board board = Board.getInstance();
	@XmlElement
	private LinkedList<Player> players;	//int miplayer mı
	@XmlElement
	private Player currentPlayer;
	@XmlElement
	private ArrayList<Card> pendingCards;			//arraylist? sayısı
	private diceMoveHandler diceMvHandler;
	@XmlElement
	private Dice dice= new Dice();
	private GuiHandler gui;
	private Player[] playersArray;
	private int defaultBalance = 500;//modify

	public static void main(String[] args){
		Game.getInstance();
		/*try{
			 JAXBContext jc = JAXBContext.newInstance( Game.class );
		       Marshaller m = jc.createMarshaller();
		       System.out.println(m.getClass());
		       m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		       m.marshal(Game.getInstance(), System.out);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	protected Game() {
		gui= GuiHandler.getInstance();
		gui.setGame(this);
		//Player sayısı? GUI startlamadan önce sor!!!
		int playerNum;
		playerNum=Integer.parseInt(gui.askForInput("Enter the number of the players","Number of Players"));
		players = new LinkedList<Player>();

		for (int i = 0; i < playerNum; i++) {
			String name= gui.askForInput("Enter The Name of the Player"+(i+1),"Name");
			players.add(new Player(i,name,defaultBalance));
		}
		playersArray= players.toArray(new Player[playerNum]);
		//playersı başta bi arraye çevir
		currentPlayer = players.pollFirst();
		players.addLast(currentPlayer);
		//GUI start
		gui.init(playersArray);
		gui.displayPlayer(currentPlayer);
		diceMvHandler = new diceMoveHandler(dice, currentPlayer, gui);
		diceMvHandler.startMoveByRolling(currentPlayer, board);
	}
	public static Game getInstance(){
		return instance;
	}
	public void endTurn(){ 

		gui.enableEndTurn(false);
		dice.flush();
		if (players.size()>1) {
			changeThePlayer();	
			diceMvHandler.startMoveByRolling(currentPlayer, board);

		}
		else
			finishGame();
	}
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	private void finishGame() {
		gui.showMessage("Victory and the Glory!","Victory!");
		//Oyunu bitirmek için gerekli işlemler
	}
	public void addPendingCard(Card crd){			
		pendingCards.add(crd);
	}
	public void offerBelonging(Keepable kp, int id, Player pl,int price){
		//önce bu keepable bu adamda var mı kontrol edilsin
		boolean q = gui.askYesNo("Does "+pl.getName()+"accept to trade "+kp.toString()+"from "+currentPlayer.getName() +"for "+price+"$ ?","Offer");
		//o keeping cardı birinden çıkarıp diğerine eklenecek
		if(q){

		}
		else
			gui.showMessage("Offer Rejected!","Rejection!");
	}
	public void helperOfferBelonging(Property pr, int id, Player pl){
		/*	
		boolean qu = gui.askYesNo("Does "+pl.getName()+"accept to trade "+pr.getName()+"from "+currentPlayer.getName() +"for "+pr.getPrice()+"$ ?","Offer");
		//o property birinden çıkarıp diğerine eklenecek
		if(qu){
			currentPlayer.makeOfferTo(pl, pr.getPrice(), pr);
			gui.showMessage("Offer Accepted!","Acception!");
		}
		else
			gui.showMessage("Offer Rejected!","Rejection!");
	}
		 */
		boolean qu = gui.askYesNo("Does "+pl.getName()+"accept to trade "+pr.getName()+"from "+currentPlayer.getName() +"for "+pr.getPrice()+"$ ?","Offer");
		//o property birinden çıkarıp diğerine eklenecek
		if(qu){
			if(pl.getBalance() < pr.getPrice()){
				GuiHandler.getInstance().showMessage(pl.getName() + " cannot afford to pay this amount", "Player");
			}else{
				pl.transfer(currentPlayer, pr.getPrice());
				pl.givePropertyTo(currentPlayer, pr);
			}
			if(pl.isBankrupt()){
				pl.setBalance(0);
				pl.setBankrupt(true);
			}
			gui.showMessage("Offer Accepted!","Acception!");
		}
		else
			gui.showMessage("Offer Rejected!","Rejection!");
	}
	public void offerBelonging(){
		ArrayList<Square> cumulation = new ArrayList<Square>();
		cumulation.addAll(currentPlayer.getEstates());
		cumulation.addAll(currentPlayer.getTransitStations());
		cumulation.addAll(currentPlayer.getCabCompanies());
		cumulation.addAll(currentPlayer.getUtilities());
		Property pr =gui.askSelection("Select the property which you want to upgrade:","Upgrade Property",cumulation.toArray(new Property[1]));
		Player pl = gui.askSelection("To which player you want to make an offer","", getPlayers());	
	}
	public boolean userToggledMortgage(){
		ArrayList<Square> cumulation = new ArrayList<Square>();
		cumulation.addAll(currentPlayer.getEstates());
		cumulation.addAll(currentPlayer.getTransitStations());
		cumulation.addAll(currentPlayer.getCabCompanies());
		cumulation.addAll(currentPlayer.getUtilities());
		Property pr =gui.askSelection("Select the property which you want to Mortgage:","Mortgage Property",cumulation.toArray(new Property[1]));
		Player pl = currentPlayer;
		if(pr.getOwner()== pl){
			pr.setMortgaged(true);
			pl.deposit(pr.getMortgageValue());
			return true;
		}
		else return false;

	}
	public boolean userToggledDemortgage(Property pr,Player pl){

		ArrayList<Square> cumulation = new ArrayList<Square>();
		cumulation.addAll(currentPlayer.getEstates());
		cumulation.addAll(currentPlayer.getTransitStations());
		cumulation.addAll(currentPlayer.getCabCompanies());
		cumulation.addAll(currentPlayer.getUtilities());
		pr =gui.askSelection("Select the property which you want to Releas of Mortgage:","Demortgage Property",cumulation.toArray(new Property[1]));
		pl = currentPlayer;
		if((pr.getOwner()== pl)&&pr.isMortgaged()){
			pr.setMortgaged(false);
			double newValue = pr.getMortgageValue(); //Mortgaged geri ödeme
			pl.withdraw((int) newValue);
			return true;

		}
		else return false;
	}
	public void userRoll(){
		diceMvHandler.roll();
	}
	public void userMrM(){
		diceMvHandler.monopolyMove();
	}
	public void userBuyBuilding() //başırılıysa true, değilse false, Ata integration
	{

		ArrayList<Square> cumulation = new ArrayList<Square>();
		cumulation.addAll(currentPlayer.getEstates());
		cumulation.addAll(currentPlayer.getTransitStations());
		cumulation.addAll(currentPlayer.getCabCompanies());
		cumulation.addAll(currentPlayer.getUtilities());
		Player pl = currentPlayer;

		Property p =gui.askSelection("Select the property which you want to buy upgrade:","Upgrade Property",cumulation.toArray(new Property[1]));
		((Buildable)p).build(currentPlayer);
	}
	public void userSellBuilding(){

		ArrayList<Square> cumulation = new ArrayList<Square>();
		cumulation.addAll(currentPlayer.getEstates());
		cumulation.addAll(currentPlayer.getTransitStations());
		cumulation.addAll(currentPlayer.getCabCompanies());
		cumulation.addAll(currentPlayer.getUtilities());
		Player pl = currentPlayer;
		Property p =gui.askSelection("Select the property which you want to downgrade:","Downgrade Property",cumulation.toArray(new Property[1]));
		((Buildable) p).sellBack(currentPlayer);
	}
	public void userEndTurn(){
		endTurn();
	}
	public void updateGui(){

	}
	public Player[] getPlayers(){ // Array'e çevrilmişini ver!!
		return playersArray;
	}
	public void executeBankruptcyOperations(Player pl){
		pl.setBankrupt(true);
		pl.setBalance(0);
		pl.releaseAllProperties();
		currentPlayer = players.pollFirst();
		players.remove(pl); // linkedlistte bu sorun çıkarır mı?


	}
	private void checkPendingCards(){
		for (Card card : pendingCards) {
			//card.checkForConditions(); //Card için check
		}	
	}

	public void changeThePlayer(){
		currentPlayer = players.pollFirst();
		players.addLast(currentPlayer);
		gui.displayPlayer(currentPlayer);
		return;
	}
//	public void debug(){
//
//		for (int i = 0; i < players.size(); i++) {
//
//			Player pl = getPlayers()[i];
//
//			boolean desired = gui.askYesNo("Do you want to modify Player"+i,"Selection of Player");
//
//			if(desired){
//
//				boolean propertyAddCheck = gui.askYesNo("Do you want to add Property","Add Property");
//
//				if(propertyAddCheck){
//					ArrayList<Square> sqs = new ArrayList<Square>();
//					sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[0]));
//					sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[1]));
//					sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[2]));
//					Property pr = (Property) gui.askSelection("Select the property which you want to add.","Add Property", sqs.toArray(new Square[1]));
//
//					pl.deposit(pr.getPrice());
//
//					pl.buyProperty(pr);
//
//				}
//
//				boolean moneyModifyCheck = gui.askYesNo("Do you want to change money","Change Money");
//
//				if(moneyModifyCheck){
//
//					int money=Integer.parseInt(gui.askForInput("Enter the number of the players","Number of Players"));
//
//					pl.setBalance(money);
//
//				}
//
//				boolean propertyBuildingAddCheck = gui.askYesNo("Do you want to build an upgrade to your Properties","Build Upgrade");
//
//				if(propertyBuildingAddCheck){
//					ArrayList<Square> cumulation = new ArrayList<Square>();
//					cumulation.addAll(currentPlayer.getEstates());
//					cumulation.addAll(currentPlayer.getTransitStations());
//					cumulation.addAll(currentPlayer.getCabCompanies());
//					cumulation.addAll(currentPlayer.getUtilities());
//					Property p =gui.askSelection("Select the property which you want to upgrade:","Upgrade Property",cumulation.toArray(new Property[1]));
//
//
//					((Buildable)p).build(pl);
//
//				}
//
//				boolean propertyDowngradeCheck = gui.askYesNo("Do you want to build an upgrade (downgrade?) to your Properties","Build Upgrade");
//
//				if(propertyDowngradeCheck){
//					ArrayList<Square> cumulation = new ArrayList<Square>();
//					cumulation.addAll(currentPlayer.getEstates());
//					cumulation.addAll(currentPlayer.getTransitStations());
//					cumulation.addAll(currentPlayer.getCabCompanies());
//					cumulation.addAll(currentPlayer.getUtilities());
//					Property p =gui.askSelection("Select the property which you want to upgrade:","Upgrade Property",cumulation.toArray(new Property[1]));
//
//
//					((Buildable) p).sellBack(pl);
//
//				}
//
//				boolean positionModCheck = gui.askYesNo("Do you want to change position of the player","Position Change");
//
//				if(positionModCheck){
//					ArrayList<Square> sqs = new ArrayList<Square>();
//					sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[0]));
//					sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[1]));
//					sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[2]));
//					Square sq = (Square) gui.askSelection("Select the square to move on.","Change Location",sqs.toArray(new Square[1]));
//
//					board.moveDirect(pl,sq);
//
//				}
//
//			}
//
//		}
//
//		boolean diceModCheck = gui.askYesNo("Do you want to change values of Dice","Dice Modification");
//
//		if(diceModCheck){
//
//			dice.getInstance().debug=true;
//
//			dice.getInstance().debug1=Integer.parseInt(gui.askForInput("Enter the value for the 1st dice","1st Dice Value"));
//
//			dice.getInstance().debug2=Integer.parseInt(gui.askForInput("Enter the value for the 2nd Dice","2nd Dice Value"));
//
//			dice.getInstance().debug3=Integer.parseInt(gui.askForInput("Enter the value for the 3rd Dice","3rd Dice Value"));
//
//		}
//
//	}
//

	public void debug(){

		
			dice.getInstance().debug=true;

			dice.getInstance().debug1=Integer.parseInt(gui.askForInput("Enter the value for the 1st dice","1st Dice Value"));

			dice.getInstance().debug2=Integer.parseInt(gui.askForInput("Enter the value for the 2nd Dice","2nd Dice Value"));

			dice.getInstance().debug3=Integer.parseInt(gui.askForInput("Enter the value for the 3rd Dice","3rd Dice Value"));

		

	}
	public void triggerCard(){
		ArrayList<Card> sqs = new ArrayList<Card>();
		sqs.addAll(Board.getInstance().getChanceCards());
		sqs.addAll(Board.getInstance().getCommunityCards());
		Card crd = gui.askSelection("Select the card to be triggered", "Trigger", sqs.toArray(new Card[1]));
		crd.cardAction();
	}


}

