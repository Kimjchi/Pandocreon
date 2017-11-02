package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import cartes.Croyant;
import cartes.GuideSpirituel;
import joueurs.Joueur;

public class VueDevantJoueur extends JFrame implements Observer, ThemeFoncé {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();
	private Joueur j;

	private LinkedList<VueCarte> vuesCarte;

	public VueDevantJoueur(Joueur j) {

		j.addObserver(this);
		this.vuesCarte = new LinkedList<VueCarte>();
		this.j = j;

		this.setTitle("Cartes devant le joueur");
		this.setResizable(false);

		this.setBounds(484, 0, 1000, 484);

		panel.setBackground(DARK_BG);
		panel.setLayout(new GridLayout(1, 10, 0, 0));
		panel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) BORDER_COLOR));
		// this.construirelistModel();
		// lesCarte = new DefaultListModel();

		Iterator<VueCarte> itv = this.vuesCarte.iterator();
		while (itv.hasNext()) {
			itv.next().nettoyer();
		}
		//panel.removeAll();

		Iterator<GuideSpirituel> it = j.getCarteDevant().iterator();
		while (it.hasNext()) {
			GuideSpirituel carte = (GuideSpirituel) it.next();
			VueCarte vueCarte = new VueCarte(carte.getNom(), carte);

			this.vuesCarte.add(vueCarte);
			if (it.hasNext()) {
				vueCarte.setBorder(new MatteBorder(0, 0, 0, 1, (Color) BORDER_COLOR));
			}
			panel.add(vueCarte);

			Iterator<Croyant> it1 = carte.getCroyantAttache().iterator();
			while (it1.hasNext()) {
				Croyant croyant = it1.next();
				VueCarte vueCarte1 = new VueCarte(croyant.getNom(), croyant);

				this.vuesCarte.add(vueCarte1);
				if (it1.hasNext()) {
					vueCarte1.setBorder(new MatteBorder(0, 0, 0, 1, (Color) BORDER_COLOR));
				}
				panel.add(vueCarte1);
			}
		}

		// j.getCarteDevant().addObserver(this);
		// this.listeCartes = new LinkedList<VueCarteAction>();

		this.getContentPane().add(panel);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int taskBarSize = scnMax.bottom;
		this.setLocation(484, dim.height / 2 - this.getSize().height / 2);
		this.setVisible(true);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		/*Iterator<VueCarte> itv = this.vuesCarte.iterator();
		while (itv.hasNext()) {
			itv.next().nettoyer();
		}
		panel.removeAll();
		
		Iterator<GuideSpirituel> it = j.getCarteDevant().iterator();
		while (it.hasNext()) {
			GuideSpirituel carte = (GuideSpirituel) it.next();
			VueCarte vueCarte = new VueCarte(carte.getNom(), carte);

			this.vuesCarte.add(vueCarte);
			if (it.hasNext()) {
				vueCarte.setBorder(new MatteBorder(0, 0, 0, 1, (Color) BORDER_COLOR));
			}
			panel.add(vueCarte);

			Iterator<Croyant> it1 = carte.getCroyantAttache().iterator();
			while (it1.hasNext()) {
				Croyant croyant = it1.next();
				VueCarte vueCarte1 = new VueCarte(croyant.getNom(), croyant);

				this.vuesCarte.add(vueCarte1);
				if (it1.hasNext()) {
					vueCarte1.setBorder(new MatteBorder(0, 0, 0, 1, (Color) BORDER_COLOR));
				}
				panel.add(vueCarte1);
			}
			

		} */
		this.repaint();
		this.revalidate();
	}
}
