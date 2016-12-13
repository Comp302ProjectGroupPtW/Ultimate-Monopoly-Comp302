package domain;

import javax.xml.bind.annotation.XmlElement;

public interface Buildable {
	
	@XmlElement //Not Sure
	public int getBuildings();

	public void build(Player currentPlayer);
	
	public void sellBack(Player currentPlayer);
	
	public void demolish();
	
	public void demolishAll();
	
	public void setBuildings(int buildings);
}