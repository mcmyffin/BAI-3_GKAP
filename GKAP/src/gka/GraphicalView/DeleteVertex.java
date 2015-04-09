package gka.GraphicalView;

import gka.GraphBuilder.Extension.OwnVertex;

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

public class DeleteVertex extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JComboBox vertexBox;
	private JButton okButton;
	private JButton cancelButton;
	
	private MainFrame parent;



	/**
	 * Create the dialog.
	 */
	public DeleteVertex(Frame parent, boolean modal) {
		
		super(parent, modal);
		this.parent = (MainFrame) parent;
		
		init();
		preloadVertices();
	}

	private void init(){
		
		setTitle("Delete Vertex");
		setBounds(100, 100, 320, 140);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Select Vertex");
			lblNewLabel.setBounds(12, 28, 115, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			vertexBox = new JComboBox(new DefaultComboBoxModel<String>());
			vertexBox.setBounds(145, 23, 161, 24);
			contentPanel.add(vertexBox);
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

	private void preloadVertices(){
		
		vertexBox.removeAllItems();
		
		for(OwnVertex v : parent.gmanager.getAllVertices()){
			vertexBox.addItem(v.get_name());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// OK Button
		if(e.getActionCommand().equals(okButton.getText()))
		{
			if(vertexBox.getSelectedItem() != null){
				
				OwnVertex v = parent.gmanager.getVertexByName(vertexBox.getSelectedItem().toString());
				parent.deleteVertex(v);
				
				this.dispose();
			}
			
		}
		// Cancel Button
		else if(e.getActionCommand().equals(cancelButton.getText()))
		{
			this.dispose();
		}
		
	}

}
