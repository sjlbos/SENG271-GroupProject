package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class DieComponent extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;

	// Die Dimensions
	
	public static final int SIDE_LENGTH = 45;
	
	private static final Dimension size = new Dimension(DieComponent.SIDE_LENGTH,DieComponent.SIDE_LENGTH);
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
	private boolean isActive;
	private boolean mouseEntered;
	private ArrayList<ActionListener> actionListeners;
	
	/*===================================
	 CONSTRUCTORS
	 ===================================*/
	
	public DieComponent(int startingValue){
		this.enableInputMethods(true);
		this.addMouseListener(this);
		
		this.dieColor=Color.WHITE;
		this.dotColor=Color.BLACK;
		this.dieRoll=startingValue;
		
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		this.setSize(size);
		
		int dotDiameter=DieComponent.SIDE_LENGTH/3;
		this.dotHeight = dotDiameter*2/3;
		this.dotWidth = dotDiameter*2/3;
		
		this.left=getWidth()*1/3 - dotDiameter/2 - dotWidth/4;
		this.center=getWidth()*2/3 - dotDiameter/2 - dotWidth/2;
		this.right=getWidth() - dotDiameter/2 - dotWidth*3/4;
		
		this.top=getHeight()*1/3 - dotDiameter/2 - dotHeight/4;
		this.middle=getHeight()*2/3 - dotDiameter/2 - dotHeight/2;
		this.bottom=getHeight() - dotDiameter/2 - dotHeight*3/4;
		
		this.isActive = false;
		this.mouseEntered = false;
		this.actionListeners = new ArrayList<ActionListener>();
	}
	
	/*===================================
	 GETTERS & SETTERS
	 ===================================*/
	
	public void setDieRoll(int roll){
		this.dieRoll=roll;
		this.paintImmediately(0, 0, DieComponent.SIDE_LENGTH, DieComponent.SIDE_LENGTH);
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
	
	public Dimension getPreferredSize(){
		return DieComponent.size;
	}
	
	public Dimension getMaximumSize(){
		return DieComponent.size;
	}
	
	public Dimension getMinimumSize(){
		return DieComponent.size;
	}
	
	public void toggleIsActive(){
		this.isActive = this.isActive?false:true;
	}
	
	public void addActionListener(ActionListener subscriber){
		this.actionListeners.add(subscriber);
	}
	
	public void removeActionLister(ActionListener subscriber){
		this.actionListeners.remove(subscriber);
	}
	
	/*===================================
	 DRAWING METHODS
	 ===================================*/
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// Turn on anti-aliasing
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw background
		g.setColor(this.dieColor);
		g.fillRoundRect(0, 0, getWidth(), getHeight(),(int)Math.sqrt(getWidth()),(int)Math.sqrt(getHeight()));
		
		// Draw border
		if(this.mouseEntered){
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.BLUE);
			g2.drawRoundRect(0, 0, getWidth(), getHeight(),(int)Math.sqrt(getWidth()),(int)Math.sqrt(getHeight()));
		}
		
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
			System.out.println("Invalid dice roll encountered:"+this.dieRoll+".");
		}
	}
	
	/*===================================
	 MOUSE EVENT HANDLERS
	 ===================================*/
	
	/**
	 * Fires an ActionEvent to all subscribers when the component is clicked.
	 */
	public void mouseClicked(MouseEvent e) {
		if(this.isActive){
			ActionEvent event = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Dice Clicked");
			for(ActionListener subscriber:this.actionListeners){
				subscriber.actionPerformed(event);
			}	
		}
	}
	
	/**
	 * If the mouse enters the component, the field is redrawn with a highlighted border.
	 */
	public void mouseEntered(MouseEvent e) {
		if(this.isActive){
			this.mouseEntered = true;
			this.repaint();	
		}
	}
	
	/**
	 * If the mouse exits the component, the field is redrawn with a normal border.
	 */
	public void mouseExited(MouseEvent e) {
		if(this.isActive){
			this.mouseEntered = false;
			this.repaint();	
		}
	}

	public void mousePressed(MouseEvent e) {
		// Unused event	
	}

	public void mouseReleased(MouseEvent e) {
		// Unused event
	}
}
