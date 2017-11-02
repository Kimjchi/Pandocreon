package interfaceGraphique.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cartes.Apocalypse;
import cartes.CarteAction;
import interfaceGraphique.ThemeFoncé;
import partie.Partie;

public class ButtonApocalypse extends JButton implements MouseListener, ThemeFoncé {
	
private Apocalypse apo;
	
	private JFrame frame;
	
	public ButtonApocalypse(JFrame frame, Apocalypse carte) {

		this.frame = frame;
		this.apo = carte;
		this.setText("Déclancher l'Apocalypse !");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.addMouseListener(this);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (Partie.getInstance().getjEnCours() == Partie.getInstance().getJReel()) {
			JOptionPane.showMessageDialog(this.frame,
					"Vous activez votre carte Apocalypse !", "Activation apocalypse",
					JOptionPane.INFORMATION_MESSAGE);
			Partie.getInstance().getJReel().poserApocalypse(apo);
			System.out.println("Youhou, on m'a cliqué dessus !! (bouton defausse)");
			Partie.getInstance().getJReel().getMainJoueur().afficherMain();
			if (Partie.getInstance().getJoueurs().size() == 0) {
				JOptionPane.showMessageDialog(this.frame,
						"Le joueur " + Partie.getInstance().getJoueurs().get(0).getNom() + "a gagné !", "Activation apocalypse",
						JOptionPane.INFORMATION_MESSAGE);
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
