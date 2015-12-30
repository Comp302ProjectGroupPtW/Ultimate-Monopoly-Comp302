package domain;


public class BirthdayGift extends Square {

	public BirthdayGift(int id){
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}

	@Override
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		boolean get = GuiHandler.getInstance().askYesNo("Do you want to get 100$ (else you will be moved to the nearest cab station)","Buy Property");
		if(get){
			currentPlayer.deposit(100);
		}
		else {
			Board.getInstance().moveDirect(currentPlayer, Board.getInstance().getSquareById(68));
		}
	}

}
