import java.util.ArrayList;


public class Board { //inherits other boards
	private ArrayList<Square> Squares = new ArrayList<Square>(); 
	private ArrayList<Boolean> pending = new ArrayList<Boolean>(); //pending kartlarının aktif olup olmadığını tutuyor

	public void addSquare(Square sq){
		Squares.add(sq);
	}

	public Square getSquareById(int i){
		return Squares.get(i);
	}

	public int getSquareId(Square sq){
		return Squares.indexOf(sq);
	}
	
	public void addPending(){
		pending.add(false);
	}
	public void setPending(int index, boolean boo){
		pending.set(index, boo);
	}

}
