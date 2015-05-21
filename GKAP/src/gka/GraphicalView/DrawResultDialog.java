package gka.GraphicalView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.visualization.VisualizationViewer;

public class DrawResultDialog extends JDialog implements ActionListener{

	private JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public DrawResultDialog(JFrame parent, boolean modal, VisualizationViewer viewComponent) {

		super(parent,modal);
		init();
		contentPanel.add(viewComponent);
		this.repaint();
	}
	
	private void init(){
		setBounds(100, 100, 600, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand(okButton.getText());
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand(cancelButton.getText());
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// OK BUTTON
		
		if(e.getActionCommand().equals(okButton.getText()))
		{
			this.dispose();
		}
		// CANCEL BUTTOn
		else if(e.getActionCommand().equals(cancelButton.getText()))
		{
			this.dispose();
		}
	}

}
