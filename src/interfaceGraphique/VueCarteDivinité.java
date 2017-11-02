package interfaceGraphique;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import cartes.Divinit�;
import interfaceGraphique.buttons.ButtonCapacit�Divinit�;
import partie.Partie;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VueCarteDivinit� extends JFrame implements Observer, ThemeFonc� {

	private Divinit� divinit�;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VueCarteDivinit�(Divinit� divinit�) {
		divinit�.addObserver(this);
		this.divinit� = divinit�;
		ImageIcon imgDiv = new ImageIcon(VueCarteDivinit�.class.getClassLoader()
				.getResource("interfaceGraphique/ImagesDivinit�/" + divinit�.getNom() + ".png"));
		JLabel Div = new JLabel(imgDiv);
		Div.setToolTipText(divinit�.getNom() + ", Origine : " + divinit�.getOrigine() + ", Dogmes : "
				+ divinit�.getDogme(0) + ", " + divinit�.getDogme(1) + ", " + divinit�.getDogme(2) + ", Capacit� : "
				+ divinit�.getCapacite());

		Div.setIcon(imgDiv);

		// this.add(Div);
		this.setTitle("Divinit�");
		this.setResizable(false);

		this.setBounds(0, 0, 484, 380);

		this.getContentPane().add(Div, BorderLayout.CENTER);

		// On ajoute le bouton au content pane de la JFrame

		// Au centre
		if (this.divinit� == Partie.getInstance().getJReel().getDivinit�()) {
			ButtonCapacit�Divinit� buttonCapacit� = new ButtonCapacit�Divinit�(this);
			this.getContentPane().add(buttonCapacit�, BorderLayout.SOUTH);
			
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
