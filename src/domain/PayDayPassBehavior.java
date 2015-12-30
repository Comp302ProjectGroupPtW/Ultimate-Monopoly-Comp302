package domain;



public class PayDayPassBehavior implements PassBehavior {

	@Override
	public void pass(Player player) {
		if(Dice.getInstance().isEven()){
			player.deposit(400);
			GuiHandler.getInstance().showMessage("You have earned $400 for passing Pay Day!"
					+ "with an even roll", "Pay Day");
		}else {
			player.deposit(300);
			GuiHandler.getInstance().showMessage("You have earned $300 for passing Pay Day!"
					+ "with an odd roll", "Pay Day");
		}
	}

}
