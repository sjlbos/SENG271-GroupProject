package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

/**
 * 
 * @author Stephen Bos
 * June, 2013
 *
 * This class is a JPanel label component that displays the current status of the game.
 * This panel can display the current player, report a player's victory, or display a custom
 * message.
 */
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
		this.labelText="Welcome to Ludo!";
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setPreferredSize(new Dimension(TitlePanel.WIDTH,TitlePanel.HEIGHT));
	}
	
	/*===================================
	 GETTERS & SETTERS
	 ===================================*/
	
	public Dimension getPreferredSize(){
		return new Dimension(TitlePanel.WIDTH,TitlePanel.HEIGHT);
	}
	
	public Dimension getMaximumSize(){
		return this.getPreferredSize();
	}
	
	public Dimension getMinimumSize(){
		return this.getPreferredSize();
	}
	
	/**
	 * Displays a custom message in a black font on the label.
	 * 
	 * @param message - the custom message to be displayed. This component will not resize to account for longer strings.
	 */
	public void setCustomMessage(String message){
		this.textColor = Color.BLACK;
		this.labelText = message;
		this.repaint();
	}
	
	/**
	 * Sets the label to display the current turn.
	 * @param playerNumber - The player number. Must be an integer between 1 and 4. 
	 */
	public void setTurnForPlayerNumber(int playerNumber, String playerName){
		switch(playerNumber){
		case 1:
			this.textColor = ViewPanel.PLAYER_1_COLOR;
			this.labelText = playerName + "'s Turn";
			break;
		case 2:
			this.textColor = ViewPanel.PLAYER_2_COLOR;
			this.labelText = playerName + "'s Turn";
			break;
		case 3:
			this.textColor = ViewPanel.PLAYER_3_COLOR;
			this.labelText = playerName + "'s Turn";
			break;
		case 4:
			this.textColor = ViewPanel.PLAYER_4_COLOR;
			this.labelText = playerName + "'s Turn";
			break;
		default:
			System.out.println("Invalid player number passed to TitlePanel.");
		}
		this.repaint();
	}
	
	/**
	 * Sets the label to display a player victory.
	 * @param playerNumber - The player number. Must be an integer between 1 and 4.
	 */
	public void setVictoryForPlayer(int playerNumber){
		switch(playerNumber){
		case 1:
			this.textColor = ViewPanel.PLAYER_1_COLOR;
			this.labelText = "Player 1 Wins!";
			break;
		case 2:
			this.textColor = ViewPanel.PLAYER_2_COLOR;
			this.labelText = "Player 2 Wins!";
			break;
		case 3:
			this.textColor = ViewPanel.PLAYER_3_COLOR;
			this.labelText = "Player 3 Wins!";
			break;
		case 4:
			this.textColor = ViewPanel.PLAYER_4_COLOR;
			this.labelText = "Player 4 Wins!";
			break;
		default:
			System.out.println("Invalid player number passed to TitlePanel.");
		}
		this.repaint();
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
