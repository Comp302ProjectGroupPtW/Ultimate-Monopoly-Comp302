import java.util.Random;
//Dice biased bir şekilde 2 ve 3 gelecek gibi atılabilsin.
//GUI adaptasyon!!(iki sayı da görünecek)
//eğer çift atılırsa playerın notFinished() fonksiyonu çağrılsın.
//zar yüzleri için fieldlar ve getterları

public class Dice {
	Random rd = new Random();
	private Board board;
	
	
	public Dice(Board board){
		this.board=board;
	}
	
	public Square roll(Player pl){
		int a = rd.nextInt(6)+1;
		int b = rd.nextInt(6)+1;
		Square location = pl.getLocation();
		int current = board.getSquareId(location);
		int target = (current + a + b) % 20;		
		return board.getSquareById(target);		
	}
	
}
