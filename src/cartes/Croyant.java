/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

package cartes;

//import java.util.HashSet;
import cartes.capacit�s.Capacit�;

/**
 * Description of Croyant.
 * 
 * @author marti
 */
public class Croyant extends CarteAction {

	/**
	 * Nombres de pri�res associ�es au croyant.
	 */
	private Integer nbPrieres;
	private boolean peutEtreRecuperer;
	private GuideSpirituel guidePropri�taire;

	/**
	 * Constructeur d'un croyant.
	 */
	public Croyant(int identifiant, Type type, String nom, Origine origine, Dogme dogme1, Dogme dogme2, Dogme dogme3,
			int pri�res, String effet, Capacit� capacit�) {
		super(identifiant, type, nom, origine, capacit�);
		this.dogme[0] = dogme1;
		this.dogme[1] = dogme2;
		this.dogme[2] = dogme3;
		this.guidePropri�taire = null;
		this.effet = effet;
		this.nbPrieres = pri�res;
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
	
	public GuideSpirituel getGuidePropri�taire() {
		return this.guidePropri�taire;
	}
	
	public void setGuidePropri�taire(GuideSpirituel guide) {
		this.guidePropri�taire = guide;
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
				+ "\nNombre de pri�res : " + this.getNbPrieres() + " \nCapacit� : " + this.getEffet());
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
