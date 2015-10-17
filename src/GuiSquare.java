import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class GuiSquare extends JPanel {
	
	private JLabel nameLabel;
	private ArrayList<GuiPlayer> players;

	/**
	 * Create the panel.
	 */
	public GuiSquare(String name) {
		players = new ArrayList<GuiPlayer>();
		setPreferredSize(new Dimension(120, 120));
		//setMinimumSize(new Dimension(100, 100));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		nameLabel = new JLabel(name);
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.insets = new Insets(0, 0, 0, 0);
		gbc_name.gridx = 0;
		gbc_name.gridy = 0;
		gbc_name.weightx = 1.0;
		add(nameLabel, gbc_name);

	}
	
	public void addPlayer(GuiPlayer player){
		players.add(player);
		add(player);
	}
	
	public void removePlayer(GuiPlayer player){
		remove(player);
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
