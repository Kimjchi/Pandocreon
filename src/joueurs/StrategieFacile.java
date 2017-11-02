package joueurs;

import java.util.ArrayList;
import java.util.Iterator;

import cartes.Carte;
import cartes.CarteAction;
import cartes.Croyant;
import cartes.Defausse;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.MilieuDeTable;
import cartes.Origine;
import cartes.Type;
import partie.Partie;

public class StrategieFacile implements Strategie {

	boolean aJoué;

	public StrategieFacile() {
		this.aJoué = true;
	}

	public int sélectionnerAction(IA ia) {
		if (!aJoué) {
			int choix = (int) (8 * Math.random() - 1);
			while (choix != 0 || choix != 1 || choix != 2 || choix != 3 || choix != 4 || choix != 5 || choix != 6
					|| choix != 7)
				choix = (int) (8 * Math.random() - 1);
			switch (choix) {
			case 1:
				if (this.peutPoserCroyant(ia)) {
					return 1;
				} else {
					return 0;
				}

			case 2:
				if (this.peutPoserGuide(ia)) {
					return 2;
				} else {
					return 0;
				}

			case 3:
				if (this.peutPoserApocalypse(ia)) {
					return 3;
				} else {
					return 0;
				}

			case 4:
				if (Partie.getInstance().getjEnCours().isPeutSacrifierCroyant()) {
					return 10;
				} else {
					return 0;
				}

			case 5:
				if (Partie.getInstance().getjEnCours().isPeutSacrifierGuide()) {
					return 11;
				} else {
					return 0;
				}
				// case 6:
				// if (this.peutPoserDeuxEx()) {
				// return 12;
				// } else {
				// return 0;
				// }
				// case 7:
				// if
				// (Partie.getInstance().getjEnCours().getDivinité().getLaCapaciteEstUtilisee()
				// == false) {
				// return 13;
				// } else {
				// return 0;
				// }
			case 0:
				return 0;
			default:
				return 0;
			}
		} else {
			this.aJoué = true;
			return 0;

		}
	}

	public void voulezVousDefausser(IA ia) {
		Iterator<CarteAction> it = ia.mainJoueur.getContenuMain().iterator();
		ArrayList<Integer> tabDefausse = new ArrayList<Integer>();
		if (Partie.getInstance().getNumTour() % 20 != 0) {

			switch (ia.getDivinité().getOrigine()) {
			case Jour:
				while (it.hasNext()) {
					CarteAction ca = it.next();
					if ((ca.getOrigine() != Origine.Nuit && ca.getOrigine() != Origine.Nul)
							|| ca.getType() == Type.DeusEx) {
						tabDefausse.add(ia.mainJoueur.getContenuMain().indexOf(ca));
					}

				}
				break;
			case Aube:
				while (it.hasNext()) {
					CarteAction ca = it.next();
					if ((ca.getOrigine() != Origine.Jour && ca.getOrigine() != Origine.Nul
							&& ca.getOrigine() != Origine.Néant) || ca.getType() == Type.DeusEx) {
						tabDefausse.add(ia.mainJoueur.getContenuMain().indexOf(ca));
					}

				}
				break;
			case Nuit:
				while (it.hasNext()) {
					CarteAction ca = it.next();
					if ((ca.getOrigine() != Origine.Nuit && ca.getOrigine() != Origine.Nul)
							|| ca.getType() == Type.DeusEx) {
						tabDefausse.add(ia.mainJoueur.getContenuMain().indexOf(ca));
					}

				}
				break;
			case Crépuscule:
				while (it.hasNext()) {
					CarteAction ca = it.next();
					if ((ca.getOrigine() != Origine.Nuit && ca.getOrigine() != Origine.Nul
							&& ca.getOrigine() != Origine.Néant) || ca.getType() == Type.DeusEx) {
						tabDefausse.add(ia.mainJoueur.getContenuMain().indexOf(ca));
					}
				}
				break;
			}

		} else {
			while (it.hasNext()) {
				CarteAction ca = it.next();
				tabDefausse.add(ia.mainJoueur.getContenuMain().indexOf(ca));

			}
		}
		System.out.println(ia.getNom() + " se defausse de " + tabDefausse.size() + " cartes.");
		// Iterator<Integer> it2 = tabDefausse.iterator();

		if (!tabDefausse.isEmpty()) {
			int i = tabDefausse.size() - 1;
			while (!tabDefausse.isEmpty()) {// && it2.hasNext()) {

				Defausse.getInstance().défausser(ia.getMainJoueur().get(tabDefausse.get(i)));
				ia.getMainJoueur().enleverMain(tabDefausse.get(i));
				tabDefausse.remove(i);
				i--;

			}
		}
	}

