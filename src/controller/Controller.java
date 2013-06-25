package controller;
import java.awt.event.*;

public class Controller {
	
	private StartNewGameListener startNewGameListener;
	
	public ActionListener getStartNewGameListener(){
		if(this.startNewGameListener==null){
			this.startNewGameListener = new StartNewGameListener();
		}
		return this.getStartNewGameListener();
	}
	
	private class StartNewGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}	
	}	
}
