package interfaceGraphique.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cartes.Croyant;
import interfaceGraphique.ThemeFoncé;
import partie.Partie;
import partie.PhaseDeJeu;

public class ButtonPoserCroyant extends JButton implements MouseListener, ThemeFoncé {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;

	private Croyant croyant;

	public ButtonPoserCroyant(JFrame frame, Croyant croyant) {

		this.croyant = croyant;
		this.setText("Poser ");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (Partie.getInstance().getjEnCours() == Partie.getInstance().getJReel()) {
			if (Partie.getInstance().getPhaseDeJeu() == PhaseDeJeu.PhaseJeu) {

				int tailleMain1 = Partie.getInstance().getJReel().getMainJoueur().getContenuMain().size();
				Partie.getInstance().getJReel().poserCroyant(croyant);
				int tailleMain2 = Partie.getInstance().getJReel().getMainJoueur().getContenuMain().size();
				if (tailleMain1 == tailleMain2) {
					JOptionPane.showMessageDialog(this.frame, "Vous n'avez pas assez de points d'action !",
							"Action impossible", JOptionPane.ERROR_MESSAGE);

				}
			} else {
				JOptionPane.showMessageDialog(this.frame,
						"Vous devez attendre la phase de jeu pour pouvoir sacrifier un croyant", "Action impossible",
						JOptionPane.ERROR_MESSAGE);
			}
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(this.frame, "Ce n'est pas votre tour de jeu.", "Action hors tour",
					JOptionPane.ERROR_MESSAGE);
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