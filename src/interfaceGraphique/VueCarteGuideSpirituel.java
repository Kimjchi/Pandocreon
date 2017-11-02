package interfaceGraphique;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cartes.GuideSpirituel;
import cartes.Position;
import interfaceGraphique.buttons.ButtonDéfausser;
import interfaceGraphique.buttons.ButtonPoserGuide;
import interfaceGraphique.buttons.ButtonSacrifierGuide;
import net.miginfocom.swing.MigLayout;

public class VueCarteGuideSpirituel extends JFrame implements Observer, ThemeFoncé {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	

	public VueCarteGuideSpirituel(GuideSpirituel guide) {
		
		guide.addObserver(this);

		this.setTitle("Guide");
		this.setResizable(false);

		this.setBounds(0, 0, 343, 484);

		this.getContentPane().setLayout(new MigLayout("", "0[100%,grow]0", "0[95%]0[5%]0"));
		this.getContentPane().add(new VueCarte(guide.getNom(), guide), "cell 0 0, grow");

		// On ajoute le bouton au content pane de la JFrame

		// Au centre
		JPanel Div = new JPanel();

		Div.setLayout(new GridLayout(1, 2));

		// this.getContentPane().add(new JButton("Utiliser capacité"));
		// this.getContentPane().add(new JButton("Défausser"));
		
		ButtonPoserGuide buttonPoser = new ButtonPoserGuide(this, guide);
		
		ButtonSacrifierGuide buttonSacrifier = new ButtonSacrifierGuide(this, guide);

		ButtonDéfausser buttonDefausser = new ButtonDéfausser(this, guide);
		//buttonDefausser.setBackground(DARK_BG);
		//buttonDefausser.setForeground(LIGHT_FG);

		if (guide.getPosition() == Position.Main) {
			Div.add(buttonPoser);
			Div.add(buttonDefausser);
		} else if (guide.getPosition() == Position.TerrainJoueur) {
			Div.add(buttonSacrifier);
		}

		this.getContentPane().add(Div, "cell 0 1, grow");

		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
