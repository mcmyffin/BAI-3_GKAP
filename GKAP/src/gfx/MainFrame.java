package gfx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import 
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphXAdapter


public class MainFrame {

	private         JGraphXAdapter<String, NamedWeightedEdge>   _adapter;
	private JFrame mainframe;
	private final String titel = "Main";
	private Dimension frameDimension;
	
	private JMenuBar menuBar;
	
	
	// CREATION
	public MainFrame(){
		
		init();
	}
	
	private void init(){
		
		// MainFrame configuration
		double height = 600;
		double width = height * (16.0 /9.0);
		
		mainframe = new JFrame(titel);
		frameDimension = new Dimension();
		frameDimension.setSize(width, height);
		
		mainframe.setSize(frameDimension);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setLocationRelativeTo(null);
		mainframe.setVisible(true);
		
		// MenuBar configuration
		menuBar = new JMenuBar();
		
		//Menu header
		JMenu file = new JMenu("File"); 
		JMenu edit = new JMenu("Edit");
		
		//menuitems
		JMenuItem newFile = new JMenuItem("New File");
		JMenuItem openFile = new JMenuItem("Open File");
		JMenuItem saveFile = new JMenuItem("Save File");
		
		JMenuItem addNode = new JMenuItem("Add Node");
		JMenuItem delNode = new JMenuItem("Del Node");
		JMenuItem addEdge = new JMenuItem("Add Edge");
		JMenuItem delEdge = new JMenuItem("Del Edge");
		
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);
		
		edit.add(addNode);
		edit.add(delNode);
		edit.add(addEdge);
		edit.add(delEdge);
		
		menuBar.add(file);
		menuBar.add(edit);
		
		//MainPanel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.red);
		
		int panelWidth = mainframe.getWidth();
		int panelHeigh = menuBar.getHeight() - mainframe.getHeight();
		
		mainPanel.setBounds(0, menuBar.getHeight()+1, panelWidth, panelHeigh);
		mainframe.add(mainPanel);
		
		
		mainframe.add(menuBar, BorderLayout.NORTH);
		
		
		
	}
	
}
