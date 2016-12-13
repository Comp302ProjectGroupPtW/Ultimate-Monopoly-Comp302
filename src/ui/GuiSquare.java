package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class GuiSquare extends JPanel {
	
	private JLabel nameLabel;
	private JPanel playerArea;
	private ArrayList<GuiPlayer> players;

	private static final int SIZE = 120;
	
	/**
	 * Create the panel.
	 */
	public GuiSquare(String name) {
		players = new ArrayList<GuiPlayer>();
		setPreferredSize(new Dimension(SIZE, SIZE));
		setMinimumSize(new Dimension(SIZE, SIZE));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		nameLabel = new JLabel(name);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		add(nameLabel, constraints);
		
		playerArea = new JPanel();
		//playerArea.setLayout(new );
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.weightx = 1.0;
		constraints.weighty = 0.5;
		add(playerArea, constraints);
	}
	
	public void addPlayer(GuiPlayer player){
		players.add(player);
		playerArea.add(player);
	}
	
	public void removePlayer(GuiPlayer player){
		playerArea.remove(player);
		players.remove(player);
	}
	
	public void updateAllPlayers(GuiPlayer[] players){
		removeAllPlayers();
		for (GuiPlayer player : players) {
			addPlayer(player);
		}
	}
	
	public void removeAllPlayers(){
		for(GuiPlayer player : players)
			remove(player);
		players = new ArrayList<GuiPlayer>();
	}

}
