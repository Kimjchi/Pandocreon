package interfaceGraphique;

import interfaceGraphique.ThemeFonc�;
import interfaceGraphique.buttons.ButtonAvancerTour;
import joueurs.Joueur;
import message.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import partie.Partie;
import resources.AePlayWave;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import cartes.CarteAction;
import exception.DemarrerPartieException;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class VuePartie implements Vue, ThemeFonc�, Observer {

	/**
	 * R�f�rence vers la frame principale de la fen�tre.
	 */
	private JFrame frame;

	/**
	 * R�f�rence vers le panneau contenant les vues des joueurs de la partie.
	 */
	private JPanel panelJoueurs;

	/**
	 * Liste contenant toutes les vues des joueurs ajout�es au panneau des
	 * joueurs.
	 * 
	 * Cette r�f�rence est n�cessaire notamment afin de pouvoir
	 * ajouter/supprimer les observateurs des joueurs avant la re-cr�ation de
	 * nouvelles vues de joueurs pour �viter d'avoir des observateurs fant�mes.
	 * 
	 * @see interfaceGraphique.VueJoueur
	 */
	private LinkedList<VueJoueur> vuesJoueurs;

	/**
	 * R�f�rence vers le panneau contenant la vues de la main du joueur.
	 * 
	 * @see interfaceGraphique.VueMainJoueur
	 */
	private JPanel panelMain;

	/**
	 * R�ference vers le panneau contenant la vue du milieu de la table.
	 */
	private JPanel panelMilieuTable;

	private JPanel cosmogonie;
	/**
	 * R�f�rence vers le panneau contenant les informations de suivi des actions
	 * de la partie.
	 *
	 * TODO: pas besoin de le garder en attribut
	 */
	private JPanel panelSuiviEffets;

	/**
	 * Label affich� contenant les derni�res actions effectu�es par les joueurs.
	 */
	private JLabel lblSuiviEffets;

	/**
	 * Label affich� contenant le nom du joueur en train de jouer.
	 */
	private JLabel lblNomJoueurEnCours;

	/**
	 * Label affich� contenant les informations g�n�rales de la partie
	 */
	private JLabel lblInformations;

	private JPanel vuePhaseDeJeu;

	/**
	 * Label affich� contenant le num�ro du tour en cours.
	 */
	private JLabel lblNumTour;

	/**
	 * Repr�sente le num�ro de manche actuellement jou�e.
	 * 
	 * TODO: � d�pr�cier ; devrait �tre gard� dans le jeu
	 */
	private int TourActuel;

	public VuePartie() {
		Thread playWave2 = new AePlayWave("src/resources/Hearthstone.wav");
		playWave2.start();
		this.vuesJoueurs = new LinkedList<VueJoueur>();

		frame = new JFrame();
		frame.setIconImage(frame.getToolkit()
				.getImage(getClass().getClassLoader().getResource("resources/images/pandocreon-entrelac-0.8.0.png")));
		frame.setTitle("Pandocreon Divinae - Bois & Kim");
		frame.setResizable(true);
		frame.getContentPane().setBackground(DARK_BG);
		frame.setBackground(DARK_BG);
		// frame.setBounds(0, 0, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);

		UIManager.put("OptionPane.background", DARK_BG);
		UIManager.put("Panel.background", DARK_BG);
		UIManager.put("OptionPane.messageForeground", LIGHT_FG);
	}

	private void initialize() {
		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(new MigLayout("", "0[100%,grow]0", "0[15%]0[5%]0[10%]0[35%]0[30%]0"));

		this.TourActuel = 0;
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, BORDER_COLOR));
		panel.setBackground(ACCENT_1);
		frame.getContentPane().add(panel, "cell 0 2,growx,aligny top");
		panel.setLayout(new MigLayout("", "20[10%]10[80%]10[10%]20", "[100%]"));

		this.lblNomJoueurEnCours = new JLabel("Joueur".toUpperCase());
		this.lblNomJoueurEnCours.setToolTipText("Joueur en cours");
		this.lblNomJoueurEnCours.setFont(TITLE_FONT);
		this.lblNomJoueurEnCours.setForeground(LIGHT_FG);
		panel.add(this.lblNomJoueurEnCours, "cell 0 0,alignx left,growy");

		/*
		 * this.lblInformations = new JLabel("<...>");
		 * this.lblInformations.setFont(UIManager.getFont("Table.font"));
		 * this.lblInformations.setForeground(LIGHT_FG);
		 * panel.add(this.lblInformations,
		 * "cell 1 0, alignx center,aligny center");
		 */

		this.vuePhaseDeJeu = new VuePhaseDeJeu();
		panel.add(this.vuePhaseDeJeu, "cell 1 0, alignx center,aligny center");

		ButtonAvancerTour buttonChangerEtat = new ButtonAvancerTour();

		buttonChangerEtat.setPreferredSize(new Dimension(200, 70));
		buttonChangerEtat.setMinimumSize(new Dimension(200, 70));
		buttonChangerEtat.setMaximumSize(new Dimension(200, 70));
		panel.add(buttonChangerEtat, "cell 2 0, grow, alignx center,aligny"); // alignx
																				// center,aligny
		// center, grow");

		this.lblNumTour = new JLabel("TOUR x");
		this.lblNumTour.setToolTipText("Tour en cours");
		this.lblNumTour.setForeground(LIGHT_FG);
		this.lblNumTour.setFont(TITLE_FONT);
		panel.add(this.lblNumTour, "cell 3 0,alignx right,aligny center");

		this.cosmogonie = new JPanel();
		VueResultatD� resD� = new VueResultatD�();
		this.cosmogonie.add(resD�);
		panel.add(this.cosmogonie);

		this.panelJoueurs = new JPanel();
		this.panelJoueurs.setBackground(DARK_BG);
		frame.getContentPane().add(this.panelJoueurs, "cell 0 0,grow");
		this.panelJoueurs.setLayout(new GridLayout(1, 6, 0, 0));

		this.remplirPanelJoueurs();

		this.panelSuiviEffets = new JPanel();
		this.panelSuiviEffets.setBackground(ACCENT_2);
		this.panelSuiviEffets.setBorder(new MatteBorder(0, 0, 1, 0, BORDER_COLOR));
		frame.getContentPane().add(this.panelSuiviEffets, "cell 0 1,grow");
		this.lblSuiviEffets = new JLabel("En attente d'actions � afficher");
		this.lblSuiviEffets.setForeground(ACCENT_FG);
		this.lblSuiviEffets.setFont(DEFAULT_FONT);
		this.lblSuiviEffets.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblSuiviEffets.setBorder(new MatteBorder(4, 0, 0, 0, ACCENT_2)); // padding
		this.panelSuiviEffets.add(this.lblSuiviEffets);

		this.panelMilieuTable = new VueMilieuTable();
		frame.getContentPane().add(this.panelMilieuTable, "cell 0 3, grow");
		// this.panelPioche = new VuePioche();
		// frame.getContentPane().add(this.panelPioche, "cell 0 3");

		this.panelMain = new VueMainJoueur(); // bloque ici fr�re
		frame.getContentPane().add(this.panelMain, "cell 0 4, grow");

		// On surveille le jeu afin de savoir quand d�marrer/finir l'affichage
		// selon le d�but/fin de partie.
		/*
		 * Partie.getInstance().addObserver(new Observer() { public void
		 * update(Observable o, Object message) { if (message == null) { switch
		 * (((Message) message).getType()) { case DEBUT_PARTIE:
		 * VuePartie.this.initTopPanel(); break; case FIN_PARTIE:
		 * VuePartie.this.afficherClassement(); break; default: break; } }} });
		 */

		// On surveille les cartes ingr�dient pour savoir quand elles sont
		// ex�cut�es afin d'afficher leur effet dans le panneau d'informations
		Iterator<CarteAction> itc = Partie.getInstance().getPiocheCA().getDeck().iterator();
		while (itc.hasNext()) {
			CarteAction carte = itc.next();
			carte.addObserver(new Observer() {
				public void update(Observable o, Object message) {
					if (message instanceof Message) {
						Message mes = (Message) message;
						switch (mes.getType()) {
						case CARTE_EXEC:
							CarteAction c = (CarteAction) o;
							VuePartie.this.lblSuiviEffets.setText(c.getEffet());

						default:
							break;
						}
					}
				}
			});
		}

		// On surveille les joueurs afin de mettre � jour le panneau
		// d'informations lorsqu'un joueur doit jouer
		Iterator<Joueur> itj = Partie.getInstance().getJoueurs().iterator();
		while (itj.hasNext()) {
			itj.next().addObserver(new Observer() {
				public void update(Observable o, Object message) {
					if (message instanceof Message) {
						switch (((Message) message).getType()) {
						case JOUEUR_DEBUT_TOUR:
							Joueur j = (Joueur) o;
							VuePartie.this.lblInformations.setText("Tour de " + j.getNom());
							break;
						default:
							break;
						}
					}
				}
			});
		}

		this.frame.pack();
	}

	/**
	 * Affiche le classement des joueurs en fin de partie.
	 * 
	 * Cette m�thode supprime les informations inutiles en fin de partie, et
	 * affiche les joueurs dans l'ordre de fin de partie fourni par le jeu. Un
	 * bouton permettant de Rejouer est ins�r�. Il permet, au clic, de relancer
	 * une nouvelle partie avec de nouveaux r�glages.
	 */
	private void afficherClassement() {
		this.panelMain.removeAll();
		this.panelMain.setLayout(new BorderLayout());

		this.lblNumTour.setText("");
		this.lblNomJoueurEnCours.setText("");
		this.lblInformations.setText("Partie termin�e !");
		this.lblSuiviEffets.setText("Voici le classement :");

		this.remplirPanelJoueurs();

		JButton boutonRejouer = new JButton("REJOUER");
		boutonRejouer.setBackground(DARK_BG);
		boutonRejouer.setForeground(LIGHT_FG);
		boutonRejouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VuePartie.this.demarrerJeu();
			}
		});
		this.panelMain.add(boutonRejouer, BorderLayout.CENTER);

		this.frame.pack();
	}

	/**
	 * Initialise l'affichage de la barre d'informations (haut de la fen�tre).
	 * 
	 * Cette m�thode est appel�e lorsque la partie a �t� initialis�e et d�marr�e
	 * car on a besoin de r�cup�rer les informations de la manche.
	 */
	private void initTopPanel() {
		lblNomJoueurEnCours.setText(Partie.getInstance().getjEnCours().getNom());
		Partie.getInstance().getjEnCours().addObserver(new Observer() {
			public void update(Observable o, Object message) {
				Message mes = (Message) message;
				switch (mes.getType()) {
				case DEBUT_TOUR_JOUEUR:
					lblNomJoueurEnCours.setText(Partie.getInstance().getjEnCours().getNom());
					lblInformations.setText("Changement de joueur !");
					break;
				case DEBUT_TOUR:
					remplirPanelJoueurs();
					TourActuel++;
					lblNumTour.setText("Manche " + TourActuel);
					lblInformations.setText("Changement de tour !");
				default:
				}
			}
		});
	}

	/**
	 * Remplit le panel des joueurs avec les joueurs de la partie.
	 * 
	 * Cette m�thode est appel�e une fois que le jeu a termin� d'initialiser les
	 * joueurs. On ajoute successivement, dans l'ordre fourni par le jeu, les
	 * joueurs au panel.
	 * 
	 */
	private void remplirPanelJoueurs() {
		Iterator<VueJoueur> itv = this.vuesJoueurs.iterator();
		while (itv.hasNext()) {
			itv.next().nettoyer();
		}
		this.panelJoueurs.removeAll();

		Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			VueJoueur vueJoueur = new VueJoueur(j);
			this.vuesJoueurs.add(vueJoueur);
			if (it.hasNext()) {
				// vueJoueur.setBorder(new MatteBorder(0, 0, 0, 1, (Color)
				// BORDER_COLOR));
			}
			this.panelJoueurs.add(vueJoueur);
		}
	}

	/**
	 * Demande � l'utilisateur de confirmer le d�marrage du jeu. Choisir
	 * "Annuler" ferme le jeu.
	 */
	private void confirmerDemarrage() {
		String texte = "Bienvenue dans Pandocreon Divinae !" + "\n" + "La partie va d�marrer !";
		int result = JOptionPane.showConfirmDialog(this.frame, texte, "Bienvenue - Pandocreon Divinae",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (result != 0) {
			System.exit(0);
		} else {
			Partie.getInstance().setChoixInterface(1);
		}
	}

	/**
	 * Demande � l'utilisateur son nom.
	 * 
	 * Tant que le r�sultat saisi est vide, la question est re-pos�e. Choisir
	 * "Annuler" ferme le jeu.
	 * 
	 * @return La cha�ne lue
	 */
	private String demanderNom() {
		String nomJoueur = null;
		while (nomJoueur == null || (nomJoueur != null && nomJoueur.equals(""))) {
			nomJoueur = (String) JOptionPane.showInputDialog(this.frame, "Quel est votre nom ?",
					"Choix du nom - Pandocreon", JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (nomJoueur == null)
				System.exit(0);
		}
		return nomJoueur;
	}

	/**
	 * Demande � l'utilisateur s'il veut jouer en partie avanc�e ou simple.
	 * 
	 * @return Vrai si l'utilisateur veut jouer en mode avanc�
	 */
	private int demanderModePartie() {
		String texte = "En quel mode de difficult� voulez-vous jouer ?";
		String[] possibleValues = { "Facile", "Moyen" };
		String selectedValue = (String) JOptionPane.showInputDialog(this.frame, texte,
				"Choix du mode de jeu - Pandocreon", JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
				possibleValues[0]);
		switch (selectedValue) {
		case ("Facile"):
			return 1;
		case ("Moyen"):
			return 2;
		default:
			return 2;
		}
		/*
		 * int result = JOptionPane.showConfirmDialog(this.frame, texte,
		 * "Choix du mode de jeu - Pandocreon", JOptionPane.YES_NO_OPTION,
		 * JOptionPane.PLAIN_MESSAGE);
		 */
	}

	/**
	 * Demande � l'utilisateur son �ge.
	 * 
	 * Tant que le r�sultat est incorrect, la question est re-pos�e. Choisir
	 * "Annuler" ferme le jeu. Si l'�ge donn� est en dehors des bornes
	 * autoris�es par les r�gles, le jeu se ferme avec une fen�tre d'erreur.
	 * 
	 * @return L'entier lu de l'�ge
	 */
	private int demanderAge() {
		int age = 0;
		while (age < 1) {
			String reponse = (String) JOptionPane.showInputDialog(this.frame, "Quel �ge avez-vous ?",
					"Choix de l'�ge - Pandocreon", JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (reponse == null)
				System.exit(0);
			try {
				age = Integer.parseInt(reponse, 10);
			} catch (NumberFormatException e) {
				age = 0;
			}
		}
		if (age < 8) {
			JOptionPane.showMessageDialog(this.frame, "Vous �tes trop jeune pour jouer (- de 8 ans) !",
					"Joueur trop jeune", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return age;
	}

	private void demanderJouerConsole() {

		int reponse = (int) JOptionPane.showConfirmDialog(this.frame, "Voulez-vous activer l'interface graphique ?",
				"Choix de l'interface - Pandocreon", JOptionPane.YES_NO_OPTION);
		if (reponse == JOptionPane.YES_OPTION) {
			Partie.getInstance().setInterfaceGraphique(true);
		} else if (reponse == JOptionPane.NO_OPTION) {
			Partie.getInstance().setInterfaceGraphique(false);
			frame.dispose();
		}

	}

	/**
	 * Demande � l'utilisateur le nombre de joueurs virtuels avec qui il veut
	 * jouer.
	 * 
	 * Tant que le r�sultat est incorrect, la question est re-pos�e. Choisir
	 * "Annuler" ferme le jeu.
	 * 
	 * @return L'entier lu
	 */
	private int demanderNombreJoueurs() {
		int nombreJoueurs = 0;
		String[] possibleValues = { "1", "2", "3", "4", "5" };
		String texte = "Avec combien de joueurs voulez-vous jouer ?";
		String choix = (String) JOptionPane.showInputDialog(this.frame, texte,
				"Choix du nombre de joueurs - Pandocreon", JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
				possibleValues[0]);
		nombreJoueurs = Integer.parseInt(choix);
		return nombreJoueurs;

	}

	/**
	 * Demande � l'utilisateur le nombre de joueurs virtuels avec qui il veut
	 * jouer.
	 * 
	 * Tant que le r�sultat est incorrect, la question est re-pos�e. Choisir
	 * "Annuler" ferme le jeu.
	 * 
	 * @return L'entier lu
	 */
	private ArrayList<String> demanderNomJoueurs(int nbJoueursIA) {
		ArrayList<String> listeNomJoueurs = new ArrayList<String>();
		for (int i = 1; i <= nbJoueursIA; i++) {
			String reponse = (String) JOptionPane.showInputDialog(this.frame,
					"Comment s'appelle le joueur " + (i + 1) + " ?" + "\n" + "",
					"Choix du nom des joueurs - Pandocreon", JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (reponse == null)
				System.exit(0);
			try {
				listeNomJoueurs.add(reponse);
			} catch (NumberFormatException e) {
				listeNomJoueurs.add("default");
			}
		}
		return listeNomJoueurs;
	}

	/**
	 * D�marre le jeu en posant toutes les questions de configuration �
	 * l'utilisateur pour initialiser l'interface graphique et la partie.
	 * 3
	 * On lance le jeu du Menhir dans un thread s�par�.
	 * 
	 * @see fr.bragabresolin.menhir.Core.JeuMenhirThread
	 */
	private void demarrerJeu() {
		demanderJouerConsole();
		int nombreJoueurs = 0;
		int difficult� = 0;
		ArrayList<String> nomDesJoueurs = null;

		if (Partie.getInstance().isEnInterfaceGraphique()) {
			nomDesJoueurs = new ArrayList<String>();
			nomDesJoueurs.add(this.demanderNom());
			// this.demanderAge();
			nombreJoueurs = this.demanderNombreJoueurs();
			nomDesJoueurs.addAll(this.demanderNomJoueurs(nombreJoueurs));
			difficult� = this.demanderModePartie();
		}	

		try {
			Partie.getInstance().setEstEnCours(Partie.getInstance().demarrerPartie());
		} catch (DemarrerPartieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Partie.getInstance().InitialiserPartie(nombreJoueurs, difficult�, nomDesJoueurs);
		this.initialize();
		Thread playWave = new AePlayWave("src/resources/heure du duel yu gi oh.wav");
		playWave.start();
		
		while (Partie.getInstance().getEstEnCours()) {
			Partie.getInstance().tourComplet(Partie.getInstance().getJoueurs(), Partie.getInstance());
		}
		Partie.getInstance().terminerPartie();
	}

	/**
	 * Affiche le splashscreen (�cran de garde) du jeu du Menhir.
	 * 
	 * On enl�ve tout contenu d�j� pr�sent dans la fen�tre, afin d'afficher
	 * uniquement l'image de garde du jeu, au centre de la fen�tre. La taille
	 * pr�c�dente n'est pas chang�e.
	 */
	private void afficherSplashScreen() {
		frame.getContentPane().removeAll();
		JLabel splash = new JLabel("");
		splash.setIcon(new ImageIcon(VuePartie.class.getClassLoader().getResource("resources/images/splash.png")));
		frame.getContentPane().add(splash, BorderLayout.CENTER);
	}

	/**
	 * Lance et affiche l'interface graphique du jeu.
	 * 
	 * On affiche le splashscreen, puis on d�marre.
	 */
	public void lancer() {
		this.afficherSplashScreen();
		this.frame.setVisible(true);

		this.confirmerDemarrage();
		this.demarrerJeu();

	}

	public static void main(String[] args) {
		VuePartie vue = new VuePartie();

		vue.lancer();

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
