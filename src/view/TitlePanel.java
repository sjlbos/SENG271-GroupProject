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
	 * Sets the label to display the current turn.
	 * @param playerNumber - The player number. Must be an integer between 1 and 4. 
	 */
	public void setTurnForPlayerNumber(int playerNumber){
		switch(playerNumber){
		case 1:
			this.textColor = ViewPanel.PLAYER_1_COLOR;
			this.labelText = "Player 1 Turn";
			break;
		case 2:
			this.textColor = ViewPanel.PLAYER_2_COLOR;
			this.labelText = "Player 2 Turn";
			break;
		case 3:
			this.textColor = ViewPanel.PLAYER_3_COLOR;
			this.labelText = "Player 3 Turn";
			break;
		case 4:
			this.textColor = ViewPanel.PLAYER_4_COLOR;
			this.labelText = "Player 4 Turn";
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
