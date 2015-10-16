
public class Game {

	public static int numOfPlayers;
	public PrototypeBoard board;
	public static Player[] players;
	public static int currentPlayerID;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		//Game Initializers
			//GUI will inform how many users there will be (unnecessary for prototype:4)
		numOfPlayers=4;
		
		
			//Board Initializers
		
			board = new PrototypeBoard();
			
			//Dice initializer
			
			Dice dice = new Dice(board);
			
			//Player Initializers
			players = new Player[numOfPlayers];
			//bütün playerları idleriyle construct et. for loop
			currentPlayerID=0; // save load için burası modifiye edilecek
			//GUI INIT
			GuiHandler.Init(players, board);
			GuiHandler.displayPlayer(currentPlayer()); //input olarak Player type
		
	}
	private static void userRoll() {
		// TODO Auto-generated method stub
		//Main Loop
		//GUI nereden updatelenecek???
			if(!(currentPlayer().isBankrupt())){
				
				Square temp = Dice.rollDice(currentPlayer()); 
				GuiHandler.diceRolled(dice);// Dice alacak!!! iki int
				GuiHandler.playerMoved(temp) //int indsiverecek
				currentPlayer().move(temp); //player önce ilerleyecek sonra o yerin executorını çalıştıracak
				
				if(currentPlayer().isFinished()){
					currentPlayerID = (currentPlayerID+1)%(players.length()-1);
					if(!(currentPlayer().isBankrupt()))
					GuiHandler.displayPlayer(currentPlayer()); 
				}
				
					
			}
			else{
				currentPlayerID = (currentPlayerID+1)%(players.length()-1);
				if(!(currentPlayer().isBankrupt()))
					GuiHandler.displayPlayer(currentPlayer());
			}
		
	}
	private static Player currentPlayer() {
		// TODO Auto-generated method stub
		
		return players[currentPlayerID];
	}
	
	private static  beBankrupt(int plID) {
		numOfPlayers--;
		if(numOfPlayers==1)
			finishGame();
		players[plID].bankrupt = true;
		//Batarsa bir oyuncu o oyuncuyu sil(array ve int), mallarını ne yap?, kartlarını ne yap? pending bağlamı?
		//GUI bağlamında, artık o adam ekranda gözükmesin me?
		players[plID].releaseAllProperties();
		
	}
	private static void finishGame() {
		// Oyunu bitir
		// GUI!!!
	}

}
