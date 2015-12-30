import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.*;

import org.junit.Test;

import domain.Company;
import domain.Player;
import domain.Share;

public class CompanyTest {
	//These initialisations and constructions of the anciallary objects are necessary in order to be able to construct the tested class's instances
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
	private Company example = new Company("Motion Pictures",100,ary){{
		Player pl = new Player(0, "Melody", 200);
		Player pl2 = new Player(1, "Amy", 100);
		Player pl3 = new Player(2, "Clara", 200);
		share1 = new Share(example);//sahibi 0idli
		share2 = new Share(this, pl, false);
		share3 = new Share(this, pl, false);
		share4 = new Share(this, pl3, false);
		
		shareList.add(share1);//sahibi 0 idli
		shareList.add(share2);
		shareList.add(share3);
	}};
	
	@Test
	public void testGetPlayer() {//This test tests if the owners of the company's shares can be returned specifically by giving the share indice.
		Player a = example.getPlayer(1);
			assertTrue("the particular owner of the share with specified id successfully returned by getPlayer(id) function",(a==share1.getOwner())&&example.repOk());
	}
	
	@Test
	public void testGetShares() {//This test tests if the shares of the company can be returned
		int num = example.getShares();
			assertTrue("total number of shares has been returned correctly",num==5&&example.repOk());
	}
	
	@Test
	public void testGetPriceOfShare() {//This test tests if the revenue value of the share can be returned
		int prc = example.getPriceOfShare(share1);
			assertTrue(" The revenue for the particular Share for  its owner returned correctly",(prc==90)&&example.repOk());
	}
	@Test
	public void testHasBuyableShare() {//This method tests if there exist any unsold share of the company.
		boolean b = example.hasBuyableShare();
			assertTrue("The company can return correctly if there has left any shares",(b==true)&&example.repOk());
	}
	@Test
	public void testBuyShare() { //THis method tests if a share has beenn succesfully sold to a player.
		Player a = example.getPlayer(1);
		int remember = example.getDividendValue()[a.getId()];
		int sizerem a.getShareList().size(); //Playerda bu method eklenmeli
		example.buyShare(a);//player a,
			assertTrue("Tests if the player has successfully bought the share",(example.getDividendValue()[a.getId()]==(remember+1)&&a.getShareList().size()==(sizerem+1)&&example.repOk());
	}
}
