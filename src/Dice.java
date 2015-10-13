import java.util.Random;


public class Dice {
	Random rd = new Random();
	private Board board;
	
	public Dice(Board board){
		this.board=board;
	}
	
	public Pair roll(Player pl){
		boolean isEven = false;
		int a = rd.nextInt(6)+1;
		int b = rd.nextInt(6)+1;
		if(a==b) isEven = true;
		Square location = pl.getLocation();
		int current = board.getSquareId(location);
		int target = (current + a + b) % 20;		
		return new Pair(board.getSquareById(target), isEven);		
	}
	
}
