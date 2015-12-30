package domain;


public class TransitStationPassBehavior implements PassBehavior {


	@Override
	public void pass(Player player) {
		if(Dice.getInstance().isEven())
			Board.getInstance().switchLayerByTransitStation(player);
	}

}
