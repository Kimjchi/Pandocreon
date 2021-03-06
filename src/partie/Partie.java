package partie;

import message.*;
import java.util.*;

import javax.swing.JOptionPane;

import cartes.*;
import exception.DemarrerPartieException;
import joueurs.*;
import message.Message;

public class Partie extends Observable {

	private static Partie instance;

	private int nombreJoueurs;
	private int numTour;
	private int apocalypseDejaJouee;
	private int choixInterface;
	private int resultatDe;

	private Joueur jEnCours;
	private Joueur jIntervenant;
	private Joueur JReel;

	private CarteAction carteActivee;

	private boolean estEnCours;
	private boolean estEnInterfaceGraphique;

	private Pioche<CarteAction> piocheCA;
	private ArrayList<Joueur> joueurs;
	private PileDivinit�s<Divinit�> pileDiv;

	private PhaseDeJeu phaseDeJeu;

	// Construit une partie
	private Partie() {
		this.numTour = 1;
		this.choixInterface = 0;
	}

	public void InitialiserPartie(int numVirtuels, int diff, ArrayList<String> nomJoueur) {
		int numV = numVirtuels;

		this.apocalypseDejaJouee = 2;

		this.nombreJoueurs = 1 + numV;
		Defausse.getInstance();
		this.piocheCA = new Pioche<CarteAction>();
		this.piocheCA.ajouterCartes(ConstructeurDeCartes.construireDeck()); // On
																			// ajoute
																			// les
																			// cartes
																			// dans
																			// cette
																			// pioche
																			// en
																			// appelant
																			// la
																			// m�thode
																			// de
																			// construction
																			// des
																			// cartes
																			// du
																			// Deck
		this.piocheCA.remettreCartesEtm�langer(); // On m�lange les cartes
													// de la
													// pioche

		MilieuDeTable.getInstance();

		this.pileDiv = new PileDivinit�s<Divinit�>(); // On cr�e une pile
														// pour
														// les divinit�s
		this.pileDiv.ajouterCartes(ConstructeurDeCartes.ConstruireDivinites()); // On
																				// y
																				// ajoute
		this.phaseDeJeu = PhaseDeJeu.PhaseD�fausse; // les
		// divinit�s
		this.pileDiv.remettreCartesEtm�langer(); // On m�lange

		System.out.println("Vous �tes le joueur n�1");
		joueurs = new ArrayList<Joueur>();
		for (int i = 0; i < 1; i++) {
			this.joueurs.add(new Joueur(i, this.piocheCA, nomJoueur.get(i)));
			joueurs.get(i).setDivinit�(this.pileDiv.piocherCarte());
			// On
			// l'associe
			// �
			// une
			// divinit�

			System.out.println(joueurs.get(i));
			System.out.println("Sa carte divinit� est : ");
			System.out.println(joueurs.get(i).getDivinit�());
			System.out.println(
					"----------------------------------------------------------------------------------------------");
		}

		for (int i = 1; i < this.nombreJoueurs; i++) {
			joueurs.add(new IA(i, this.piocheCA, nomJoueur.get(i), diff));
			joueurs.get(i).setDivinit�(this.pileDiv.piocherCarte());
			// On
			// l'associe
			// �
			// une
			// divinit�

			System.out.println(joueurs.get(i));
			System.out.println("Sa carte divinit� est : ");
			System.out.println(joueurs.get(i).getDivinit�());
			System.out.println(
					"----------------------------------------------------------------------------------------------");
		}

		jEnCours = joueurs.get(0);
	}

