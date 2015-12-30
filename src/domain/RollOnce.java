package domain;
import java.util.Random;


public class RollOnce extends Square {

	public RollOnce(String name, int id){
		this.setName(name);
		this.setId(id);
		pb = new DoNothingPassBehavior();
	}
	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int a = r.nextInt(5)+1;
		GuiHandler.getInstance().showMessage("The card you picked has the number: " + a, "");  
		int b = r.nextInt(5)+1;
		GuiHandler.getInstance().showMessage("The dice you rolled is: " + b, "");     
		if(a==b){
			currentPlayer.deposit(100);
		}
	}
	
}
