import javax.swing.JLabel;
import javax.swing.JPanel;


public class GuiPlayer extends JPanel {
	private JLabel nameLabel;

	public GuiPlayer(String name) {
		nameLabel = new JLabel(name);
		add(nameLabel);
	}
}
