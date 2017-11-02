package interfaceGraphique;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;
import partie.Partie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.border.LineBorder;

import cartes.CarteAction;
import cartes.ConstructeurDeCartes;
import cartes.Croyant;
import cartes.MilieuDeTable;
import cartes.Pioche;
import cartes.Position;
import cartes.Type;
import cartes.capacit�s.GagnerPointAction;
import joueurs.IA;
import joueurs.Joueur;
import joueurs.Main;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 * Composant graphique repr�sentant une carte alli� (chien ou taupe). Ce
 * composant affiche les informations de la carte qu'il suit. Ces informations
 * n'�voluant pas au cours du temps, l'affichage n'est pas mis � jour, sauf
 * lorsque la carte est sacrifi�e, auquel cas ce composant devient invisible. Ce
 * composant inclut la logique de s�lection de la carte lors du clic.
 *
 */
public class VueCarteAction extends JPanel implements Observer, ThemeFonc�, Vue {

	/**
	 * Constante d'identification pour la s�rialisation.
	 */
	public static final long serialVersionUID = 1l;

	/**
	 * R�f�rence vers la carte alli� observ�e par le composant.
	 * 
	 * Une valeur nulle signifie que le composant est dans un �tat non
	 * utilisable.
	 * 
	 * @see fr.bragabresolin.menhir.Core.Cartes.CarteAllie
	 */
	private CarteAction carte;

	private Joueur j;

	

	/**
	 * Un bouton permettant de jouer la carte "s�lectionn�e"
	 */
	protected JToggleButton bouttonPoser = new JToggleButton("Poser");

	/**
	 * Un bouton permettant de jouer la carte "s�lectionn�e"
	 */
	protected JToggleButton bouttonSacrifier = new JToggleButton("Sacrifier");

	protected JToggleButton bouttonGuider = new JToggleButton("Guider");

	/**
	 * Une comboBox permettant de choisir la cible
	 */
	private JComboBox comboCible;

	/**
	 * R�f�rence vers le jeu en train d'�tre jou�. Cette r�f�rence est utilis�e
	 * pour obtenir l'acc�s � des informations inacessibles autrement, comme la
	 * liste des autres joueurs (par exemple pour proposer de choisir une cible
	 * lors du clic sur le composant).
	 * 
	 * Une valeur nulle signifie que le composant est dans un �tat non
	 * utilisable.
	 * 
	 * @see fr.bragabresolin.menhir.Core.JeuMenhir
	 */

	/**
	 * Indique si la carte est actuellement jouable (et donc cliquable). Si ce
	 * n'est pas le cas, les actions au clic renvoient une erreur visuelle.
	 */
	private boolean estJouable;

	/**
	 * Mutateur pour la possibilit� que la carte soit cliqu�e (et jou�e) ou non.
	 * 
	 * @param estJouable
	 *            Si la carte doit �tre jouable ou non
	 */
	public void setEstJouable(boolean estJouable) {
		this.estJouable = estJouable;
	}

	/**
	 * Met � jour l'affichage de la carte. Si la carte suivie a �t� jou�e, elle
	 * devient invisible.
	 * 
	 * @param o
	 *            L'objet observ� (la carte)
	 * @param message
	 *            Le message envoy�
	 */
	public void update(Observable o, Object message) {
		if (this.carte.getPosition() == Position.D�fausse) {
			this.setVisible(false);
		}
	}

	/**
	 * Constructeur. On construit la vue de la carte en affichant sa matrice des
	 * forces et son nom, ainsi qu'en y attachant des �couteurs d'�v�nement
	 * clic. Un clic sur la carte signifie que le joueur veut jouer cette carte,
	 * auquel cas, si la carte est jouable (par exemple si c'est au tour du
	 * joueur), les informations n�cessaires � son ex�cution sont demand�es, et
	 * elle est d�pos�e dans le tampon de communication adapt�.
	 *
	 * @param carte
	 *            La carte � observer
	 */
	public VueCarteAction(CarteAction carte, Joueur j) {

		bouttonPoser.setVisible(false);
		bouttonGuider.setVisible(false);
		bouttonSacrifier.setVisible(false);
		carte.addObserver(this);
		j.addObserver(this);
		MilieuDeTable.getInstance().addObserver(this);
		this.carte = carte;
		ArrayList<String> cibles = new ArrayList<String>();
		for (int i = 0; i < Partie.getInstance().getJoueurs().size(); i++) {
			if (j != Partie.getInstance().getJoueurs().get(i)) {
				cibles.add(Integer.toString(i + 1));
			}
		}
		
		comboCible = new JComboBox<String>(cibles.toArray(new String[cibles.size()]));
		comboCible.setSelectedIndex(0);
		if (carte.getPosition() == Position.Main && j.isEstR�el()) {
			bouttonPoser.setVisible(true);
		} else if (carte.getPosition() == Position.Centre) {
			bouttonGuider.setVisible(true);
		} else if (carte.getPosition() == Position.TerrainJoueur && j.isEstR�el()) {
			bouttonSacrifier.setVisible(true);
		}
		this.setLayout(new MigLayout("wrap 1"));
		this.add(affichageCarte(), "span 3");
		//this.add(comboCible);
		this.add(bouttonGuider);
		this.add(bouttonPoser);
		this.add(bouttonSacrifier);
		
	}
	
	/**
	 * Un label permettant d'afficher le contenu de la carte alli�s du joueur
	 */
	private JLabel affichageCarte() {
		String path = "resources/images/" + carte.getType().toString() + "/" + (String) carte.getNom() + ".png";
		ImageIcon imgCarte = null;
		System.out.println(path);
		if (carte.getType() == Type.Croyant) {
			imgCarte = new ImageIcon("resources/images/Croyant/" + (String) carte.getNom() + ".png");
		} else if (carte.getType() == Type.GuideSpirituel) {
			imgCarte = new ImageIcon("resources/images/GuideSpirituel/" + (String) carte.getNom() + ".png");

		} else if (carte.getType() == Type.Apocalypse) {
			imgCarte = new ImageIcon("resources/images/Apocalypse/" + (String) carte.getNom() + ".png");

		} else if (carte.getType() == Type.DeusEx) {
			imgCarte = new ImageIcon("resources/images/DeusEx/" + (String) carte.getNom() + ".png");
		}
		JLabel lblCarte = new JLabel();
		lblCarte.setIcon(imgCarte);
		return lblCarte;
	}

	@Override
	public void lancer() {
		// TODO Auto-generated method stub

	}
}
