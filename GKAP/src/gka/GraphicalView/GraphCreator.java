package gka.GraphicalView;

import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.GraphType;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SpinnerNumberModel;

public class GraphCreator extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton createButton;
	private JButton cancelButton;
	private JSpinner verticesSpinner;
	private JSpinner edgesSpinner;
	private JSpinner spreadSpinner;
	private JComboBox<String> graphTypeComboBox;
	
	private final String UNDIRECTED_WEIGHTED_ATTRIBUTED = "UNDIRECTED_WEIGHTED_ATTRIBUTED";
	private final String UNDIRECTED_WEIGHTED_COHERENTLY = "UNDIRECTED_WEIGHTED_COHERENTLY";
	private final String UNDIRECTED_WEIGHTED = "UNDIRECTED_WEIGHTED";
	
	
	private MainFrame parent;

	/**
	 * Create the dialog.
	 */
	public GraphCreator(JFrame parent, boolean modal) {
		super(parent,modal);
		this.parent = (MainFrame) parent;
		init();
	}
	
	private void init(){
		
		setBounds(100, 100, 550, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblVertices = new JLabel("Vertices");
		lblVertices.setBounds(12, 73, 115, 15);
		contentPanel.add(lblVertices);
		{
			JLabel lblNewLabel = new JLabel("Edges");
			lblNewLabel.setBounds(12, 105, 115, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			verticesSpinner = new JSpinner();
			verticesSpinner.setBounds(145, 71, 370, 20);
			contentPanel.add(verticesSpinner);
		}
		{
			edgesSpinner = new JSpinner();
			edgesSpinner.setBounds(145, 103, 370, 20);
			contentPanel.add(edgesSpinner);
		}
		{
			graphTypeComboBox = new JComboBox<String>();
			graphTypeComboBox.addItem(UNDIRECTED_WEIGHTED_ATTRIBUTED);
			graphTypeComboBox.addItem(UNDIRECTED_WEIGHTED_COHERENTLY);
			graphTypeComboBox.addItem(UNDIRECTED_WEIGHTED);
			graphTypeComboBox.setBounds(145, 35, 370, 24);
			contentPanel.add(graphTypeComboBox);
		}
		{
			JLabel lblGraphType = new JLabel("Graph Type");
			lblGraphType.setBounds(12, 40, 115, 15);
			contentPanel.add(lblGraphType);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Spread");
			lblNewLabel_1.setBounds(12, 140, 70, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			spreadSpinner = new JSpinner();
			spreadSpinner.setEnabled(false);
			spreadSpinner.setModel(new SpinnerNumberModel(1, 1, 20, 1));
			spreadSpinner.setBounds(145, 135, 370, 20);
			contentPanel.add(spreadSpinner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				createButton = new JButton("Create");
				createButton.setActionCommand(createButton.getText());
				createButton.addActionListener(this);
				buttonPane.add(createButton);
				getRootPane().setDefaultButton(createButton);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// CREATE BUTTON
		if(e.getActionCommand().equals(createButton.getText())){
			
			String graphType = graphTypeComboBox.getSelectedItem().toString();
			int vertices = Integer.parseInt(verticesSpinner.getValue().toString());
			int edges = Integer.parseInt(edgesSpinner.getValue().toString());
			int spread = Integer.parseInt(spreadSpinner.getValue().toString());
			boolean withHeiristic = false;
			this.dispose();
			
			if(graphType.equals(UNDIRECTED_WEIGHTED))
			{
				parent.newGraph(vertices, edges, false, GraphType.WEIGHTED);
			}
			else if(graphType.equals(UNDIRECTED_WEIGHTED_ATTRIBUTED))
			{
				parent.newGraph(vertices, edges, false, GraphType.WEIGHTED, GraphType.ATTRIBUTED);
			}
			else if(graphType.equals(UNDIRECTED_WEIGHTED_COHERENTLY))
			{
				parent.newGraph(vertices, edges, true, GraphType.WEIGHTED);
			}
			else
			{
				WarningDialog warn = new WarningDialog(parent, true, "NOT IMPLEMENTED", "SORRY NOT IMPLEMENTED");
				warn.setVisible(true);
			}
			
			
			
		}
		// CANCEL BUTTON
		else if(e.getActionCommand().equals(cancelButton.getText())){
			this.dispose();
		}
		
	}
}
