package interfaceGraphique;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cartes.Croyant;
import cartes.Position;
import interfaceGraphique.buttons.ButtonD�fausser;
import interfaceGraphique.buttons.ButtonGuiderCeCroyant;
import interfaceGraphique.buttons.ButtonPoserCroyant;
import interfaceGraphique.buttons.ButtonSacrifierCroyant;
import net.miginfocom.swing.MigLayout;
import partie.Partie;
import partie.PhaseDeJeu;

public class VueCroyant extends JFrame implements Observer, ThemeFonc� {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Croyant croyant;
	
	public VueCroyant(Croyant croyant) {

		/*
		 * if (carte.getPosition() == Position.Main && j.isEstR�el()) {
		 * bouttonPoser.setVisible(true); bouttonSacrifier.setVisible(false);
		 * 
		 * } else if (carte.getPosition() == Position.Centre) {
		 * bouttonGuider.setVisible(true); bouttonPoser.setVisible(false);
		 * bouttonSacrifier.setVisible(false);
		 * 
		 * } else if (carte.getPosition() == Position.TerrainJoueur &&
		 * j.isEstR�el()) { bouttonSacrifier.setVisible(true);
		 * bouttonPoser.setVissible(false); bouttonGuider.setVisible(false); }
		 */
		Partie.getInstance().addObserver(this);
		croyant.addObserver(this);
		this.croyant = croyant;
		this.setTitle("Croyant");
		this.setResizable(false);

		this.setBounds(0, 0, 343, 484);

		this.getContentPane().setLayout(new MigLayout("", "0[100%,grow]0", "0[95%]0[5%]0"));
		this.getContentPane().add(new VueCarte(croyant.getNom(), croyant), "cell 0 0, grow");

		// On ajoute le bouton au content pane de la JFrame

		// Au centre
		JPanel Div = new JPanel();

		Div.setLayout(new GridLayout(1, 2));

		ButtonPoserCroyant buttonPoser = new ButtonPoserCroyant(this, croyant);

		ButtonSacrifierCroyant buttonSacrifier = new ButtonSacrifierCroyant(this, croyant);
		
		ButtonGuiderCeCroyant buttonGuide = new ButtonGuiderCeCroyant(this, croyant);

		ButtonD�fausser buttonD�fausser = new ButtonD�fausser(this, croyant);
		
		// this.getContentPane().add(new JButton("Utiliser capacit�"));
		// this.getContentPane().add(new JButton("D�fausser"));
		if (croyant.getPosition() == Position.Main) {
			Div.add(buttonPoser);
			Div.add(buttonD�fausser);
		} else if (croyant.getPosition() == Position.TerrainJoueur) {
			Div.add(buttonSacrifier);
		} else if (croyant.getPosition() == Position.Centre) {
			Div.add(buttonGuide);
		}

		this.getContentPane().add(Div, "cell 0 1, grow");

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.removeAll();
		this.getContentPane().setLayout(new MigLayout("", "0[100%,grow]0", "0[95%]0[5%]0"));
		this.getContentPane().add(new VueCarte(croyant.getNom(), croyant), "cell 0 0, grow");

		JPanel Div = new JPanel();

		Div.setLayout(new GridLayout(1, 2));

		ButtonPoserCroyant buttonPoser = new ButtonPoserCroyant(this, croyant);

		ButtonSacrifierCroyant buttonSacrifier = new ButtonSacrifierCroyant(this, croyant);
		
		ButtonGuiderCeCroyant buttonGuide = new ButtonGuiderCeCroyant(this, croyant);

		ButtonD�fausser buttonD�fausser = new ButtonD�fausser(this, croyant);
		
		// this.getContentPane().add(new JButton("Utiliser capacit�"));
		// this.getContentPane().add(new JButton("D�fausser"));
		if (croyant.getPosition() == Position.Main) {
			Div.add(buttonPoser);
			Div.add(buttonD�fausser);
		} else if (croyant.getPosition() == Position.TerrainJoueur) {
			Div.add(buttonSacrifier);
		} else if (croyant.getPosition() == Position.Centre && Partie.getInstance().getPhaseDeJeu() == PhaseDeJeu.PhaseGuideSpirituel) {
			Div.add(buttonGuide);
		}

		this.getContentPane().add(Div, "cell 0 1, grow");
		this.repaint();
		this.revalidate();
	}

}
