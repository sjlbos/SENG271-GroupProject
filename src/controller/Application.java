package controller;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import view.ControlPanel;
import view.TitlePanel;
import view.ViewPanel;
import model.Board;

public class Application {
	
	// Properties
	private Controller controller;
	private JFrame applicationFrame;
	
	// Constructor
	public Application(){
		this.initialize();
	}
	
	/**
	 * Initializes the application. Creates the base JFrame container,
	 * instantiates the view and model classes, and creates the a controller,
	 * ensuring the controller has the proper references to the view and model.  
	 */
	private void initialize(){
		
		// Initialize the controller
		this.controller = new Controller();
		
		// Initialize the model
		Board gameBoard = new Board(this.controller);
		
		// Initialize the game panels
		ViewPanel viewPanel = new ViewPanel(this.controller);
		ControlPanel controlPanel = new ControlPanel(this.controller);
		TitlePanel titlePanel = new TitlePanel();
		
		// Set up the JFrame
		this.applicationFrame = new JFrame("Ludo");
		this.applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.applicationFrame.setResizable(false);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.LINE_AXIS));
		topPanel.add(titlePanel);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.setBackground(Color.BLACK);
		
		JPanel backPanel = new JPanel(new BorderLayout());
		backPanel.add(topPanel,BorderLayout.NORTH);
		backPanel.add(viewPanel,BorderLayout.CENTER);
		backPanel.add(controlPanel,BorderLayout.SOUTH);
		
		this.applicationFrame.add(backPanel);
		this.applicationFrame.pack();
		
		// Add components to the controller
		this.controller.setBoard(gameBoard);
		this.controller.setViewPanel(viewPanel);	
	}
	
	/**
	 * Sets the application visible and passes control to the controller.
	 */
	public void run(){
		this.applicationFrame.setVisible(true);
	}
	
	// Main
	public static void main(String args[]){
		Application app = new Application();
		app.run();
	}
}
