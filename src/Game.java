
public class Game {

	public static int numOfPlayers;
	public Board board;
	public static Player[] players;
	public static int currentPlayerID;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		//Game Initializers
			//GUI will inform how many users there will be (unnecessary for prototype:4)
		numOfPlayers=4;
		
		
			//Board Initializers
		
			board = new Board();
			
			//Player Initializers
			players = new Player[numOfPlayers];
			//bütün playerları idleriyle construct et. for loop
			currentPlayerID=0; // save load için burası modifiye edilecek
			//GUI INIT
			GUI.Init(players, board);
			
		//Main Loop
			//GUI nereden updatelenecek???
			while(true){
				if(!(currentPlayer().isBankrupt())){
					
					currentPlayer().rollDice();
					GUI.diceRolled();// Dice alacak!!!
					currentPlayer().move(); //player önce ilerleyecek sonra o yerin executorını çalıştıracak
					GUI
					if(currentPlayer().isFinished())
						currentPlayerID = (currentPlayerID+1)%(players.length()-1);
				}
				else
					currentPlayerID = (currentPlayerID+1)%(players.length()-1);

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
