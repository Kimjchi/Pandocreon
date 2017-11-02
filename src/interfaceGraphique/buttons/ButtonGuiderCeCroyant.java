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

public class ButtonGuiderCeCroyant extends JButton implements MouseListener, ThemeFoncé {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;

	private Croyant croyant;

	public ButtonGuiderCeCroyant(JFrame frame, Croyant croyant) {
		this.croyant = croyant;
		this.frame = frame;
		this.setText("Guider");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (Partie.getInstance().getjEnCours() == Partie.getInstance().getJReel()) {
			if (Partie.getInstance().getCarteActivee() != null) {
				if (Partie.getInstance().getJReel()
						.testerCroyant((GuideSpirituel) Partie.getInstance().getCarteActivee(), croyant)) {
					Partie.getInstance().getjEnCours()
							.guiderCroyant((GuideSpirituel) Partie.getInstance().getCarteActivee(), croyant);
					System.out.println(((GuideSpirituel) Partie.getInstance().getCarteActivee()).getCroyantAttache().size());
					// } else {
					// JOptionPane.showMessageDialog(this.frame, "Vous n'avez
					// pas
					// posé de croyant", "Action impossible",
					// JOptionPane.ERROR_MESSAGE);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(this.frame, "Ce croyant n'est pas valide", "Action invalide",
							JOptionPane.ERROR_MESSAGE);
				}
				// } else {
				// JOptionPane.showMessageDialog(this.frame, "Ce croyant n'est
				// pas valide", "Action invalide",
				// JOptionPane.ERROR_MESSAGE);
				// }

			} else {
				JOptionPane.showMessageDialog(this.frame, "Ce n'est pas votre tour de jeu.", "Action hors tour",
						JOptionPane.ERROR_MESSAGE);
			} 
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
