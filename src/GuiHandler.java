
import java.awt.Color;
import java.util.Collection;

import javax.swing.JOptionPane;

import gui.*;

public class GuiHandler {

	private Game game;
	private AppWindow window;

	public static void main(String[] args){
		GuiHandler handler = new GuiHandler(null);
		handler.init();
	}

	public GuiHandler(Game game) {
		this.game = game;
	}

	public void init(){
		window = new AppWindow();
		window.init();
	}

	/*public void diceRolled(Dice dice){
		String message = "You rolled: " + dice.getLastDie1() + ", " + dice.getLastDie2();
		String title = "Dice";
		JOptionPane.showMessageDialog(window, message, title, JOptionPane.PLAIN_MESSAGE);
	}*/

	public void playerMoved(){

	}

	private GuiSquare[] squaresToGui(Collection<Square> squares){
		int i = 0;
		GuiSquare[] guiSquares = new GuiSquare[squares.size()];
		for (Square square : squares) {
			if(square instanceof Property)
				guiSquares[i++] = new GuiProperty(square.getname(), (String) square.getPrice(), stringToColor(square.getColor()));
			else
				guiSquares[i++] = new GuiSquare(square.getname());
		}
		
		return guiSquares;
	}

	private static Color stringToColor(String name){ //There's something wrong if this returns Magenta.
		if(name.equalsIgnoreCase("Blue"))
			return Color.BLUE;
		if(name.equalsIgnoreCase("Green"))
			return Color.GREEN;
		if(name.equalsIgnoreCase("Pink"))
			return Color.PINK;
		if(name.equalsIgnoreCase("Orange"))
			return Color.ORANGE;
		return Color.MAGENTA;
	}
}
