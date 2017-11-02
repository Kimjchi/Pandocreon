package interfaceGraphique.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cartes.Croyant;
import cartes.GuideSpirituel;
import interfaceGraphique.ThemeFoncé;
import partie.Partie;
import partie.PhaseDeJeu;

public class ButtonSacrifierCroyant extends JButton implements MouseListener, ThemeFoncé {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;

	private Croyant croyant;

	public ButtonSacrifierCroyant(JFrame frame, Croyant croyant) {
		this.frame = frame;
		this.croyant = croyant;
		this.setText("Sacrifier");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (Partie.getInstance().getjEnCours() == Partie.getInstance().getJReel()) {
			if (Partie.getInstance().getPhaseDeJeu() == PhaseDeJeu.PhaseJeu) {

				Partie.getInstance().getjEnCours().sacrifierCroyant(croyant);
				JOptionPane.showMessageDialog(this.frame,
						"Vous sacrifiez votre croyant : " + croyant.getNom() + ". L'effet s'active.",
						"Sacrifice Croyant", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this.frame,
						"Vous devez attendre la phase de jeu pour pouvoir sacrifier un croyant", "Action impossible",
						JOptionPane.ERROR_MESSAGE);
			}
			frame.dispose();
		}

		else {
			JOptionPane.showMessageDialog(this.frame, "Ce n'est pas votre tour de jeu.", "Action hors tour",
					JOptionPane.ERROR_MESSAGE);
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
