package interfaceGraphique;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import cartes.Carte;
import cartes.Croyant;
import cartes.MilieuDeTable;
import net.miginfocom.swing.MigLayout;

public class VueMilieuTable extends JPanel implements Observer, ThemeFoncé {

	/**
	 * Constante d'identification pour la sérialisation.
	 */
	public static final long serialVersionUID = 1l;

	private LinkedList<VueCarte> vuesCarte;
	

	private JPanel panel;

	private JScrollPane scroll;

	GridBagConstraints c;

	public VueMilieuTable() {

		this.vuesCarte = new LinkedList<VueCarte>();
		MilieuDeTable.getInstance().addObserver(this);

		this.panel = new JPanel();

		this.setBackground(DARK_BG);
		this.panel.setLayout(new MigLayout("", "", "0[100%]0"));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.scroll = new JScrollPane(this.panel);
		this.scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.scroll.setPreferredSize(new Dimension(dim.width, (int) (dim.height / 3.06)));

		this.add(this.scroll);
		
		Iterator<Croyant> it = MilieuDeTable.getInstance().getListeCroyants().iterator();
		while (it.hasNext()) {
			Carte carte = it.next();
			VueCarte vueCarte = new VueCarte(carte.getNom(), carte);

			this.vuesCarte.add(vueCarte);

			this.panel.add(vueCarte);

		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Iterator<VueCarte> itv = this.vuesCarte.iterator();
		while (itv.hasNext()) {
			itv.next().nettoyer();
		}

		this.panel.removeAll();

		System.out.println("Oh tiens, on m'appelle ! (update VueMilieu)");
		MilieuDeTable.getInstance().afficherMilieu();

		Iterator<Croyant> it = MilieuDeTable.getInstance().getListeCroyants().iterator();
		while (it.hasNext()) {
			Carte carte = it.next();
			VueCarte vueCarte = new VueCarte(carte.getNom(), carte);

			this.vuesCarte.add(vueCarte);

			this.panel.add(vueCarte);

		}
		this.repaint();
		this.validate();

	}
	
	

}
