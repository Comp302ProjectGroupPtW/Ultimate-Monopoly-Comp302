package domain;


public abstract class Square {

	private String name;
	private int id;
	protected PassBehavior pb;
	
	public Square(){
		this.name=null;
		this.id=0;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void pass(Player currentPlayer){
		pb.pass(currentPlayer);
	}
	
	public abstract void squareAction(Player currentPlayer, Board board);

	@Override
	public String toString() {
		return  name;
	}
	
	
}
