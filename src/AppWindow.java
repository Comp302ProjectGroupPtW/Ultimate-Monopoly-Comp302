
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class AppWindow extends JFrame {

	private JFrame frame;
	private GamePanel gamePanel;
	private JButton rollDice;

	
	public void init(){
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppWindow() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 600);//450,300
		frame.setPreferredSize(new Dimension(1200, 900));
		frame.setMinimumSize(new Dimension(450, 300));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		gamePanel = new GamePanel();
		//setContentPane(gamePanel);

		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);

		
		rollDice = new JButton("Roll Dice");
		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Rolled!", "Dice", JOptionPane.PLAIN_MESSAGE);
				
				//game.userRolledDice();
			}
		});

		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		bottomPanel.add(rollDice);
		frame.pack();

		//gamePanel.setLayout(new CardLayout(0, 0));
		GuiBoard b = new GuiBoard();
		b.setMinimumSize(new Dimension(720, 720));
		b.addSquares(new GuiSquare[]{
				new GuiSquare("asd"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiProperty("p", "345", Color.GREEN).setOwner("Owner"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name"),
				new GuiSquare("name")
		});
		gamePanel.add(b);
	}
}
