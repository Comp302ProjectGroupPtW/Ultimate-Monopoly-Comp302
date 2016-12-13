package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
@XmlAccessorType(XmlAccessType.FIELD)
public class Share {
	@XmlIDREF
	private Company company;
	@XmlIDREF
	private Player owner;
	private boolean mortgaged;
	/**
	 * @requires The corresponding registered company to be given as input, and the owner the share must be in the game.
	 * @param company
	 * @param owner
	 * @param mortgaged
	 */
	public Share(Company company) {
		this.company = company;
		setMortgaged(false);
	}
	public Share(){
	}
	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	/**
	 * @return the mortgaged
	 */
	public boolean isMortgaged() {
		return mortgaged;
	}
	/**
	 * @param mortgaged the mortgaged to set
	 */
	public void setMortgaged(boolean mortgaged) {
		this.mortgaged = mortgaged;
	}

	/**
	 * @requires The player to invoke mortgaging button in gui.
	 * @return The loan value of the Share
	 */
	public int getMortgagedPrice(){
		return company.getLoanValue();

	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Share [Company :" + company + ", Owner :" + owner + ", Is Mortgaged? :" + isMortgaged()
				+ ", The Mortgaged Price? :" + getMortgagedPrice() + "]";
	}
	// EFFECTS: Returns true if the rep invariant holds 	
	// for this; otherwise returns false. 
	/**
	 * @effects Returns true if the rep invariant holds for this; otherwise returns false. 
	 * @return boolen true or false
	 */
	public boolean repOk(){
		if(getMortgagedPrice()<0)
			return false;
		if(!(company instanceof Company))
			return false;
		if(company == null)
			return false;
		return true;
	}
	
	@XmlID
	public String getXmlId(){
		return toString();
	}

	public void setXmlId(String id){
		//Nothing.
	}
}
