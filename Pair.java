
public class Pair {

	private Square sq;
	private boolean isEven;
	
	public Pair(Square sq, boolean isEven){
		this.sq = sq;
		this.isEven = isEven;
	}
	
	public Square getSquare(){
		return sq;
	}
	
	public boolean isEven(){
		return isEven;
	}
	
}
