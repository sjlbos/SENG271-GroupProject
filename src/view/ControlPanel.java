package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import controller.Controller;

/**
 * 
 * @author Stephen Bos
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
		
		// Create the new game button
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(controller.getStartNewGameListener());
		controller.setStartButton(newGameButton);
		
		// Load, scale, and add the volume icon
		try {
			BufferedImage volumePNG = ImageIO.read(getClass().getResource("/resources/volume.png"));
			Image icon = volumePNG.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			JLabel volumeLabel = new JLabel(new ImageIcon(icon));
			this.add(volumeLabel);
			
		} catch (IOException e) {
			e.toString();
		}
		
		// Create the volume slider
		JSlider slider = new JSlider(JSlider.HORIZONTAL,-5,6,5);
		Dimension sliderSize = new Dimension(75,newGameButton.getPreferredSize().height);
		slider.setPreferredSize(sliderSize);
		slider.setSize(sliderSize);
		slider.setPaintTicks(false);
		slider.setPaintLabels(false);
		slider.addChangeListener(controller.getSoundSliderListener());
		
		// Create the speed slider
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1,20,1);
		Dimension speedSliderSize = new Dimension(75, newGameButton.getPreferredSize().height);
		speedSlider.setPreferredSize(speedSliderSize);
		speedSlider.setSize(speedSliderSize);
		speedSlider.setPaintTicks(false);
		speedSlider.setPaintLabels(true);
		speedSlider.addChangeListener(controller.getSpeedSliderListener());
		
		// Create a label for the slider
		JLabel label = new JLabel("Game Speed ");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		// Add the components to the panel
		this.add(slider);
		this.add(Box.createHorizontalGlue());
		this.add(Box.createHorizontalGlue());
		this.add(label);
		this.add(speedSlider);
		this.add(Box.createHorizontalGlue());
		this.add(Box.createHorizontalGlue());
		this.add(newGameButton);
	}
}
