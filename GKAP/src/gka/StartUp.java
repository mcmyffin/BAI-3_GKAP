package gka;

import java.awt.EventQueue;

import gka.GraphicalView.MainFrame;


public class StartUp{
	
    public static void main(String [] args)
    {
    
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
			}
		});
    
    }
}
