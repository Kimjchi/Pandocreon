package interfaceGraphique;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.border.MatteBorder;

import java.awt.*;
import javax.swing.*;
import cartes.*;
import net.miginfocom.swing.MigLayout;
import partie.Partie;

/**
 * Composant graphique repr�sentant la main du joueur, c'est-�-dire l'ensemble
 * des cartes qu'il a en main. Ce composant est mis � jour lorsque le joueur
 * r�el qu'il suit pioche ou rend des cartes. Ce composant est compos� des vues
 * des cartes du joueur, et c'est lui qui indique � chacune d'entre elle si elle
 * peut �tre utili�e selon les besoins du joueur suivi.
 *
 */
public class VueMainJoueur extends JPanel implements Observer, ThemeFonc� {

	/**
	 * Constante d'identification pour la s�rialisation.
	 */
	public static final long serialVersionUID = 1l;

	/**
	 * R�f�rence vers les vues des cartes ingr�dient du panneau.
	 * 
	 * Cette r�f�rence est n�cessaire afin d'effectuer les actions individuelles
	 * sur chaque vues, comme les rendre jouables/cliquables.
	 */
	private LinkedList<VueCarteAction> listeCartes;

	private DefaultListModel<CarteAction> lesCarte;

	private LinkedList<VueCarte> vuesCarte;
		
	private JLabel labelInfo;
	
	private VuePioche vuePioche = new VuePioche();;
	/**
	 * R�f�rence vers les vues des cartes alli� du panneau.
	 * 
	 * Cette r�f�rence est n�cessaire afin d'effectuer les actions individuelles
	 * sur chaque vues, comme les rendre jouables/cliquables.
	 */
	// private LinkedList<VueCarteGuideSpirituel> listeGuidesSpirituels;

	/**
	 * R�f�rence vers les vues des cartes alli� du panneau.
	 * 
	 * Cette r�f�rence est n�cessaire afin d'effectuer les actions individuelles
	 * sur chaque vues, comme les rendre jouables/cliquables.
	 */
	// private LinkedList<VueCarteDeusEx> listeDeusEx;

	/**
	 * R�f�rence vers les vues des cartes alli� du panneau.
	 * 
	 * Cette r�f�rence est n�cessaire afin d'effectuer les actions individuelles
	 * sur chaque vues, comme les rendre jouables/cliquables.
	 */
	// private LinkedList<VueCarteApocalypse> listeApocalypses;

	/**
	 * Construit la vue de la main du joueur � partir du jeu. On commence avec
	 * un panel vide.
	 *
	 * @param jeu
	 *            Le jeu en cours
	 */
	public VueMainJoueur() {
		
		this.vuesCarte = new LinkedList<VueCarte>();
		Partie.getInstance().getJReel().getMainJoueur().addObserver(this);

		this.setBackground(DARK_BG);
		this.setLayout(new MigLayout("","0[12.5%]0[12.5%]0[12.5%]0[12.5%]0[12.5%]0[12.5%]0[12.5%]0[12.5%]0", "0[100%, grow]0" ));
		this.setBorder(new MatteBorder(1, 0, 0, 0, (Color) BORDER_COLOR));
		
		this.labelInfo = new JLabel("Votre Main");
		this.labelInfo.setToolTipText("Votre Main");
		this.labelInfo.setVerticalAlignment(SwingConstants.TOP);
		this.labelInfo.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.labelInfo.setHorizontalAlignment(SwingConstants.LEFT);
		this.labelInfo.setForeground(LIGHT_FG);
		this.add(labelInfo, "cell 0 0 2 1,alignx left,aligny top");
		
		Iterator<VueCarte> itv = this.vuesCarte.iterator();
		while (itv.hasNext()) {
			itv.next().nettoyer();
		}
		
		this.removeAll();
		
		this.add(labelInfo);
		
		Iterator<CarteAction> it = Partie.getInstance().getJReel().getMainJoueur().getContenuMain().iterator(); 
		
		while (it.hasNext()) {
			Carte carte = it.next();
			VueCarte vueCarte = new VueCarte(carte.getNom(), carte);
			this.vuesCarte.add(vueCarte);
			if (it.hasNext()) {
				vueCarte.setBorder(new MatteBorder(0, 0, 0, 1, (Color) BORDER_COLOR));
			}
			this.add(vueCarte);
			this.add(vuePioche);
	
			//panelCarte.add(vueCarte);
		}
		
		
		this.listeCartes = new LinkedList<VueCarteAction>();
		System.out.println("On est l� !");
	}

	/**
	 * Ajoute la carte fournie au panel de la main, sa position �tant d�termin�e
	 * par le type de la carte.
	 *
	 * @param carte
	 *            La carte � ajouter � la main
	 * @see fr.bragabresolin.menhir.Core.Cartes.Carte
	 * @see fr.bragabresolin.menhir.Core.Cartes.CarteAllie
	 * @see fr.bragabresolin.menhir.Core.Cartes.CarteIngredient
	 */
	private void ajouterCarte(CarteAction carte) {
		lesCarte.add(0,carte);
		//String path = "resources/images/" + carte.getType() + "/" + carte.getNom() + ".png";
		//System.out.println(System.getProperty("java.class.path"));
		

	}

