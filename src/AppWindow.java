import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AppWindow extends JFrame {

	private JFrame frame;
	private GamePanel gamePanel;
	private JButton rollDice;

	private GuiHandler handler;
	
	public void init(){
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppWindow(GuiHandler guiHandler, GuiSquare[] squares, GuiPlayer[] players) {
		this.handler = guiHandler;
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 600);//450,300
		frame.setPreferredSize(new Dimension(1200, 900));
		frame.setMinimumSize(new Dimension(450, 300));
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		gamePanel = new GamePanel();

		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);

		
		rollDice = new JButton("Roll Dice");
		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				handler.userRoll();
			}
		});

		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		bottomPanel.add(rollDice);


		gamePanel.initBoard(squares);
				
		frame.pack();
	}
	
	public GamePanel getGamePanel(){
		return gamePanel;
	}
}
