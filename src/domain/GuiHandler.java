package domain;


import ui.*;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class GuiHandler {

	private static GuiHandler instance;
	private Game game;
	private AppWindow window;

	private LinkedHashMap<Player, GuiPlayer> playerToGuiMap;
	private LinkedHashMap<Square, GuiSquare> squareToGuiMap;
	private ArrayList<GuiSquare> squareToGuiList;


	private GuiHandler() {
		//Does nothing. (To initialize GUI, call "init(Player[])")
	}

	public static GuiHandler getInstance(){
		if(instance == null)
			instance = new GuiHandler();
		return instance;
	}

	public void setGame(Game g){
		game = g;
	}

	public void init(Player[] players){


		playerToGuiMap = new LinkedHashMap<Player, GuiPlayer>();
		bindToGui(players);

		squareToGuiMap = new LinkedHashMap<Square, GuiSquare>();
		squareToGuiList = new ArrayList<GuiSquare>();
		bindToGui(Board.getInstance().getSquares());
		GuiSquare[][] squares = divideGuiSquaresToLayers();
		window = new AppWindow(this, squares, getGuiPlayersOrdered());
		window.init();
		initPlayers();
		updateBalances();
		//updateOwners();

	}

	/* Deprecated
	 * public void diceRolled(Dice dice){
		String message = "You rolled: " + dice.getLastDie1() + ", " + dice.getLastDie2();
		String title = "Dice";
		JOptionPane.showMessageDialog(window.getFrame(), message, title, JOptionPane.PLAIN_MESSAGE);
	}*/

	private GuiSquare[][] divideGuiSquaresToLayers() {
		GuiSquare[][] gsqs = new GuiSquare[3][];
		int prevLayerLenght = 0;
		for (int i = 0; i < gsqs.length; i++) {
			gsqs[i] = new GuiSquare[Board.getInstance().getLayerLength(i)];
			for (int j = 0; j < gsqs[i].length; j++) {
				gsqs[i][j] = squareToGuiList.get(prevLayerLenght+j);
			}
			prevLayerLenght += Board.getInstance().getLayerLength(i);
		}
		return gsqs;
	}

	public void playerMoved(Player player, Square square){
		if(window == null)
			return;
		GuiPlayer gp = getGui(player);
		((GuiSquare) gp.getParent().getParent()).removePlayer(gp);
		getGui(square).addPlayer(gp);
		//window.getFrame().paintAll(window.getFrame().getGraphics());
		//window.getFrame().revalidate();
		window.getFrame().repaint();

	}

	public void displayPlayer(Player player){
		if(window == null)
			return;
		window.getGamePanel().setCurrentPlayer(player.getName());
		updateKeepings(player);
	}

	//For controller class(es) only!
	public boolean askYesNo(String message, String title){
		Component frame = (window == null ? null : window.getFrame());
		int selection = JOptionPane.showConfirmDialog(frame, message, title,
				JOptionPane.YES_NO_OPTION);
		return selection == JOptionPane.YES_OPTION;
	}

	//For controller class(es) only!
	public String askForInput(String message, String title){
		Component frame = (window == null ? null : window.getFrame());
		return JOptionPane.showInputDialog(frame, message, title, JOptionPane.QUESTION_MESSAGE);
	}

	//For controller class(es) only!
	@SuppressWarnings("unchecked")
	public <T> T askSelection(String message, String title, T[] selectionValues){
		Component frame = (window == null ? null : window.getFrame());
		return (T) JOptionPane.showInputDialog(frame, message, title, JOptionPane.QUESTION_MESSAGE, null,
				selectionValues, selectionValues[0]);
	}

	public void askYesNo(String message, String title, Consumer<Boolean> callback){
		if(window == null)
			return;
		callback.accept(askYesNo(message, title));
	}

	public void askForInput(String message, String title, Consumer<String> callback){
		if(window == null)
			return;
		callback.accept(askForInput(message, title));
	}

	public <T> void askSelection(String message, String title, T[] selectionValues, Consumer<T> callback){
		if(window == null)
			return;
		callback.accept(askSelection(message, title, selectionValues));
	}

	public void showMessage(String message, String title){
		if(window == null)
			return;
		if(window.getFrame() != null)
			JOptionPane.showMessageDialog(window.getFrame(), message, title, JOptionPane.PLAIN_MESSAGE);
	}

	public void updateBalances(){
		if(window == null)
			return;
		String[] names = new String[playerToGuiMap.size()];
		String[] balances = new String[playerToGuiMap.size()];
		int i = 0;
		for (Player player : playerToGuiMap.keySet()) {
			names[i] = player.getName();
			balances[i] = Integer.toString(player.getBalance());
			i++;
		}

		window.getGamePanel().setBalances(names, balances);
		window.getFrame().repaint();
	}
	public void updateKeepings(Player p){
		if(window == null)
			return;
		Card[] cards = p.getKeepableCardList();
		String[] cardStrings = new String[cards.length];
		for (int i = 0; i < cardStrings.length; i++) {
			cardStrings[i] = cards[i].getName();
		}
		window.getGamePanel().setKeepings(cardStrings);
		window.getFrame().repaint();
	}

	public void updateBuilding(Property p){
		if(window == null)
			return;
		int buildingLevel = ((Buildable) p).getBuildings();
		((GuiProperty) getGui(p)).setBuildingLevel(buildingLevel);
		window.getFrame().repaint();

	}

	public void updateOwners(){
		/*Property p;
		for (Square sq : squareToGuiMap.keySet()) {
			if(sq instanceof Property && (p = (Property) sq).getOwner() != null)
			setOwner(p, p.getOwner());
		}*/
		for(Player currentPlayer : game.getPlayers()){
			ArrayList<Square> cumulation = new ArrayList<Square>();
			cumulation.addAll(currentPlayer.getEstates());
			cumulation.addAll(currentPlayer.getTransitStations());
			cumulation.addAll(currentPlayer.getCabCompanies());
			cumulation.addAll(currentPlayer.getUtilities());
			for (Square square : cumulation) {
				if(square instanceof Property){
					((Property) square).setOwner(currentPlayer);
				}
			}
		}
	}

	public void finish(Player player){
		if(window == null)
			return;
		showMessage("Congratulations " + player.getName() + " you have won the game!", "Game Over");
	}

	public void userRoll(){
		game.userRoll();
	}

	public void userMrM() {
		game.userMrM();

	}

	public void userSellBelonging() {
		game.offerBelonging();

	}

	public void userBuyBuilding() {
		game.userBuyBuilding();

	}

	public void userMortgage() {
		game.userToggledMortgage();
	}

	public void userTriggerCard() {
		game.triggerCard();	
	}

	public void userEndTurn() {
		game.userEndTurn();

	}
	public void setOwner(Property property, Player player){
		if(window == null)
			return;
		((GuiProperty) getGui(property)).setOwner(player.getName());
	}

	public void enableRoll(boolean b) {
		window.getRollDice().setEnabled(b);
	}

	public void enableMrM(boolean b) {
		window.getMrM().setEnabled(b);
	}

	public void enableSellBelonging(boolean b) {
		window.getSellBelonging().setEnabled(b);
	}

	public void enableBuyBuilding(boolean b) {
		window.getBuyBuilding().setEnabled(b);
	}

	public void enableEndTurn(boolean b) {
		window.getEndTurn().setEnabled(b);
		if(!b && !window.getRollDice().isEnabled() && !window.getMrM().isEnabled())
			window.getEndTurn().setEnabled(true);
	}

	private GuiPlayer[] getGuiPlayersOrdered(){
		GuiPlayer[] players = new GuiPlayer[playerToGuiMap.size()];
		int i = 0;
		for (GuiPlayer gp : playerToGuiMap.values()) {
			players[i++] = gp;
		}
		return players;
	}

	private GuiSquare[] getGuiSquaresOrdered(){
		GuiSquare[] squares = new GuiSquare[squareToGuiMap.size()];
		int i = 0;
		for (GuiSquare gs : squareToGuiMap.values()) {
			squares[i++] = gs;
		}
		return squares;
	}

	private void initPlayers(){
		System.out.println(Board.getInstance().getSquareAt(1, 0));
		for (Player player : playerToGuiMap.keySet()) {
			getGui(player.getLocation()).addPlayer(getGui(player));
		}
	}

	private void bindToGui(Player[] players){
		for (Player player : players) {
			playerToGuiMap.put(player, convertToGui(player));
		}
	}

	private void bindToGui(Square[] squares){
		for (Square square : squares) {
			if(square != null){
				GuiSquare gsq = convertToGui(square);
				squareToGuiMap.put(square, gsq);
				squareToGuiList.add(gsq);
			}
		}
	}

	private void bindToGui(Square[][] layers){
		for(Square[] squares : layers)
			bindToGui(squares);
	}
	private GuiPlayer getGui(Player player){
		return playerToGuiMap.get(player);
	}

	private GuiSquare getGui(Square square){
		return squareToGuiMap.get(square);
	}

	private static GuiSquare convertToGui(Square square){
		if(square instanceof Property){
			Property p = (Property) square;
			int upgradeLevel = 0;
			if(p instanceof Buildable)
				upgradeLevel = ((Buildable) p).getBuildings();
			if(p instanceof Estate)
				return new GuiProperty(p.getName(), Integer.toString(p.getPrice()),
						ColorFactory.getInstance().stringToColor(((Estate)p).getColor()), upgradeLevel);
			else 
				return new GuiProperty(p.getName(), Integer.toString(p.getPrice()),
						null, upgradeLevel);
		} else
			return new GuiSquare(square.getName());
	}

	private static GuiPlayer convertToGui(Player player){
		return new GuiPlayer(player.getName());
	}

	public void debug() {
		game.debug();

	}


}
