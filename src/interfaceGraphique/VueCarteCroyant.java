package interfaceGraphique;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import partie.Partie;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.border.LineBorder;

import cartes.CarteAction;
import cartes.Croyant;
import cartes.MilieuDeTable;
import cartes.Position;
import joueurs.IA;
import joueurs.Joueur;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 * Composant graphique représentant une carte allié (chien ou taupe). Ce
 * composant affiche les informations de la carte qu'il suit. Ces informations
 * n'évoluant pas au cours du temps, l'affichage n'est pas mis à jour, sauf
 * lorsque la carte est sacrifiée, auquel cas ce composant devient invisible. Ce
 * composant inclut la logique de sélection de la carte lors du clic.
 *
 */
public class VueCarteCroyant extends JPanel implements Observer, ThemeFoncé, Vue {

	/**
	 * Constante d'identification pour la sérialisation.
	 */
	public static final long serialVersionUID = 1l;

	/**
	 * Référence vers la carte allié observée par le composant.
	 * 
	 * Une valeur nulle signifie que le composant est dans un état non
	 * utilisable.
	 * 
	 * @see fr.bragabresolin.menhir.Core.Cartes.CarteAllie
	 */
	private Croyant carte;

	/**
	 * Référence vers le jeu en train d'être joué. Cette référence est utilisée
	 * pour obtenir l'accès à des informations inacessibles autrement, comme la
	 * liste des autres joueurs (par exemple pour proposer de choisir une cible
	 * lors du clic sur le composant).
	 * 
	 * Une valeur nulle signifie que le composant est dans un état non
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
	 * Mutateur pour la possibilité que la carte soit cliquée (et jouée) ou non.
	 * 
	 * @param estJouable
	 *            Si la carte doit être jouable ou non
	 */
	public void setEstJouable(boolean estJouable) {
		this.estJouable = estJouable;
	}

	/**
	 * Met à jour l'affichage de la carte. Si la carte suivie a été jouée, elle
	 * devient invisible.
	 * 
	 * @param o
	 *            L'objet observé (la carte)
	 * @param message
	 *            Le message envoyé
	 */
	public void update(Observable o, Object message) {
		if (this.carte.getPosition() == Position.Défausse) {
			this.setVisible(false);
		}
	}

	/**
	 * Constructeur. On construit la vue de la carte en affichant sa matrice des
	 * forces et son nom, ainsi qu'en y attachant des écouteurs d'événement
	 * clic. Un clic sur la carte signifie que le joueur veut jouer cette carte,
	 * auquel cas, si la carte est jouable (par exemple si c'est au tour du
	 * joueur), les informations nécessaires à son exécution sont demandées, et
	 * elle est déposée dans le tampon de communication adapté.
	 *
	 * @param carte
	 *            La carte à observer
	 */
	public VueCarteCroyant(Croyant carte) {
		carte.addObserver(this);
		this.carte = carte;
		this.estJouable = false;

		/*
		 * this.addMouseListener(new MouseAdapter() { public void
		 * mouseReleased(MouseEvent e) { boolean carteEstOK = false;
		 * 
		 * if (VueCarteCroyant.this.estJouable && carte.getPosition() ==
		 * Position.Main && Partie.getInstance().getjEnCours().isEstRéel()) {
		 * carteEstOK = true; if (VueCarteCroyant.this.carte instanceof Croyant)
		 * { carteEstOK = false; JPanel panelJoueurs = new JPanel(); JLabel
		 * labelJoueurs = new JLabel("Veuillez choisir le joueur cible");
		 * labelJoueurs.setForeground(LIGHT_FG); panelJoueurs.add(labelJoueurs,
		 * BorderLayout.NORTH); /*DefaultComboBoxModel modelJoueurs = new
		 * DefaultComboBoxModel(); for (Joueur joueur :
		 * Partie.getInstance().getJoueurs()) { if (joueur instanceof IA) {
		 * modelJoueurs.addElement(joueur); } }
		 * 
		 * /*JComboBox comboBoxJoueurs = new JComboBox(modelJoueurs);
		 * panelJoueurs.add(comboBoxJoueurs, BorderLayout.CENTER);
		 * 
		 * int veutPoser = JOptionPane.showConfirmDialog(null,
		 * "Voulez vous poser le croyant " + carte.getNom() + " au centre ?",
		 * "Attention", JOptionPane.OK_CANCEL_OPTION,
		 * JOptionPane.QUESTION_MESSAGE); switch (veutPoser) { case
		 * JOptionPane.OK_OPTION: if (carte.estJouable(j)) {
		 * MilieuDeTable.getInstance().poserCroyantMilieu(carte); carteEstOK =
		 * true; } else {JOptionPane.showMessageDialog(null,
		 * "Vous n'avez pas assez de points d'action !",
		 * JOptionPane.ERROR_MESSAGE);}; } }
		 * 
		 * if (VueCarteCroyant.this.estJouable && carteEstOK) {
		 * TamponCarte.getInstance().deposerCarte(VueCarteCroyant.this.carte); }
		 * else if (carteEstOK) {
		 * VueCarteCroyant.this.carte.setOrigine(VueCarteCroyant.this.jeu.
		 * getJoueurPhysique());
		 * VueCarteAllie.this.carte.executer(VueCarteAllie.this.jeu.
		 * getMancheEnCours().getSaisonEnCours()); } } else {
		 * JOptionPane.showMessageDialog(null,
		 * "Impossible de jouer cette carte maintenant.",
		 * "Attendez votre tour !", JOptionPane.ERROR_MESSAGE); } } });
		 */

		setBorder(new LineBorder(BORDER_COLOR));
		setBackground(ACCENT_2);
		setLayout(new MigLayout("", "[100%,grow]", "[334px]10[100%-484px,grow]"));

		JLabel label = new JLabel(this.carte instanceof Croyant ? "Taupe géante" : "Chien de garde");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(LIGHT_FG);
		label.setFont(new Font("SansSerif", Font.BOLD, 11));
		add(label, "cell 0 0,alignx center,aligny center");

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(ACCENT_2);
		add(panel, "cell 0 1,grow");
		panel.setLayout(new GridLayout(4, 2, 0, 0));
		JLabel labelCarte = new JLabel("");

		labelCarte.setHorizontalAlignment(SwingConstants.CENTER);
		labelCarte.setIcon(
				new ImageIcon(VueCarteCroyant.class.getResource("resources/images/Croyant" + carte.getNom() + ".png")));
		labelCarte.setForeground(ACCENT_FG);
		panel.add(labelCarte);
	}

	@Override
	public void lancer() {
		// TODO Auto-generated method stub
		
	}
}
