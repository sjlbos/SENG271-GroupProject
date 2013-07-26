package view;
import java.awt.Color;
import java.awt.Dimension;
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
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL,0,100,75);
		Dimension sliderSize = new Dimension(75,newGameButton.getPreferredSize().height);
		slider.setPreferredSize(sliderSize);
		slider.setSize(sliderSize);
		slider.setPaintTicks(false);
		slider.setPaintLabels(false);
		
		String[] difficulties = {"Over 9000!","Hard","Normal","Easy"};
		JComboBox difficultySelector = new JComboBox(difficulties);
		difficultySelector.setSelectedIndex(2);
		Dimension selectorSize = new Dimension(75,newGameButton.getPreferredSize().height);
		difficultySelector.setPreferredSize(selectorSize);
		difficultySelector.setSize(selectorSize);
		
		this.add(slider);
		//this.add(Box.createRigidArea(new Dimension(20,1)));
		this.add(Box.createHorizontalGlue());
		this.add(difficultySelector);
		this.add(Box.createHorizontalGlue());
		this.add(newGameButton);
	}
}
