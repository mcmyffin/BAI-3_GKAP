package gka.GraphicalView.Vertex;

import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphicalView.MainFrame;
import gka.GraphicalView.Edge.CreateEdge;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.BoxLayout;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CreateVertex extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField vertexNameField;
	private JTextField attributField;
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox connectToBox;

	private MainFrame parent;
	private String    graphType;

	/**
	 * Create the dialog.
	 */
	public CreateVertex(Frame parent, boolean modal, String graphType){
		
		super(parent,modal);
		
		init();
		this.parent = (MainFrame) parent;
		this.graphType = graphType;
		
		graphType();
		preloadVertices();
	}
	
	
	private void init(){
		
		setTitle("Create Vertex");
		setBounds(100, 100, 274, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			vertexNameField = new JTextField();
			vertexNameField.setBounds(144, 28, 116, 19);
			vertexNameField.setHorizontalAlignment(SwingConstants.CENTER);
			vertexNameField.setColumns(10);
			vertexNameField.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {}
				
				@Override
				public void keyReleased(KeyEvent e) {
					
					if(vertexNameField.getText().length() > 0) okButton.setEnabled(true);
					else okButton.setEnabled(false);
				}
				
				@Override
				public void keyPressed(KeyEvent e) {}
			});
			contentPanel.add(vertexNameField);
		}
		
		JLabel lblVertexName = new JLabel("Vertex Name");
		lblVertexName.setBounds(12, 30, 104, 15);
		contentPanel.add(lblVertexName);
		
		attributField = new JTextField();
		attributField.setEnabled(false);
		attributField.setBounds(144, 59, 116, 19);
		attributField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(attributField);
		attributField.setColumns(10);
		
		JLabel lblAttribute = new JLabel("Attribute");
		lblAttribute.setBounds(12, 61, 104, 15);
		contentPanel.add(lblAttribute);
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(12, 158, 248, 2);
			contentPanel.add(separator);
		}
		{
			JLabel lblConnectTo = new JLabel("Connect to");
			lblConnectTo.setBounds(12, 88, 104, 15);
			contentPanel.add(lblConnectTo);
		}
		
		connectToBox = new JComboBox();
		connectToBox.setModel(new DefaultComboBoxModel<String>(new String[] {"---"}));
		connectToBox.setBounds(144, 90, 116, 24);
		contentPanel.add(connectToBox);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.setActionCommand(okButton.getText());
				okButton.addActionListener(this);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand(cancelButton.getText());
				cancelButton.addActionListener(this);
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addGap(5)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addGap(20))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cancelButton)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
			
			
		}
		
		// *****
		setLocationRelativeTo(null);
	}
	
	private void graphType(){
		
		if(graphType.contains(GraphBuilder.ATTRIBUTED)){
			
			attributField.setEnabled(true);
		}
		else
		{
			attributField.setEnabled(false);
		}
	}

	
	private void preloadVertices(){
		String defaultItem = "---";
		
		connectToBox.removeAllItems();
		connectToBox.addItem(defaultItem);
		
		for(OwnVertex v : parent.gmanager.getAllVertices()){
			
			connectToBox.addItem(v.get_name());
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// OK Button
		if(e.getActionCommand().equals(okButton.getText()))
		{
			
			// connect to selected?
			if(!connectToBox.getSelectedItem().equals("---"))
			{
				
				// WEIGHTED EDGE?
				if(graphType.contains(GraphBuilder.WEIGHTED))
				{
					String attr = (attributField.getText().length() > 0? attributField.getText() : "0");
					int attr1 = Integer.parseInt(attr);
					
					String v_2 = connectToBox.getSelectedItem().toString();
					
					OwnVertex v1 = new OwnVertex(vertexNameField.getText());
					OwnVertex v2 = parent.gmanager.getVertexByName(v_2);
					
					parent.addVertex(v1);
					this.dispose();
					
					CreateEdge edgeDialog = new CreateEdge(parent, true, true, v1, v2);
					edgeDialog.setVisible(true);

				}
				// ELSE
				else
				{					
					String attr = (attributField.getText().length() > 0? attributField.getText() : "0");
					int attr1 = Integer.parseInt(attr);
					
					String v_2 = connectToBox.getSelectedItem().toString();
					
					OwnVertex v1 = new OwnVertex(vertexNameField.getText());
					OwnVertex v2 = parent.gmanager.getVertexByName(v_2);
					OwnEdge edge = new OwnEdge();
					
					parent.addVertex(v1);
					parent.addEdge(edge, v1, v2);
					this.dispose();
					
				}
			}
			// ELSE ONLY VERTEX
			else
			{
				System.out.println("only vertex");
				String attr = (attributField.getText().length() > 0? attributField.getText() : "0");
				int attr1 = Integer.parseInt(attr);
				parent.addVertex(new OwnVertex(vertexNameField.getText(), attr1));	
				
				this.dispose();
			}
		}
		else if(e.getActionCommand().equals(cancelButton.getText()))
		{
			this.dispose();
		}
		
	}
	
	
	
	
}
