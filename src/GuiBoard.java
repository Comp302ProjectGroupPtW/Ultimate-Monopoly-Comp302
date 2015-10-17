

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.Dimension;
import java.util.HashMap;

public class GuiBoard extends JPanel {
	//private int size;
	private GuiSquare[] squares;

	/**
	 * Create the panel.
	 */
	public GuiBoard(/*int size*/) {
	
		//this.size = size;
		setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setBackground(Color.CYAN);
		GridBagLayout gridBagLayout = new GridBagLayout();
		/*gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};*/
		setLayout(gridBagLayout);

	}
	
	public GuiBoard(GuiSquare[] squares) {
		this();
		addSquares(squares);
	}

	public void addSquares(GuiSquare[] squares){
		this.squares = squares;
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
	
	public void updateAllPlayers(HashMap<GuiPlayer, GuiSquare> playerLocations){
		
	}
	

	private void addSquare(GuiSquare square, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		add(square, constraints);
	}

}
