

import java.util.Random;


public class Dice {
	Random rd = new Random();
	private Board board;
	private int lastDie1 = 0; //null bırakıp testingde random error yemeyelim diye initialize ediyorum 0 olarak.
	private int lastDie2 = 0;
	
	public Dice(Board board){
		this.board=board;
	}
	
	public boolean isEven(){ //Fatih dice.isEven() false ise sonraki playera geçecek, değilse aynı player oynayacak.
		return (lastDie1 == lastDie2);
	}
	
	
	
	public int getLastDie1() { //yalnızca ata kullanacak.
		return lastDie1;
	}

	public int getLastDie2() { //yalnızca ata kullanacak.
		return lastDie2;
	}

	public Square roll(Player pl){
		int a = rd.nextInt(6)+1;
		int b = rd.nextInt(6)+1;
		lastDie1 = a;
		lastDie2 = b;
		Square location = pl.getLocation();
		int current = board.getSquareId(location);
		int target = (current + a + b) % 20;		
		return board.getSquareById(target);		
	}
	
}
