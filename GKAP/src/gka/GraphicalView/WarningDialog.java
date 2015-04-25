package gka.GraphicalView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WarningDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private String dialogTitel;
	private String warningMessage;

	
	/**
	 * Create the dialog.
	 */
	public WarningDialog(JFrame parent, boolean modal, String titel, String message) {
		
		super(parent,modal);
		this.dialogTitel 	= titel;
		this.warningMessage = message;
		init();
	}

	private void init(){
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		setTitle(dialogTitel);
		setBounds(100, 100, 400, 145);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 204, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
		{
			JLabel warningLabel = new JLabel(this.warningMessage);
			warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
			warningLabel.setBounds(5, 5, 393, 73);
			contentPanel.add(warningLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(153, 153, 255));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		//*******
		setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
