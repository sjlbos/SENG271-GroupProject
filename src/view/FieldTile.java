package view;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class FieldTile extends JComponent implements MouseListener{
	
	public static final String EXIT_EVENT = "exit";
	public static final String ENTER_EVENT = "enter";
	public static final String CLICK_EVENT ="click";
	
	private static final long serialVersionUID = 1L;
	private static final int Padding = 5;
	private static final int Diameter = 30;
	
	public static final int HEIGHT = 2*Padding+Diameter;
	public static final int WIDTH = 2*Padding+Diameter;
	
	private static final Dimension dimension = new Dimension(WIDTH,HEIGHT);
	
	/*===================================
	 FIELDS
	 ===================================*/
	private boolean isActive;
	private boolean mouseEntered;
	private boolean mousePressed;
	private boolean isActiveDestination;
	private Shape circle;
	private Color fieldColor;
	private ArrayList<ActionListener> actionListeners;
	private String id;
	
	/*===================================
	 CONSTRUCTORS
	 ===================================*/
	public FieldTile(){
		super();
		this.enableInputMethods(true);
		this.addMouseListener(this);
		this.setPreferredSize(FieldTile.dimension);
		
		this.actionListeners = new ArrayList<ActionListener>();
		
		this.fieldColor=Color.WHITE;
		this.circle = new Ellipse2D.Double(Padding,Padding,Diameter,Diameter);
		this.isActive = false;
		this.id="";
	}
	
	public FieldTile(Color c){
		super();
		this.enableInputMethods(true);
		this.addMouseListener(this);
		this.setPreferredSize(FieldTile.dimension);
		
		this.actionListeners = new ArrayList<ActionListener>();
		
		this.fieldColor = c;
		this.circle = new Ellipse2D.Double(Padding,Padding,Diameter,Diameter);
		this.isActive = true;
		this.id="";
	}
	
	/*===================================
	 GETTERS & SETTERS
	 ===================================*/
	
	public void setId(String id){
		this.id=id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void toggleIsActive(){
		this.isActive=this.isActive?false:true;
	}
	
	public boolean isActive(){
		return this.isActive;
	}
	
	public void setColor(Color c){
		this.fieldColor=c;
	}
	
	public Color getColor(){
		return this.fieldColor;
	}
	
	public Dimension getPrefferedSize(){
		return FieldTile.dimension;
	}
	
	public Dimension getMinimumSize(){
		return FieldTile.dimension;
	}
	
	public Dimension getMaximumSize(){
		return FieldTile.dimension;
	}
	
	public void toggleIsActiveDestination(){
		this.isActiveDestination = isActiveDestination?false:true;
	}
	
	public void addActionListener(ActionListener subscriber){
		this.actionListeners.add(subscriber);
	}
	
	public void removeActionListener(ActionListener subscriber){
		this.actionListeners.remove(subscriber);
	}
	
	/*===================================
	 DRAWING METHODS
	 ===================================*/
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// Get 2D graphics object and turn anti aliasing on.
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Fill field
		g2.setColor(mousePressed?Color.GRAY:this.fieldColor);
		g2.fill(this.circle);
		
		// Draw border
		if(mouseEntered){
			g2.setColor(Color.BLUE);
		}
		else if(isActiveDestination){
			g2.setColor(Color.RED);
		}
		else{
			g2.setColor(Color.BLACK);
		}
		g2.setStroke(new BasicStroke(3));
		g2.draw(this.circle);
	}
	
	/*===================================
	 MOUSE EVENT HANDLERS
	 ===================================*/
	
	/**
	 * Fires an ActionEvent to all subscribers when the component is clicked.
	 */
	public void mouseClicked(MouseEvent e) {
		if(this.isActive){
			ActionEvent event = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,FieldTile.CLICK_EVENT);
			for(ActionListener subscriber:this.actionListeners){
				subscriber.actionPerformed(event);
			}
		}
	}

	/**
	 * If the mouse enters the component, the field is redrawn with a highlighted border.<br>
	 * An ActionEvent is also fired to all subscribers.
	 */
	public void mouseEntered(MouseEvent e) {
		if(this.isActive){
			this.mouseEntered = true;
			this.repaint();	
			
			ActionEvent event = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,FieldTile.ENTER_EVENT);
			for(ActionListener subscriber:this.actionListeners){
				subscriber.actionPerformed(event);
			}
		}
	}
	
	/**
	 * If the mouse exits the component, the field is redrawn with a normal border.<br>
	 * An ActionEvent is also fired to all subscribers
	 */
	public void mouseExited(MouseEvent e) {
		if(this.isActive){
			this.mouseEntered = false;
			this.repaint();
			
			ActionEvent event = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,FieldTile.EXIT_EVENT);
			for(ActionListener subscriber:this.actionListeners){
				subscriber.actionPerformed(event);
			}
		}
	}
	
	/**
	 * When the mouse is held down, paints the field background gray.
	 */
	public void mousePressed(MouseEvent e) {
		if(this.isActive){
			this.mousePressed = true;
			this.repaint();	
		}
	}
	
	/**
	 * When the mouse is released, paints the field background its regular color.
	 */
	public void mouseReleased(MouseEvent e) {
		if(this.isActive){
			this.mousePressed = false;
			this.repaint();	
		}
	}
}
