


public class PrototypeBoard extends Board {
	
	
	// Property(String name, int basePrice, int BaseRent, String color)
	// CommonCard(String name)
	

	public void createBoard(){
		Board br = new Board(); 
		br.addSquare(new Misc("Go")); 										//0
		br.addSquare(new Property("Oriental Ave", 100, 6, "Blue"));			//1 
		br.addSquare(new CommonCard("Community Chest"));					//2
		br.addSquare(new Property("Vermont Ave", 100, 6, "Blue"));			//3
		br.addSquare(new Property("Connecticut Ave", 120, 8, "Blue"));		//4
		br.addSquare(new Misc("Roll once"));								//5
		br.addSquare(new Property("St.Charles Place", 140, 10, "Pink"));	//6
		br.addSquare(new CommonCard("Chance"));								//7
		br.addSquare(new Property("States Avenue", 140, 10, "Pink"));		//8
		br.addSquare(new Property("Virginia Ave", 160, 12, "Pink"));		//9
		br.addSquare(new Misc("Free Parking"));								//10
		br.addSquare(new Property("St. James Place", 180, 14, "Orange"));	//11
		br.addSquare(new CommonCard("Community Chest"));					//12
		br.addSquare(new Property("Tennessee Ave", 180, 14, "Orange")); 	//13
		br.addSquare(new Property("New York Ave", 200, 16, "Orange"));  	//14
		br.addSquare(new Misc("Squeeze Play"));								//15
		br.addSquare(new Property("Pacific Ave", 300, 26, "Green"));		//16
		br.addSquare(new Property("North Carolina Ave", 300, 26, "Green"));	//17
		br.addSquare(new CommonCard("Chance"));								//18
		br.addSquare(new Property("Pennsylvania Ave", 320, 28, "Green"));	//19
		br.addPending();  //şimdilik tek pending kartı olduğu için tek elemanlı 
		
	}
		
		
		
		
		
		
		return br;
	}

}
