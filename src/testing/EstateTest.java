package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.Player;
import domain.Estate;

public class EstateTest {

	@Test
	public void testBuild(){
		//This method tests if the representation invariant of the estate class holds
		//when a new instance is created, when the player can build a house on a 
		//normal condition, when the user doesn't have enough money, and when the 
		//given user to the method is not the owner of the estate.
		Estate testEstate = new Estate("TestEstate", 0, 10, 10, 10, "Blue");
		Player testPlayer = new Player(0, "TestUser", 100);
		Player testPlayer2 = new Player(1, "TestUser2", 100);
		assertTrue("Representation doesn't hold",testEstate.repOk());
		testEstate.setOwner(testPlayer);
		testEstate.build(testPlayer);
		assertTrue("Cannot build on this property", testEstate.getBuildings()==0);
		assertTrue("Cannot build on this property", testPlayer.getBalance()+testEstate.getHousePrice() == 100 );
		testPlayer.setBalance(0);
		testEstate.build(testPlayer);
		assertTrue("Player doesn't have enough money", testEstate.getBuildings()==0);
		testEstate.build(testPlayer2);
		assertTrue("Player is not the owner", testEstate.getBuildings()==0);
	}
	
	@Test
	public void testSellBack(){
		//This method tests if the representation invariant of the estate class holds
		//when a new instance is created, when the player is able to sell back a house
		//to the bank, when the user tries to sell back a house although there isn't
		//one and when the input given to the method is not the owner of the estate
		Estate testEstate = new Estate("TestEstate", 0, 10, 10, 10, "Blue");
		assertTrue("Representation doesn't hold",testEstate.repOk());
		testEstate.setHouses(1);
		Player testPlayer = new Player(0, "TestUser", 100);
		Player testPlayer2 = new Player(1, "TestUser2", 100);
		testEstate.setOwner(testPlayer);
		testEstate.sellBack(testPlayer2);
		assertTrue("Player is not the owner", testEstate.getBuildings()==0);
		testEstate.sellBack(testPlayer);
		assertTrue("Balance of user is wrong", testPlayer.getBalance() == 105);
		assertTrue("Number of houses is wrong", testEstate.getBuildings()==0);
		testEstate.sellBack(testPlayer);
		assertTrue("Cannot sell, there are no houses",testPlayer.getBalance()==105);
		assertTrue("Player is not the owner so the balance should not be increased",
				testPlayer2.getBalance()==100);
	}
	
	@Test
	public void testDemolish(){
		//This method tests if the representation invariant of the estate class holds
		//when a new instance is created, when there are houses to be demolished
		//on the estate and when there aren't any houses to demolish and assures that
		//the owner of the property doesn't get back the price of the houses back.
		Estate testEstate = new Estate("TestEstate", 0, 10, 10, 10, "Blue");
		assertTrue("Representation doesn't hold",testEstate.repOk());
		testEstate.setHouses(1);
		Player testPlayer = new Player(0, "TestUser", 100);
		testEstate.setOwner(testPlayer);
		testEstate.demolish();
		assertTrue("Balance of user is wrong", testPlayer.getBalance() == 100);
		assertTrue("Number of houses is wrong", testEstate.getBuildings()==0);
		testEstate.demolish();
		assertTrue("There are no houses to demolish",testPlayer.getBalance()==100);
	}
	
	@Test
	public void testUpdateRent(){
		//This method tests if the representation invariant of the estate class holds
		//when a new instance is created, and how the rent of the estate is changed
		//if there is a majority ownership, monopoly, and different number of houses.
		Estate testEstate = new Estate("TestEstate", 0, 10, 10, 10, "Blue");
		Player testPlayer = new Player(0, "TestUser", 100);
		testEstate.setOwner(testPlayer);
		assertTrue("Representation doesn't hold",testEstate.repOk());
		testEstate.setMajority(true);
		testPlayer.getMajorEstates().add("Blue");
		testEstate.updateRent();
		assertTrue("Rent isn't updated properly, there is majority ownership", testEstate.getRent() == 20);
		Estate testEstate2 = new Estate("TestEstate2", 1, 10, 10, 10, "Red");
		assertTrue("Representation doesn't hold",testEstate2.repOk());
		testEstate2.setOwner(testPlayer);
		testEstate2.setMonopoly(true);
		testPlayer.getMonopolyEstates().add("Red");
		testEstate2.updateRent();
		assertTrue("Rent isn't updated properly, there is monopoly", testEstate2.getRent() == 30);
		testEstate.setHouses(1);
		testEstate.updateRent();
		assertTrue("Rent isn't updated properly, there is one house", testEstate.getRent() == 16);
		testEstate.setHouses(2);
		testEstate.updateRent();
		assertTrue("Rent isn't updated properly, there are two houses", testEstate.getRent() == 19);
		testEstate.setHouses(3);
		testEstate.updateRent();
		assertTrue("Rent isn't updated properly, there are three houses", testEstate.getRent() == 21);
		testEstate.setHouses(4);
		testEstate.updateRent();
		assertTrue("Rent isn't updated properly, there are four houses", testEstate.getRent() == 22);
		testEstate.setHouses(5);
		testEstate.updateRent();
		assertTrue("Rent isn't updated properly, there are five houses", testEstate.getRent() == 23);
		testEstate.setHouses(6);
		testEstate.updateRent();
		assertTrue("Rent isn't updated properly, there are six houses", testEstate.getRent() == 35);
	}
	
	public void testDemolishAll(){
		//This method tests if the representation invariant of the estate class holds
		//when a new instance is created,when there is 2 houses to be demolished,
		//on the estate, when there is 1 house to be demolished on the estate
		//and when there aren't any houses to demolish and assures that
		//the owner of the property doesn't get back the price of the houses back.
		Estate testEstate = new Estate("TestEstate", 0, 10, 10, 10, "Blue");
		assertTrue("Representation doesn't hold",testEstate.repOk());
		testEstate.setHouses(1);
		Player testPlayer = new Player(0, "TestUser", 100);
		testEstate.setOwner(testPlayer);
		testEstate.demolishAll();
		assertTrue("Balance of user is wrong", testPlayer.getBalance() == 100);
		assertTrue("Number of houses is wrong", testEstate.getBuildings()==0);
		Estate testEstate2 = new Estate("TestEstate2", 0, 10, 10, 10, "Blue");
		assertTrue("Representation doesn't hold",testEstate2.repOk());
		testEstate2.setOwner(testPlayer);
		testEstate2.setHouses(2);
		testEstate2.demolishAll();
		assertTrue("Balance of user is wrong", testPlayer.getBalance() == 100);
		assertTrue("Number of houses is wrong", testEstate2.getBuildings()==0);
		testEstate2.demolishAll();
		assertTrue("Balance of user is wrong", testPlayer.getBalance() == 100);
		assertTrue("Number of houses is wrong", testEstate2.getBuildings()==0);
		
	}
	
}