	public static Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
		}
		return instance;
	}

	public String toString() {
		return ("La partie a " + this.nombreJoueurs + " joueurs, nous sommes au " + this.numTour
				+ " tour et estEnCours = " + this.estEnCours);
	}

	// Permet de d�marrer la partie de jeu
	public boolean demarrerPartie() throws DemarrerPartieException {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"~~~ Bienvenue dans Pandocreon Divinae ! ~~~\n\nVoulez-vous commencer une partie ? 1=oui/0=non"); // Essayer
																													// de
																													// changer
																													// avec
																													// Y/N
																													// plus

		if (this.estEnInterfaceGraphique || (sc.nextInt()) == 1) {
			return true;
		} else if (!this.estEnInterfaceGraphique) {

			try {
				this.estEnCours = this.demarrerPartie();
			} catch (DemarrerPartieException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int numVirtuels = this.demanderNbJVirtuels();
			int diff = this.demanderDifficult�();
			ArrayList<String> nomDesJoueurs = this.demanderNomJoueurs(numVirtuels + 1);
			this.InitialiserPartie(numVirtuels, diff, nomDesJoueurs);
			while (this.estEnCours) {
				this.tourComplet(this.joueurs, this);
			}
			this.terminerPartie();
			return false;
		} else {
			throw new DemarrerPartieException();
		}
	}

	// termine la partie de jeu
	public void terminerPartie() {
		this.estEnCours = false;
		System.out.println("Merci d'avoir jou� !");
		System.exit(0);
	}

	public int lancerDe() {
		int resultat = (int) (1 + 3 * Math.random());
		switch (resultat) {
		case 1:
			System.out.println("Le r�sultat du d� est Jour");
			this.notifyObservers(new Message(MessageType.RESULTAT_JOUR));
			this.r�sultatD� = 1;
			break;
		case 2:
			System.out.println("Le r�sultat du d� est Nuit");
			this.notifyObservers(new Message(MessageType.RESULTAT_NUIT));
			this.r�sultatD� = 2;
			break;
		case 3:
			System.out.println("Le r�sultat du d� est N�ant");
			this.notifyObservers(new Message(MessageType.RESULTAT_NEANT));
			this.r�sultatD� = 3;
			break;
		}
		this.setChanged();
		this.notifyObservers(Partie.getInstance().getR�sultatD�());
		return resultat;
	}

	// Lance le d� et attribue les points d'action en fonction du r�sultat et de
	// l'origine de la divinit� de chaque joueur.
	public void D�buterTour() {
		int resultat = lancerDe();
		for (Iterator<Joueur> it = joueurs.iterator(); it.hasNext();) {
			Joueur joueur = it.next();
			joueur.gagnerPointsAction(resultat);
		}

	}

	public int demanderNbJVirtuels() {
		int numV = 0;
		if (!this.estEnInterfaceGraphique) {
			numV = 0;
			if (this.estEnCours) {
				boolean choixJ = true;

				while (choixJ) {
					Scanner sc = new Scanner(System.in);
					System.out.println(
							"Combien de joueurs virtuels voulez-vous ? Le nombre de joueurs r�els et virtuels max est de 6");
					numV = sc.nextInt();
					if ((numV <= 5) && (numV >= 1)) {
						choixJ = false;
					}
				}
			}
			return numV;
		}
		return numV;
	}

	public ArrayList<String> demanderNomJoueurs(int nbJoueurs) {
		ArrayList<String> NomJoueurs = new ArrayList<String>();
		if (!this.estEnInterfaceGraphique) {
			for (int i = 0; i < nbJoueurs; i++) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Comment s'appelle le joueur n�" + (i + 1));
				NomJoueurs.add(sc.nextLine());
			}
		}
		return NomJoueurs;
	}

	public int demanderDifficult�() {
		int diff = 0;
		if (!this.isEnInterfaceGraphique()) {
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Difficult� ? [1] Facile [2] Moyen");
			diff = sc2.nextInt();
			return diff;
		}

		return diff;
	}

	public void InterfaceJouer() {
		System.out.println("[1] Poser des croyants");
		System.out.println("[2] Guider des croyants");
		System.out.println("[3] Regarder votre main");
		System.out.println("[4] Consulter vos points");
		System.out.println("[5] Regarder le milieu");
		System.out.println("[6] Afficher les cartes ramen�es");
		System.out.println("[7] Afficher les cartes ramen�es des autres");
		System.out.println("[8] Afficher votre divinit�");
		System.out.println("[9] Activer une apocalypse");
		System.out.println("[10] Sacrifier un croyant");
		System.out.println("[11] Sacrifier un guide");
		System.out.println("[12] Utiliser un DeusEx");
		System.out.println("[13] Utiliser la capacit� de votre divinit�");
		System.out.println("[0] Finir votre tour");
	}

	public void tourComplet(ArrayList<Joueur> joueurs, Partie p0) {
		// System.out.println(p0);
		p0.D�buterTour();
		System.out.println("Tour num�ro : " + this.numTour);
		for (Iterator<Joueur> it = joueurs.iterator(); it.hasNext();) {
			jEnCours = it.next();

			if (jEnCours.isEstR�el()) {
				Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseD�fausse);
				System.out.println("Jencours reel");
			} else {
				Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseAdversaire);
				System.out.println("Jencourspas reeel");
			}

			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println(jEnCours.getNom() + " a " + jEnCours.getPointsJour() + " points jour, "
					+ jEnCours.getPointsNuit() + " points nuit, " + jEnCours.getPointsNeant() + " points n�ant");
			System.out.println(jEnCours.getNom() + " a " + jEnCours.getnbPri�res() + " pri�res.");
			int choice = 0;
			boolean tour = true;
			System.out.println(jEnCours.getNom() + " joue !");

			while (tour) {

				if (this.phaseDeJeu == PhaseDeJeu.PhaseD�fausse || this.phaseDeJeu == PhaseDeJeu.PhaseAdversaire) {
					jEnCours.voulezVousDefausser();
					System.out.println("phase d�fausse");


				}

				if (this.phaseDeJeu == PhaseDeJeu.PhasePioche || this.phaseDeJeu == PhaseDeJeu.PhaseAdversaire) {
					jEnCours.voulezVousPiocher();
					System.out.println("phase pioche");
					if (jEnCours == JR�el) {
						Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhaseJeu);
					}

				}

				if (this.phaseDeJeu == PhaseDeJeu.PhaseJeu || this.phaseDeJeu == PhaseDeJeu.PhaseAdversaire) {
					while (tour) {
						System.out.println("ici");

						if (this.jEnCours.isEstR�el() && this.phaseDeJeu == PhaseDeJeu.PhaseTourFini) {
							tour = false;

							int i = 0;
							
							System.out.println(this.getjEnCours().getNom() + " fini son tour.");

						} else if ((!this.estEnInterfaceGraphique) || (!this.getjEnCours().isEstR�el())) {

							if (this.jEnCours.isEstR�el()) {
								InterfaceJouer();
							}

							System.out.println(jEnCours.getNom() + " a " + jEnCours.getPointsJour() + " points jour, "
									+ jEnCours.getPointsNuit() + " points nuit, " + jEnCours.getPointsNeant()
									+ " points n�ant");

							choice = jEnCours.s�lectionnerAction();
							switch (choice) {
							case 0:
								tour = false;
								System.out.println(this.getjEnCours().getNom() + " fini son tour.");
								this.phaseDeJeu = PhaseDeJeu.PhaseTourFini;
								break;
							case 1:
								if (this.jEnCours.isEstR�el()) {
									jEnCours.getMainJoueur().afficherMain();
								}

								jEnCours.poserCroyant();
								break;
							case 2:
								if (this.jEnCours.isEstR�el()) {
									jEnCours.getMainJoueur().afficherMain();
								}
								jEnCours.poserGuide();
								break;
							case 3:
								jEnCours.getMainJoueur().afficherMain();
								break;
							case 4:
								System.out.println(jEnCours.getNom() + " a " + jEnCours.getPointsJour()
										+ " points jour, " + jEnCours.getPointsNuit() + " points nuit, "
										+ jEnCours.getPointsNeant() + " points n�ant");
								break;
							case 5:
								MilieuDeTable.getInstance().afficherMilieu();
								break;
							case 6:
								jEnCours.afficherCartesDevant();
								break;
							case 7:
								for (Iterator<Joueur> it1 = joueurs.iterator(); it1.hasNext();) {
									Joueur joueurDevant = it1.next();
									joueurDevant.afficherCartesDevant();
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------");
								break;
							case 8:
								System.out.println("Votre carte divinit� est : ");
								System.out.println(jEnCours.getDivinit�());
								System.out.println(
										"----------------------------------------------------------------------------------------------");
								break;
							case 9:
								jEnCours.poserApocalypse();
								break;
							case 10:
								jEnCours.sacrifierCroyant();
								break;
							case 11:
								jEnCours.sacrifierGuide();
								break;
							case 12:
								jEnCours.utiliserDeusEx();
								break;
							case 13:
								jEnCours.utiliserDivinit�();
								break;
							case 100:
								this.terminerPartie();
								break;
							default:
								System.out.println("Donne un chiffre correct !");
								break;
							}

						}

					}

				}

			}
			for (Iterator<Croyant> it0 = MilieuDeTable.getInstance().getListeCroyants().iterator(); it0.hasNext();) {
				Croyant croyant = it0.next();
				croyant.setPeutEtreRecuperer(true);
			}

		}
		for (Iterator<Joueur> it = this.joueurs.iterator(); it.hasNext();) {
			Joueur joueur = it.next();
			joueur.setPeutSacrifierCroyant(true);
			joueur.setPeutSacrifierGuide(true);
			joueur.setPeutGagnerPointAction(true);
		}

		joueurs.add(joueurs.get(0));
		joueurs.remove(0);
		this.numTour++;
		this.apocalypseDejaJouee++;
		if (piocheCA.getDeck().isEmpty()) {
			this.piocheCA.prendreDefausse();
		}
	}

	/**
	 * Returns estEnCours.
	 * 
	 * @return estEnCours
	 */
	public boolean getEstEnCours() {
		return this.estEnCours;
	}

	public int getApocalypseDejaJouee() {
		return apocalypseDejaJouee;
	}

	public void setApocalypseDejaJouee(int apocalypseDejaJouee) {
		this.apocalypseDejaJouee = apocalypseDejaJouee;
	}

	public int getNumTour() {
		return numTour;
	}

	public void setNumTour(int numTour) {
		this.numTour = numTour;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public Joueur getJReel() {
		ArrayList<Joueur> joueurs = this.getJoueurs();
		Joueur j = null;
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j2 = it.next();
			if (j2.isEstR�el()) {
				j = j2;
			}

		}
		return j;
	}

	/**
	 * Sets a value to attribute estEnCours.
	 * 
	 * @param newEstEnCours
	 */
	public void setEstEnCours(boolean newEstEnCours) {
		this.estEnCours = newEstEnCours;
	}

	/**
	 * Returns nombreJoueurs.
	 * 
	 * @return nombreJoueurs
	 */
	public int getNombreJoueurs() {
		return this.nombreJoueurs;
	}

	public int getR�sultatD�() {
		return this.r�sultatD�;
	}

	public PhaseDeJeu getPhaseDeJeu() {
		return this.phaseDeJeu;
	}

	public void setPhaseDeJeu(PhaseDeJeu phaseDeJeu) {
		this.phaseDeJeu = phaseDeJeu;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * D�cr�mente le nombre de joueurs
	 * 
	 * @return
	 */
	public void setNombreJoueurs() {
		this.nombreJoueurs -= 1;
	}

	public Joueur getjEnCours() {
		return jEnCours;
	}

	public void setjEnCours(Joueur jEnCours) {
		this.jEnCours = jEnCours;
	}

	public Pioche<CarteAction> getPiocheCA() {
		return piocheCA;
	}

	public void setPiocheCA(Pioche<CarteAction> piocheCA) {
		this.piocheCA = piocheCA;
	}

	public CarteAction getCarteActivee() {
		return carteActivee;
	}

	public void setCarteActivee(CarteAction carteActivee) {
		this.carteActivee = carteActivee;
	}
	
	public void setCarteActiveeNull() {
		this.carteActivee = null;
	}

	public Joueur getjIntervenant() {
		return jIntervenant;
	}

	public boolean isEnInterfaceGraphique() {
		return this.estEnInterfaceGraphique;
	}

	public void setInterfaceGraphique(boolean choix) {
		this.estEnInterfaceGraphique = choix;
	}

	public void setjIntervenant(Joueur jIntervenant) {
		this.jIntervenant = jIntervenant;
	}

	public int getChoixInterface() {
		return choixInterface;
	}

	public void setChoixInterface(int choixInterface) {
		this.choixInterface = choixInterface;
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

		Partie p0 = Partie.getInstance();
		try {
			p0.estEnCours = p0.demarrerPartie();
		} catch (DemarrerPartieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numVirtuels = p0.demanderNbJVirtuels();
		int diff = p0.demanderDifficult�();
		ArrayList<String> nomDesJoueurs = p0.demanderNomJoueurs(numVirtuels + 1);
		p0.InitialiserPartie(numVirtuels, diff, nomDesJoueurs);
		while (p0.estEnCours) {
			p0.tourComplet(p0.joueurs, p0);
		}
		p0.terminerPartie();
	}*/

}
