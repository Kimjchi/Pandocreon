package joueurs;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cartes.*;
import interfaceGraphique.VueChoisirAdversaire;
import partie.Partie;
import partie.PhaseDeJeu;

public class Joueur extends Observable {

	protected String nom;
	protected int pointsNeant;
	protected int pointsNuit;
	protected int pointsJour;
	// Cr�er classe divinit� et son attribut ici
	protected int nbPri�res;
	protected int nbCroyants;
	protected int nbGuidesSprituels;
	protected int numJoueur; // attribuer un num�ro � chaque joueur
	protected int numArray; // num�ro du joueur dans le tableau
	protected Divinit� divinit�;
	protected Main mainJoueur;
	protected ArrayList<GuideSpirituel> carteDevant;
	protected GuideSpirituel guidePos�;
	protected boolean peutSacrifierCroyant;
	protected boolean peutSacrifierGuide;
	protected boolean peutGagnerPointAction;
	protected boolean capaciteAnnulee;
	protected boolean peutPoserGuide;
	protected boolean guideProtege;
	protected boolean estR�el;

	public Joueur(int numTab, Pioche<CarteAction> pioche, String nom) {
		this.numJoueur = numTab + 1;
		this.numArray = numTab;
		this.nom = nom;

		this.pointsJour = 100;
		this.pointsNeant = 100;
		this.pointsNuit = 100;
		this.nbPri�res = 0;
		mainJoueur = new Main(pioche);
		this.carteDevant = new ArrayList<GuideSpirituel>();
		this.peutSacrifierCroyant = false;
		this.peutSacrifierGuide = false;
		this.peutGagnerPointAction = true;
		this.capaciteAnnulee = false;
		this.peutPoserGuide = true;
		this.guideProtege = false;
		this.estR�el = true;
	}

	@Override
	public String toString() {
		return ("Le joueur n�" + this.numJoueur + " s'appelle " + getNom());
	}

