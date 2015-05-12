package gka.GraphicalView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class CreateEdge extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox<String> s_vBox;
	private JComboBox<String> t_vBox;
	private JSpinner weightSpinner;

	private MainFrame parent;
	private boolean isWeightedGraph;
	private String s_preset = null;
	private String t_preset = null;
	/**
	 * Create the dialog.
	 */
	public CreateEdge(Frame parent, boolean modal, boolean isWeighted) {
		
		super(parent, modal);
		
		this.parent = (MainFrame) parent;
		this.isWeightedGraph = isWeighted;
		init();
		checkGraphType();
		preloadVertices();
	}
	
	public CreateEdge(Frame parent, boolean modal, boolean isWeighted, String v1, String v2){
		
		super(parent, modal);
		
		this.parent = (MainFrame) parent;
		this.isWeightedGraph = isWeighted;
		this.s_preset = v1;
		this.t_preset = v2;
		
		init();
		checkGraphType();
		preloadVertices();
	}
	

	private void init(){
		
		setTitle("Create Edge");
		setBounds(100, 100, 262, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSvertex = new JLabel("S_Vertex");
			lblSvertex.setBounds(12, 25, 97, 15);
			contentPanel.add(lblSvertex);
		}
		{
			JLabel lblTvertex = new JLabel("T_Vertex");
			lblTvertex.setBounds(12, 68, 97, 15);
			contentPanel.add(lblTvertex);
		}
		{
			s_vBox = new JComboBox<String>(new DefaultComboBoxModel<String>());
			s_vBox.setBounds(127, 20, 121, 24);
			s_vBox.setActionCommand("s_v");
			s_vBox.addActionListener(this);
			contentPanel.add(s_vBox);
		}
		{
			t_vBox = new JComboBox<String>(new DefaultComboBoxModel<String>());
			t_vBox.setBounds(127, 63, 121, 24);
			t_vBox.setActionCommand("t_v");
			t_vBox.addActionListener(this);
			contentPanel.add(t_vBox);
		}
		{
			JLabel lblWeight = new JLabel("Weight");
			lblWeight.setBounds(12, 103, 70, 15);
			contentPanel.add(lblWeight);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(12, 142, 236, 2);
			contentPanel.add(separator);
		}
		
		weightSpinner = new JSpinner();
		weightSpinner.setEnabled(false);
		weightSpinner.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		weightSpinner.setBounds(137, 101, 111, 20);
		contentPanel.add(weightSpinner);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand(okButton.getText());
				okButton.addActionListener(this);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand(cancelButton.getText());
				cancelButton.addActionListener(this);
			}
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPane.add(okButton);
			buttonPane.add(cancelButton);
		}
		
		this.setLocationRelativeTo(null);
	}
	
	private void checkGraphType(){
		if(isWeightedGraph) weightSpinner.setEnabled(true);
		else weightSpinner.setEnabled(false);
	}

	private void preloadVertices(){
		
		if(s_preset != null && t_preset != null)
		{
			System.out.println("preload Setted Vertices");
			preloadSettedVertices(s_preset, t_preset);
		}
		else
		{
			System.out.println("preload all Vertices");
			s_vBox.removeAllItems();
			t_vBox.removeAllItems();
			for(String v : parent.gmanager.getAllVerticesAsString()){
				
				s_vBox.addItem(v);
				t_vBox.addItem(v);
			}
		}
	}
	
	private void preloadSettedVertices(String v1, String v2){
		s_vBox.removeAllItems();
		t_vBox.removeAllItems();
		
		s_vBox.addItem(v1);
		t_vBox.addItem(v2);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// OK Button
		if(e.getActionCommand().equals(okButton.getText()))
		{
			if(s_vBox.getSelectedItem() != null && s_vBox.getSelectedItem().toString().length() > 0){

				int weight = 0;
				
				// if attribute not a number
				try{
					weight= Integer.parseInt(weightSpinner.getValue().toString());
				}catch(NumberFormatException ex){
					this.dispose();
					
					WarningDialog warn = new WarningDialog(parent, true, "Number Format wrong", "weight ist not a number !");
					warn.setVisible(true);
					return;
				}
				
				
				String v1 = s_vBox.getSelectedItem().toString();
				String v2 = t_vBox.getSelectedItem().toString();
				
				this.dispose();
				parent.addEdge(weight, v1, v2);
			}
		}
		// Cancel Button
		else if(e.getActionCommand().equals(cancelButton.getText()))
		{
			this.dispose();
		}
	}
}
