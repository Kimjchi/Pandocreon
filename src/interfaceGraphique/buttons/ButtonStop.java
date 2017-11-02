package interfaceGraphique.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import interfaceGraphique.ThemeFoncé;

public class ButtonStop extends JButton implements MouseListener, ThemeFoncé  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean aFini;
	
	public ButtonStop() {
		
		this.aFini = false;
		this.setText("J'ai fini de guider les croyants");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);
		//this.frame = frame;
	}
	
	public boolean aFini(){
		return this.aFini;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.aFini = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
