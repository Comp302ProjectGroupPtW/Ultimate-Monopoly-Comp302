package ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private JLabel currentPlayer;
	private JList<String> balances;
	private JList<String> keepings;

	public GamePanel(){
		
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new FlowLayout());

		currentPlayer = new JLabel("Current Player: ");
		balances = new JList<String>();
		keepings = new JList<String>();
	}

	public GuiBoardLayer initBoard(GuiSquare[][] squares){
		GuiBoardLayer board = new GuiBoardLayer(squares);
		add(currentPlayer);
		JScrollPane scrollPane = new JScrollPane(board);
		Component root = SwingUtilities.getRoot(this);
		scrollPane.setPreferredSize(new Dimension(root.getPreferredSize().width*12/15,
				root.getPreferredSize().height*9/10));
		scrollPane.setMinimumSize(new Dimension(getWidth()*2/3, getHeight()));
		System.out.println(getHeight());
		add(scrollPane);
		add(balances);
		add(keepings);
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
	
	public void setKeepings(String[] keepings){
		this.keepings.setListData(keepings);
		
	}
}
