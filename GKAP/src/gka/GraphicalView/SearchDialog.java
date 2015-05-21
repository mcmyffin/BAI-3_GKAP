package gka.GraphicalView;

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
	private JComboBox<String> startVertexBox;
	private JComboBox<String> targetVertexBox;
	private JButton okButton;
	private JButton cancelButton;
	private MainFrame parent;
	private String algoMode;
	
	// Search Algorithm types
	public static final String BEADTHFIRSTSEARCH = "BFS";
	public static final String DIJKSTRA = "DIJKSTRA";
	public static final String ASTERNCHEN = "A*";
	

	/**
	 * Create the dialog.
	 */
	public SearchDialog(Frame parent, boolean modal, String algoMode) {
		
		super(parent, modal);
		this.parent = (MainFrame) parent;
		this.algoMode = algoMode;
		
		init();
		preloadVertices();
	}
	
	private void init(){
		
		this.setTitle(this.algoMode);
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
		
		startVertexBox = new JComboBox<String>(new DefaultComboBoxModel<String>());
		startVertexBox.setBounds(188, 36, 165, 24);
		contentPanel.add(startVertexBox);
		{
			JLabel lblTargetNode = new JLabel("Target Node");
			lblTargetNode.setBounds(20, 84, 124, 24);
			contentPanel.add(lblTargetNode);
		}
		{
			targetVertexBox = new JComboBox<String>(new DefaultComboBoxModel<String>());
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
				cancelButton = new JButton("Cancel");
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
		
		for(String v : parent.gmanager.getAllVerticesAsString()){
			
			startVertexBox.addItem(v);
			targetVertexBox.addItem(v);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// OK Button
		if(e.getActionCommand().equals(okButton.getText()))
		{
			// precondition
			if(startVertexBox.getItemCount() == 0 && targetVertexBox.getItemCount() == 0) return;
			
			if(this.algoMode.equals(SearchDialog.BEADTHFIRSTSEARCH))
			{
				String v1 = startVertexBox.getSelectedItem().toString();
				String v2 = targetVertexBox.getSelectedItem().toString();
				
				this.dispose();
				parent.startBFS(v1, v2);
			}
			else if(this.algoMode.equals(SearchDialog.DIJKSTRA))
			{
				String v1 = startVertexBox.getSelectedItem().toString();
				String v2 = targetVertexBox.getSelectedItem().toString();
				
				this.dispose();
				parent.startDijkstra(v1, v2);
			}
			else if(this.algoMode.equals(SearchDialog.ASTERNCHEN))
			{
				String v1 = startVertexBox.getSelectedItem().toString();
				String v2 = targetVertexBox.getSelectedItem().toString();
				
				this.dispose();
				parent.startAStar(v1, v2);
			}
		}
		else if(e.getActionCommand().equals(cancelButton.getText()))
		{
			this.dispose();
		}
		
	}
}
