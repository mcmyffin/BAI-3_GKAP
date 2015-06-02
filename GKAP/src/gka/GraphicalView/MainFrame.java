package gka.GraphicalView;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gka.AlgorithmManager.Extension.BFS_Report;
import gka.AlgorithmManager.Extension.IAlgoReport;
import gka.AlgorithmManager.Extension.Kruskal_Prim_Report;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.GraphType;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphGenerator.GraphGenerator;
import gka.GraphVisualControler.GraphManager;
import gka.GraphVisualControler.IGraphManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FileDialog;

import javafx.util.Pair;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import cern.jet.random.VonMises;
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
		
		menuItem_NewGraph = new JMenuItem("New Graph");
		menuItem_NewGraph.setActionCommand(menuItem_NewGraph.getText());
		menuItem_NewGraph.addActionListener(this);
		menuItem_NewGraph.setEnabled(true);
		menuFile.add(menuItem_NewGraph);
		
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
		
		separator_3 = new JSeparator();
		menuAlgorithm.add(separator_3);
		menuAlgorithm.add(menuItem_Dijkstra);
		
		menuItem_AStar = new JMenuItem("A*");
		menuItem_AStar.setActionCommand(menuItem_AStar.getText());
		menuItem_AStar.addActionListener(this);
		menuAlgorithm.add(menuItem_AStar);
		
		separator_4 = new JSeparator();
		menuAlgorithm.add(separator_4);
		
		menuItem_Kruskal = new JMenuItem("Kruskal");
		menuItem_Kruskal.setActionCommand(menuItem_Kruskal.getText());
		menuItem_Kruskal.addActionListener(this);
		
		menuItem_drawResult = new JCheckBox("Draw Result");
		menuAlgorithm.add(menuItem_drawResult);
		menuAlgorithm.add(menuItem_Kruskal);
		
		menuItem_Prim = new JMenuItem("Prim");
		menuItem_Prim.setActionCommand(menuItem_Prim.getText());
		menuItem_Prim.addActionListener(this);
		menuAlgorithm.add(menuItem_Prim);
		
		menuItem_Prim_fib = new JMenuItem("Prim with Fibheap");
		menuItem_Prim_fib.setActionCommand(menuItem_Prim_fib.getText());
		menuItem_Prim_fib.addActionListener(this);
		menuAlgorithm.add(menuItem_Prim_fib);
		
		// Menu Help settings
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		menuItem_Reload = new JMenuItem("Reload");
		menuItem_Reload.setActionCommand(menuItem_Reload.getText());
		menuItem_Reload.addActionListener(this);
		menuHelp.add(menuItem_Reload);
		
		menuHelp.setEnabled(false);
		
		drawMode = new JCheckBox("draw");
		drawMode.setSelected(true);
		drawMode.setActionCommand(drawMode.getText());
		drawMode.addActionListener(this);
		menuHelp.add(drawMode);
		
		// Menu MoveMode
		pickMode = new JCheckBox("Pick Mode");
		pickMode.setEnabled(false);
		pickMode.setActionCommand(pickMode.getText());
		pickMode.addActionListener(this);
		pickMode.setBackground(new Color(153, 153, 255));
		menuBar.add(pickMode);
		
		setLocationRelativeTo(null);
	}
	
	public void open(){
		
		FileDialog fd = new FileDialog(this,"Load Graph", FileDialog.LOAD);
		fd.setFilenameFilter(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".graph");
			}
		});
		
		fd.setVisible(true);
		String path = fd.getDirectory()+fd.getFile();
		
		if(path !=  null || !path.isEmpty())
		{
			try {
				VisualizationViewer vv = gmanager.loadGraph(path);				
//				for(Component c: contentPane.getComponents()){
//					if(((Object) c) instanceof VisualizationViewer){
//						contentPane.remove(c);
//					}
//				}
				if(viewComponent != null) contentPane.remove(viewComponent);
				viewComponent = vv;
				
				viewComponent.setVisible(drawMode.isSelected());
				contentPane.add(viewComponent);
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
	
	public void newGraph(int vertices, int edges, boolean coherently, GraphType...type){
		
		try {
			VisualizationViewer vv = gmanager.generateNewGraph(vertices, edges, coherently, type);				
			
			if(viewComponent != null) contentPane.remove(viewComponent);
			viewComponent = vv;
			
			
			viewComponent.setVisible(drawMode.isSelected());
			contentPane.add(viewComponent);
			pickMode.setEnabled(true);
			menuEdit.setEnabled(true);
			menuAlgorithm.setEnabled(true);
			menuHelp.setEnabled(true);
			this.pack();
			
		} catch (GraphBuildException e) {
		
			String message = e.getMessage();
			WarningDialog warningDialog = new WarningDialog(this, true, "ein Problem ist aufgetreten", message);
			warningDialog.setVisible(true);
		}
	}
	
	public void save(){
		
		FileDialog fd = new FileDialog(this,"Load Graph",FileDialog.SAVE);
		fd.setFilenameFilter(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".graph");
			}
		});
		
		fd.setVisible(true);
		
		String path = fd.getDirectory();
		String file = fd.getFile();
		
		if(path == null || file == null) return;
		
		File aFile = new File(path+file);
		if(gmanager.saveGraph(aFile)){
		
			WarningDialog wd = new WarningDialog(this, true, "Save Graph", "Graph saved");
			wd.setVisible(true);
		}
		else
		{
			WarningDialog wd = new WarningDialog(this, true, "Save Graph", "Graph can not save !");
			wd.setVisible(true);
		}
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
		
		SearchResultDialog resultDialog= new SearchResultDialog(this, false, report);
		resultDialog.setVisible(true);
	}

	public void startDijkstra(String start, String target){
		
		IAlgoReport report = gmanager.startDijkstra(start, target);

		SearchResultDialog resultDialog = new SearchResultDialog(this, false, report);
		resultDialog.setVisible(true);
	}
	
	public void startAStar(String start, String target){
		
		IAlgoReport report = gmanager.startAStar(start, target);
		
		SearchResultDialog resultDialog = new SearchResultDialog(this, false, report);
		resultDialog.setVisible(true);
	}
	
	public void startKruskal(boolean drawResult){
		
		Pair<IAlgoReport,VisualizationViewer> report = gmanager.startKruskal();
		
		SearchResultDialog searchResultDialog = new SearchResultDialog(this, false, report.getKey());
		searchResultDialog.setVisible(true);
		
		if(drawResult){
			DrawResultDialog drawResultDialog = new DrawResultDialog(this, false, report.getValue());
			drawResultDialog.setVisible(true);
		}
	}
	
	public void startPrim(boolean withFibHeap,boolean drawResult){

		Pair<IAlgoReport,VisualizationViewer> report = gmanager.startPrim(withFibHeap);
		
		SearchResultDialog searchResultDialog = new SearchResultDialog(this, false, report.getKey());
		searchResultDialog.setVisible(true);
		
		if(drawResult){
			DrawResultDialog drawResultDialog = new DrawResultDialog(this, false, report.getValue());
			drawResultDialog.setVisible(true);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Menu File
		{
			// MenuItem new Graph
			if(e.getActionCommand().equals(menuItem_NewGraph.getText()))
			{
				GraphCreator graphGen = new GraphCreator(this, true);
				graphGen.setVisible(true);
			}
			else if(e.getActionCommand().equals(menuItem_OpenFile.getText()))
			{
				// Open File
//				FileChooser fileChooser = new FileChooser(this, true, FileChooser.LOAD_MODE);
//				fileChooser.setVisible(true);
				
				
				this.open();

			}
			else if(e.getActionCommand().equals(menuItem_SaveFile.getText()))
			{
				// Save File
//				FileChooser fileChooser = new FileChooser(this, true, FileChooser.SAVE_MODE);
//				fileChooser.setVisible(true);
				
				this.save();
				
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
			else if(e.getActionCommand().equals(menuItem_AStar.getText()))
			{
				SearchDialog sad = new SearchDialog(this, true, SearchDialog.ASTERNCHEN);
				sad.setVisible(true);
			}
			else if(e.getActionCommand().equals(menuItem_Kruskal.getText()))
			{
				startKruskal(menuItem_drawResult.isSelected());
			}
			else if(e.getActionCommand().equals(menuItem_Prim.getText()))
			{
				// todo
				startPrim(false,menuItem_drawResult.isSelected());
			
			}else if(e.getActionCommand().equals(menuItem_Prim_fib.getText()))
			{
				startPrim(true, menuItem_drawResult.isSelected());
			}
		}
		// Menu Help
		{
			if(e.getActionCommand().equals(menuItem_Reload.getText()))
			{
				this.repaint();
			}
			else if(e.getActionCommand().equals(drawMode.getText()))
			{
				
				VisualizationViewer vv = null;				
				for(Component c: contentPane.getComponents()){
					if(((Object) c) instanceof VisualizationViewer){
						vv = (VisualizationViewer) c;
						break;
					}
				}
				
				if(vv != null){
					if(drawMode.isSelected()) vv.setVisible(true);
					else vv.setVisible(false);
				}
			}
		}
		// Menu MoveMode
		{
			
			if(e.getActionCommand().equals(pickMode.getText())){
				
				if(pickMode.isSelected()){
					gmanager.setPicMode(viewComponent);
				}else{
					gmanager.setTrasformMode(viewComponent);
				}
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
	private JSeparator separator_3;
	private JSeparator separator_4;

	// Menu components
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuEdit;
	private JMenu menuAlgorithm;
	private JMenu menuHelp;
	private JMenuItem menuItem_NewGraph;
	private JMenuItem menuItem_OpenFile;
	private JMenuItem menuItem_SaveFile;
	private JMenuItem menuItem_Quit;	
	private JMenuItem menuItem_AddVertex;
	private JMenuItem menuItem_AddEdge;
	private JMenuItem menuItem_RemoveVertex;
	private JMenuItem menuItem_RemoveEdge;	
	private JMenuItem menuItem_Reload;
	private JCheckBox pickMode;
	private JCheckBox drawMode;
	private JMenuItem menuItem_BFS;
	
	private JMenuItem menuItem_Dijkstra;
	private JMenuItem menuItem_AStar;
	
	private JMenuItem menuItem_Kruskal;
	private JMenuItem menuItem_Prim;
	private JMenuItem menuItem_Prim_fib;
	private VisualizationViewer viewComponent;
	private JCheckBox menuItem_drawResult;
	
}
