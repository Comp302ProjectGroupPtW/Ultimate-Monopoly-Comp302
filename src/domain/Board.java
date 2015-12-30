package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Board class holds {@link Square} objects, pool, {@link ChanceCard} and {@link CommunityCard} objects in the game.
 * This class is responsible from the movement of the {@link Player} objects on the Board.
 *
 */
public class Board {
	private static Board instance = new Board();
	private int pool;
	private Square[][] squares;
	private LinkedList<CommunityCard> communityCards;
	private LinkedList<ChanceCard> chanceCards;
	private HashMap<String, Integer> map;
	private Company[] companyArray;

	/**
	 * Creates a Board object filled with squares, chance and community cards
	 * specified by Ultimate Monopoly game rules.
	 */
	protected Board(){
		pool = 0;
		squares = new Square[3][61];
		communityCards = new LinkedList<CommunityCard>();
		chanceCards = new LinkedList<ChanceCard>();
		map = new HashMap<String, Integer>();
		companyArray = new Company[6];
		createBoard();
		fillHashMap();			
		fillChanceCards();
		fillCommunityChestCards();
		fillCompanyArray();
		shuffleChanceCards();
		shuffleCommunityCards();
	}

	private void fillChanceCards(){
		addToChanceCards(new Hurricane());
		addToChanceCards(new MardiGras());
		addToChanceCards(new PropertyTaxes());
	}

	private void fillCommunityChestCards(){
		addToCommunityCards(new HouseCondemned());
		addToCommunityCards(new SpecialOnlinePricing());
		addToCommunityCards(new StreetRepairs());
	}
	private void fillCompanyArray(){		
		companyArray[0] = new Company("Motion Pictures", 100, new int[] {10,40,90,160,250});
		companyArray[1] = new Company("Allied Steamships", 110, new int[] {11,44,99,176,275});
		companyArray[2] = new Company("National Utilities", 120, new int[] {12,48,108,192,300});
		companyArray[3] = new Company("General Radio", 130, new int[] {13,52,117,208,325});
		companyArray[4] = new Company("United Railways", 140, new int[] {14,56,126,224,350});
		companyArray[5] = new Company("American/Acme Motors", 150, new int[] {15,60,135,240,375});
	}
	
	public Company[] getCompanyArray(){
		return companyArray;
	}
	
		public Square getHighestRentInLayer(int layer){ //0,1 or 2, içten dışa respectively.
		Square sq = squares[0][0]; //if "go" square returns,there is no owned square with rent.
		int rent = 1;
		for (int j = 0; j < squares[layer].length; j++) {
			Square current = squares[layer][j];
			if(current instanceof Property){
				Property property = (Property) current;
				if(property.getOwner()!=null && property.getRent()>rent) sq = current;
			}			
		}
		return sq;
	}
	
