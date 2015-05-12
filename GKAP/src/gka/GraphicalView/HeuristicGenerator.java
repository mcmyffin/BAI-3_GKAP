package gka.GraphicalView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

public class HeuristicGenerator extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HeuristicGenerator dialog = new HeuristicGenerator();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HeuristicGenerator() {
		setBounds(100, 100, 346, 156);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSetTarget = new JLabel("Set target");
			lblSetTarget.setBounds(25, 32, 120, 15);
			contentPanel.add(lblSetTarget);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(163, 32, 150, 24);
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton START = new JButton("START");
				START.setActionCommand("OK");
				buttonPane.add(START);
				getRootPane().setDefaultButton(START);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
