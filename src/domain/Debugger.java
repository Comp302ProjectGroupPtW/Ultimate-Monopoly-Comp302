package domain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;

import java.awt.Component;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Debugger extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JButton okButton;
	private JButton cancelButton;
	private Game game = Game.getInstance();
	private JList<Player> list;
	private JList<Square> list1;
	private JList<Square> list2;
	private JList<Card> list3;
	private JList<Company> list4;
	private JCheckBox chckbxSetAStart;
	private JCheckBox chckbxSetBalance;
	private JCheckBox chckbxAddProperty;
	private JCheckBox chckbxAddCard;
	private JCheckBox chckbxAddShare;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Debugger dialog = new Debugger();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Debugger() {
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 8, 0, 0));
		{
			list = new JList<Player>(Game.getInstance().getPlayers());

			contentPanel.add(list);
		}
		{
			ArrayList<Square> sqs = new ArrayList<Square>();
			sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[0]));
			sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[1]));
			sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[2]));
			list1 = new JList<Square>(sqs.toArray(new Square[1]));

			contentPanel.add(new JScrollPane(list1));
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			ArrayList<Square> sqs = new ArrayList<Square>();
			sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[0]));
			sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[1]));
			sqs.addAll(Arrays.asList(Board.getInstance().getSquares()[2]));
			sqs.removeIf(new Predicate<Square>() {

				@Override
				public boolean test(Square t) {
					return !(t instanceof Property);
				}
			});
			list2 = new JList<Square>(sqs.toArray(new Square[1]));
			contentPanel.add(new JScrollPane(list2));
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			ArrayList<Card> sqs = new ArrayList<Card>();
			sqs.addAll(Board.getInstance().getChanceCards());
			sqs.addAll(Board.getInstance().getCommunityCards());
			list3 = new JList<Card>(sqs.toArray(new Card[1]));
			contentPanel.add(list3);


		}
		{
			list4 = new JList<Company>(Board.getInstance().getCompanyArray());
			contentPanel.add(list4);
		}
		{
			textArea = new JTextArea();
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();

			buttonPane.setAlignmentX(Component.LEFT_ALIGNMENT);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setVisible(false);
			}
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				chckbxSetAStart = new JCheckBox("Set a start square\n");
				buttonPane.add(chckbxSetAStart);
			}
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
			}
			{
				chckbxSetBalance = new JCheckBox("Set Balance");
				buttonPane.add(chckbxSetBalance);
			}
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
			}
			{
				chckbxAddProperty = new JCheckBox("Add Property");
				buttonPane.add(chckbxAddProperty);
			}
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
			}
			{
				chckbxAddCard = new JCheckBox("Add Card");
				buttonPane.add(chckbxAddCard);
			}
			{
				chckbxAddShare = new JCheckBox("Add Share");
				buttonPane.add(chckbxAddShare);
			}
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e)
					{
						Player pl = list.getSelectedValue();
						if(chckbxSetAStart.isSelected()){
							Board.getInstance().moveDirectWithoutSquareAction(pl, list1.getSelectedValue());

						}
						if(chckbxAddProperty.isSelected()){
							Property pr = (Property) list2.getSelectedValue();
							pl.deposit(pr.getPrice());
							pl.buyProperty(pr);
							int num =Integer.parseInt(textField_1.getText());
							((Buildable) pr).setBuildings(num);
							GuiHandler.getInstance().updateBuilding(pr);
						}
						if(chckbxSetBalance.isSelected()){
							int money=Integer.parseInt(textField.getText());
							pl.setBalance(money);
							GuiHandler.getInstance().updateBalances();
						}
						if(chckbxAddCard.isSelected()){
							pl.addCard(list3.getSelectedValue());
						}
						if(chckbxAddShare.isSelected()){
							Company co = list4.getSelectedValue();
							for (int i = 0; i < Integer.parseInt(textArea.getText()); i++) {
								if(co.hasBuyableShare())
									pl.deposit(co.getPriceOfShare());
								pl.buyShare(co);
							}


						}



					}
				});  
			}
			buttonPane.add(okButton);
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
			}
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
			}
			buttonPane.add(cancelButton);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
			{
				JLabel lblSelectPlayer = new JLabel("Select Player:");
				panel.add(lblSelectPlayer);
			}
			{
				JLabel lblNewLabel = new JLabel("Select Square:");
				panel.add(lblNewLabel);
			}
			{
				JLabel lblEnterBalance = new JLabel("Balance :");
				panel.add(lblEnterBalance);
			}
			{
				JLabel lblSelectProperty = new JLabel("Select Property: num. of buildings:");
				panel.add(lblSelectProperty);
			}
			{
				JLabel lblSelectCard = new JLabel("Select a Card :");
				panel.add(lblSelectCard);
			}
			{
				JLabel lblA = new JLabel("Select the Company :");
				panel.add(lblA);
			}
			{
				JLabel lblNumOfShare = new JLabel("Share Quantity:");
				panel.add(lblNumOfShare);
			}
		}
	}

}