		public ArrayList<Square> getUnownedProperties(){
		ArrayList<Square> unownedProperties = new ArrayList<Square>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				Square sq = squares[i][j];
				if(sq instanceof Property){
					Property p=(Property)sq;
					if(p.getOwner() == null) unownedProperties.add(sq);
				}
			}
		}
		return unownedProperties;
	}
	
	


	/**
	 * This method provides a unique way of access to the singleton Board instance.
	 * 
	 * @effects Returns the singleton Board instance, if not have done so already, first initializes it.
	 * 
	 * @return The singleton Board instance
	 */
	public static Board getInstance(){
		return instance;
	}
	/**
	 * Adds a {@link CommunityCard} instance to the collection of Community Cards.
	 * 
	 * @requires {@code card} is not null.
	 * 
	 * @param card Card to be appended to the pile of Community Cards.
	 * 
	 * @modifies communityCards - Collection holding {@link CommunityCard} instances
	 * 
	 * @effects Adds {@code card} to the collection of Community Cards, if requirement is not met,
	 * throws {@link IllegalArgumentException}.
	 * 
	 */
	public void addToCommunityCards(CommunityCard card){
		if(card == null)
			throw new IllegalArgumentException();
		communityCards.add(card);
	}

	/**
	 * Adds a {@link ChanceCard} instance to the collection of Chance Cards.
	 * 
	 * @requires {@code card} is not null.
	 * 
	 * @param card Card to be appended to the pile of Chance Cards.
	 * 
	 * @modifies chanceCards - Collection holding {@link ChanceCard} instances
	 * 
	 * @effects Adds {@code card} to the collection of Chance Cards, if requirement is not met,
	 * throws {@link IllegalArgumentException}.
	 */	
	public void addToChanceCards(ChanceCard card){
		if(card == null)
			throw new IllegalArgumentException();
		chanceCards.add(card);
	}

	private void shuffleCommunityCards(){
		Collections.shuffle(communityCards);
	}

	private void shuffleChanceCards(){
		Collections.shuffle(chanceCards);
	}
	/**
	 * Simulates picking a Community Card from a pile of Community Cards.
	 * 
	 * @modifies communityCards - Collection holding {@link CommunityCard} instances
	 * 
	 * @effects Retrieves and removes the first card in the collection of
	 * 			Community Cards or null if it is empty.
	 * 
	 * @return A {@link CommunityCard} instance or null.
	 */
	public CommunityCard pickACommunityCard(){
		return communityCards.poll();
	}
	/**
	 * Simulates picking a Chance Card from a pile of Chance Cards.
	 * 
	 * @modifies this.chanceCards - Collection holding {@link ChanceCard} instances
	 * 
	 * @effects Retrieves and removes the first card in the collection of
	 * 			Chance Cards or null if it is empty.
	 * 
	 * @return A {@link ChanceCard} instance or null.
	 */
	public ChanceCard pickAChanceCard(){
		return chanceCards.poll();
	}
	/**
	 * Finds a {@link Square} object in this Board from its id.
	 * 
	 * 
	 * @param id Id of a {@link Square} object
	 * 
	 * @effects Returns a {@link Square} object in the board with the id given as the argument if it is contained
	 * 			in this Board, else returns null.
	 * 
	 * @return A {@link Square} object or null
	 */
	public Square getSquareById(int id){		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				Square sq = squares[i][j];
				if(sq != null){
					if(sq.getId() == id) return sq;					
				}				
			}			
		}
		return null;
	}

	/**
	 * Finds the index of layer which is holding a specific {@link Square} object.
	 * 
	 * @param sq The Square to be searched.
	 * 
	 * @effects If {@code sq} is contained in this Board, returns its layer number
	 * between 0 and 2 (inclusive) else returns -1.
	 * 
	 * @return A layer number or -1
	 */
	public int getLayerNum(Square sq){ // returns 0, 1 or 2
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				Square sqTraverse = squares[i][j];
				if(sq != null){
					if(sq == sqTraverse) return i;					
				}				
			}			
		}
		return -1;
	}

	/**
	 * Finds the position of a {@link Square} object in a given layer.
	 * 
	 * 
	 * @param sq The Square to be searched.
	 * @param layer Index of a layer in this Board.
	 * 
	 * @effects If {@code sq} is present in {@code layer} returns its index at {@code layer}
	 * 			else returns -1.
	 * 
	 * @return A position or -1
	 */
	public int getPositionInLayer(Square sq, int layer){
		for (int i = 0; i < getLayerLength(layer); i++) {
			if(squares[layer][i] == sq) return i;
		}
		return -1;
	}

	/**
	 * Gives {@link Square} objects contained in this Board.
	 * 
	 * @effects Returns an array {@link Square} objects contained in this Board.
	 * Each call to this method returns a new array.
	 * (The order of the {@link Square} objects are preserved.)
	 * 	
	 * @return A 2-dimensional {@link Square} array
	 */
	public Square[][] getSquares(){
		return squares.clone();
	}


	/**
	 * Finds the length of a desired layer in this Board.
	 * 
	 * @param layerNum Index of the desired layer.
	 * 
	 * @effects If {@code layerNum} is between 0 and 2 (inclusive), returns its length else returns 0.
	 * 
	 * @return Length of a layer or 0.
	 */
	public int getLayerLength(int layerNum){ //0, 1 or 2
		if(layerNum==0) return 24;
		if(layerNum==1) return 40;
		if(layerNum==2) return 56;
		return 0;
	}
	/**
	 * Finds the {@link Square} instance at the desired position in the desired layer.
	 * 
	 * @requires 0 &lt;= {@code layer} &lt; Number of Layers and
	 * 			 0 &lt;= {@code position} &lt; Length of {@code layer}
	 * 
	 * @param layer Desired Layer Number
	 * @param position Desired Position in {@code layer}
	 * 
	 * @effects Returns {@link Square} object at the desired Position in desired Layer.
	 * 
	 * @return A {@link Square} object
	 */
	public Square getSquareAt(int layer, int position){
		return squares[layer][position];
	}

	private void fillHashMap(){
		map.put("purple", 2);
		map.put("light blue", 3);
		map.put("pink", 3);
		map.put("orange", 3);
		map.put("red", 3);
		map.put("yellow", 3);
		map.put("green", 3);
		map.put("blue", 2);
		map.put("white", 3);
		map.put("black", 3);
		map.put("silver", 3);
		map.put("brown", 3);
		map.put("light pink", 3);
		map.put("light green", 4);
		map.put("cream", 4);
		map.put("teal", 4);
		map.put("wine", 4);
		map.put("gold", 4);
		map.put("peach", 4);
		map.put("maroon", 3);
	}

	/**
	 * Gives the colors of {@link Estate} instances contained in this Board.
	 * 
	 * @effects Returns an array containing colors of {@link Estate} objects contained in this Board.
	 * 
	 * @return A {@link String}[][]
	 */
	public String[] getColorArray(){
		return map.keySet().toArray(new String[1]);
	}

	/**
	 * Finds how many {@link Estate} instances with the given color are contained in this Board.
	 * 
	 * @requires {@code colorName} must be in lower case.
	 * 
	 * @param colorName Name of the desired color (in lower case).
	 * 
	 * @effects Number of {@link Estate} objects with desired color.
	 * 
	 * @return A non-negative integer.
	 */
	public int getColorAmount(String colorName){
		try {
			return map.get(colorName);
		} catch (Exception e) {
			return 0;
		}
	}
	/**
	 * Creates an Ultimate Monopoly board.
	 * 
	 * @modifies this.squares
	 * 
	 * @effects Creates and adds the {@link Square} objects specified in Ultimate Monopoly game rules.
	 * 
	 * 
	 */
	private void createBoard(){
		squares[0] = new Square[24];
		squares[1] = new Square[40];
		squares[2] = new Square[56];

		//2nd layer
		squares[1][0]  = new Go("Go", 0);
		squares[1][1]  = new Estate("Mediterranean Avenue", 1, 2, 60, 50, "purple"); //name, id, rent, price, house cost, color
		squares[1][2]  = new CommunityChest(2); //id
		squares[1][3]  = new Estate("Baltic Avenue", 3, 4, 60, 50, "purple"); 	
		squares[1][4]  = new IncomeTax("Income Tax", 4);
		TransitStation reading = new TransitStation("Reading Railroad", 5); //name, id
		squares[1][5]  = reading;
		squares[1][6]  = new Estate("Oriental Avenue", 6, 6, 100, 50, "light blue");
		squares[1][7]  = new Chance(7); //id
		squares[1][8]  = new Estate("Vermont Avenue", 8, 6, 100, 50, "light blue"); 
		squares[1][9]  = new Estate("Connecticut Avenue", 9, 8, 120, 50, "light blue"); 
		squares[1][10] = new Misc("Jail(Just Visiting)", 10);
		squares[1][11] = new Estate("St. Charles Place", 11, 10, 140, 100, "pink");
		squares[1][12] = new Utility("Electric Company", 12); //(fiyatlar� class�n i�inde)
		squares[1][13] = new Estate("States Avenue", 13, 10, 140, 100, "pink");
		squares[1][14] = new Estate("Virginia Avenue", 14, 12, 160, 100, "pink");
		TransitStation pennsyl = new TransitStation("Pennsylvania Railroad", 15); //(fiyatlar� class�n i�inde)
		squares[1][15] = pennsyl;
		squares[1][16] = new Estate("St. James Place", 16, 14, 180, 100, "orange");
		squares[1][17] = new CommunityChest(17);
		squares[1][18] = new Estate("Tennessee Avenue", 18, 14, 180, 100, "orange");
		squares[1][19] = new Estate("New York Avenue", 19, 16, 200, 100, "orange");
		squares[1][20] = new Misc("Free Parking", 20);
		squares[1][21] = new Estate("Kentucky Avenue", 21, 18, 220, 150, "red");
		squares[1][22] = new Chance(22);
		squares[1][23] = new Estate("Indiana Avenue", 23, 18, 220, 150, "red");
		squares[1][24] = new Estate("Illinois Avenue", 24, 20, 240, 150, "red");
		TransitStation BnO = new TransitStation("B&O Railroad", 25);
		squares[1][25] = BnO; 
		squares[1][26] = new Estate("Atlantic Avenue", 26, 22, 260, 150, "yellow");
		squares[1][27] = new Estate("Ventnor Avenue", 27, 22, 260, 150, "yellow");
		squares[1][28] = new Utility("Water Works", 28);
		squares[1][29] = new Estate("Marvin Gardens", 29, 24, 280, 150, "yellow");
		squares[1][30] = new RollOnce("Roll Once", 30);
		squares[1][31] = new Estate("Pacific Avenue", 31, 26, 300, 200, "green");
		squares[1][32] = new Estate("North Carolina Avenue", 32, 26, 300, 200, "green");
		squares[1][33] = new CommunityChest(33);
		squares[1][34] = new Estate("Pennsylvania Avenue", 32, 28, 320, 200, "green");
		TransitStation shrt = new TransitStation("Short Line", 35);
		squares[1][35] = shrt;
		squares[1][36] = new Chance(36);
		squares[1][37] = new Estate("Park Place", 37, 35, 350, 200, "blue");
		squares[1][38] = new LuxuryTax("Luxury Tax", 38);
		squares[1][39] = new Estate("Board Walk", 39, 50, 400, 200, "blue");
		//1st layer
		squares[0][0]  = new SqueezePlay("Squeeze Play", 40);
		squares[0][1]  = new Estate("The Embarcadero", 41, 17, 210, 100, "white");
		squares[0][2]  = new Estate("Fisherman's Wharf", 42, 21, 250, 100, "white");
		squares[0][3]  = new Utility("Telephone Company", 43);
		squares[0][4]  = new CommunityChest(44);
		squares[0][5]  = new Estate("Beacon Street", 45, 30, 330, 200, "black");
		squares[0][6]  = new Bonus("BONUS", 46);
		squares[0][7]  = new Estate("Boylston Street", 47, 30, 330, 200, "black");
		squares[0][8]  = new Estate("Newsbury Street", 48, 40, 380, 200, "black");
		squares[0][9]  = pennsyl;
		squares[0][10] = new Estate("Fifth Avenue", 49, 60, 430, 300, "silver");
		squares[0][11] = new Estate("Madison Avenue", 50, 60, 430, 300, "silver");
		squares[0][12] = new StockExchange("Stock Exchange", 51);
		squares[0][13] = new Estate("Wall Street", 52, 80, 500, 300, "silver");
		squares[0][14] = new TaxRefund("Tax Refund", 53);
		squares[0][15] = new Utility("Gas Company", 54);
		squares[0][16] = new Chance(55);
		squares[0][17] = new Estate("Florida Avenue", 56, 9, 130, 50, "brown");

		HollandTunnel h1 = new HollandTunnel("Holland Tunnel", 57);
		HollandTunnel h2 = new HollandTunnel("Holland Tunnel", 75);
		h1.setDestination(h2);
		h2.setDestination(h1);

		squares[0][18] = h1;
		squares[0][19] = new Estate("Miami Avenue", 58, 9, 130, 50, "brown");
		squares[0][20] = new Estate("Biscayne Avenue", 59, 11, 150, 50, "brown");
		squares[0][21] = shrt;
		squares[0][22] = new Misc("Reverse Direction", 60);
		squares[0][23] = new Estate("Lombard Street", 61, 17, 210, 100, "white");
		//3rd layer
		squares[2][0]  = new Subway("Subway", 62);
		squares[2][1]  = new Estate("Lake Street", 63, 1, 30, 50, "light pink");
		squares[2][2]  = new CommunityChest(64);
		squares[2][3]  = new Estate("Nicollet Avenue", 65, 1, 30, 50, "light pink");
		squares[2][4]  = new Estate("Hennepin Avenue", 66, 3, 60, 50, "light pink");
		squares[2][5]  = new Misc("Bus Ticket", 67);
		squares[2][6]  = new CabCompany("Checker Cab Co.", 68); //name, id (fiyatlar� class�n i�inde)
		squares[2][7]  = reading;
		squares[2][8]  = new Estate("Esplanade Avenue", 69, 5, 90, 50, "light green");
		squares[2][9]  = new Estate("Canal Street", 70, 5, 90, 50, "light green");
		squares[2][10] = new Chance(71);
		squares[2][11] = new Utility("Cable Company", 72);
		squares[2][12] = new Estate("Magazine Street", 73, 8, 120, 50, "light green");
		squares[2][13] = new Estate("Bourbon Street", 74, 8, 120, 50, "light green");
		squares[2][14] = h2;
		squares[2][15] = new Misc("Auction House", 76);
		squares[2][16] = new Estate("Katy Freeway", 77, 11, 150, 100, "cream");
		squares[2][17] = new Estate("Westheimer Road", 78, 11, 150, 100, "cream");
		squares[2][18] = new Utility("Internet Service Provider", 79);
		squares[2][19] = new Estate("Kirby Drive", 80, 14, 180, 100, "cream");
		squares[2][20] = new Estate("Cullen Boulevard", 81, 14, 180, 100, "cream");
		squares[2][21] = new Chance(82);
		squares[2][22] = new CabCompany("Black & White Co.", 83);
		squares[2][23] = new Estate("Dekalb Avenue", 84, 17, 210, 100, "teal");
		squares[2][24] = new CommunityChest(85);
		squares[2][25] = new Estate("Andrew Young Int. Boulevard", 86, 17, 210, 100, "teal");
		squares[2][26] = new Estate("Decatur Street", 87, 20, 240, 100, "teal");
		squares[2][27] = new Estate("Peachtree Street", 88, 20, 240, 100, "teal");
		squares[2][28] = new Payday(89); //id
		squares[2][29] = new Estate("Randolph Street", 90, 23, 270, 150, "wine");
		squares[2][30] = new Chance(91);
		squares[2][31] = new Estate("Lake Shore Drive", 92, 23, 270, 150, "wine");
		squares[2][32] = new Estate("Wacker Drive", 93, 26, 300, 150, "wine");
		squares[2][33] = new Estate("Michigan Avenue", 94, 26, 300, 150, "wine");
		squares[2][34] = new CabCompany("Yellow Cab Co.", 95);
		squares[2][35] = BnO;
		squares[2][36] = new CommunityChest(96);
		squares[2][37] = new Estate("South Temple", 97, 32, 330, 200, "gold");
		squares[2][38] = new Estate("West Temple", 98, 32, 330, 200, "gold");
		squares[2][39] = new Utility("Trash Collector", 99);
		squares[2][40] = new Estate("North Temple", 100, 38, 360, 200, "gold");
		squares[2][41] = new Estate("Temple Square", 101, 38, 360, 200, "gold");
		squares[2][42] = new Jail("Go To Jail", 102);
		squares[2][43] = new Estate("South Street", 103, 45, 390, 250, "peach");
		squares[2][44] = new Estate("Broad Street", 104, 45, 390, 250, "peach");
		squares[2][45] = new Estate("Walnut Street", 105, 55, 420, 250, "peach");
		squares[2][46] = new CommunityChest(106);
		squares[2][47] = new Estate("Market Street", 107, 55, 420, 250, "peach");
		squares[2][48] = new Misc("Bus Ticket", 108);
		squares[2][49] = new Utility("Sewage System", 109);
		squares[2][50] = new CabCompany("Ute Cab Co.", 110);
		squares[2][51] = new BirthdayGift(111); //id
		squares[2][52] = new Estate("Mulholland Drive", 112, 70, 450, 300, "maroon");
		squares[2][53] = new Estate("Ventura Boulevard", 113, 80, 480, 300, "maroon");
		squares[2][54] = new Chance(114);
		squares[2][55] = new Estate("Rodeo Drive", 115, 90, 510, 300, "maroon");
	}

	/**
	 * Adds a given amount of money to the pool.
	 * 
	 * @requires {@code amount} must be non-negative.
	 * 
	 * @param amount A non-negative integer representing the amount of money 
	 * 		  which will be added to the pool.
	 * 
	 * @modifies {@code this.pool}
	 * 
	 * @effects Adds {@code amount} to the pool, does nothing if the requirement is not met,
	 * throws an {@link ArithmeticException} if {@code pool + amount} is
	 * greater than {@code Integer.MAX_VALUE}.
	 */
	public void addToPool(int amount){
		if(amount < 0)
			return;
		pool = Math.addExact(pool, amount);
	}

	/**
	 * Takes a given amount of money from the pool.
	 * 
	 * @requires {@code amount} must be non-negative.
	 * 
	 * @param amount A non-negative integer representing the amount of money
	 * 		  which will be subtracted from the pool.
	 * 
	 * @modifies {@code this.pool}
	 * 
	 * @effects Subtracts {@code amount} from the pool, does nothing if the requirement is not met.
	 */
	public void withdrawFromPool(int amount){
		if(amount < 0)
			return;
		if(amount > pool)
			pool = 0;
		else
			pool-=amount;
	}

	/**
	 * Gets half of the money accumulated in the pool.
	 * 
	 * @effects Halves the value accumulated in the pool and returns the subtracted amount.
	 * Note that if the total money in the pool is an odd number,
	 * the returned amount of money will be one less than the remaining amount.
	 * 
	 * @modifies {@code this.pool}
	 * 
	 * @return A non-negative integer
	 */
	public int collectHalf(){
		int x = getPool() / 2;
		pool -= x;
		return x;
	}

	public int getPool(){
		return pool;
	}

	//move related things
	private MoveHandler mh = new MoveHandler(this);

	/**
	 * Moves the given {@link Player} object according to the given dice value.
	 * 
	 * @requires {@code player} must not be null and {@code diceValue} must be greater than 0.
	 * 
	 * @param player {@link Player} to be moved
	 * @param diceValue Sum of the pips on the dice
	 * 
	 * @modifies {@code player}
	 * 
	 * @effects Moves {@code player} according to {@code diceValue}, makes the {@code player}
	 * pass the {@link Square} objects on the way to its destination and triggers the 
	 * square action of the {@link Square} that {@code player} has stopped on,
	 * if the requirements are not met, throws {@link IllegalArgumentException}.
	 */
	public void move(Player player, int diceValue){
		mh.move(player, diceValue);
	}

	/**
	 * Simulates the "move direct" command specified in the Ultimate Monopoly game rules,
	 * but doesn't trigger the square action.
	 * 
	 * @param player {@link Player} to be moved
	 * @param square Destination {@link Square}
	 * 
	 * @requires {@code player} must not be null and {@code square} must be contained in
	 * {@code this.board}.
	 * 
	 * @modifies {@code player}
	 * 
	 * @effects Places {@code player} to {@code square}
	 * without passing any {@link Square} objects on the way,
	 * if the requirements are not met, throws {@link IllegalArgumentException}.
	 */
	public void moveDirectWithoutSquareAction(Player player, Square square){
		mh.moveDirectWithoutSquareAction(player, square);
	}

	/**
	 * Simulates the "move direct" command specified in the Ultimate Monopoly game rules.
	 * 
	 * @param player {@link Player} to be moved
	 * @param square Destination {@link Square}
	 * 
	 * @requires {@code player} must not be null and {@code square} must be contained in
	 * {@code this.board}.
	 * 
	 * @modifies {@code player}
	 * 
	 * @effects Places {@code player} to {@code square} and triggers
	 *  the square action of {@code square} without passing any {@link Square} objects on the way,
	 *  if the requirements are not met, throws {@link IllegalArgumentException}.
	 */
	public void moveDirect(Player player, Square square){
		mh.moveDirect(player, square);
	}

	/**
	 * Simulates a layer change by a Transit Station in a game of Ultimate Monopoly.
	 * 
	 * @param player {@link Player} to be moved
	 * 
	 * @requires {@code player} must be on a {@link TransitStation} contained in {@code this.board}.
	 * 
	 * @modifies {@code player}
	 * 
	 * @effects Places {@code player} to the other end of the {@link TransitStation}
	 * in which {@code player} is initially located, if the requirement is not met,
	 * throws {@link IllegalArgumentException}.
	 */
	public void switchLayerByTransitStation(Player player){
		mh.switchLayerByTransitStation(player);
	}

	/**
	 * Sends a {@link Player} to jail.
	 * 
	 * @param player {@link Player} to be sent to {@link Jail}
	 * 
	 * @requires {@code player} must not be null.
	 * 
	 * @modifies {@code player}
	 * 
	 * @effects Places {@code player} to {@link Jail} in {@code this.board} and 
	 * marks {@code player} as in jail, if the requirement is not met,
	 * throws {@link NullPointerException}.
	 */
	public void goToJail(Player player){
		mh.goToJail(player);
	}

	/**
	 * Simulates the "Mr. Monopoly Bonus Move" specified in the Ultimate Monopoly game rules.
	 * 
	 * @param player {@link Player} to be moved
	 * 
	 * @requires {@code player} must not be null.
	 * 
	 * @modifies {@code player}
	 * 
	 * @effects Moves {@code player} according to the rules of Mr. Monopoly Bonus Move
	 * specified in the game rules.
	 */
	public void moveMrm(Player player){
		mh.moveMrM(player);
	}

	/**
	 * Checks whether the representation invariant of this object holds or not.
	 * 
	 * @return true if the representation invariant holds for this otherwise false. 
	 */
	public boolean repOk(){
		if(pool<0 || squares == null || communityCards == null || chanceCards == null || map == null)
			return false;
		for (Square[] layer : squares) {
			for (Square square : layer) {
				if(square == null)
					return false;
			}
		}

		return mh.repOk();
	}

	@Override
	public String toString() {
		return "Board [pool=" + pool + ", squares=" + Arrays.toString(squares)
				+ ", communityCards=" + communityCards + ", chanceCards="
				+ chanceCards + ", map=" + map + ", mh=" + mh + ", repOk()="
				+ repOk() + "]";
	}
	
	/**
	 * The inner class responsible from moving the {@link Player} objects across the {@link Board}
	 * and triggering the necessary {@link PassBehavior}s and square actions.
	 *
	 */
	private class MoveHandler {

		private Board board;
		private int layer;
		private int position;

		/**
		 * @requires {@code b} must be initialized.
		 * 
		 * @param b {@link Board} of the game
		 * 
		 * @effects Creates a new MoveHandler object which can move {@link Player} objects
		 * on the {@link Board} {@code b}.
		 */
		public MoveHandler(Board b){
			this.board = b;
			layer = 0;
			position = 0;
		}

		/**
		 * @see Board#move(Player, int)
		 * @param player Explained in {@link Board}
		 * @param diceValue Explained in {@link Board}
		 * 
		 */
		public void move(Player player, int diceValue){
			if(player == null || diceValue <= 0)
				throw new IllegalArgumentException();
			setCoordinates(player);
			Square sq= board.getSquareAt(layer, position);
			for (int i = 0; i < diceValue; i++) {

				next();
				sq = board.getSquareAt(layer, position);
				placePlayerTo(player, sq);
				sq.pass(player);
			}
			sq.squareAction(player, board);
		}

		/** 
		 * @see Board#moveDirectWithoutSquareAction(Player, Square)
		 * 
		 * @param player Explained in {@link Board}
		 * @param square Explained in {@link Board}
		 * 
		 */
		public void moveDirectWithoutSquareAction(Player player, Square square){
			setCoordinates(square);
			placePlayerTo(player, square);
		}

		/** 
		 * @see Board#moveDirect(Player, Square)
		 * 
		 * @param player Explained in {@link Board}
		 * @param square Explained in {@link Board}
		 */
		public void moveDirect(Player player, Square square){
			moveDirectWithoutSquareAction(player, square);
			square.squareAction(player, board);
		}

		/** 
		 * @see Board#switchLayerByTransitStation(Player)
		 * 
		 * @param player Explained in {@link Board}
		 */
		public void switchLayerByTransitStation(Player player){
			if(player == null || !(player.getLocation() instanceof TransitStation))
				throw new IllegalArgumentException();
			setCoordinates(player);
			Square sq = board.getSquareAt(layer, position);
			setCoordinatesToOtherLayer(sq);
			placePlayerTo(player, sq);

		}

		/** 
		 * @see Board#goToJail(Player)
		 * 
		 * @param player Explained in {@link Board}
		 */
		public void goToJail(Player player){
			moveDirectWithoutSquareAction(player, board.getSquareById(10));
			player.setJail(true);
		}

		/** 
		 * @see Board#moveMrM(Player)
		 * 
		 * @param player Explained in {@link Board}
		 */
		public void moveMrM(Player player){
			moveMrM(player, true);
		}

		private void moveMrM(Player player, boolean isSearchingUnowned) {
			setCoordinates(player);
			Square currentLoc = player.getLocation();
			Square sq = null;
			int squaresPassed = 0;
			while(currentLoc != sq){

				next();
				sq = board.getSquareAt(layer, position);
				squaresPassed++;

				if(sq instanceof Property &&
						(isSearchingUnowned ?
								(((Property) sq).getOwner() == null) :
									(((Property) sq).getOwner() != player))){
					move(player, squaresPassed);
					return;
				}
				else if(sq instanceof TransitStation && Dice.getInstance().isEven())
					setCoordinatesToOtherLayer(sq);
			}
			if(isSearchingUnowned)
				moveMrM(player, false);
		}

		private void setCoordinatesToOtherLayer(Square sq) {
			for (int i = 0; i < 3; i++) {
				if(i != layer){
					int pos = board.getPositionInLayer(sq, i);
					if(pos >= 0){
						setLayer(i);
						setPosition(pos);
						break;
					}
				}
			}
		}

		private void setCoordinates(Player player){
			setLayer(player.getLayer());
			setPosition(player.getPosition());
		}

		private void setCoordinates(Square square){
			setLayer(board.getLayerNum(square));
			setPosition(board.getPositionInLayer(square, layer));
		}
		private void next(){
			setPosition((position + 1) % board.getLayerLength(layer));
		}
		private void placePlayerTo(Player player, Square square){
			if(layer < 0 || position < 0)
				throw new IllegalArgumentException();
			if(player == null)
				throw new IllegalArgumentException();
			player.setLocation(square);
			player.setLayer(layer);
			player.setPosition(position);
			GuiHandler.getInstance().playerMoved(player, square);
		}

		private void setLayer(int layer) {
			if(layer < 0)
				throw new IllegalArgumentException();
			this.layer = layer;
		}

		private void setPosition(int position) {
			if(position < 0)
				throw new IllegalArgumentException();
			this.position = position;
		}

		/**
		 * Checks whether the representation invariant of this object holds or not.
		 * 
		 * @return true if the representation invariant holds for this otherwise false. 
		 */
		public boolean repOk(){
			if(board == null || layer < 0 || position < 0)
				return false;
			if(layer >= 3 || position >= board.getLayerLength(layer))
				return false;

			return true;
		}

		@Override
		public String toString() {
			return "MoveHandler [board=" + board + ", layer=" + layer
					+ ", position=" + position + ", repOk()=" + repOk() + "]";
		}
	}

}
