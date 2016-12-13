package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;

public abstract class Card {
	
	@XmlID
	private String name;
	@XmlElement
	private String description;
	
	private boolean pending;
	@XmlElement
	private boolean keeping;
	
	Card(String name, String description){
		this.name = name;
		this.description = description;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	// interfaces ? lel
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}
	public boolean isKeeping() {
		return keeping;
	}
	public void setKeeping(boolean keeping) {
		this.keeping = keeping;
	}
	
	public abstract void cardAction();

	@Override
	public String toString() {
		return name + " " +getClass().getSimpleName() + ", pending=" + pending + ", keeping="
				+ keeping + "]";
	}
	
}
