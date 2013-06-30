package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

public class TitlePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final int HEIGHT = 60;
	public static final int WIDTH = 300;
	
	/*===================================
 	FIELDS
 	===================================*/
	private String labelText;
	private Color textColor;
	private Font labelFont = new Font("Calibri",Font.BOLD,30);
	
	/*===================================
	 CONSTRUCTORS
	 ===================================*/
	public TitlePanel(){
		this.labelText="Player 1 Turn";
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setPreferredSize(new Dimension(TitlePanel.WIDTH,TitlePanel.HEIGHT));
	}
	
	/*===================================
	 GETTERS & SETTERS
	 ===================================*/
	
	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(TitlePanel.WIDTH,TitlePanel.HEIGHT);
	}
	
	public Dimension getMaximumSize(){
		return this.getPreferredSize();
	}
	
	public Dimension getMinimumSize(){
		return this.getPreferredSize();
	}
	
	/*===================================
	 DRAWING METHODS
	 ===================================*/

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(this.textColor);
		g.setFont(labelFont);
		
		int fontHeight = g.getFontMetrics().getHeight();
		int fontStart = (TitlePanel.WIDTH-g.getFontMetrics().stringWidth(this.labelText))/2;
		g.drawString(this.labelText, fontStart, fontHeight);
	}
}
