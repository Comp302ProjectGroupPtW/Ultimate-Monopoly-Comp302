package ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.border.LineBorder;



public class GuiProperty extends GuiSquare {

	private JLabel priceLabel;
	private JLabel houseLabel;
	private JLabel owner;
	/**
	 * Create the panel.
	 */
	public GuiProperty(String name, String price, Color color, int upgradeLevel) {
		super(name);
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

		JPanel colorPanel = new JPanel();
		colorPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		houseLabel = new JLabel();
		//houseLabel.setBackground(Color.WHITE);
		if(color != null)
			colorPanel.setBackground(color);
		
		setBuildingLevel(upgradeLevel);
		
		colorPanel.add(houseLabel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1.0;
		//constraints.weighty = 0.0;
		//constraints.gridheight = 30;
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		colorPanel.setMinimumSize(new Dimension(1, 30));
		colorPanel.setPreferredSize(new Dimension(1, 30));
		add(colorPanel, constraints);

		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1.0;
		priceLabel = new JLabel("$" + price);
		add(priceLabel, constraints);
	}

	public GuiProperty setOwner(String owner){
		if(this.owner != null){
			remove(this.owner);
		}
		else if(owner != null){
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(0, 0, 0, 0);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.weightx = 1.0;
			this.owner = new JLabel("Owner: " + owner);
			add(this.owner, constraints);
		}
		return this;
	}
	public void setBuildingLevel(int upgradeLevel){
		if(upgradeLevel > 0 && upgradeLevel <= 4)
			houseLabel.setText(String.valueOf(upgradeLevel));
		else if(upgradeLevel == 5)
			houseLabel.setText("Hotel");
		else if(upgradeLevel == 6)
			houseLabel.setText("Skyscraper");
		else
			houseLabel.setText(null);
	}
}
