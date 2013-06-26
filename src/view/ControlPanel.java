package view;
import java.awt.Color;

import javax.swing.*;
import controller.Controller;

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
