package interfaceGraphique;

import interfaceGraphique.ThemeFoncé;
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

public class VuePartie implements Vue, ThemeFoncé, Observer {

	/**
	 * Référence vers la frame principale de la fenêtre.
	 */
	private JFrame frame;

	/**
	 * Référence vers le panneau contenant les vues des joueurs de la partie.
	 */
	private JPanel panelJoueurs;

	/**
	 * Liste contenant toutes les vues des joueurs ajoutées au panneau des
	 * joueurs.
	 * 
	 * Cette référence est nécessaire notamment afin de pouvoir
	 * ajouter/supprimer les observateurs des joueurs avant la re-création de
	 * nouvelles vues de joueurs pour éviter d'avoir des observateurs fantômes.
	 * 
	 * @see interfaceGraphique.VueJoueur
	 */
	private LinkedList<VueJoueur> vuesJoueurs;

	/**
	 * Référence vers le panneau contenant la vues de la main du joueur.
	 * 
	 * @see interfaceGraphique.VueMainJoueur
	 */
	private JPanel panelMain;

	/**
	 * Réference vers le panneau contenant la vue du milieu de la table.
	 */
	private JPanel panelMilieuTable;

	private JPanel cosmogonie;
	/**
	 * Référence vers le panneau contenant les informations de suivi des actions
	 * de la partie.
	 *
	 * TODO: pas besoin de le garder en attribut
	 */
	private JPanel panelSuiviEffets;

	/**
	 * Label affiché contenant les dernières actions effectuées par les joueurs.
	 */
	private JLabel lblSuiviEffets;

	/**
	 * Label affiché contenant le nom du joueur en train de jouer.
	 */
	private JLabel lblNomJoueurEnCours;

	/**
	 * Label affiché contenant les informations générales de la partie
	 */
	private JLabel lblInformations;

	private JPanel vuePhaseDeJeu;

	/**
	 * Label affiché contenant le numéro du tour en cours.
	 */
	private JLabel lblNumTour;

	/**
	 * Représente le numéro de manche actuellement jouée.
	 * 
	 * TODO: à déprécier ; devrait être gardé dans le jeu
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
		VueResultatDé resDé = new VueResultatDé();
		this.cosmogonie.add(resDé);
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
		this.lblSuiviEffets = new JLabel("En attente d'actions à afficher");
		this.lblSuiviEffets.setForeground(ACCENT_FG);
		this.lblSuiviEffets.setFont(DEFAULT_FONT);
		this.lblSuiviEffets.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblSuiviEffets.setBorder(new MatteBorder(4, 0, 0, 0, ACCENT_2)); // padding
		this.panelSuiviEffets.add(this.lblSuiviEffets);

		this.panelMilieuTable = new VueMilieuTable();
		frame.getContentPane().add(this.panelMilieuTable, "cell 0 3, grow");
		// this.panelPioche = new VuePioche();
		// frame.getContentPane().add(this.panelPioche, "cell 0 3");

		this.panelMain = new VueMainJoueur(); // bloque ici frère
		frame.getContentPane().add(this.panelMain, "cell 0 4, grow");

		// On surveille le jeu afin de savoir quand démarrer/finir l'affichage
		// selon le début/fin de partie.
		/*
		 * Partie.getInstance().addObserver(new Observer() { public void
		 * update(Observable o, Object message) { if (message == null) { switch
		 * (((Message) message).getType()) { case DEBUT_PARTIE:
		 * VuePartie.this.initTopPanel(); break; case FIN_PARTIE:
		 * VuePartie.this.afficherClassement(); break; default: break; } }} });
		 */

		// On surveille les cartes ingrédient pour savoir quand elles sont
		// exécutées afin d'afficher leur effet dans le panneau d'informations
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

