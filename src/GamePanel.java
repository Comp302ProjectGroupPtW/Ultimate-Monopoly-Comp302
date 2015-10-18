import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private JLabel currentPlayer;
	private JList<String> balances;

	public GamePanel(){
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new FlowLayout());

		currentPlayer = new JLabel("Current Player: ");
		balances = new JList<String>();
	}

	public GuiBoard initBoard(GuiSquare[] squares){
		GuiBoard board = new GuiBoard(squares);
		add(currentPlayer);
		add(board);
		add(balances);
		return board;
	}

	public void setCurrentPlayer(String name){
		currentPlayer.setText("Current Player: " + name);
	}

	public void setBalances(String[] names, String[] balances){
		String[] listData = new String[names.length];

		for (int i = 0; i < listData.length; i++)
			listData[i] = names[i] + ": $" + balances[i];

		this.balances.setListData(listData);
	}
}