	/**
	 * R�initialise le panel en supprimant toutes les vues de cartes.
	 */
	private void supprimerCartes() {
		this.removeAll();
		this.listeCartes.clear();
		//this.construirelistModel();
	}


	/**
	 * Met � jour l'affichage de la main du joueur.
	 * 
	 * On ajoute/supprime les cartes au fur et � mesure que le joueur
	 * pioche/rend des cartes. On r�agit aux demandes de donn�es du joueur et de
	 * d�but/fin de tour, afin de rendre jouables les cartes du panel
	 * correspondant au besoin du moment.
	 * 
	 * @param o
	 *            L'objet observ� (le joueur)
	 * @param message
	 *            Le message envoy�
	 */
	public void update(Observable o, Object message) {
		
		Iterator<VueCarte> itv = this.vuesCarte.iterator();
		while (itv.hasNext()) {
			itv.next().nettoyer();
		}
		this.removeAll();
		
		this.add(labelInfo);
		
		Iterator<CarteAction> it = Partie.getInstance().getJReel().getMainJoueur().getContenuMain().iterator(); 
		while (it.hasNext()) {
			Carte carte = it.next();
			VueCarte vueCarte = new VueCarte(carte.getNom(), carte);
			this.vuesCarte.add(vueCarte);
			if (it.hasNext()) {
				vueCarte.setBorder(new MatteBorder(0, 0, 0, 1, (Color) BORDER_COLOR));
			}
			this.add(vueCarte);
	
			//panelCarte.add(vueCarte);
		}
		System.out.println("Je suis l'update de vueMain Joueur");
		this.listeCartes = new LinkedList<VueCarteAction>();			
		this.add(vuePioche);
		
		this.repaint();
		this.revalidate();

	}
		/*if (message instanceof Message) {
			Message mes = (Message) message;
			switch (mes.getType()) {
			case JOUEUR_PIOCHE_CARTE:
				CarteAction carte = (CarteAction) mes.getBody();
				this.ajouterCarte(carte);
				break;
			case JOUEUR_DEFAUSSE_CARTE:
				this.supprimerCartes();
				break;
			case JOUEUR_POSE_GUIDE:
				JOptionPane.showMessageDialog(null, "C'est � votre tour. S�lectionnez une carte guide � jouer.",
						"A vous de jouer !", JOptionPane.INFORMATION_MESSAGE);
				Iterator<VueCarteAction> it = this.listeCartes.iterator();
				while (it.hasNext()) {
					it.next().setEstJouable(true);
				}
				break;
			case JOUEUR_POSE_CROYANT:
				if (!Partie.getInstance().getJoueurPhysique().carteAllieDispo()) {
					TamponCarte.getInstance().setIgnorer(true);
					break;
				}

			case JOUEUR_ACTIVE_CROYANT:
				if (!this.jeu.getJoueurPhysique().carteAllieDispo()) {
					TamponCarte.getInstance().setIgnorer(true);
					break;
				}

			case JOUEUR_ACTIVE_DEUSEX:
				if (!this.jeu.getJoueurPhysique().carteAllieDispo()) {
					TamponCarte.getInstance().setIgnorer(true);
					break;
				}

			case JOUEUR_ACTIVE_GUIDE:
				if (!this.jeu.getJoueurPhysique().carteAllieDispo()) {
					TamponCarte.getInstance().setIgnorer(true);
					break;
				}

				int result = JOptionPane.showConfirmDialog(null,
						"Voulez-vous jouer une carte alli� ? Si OK, s�lectionnez-l�.", "A vous de jouer !",
						JOptionPane.YES_NO_OPTION);
				switch (result) {
				case JOptionPane.OK_OPTION:
					Iterator<VueCarteAllie> ital = this.listeCartesAllie.iterator();
					while (ital.hasNext())
						ital.next().setEstJouable(true);
					break;
				default:
					TamponCarte.getInstance().setIgnorer(true);
					break;
				}
				// Absence de break volontaire, on cascade
			case JOUEUR_FIN_TOUR:
				it = this.listeCartes.iterator();
				while (it.hasNext())
					it.next().setEstJouable(false);
				break;
			case JOUEUR_CHOIX_PIOCHER_ALLIE:
				result = JOptionPane.showConfirmDialog(null,
						"Voulez-vous piocher une carte alli� ? Si non, vous obtiendrez 2 graines.",
						"Distribution des cartes", JOptionPane.YES_NO_OPTION);
				switch (result) {
				case JOptionPane.OK_OPTION:
					TamponBooleen.getInstance().deposerBool(true);
					break;
				default:
					TamponBooleen.getInstance().deposerBool(false);
				}
				break;
			default:
				break;
			}
		}*/
	
}
/*
 * Contact GitHub API Training Shop Blog About�2016 GitHub, Inc. Terms Privacy
 * Security Status
 */