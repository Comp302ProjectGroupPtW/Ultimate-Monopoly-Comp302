package domain;
import java.util.Random;


public class SqueezePlay extends Square {

	public SqueezePlay(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}
	public SquuezePlay(){
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		Player[] players = Game.getInstance().getPlayers();
		Random r = new Random();
		int num = r.nextInt(11)+1;
		GuiHandler.getInstance().showMessage("The dice you rolled is: " + num, "Squeeze Play");
		if(num>=5 && num<=9){
			for(int i=0; i<players.length; i++){
				currentPlayer.transfer(players[i], 50);
			}
		} else if(num==3 || num==4){
			for(int i=0; i<players.length; i++){
				currentPlayer.transfer(players[i], 100);
			}
		} else if(num==10 || num==11){
			for(int i=0; i<players.length; i++){
				currentPlayer.transfer(players[i], 100);
			}
		} else if(num==2 || num==12){
			for(int i=0; i<players.length; i++){
				currentPlayer.transfer(players[i], 200);
			}
		}
	}

}
