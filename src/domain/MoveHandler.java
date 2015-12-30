package domain;

public class MoveHandler {

	private Board board;
	private int layer;
	private int position;

	public MoveHandler(Board b){
		this.board = b;
		layer = 0;
		position = 0;
	}

	public void move(Player player, int diceValue){
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

	public void moveDirect(Player player, Square square){
		setCoordinates(square);
		placePlayerTo(player, square);
		square.squareAction(player, board);
	}

	public void moveDirectWithoutSquareAction(Player player, Square square){
		setCoordinates(square);
		placePlayerTo(player, square);
	}

	public void switchLayerByTransitStation(Player player){
		setCoordinates(player);
		Square sq = board.getSquareAt(layer, position);
		setCoordinatesToOtherLayer(sq);
		placePlayerTo(player, sq);

	}

	public void goToJail(Player player){
		placePlayerTo(player, board.getSquareById(10));
		player.setJail(true);
	}

	public void moveMrM(Player player){
		moveMrM(player, true);
		//TODO Test
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
		layer = player.getLayer();
		position = player.getPosition();
	}

	private void setCoordinates(Square square){
		layer = board.getLayerNum(square);
		position = board.getPositionInLayer(square, layer);
	}
	private void next(){
		position = (position + 1) % board.getLayerLength(layer);
	}
	private void placePlayerTo(Player player, Square square){
		player.setLocation(square);
		player.setLayer(layer);
		player.setPosition(position);
		GuiHandler.getInstance().playerMoved(player, square);
	}

	private void setLayer(int layer) {
		this.layer = layer;
	}

	private void setPosition(int position) {
		this.position = position;
	}
}
