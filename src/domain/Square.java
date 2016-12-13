package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlTransient
//@XmlJavaTypeAdapter(SaveLoadHandler.RefAdapter.class)
@XmlSeeAlso({Property.class, Bonus.class, CommunityChest.class, Go.class, HollandTunnel.class, Jail.class,
	LuxuryTax.class, Misc.class, Payday.class, RollOnce.class, StockExchange.class, Subway.class, TaxRefund.class})
public abstract class Square {

	@XmlElement
	private String name;
	private int id;
	@XmlTransient
	protected PassBehavior pb;
	
	public Square(){
		this.name=null;
		this.id=0;
		this.pb = new DoNothingPassBehavior();
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
	
	@XmlID
	public String getXmlId(){
		return String.valueOf(id);
	}
	
	public void setXmlId(String id){
		this.id = Integer.parseInt(id);
	}
	
}
