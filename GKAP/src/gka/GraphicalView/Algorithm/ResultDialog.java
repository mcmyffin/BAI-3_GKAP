package gka.GraphicalView.Algorithm;

import gka.AlgorithmManager.AlgoReport;
import gka.GraphicalView.MainFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

public class ResultDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JTextArea resultArea;
	
	private MainFrame parent;
	private AlgoReport report;
	

	/**
	 * Create the dialog.
	 */
	public ResultDialog(Frame parent, boolean modal, AlgoReport report) {
		
		super(parent, modal);
		this.parent = (MainFrame) parent;
		this.report = report;
		
		init();
		printReport();
	}
	
	private void init(){
		
		setBounds(100, 100, 551, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		resultArea = new JTextArea();
		resultArea.setLineWrap(true);
		resultArea.setEditable(false);
		resultArea.setLayout(new BorderLayout());
		contentPanel.add(resultArea);
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
		}
		
		this.setLocationRelativeTo(null);
	}

	private void printReport(){
		
		resultArea.removeAll();
		resultArea.setText(report.toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Ok Button
		if(e.getActionCommand().equals(okButton.getText()))
		{
			this.dispose();
		}
		
	}
}
