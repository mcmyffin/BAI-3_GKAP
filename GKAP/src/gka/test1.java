package gka;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import gka.GraphBuilder.Extension.OwnVertex;

public class test1 {

	Graph<Integer, String> g;
    
    public test1(){
    
    	// Graph<V, E> where V is the type of the vertices and E is the type of the edges
        g = new SparseMultigraph<Integer, String>();
        // Add some vertices and edges
        g.addVertex((Integer)1);
        g.addVertex((Integer)2);
        g.addVertex((Integer)3); 
        g.addEdge("Edge-A", 1, 2); 
        g.addEdge("Edge-B", 2, 3);  
        
//        System.out.println(((Object)g) instanceof UndirectedSparseGraph);
    
}

	public static void main(String[] args) {
		// I create the graph in the following...
		 test1 sgv = new test1();
		 
		 // Layout<V, E>, VisualizationComponent<V,E>
		 Layout<Integer, String> layout = new CircleLayout(sgv.g);
		 
		 layout.setSize(new Dimension(300,300));
		 
		 VisualizationViewer<Integer,String> vv =
		 new VisualizationViewer<Integer,String>(layout);
		 vv.setPreferredSize(new Dimension(350,350));
		 
		 // Show vertex and edge labels
		 vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		 vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		
		 // Create a graph mouse and add it to the visualization component
		 DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		 gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		 vv.setGraphMouse(gm);
		 
		 
		 JFrame frame = new JFrame("Interactive Graph View 1");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().add(vv);
		 frame.pack();
		 frame.setVisible(true); 
	 }
		
		
		
	
}
