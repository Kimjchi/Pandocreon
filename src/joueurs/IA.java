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

	private Strategie niveauDeDifficulté;

	public IA(int numTab, Pioche<CarteAction> pioche, String nom, int niveauDeDifficulté) {
		super(numTab, pioche, nom);
		if (niveauDeDifficulté == 1) {
			this.niveauDeDifficulté = new StrategieFacile();
		} else if (niveauDeDifficulté == 2) {
			this.niveauDeDifficulté = new StrategieMoyen();
		}
		this.estRéel = false;

	}

	public int séléctionnerCosmogonie() {
		Origine origine = this.getDivinité().getOrigine();
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

				if (this.getDivinité().getOrigine() == Origine.Jour) {
					this.setPointsJour(2);
				} else if (this.getDivinité().getOrigine() == Origine.Aube) {
					this.setPointsJour(1);
				}
				break;
			case 2:

				if (this.getDivinité().getOrigine() == Origine.Nuit) {
					this.setPointsNuit(2);
				} else if (this.getDivinité().getOrigine() == Origine.Crépuscule) {
					this.setPointsNuit(1);
				}
				break;
			case 3:

				if (this.getDivinité().getOrigine() == Origine.Aube) {
					this.setPointsNeant(1);
				} else if (this.getDivinité().getOrigine() == Origine.Crépuscule) {
					this.setPointsNeant(1);
				}
			}

		}

	}

	public int sélectionnerAction() {
		return this.niveauDeDifficulté.sélectionnerAction(this);
	}

	public void voulezVousDefausser() {
		this.niveauDeDifficulté.voulezVousDefausser(this);
	}

	public void voulezVousPiocher() {
		this.niveauDeDifficulté.voulezVousPiocher(this);
	}

	public boolean verifierPoints(Carte carte) {
		return this.niveauDeDifficulté.verifierPoints(this, carte);

	}

	public void poserApocalypse() {
		this.niveauDeDifficulté.poserApocalypse(this);
	}

	public boolean peutPoserApocalypse() {
		return this.niveauDeDifficulté.peutPoserApocalypse(this);
	}

	public boolean aApocalypseDansSaMain() {
		return this.niveauDeDifficulté.aApocalypseDansSaMain(this);
	}

	public void poserGuide() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficulté.poserGuide(this);
	}

	public boolean peutPoserGuide() {
		return this.niveauDeDifficulté.peutPoserGuide(this);
	}

	public boolean peutGuiderCroyant(GuideSpirituel guide, int numCarte) {
		return this.niveauDeDifficulté.peutGuiderCroyant(guide, numCarte);
	}

	public void poserCroyant() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficulté.poserCroyant(this);
	}

	public boolean peutPoserCroyant() {
		return this.niveauDeDifficulté.peutPoserCroyant(this);
	}

	public void utiliserDeusEx() {
		this.niveauDeDifficulté.utiliserDeusEx(this);
	}

	public boolean peutPoserDeuxEx(IA this) {
		return this.niveauDeDifficulté.peutPoserDeuxEx(this);
	}

	public void utiliserDivinité() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficulté.utiliserDivinité(this);
	}

	public void guiderCroyant(GuideSpirituel guide, int numCarte) {
		this.niveauDeDifficulté.guiderCroyant(this, guide, numCarte);
	}

	public void sacrifierGuide() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficulté.sacrifierGuide(this);
	}

	public void sacrifierCroyant() {
		this.setChanged();
		this.notifyObservers();
		this.niveauDeDifficulté.sacrifierCroyant(this);
	}

	public int choisirUnDeSesGuides() {
		return this.niveauDeDifficulté.choisirUnDeSesGuides(this);
	}

	public int choisirUnDeSesCroyant(int guide) {
		return this.niveauDeDifficulté.choisirUnDeSesCroyant(this, guide);
	}

	public int choisirGuideOuCroyant() {
		return this.niveauDeDifficulté.choisirGuideOuCroyant();
	}

	public int choisirAdversaire() {
		return this.niveauDeDifficulté.choisirAdversaire(this);
	}

	public int choisirAdversaireAvecGuideSprituels() {
		return this.niveauDeDifficulté.choisirAdversaireAvecGuideSprituels(this);
	}
	
	public int choisirAdversaireAvec2Croyants(){
		return this.niveauDeDifficulté.choisirAdversaireAvec2Croyants(this);
	}
	
	public int choisirAutreCroyant(int adversaire, int guide1, int guide2, int croyant1){
		return this.niveauDeDifficulté.choisirAutreCroyant(adversaire, guide1, guide2, croyant1);
	}

	public int choisirGuideAdversaire(int adversaire) {
		return this.niveauDeDifficulté.choisirGuideAdversaire(adversaire);
	}

	public int sélectionnerCroyant(int adversaire, int guide) {
		return this.niveauDeDifficulté.sélectionnerCroyant(adversaire, guide);
	}
	
	

}
/**
 * Description of the property niveauDeDifficultÃ©.
 */
// public HashSet<Integer> niveauDeDifficulté = new HashSet<Integer>();

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
 * Description of the method jouerIntermÃ©diaire.
 */
// public void jouerIntermédiaire() {
// Start of user code for method jouerIntermÃ©diaire
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
 * Returns niveauDeDifficultÃ©.
 * 
 * @return niveauDeDifficultÃ©
 */
// public HashSet<Integer> getNiveauDeDifficulté() {
// return this.niveauDeDifficulté;
// }
