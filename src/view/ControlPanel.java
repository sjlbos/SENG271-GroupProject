package view;
import java.awt.Color;

import javax.swing.*;
import controller.Controller;

/**
 * 
 * @author Stephen Box
 * June, 2013
 * 
 * This panel rests on the bottom of the game screen and contains the start 
 * new game button.
 */
public class ControlPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public ControlPanel(Controller controller){
		this.setBackground(Color.GRAY);
		this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(controller.getStartNewGameListener());
		
		this.add(Box.createHorizontalGlue());
		this.add(newGameButton);
	}
}
