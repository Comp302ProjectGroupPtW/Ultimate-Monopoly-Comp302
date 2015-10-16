
public abstract class Square {

	private String name;
	
	
	public Square(){
		this.name=null;
	}
	
	public Square(String name){
		this.name=name;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public abstract void squareAction(Player p, Player[]players);
	
}
