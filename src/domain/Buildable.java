package domain;

public interface Buildable {
	
	public int getBuildings();

	public void build(Player currentPlayer);
	
	public void sellBack(Player currentPlayer);
	
	public void demolish();
	
	public void demolishAll();
	
	public void setBuildings(int buildings);
}
