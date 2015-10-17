package src;


public class Game {

	public static int numOfPlayers;
	public static PrototypeBoard board;
	public static Player[] players;
	public static int currentPlayerID;
	public static Dice dice;
	public static GuiHandler gui;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		//Game Initializers
			//GUI will inform how many users there will be (unnecessary for prototype:4)
		numOfPlayers=4;
		int startMoney=0;
		
			//Board Initializers
		
			board = new PrototypeBoard();
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
			//bütün playerları idleriyle construct et. for loop
			currentPlayerID=0; // save load için burası modifiye edilecek
			//GUI INIT
			gui.Init(players, board);
			gui.displayPlayer(currentPlayer()); //input olarak Player type
		
	}
	private void userRoll() {
		// TODO Auto-generated method stub
		//Main Loop
		//GUI nereden updatelenecek???
			if(!(currentPlayer().isBankrupt())){
				
				
				
				Square temp = dice.roll(currentPlayer()); 
				gui.diceRolled(dice);// Dice alacak!!! iki int
				gui.playerMoved(temp); 
				
				//çift atma kontolu
				if(dice.isEven())
				currentPlayer().setFinished(false);
				//
				
				//pending için
				if(board.getPending()){
					if(currentPlayer()!=board.pender){
						if(temp instanceof Property){
							if(((Property) temp).getOwner()==board.pender)
							{
								currentPlayer().withdraw(50);
							}
						}
					}
				}
			
				//
				
				currentPlayer().moveTo(board, players, board.getSquareId(temp)); //player önce ilerleyecek sonra o yerin executorını çalıştıracak
				
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
		//Batarsa bir oyuncu o oyuncuyu sil(array ve int), mallarını ne yap?, kartlarını ne yap? pending bağlamı?
		//GUI bağlamında, artık o adam ekranda gözükmesin me?
		players[plID].releaseAllProperty();
		
	}
	private static void finishGame() {
		//GUI bitir ve kazananı ilan et
	}

}
