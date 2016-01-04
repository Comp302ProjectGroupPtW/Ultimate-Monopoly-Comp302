package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import domain.GuiHandler;




@SuppressWarnings("serial")
public class AppWindow extends JFrame {

	private JFrame frame;
	private GamePanel gamePanel;
	
	private JButton rollDice;
	private JButton mrM;
	private JButton sellBelonging;
	private JButton buyBuilding;
	private JButton mortgage;
	private JButton debug;
	private JButton triggerCard;
	private JButton debugger;
	private JButton endTurn;

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
	public AppWindow(GuiHandler guiHandler, GuiSquare[][] squares, GuiPlayer[] players) {
		this.handler = guiHandler;
		
		frame = new JFrame("Ultimate Monopoly");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 600);
		frame.setPreferredSize(new Dimension(1600, 850));
		frame.setMinimumSize(new Dimension(450, 300));
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		gamePanel = new GamePanel();

		
		JScrollPane scrollPane = new JScrollPane(gamePanel);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		// *** Buttons ***
		
		rollDice = new JButton("Roll Dice");
		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						//rollDice.setEnabled(false);
						handler.userRoll();
						//rollDice.setEnabled(true);
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		mrM = new JButton("Mr. Monopoly");
		mrM.setEnabled(false); // Start as disabled.
		mrM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						
						handler.userMrM();
						
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		
		sellBelonging = new JButton("Sell Belonging");
		sellBelonging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						
						handler.userSellBelonging();
						
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		buyBuilding = new JButton("Buy Building");
		buyBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						
						handler.userBuyBuilding();
						
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		mortgage = new JButton("Mortgage");
		mortgage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						
						handler.userMortgage();
						
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		debug = new JButton("Manipulate Dice");
		debug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						
						handler.debug();
						
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		
		triggerCard = new JButton("Trigger Card");
		triggerCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						
						handler.userTriggerCard();
						
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		
		debugger = new JButton("Open Debugger");
		debugger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						
						//Do something here
						
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						
						handler.userEndTurn();
						
						return null;
					}
				};
			
					sw.execute();
				
				
			}
		});
		
		// *** End Buttons ***

		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		bottomPanel.add(rollDice);
		bottomPanel.add(mrM);
		bottomPanel.add(sellBelonging);
		bottomPanel.add(buyBuilding);
		bottomPanel.add(mortgage);
		bottomPanel.add(debug);
		bottomPanel.add(triggerCard);
		bottomPanel.add(endTurn);

		gamePanel.initBoard(squares);
		frame.pack();
	}
	
	public GamePanel getGamePanel(){
		return gamePanel;
	}
	
	public JFrame getFrame(){
		return frame;
	}


	public JButton getRollDice() {
		return rollDice;
	}

	public JButton getMrM() {
		return mrM;
	}

	public JButton getSellBelonging() {
		return sellBelonging;
	}

	public JButton getBuyBuilding() {
		return buyBuilding;
	}

	public JButton getEndTurn() {
		return endTurn;
	}

}
