package domain;



public class BonusPassBehavior implements PassBehavior {

	@Override
	public void pass(Player player) {
		player.deposit(250);
		GuiHandler.getInstance().showMessage("You have earned $200 for passing Bonus!", "Bonus");
	}

}
