package gka.GraphicalView.Algorithm;

import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphicalView.MainFrame;
import gka.GraphicalView.Edge.CreateEdge;

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

public class SearchDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JComboBox startVertexBox;
	private JComboBox targetVertexBox;
	private JButton okButton;
	private JButton cancelButton;
	private MainFrame parent;

	public static final String BEADTHFIRSTSEARCH = "BFS";

	/**
	 * Create the dialog.
	 */
	public SearchDialog(Frame parent, boolean modal, String algoMode) {
		
		super(parent, modal);
		this.parent = (MainFrame) parent;
		
		init();
		preloadVertices();
	}
	
	private void init(){
		
		this.setTitle(SearchDialog.BEADTHFIRSTSEARCH);
		setBounds(100, 100, 376, 208);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Start Node");
			lblNewLabel.setBounds(20, 36, 144, 24);
			contentPanel.add(lblNewLabel);
		}
		
		startVertexBox = new JComboBox(new DefaultComboBoxModel<String>());
		startVertexBox.setBounds(188, 36, 165, 24);
		contentPanel.add(startVertexBox);
		{
			JLabel lblTargetNode = new JLabel("Target Node");
			lblTargetNode.setBounds(20, 84, 124, 24);
			contentPanel.add(lblTargetNode);
		}
		{
			targetVertexBox = new JComboBox(new DefaultComboBoxModel<String>());
			targetVertexBox.setBounds(188, 84, 165, 24);
			contentPanel.add(targetVertexBox);
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
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand(cancelButton.getText());
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		this.setLocationRelativeTo(null);
	}

	private void preloadVertices(){
		
		startVertexBox.removeAllItems();
		targetVertexBox.removeAllItems();
		
		for(OwnVertex v : parent.gmanager.getAllVertices()){
			
			startVertexBox.addItem(v.get_name());
			targetVertexBox.addItem(v.get_name());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// OK Button
		if(e.getActionCommand().equals(okButton.getText()))
		{
			if(!startVertexBox.getSelectedItem().equals(targetVertexBox.getSelectedItem()))
			{
				OwnVertex v1 = parent.gmanager.getVertexByName(startVertexBox.getSelectedItem().toString());
				OwnVertex v2 = parent.gmanager.getVertexByName(targetVertexBox.getSelectedItem().toString());
				
				parent.startBFS(v1, v2);
				this.dispose();
			}
		}
		else if(e.getActionCommand().equals(cancelButton.getText()))
		{
			this.dispose();
		}
		
	}
}
