package gka.GraphicalView;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import gka.AlgorithmManager.BFS_Report;
import gka.AlgorithmManager.IAlgoReport;
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

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
		menuItem_NewFile.setEnabled(false);
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

		// Menu Algorithm
		menuAlgorithm = new JMenu("Algorithm");
		menuAlgorithm.setEnabled(false);
		menuBar.add(menuAlgorithm);
		
		menuItem_BFS = new JMenuItem("BFS");
		menuItem_BFS.setActionCommand(menuItem_BFS.getText());
		menuItem_BFS.addActionListener(this);
		menuAlgorithm.add(menuItem_BFS);
		
		menuItem_Dijkstra = new JMenuItem("Dijkstra");
		menuItem_Dijkstra.setActionCommand(menuItem_Dijkstra.getText());
		menuItem_Dijkstra.addActionListener(this);
		menuAlgorithm.add(menuItem_Dijkstra);
		
		menuItem_ASternchen = new JMenuItem("A*");
		menuItem_ASternchen.setActionCommand(menuItem_ASternchen.getText());
		menuItem_ASternchen.addActionListener(this);
		menuAlgorithm.add(menuItem_ASternchen);
		
		// Menu Help settings
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		menuItem_Reload = new JMenuItem("Reload");
		menuItem_Reload.setActionCommand(menuItem_Reload.getText());
		menuItem_Reload.addActionListener(this);
		menuHelp.add(menuItem_Reload);
		
		menuHelp.setEnabled(false);
		
		// Menu MoveMode
		pickMode = new JCheckBox("Pick Mode");
		pickMode.setEnabled(false);
		pickMode.setActionCommand(pickMode.getText());
		pickMode.addActionListener(this);
		pickMode.setBackground(new Color(153, 153, 255));
		menuBar.add(pickMode);
		
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
				pickMode.setEnabled(true);
				menuEdit.setEnabled(true);
				menuAlgorithm.setEnabled(true);
				menuHelp.setEnabled(true);
				this.pack();
				
			} catch (FileNotFoundException | WrongFileTypeException
					| AccessException | GraphBuildException e) {
			
				String message = e.getMessage();
				WarningDialog warningDialog = new WarningDialog(this, true, "ein Problem ist aufgetreten", message);
				warningDialog.setVisible(true);
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
	
	public void addVertex(String v1){
		
		if(!gmanager.addVertex(v1)){
			WarningDialog wd = new WarningDialog(this, true, "Add Vertex", "'"+v1+"' already exists");
			wd.setVisible(true);
		}
		else this.repaint();
	}
	
	public void addVertex(String v1, int attribute){
		
		if(!gmanager.addVertex(v1, attribute)){
			WarningDialog wd = new WarningDialog(this, true, "Add Vertex", "'"+v1+"' already exists");
			wd.setVisible(true);
		}
		else this.repaint();
	}
	
	public void addEdge(int weight, String s_v, String t_v){
		
		if(!gmanager.addEdge(weight, s_v, t_v)){
			
			WarningDialog wd = new WarningDialog(this, true, "Add Edge", "ERROR with Edge");
			wd.setVisible(true);
		}
		
		this.repaint();
	}
	
	public void deleteVertex(String v1){
		
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
	
	public void startBFS(String start, String target){
		
		IAlgoReport report = gmanager.startBreadthFirstSearch(start, target);
		
		ResultDialog resultDialog= new ResultDialog(this, true, report);
		resultDialog.setVisible(true);
	}

	public void startDijkstra(String start, String target){
		
		IAlgoReport report = gmanager.startDijkstra(start, target);

		ResultDialog resultDialog = new ResultDialog(this, true, report);
		resultDialog.setVisible(true);
	}
	
	public void startASternchen(String start, String target){
		
		IAlgoReport report = gmanager.startASternchen(start, target);
		
		ResultDialog resultDialog = new ResultDialog(this, true, report);
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
			else if(e.getActionCommand().equals(menuItem_Dijkstra.getText()))
			{
				SearchDialog sad = new SearchDialog(this, true, SearchDialog.DIJKSTRA);
				sad.setVisible(true);
			}
			if(e.getActionCommand().equals(menuItem_ASternchen.getText()))
			{
				SearchDialog sad = new SearchDialog(this, true, SearchDialog.ASTERNCHEN);
				sad.setVisible(true);
			}
		}
		// Menu Help
		{
			if(e.getActionCommand().equals(menuItem_Reload.getText()))
			{
				this.repaint();
			}
		}
		// Menu MoveMode
		{
			
			if(e.getActionCommand().equals(pickMode.getText())){
				
				if(pickMode.isSelected()) gmanager.setPicMode();
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
	private JMenuItem menuItem_Dijkstra;
	private JMenuItem menuItem_ASternchen;
	private JCheckBox pickMode;
}
