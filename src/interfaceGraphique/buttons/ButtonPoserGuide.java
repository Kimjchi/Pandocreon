package interfaceGraphique.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cartes.CarteAction;
import cartes.GuideSpirituel;
import cartes.MilieuDeTable;
import interfaceGraphique.ThemeFoncé;
import interfaceGraphique.VueGuidageCroyant;
import partie.Partie;
import partie.PhaseDeJeu;

public class ButtonPoserGuide extends JButton implements MouseListener, ThemeFoncé {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;

	private GuideSpirituel guide;

	public ButtonPoserGuide(JFrame frame, GuideSpirituel guide) {

		this.guide = guide;
		this.setText("Poser");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (Partie.getInstance().getjEnCours() == Partie.getInstance().getJReel()) {
			if (Partie.getInstance().getPhaseDeJeu() == PhaseDeJeu.PhaseJeu) {
				if (!MilieuDeTable.getInstance().getListeCroyants().isEmpty()) {
					Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseGuideSpirituel);
					if (!Partie.getInstance().getJReel().verifierPoints(guide)) {
						JOptionPane.showMessageDialog(this.frame, "Vous n'avez pas assez de points d'action !",
								"Action impossible", JOptionPane.ERROR_MESSAGE);
					} else {
						Partie.getInstance().setCarteActivee(guide);
						JOptionPane.showMessageDialog(this.frame, "Veuillez selectionner les croyants à guider",
								"Pose Guide", JOptionPane.INFORMATION_MESSAGE);
						Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseGuideSpirituel);

						VueGuidageCroyant vueGuidageCroyant = new VueGuidageCroyant();
						// vueGuidageCroyant.setVisible(true);
						// this.add(vueGuidageCroyant);
						while (vueGuidageCroyant.isActive()) {
						}

					}
					//if (guide.getCroyantAttache().size() != 0) {
						Partie.getInstance().getJReel().getCarteDevant().add((GuideSpirituel) guide);
						Partie.getInstance().getJReel().getCarteDevant();
						Partie.getInstance().getJReel().getMainJoueur().enleverMain(guide);
					//}
				} else {
					JOptionPane.showMessageDialog(this.frame, "Il n'y a pas de croyant au centre", "Action impossible",
							JOptionPane.ERROR_MESSAGE);
				}
				Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseJeu);
			} else {
				JOptionPane.showMessageDialog(this.frame,
						"Vous devez attendre la phase de jeu pour pouvoir poser un guide", "Action impossible",
						JOptionPane.ERROR_MESSAGE);
			}
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(this.frame, "Ce n'est pas votre tour de jeu.", "Action hors tour",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setCarteActiveeNull() {

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
