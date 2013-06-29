package view;
import java.awt.*;
import javax.swing.*;

public class DicePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Die Dimensions
	private Dimension size;
	private int left;
	private int right;
	private int center;
	private int top;
	private int middle;
	private int bottom;
	private int dotHeight;
	private int dotWidth;
	
	/*===================================
 	FIELDS
 	===================================*/
	private int dieRoll;
	private Color dieColor;
	private Color dotColor;
	
	/*===================================
	 CONSTRUCTORS
	 ===================================*/
	
	public DicePanel(int startingValue,int sideLength){
		this.dieColor=Color.WHITE;
		this.dotColor=Color.BLACK;
		this.dieRoll=startingValue;
		
		this.size = new Dimension(sideLength,sideLength);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		this.setSize(size);
		
		int dotDiameter=sideLength/3;
		this.dotHeight = dotDiameter*2/3;
		this.dotWidth = dotDiameter*2/3;
		
		this.left=getWidth()*1/3 - dotDiameter/2 - dotWidth/4;
		this.center=getWidth()*2/3 - dotDiameter/2 - dotWidth/2;
		this.right=getWidth() - dotDiameter/2 - dotWidth*3/4;
		
		this.top=getHeight()*1/3 - dotDiameter/2 - dotHeight/4;
		this.middle=getHeight()*2/3 - dotDiameter/2 - dotHeight/2;
		this.bottom=getHeight() - dotDiameter/2 - dotHeight*3/4;
	}
	
	/*===================================
	 GETTERS & SETTERS
	 ===================================*/
	
	public void setDieRoll(int roll){
		this.dieRoll=roll;
		this.repaint();
	}
	
	public int getDieRoll(){
		return this.dieRoll;
	}
	
	public void setDieColor(Color dieColor){
		this.dieColor=dieColor;
	}
	
	public void setDotColor(Color dotColor){
		this.dotColor=dotColor;
	}
	
	/*===================================
	 DRAWING METHODS
	 ===================================*/
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// Turn on anti-aliasing
		Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw background
		g.setColor(this.dieColor);
		g.fillRoundRect(0, 0, getWidth(), getHeight(),(int)Math.sqrt(getWidth()),(int)Math.sqrt(getHeight()));
		
		// Draw dots
		g.setColor(this.dotColor);
		switch(this.dieRoll){
		case 1:
			g.fillOval(center, middle,dotWidth,dotHeight);
			break;
		case 2:
			g.fillOval(left, top, dotWidth, dotHeight);
			g.fillOval(right, bottom, dotWidth, dotHeight);
			break;
		case 3:
			g.fillOval(left, top, dotWidth, dotHeight);
			g.fillOval(center, middle, dotWidth, dotHeight);
			g.fillOval(right, bottom, dotWidth, dotHeight);
			break;
		case 4:
			g.fillOval(left, top, dotWidth, dotHeight);
			g.fillOval(left, bottom, dotWidth, dotHeight);
			g.fillOval(right, top, dotWidth, dotHeight);
			g.fillOval(right, bottom, dotWidth, dotHeight);
			break;
		case 5:
			g.fillOval(left, top, dotWidth, dotHeight);
			g.fillOval(left, bottom, dotWidth, dotHeight);
			g.fillOval(right, top, dotWidth, dotHeight);
			g.fillOval(right, bottom, dotWidth, dotHeight);
			g.fillOval(center, middle, dotWidth, dotHeight);
			break;
		case 6:
			g.fillOval(left, top, dotWidth, dotHeight);
			g.fillOval(left, middle, dotWidth, dotHeight);
			g.fillOval(left, bottom, dotWidth, dotHeight);
			g.fillOval(right, top, dotWidth, dotHeight);
			g.fillOval(right, middle, dotWidth, dotHeight);
			g.fillOval(right, bottom, dotWidth, dotHeight);
			break;
		default:
			//How did this happen?
			System.out.println("Invalid dice roll encountered.");
		}
	}
}