		// On surveille les joueurs afin de mettre à jour le panneau
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
	 * Cette méthode supprime les informations inutiles en fin de partie, et
	 * affiche les joueurs dans l'ordre de fin de partie fourni par le jeu. Un
	 * bouton permettant de Rejouer est inséré. Il permet, au clic, de relancer
	 * une nouvelle partie avec de nouveaux réglages.
	 */
	private void afficherClassement() {
		this.panelMain.removeAll();
		this.panelMain.setLayout(new BorderLayout());

		this.lblNumTour.setText("");
		this.lblNomJoueurEnCours.setText("");
		this.lblInformations.setText("Partie terminée !");
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
	 * Initialise l'affichage de la barre d'informations (haut de la fenêtre).
	 * 
	 * Cette méthode est appelée lorsque la partie a été initialisée et démarrée
	 * car on a besoin de récupérer les informations de la manche.
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
	 * Cette méthode est appelée une fois que le jeu a terminé d'initialiser les
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
	 * Demande à l'utilisateur de confirmer le démarrage du jeu. Choisir
	 * "Annuler" ferme le jeu.
	 */
	private void confirmerDemarrage() {
		String texte = "Bienvenue dans Pandocreon Divinae !" + "\n" + "La partie va démarrer !";
		int result = JOptionPane.showConfirmDialog(this.frame, texte, "Bienvenue - Pandocreon Divinae",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (result != 0) {
			System.exit(0);
		} else {
			Partie.getInstance().setChoixInterface(1);
		}
	}

	/**
	 * Demande à l'utilisateur son nom.
	 * 
	 * Tant que le résultat saisi est vide, la question est re-posée. Choisir
	 * "Annuler" ferme le jeu.
	 * 
	 * @return La chaîne lue
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
	 * Demande à l'utilisateur s'il veut jouer en partie avancée ou simple.
	 * 
	 * @return Vrai si l'utilisateur veut jouer en mode avancé
	 */
	private int demanderModePartie() {
		String texte = "En quel mode de difficulté voulez-vous jouer ?";
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
	 * Demande à l'utilisateur son âge.
	 * 
	 * Tant que le résultat est incorrect, la question est re-posée. Choisir
	 * "Annuler" ferme le jeu. Si l'âge donné est en dehors des bornes
	 * autorisées par les règles, le jeu se ferme avec une fenêtre d'erreur.
	 * 
	 * @return L'entier lu de l'âge
	 */
	private int demanderAge() {
		int age = 0;
		while (age < 1) {
			String reponse = (String) JOptionPane.showInputDialog(this.frame, "Quel âge avez-vous ?",
					"Choix de l'âge - Pandocreon", JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (reponse == null)
				System.exit(0);
			try {
				age = Integer.parseInt(reponse, 10);
			} catch (NumberFormatException e) {
				age = 0;
			}
		}
		if (age < 8) {
			JOptionPane.showMessageDialog(this.frame, "Vous êtes trop jeune pour jouer (- de 8 ans) !",
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
	 * Demande à l'utilisateur le nombre de joueurs virtuels avec qui il veut
	 * jouer.
	 * 
	 * Tant que le résultat est incorrect, la question est re-posée. Choisir
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
	 * Demande à l'utilisateur le nombre de joueurs virtuels avec qui il veut
	 * jouer.
	 * 
	 * Tant que le résultat est incorrect, la question est re-posée. Choisir
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
	 * Démarre le jeu en posant toutes les questions de configuration à
	 * l'utilisateur pour initialiser l'interface graphique et la partie.
	 * 3
	 * On lance le jeu du Menhir dans un thread séparé.
	 * 
	 * @see fr.bragabresolin.menhir.Core.JeuMenhirThread
	 */
	private void demarrerJeu() {
		demanderJouerConsole();
		int nombreJoueurs = 0;
		int difficulté = 0;
		ArrayList<String> nomDesJoueurs = null;

		if (Partie.getInstance().isEnInterfaceGraphique()) {
			nomDesJoueurs = new ArrayList<String>();
			nomDesJoueurs.add(this.demanderNom());
			// this.demanderAge();
			nombreJoueurs = this.demanderNombreJoueurs();
			nomDesJoueurs.addAll(this.demanderNomJoueurs(nombreJoueurs));
			difficulté = this.demanderModePartie();
		}	

		try {
			Partie.getInstance().setEstEnCours(Partie.getInstance().demarrerPartie());
		} catch (DemarrerPartieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Partie.getInstance().InitialiserPartie(nombreJoueurs, difficulté, nomDesJoueurs);
		this.initialize();
		Thread playWave = new AePlayWave("src/resources/heure du duel yu gi oh.wav");
		playWave.start();
		
		while (Partie.getInstance().getEstEnCours()) {
			Partie.getInstance().tourComplet(Partie.getInstance().getJoueurs(), Partie.getInstance());
		}
		Partie.getInstance().terminerPartie();
	}

	/**
	 * Affiche le splashscreen (écran de garde) du jeu du Menhir.
	 * 
	 * On enlève tout contenu déjà présent dans la fenêtre, afin d'afficher
	 * uniquement l'image de garde du jeu, au centre de la fenêtre. La taille
	 * précédente n'est pas changée.
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
	 * On affiche le splashscreen, puis on démarre.
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
