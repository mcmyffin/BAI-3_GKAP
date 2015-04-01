package gka;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JApplet;

import org.jgraph.JGraph;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.UndirectedWeightedSubgraph;
import org.jgrapht.graph.WeightedPseudograph;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;
import com.jgraph.layout.tree.JGraphCompactTreeLayout;
import com.jgraph.layout.tree.JGraphTreeLayout;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;

/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 *
 * @since Aug 3, 2003
 */
public class JGraphAdapterDemo extends JApplet {
    private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 630, 420 );

    // 
//    private mxGraphComponent comp;
//    private Graph jgrapht;
    JGraph g;
    private JGraphModelAdapter<String, DefaultEdge> m_jgAdapter;

    /**
     * @see java.applet.Applet#init().
     */
    
	public void init(  ) {
    	
		// Undirected, Unweighted
//    	Graph<EigenerKnoten, DefaultWeightedEdge> graph1 = new Pseudograph<V, E>(arg0);
		// Undirected, Weighted
//    	Graph<EigenerKnoten, DefaultWeightedEdge> graph3 = new WeightedPseudograph<V, E>(arg0);
		// Directed, Unweighted
//    	Graph<EigenerKnoten, DefaultWeightedEdge> graph4 = new DirectedPseudograph<V, E>(arg0);
		// Directed, Weighted
//		Graph<EigenerKnoten, DefaultWeightedEdge> graph2 = new DirectedWeightedPseudograph(arg0);

		
    	
    	// Setup ListenerGraph
		// ADT GRAPH
		Graph<String, DefaultEdge> ug = new DirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);
		// ListenableGraph
		ListenableGraph<String, DefaultEdge> jgrapht = new ListenableDirectedGraph<String, DefaultEdge>((DirectedPseudograph<String, DefaultEdge>)ug);
		
		// ModelAdapter
		m_jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(jgrapht);
		
		// JGraph View Component
    	g = new JGraph(m_jgAdapter);
    	
    	// Add view Component to Panelname
    	getContentPane().add(g);
		        
    	// Frame Settings Example
        resize( DEFAULT_SIZE );
        
        // add some Vertexes (graph manipulated via JGraphT)
        jgrapht.addVertex( "v1" );
        jgrapht.addVertex( "v2" );
        jgrapht.addVertex( "v3" );
        jgrapht.addVertex( "v4" );
        
        
        // add some Edges (graph manipulated via JGraphT)
        jgrapht.addEdge("v1", "v2");
        jgrapht.addEdge( "v3", "v2" );
        jgrapht.addEdge( "v2", "v3" );
        jgrapht.addEdge( "v3", "v1" );
        jgrapht.addEdge( "v4", "v3" );
        
        // Setup Position for Vertexes
        positionVertexAt( "v1", 130, 40);
        positionVertexAt( "v2", 60, 200);
        positionVertexAt( "v3", 310, 230);
        positionVertexAt( "v4", 380, 70);
        
    }

//    private void setUpLayout(Graph g){
//    	
//    	
//    	mxCircleLayout cl = new mxCircleLayout();
//    	cl.execute(adapter.getDefaultParent());
//    	
//    	comp = new mxGraphComponent(adapter);
//    	getContentPane().add(comp);
//    	
//    }
    
//    private int[] getRandPos(int maxX, int maxY){
//    	
//    	Random randX = new Random(maxX);
//    	Random randY = new Random(maxY);
//    	int[] pos = {randX.nextInt(),randY.nextInt()};
//    	
//    	return pos;
//    }
    
    
    private void adjustDisplaySettings() {
        
    	setPreferredSize( DEFAULT_SIZE );
        
        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        g.setBackground( c );
    }


    private void positionVertexAt( String vertex, int x, int y) {

    	DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes();
    	
        Rectangle2D        b    = GraphConstants.getBounds(attr);
        b.setRect(x, y, 30.0, 20.0);
        
        GraphConstants.setBounds(attr, b);

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr, null, null, null);
    }
}