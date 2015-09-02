package view;

import java.awt.EventQueue;
import javax.swing.JFrame;

import model.Board;


public class Window {

	public Window(){
		
		EventQueue.invokeLater(new Runnable(){
			
			public void run(){
				
				MyFrame frame = new MyFrame();
				frame.setTitle("Chess");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.repaint();
			}
		});	
	}
	
}