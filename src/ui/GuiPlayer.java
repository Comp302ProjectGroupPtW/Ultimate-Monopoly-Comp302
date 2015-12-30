package ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class GuiPlayer extends JPanel {
	private JLabel nameLabel;

	public GuiPlayer(String name) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1));
		nameLabel = new JLabel(name);
		add(nameLabel);
	}
}
