package gka.GraphicalView;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gka.AlgorithmManager.AlgoReport;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphVisualControler.GraphManager;
import gka.GraphVisualControler.IGraphManager;
import gka.GraphicalView.Algorithm.ResultDialog;
import gka.GraphicalView.Algorithm.SearchDialog;
import gka.GraphicalView.Edge.CreateEdge;
import gka.GraphicalView.Edge.DeleteEdge;
import gka.GraphicalView.Vertex.CreateVertex;
import gka.GraphicalView.Vertex.DeleteVertex;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.media.j3d.ConeSound;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MainFrame extends JFrame implements ActionListener{

	// control Class
	public IGraphManager gmanager;	


	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		gmanager = new GraphManager();
		init();
	}
	
	/**
	 * Initialize Components
	 */
	private void init(){
		
		// Frame settings
		setTitle("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		BorderLayout contentPaneLayout = new BorderLayout();
		contentPane.setLayout(contentPaneLayout);
		
		
		// MenuBar settings
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(153, 153, 255));
		setJMenuBar(menuBar);
		
		// Menu File settings
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		menuItem_NewFile = new JMenuItem("New File");
		menuItem_NewFile.setActionCommand(menuItem_NewFile.getText());
		menuItem_NewFile.addActionListener(this);
		menuFile.add(menuItem_NewFile);
		
		separator_2 = new JSeparator();
		menuFile.add(separator_2);
		
		menuItem_OpenFile = new JMenuItem("Open File");
		menuItem_OpenFile.setActionCommand(menuItem_OpenFile.getText());
		menuItem_OpenFile.addActionListener(this);
		menuFile.add(menuItem_OpenFile);
		
		menuItem_SaveFile = new JMenuItem("Save File");
		menuItem_SaveFile.setActionCommand(menuItem_SaveFile.getText());
		menuItem_SaveFile.addActionListener(this);
		menuFile.add(menuItem_SaveFile);
		
		separator_1 = new JSeparator();
		menuFile.add(separator_1);
		
		menuItem_Quit = new JMenuItem("Quit");
		menuItem_Quit.setActionCommand(menuItem_Quit.getText());
		menuItem_Quit.addActionListener(this);
		menuFile.add(menuItem_Quit);
		
		// Menu Edit settings
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		
		menuItem_AddVertex = new JMenuItem("Add Vertex");
		menuItem_AddVertex.setActionCommand(menuItem_AddVertex.getText());
		menuItem_AddVertex.addActionListener(this);
		menuEdit.add(menuItem_AddVertex);
		
		menuItem_AddEdge = new JMenuItem("Add Edge");
		menuItem_AddEdge.setActionCommand(menuItem_AddEdge.getText());
		menuItem_AddEdge.addActionListener(this);
		menuEdit.add(menuItem_AddEdge);
		
		separator = new JSeparator();
		menuEdit.add(separator);
		
		menuItem_RemoveVertex = new JMenuItem("Del Vertex");
		menuItem_RemoveVertex.setActionCommand(menuItem_RemoveVertex.getText());
		menuItem_RemoveVertex.addActionListener(this);
		menuEdit.add(menuItem_RemoveVertex);
		
		menuItem_RemoveEdge = new JMenuItem("Del Edge");
		menuItem_RemoveEdge.setActionCommand(menuItem_RemoveEdge.getText());
		menuItem_RemoveEdge.addActionListener(this);
		menuEdit.add(menuItem_RemoveEdge);
		
		menuEdit.setEnabled(false);
		
		menuAlgorithm = new JMenu("Algorithm");
		menuAlgorithm.setEnabled(false);
		menuBar.add(menuAlgorithm);
		
		menuItem_BFS = new JMenuItem("BFS");
		menuItem_BFS.setActionCommand(menuItem_BFS.getText());
		menuItem_BFS.addActionListener(this);
		menuAlgorithm.add(menuItem_BFS);
		
		// Menu Help settings
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		menuItem_Reload = new JMenuItem("Reload");
		menuItem_Reload.setActionCommand(menuItem_Reload.getText());
		menuItem_Reload.addActionListener(this);
		menuHelp.add(menuItem_Reload);
		
		menuHelp.setEnabled(false);
		
		// Menu MoveMode
		moveMode = new JCheckBox("Move Mode");
		moveMode.setEnabled(false);
		moveMode.setActionCommand(moveMode.getText());
		moveMode.addActionListener(this);
		moveMode.setBackground(new Color(153, 153, 255));
		menuBar.add(moveMode);
		
		setLocationRelativeTo(null);
	}
	
	public void open(String path){
		if(path !=  null || !path.isEmpty())
		{
			try {
				VisualizationViewer<OwnVertex, OwnEdge> vv = gmanager.loadGraph(path);				
				for(Component c: contentPane.getComponents()){
					if(((Object) c) instanceof VisualizationViewer){
						contentPane.remove(c);
					}
				}
				
				contentPane.add(vv);
				moveMode.setEnabled(true);
				menuEdit.setEnabled(true);
				menuAlgorithm.setEnabled(true);
				menuHelp.setEnabled(true);
				this.pack();
				
			} catch (FileNotFoundException | WrongFileTypeException
					| AccessException | GraphBuildException e) {
			
				String message = e.getMessage();
				WarningDialog warningDialog = new WarningDialog(this, true, "ein Problem ist aufgetreten", message);
			}
		}
	}
	
	public void save(File path){
		if(gmanager.saveGraph(path)){
		
			WarningDialog wd = new WarningDialog(this, true, "Save Graph", "Graph saved");
			wd.setVisible(true);
		}
		else
		{
			WarningDialog wd = new WarningDialog(this, true, "Save Graph", "Graph can not save !");
			wd.setVisible(true);
		}
	}
	
	public void addVertex(OwnVertex v1){
		
		if(!gmanager.addVertex(v1)){
			WarningDialog wd = new WarningDialog(this, true, "Add Vertex", "'"+v1.get_name()+"' already exists");
			wd.setVisible(true);
		}
		else this.repaint();
	}
	
	public void addEdge(OwnEdge e, OwnVertex s_v, OwnVertex t_v){
		
		if(e == null || s_v == null || t_v == null){
			WarningDialog wd = new WarningDialog(this, true, "Add Edge",  "is impossible");
			wd.setVisible(true);
		}
		
		if(!gmanager.addEdge(e, s_v, t_v)){
			
			WarningDialog wd = new WarningDialog(this, true, "Add Edge", "ERROR with Edge");
			wd.setVisible(true);
		}
		
		this.repaint();
	}
	
	public void deleteVertex(OwnVertex v1){
		
		if(v1 == null)
		{
			WarningDialog wd = new WarningDialog(this, true, "Delete Vertex",  "Vertex not found !");
			wd.setVisible(true);
		}
		else
		{
			if(!gmanager.removeVertex(v1))
			{
				WarningDialog wd = new WarningDialog(this, true, "Delete Vertex",  "delete problem");
				wd.setVisible(true);
			}
		}
		
		this.repaint();
	}
	
	public void deleteEdge(long id){
		
		if(!gmanager.removeEdge(id))
		{
			WarningDialog wd = new WarningDialog(this, true, "Delete Edge",  "delete problem");
			wd.setVisible(true);
		}
		this.repaint();
	}
	
	public void startBFS(OwnVertex start, OwnVertex target){
		
		if(start == null || target == null)
		{
			WarningDialog wd = new WarningDialog(this, true, "Algorithm ERROR",  "Algorithm problem");
			wd.setVisible(true);
		}
		
		AlgoReport report = gmanager.breadthFirstSearch(start, target);
		
		ResultDialog resultDialog= new ResultDialog(this, true, report);
		resultDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Menu File
		{
			// MenuItem new File
			if(e.getActionCommand().equals(menuItem_NewFile.getText()))
			{
				System.out.println("new File");
			}
			else if(e.getActionCommand().equals(menuItem_OpenFile.getText()))
			{
				// Open File
				FileChooser fileChooser = new FileChooser(this, true, FileChooser.LOAD_MODE);
				fileChooser.setVisible(true);

			}
			else if(e.getActionCommand().equals(menuItem_SaveFile.getText()))
			{
				// Save File
				FileChooser fileChooser = new FileChooser(this, true, FileChooser.SAVE_MODE);
				fileChooser.setVisible(true);
			}
			else if(e.getActionCommand().equals(menuItem_Quit.getText()))
			{
				// todo show warning dialog
				// user must confirm with yes in dialog
				System.exit(0);
			}
		}
		// Menu Edit
		{
			if(e.getActionCommand().equals(menuItem_AddVertex.getText()))
			{
				// Add Vertex
				CreateVertex vertexDialog = new CreateVertex(this, true, gmanager.getGraphType());
				vertexDialog.setVisible(true);
			}
			else if(e.getActionCommand().equals(menuItem_AddEdge.getText()))
			{
				// Add Edge
				boolean weighted = gmanager.getGraphType().contains(GraphBuilder.WEIGHTED);
				
				CreateEdge edgeDialog = new CreateEdge(this, true, weighted);
				edgeDialog.setVisible(true);
			}
			else if(e.getActionCommand().equals(menuItem_RemoveVertex.getText()))
			{
				// Remove Vertex
				DeleteVertex delVertexDialog = new DeleteVertex(this, true);
				delVertexDialog.setVisible(true);
			}
			else if(e.getActionCommand().equals(menuItem_RemoveEdge.getText()))
			{
				// Remove Edge
				DeleteEdge delEdgeDialog = new DeleteEdge(this, true);
				delEdgeDialog.setVisible(true);
			}
		}
		// Menu Algorithm
		{
			if(e.getActionCommand().equals(menuItem_BFS.getText()))
			{
				SearchDialog sad = new SearchDialog(this, true, SearchDialog.BEADTHFIRSTSEARCH);
				sad.setVisible(true);
				
			}
		}
		// Menu Help
		{
			if(e.getActionCommand().equals(menuItem_Reload.getText()))
			{
				// todo manual reload graph in panel
				System.out.println("reload");
			}
		}
		// Menu MoveMode
		{
			
			if(e.getActionCommand().equals(moveMode.getText())){
				
				if(moveMode.isSelected()) gmanager.setPicMode();
				else gmanager.setTrasformMode();
			}
		}
	}
	
	// Component declaration
	
	// Panel components
	private JPanel contentPane;
	
	// Separator components
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;

	// Menu components
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuEdit;
	private JMenu menuAlgorithm;
	private JMenu menuHelp;
	private JMenuItem menuItem_NewFile;
	private JMenuItem menuItem_OpenFile;
	private JMenuItem menuItem_SaveFile;
	private JMenuItem menuItem_Quit;	
	private JMenuItem menuItem_AddVertex;
	private JMenuItem menuItem_AddEdge;
	private JMenuItem menuItem_RemoveVertex;
	private JMenuItem menuItem_RemoveEdge;	
	private JMenuItem menuItem_Reload;
	private JMenuItem menuItem_BFS;
	private JCheckBox moveMode;
}
