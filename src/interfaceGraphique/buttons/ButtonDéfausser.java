package interfaceGraphique.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cartes.CarteAction;
import interfaceGraphique.ThemeFoncé;
import partie.Partie;
import partie.PhaseDeJeu;

public class ButtonDéfausser extends JButton implements MouseListener, ThemeFoncé {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CarteAction carte;

	private JFrame frame;

	public ButtonDéfausser(JFrame frame, CarteAction carte) {

		this.frame = frame;
		this.carte = carte;
		this.setText("Défausser");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (Partie.getInstance().getjEnCours() == Partie.getInstance().getJReel()) {
			if (Partie.getInstance().getPhaseDeJeu() == PhaseDeJeu.PhaseDéfausse) {

				Partie.getInstance().getJReel().getMainJoueur().enleverMain(carte);
				System.out.println("Youhou, on m'a cliqué dessus !! (bouton defausse)");
				Partie.getInstance().getJReel().getMainJoueur().afficherMain();
			} else {
				JOptionPane.showMessageDialog(this.frame, "La phase de défausse est déjà passée, il faut attendre le tour suivant.", "Action impossible",
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
