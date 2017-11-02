/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

package cartes;

//import java.util.HashSet;
import cartes.capacités.Capacité;

/**
 * Description of Croyant.
 * 
 * @author marti
 */
public class Croyant extends CarteAction {

	/**
	 * Nombres de prières associées au croyant.
	 */
	private Integer nbPrieres;
	private boolean peutEtreRecuperer;
	private GuideSpirituel guidePropriétaire;

	/**
	 * Constructeur d'un croyant.
	 */
	public Croyant(int identifiant, Type type, String nom, Origine origine, Dogme dogme1, Dogme dogme2, Dogme dogme3,
			int prières, String effet, Capacité capacité) {
		super(identifiant, type, nom, origine, capacité);
		this.dogme[0] = dogme1;
		this.dogme[1] = dogme2;
		this.dogme[2] = dogme3;
		this.guidePropriétaire = null;
		this.effet = effet;
		this.nbPrieres = prières;
		this.peutEtreRecuperer = false;
	}

	/**
	 * Description of the method placerAuCentre.
	 */
	public void placerAuCentre() {
		// Start of user code for method placerAuCentre
		// End of user code
	}

	public Dogme getDogme(int i) {
		return this.dogme[i];
	}
	
	public GuideSpirituel getGuidePropriétaire() {
		return this.guidePropriétaire;
	}
	
	public void setGuidePropriétaire(GuideSpirituel guide) {
		this.guidePropriétaire = guide;
	}

	/**
	 * Returns nbPrieres.
	 * 
	 * @return nbPrieres
	 */
	public int getNbPrieres() {
		return this.nbPrieres;
	}

	/**
	 * Returns capaciteSpeciale.
	 * 
	 * @return effet
	 */
	public String getCapaciteSpeciale() {
		return this.effet;
	}

	public boolean isPeutEtreRecuperer() {
		return peutEtreRecuperer;
	}

	public void setPeutEtreRecuperer(boolean peutEtreRecuperer) {
		this.peutEtreRecuperer = peutEtreRecuperer;
	}

	public String toString() {
		return (" \n| " + "Type : " + this.getType() + " | Nom : " + this.getNom() + " | Origine : " + this.getOrigine()
				+ " | Dogmes : " + this.getDogme(0) + ", " + this.getDogme(1) + ", " + this.getDogme(2)
				+ "\nNombre de prières : " + this.getNbPrieres() + " \nCapacité : " + this.getEffet());
	}

	public boolean verifierDogme(cartes.Dogme dogme) {
		boolean dogmeOk = false;
		for (int i = 0; i < 3; i++) {
			if (dogme == this.getDogme(i)) {
				dogmeOk = true;
			}
		}
		return dogmeOk;
	}

}
