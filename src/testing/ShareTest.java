import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Company;
import domain.Player;
import domain.Share;

public class ShareTest {
	//These initialisations and constructions of the anciallary objects are necessary in order to be able to construct the tested class's instances.

	Player pl;
	Player pl2 ;
	Player pl3;
	private int[] ary = new int[]{10,40,90,160,250};
	private Share share1 ;
	private Share share2 ;
	private Share share3 ;
	private Share share4 ;
	private List<Integer> shareByPlayer = new ArrayList<Integer>(){{;
	shareByPlayer.add(2);
	shareByPlayer.add(0);
	shareByPlayer.add(1);}};
	private ArrayList<Share> shareList = new ArrayList<Share>();
	private int[] dividendValue = new int[]{3,0,1};
	private Company company1 = new Company(shareByPlayer,"Motion Pictures",100,50,ary,4,shareList){{
		Player pl = new Player(0, "Melody", 200);
		Player pl2 = new Player(1, "Amy", 100);
		Player pl3 = new Player(2, "Clara", 200);
		share1 = new Share(this, pl, false);//sahibi 0idli
		share2 = new Share(this, pl, false);
		share3 = new Share(this, pl, false);
		share4 = new Share(this, pl3, false);
		
		shareList.add(share1);//sahibi 0 idli
		shareList.add(share2);
		shareList.add(share3);
	}};
	
	private Share example = new Share(company1, pl, false);
	
	@Test
	public void testSetOwner() { //This method tests if the owner of the share has been modified correctly.
		example.setOwner(pl2);
		assertTrue("Test successfull. The owner of the share has been modified",example.getOwner()==pl2);
	}
	@Test
	public void testGetCompany() { //This method tests if the owner of the company has been returned correctly.
		assertTrue("Test successfull. Associated Company is returned ",example.getCompany()==company1);

	}
	@Test
	public void testGetMortgagedPrice() { //This method tests if the loan prices of the shares has been returned correctly.
		assertTrue("Test successfull. The price of Mortgade/loan has returned correctly ",example.getMortgagedPrice()==50);

	}

}
