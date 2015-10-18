package src;
public class Game {

	public static int numOfPlayers;
	public static PrototypeBoard board;
	public static Player[] players;
	public static int currentPlayerID;
	public static Dice dice;
	public static GuiHandler gui;
	
	private static Game self = new Game();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		//Game Initializers
			//GUI will inform how many users there will be (unnecessary for prototype:4)
		numOfPlayers=4;
		int startMoney=2000;
		
			//Board Initializers
		
			board = new PrototypeBoard().createBoard();
			board.pender = null;
			//Dice initializer
			
			dice = new Dice(board);
			
			//Player Initializers
			players = new Player[numOfPlayers];
			String[] names = new String[4];
			names[0] = "Ali";
			names[1] = "Veli";
			names[2] = "Deli";
			names[3] = "Fethi";
			for (int i = 0; i < players.length; i++) {
				players[i] = new Player(i, names[i], startMoney, board.getSquareById(0));
			}
			//bÃ¼tÃ¼n playerlarÄ± idleriyle construct et. for loop
			currentPlayerID=0; // save load iÃ§in burasÄ± modifiye edilecek
			//GUI INIT
			gui = new GuiHandler(self);
			gui.init(players, board);
			gui.displayPlayer(currentPlayer()); //input olarak Player type
		
	}
	public void userRoll() {
		// TODO Auto-generated method stub
		//Main Loop
		//GUI nereden updatelenecek???
			if(!(currentPlayer().isBankrupt())){
				currentPlayer().setFinished(true);
				
				
				Square temp = dice.roll(currentPlayer()); 
				gui.diceRolled(dice);// Dice alacak!!! iki int
				gui.playerMoved(currentPlayer(), temp);
				currentPlayer().moveTo(board, players, board.getSquareId(temp));
				
				//Ã§ift atma kontolu
				if(dice.isEven())
				currentPlayer().setFinished(false);
				dice = new Dice(board);
				//
				
				//pending iÃ§in
				if(board.getPending()){
					if(currentPlayer()!=board.pender){
						if(temp instanceof Property){
							if(((Property) temp).getOwner()==board.pender)
							{
								currentPlayer().withdraw(50);
								board.pending=false;
							}
						}
					}
				}
			
				//
				
				currentPlayer().moveTo(board, players, board.getSquareId(temp)); //player Ã¶nce ilerleyecek sonra o yerin executorÄ±nÄ± Ã§alÄ±ÅŸtÄ±racak
				
				if(currentPlayer().isFinished()){
					currentPlayerID = (currentPlayerID+1)%(players.length-1);
					if(!(currentPlayer().isBankrupt()))
					gui.displayPlayer(currentPlayer()); 
				}
				
					
			}
			else{
				currentPlayerID = (currentPlayerID+1)%(players.length-1);
				if(!(currentPlayer().isBankrupt()))
					gui.displayPlayer(currentPlayer());
			}
		
	}
	private static Player currentPlayer() {
		// TODO Auto-generated method stub
		
		return players[currentPlayerID];
	}
	
	private static  void beBankrupt(int plID) {
		numOfPlayers--;
		if(numOfPlayers==1)
			finishGame();
		players[plID].bankrupt = true;
		//Batarsa bir oyuncu o oyuncuyu sil(array ve int), mallarÄ±nÄ± ne yap?, kartlarÄ±nÄ± ne yap? pending baÄŸlamÄ±?
		//GUI baÄŸlamÄ±nda, artÄ±k o adam ekranda gÃ¶zÃ¼kmesin me?
		players[plID].releaseAllProperty();
		if(board.pender==players[plID]){
-			board.pending=false;
-		}
		
	}
	private static void finishGame() {
		//GUI bitir ve kazananÄ± ilan et
		Player winnerPlayer=null;
		for (int i = 0; i < players.length; i++) {
			if(!players[i].isBankrupt())
				winnerPlayer = players[i];
		}
		gui.finish(winnerPlayer);
	}
	
	public static void show(String message){
		gui.showMessage(message, "Message");
	}
	
	public static boolean ask(String message){
		return gui.askYesNo(message, "Confirm");
	}

}
