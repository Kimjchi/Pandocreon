package interfaceGraphique.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import cartes.DeusEx;
import interfaceGraphique.ThemeFoncé;
import partie.Partie;
import partie.PhaseDeJeu;

public class ButtonActiverDeusEx extends JButton implements MouseListener, ThemeFoncé {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DeusEx deusEx;

	public ButtonActiverDeusEx(DeusEx deusEx) {
		this.deusEx = deusEx;
		this.setText("Activer DeusEx");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (Partie.getInstance().getjEnCours() == Partie.getInstance().getJReel()) {
			if (Partie.getInstance().getPhaseDeJeu() == PhaseDeJeu.PhaseJeu) {
				
				Partie.getInstance().getJReel().utiliserDeusEx(deusEx);
			} else {
				JOptionPane.showMessageDialog(this, "Vous devez attendre la phase de jeu pour pouvoir activer une carte DeusEx", "Action impossible",
						JOptionPane.ERROR_MESSAGE);
			}
		}
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
