import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;



import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.LinkedHashMap;

public class GuiBoard extends JPanel {

	/**
	 * Create the panel.
	 */
	public GuiBoard() {
		
		setMinimumSize(new Dimension(720, 720));
		setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setBackground(Color.CYAN);
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

	}
	
	public GuiBoard(GuiSquare[] squares) {
		this();
		addSquares(squares);
	}

	public void addSquares(GuiSquare[] squares){
		//this.squares = squares;
		int size = squares.length;
		int x = 0, y = 0, current = 0;
		for (; x < size/4 + 1; x++) {
			addSquare(squares[current++], x, y);
		}
		x--;
		y++;
		for (; y < size/4 + 1; y++) {
			addSquare(squares[current++], x, y);
		}
		y--;
		x--;
		for (; x > 0; x--) {
			addSquare(squares[current++], x, y);
			
		}
		
		for (; y > 0; y--) {
			addSquare(squares[current++], x, y);
		}
	}
	
	public void updateAllPlayers(LinkedHashMap<GuiPlayer, GuiSquare> playerLocations){
		
	}
	

	private void addSquare(GuiSquare square, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		add(square, constraints);
	}

}
