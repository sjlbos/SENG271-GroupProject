package controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;
import java.util.HashMap;
import javax.swing.*;
import javax.sound.sampled.*;
import view.ControlPanel;
import view.TitlePanel;
import view.ViewPanel;

/** 
 * @author Stephen Bos
 * Date: June 2013
 *
 * This class is responsible for initializing the game, creating and linking all of its components.
 */
public class Application {
	
	/*===================================
 	FIELDS
 	===================================*/
	private Controller controller;
	private JFrame applicationFrame;
	
	/*===================================
 	CONSTRUCTOR
 	===================================*/
	public Application(){
		this.initialize();
	}
	
	/*===================================
	 METHODS
	 ===================================*/
	
	/**
	 * Initializes the application. Creates the base JFrame container,
	 * instantiates the view and model classes, and creates the controller,
	 * ensuring the controller has the proper references to the view and model.  
	 */
	private void initialize(){
		
		// Initialize the controller
		this.controller = new Controller();
		
		// Initialize the game panels
		ViewPanel viewPanel = new ViewPanel(this.controller);
		ControlPanel controlPanel = new ControlPanel(this.controller);
		TitlePanel titlePanel = new TitlePanel();
		
		// Load audio resources while board is being built
		loadSoundResourcesIntoController();
		
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
		this.controller.setViewPanel(viewPanel);
		this.controller.setTitlePanel(titlePanel);
	}
	
	/**
	 * Sets the application visible, passing control to the controller.
	 */
	public void run(){
		this.applicationFrame.setVisible(true);
	}
	
	/**
	 * Loads the required sound files into the application. The sound files are converted into Clip objects and put into
	 * a hashmap which is subsequently loaded into the controller.
	 */
	private void loadSoundResourcesIntoController(){
		
		String[] audioFiles = new String[]{"PlayerCapture.wav","Dice.wav","Victory.wav","Move.wav","Goal.wav","PlayerDeath.wav","GameOver.wav"};
		HashMap<String,Clip> audioClips = new HashMap<String,Clip>();
		
		for(String fileName: audioFiles){
			try{
				Clip clip = AudioSystem.getClip();
				InputStream audioFile = Application.class.getResourceAsStream("/resources/"+fileName);
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(audioFile);
				String[] tokens = fileName.split("\\.");
				clip.open(inputStream);
				audioClips.put(tokens[0], clip);
			}
			catch(Exception e){
				System.out.println(e.toString());
				System.out.println("Audio load failed.");
			}	
		}
		
		this.controller.setAudioClips(audioClips);
	}
	
	/*===================================
	 MAIN
	 ===================================*/
	public static void main(String args[]){
		Application app = new Application();
		app.run();
	}
}
