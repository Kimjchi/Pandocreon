package interfaceGraphique.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import interfaceGraphique.ThemeFoncé;
import partie.Partie;

public class ButtonCapacitéDivinité extends JButton implements MouseListener, ThemeFoncé {
	
	private JFrame frame;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public ButtonCapacitéDivinité(JFrame frame) {
		this.frame = frame;
		this.setText("Utiliser Capacité");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (Partie.getInstance().getjEnCours() == Partie.getInstance().getJReel()) {
			Partie.getInstance().getJReel().utiliserDivinité();
			this.setForeground(ACCENT_FG);
			this.setEnabled(false);
			JOptionPane.showMessageDialog(this.frame,
					"L'effet de votre divinité s'active.", "Activation Effet Divinité",
					JOptionPane.INFORMATION_MESSAGE);
		} 
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
