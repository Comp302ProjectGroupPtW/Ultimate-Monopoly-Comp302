


public abstract class Square {

	private String name;
	
	
	public Square(){
		this.name=null;
	}
	
	public Square(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void squareAction(Player p, Player[]players, Board b);
	
}
