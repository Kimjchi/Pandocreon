package interfaceGraphique.buttons;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.UIManager;

import interfaceGraphique.ThemeFoncé;
import partie.Partie;
import partie.PhaseDeJeu;

public class ButtonAvancerTour extends JButton implements MouseListener, ThemeFoncé {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonAvancerTour() {

		this.setText("Passer à la phase de pioche");
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		this.setFont(UIManager.getFont("Table.font"));
		this.addMouseListener(this);
		System.out.println(Partie.getInstance().getPhaseDeJeu());
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(Partie.getInstance().getPhaseDeJeu());
		this.removeAll();
		switch (Partie.getInstance().getPhaseDeJeu()) {
		case PhaseDéfausse :
			this.setText("Passer à la phase de jeu");
			Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhasePioche);
			break;
		case PhasePioche :
			this.setText("Finir votre Tour");
			Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseJeu);
			break;
		case PhaseJeu :
			this.setText("Veuillez Patienter...");
			Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseTourFini);
			break;
		case PhaseTourFini :
			this.setText("Veuillez Patienter...");
			Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseTourFini);
			break;
		case PhaseAdversaire :
			this.setText("Veuillez Patienter...");
			break;
		
		case PhaseGuideSpirituel :
			this.setText("Finir votre Tour");
			Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.FinPhaseGuideSpirituel);
			break;
		
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