	public String saisirNom() {
		String nom = null;
		if (!Partie.getInstance().isEnInterfaceGraphique()) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Comment s'appelle le joueur n�" + this.numJoueur);
			nom = sc.nextLine();

			return nom;
		}
		return nom;

	}

	public void gagnerPointsAction(int resultat) {
		if (this.peutGagnerPointAction) {
			switch (resultat) {
			case 1:

				if (this.getDivinit�().getOrigine() == Origine.Jour) {
					this.setPointsJour(2);
					this.setChanged();
					this.notifyObservers();
				} else if (this.getDivinit�().getOrigine() == Origine.Aube) {
					this.setPointsJour(1);
					this.setChanged();
					this.notifyObservers();
				}
				break;
			case 2:

				if (this.getDivinit�().getOrigine() == Origine.Nuit) {
					this.setPointsNuit(2);
					this.setChanged();
					this.notifyObservers();
				} else if (this.getDivinit�().getOrigine() == Origine.Cr�puscule) {
					this.setPointsNuit(1);
					this.setChanged();
					this.notifyObservers();
				}
				break;
			case 3:

				if (this.getDivinit�().getOrigine() == Origine.Aube) {
					this.setPointsNeant(1);
					this.setChanged();
					this.notifyObservers();
				} else if (this.getDivinit�().getOrigine() == Origine.Cr�puscule) {
					this.setPointsNeant(1);
					this.setChanged();
					this.notifyObservers();
				}
			}
		} else {
			System.out.println("Vous ne pouvez pas gagner de points d'action ce tour");
		}

	}

	public int s�l�ctionnerCosmogonie() {
		int choix = 0;
		if (!Partie.getInstance().isEnInterfaceGraphique()) {

			Scanner sc = new Scanner(System.in);
			System.out.println("Quelle cosmologie de d� voulez-vous ? [1] Jour [2] Nuit [3] N�ant");
			choix = sc.nextInt();

			return choix;
		}
		return choix;
	}

	public boolean verifierPoints(Joueur this,Carte carte) {
		if ((carte.getOrigine() == cartes.Origine.Nuit) && (this.pointsNuit > 0)) {
			this.pointsNuit--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Jour) && (this.pointsJour > 0)) {
			this.pointsJour--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.N�ant) && (this.pointsNeant > 0)) {
			this.pointsNeant--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.N�ant) && (this.pointsJour > 1)) {
			this.pointsJour = this.pointsJour - 2;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.N�ant) && (this.pointsNuit > 1)) {
			this.pointsNuit = this.pointsNuit - 2;
			return true;
		} else if (carte.getOrigine() == cartes.Origine.Nul) {
			return true;
		} else {
			System.out.println("Tu n'as pas assez de points !");
			return false;
		}
	}

	public int s�lectionnerAction() {
		if (!Partie.getInstance().isEnInterfaceGraphique()) {

			Scanner sc = new Scanner(System.in);
			int action = sc.nextInt();

			return action;
		}
		return 0;
	}

	public void poserCroyant(Joueur this) {
		boolean peutJouer;
		int numCarte;
		System.out.println("Quelle est le num�ro du croyant � poser ?");
		Scanner sc = new Scanner(System.in);
		numCarte = sc.nextInt() - 1;
		peutJouer = verifierPoints(mainJoueur.get(numCarte));
		if (peutJouer) {
			MilieuDeTable.getInstance().poserCroyantMilieu((Croyant) mainJoueur.get(numCarte));
			mainJoueur.enleverMain(numCarte);
			mainJoueur.get(numCarte).setPosition(Position.Centre);
			MilieuDeTable.getInstance().afficherMilieu();
		}

	}

	public void poserCroyant(Croyant croyant) {
		boolean peutJouer;
		peutJouer = verifierPoints(croyant);
		if (peutJouer) {
			MilieuDeTable.getInstance().poserCroyantMilieu(croyant);
			mainJoueur.enleverMain(croyant);
			croyant.setPosition(Position.Centre);
			MilieuDeTable.getInstance().afficherMilieu();

		}
		this.setChanged();
		this.notifyObservers();
	}

	public void poserGuide(Joueur this) {
		boolean peutJouer;
		int numCarte;
		System.out.println("Quelle est le num�ro du guide � poser ?");
		Scanner sc = new Scanner(System.in);
		numCarte = sc.nextInt() - 1;
		Partie.getInstance().setCarteActivee(this.mainJoueur.get(numCarte));
		this.reagir();
		if (this.peutPoserGuide) {
			peutJouer = verifierPoints(this.mainJoueur.get(numCarte));
			if (peutJouer) {
				if (this.mainJoueur.get(numCarte).getType() == cartes.Type.GuideSpirituel) {
					guiderCroyant((GuideSpirituel) mainJoueur.get(numCarte), numCarte);
				} else {
					System.out.println("Ce n'est pas un guide spirituel !");
				}

			}
		} else {
			System.out.println("Vous ne pouvez pas poser de guide ce tour");
			this.mainJoueur.enleverMain(numCarte);
		}
		this.peutPoserGuide = true;

	}

	public void poserGuide(GuideSpirituel guide) {
		boolean peutJouer;
		Partie.getInstance().setCarteActivee(guide);
		this.reagir();
		if (this.peutPoserGuide) {
			peutJouer = verifierPoints(guide);
			if (peutJouer) {
				if (guide.getType() == cartes.Type.GuideSpirituel) {
					guide.setPosition(Position.TerrainJoueur);
					guiderCroyant(guide, this.mainJoueur.getContenuMain().indexOf(guide));
				} else {
					System.out.println("Ce n'est pas un guide spirituel !");
				}

			}
		} else {
			System.out.println("Vous ne pouvez pas poser de guide ce tour");
			this.mainJoueur.enleverMain(guide);
		}
		this.peutPoserGuide = true;
	}

	public boolean testerCroyant(GuideSpirituel guide, Croyant croyant) {
		boolean compatible = false;
		if (MilieuDeTable.getInstance().getListeCroyants().size() != 0) {
			if ((guide.getNbCroyantsAttach�s() < guide.getNbCroyantsMax())) {
				MilieuDeTable.getInstance().afficherMilieu();
				compatible = false;
				if (croyant.isPeutEtreRecuperer()) {
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 3; j++) {
							if (guide.getDogme(i) == croyant.getDogme(j)) {
								compatible = true;
							}
						}
					}
				}
			}
		}
		return compatible;
	}

	public void guiderCroyant(GuideSpirituel guide, Croyant croyant) {

		guide.attacherCroyant((Croyant) croyant);
		guide.setPosition(Position.TerrainJoueur);
		croyant.setPosition(Position.TerrainJoueur);

		this.setnbPri�res(croyant.getNbPrieres());
		MilieuDeTable.getInstance().enleverDuMilieu((Croyant) croyant);
		System.out.println("La carte est bien guid�e !");
	}

	public void guiderCroyant(Joueur this,GuideSpirituel guide, int numCarte) {
		boolean passe = false;
		boolean possible = true;
		while ((guide.getNbCroyantsAttach�s() < guide.getNbCroyantsMax()) && (possible)) {
			MilieuDeTable.getInstance().afficherMilieu();
			System.out.println("Donnez le n� de la carte Croyant que vous voulez ramener");
			Scanner sc = new Scanner(System.in);
			int numCroyant = sc.nextInt() - 1;
			boolean compatible = false;
			if (MilieuDeTable.getInstance().getListeCroyants().get(numCroyant).isPeutEtreRecuperer()) {
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 3; j++) {
						if (guide.getDogme(i) == MilieuDeTable.getInstance().getListeCroyants().get(numCroyant)
								.getDogme(j)) {
							compatible = true;
						}
					}
				}
			} else {
				System.out.println("Il faut passer son tour pour pouvoir r�cup�rer un croyant qu'on a pos�");
			}

			if (compatible) {
				passe = true;

				guide.attacherCroyant((Croyant) MilieuDeTable.getInstance().getListeCroyants().get(numCroyant));
				this.setnbPri�res(MilieuDeTable.getInstance().getListeCroyants().get(numCroyant).getNbPrieres());
				MilieuDeTable.getInstance()
						.enleverDuMilieu((Croyant) MilieuDeTable.getInstance().getListeCroyants().get(numCroyant));
				guide.setPosition(Position.TerrainJoueur);
				System.out.println("La carte est bien guid�e !");
				;

				if (MilieuDeTable.getInstance().getListeCroyants().size() == 0) {
					System.out.println("Il n'y a plus de croyants au milieu");
					possible = false;
				} else {
					System.out.println("Voulez-vous continuer ? [0] Oui/[1] Non");
					Scanner sc1 = new Scanner(System.in);
					int choix = sc1.nextInt();
					if (choix == 1) {
						possible = false;
					}

				}

			} else {
				System.out.println("Le croyant s�lectionn� n'est pas compatible avec votre guide");
				System.out.println("Voulez-vous r�essayer ? [0] Oui/[1] Non");
				Scanner sc1 = new Scanner(System.in);
				int choix = sc1.nextInt();
				if (choix == 1) {
					possible = false;
				}

			}

		}

		if (passe) {
			this.carteDevant.add((GuideSpirituel) mainJoueur.get(numCarte));
			mainJoueur.enleverMain(numCarte);

		}

	}

	public void poserApocalypse(Joueur this) {
		this.getMainJoueur().afficherMain();
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Quelle est le num�ro de votre carte Apocalypse ?");
		int numCarte = sc2.nextInt() - 1;
		if ((Partie.getInstance().getNumTour() != 1) && (Partie.getInstance().getApocalypseDejaJouee() >= 2)) {
			if (this.getMainJoueur().get(numCarte).getType() == Type.Apocalypse) {

				Partie.getInstance().setCarteActivee(this.getMainJoueur().getContenuMain().get(numCarte));
				this.reagir();

				if (!this.capaciteAnnulee) {
					this.getMainJoueur().get(numCarte).getCapacit�().sacrifier(this);
					;
				} else {
					System.out.println("Votre carte Apocalypse est annul�e");
				}

				this.getMainJoueur().enleverMain(numCarte);
				Partie.getInstance().setApocalypseDejaJouee(0);
				for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
					Joueur joueur = it.next();
					if (joueur.isGuideProtege() == true) {
						for (Iterator<GuideSpirituel> it2 = joueur.carteDevant.iterator(); it2.hasNext();) {
							GuideSpirituel guide = it2.next();
							guide.setProtege(false);
						}
					}
				}
			} else {
				System.out.println("Ce n'est pas une carte Apocalypse");
			}
		} else {
			System.out.println("Impossible de jouer une carte Apocalypse dans cette situation, lisez les r�gles !");
		}

	}

	public void poserApocalypse(Apocalypse apo) {
		this.getMainJoueur().afficherMain();
		if ((Partie.getInstance().getNumTour() != 1) && (Partie.getInstance().getApocalypseDejaJouee() >= 2)) {

			Partie.getInstance().setCarteActivee(apo);
			// this.reagir();

			if (!this.capaciteAnnulee) {
				apo.getCapacit�().sacrifier(this);

			} else {
				System.out.println("Votre carte Apocalypse est annul�e");
			}

			this.getMainJoueur().enleverMain(apo);
			Partie.getInstance().setApocalypseDejaJouee(0);
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur joueur = it.next();
				if (joueur.isGuideProtege() == true) {
					for (Iterator<GuideSpirituel> it2 = joueur.carteDevant.iterator(); it2.hasNext();) {
						GuideSpirituel guide = it2.next();
						guide.setProtege(false);
					}
				}
			}

		} else {
			System.out.println("Impossible de jouer une carte Apocalypse dans cette situation, lisez les r�gles !");
		}

	}

	public void afficherCartesDevant() {
		System.out.println("Cartes ramen�es de " + this.getNom() + " :");
		int i = 1;
		for (Iterator<GuideSpirituel> it = this.carteDevant.iterator(); it.hasNext();) {
			GuideSpirituel guide = it.next();
			System.out.print("Carte n�" + (i));
			System.out.println(guide);
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			i++;
		}
	}

	public void sacrifierCroyant(Joueur this) {
		if (this.peutSacrifierCroyant) {
			afficherCartesDevant();
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Quelle est le num�ro du guide qui a votre croyant � sacrifier ?");
			int numGuide = sc3.nextInt() - 1;

			getCarteDevant().get(numGuide).afficherCroyantsAttach�s();
			Scanner sc4 = new Scanner(System.in);
			System.out.println("Quelle est le num�ro du croyant � sacrifier ?");
			int numCroyant = sc4.nextInt() - 1;
			Partie.getInstance().setCarteActivee(this.getMainJoueur().getContenuMain().get(numCroyant));
			this.reagir();

			if (!this.capaciteAnnulee) {
				this.getCarteDevant().get(numGuide).getCroyantAttache().get(numCroyant).getCapacit�().sacrifier(this);
			} else {
				System.out.println("La capacit� du croyant est annul�e");
			}
			this.defausserCroyant(numGuide, numCroyant);

		} else {
			System.out.println("Vous ne pouvez pas sacrifier de croyant pendant ce tour");
		}
		this.capaciteAnnulee = false;
	}

	public void sacrifierCroyant(Croyant croyant) {
		if (this.peutSacrifierCroyant) {
			Partie.getInstance().setCarteActivee(croyant);
			this.reagir();
			if (!this.capaciteAnnulee) {
				croyant.getCapacit�().sacrifier(this);
			} else {
				System.out.println("La capacit� du croyant est annul�e");
			}
			this.defausserCroyant(croyant);
		} else {
			System.out.println("Vous ne pouvez pas sacrifier de croyant pendant ce tour");
		}
		this.capaciteAnnulee = false;
	}

	public void sacrifierGuide(Joueur this) {
		if (this.peutSacrifierGuide) {
			this.afficherCartesDevant();
			Scanner sc = new Scanner(System.in);
			System.out.println("Donner l'indice du guide � sacrifier");
			int numGuide = sc.nextInt();
			Partie.getInstance().setCarteActivee(this.getMainJoueur().getContenuMain().get(numGuide));
			this.reagir();
			if (!this.capaciteAnnulee) {
				this.carteDevant.get(numGuide).getCapacit�().sacrifier(this);
			} else {
				System.out.println("La capacit� du guide est annul�e");
			}
			this.defausserGuide(numGuide);

		} else {
			System.out.println("Vous ne pouvez pas sacrifier de guide pendant ce tour");
		}
		this.capaciteAnnulee = false;
		this.setChanged();
		this.notifyObservers();
	}

	public void sacrifierGuide(GuideSpirituel guide) {
		if (this.peutSacrifierGuide) {

			Partie.getInstance().setCarteActivee(guide);
			this.reagir();
			if (!this.capaciteAnnulee) {
				guide.getCapacit�().sacrifier(this);
			} else {
				System.out.println("La capacit� du guide est annul�e");
			}
			this.defausserGuide(guide);
		} else {
			System.out.println("Vous ne pouvez pas sacrifier de guide pendant ce tour");
		}
		this.capaciteAnnulee = false;
		this.setChanged();
		this.notifyObservers();
	}

	public void defausserGuide(int numGuide) {
		renvoyerCroyant(numGuide);
		Defausse.getInstance().d�fausser(this.carteDevant.get(numGuide));
		this.carteDevant.remove(numGuide);

	}

	public void defausserGuide(GuideSpirituel guide) {
		renvoyerCroyant(guide);
		Defausse.getInstance().d�fausser(guide);
		this.carteDevant.remove(guide);

	}

	public void defausserCroyant(int numGuide, int numCroyant) {

		this.setnbPri�res(-this.getCarteDevant().get(numGuide).getCroyantAttache().get(numCroyant).getNbPrieres());

		Defausse.getInstance().d�fausser(this.getCarteDevant().get(numGuide).getCroyantAttache().get(numCroyant));
		this.getCarteDevant().get(numGuide).getCroyantAttache().remove(numCroyant);

		boolean detruireGuide = getCarteDevant().get(numGuide).verificationCroyant();

		if (detruireGuide) {

			this.defausserGuide(numGuide);
		}
	}

	public void defausserCroyant(Croyant croyant) {
		this.setnbPri�res(croyant.getNbPrieres());

		Defausse.getInstance().d�fausser(croyant);
		GuideSpirituel guide = croyant.getGuidePropri�taire();
		Partie.getInstance().getjEnCours().getCarteDevant()
				.get(Partie.getInstance().getjEnCours().getCarteDevant().indexOf(guide)).getCroyantAttache()
				.remove(croyant);
		boolean detruireGuide = guide.verificationCroyant();

		if (detruireGuide) {

			this.defausserGuide(guide);
		}
	}

	public void renvoyerCroyant(int numGuide) {
		for (Iterator<Croyant> it = this.carteDevant.get(numGuide).getCroyantAttache().iterator(); it.hasNext();) {
			Croyant croyant = it.next();
			this.setnbPri�res(-croyant.getNbPrieres());
			MilieuDeTable.getInstance().poserCroyantMilieu(croyant);
			it.remove();

		}

	}

	public void renvoyerCroyant(GuideSpirituel guide) {
		for (Iterator<Croyant> it = guide.getCroyantAttache().iterator(); it.hasNext();) {
			Croyant croyant = it.next();
			this.setnbPri�res(-croyant.getNbPrieres());
			MilieuDeTable.getInstance().poserCroyantMilieu(croyant);
			it.remove();

		}

	}

	public void utiliserDeusEx() {
		this.getMainJoueur().afficherMain();
		Scanner sc = new Scanner(System.in);
		System.out.println("Donner l'indice de la carte DeusEx � utiliser");
		int numDeus = sc.nextInt();
		if (this.getMainJoueur().getContenuMain().get(numDeus).getType() == cartes.Type.DeusEx) {
			Partie.getInstance().setCarteActivee(this.getMainJoueur().getContenuMain().get(numDeus));
			this.reagir();
			if (!this.capaciteAnnulee) {
				this.getMainJoueur().getContenuMain().get(numDeus).getCapacit�().sacrifier(this);
			} else {
				System.out.println("La carte DeusEx est annul�e");
			}
			this.getMainJoueur().enleverMain(numDeus);
		} else {
			System.out.println("Ce n'est pas une carte DeusEx");
		}
		this.capaciteAnnulee = false;

	}

	public void utiliserDeusEx(DeusEx deusEx) {
		this.getMainJoueur().afficherMain();
		System.out.println("Donner l'indice de la carte DeusEx � utiliser");
		if (deusEx.getType() == cartes.Type.DeusEx) {
			Partie.getInstance().setCarteActivee(deusEx);
			// this.reagir();
			if (!this.capaciteAnnulee) {
				deusEx.getCapacit�().sacrifier(this);
			} else {
				System.out.println("La carte DeusEx est annul�e");
			}
			this.getMainJoueur().enleverMain(deusEx);
		} else {
			System.out.println("Ce n'est pas une carte DeusEx");
		}
		this.capaciteAnnulee = false;
	}

	public void utiliserDivinit�() {
		if (this.getDivinit�().getLaCapaciteEstUtilisee()) {
			System.out.println("Vous avez d�j� utilis� la capacit� de votre divinit�");
		} else {
			System.out.println(this.getDivinit�());
			Scanner sc = new Scanner(System.in);
			System.out.println("Voulez-vous utiliser la capacit� de votre divinit� ? [0] Oui/[1] Non");
			int choix = sc.nextInt();
			switch (choix) {
			case 0:
				this.getDivinit�().getCapacit�().sacrifier(this);
				this.getDivinit�().setLaCapaciteEstUtilisee(true);
				break;
			default:
				System.out.println("Vous n'avez pas utilis� votre capacit� divine");
				break;
			}

		}

	}

	public int choisirAdversaire(Joueur this) {
		if (Partie.getInstance().isEnInterfaceGraphique()) {
			Joueur[] joueurs = null;
			String[] possibleValues = null;
			int j = 0;
			for (int i = 0; i < Partie.getInstance().getNombreJoueurs(); i++) {
				if (!Partie.getInstance().getJoueurs().get(i).isEstR�el()) {
					possibleValues[j] = Partie.getInstance().getJoueurs().get(i).getNom();
					joueurs[j] = Partie.getInstance().getJoueurs().get(i);
					j++;

				}
			}
			String texte = "Quelle est votre cible ?";
			JFrame frame = new JFrame();
			String choix = (String) JOptionPane.showInputDialog(frame, texte, "Choix du nombre de joueurs - Pandocreon",
					JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
			int i = 0;
			while (Partie.getInstance().getJoueurs().get(i).getNom() != choix) {
				i++;

			}
			Joueur cible = Partie.getInstance().getJoueurs().get(i);
			return Partie.getInstance().getJoueurs().indexOf(cible);
		} else {
			boolean choix = true;
			int i = 1;
			int indice = 0;
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur joueur = it.next();
				System.out.println(i + ". " + joueur.getNom());
				System.out.println(joueur.getDivinit�());
			}
			Scanner sc = new Scanner(System.in);
			while (choix) {
				System.out.println("Quelle joueur voulez-vous attaquer ?");
				indice = sc.nextInt() - 1;
				if (Partie.getInstance().getJoueurs().get(indice) == Partie.getInstance().getjEnCours()) {
					System.out.println("Vous ne pouvez pas vous attaquer !");
				} else {
					choix = false;
				}
			}

			return indice;
		}
	}

	public int choisirAdversaireAvecGuideSprituels(Joueur this) {

		if (Partie.getInstance().isEnInterfaceGraphique()) {
			Joueur[] joueurs = null;
			String[] possibleValues = null;
			int j = 0;
			for (int i = 0; i < Partie.getInstance().getNombreJoueurs(); i++) {
				if (!Partie.getInstance().getJoueurs().get(i).isEstR�el()
						&& !(Partie.getInstance().getJoueurs().get(i).getCarteDevant().size() == 0)) {
					possibleValues[j] = Partie.getInstance().getJoueurs().get(i).getNom();
					joueurs[j] = Partie.getInstance().getJoueurs().get(i);
					j++;

				}
			}
			String texte = "Quelle est votre cible ?";
			JFrame frame = new JFrame();
			if (joueurs != null) {
				String choix = (String) JOptionPane.showInputDialog(frame, texte,
						"Choix du nombre de joueurs - Pandocreon", JOptionPane.INFORMATION_MESSAGE, null,
						possibleValues, possibleValues[0]);
				int i = 0;
				while (Partie.getInstance().getJoueurs().get(i).getNom() != choix) {
					i++;

				}
				Joueur cible = Partie.getInstance().getJoueurs().get(i);
				return Partie.getInstance().getJoueurs().indexOf(cible);
			} else {

				JOptionPane.showMessageDialog(new JFrame(),
						"Vous devez attendre la phase de jeu pour pouvoir activer une carte DeusEx",
						"Action impossible", JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		}
		boolean choix = true;
		int i = 1;
		int indice = 0;
		for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
			Joueur joueur = it.next();
			System.out.println(i + ". " + joueur.getNom());
		}
		Scanner sc = new Scanner(System.in);
		while (choix) {
			System.out.println(
					"Quelle joueur voulez-vous attaquer ? (Vous devez forcement choisir un adversaire avec au moins un croyant.)");
			indice = sc.nextInt() - 1;
			if (Partie.getInstance().getJoueurs().get(indice) == Partie.getInstance().getjEnCours()) {
				System.out.println("Vous ne pouvez pas vous attaquer !");
			} else if (Partie.getInstance().getJoueurs().get(indice).getNbGuidesSprituels() == 0) {
				System.out.println("La divinit� choisie n'a pas de croyant devant elle. Choisissez en une autre.");
			} else {
				choix = false;
			}
		}

		return indice;

	}

	public int choisirUnDeSesGuides(Joueur this) {
		this.afficherCartesDevant();
		int indice = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel guide voulez-vous selectionner ?");
		boolean valide = false;
		while (!valide) {
			indice = sc.nextInt() - 1;
			if (this.getCarteDevant().get(indice).getType() == Type.GuideSpirituel) {
				valide = true;
			} else {
				System.out.println("Veuillez saisir un guide valide.");
			}

		}

		return indice;
	}

	public int choisirGuideAdversaire(Joueur this,int adversaire) {
		int indice = 0;
		boolean valide = false;
		Partie.getInstance().getJoueurs().get(adversaire).afficherCartesDevant();
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel guide voulez-vous attaquer");
		while (!valide) {

			indice = sc.nextInt() - 1;
			if (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(indice)
					.getType() == Type.GuideSpirituel) {
				valide = true;
			} else {
				System.out.println("Veuillez saisir un guide valide.");
			}

		}

		return indice;
	}

	public int s�lectionnerCroyant(Joueur this,int divinit�, int guide) {
		int choice = 0;
		Partie.getInstance().getJoueurs().get(divinit�).getCarteDevant().get(guide).afficherCroyantsAttach�s();
		System.out.println("Quel croyant voulez vous cibler ?");
		boolean valide = false;
		Scanner sc = new Scanner(System.in);
		while (!valide) {
			choice = sc.nextInt() - 1;
			if (Partie.getInstance().getJoueurs().get(divinit�).getCarteDevant().get(choice)
					.getType() == Type.Croyant) {
				valide = true;
			} else {
				System.out.println("Veuillez saisir un croyant valide.");
			}
		}

		return choice;
	}

	public int choisirGuideOuCroyant() {
		int choice = 0;
		System.out.println("Voulez-vous utiliser la capacit� d'un guide ou d'un croyant ? [0] Guide/[1] Croyant");
		boolean valide = false;
		Scanner sc = new Scanner(System.in);
		while (!valide) {
			choice = sc.nextInt() - 1;
			if ((choice == 0) || (choice == 1)) {
				valide = true;
			} else {
				System.out.println("Veuillez saisir une valeur valide.");
			}
		}

		return choice;
	}

	public int choisirAdversaireAvec2Croyants(Joueur this) {
		int indice;
		if (Partie.getInstance().isEnInterfaceGraphique()) {
			indice = this.choisirAdversaire();
			if (!(Partie.getInstance().getJoueurs().get(indice).getNbCroyants() < 2)) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Pas d'adversaire compatible",
						"Action impossible", JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		}
		boolean choix = true;
		int i = 1;
		indice = 0;
		for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
			Joueur joueur = it.next();
			System.out.println(i + ". " + joueur.getNom());
		}
		Scanner sc = new Scanner(System.in);
		while (choix) {
			System.out.println(
					"Quelle joueur voulez-vous attaquer ? (Vous devez forcement choisir un adversaire avec au moins deux croyant.)");
			indice = sc.nextInt() - 1;
			if (Partie.getInstance().getJoueurs().get(indice) == Partie.getInstance().getjEnCours()) {
				System.out.println("Vous ne pouvez pas vous attaquer !");
			} else if (Partie.getInstance().getJoueurs().get(indice).getNbCroyants() < 2) {
				System.out.println("La divinit� choisie a moins de deux croyants, veuillez en choisir une autre.");
			} else {
				choix = false;
			}
		}

		return indice;

	}

	public void voulezVousDefausser() {

		if (!Partie.getInstance().isEnInterfaceGraphique()) {
			this.getMainJoueur().afficherMain();
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Voulez-vous d�fausser des cartes ? [0] Oui/[1] Non");
			int choix = sc2.nextInt();
			while (Partie.getInstance().getPhaseDeJeu() == PhaseDeJeu.PhaseD�fausse) {
				while (choix == 0) {
					Scanner sc0 = new Scanner(System.in);
					System.out.println("Quelle est le num�ro de la carte � d�fausser ?");
					int numCarte = sc0.nextInt() - 1;
					Defausse.getInstance().d�fausser(this.getMainJoueur().get(numCarte));
					this.getMainJoueur().enleverMain(numCarte);
					this.getMainJoueur().afficherMain();

					if (this.getMainJoueur().getContenuMain().size() == 0) {
						choix = 1;
					} else {
						System.out.println("Voulez-vous d�fausser des cartes ? [0] Oui/[1] Non");
						choix = sc2.nextInt();
					}
				}
				Partie.getInstance().setPhaseDeJeu(PhaseDeJeu.PhasePioche);
			}
		}
	}

	public void voulezVousPiocher() {

		if (!Partie.getInstance().isEnInterfaceGraphique()) {
			this.getMainJoueur().afficherMain();
			System.out.println("Voulez-vous remplir votre main ? [0] Oui/[1] Non");
			Scanner sc2 = new Scanner(System.in);

			int choix = sc2.nextInt();
			if (choix == 0) {
				this.getMainJoueur().remplirMain(Partie.getInstance().getPiocheCA());
				Partie.getInstance().getPiocheCA().prendreDefausse();
				this.getMainJoueur().remplirMain(Partie.getInstance().getPiocheCA());
				this.getMainJoueur().afficherMain();
			} else {

			}
		}
	}

	public void reagir() {
		if (!Partie.getInstance().isEnInterfaceGraphique()) {

			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Quelqu'un veut-il utiliser une carte DeusEx, Apocalypse sans origine ou la capacit� de votre divinit�? \n[0] Utiliser DeusEx ou Apocalypse \n[1] Utiliser capacit� divinit� \n[2] Personne ne veux pas jouer");
			boolean valide = false;
			while (!valide) {
				int choice = sc.nextInt();
				switch (choice) {
				case 0:
					int i = 1;
					for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
						Joueur joueur = it.next();
						System.out.println(i + ". " + joueur.getNom());
						i++;
					}
					System.out.println("Quel est l'indice du joueur qui veut jouer ?");
					Scanner sc2 = new Scanner(System.in);
					int choice2 = sc2.nextInt() - 1;
					Partie.getInstance().setjIntervenant(Partie.getInstance().getJoueurs().get(choice2));
					Partie.getInstance().getjIntervenant().getMainJoueur().afficherMain();
					System.out.println("Quelle est la carte DeusEx ou Apocalypse sans origine que vous voulez jouer ?");
					Scanner sc3 = new Scanner(System.in);
					int numCarte = sc3.nextInt() - 1;
					if (Partie.getInstance().getjIntervenant().getMainJoueur().getContenuMain().get(numCarte)
							.getOrigine() == cartes.Origine.Nul) {
						Partie.getInstance().getjIntervenant().getMainJoueur().getContenuMain().get(numCarte)
								.getCapacit�().sacrifier(this);
						Partie.getInstance().getjIntervenant().getMainJoueur().enleverMain(numCarte);
					} else {
						System.out.println("Ce n'est pas une carte sans origine");
					}
					valide = true;
					break;
				case 1:
					int j = 1;
					for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
						Joueur joueur = it.next();
						System.out.println(j + ". " + joueur.getNom());
						j++;
					}
					System.out.println("Quel est l'indice du joueur qui veut jouer ?");
					Scanner sc4 = new Scanner(System.in);
					int choice3 = sc4.nextInt() - 1;
					Partie.getInstance().setjIntervenant(Partie.getInstance().getJoueurs().get(choice3));
					System.out.println(Partie.getInstance().getjIntervenant().getDivinit�());
					System.out.println("Voulez-vous vraiment activer l'effet de votre divinit� ? [0] Oui/[1] Non");
					Scanner sc5 = new Scanner(System.in);
					int choice4 = sc5.nextInt();
					if (choice4 == 0) {
						Partie.getInstance().getjIntervenant().getDivinit�().getCapacit�().sacrifier(this);
						Partie.getInstance().getjIntervenant().getDivinit�().setLaCapaciteEstUtilisee(true);
					} else {
						System.out.println("Personne ne joue");
					}
					valide = true;
					break;
				case 2:
					System.out.println("Personne ne souhaite jouer");
					valide = true;
					break;
				default:
					break;
				}
			}

		}
	}

	public ArrayList<GuideSpirituel> getCarteDevant() {
		this.setChanged();
		this.notifyObservers();
		return carteDevant;
	}

	public void setCarteDevant(ArrayList<GuideSpirituel> carteDevant) {
		this.carteDevant = carteDevant;
	}

	public int getnbPri�res() {
		return this.nbPri�res;
	}

	public void setnbPri�res(int changement) {
		this.nbPri�res += changement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Divinit� getDivinit�() {
		return this.divinit�;
	}

	public int getPointsNeant() {
		return pointsNeant;
	}

	public void setPointsNeant(int pointsNeant) {
		this.pointsNeant += pointsNeant;
	}

	public int getPointsNuit() {
		return pointsNuit;
	}

	public void setPointsNuit(int pointsNuit) {
		this.pointsNuit += pointsNuit;
	}

	public int getPointsJour() {
		return pointsJour;
	}

	public void setPointsJour(int pointsJour) {
		this.pointsJour += pointsJour;
	}

	public void setDivinit�(Divinit� divinit�) {
		this.divinit� = divinit�;
	}

	public Main getMainJoueur() {
		return this.mainJoueur;
	}

	public boolean isPeutSacrifierCroyant() {
		return peutSacrifierCroyant;
	}

	public void setPeutSacrifierCroyant(boolean peutSacrifierCroyant) {
		this.peutSacrifierCroyant = peutSacrifierCroyant;
	}

	public boolean isPeutSacrifierGuide() {
		return peutSacrifierGuide;
	}

	public void setPeutSacrifierGuide(boolean peutSacrifierGuide) {
		this.peutSacrifierGuide = peutSacrifierGuide;
	}

	public int getNumArray() {
		return numArray;
	}

	public void setNumArray(int numArray) {
		this.numArray = numArray;
	}

	public int getNbCroyants() {
		this.nbCroyants = 0;
		for (Iterator<GuideSpirituel> it = this.carteDevant.iterator(); it.hasNext();) {
			GuideSpirituel guide = it.next();
			this.nbCroyants += guide.getNbCroyantsAttach�s();
		}
		return nbCroyants;
	}

	public int getNbGuidesSprituels() {
		return this.carteDevant.size();
	}

	public boolean isPeutGagnerPointAction() {
		return peutGagnerPointAction;
	}

	public void setPeutGagnerPointAction(boolean peutGagnerPointAction) {
		this.peutGagnerPointAction = peutGagnerPointAction;
	}

	public boolean isCapaciteAnnulee() {
		return capaciteAnnulee;
	}

	public void setCapaciteAnnulee(boolean capaciteAnnulee) {
		this.capaciteAnnulee = capaciteAnnulee;
	}

	public boolean isPeutPoserGuide() {
		return peutPoserGuide;
	}

	public void setPeutPoserGuide(boolean peutPoserGuide) {
		this.peutPoserGuide = peutPoserGuide;
	}

	public boolean isGuideProtege() {
		return guideProtege;
	}

	public void setGuideProtege(boolean guideProtege) {
		this.guideProtege = guideProtege;
	}

	public boolean isEstR�el() {
		return this.estR�el;
	}

}
