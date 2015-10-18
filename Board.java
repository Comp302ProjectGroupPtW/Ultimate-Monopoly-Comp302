

import java.util.ArrayList;


public class Board { //superclass
	private ArrayList<Square> Squares = new ArrayList<Square>(); 
	public boolean pending = false; //renovation success adlı community chest için
	public Player pender;

	public void addSquare(Square sq){
		Squares.add(sq);
	}
	
	public ArrayList<Square> getSquares(){
		return Squares;
	}

	public Square getSquareById(int i){
		return Squares.get(i);
	}

	public int getSquareId(Square sq){
		return Squares.indexOf(sq);
	}
	
	public void switchPending(){ //tersine çeviriyor
		pending = !pending;
	}
	
	public boolean getPending(){
		return pending;
	}
	
}
