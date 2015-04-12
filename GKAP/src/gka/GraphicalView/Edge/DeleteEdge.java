package gka.GraphicalView.Edge;

import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphicalView.MainFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class DeleteEdge extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JComboBox edgeBox;
	private JButton okButton;
	private JButton cancelButton;
	
	private MainFrame parent;


	/**
	 * Create the dialog.
	 */
	public DeleteEdge(Frame parent, boolean modal) {
		super(parent, modal);
		this.parent = (MainFrame) parent;
		
		init();
		preloadEdges();
	}
	
	private void init(){
		
		setTitle("Delete Edge");
		setBounds(100, 100, 280, 148);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSelectEdge = new JLabel("Select Edge");
			lblSelectEdge.setBounds(12, 31, 99, 15);
			contentPanel.add(lblSelectEdge);
		}
		{
			edgeBox = new JComboBox(new DefaultComboBoxModel<String>());
			edgeBox.setBounds(129, 26, 137, 24);
			contentPanel.add(edgeBox);
		}
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
		
		this.setLocationRelativeTo(null);
	}

	private void preloadEdges(){
		
		edgeBox.removeAllItems();
		
		for(OwnEdge edge : parent.gmanager.getAllEdges()){
			
			edgeBox.addItem(edge.getID());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Ok Button
		if(e.getActionCommand().equals(okButton.getText()))
		{
			if(edgeBox.getSelectedItem() != null)
			{
				long id = Long.parseLong(edgeBox.getSelectedItem().toString());
				parent.deleteEdge(id);
				this.dispose();
			}
		}
		// cancel Button
		else if(e.getActionCommand().equals(cancelButton.getText()))
		{
			this.dispose();
		}
		
	}

}