	public void voulezVousPiocher(IA ia) {
		ia.getMainJoueur().remplirMain(Partie.getInstance().getPiocheCA());
		System.out.println(
				"Il complète ensuite sa main. Il a " + ia.getMainJoueur().getNbCartes() + " cartes dans sa main.");

	}

	public boolean verifierPointsSansDécrément(IA ia, Carte carte) {
		if ((carte.getOrigine() == cartes.Origine.Nuit) && (ia.pointsNuit > 0)) {
			// ia.pointsNuit--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Jour) && (ia.pointsJour > 0)) {
			// ia.pointsJour--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Néant) && (ia.pointsNeant > 0)) {
			// ia.pointsNeant--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Néant) && (ia.pointsJour > 1)) {
			// ia.pointsJour =
			// ia.pointsJour - 2;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Néant) && (ia.pointsNuit > 1)) {
			// ia.pointsNuit =
			// ia.pointsNuit - 2;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Nul)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verifierPoints(IA ia, Carte carte) {
		if ((carte.getOrigine() == cartes.Origine.Nuit) && (ia.pointsNuit > 0)) {
			ia.pointsNuit--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Jour) && (ia.pointsJour > 0)) {
			ia.pointsJour--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Néant) && (ia.pointsNeant > 0)) {
			ia.pointsNeant--;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Néant) && (ia.pointsJour > 1)) {
			ia.pointsJour = ia.pointsJour - 2;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Néant) && (ia.pointsNuit > 1)) {
			ia.pointsNuit = ia.pointsNuit - 2;
			return true;
		} else if ((carte.getOrigine() == cartes.Origine.Nul)) {
			return true;
		} else {
			return false;
		}

	}

	public void poserApocalypse(IA ia) {
		Iterator<CarteAction> it = ia.getMainJoueur().getContenuMain().iterator();
		CarteAction carte = it.next();
		while (it.hasNext() && carte.getType() != Type.Apocalypse) {
			carte = it.next();

		}
		System.out.println(ia.getNom() + " pose une carte Apocalypse !");
		Defausse.getInstance().défausser(ia.getMainJoueur().get(ia.getMainJoueur().getContenuMain().indexOf(carte)));
		Partie.getInstance().setApocalypseDejaJouee(0);
	}

	public boolean peutPoserApocalypse(IA ia) {
		boolean peutPoserApocalypse = false;
		if (this.aApocalypseDansSaMain(ia)) {
			if (Partie.getInstance().getNombreJoueurs() >= 4) {
				Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
				Joueur joueur1 = it.next();
				int nbPrièresMin = joueur1.getnbPrières();
				int indice1 = Partie.getInstance().getJoueurs().indexOf(joueur1);
				int nbPrièresMin2 = -5;
				int indice2 = -1;
				while (it.hasNext()) {
					Joueur joueur = it.next();
					if (joueur.getnbPrières() <= nbPrièresMin) {
						nbPrièresMin2 = nbPrièresMin;
						indice2 = indice1;
						nbPrièresMin = joueur.getnbPrières();
						indice1 = Partie.getInstance().getJoueurs().indexOf(joueur);
					}

				}
				if (nbPrièresMin != nbPrièresMin2 && (indice1 != Partie.getInstance().getJoueurs().indexOf(this)
						|| indice2 != Partie.getInstance().getJoueurs().indexOf(this))) {
					peutPoserApocalypse = true;
				}
			} else {
				Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
				Joueur joueur1 = it.next();
				int nbPrièresMax = joueur1.getnbPrières();
				int indice1 = Partie.getInstance().getJoueurs().indexOf(joueur1);
				int nbPrièresMax2 = -5;
				int indice2 = -1;
				while (it.hasNext()) {
					Joueur joueur = it.next();
					if (joueur.getnbPrières() >= nbPrièresMax) {
						nbPrièresMax2 = nbPrièresMax;
						indice2 = indice1;
						nbPrièresMax = joueur.getnbPrières();
						indice1 = Partie.getInstance().getJoueurs().indexOf(joueur);
					}

				}
				if (nbPrièresMax != nbPrièresMax2
						&& (indice1 != Partie.getInstance().getJoueurs().indexOf(this)
								|| indice2 != Partie.getInstance().getJoueurs().indexOf(this))
						&& Partie.getInstance().getJoueurs().get(indice1) != ia) {
					peutPoserApocalypse = true;
				}
			}
			return peutPoserApocalypse;
		}
		return peutPoserApocalypse;
	}

	public boolean aApocalypseDansSaMain(IA ia) {
		boolean aApocalypse = false;
		Iterator<CarteAction> it = ia.getMainJoueur().getContenuMain().iterator();
		while (it.hasNext() && !aApocalypse) {
			if (it.next().getType() == Type.Apocalypse) {
				aApocalypse = true;
			}
		}
		return false;
	}

	public void poserGuide(IA ia) {
		boolean peutJouer = false;
		int numCarte = 0;
		CarteAction carteAction = null;
		Iterator<CarteAction> it = ia.getMainJoueur().getContenuMain().iterator();
		while (it.hasNext() && !peutJouer) {
			carteAction = it.next();
			numCarte = ia.getMainJoueur().getContenuMain().indexOf(carteAction);
			if ((verifierPointsSansDécrément(ia, carteAction) && carteAction.getType() == Type.GuideSpirituel)) {
				peutJouer = true;
			}
		}

		if (peutJouer) {
			GuideSpirituel guide = (GuideSpirituel) carteAction;
			verifierPoints(ia, carteAction);
			System.out.println(ia.getNom() + " pose le guide " + guide.getNom() + ".");
			guiderCroyant(ia, (GuideSpirituel) guide, numCarte);
		}

	}

	public boolean peutPoserGuide(IA ia) {
		boolean peutPoserGuide = false;
		boolean peutGuideCroyant = false;
		int numCarte = 0;
		int numCroyant;
		GuideSpirituel guide = null;
		Iterator<CarteAction> it = ia.getMainJoueur().getContenuMain().iterator();
		while (it.hasNext() && peutPoserGuide == false) {
			CarteAction carteAction = it.next();
			numCarte = ia.getMainJoueur().getContenuMain().indexOf(carteAction);
			if (carteAction.getType() == Type.GuideSpirituel) {
				guide = (GuideSpirituel) carteAction;
				peutPoserGuide = verifierPointsSansDécrément(ia, ia.getMainJoueur().getContenuMain().get(numCarte));

			}
		}
		if (peutPoserGuide == true) {
			Iterator<Croyant> it2 = MilieuDeTable.getInstance().getListeCroyants().iterator();
			if (!MilieuDeTable.getInstance().getListeCroyants().isEmpty()) {
				while (it2.hasNext() && !peutGuideCroyant) {
					Croyant croyant = it2.next();
					numCroyant = MilieuDeTable.getInstance().getListeCroyants().indexOf(croyant);
					peutGuideCroyant = peutGuiderCroyant(guide, numCroyant);
				}
			}
		}
		return peutGuideCroyant;
	}

	public void poserCroyant(IA ia) {
		Iterator<CarteAction> it = ia.getMainJoueur().getContenuMain().iterator();
		CarteAction carte = null;
		boolean peutJouer = false;

		while (it.hasNext() && peutJouer == false) {
			carte = it.next();
			if (carte.getType() == Type.Croyant) {
				peutJouer = verifierPoints(ia,
						ia.getMainJoueur().getContenuMain().get(ia.getMainJoueur().getContenuMain().indexOf(carte)));

			}
		}
		if (peutJouer) {
			System.out.println(ia.getNom() + " pose le croyant " + carte.getNom() + " au centre de la table.");
			MilieuDeTable.getInstance().poserCroyantMilieu((Croyant) carte);
			ia.getMainJoueur().enleverMain(ia.getMainJoueur().getContenuMain().indexOf(carte));
			MilieuDeTable.getInstance().afficherMilieu();
		}

	}

	public boolean peutPoserCroyant(IA ia) {
		Iterator<CarteAction> it = ia.getMainJoueur().getContenuMain().iterator();
		CarteAction carte = null;
		boolean peutPoserCroyant = false;

		while (it.hasNext() && peutPoserCroyant == false) {
			carte = it.next();
			if (carte.getType() == Type.Croyant) {
				peutPoserCroyant = verifierPointsSansDécrément(ia, carte);

			}
		}
		return peutPoserCroyant;
	}

	public void utiliserDeusEx(IA ia) {

		Iterator<CarteAction> it = ia.getMainJoueur().getContenuMain().iterator();
		CarteAction carte = null;
		boolean valide = false;
		while (it.hasNext() && !valide) {
			carte = it.next();
			if (carte.getType() == Type.DeusEx) {
				valide = true;
			}
		}
		if (valide == true) {
			ia.getMainJoueur().getContenuMain().get(ia.getMainJoueur().getContenuMain().indexOf(carte)).getCapacité()
					.sacrifier(ia);
			System.out.println(ia.getNom() + " utilise l'effet de la carte DeusEx " + carte.getNom() + ".");
			Defausse.getInstance().défausser(carte);
			ia.getMainJoueur().enleverMain(ia.getMainJoueur().getContenuMain().indexOf(carte));

		}

	}

	public boolean peutPoserDeuxEx(IA ia) {
		boolean peutPoserDeusEx = false;
		Iterator<CarteAction> it = ia.getMainJoueur().getContenuMain().iterator();
		while (it.hasNext() && !peutPoserDeusEx) {
			Carte carte = it.next();
			if (carte.getType() == Type.DeusEx) {
				peutPoserDeusEx = verifierPointsSansDécrément(ia, carte);

			}

		}
		return peutPoserDeusEx;
	}

	public void utiliserDivinité(IA ia) {
		if (!ia.getDivinité().getLaCapaciteEstUtilisee()) {
			System.out.println(ia.getNom() + " active l'effet de sa Divinité " + ia.getDivinité().getNom() + "!!!");
			ia.getDivinité().getCapacité().sacrifier(ia);
			ia.getDivinité().setLaCapaciteEstUtilisee(true);

		}

	}

	public void guiderCroyant(IA ia, GuideSpirituel guide, int numCarte) {
		boolean passe = false;
		boolean possible = true;
		boolean camarche = false;
		ArrayList<Integer> listeNumCroyant = new ArrayList<Integer>();
		boolean compatible = false;
		MilieuDeTable.getInstance().afficherMilieu();
		Iterator<Croyant> it = MilieuDeTable.getInstance().getListeCroyants().iterator();
		while ((listeNumCroyant.size() < guide.getNbCroyantsMax()) && it.hasNext()) {
			Croyant carte = it.next();
			int numCroyant = MilieuDeTable.getInstance().getListeCroyants().indexOf(carte);

			compatible = false;
			if (MilieuDeTable.getInstance().getListeCroyants().get(numCroyant).isPeutEtreRecuperer()) {
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 3; j++) {
						if (guide.getDogme(i) == MilieuDeTable.getInstance().getListeCroyants().get(numCroyant)
								.getDogme(j)) {
							compatible = true;

						}

					}

				}
				if (compatible) {
					listeNumCroyant.add(numCroyant);
					camarche = true;
				}
			}
		}
		if (camarche) {

			for (int i = listeNumCroyant.size() - 1; i > 0; i--) {

				passe = true;
				System.out.println("Le croyant "
						+ MilieuDeTable.getInstance().getListeCroyants().get(listeNumCroyant.get(i)).getNom()
						+ " est guidé !");
				;
				guide.attacherCroyant(
						(Croyant) MilieuDeTable.getInstance().getListeCroyants().get(listeNumCroyant.get(i)));
				ia.setnbPrières(
						MilieuDeTable.getInstance().getListeCroyants().get(listeNumCroyant.get(i)).getNbPrieres());
				MilieuDeTable.getInstance().enleverDuMilieu(
						(Croyant) MilieuDeTable.getInstance().getListeCroyants().get(listeNumCroyant.get(i)));
				listeNumCroyant.remove(i);
				if (MilieuDeTable.getInstance().getListeCroyants().size() == 0) {
					possible = false;
					// } else {
					// int choix = (int) (Math.random());
					// if (choix == 1) {
					// possible = false;
					// }
				}
			}

			// } else {
			// int choix = (int) (Math.random());
			// if (choix == 1) {
			// possible = false;
			// }

		}

		if (passe)

		{
			ia.carteDevant.add((GuideSpirituel) ia.getMainJoueur().getContenuMain().get(numCarte));
			ia.getMainJoueur().enleverMain(numCarte);

		}

	}

	public boolean peutGuiderCroyant(GuideSpirituel guide, int numCarte) {
		boolean compatible = false;
		// MilieuDeTable.getInstance().afficherMilieu();
		Iterator<Croyant> it = MilieuDeTable.getInstance().getListeCroyants().iterator();
		if (MilieuDeTable.getInstance().getListeCroyants().size() != 0) {

			while (compatible == false && it.hasNext()) {
				Croyant carte = it.next();
				int numCroyant = MilieuDeTable.getInstance().getListeCroyants().indexOf(carte);
				if (MilieuDeTable.getInstance().getListeCroyants().get(numCroyant).isPeutEtreRecuperer()) {
					for (int i = 0; i <= 1; i++) {
						for (int j = 0; j <= 2; j++) {

							Dogme dogme1 = guide.getDogme(i);
							Dogme dogme2 = MilieuDeTable.getInstance().getListeCroyants().get(numCroyant).getDogme(j);
							if (dogme1 == dogme2) {
								compatible = true;
							}

						}

					}
				}

			}
		}
		return compatible;
	}

	public void sacrifierGuide(IA ia) {
		if (ia.getNbGuidesSprituels() != 0) {
			if (ia.peutSacrifierGuide) {
				System.out.println(
						ia.getNom() + " sacrifie son guide " + ia.carteDevant.get(0).getNom() + ". L'effet s'active.");
				ia.carteDevant.get(0).getCapacité().sacrifier(ia);
				ia.defausserGuide(0);
			}
		}

	}

	public void sacrifierCroyant(IA ia) {
		if (ia.peutSacrifierCroyant) {
			int numGuide = 0;
			int numCroyant = 0;
			System.out.println(ia.getNom() + " sacrifie son croyant "
					+ ia.getCarteDevant().get(numGuide).getCroyantAttache().get(numCroyant).getNom()
					+ ". L'effet s'active.");
			ia.getCarteDevant().get(numGuide).getCroyantAttache().get(numCroyant).getCapacité().sacrifier(ia);
			ia.defausserCroyant(numGuide, numCroyant);
		}

	}

	public int choisirUnDeSesGuides(IA ia) {
		int indice = -1;
		if (ia.getNbGuidesSprituels() != 0) {
			return 0;
		}

		return indice;
	}

	public int choisirUnDeSesCroyant(IA ia, int guide) {
		int indice = -1;
		boolean valide = false;
		if (guide != -1) {
			int i = 0;
			while (!valide && i < 10) {
				i += 1;
				if (ia.getCarteDevant().get(guide).getCroyantAttache().get(indice).getType() == Type.Croyant) {
					valide = true;
				} else {
					indice += 1;
				}

			}
		}
		return indice;
	}

	public int choisirGuideOuCroyant() {
		int choice = (int) (Math.random());
		boolean valide = false;
		while (!valide) {
			if ((choice == 0) || (choice == 1)) {
				valide = true;
			}

		}
		return choice;
	}

	public int choisirAdversaire(IA ia) {
		boolean choix = false;
		int adversaire = -1;
		Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
		while (it.hasNext() && choix == false) {
			Joueur joueur = it.next();
			if (joueur != ia) {
				choix = true;
				adversaire = joueur.getNumArray();
			}

		}
		if (adversaire != -1 && ia != Partie.getInstance().getJoueurs().get(adversaire)) {
			System.out.println(ia.getNom() + " cible " + Partie.getInstance().getJoueurs().get(adversaire).getNom());

		}
		return adversaire;
	}

	public int choisirAdversaireAvecGuideSprituels(IA ia) {
		int adversaire = -1;
		boolean adversaireDeuxGuides = false;
		Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
		while (it.hasNext() && adversaireDeuxGuides == false) {
			Joueur joueur = it.next();
			if (joueur.getNbGuidesSprituels() > 1) {
				adversaireDeuxGuides = true;
				adversaire = joueur.getNumArray();
			}

		}
		if (adversaire != -1 && ia != Partie.getInstance().getJoueurs().get(adversaire)) {
			System.out.println(ia.getNom() + " cible " + Partie.getInstance().getJoueurs().get(adversaire).getNom());

		} else {
			System.out.println("Il n'y a pas d'adversaires avec au moins 2 guides spirituels");
		}
		return adversaire;
	}

	public int choisirAdversaireAvec2Croyants(IA ia) {

		int adversaire = -1;
		boolean adversaireDeuxGuides = false;
		Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
		while (it.hasNext() && adversaireDeuxGuides == false) {
			Joueur joueur = it.next();
			if (joueur.getNbCroyants() > 1) {
				adversaireDeuxGuides = true;
				adversaire = joueur.getNumArray();
			}

		}
		if (adversaire != -1 && ia != Partie.getInstance().getJoueurs().get(adversaire)) {
			System.out.println(ia.getNom() + " cible " + Partie.getInstance().getJoueurs().get(adversaire).getNom());

		} else {
			System.out.println("Il n'y a pas d'adversaires avec au moins 2 croyants");
		}
		return adversaire;
	}

	public int choisirGuideAdversaire(int adversaire) {
		if (adversaire == -1) {
			return adversaire;
		} else if (Partie.getInstance().getJoueurs().get(adversaire).getNbGuidesSprituels() == 0) {
			return -1;// (int)
		} // (Partie.getInstance().getJoueurs().get(adversaire).getNbGuidesSprituels
			// // * Math.random());
		else
			return 0;
	}

	public int sélectionnerCroyant(int adversaire, int guide) {
		int croyant = -1;
		if (adversaire != -1) {
			croyant = (int) 0;
		}
		return croyant;
	}
	
	public int choisirAutreCroyant(int adversaire, int guide1, int guide2, int croyant){
		int croyant2 = -1;
		if (adversaire != -1 && guide1 != guide2){
			croyant2 = 0;
		}
		else if (adversaire != -1 && Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide1).getNbCroyantsAttachés() > 1){
			croyant2 = 1;
		}
		return croyant2;
	}
}	

