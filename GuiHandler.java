

import java.awt.Color;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

public class GuiHandler {

	private Game game;
	private AppWindow window;

	private Board board;

	private LinkedHashMap<Player, GuiPlayer> playerToGuiMap;
	private LinkedHashMap<Square, GuiSquare> squareToGuiMap;

	public GuiHandler(Game game) {
		this.game = game;
	}

	public void init(Player[] players, Board board){

		this.board = board;

		playerToGuiMap = new LinkedHashMap<Player, GuiPlayer>();
		bindToGui(players);

		squareToGuiMap = new LinkedHashMap<Square, GuiSquare>();
		bindToGui(board.getSquares().toArray(new Square[1]));

		window = new AppWindow(this, getGuiSquaresOrdered(), getGuiPlayersOrdered());
		window.init();
		initPlayers();
		updateBalances();

	}

	public void diceRolled(Dice dice){
		String message = "You rolled: " + dice.getLastDie1() + ", " + dice.getLastDie2();
		String title = "Dice";
		JOptionPane.showMessageDialog(window, message, title, JOptionPane.PLAIN_MESSAGE);
	}

	public void playerMoved(Player player, Square square){
		GuiPlayer gp = getGui(player);
		((GuiSquare) gp.getParent().getParent()).removePlayer(gp);
		getGui(square).addPlayer(gp);
		window.invalidate();
	}

	public void displayPlayer(Player player){
		window.getGamePanel().setCurrentPlayer(player.getName());
	}

	public boolean askYesNo(String message, String title){
		int selection = JOptionPane.showConfirmDialog(window, message, title, JOptionPane.YES_NO_OPTION);
		return selection == JOptionPane.YES_OPTION;
	}

	public String askForInput(String message, String title){
		return JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE);
	}

	public void showMessage(String message, String title){
		JOptionPane.showMessageDialog(window, message, title, JOptionPane.PLAIN_MESSAGE);
	}

	public void updateBalances(){
		String[] names = new String[playerToGuiMap.size()];
		String[] balances = new String[playerToGuiMap.size()];
		int i = 0;
		for (Player player : playerToGuiMap.keySet()) {
			names[i] = player.getName();
			balances[i] = Integer.toString(player.getMoney());
			i++;
		}

		window.getGamePanel().setBalances(names, balances);
	}
	
	public void finish(Player player){
		showMessage("Congratulations " + player.getName() + " you have won the game!", "Game Over");
	}
	
	public void userRoll(){
		game.userRoll();
	}

	private GuiPlayer[] getGuiPlayersOrdered(){//TODO Check the ordering.
		GuiPlayer[] players = new GuiPlayer[playerToGuiMap.size()];
		int i = 0;
		for (GuiPlayer gp : playerToGuiMap.values()) {
			players[i++] = gp;
		}
		return players;
	}

	private GuiSquare[] getGuiSquaresOrdered(){//TODO Check the ordering.
		GuiSquare[] squares = new GuiSquare[squareToGuiMap.size()];
		int i = 0;
		for (GuiSquare gs : squareToGuiMap.values()) {
			squares[i++] = gs;
		}
		return squares;
	}

	private void initPlayers(){
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
			squareToGuiMap.put(square, convertToGui(square));
		}
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
			return new GuiProperty(p.getName(), Integer.toString(p.getPrice()), stringToColor(p.getColor()));
		} else
			return new GuiSquare(square.getName());
	}

	private static GuiPlayer convertToGui(Player player){
		return new GuiPlayer(player.getName());
	}

	private static Color stringToColor(String name){ //There's something wrong if this returns Magenta.
		if(name.equalsIgnoreCase("Blue"))
			return Color.BLUE;
		if(name.equalsIgnoreCase("Green"))
			return Color.GREEN;
		if(name.equalsIgnoreCase("Pink"))
			return Color.PINK;
		if(name.equalsIgnoreCase("Orange"))
			return Color.ORANGE;
		return Color.MAGENTA;
	}
}
