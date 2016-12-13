package ui;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;




import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedHashMap;

public class GuiBoardLayer extends JPanel {
	//private GuiSquare[] squares;

	/**
	 * Create the panel.
	 */

	public GuiBoardLayer(int size) {

		setMinimumSize(new Dimension(size, size));
		//setPreferredSize(new Dimension(size, size));

		setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setBackground(Color.CYAN);
		GridBagLayout gridBagLayout = new GridBagLayout();
		/*gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};*/
		setLayout(gridBagLayout);

	}

	public GuiBoardLayer(int size, GuiSquare[] squares) {
		this(size);
		addSquares(squares);
	}

	public GuiBoardLayer(int size, GuiSquare[] squares, GuiBoardLayer boardLayer){

		this(size, squares);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth =GridBagConstraints.RELATIVE;
		constraints.gridheight =GridBagConstraints.RELATIVE;
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		add(boardLayer, constraints);
	}

	public GuiBoardLayer(GuiSquare[][] layers){
		this(720, layers[2],
				new GuiBoardLayer(720, layers[1],
						new GuiBoardLayer(720, layers[0])));
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
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		constraints.gridx = x;
		constraints.gridy = y;
		if(square != null)
			add(square, constraints);
	}

}
