import java.util.Random;


public class Misc extends Square {

	private String name;
	
	public Misc(){
		this.name=null;
	}
	
	public Misc(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void squareAction(Player p, Player[] players, Board board) {
		if(this.name.equals("Go")){
			p.deposit(200);
		} else if(this.name.equals("Roll Once")){
			rollOnce(p);
		} else if(this.name.equals("Free Parking")){
			Game.show("Free Parking");
		} else if(this.name.equals("Squeeze Play")){
			squeezePlay(p, players);
		}
		
	}
	
	public void rollOnce(Player p){
		Random r = new Random();
		int a = r.nextInt(5)+1;
		Game.show("The card you picked has the number: " + a);      // GUI ile çekilen kartın sayısını oyuncuya göster
		int b = r.nextInt(5)+1;
		Game.show("The dice you rolled is: " + b);      // GUI ile atılan zarın sayısını oyuncuya göster
		if(a==b){
			p.deposit(100);
		}
		
	}
	

	public void squeezePlay(Player p, Player[] players){
		Random r = new Random();
		int num = r.nextInt(11)+1;
		Game.show("The dice you rolled is: " + num);
		if(num>=5 && num<=9){
			for(int i=0; i<players.length; i++){
				p.transfer(players[i], 50);
			}
		} else if(num==3 || num==4){
			for(int i=0; i<players.length; i++){
				p.transfer(players[i], 100);
			}
		} else if(num==10 || num==11){
			for(int i=0; i<players.length; i++){
				p.transfer(players[i], 100);
			}
		} else if(num==2 || num==12){
			for(int i=0; i<players.length; i++){
				p.transfer(players[i], 200);
			}
		}
	}

	
}
