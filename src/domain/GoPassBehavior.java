package domain;



public class GoPassBehavior implements PassBehavior {

	@Override
	public void pass(Player player) {
		player.deposit(200);
		GuiHandler.getInstance().showMessage("You have earned $200 for passing GO!", "GO");

	}

}
