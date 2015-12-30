package domain;

public class diceMoveHandler {
private Dice dice;
private Player currentPlayer;
private boolean movesLeft=false;
private GuiHandler gui;



/**
 * @requires gui the current player to be given s Player object refernc. It also needs to take GuiHandler and dice referneces
 * 
 * @param dice
 * @param currentPlayer
 * @param gui
 */
public diceMoveHandler(Dice dice, Player currentPlayer, GuiHandler gui) {
	this.dice = dice;
	this.currentPlayer = currentPlayer;
	
	this.gui = gui;
}

/**
 * @requires A refernce which point to the player who is going to make the move and the board on which the game is played.
 * @modifies The gui and enables the the rolling button
 * @effects It enables the rolling button by the gui.
 * @param pl
 * @param board
 */
public void startMoveByRolling(Player pl,Board board){
	currentPlayer=pl;
	dice= Dice.getInstance();
	//GUI'de adama bişeyler mortgagelama, bina yapma fırsatı verilsin
	gui.enableRoll(true);
	
}

/**
 * @requires The dice to be rolled and, the decision of making a monopoly rule to be made by the handler
 * @modifies The location of the Player
 * @effects It changes the location of the player and triggers the square action of the newly arrived square.
 */
public void monopolyMove(){  //nasıl???
	gui.enableMrM(false);
	//boardun monopoly movu
	Board.getInstance().moveMrm(currentPlayer);
	gui.enableEndTurn(true);
	
}
/**
 * @requires The currentPlayer must reference to the correct player, whih is desired to make a move, and the user must trigger this by pressing roll button.
 * @modifies The dice object is invoked and rolled.
 * @effects According to the outcome of the dices it will invoke either the normal move function or the Mr. Monopoly move function.
 */
public void roll(){//önce mr monopoly, sonra double
	gui.enableRoll(false);
	//GUI bilgi aktarma facevaluelar
	int x = dice.roll();
	if(dice.isThirdDouble())
	{
		movesLeft=false;
		Board.getInstance().goToJail(currentPlayer);
		gui.enableEndTurn(true);
	}
	else{
		if(dice.isDouble()){
			movesLeft=true;
			gui.enableRoll(true);
		}
		else{
			
			movesLeft=false;
			}
		Board.getInstance().move(currentPlayer,x); //move x int kadar ilerletip squareActionı çalıştırmalı
		
		
		if(dice.isMM()){ 
			gui.enableMrM(true);
			
		}
		else
			gui.enableEndTurn(true);
	}
}



}
