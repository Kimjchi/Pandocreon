/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package joueurs;

import cartes.Carte;
import cartes.Origine;
import cartes.CarteAction;
import cartes.GuideSpirituel;
import cartes.Pioche;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of IA.
 * 
 * @author marti
 */
public class IA extends Joueur {

	private Strategie niveauDeDifficult�;

	public IA(int numTab, Pioche<CarteAction> pioche, String nom, int niveauDeDifficult�) {
		super(numTab, pioche, nom);
		if (niveauDeDifficult� == 1) {
			this.niveauDeDifficult� = new StrategieFacile();
		} else if (niveauDeDifficult� == 2) {
			this.niveauDeDifficult� = new StrategieMoyen();
		}
		this.estR�el = false;

	}

	public int s�l�ctionnerCosmogonie() {
		Origine origine = this.getDivinit�().getOrigine();
		int choix = 1;
		switch (origine) {
		case Jour:
			choix = 1;
			break;
		case Nuit:
			choix = 2;
			break;
		default:
			choix = 3;
			break;
		}
		return choix;
	}

	public void gagnerPointsAction(int resultat) {
		if (this.peutGagnerPointAction) {
			switch (resultat) {
			case 1:

				if (this.getDivinit�().getOrigine() == Origine.Jour) {
					this.setPointsJour(2);
				} else if (this.getDivinit�().getOrigine() == Origine.Aube) {
					this.setPointsJour(1);
				}
				break;
			case 2:

				if (this.getDivinit�().getOrigine() == Origine.Nuit) {
					this.setPointsNuit(2);
				} else if (this.getDivinit�().getOrigine() == Origine.Cr�puscule) {
					this.setPointsNuit(1);
				}
				break;
			case 3:

				if (this.getDivinit�().getOrigine() == Origine.Aube) {
					this.setPointsNeant(1);
				} else if (this.getDivinit�().getOrigine() == Origine.Cr�puscule) {
					this.setPointsNeant(1);
				}
			}

		}

	}

	public int s�lectionnerAction() {
		return this.niveauDeDifficult�.s�lectionnerAction(this);
	}

	public void voulezVousDefausser() {
		this.niveauDeDifficult�.voulezVousDefausser(this);
	}

	public void voulezVousPiocher() {
		this.niveauDeDifficult�.voulezVousPiocher(this);
	}

	public boolean verifierPoints(Carte carte) {
		return this.niveauDeDifficult�.verifierPoints(this, carte);

	}

	public void poserApocalypse() {
		this.niveauDeDifficult�.poserApocalypse(this);
	}

	public boolean peutPoserApocalypse() {
		return this.niveauDeDifficult�.peutPoserApocalypse(this);
	}

	public boolean aApocalypseDansSaMain() {
		return this.niveauDeDifficult�.aApocalypseDansSaMain(this);
	}

	public void poserGuide() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficult�.poserGuide(this);
	}

	public boolean peutPoserGuide() {
		return this.niveauDeDifficult�.peutPoserGuide(this);
	}

	public boolean peutGuiderCroyant(GuideSpirituel guide, int numCarte) {
		return this.niveauDeDifficult�.peutGuiderCroyant(guide, numCarte);
	}

	public void poserCroyant() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficult�.poserCroyant(this);
	}

	public boolean peutPoserCroyant() {
		return this.niveauDeDifficult�.peutPoserCroyant(this);
	}

	public void utiliserDeusEx() {
		this.niveauDeDifficult�.utiliserDeusEx(this);
	}

	public boolean peutPoserDeuxEx(IA this) {
		return this.niveauDeDifficult�.peutPoserDeuxEx(this);
	}

	public void utiliserDivinit�() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficult�.utiliserDivinit�(this);
	}

	public void guiderCroyant(GuideSpirituel guide, int numCarte) {
		this.niveauDeDifficult�.guiderCroyant(this, guide, numCarte);
	}

	public void sacrifierGuide() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficult�.sacrifierGuide(this);
	}

	public void sacrifierCroyant() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficult�.sacrifierCroyant(this);
	}

	public int choisirUnDeSesGuides() {
		return this.niveauDeDifficult�.choisirUnDeSesGuides(this);
	}

	public int choisirUnDeSesCroyant(int guide) {
		return this.niveauDeDifficult�.choisirUnDeSesCroyant(this, guide);
	}

	public int choisirGuideOuCroyant() {
		return this.niveauDeDifficult�.choisirGuideOuCroyant();
	}

	public int choisirAdversaire() {
		return this.niveauDeDifficult�.choisirAdversaire(this);
	}

	public int choisirAdversaireAvecGuideSprituels() {
		return this.niveauDeDifficult�.choisirAdversaireAvecGuideSprituels(this);
	}
	
	public int choisirAdversaireAvec2Croyants(){
		return this.niveauDeDifficult�.choisirAdversaireAvec2Croyants(this);
	}
	
	public int choisirAutreCroyant(int adversaire, int guide1, int guide2, int croyant1){
		return this.niveauDeDifficult�.choisirAutreCroyant(adversaire, guide1, guide2, croyant1);
	}

	public int choisirGuideAdversaire(int adversaire) {
		return this.niveauDeDifficult�.choisirGuideAdversaire(adversaire);
	}

	public int s�lectionnerCroyant(int adversaire, int guide) {
		return this.niveauDeDifficult�.s�lectionnerCroyant(adversaire, guide);
	}
	
	

}
/**
 * Description of the property niveauDeDifficulté.
 */
// public HashSet<Integer> niveauDeDifficult� = new HashSet<Integer>();

// Start of user code (user defined attributes for IA)

// End of user code

/**
 * The constructor.
 */
// public IA() {
// Start of user code constructor for IA)
// super();
// End of user code

/**
 * Description of the method jouerFacile.
 */
// public void jouerFacile() {
// Start of user code for method jouerFacile
// End of user code
// }

/**
 * Description of the method jouerIntermédiaire.
 */
// public void jouerInterm�diaire() {
// Start of user code for method jouerIntermédiaire
// End of user code

/**
 * Description of the method jouerDifficile.
 */
// public void jouerDifficile() {
// Start of user code for method jouerDifficile
// End of user code

// Start of user code (user defined methods for IA)

// End of user code
/**
 * Returns niveauDeDifficulté.
 * 
 * @return niveauDeDifficulté
 */
// public HashSet<Integer> getNiveauDeDifficult�() {
// return this.niveauDeDifficult�;
// }
