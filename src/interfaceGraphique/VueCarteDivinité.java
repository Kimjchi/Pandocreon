package interfaceGraphique;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import cartes.Divinité;
import interfaceGraphique.buttons.ButtonCapacitéDivinité;
import partie.Partie;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VueCarteDivinité extends JFrame implements Observer, ThemeFoncé {

	private Divinité divinité;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VueCarteDivinité(Divinité divinité) {
		divinité.addObserver(this);
		this.divinité = divinité;
		ImageIcon imgDiv = new ImageIcon(VueCarteDivinité.class.getClassLoader()
				.getResource("interfaceGraphique/ImagesDivinité/" + divinité.getNom() + ".png"));
		JLabel Div = new JLabel(imgDiv);
		Div.setToolTipText(divinité.getNom() + ", Origine : " + divinité.getOrigine() + ", Dogmes : "
				+ divinité.getDogme(0) + ", " + divinité.getDogme(1) + ", " + divinité.getDogme(2) + ", Capacité : "
				+ divinité.getCapacite());

		Div.setIcon(imgDiv);

		// this.add(Div);
		this.setTitle("Divinité");
		this.setResizable(false);

		this.setBounds(0, 0, 484, 380);

		this.getContentPane().add(Div, BorderLayout.CENTER);

		// On ajoute le bouton au content pane de la JFrame

		// Au centre
		if (this.divinité == Partie.getInstance().getJReel().getDivinité()) {
			ButtonCapacitéDivinité buttonCapacité = new ButtonCapacitéDivinité(this);
			this.getContentPane().add(buttonCapacité, BorderLayout.SOUTH);
			
		}
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int taskBarSize = scnMax.bottom;
		this.setLocation(0, dim.height/2-this.getSize().height/2);
		
		this.setVisible(true);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.repaint();
		this.revalidate();

	}

}
