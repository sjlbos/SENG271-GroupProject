package view;
import java.awt.Color;
import java.awt.Dimension;

import controller.Controller;
import javax.swing.*;

public class ViewPanel extends JPanel {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private static final long serialVersionUID = 1L;
	
	public ViewPanel(Controller controller){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(ViewPanel.WIDTH,ViewPanel.HEIGHT));
	}
}
