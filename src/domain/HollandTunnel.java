package domain;

import domain.Board;
import domain.DoNothingPassBehavior;
import domain.Player;
import domain.Square;

/**
 * 
 * @author Elif
 * The HollandTunnel class creates a HollandTunnel square which transports the user to the
 * other end of the Holland Tunnel which is another HollandTunnel object on the board. 
 *
 */

public class HollandTunnel extends Square {
	private Square destination;
	
	/**
	 * Creates a HollandTunnel object that takes a name and an id.
	 * @param name name of the tunnel as a string
	 * @param id id of the tunnel as an integer
	 */
	public HollandTunnel(String name, int id){
		this.setName(name);
		this.setId(id);
		this.pb= new DoNothingPassBehavior();
	}
	
	@Override
	public String toString() {
		//return "HollandTunnel [destination=" + destination + "]";
		return "HollandTunnel";
	}

	/**
	 * @effects returns the destination of the Holland Tunnel where the player 
	 * will be transported to.
	 * @return destination - destination of the HollandTunnel as a Square object
	 */
	
	public Square getDestination() {
		return destination;
	}

	/**
	 * @modifies this
	 * @param destination square
	 * @effects sets the destination of the HollandTunnel, nothing happens if the Square given
	 * is not a valid square. 
	 */
	
	public void setDestination(Square destination) {
		this.destination = destination;
	}
	
	/**
	 * @effects checks the representation invariant of the Holland Tunnel
	 * @return boolean 
	 */

	public boolean repOk(){
		if(this.getName().equals(null) || this.getId()<0){
			return false;
		} return true;
	}
	
	@Override
	/**
	 * @modifies currentPlayer
	 * @param currentPlayer, players, board
	 * @effects moves the currentPlayer to destination square
	 */
	
	public void squareAction(Player currentPlayer, Board board) {
		// TODO Auto-generated method stub
		board.moveDirectWithoutSquareAction(currentPlayer, this.destination);
	}

}
