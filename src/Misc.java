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

	public void squareAction(Player p, Player[] players) {
		if(this.name.equals("Go")){
			p.deposit(200);
		} else if(this.name.equals("Roll Once")){
			rollOnce(p);
		} else if(this.name.equals("Free Parking")){
			System.out.println("Free Parking");
		} else if(this.name.equals("Squeeze Play")){
			squeezePlay();
		}
		
	}
	
	public void rollOnce(Player p){
		Random r = new Random();
		int a = r.nextInt(5)+1;
		showUser(a);      // GUI ile çekilen kartın sayısını oyuncuya göster
		int b = r.nextInt(5)+1;
		showUser(b);      // GUI ile atılan zarın sayısını oyuncuya göster
		if(a==b){
			p.deposit(100);
		}
		
	}
	
	public void squeezePlay(){
		
	}

	
}
