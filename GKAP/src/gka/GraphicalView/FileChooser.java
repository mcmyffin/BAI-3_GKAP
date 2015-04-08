package gka.GraphicalView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileChooser extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private MainFrame parent;
	private String mode;
	
	public static final String SAVE_MODE = "save";
	public static final String LOAD_MODE = "load";
	
	/**
	 * Create the dialog.
	 */
	public FileChooser(JFrame parent, boolean isLocked, String mode) {
		
		super(parent,isLocked);
		this.parent = (MainFrame) parent;
		this.mode = mode;
		
		setBounds(100, 100, 511, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			fileChooser = new JFileChooser();
			fileChooser.addActionListener(this);
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setDragEnabled(false);
			fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
			Action details = fileChooser.getActionMap().get("viewTypeDetails");
			details.actionPerformed(null);
			
			contentPanel.add(fileChooser);
		}	
	}
	
	

	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getActionCommand().matches("ApproveSelection")){
	           
        	parent.open(fileChooser.getSelectedFile().getPath());
        	dispose();
            
        }else if(evt.getActionCommand().matches("CancelSelection")) {
            dispose();
        }
		
	}
	
	private JFileChooser fileChooser;

}
