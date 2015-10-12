import java.util.ArrayList;


public class Board { //superclass
	private ArrayList<Square> Squares; 

	public void addSquare(Square sq){
		Squares.add(sq);
	}

	public Square getSquareById(int i){
		return Squares.get(i);
	}

	public int getSquareId(Square sq){
		return Squares.indexOf(sq);
	}

}
