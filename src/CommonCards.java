import java.util.List;


public class CommonCards extends Square {

	private String name;
	private int chanceCardNo=0;
	private int commCardNo=0;
	
	public CommonCards() {
		super();
	}

	public CommonCards(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void pickChanceCard(Board b, Player current, Player[] players){
			if(chanceCardNo==0){
				showUser("Advance to St. Charles Place");  //GUI ile user a kartın özelliğini gösterecek
				advanceToCharles(b, players, current);
				chanceCardNo=(chanceCardNo++)%4;
			} else if(chanceCardNo==1){
				showUser("Advance to Squeeze Play, if you pass “Go”, collect $200 from the bank.");
				advanceToSqueeze(b, players, current);
				chanceCardNo=(chanceCardNo++)%4;
			} else if(chanceCardNo==2){
				showUser(" You are elected as the Chairperson. Pay each player $50.");
				chairperson(current, players);
				chanceCardNo=(chanceCardNo++)%4;
			} else if (chanceCardNo==3){
				showUser(" Advance to “Go”, collect $200.");
				advanceToGo(b, players, current);
				chanceCardNo=(chanceCardNo++)%4;
			}
		}
	private void showUser(String string) {
		// TODO Auto-generated method stub
		
	}

	public void pickCommunityCard(Player current, Board board){
		if(commCardNo==0){
			consultancyFee(current);
			commCardNo=(commCardNo++)%3;
		} else if(commCardNo==1){
			bargainBusiness(current);
			commCardNo=(commCardNo++)%3;
		} else if(commCardNo==2){
			renovationSuccess(board);
			commCardNo=(commCardNo++)%3;
		}
	}
	

	private void renovationSuccess(Board b) {
		b.switchPending();
	}

	private void bargainBusiness(Player p) {
		p.setKeeping(true);
		
	}

	private void consultancyFee(Player p) {
		p.deposit(25);
	}

	private void advanceToGo(Board b, Player[] players, Player p) {
		p.moveTo(b, players, 0);  
	}

	private void chairperson(Player p, Player[] others) {
		for (int i=0; i<others.length; i++){
			p.withdraw(50);
			others[i].deposit(50);
		}
	}

	private void advanceToSqueeze(Board b, Player[] players, Player p) {
		if(p.passGo(b, 15)){  //passGo metodu iki square alacak, arada go yu geçip geçmediğin kontrol edip boolean döndürecek
			p.deposit(200);
		}
		p.moveTo(b, players, 15);	
	}

	private void advanceToCharles(Board b, Player[] players, Player p) {
		p.moveTo(b, players, 6);   
	}

	@Override
	public void squareAction(Player p, Player[] players, Board board){
		// TODO Auto-generated method stub
		if(this.name.equals("Chance")){
			pickChanceCard(board, p, players);
		} else{
			pickCommunityCard(p, board);
		}
	}
	
}
