
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {
private JLabel currentPlayer;

	public GamePanel(){
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("", "[]", "[]"));
		
		currentPlayer = new JLabel("Current Player: ");
		add(currentPlayer);
	}

	public GuiBoard initBoard(GuiSquare[] squares){
		GuiBoard board = new GuiBoard(squares);
		add(board);
		return board;
	}
	
	public void setCurrentPlayer(String name){
		currentPlayer.setText("Current Player: " + name);
	}
}
