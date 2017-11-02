/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

import java.util.ArrayList;
import java.util.Iterator;
import cartes.capacités.Capacité;
// End of user code

/**
 * Description of GuideSpirituel.
 * 
 * @author marti
 */
public class GuideSpirituel extends CarteAction {
	/**
	 * Description of the property nbCroyantsMax.
	 */
	private int nbCroyantsMax;

	/**
	 * Description of the property nbCroyantsAttachÃ©s.
	 */
	private int nbCroyantsAttachés;

	private ArrayList<Croyant> croyantAttache;

	private boolean protege;

	/**
	 * The constructor.
	 */
	public GuideSpirituel(int identifiant, Type type, String nom, Origine origine, Dogme dogme1, Dogme dogme2,
			int nbCroyantsMax, String effet, Capacité capacité) {
		super(identifiant, type, nom, origine, capacité);
		this.dogme[0] = dogme1;
		this.dogme[1] = dogme2;
		this.effet = effet;
		this.nbCroyantsMax = nbCroyantsMax;
		this.nbCroyantsAttachés = 0;
		this.croyantAttache = new ArrayList<Croyant>();
		this.protege = false;
	}

	/**
	 * Description of the method guiderCroyant.
	 */

	/**
	 * Returns nbCroyantsMax.
	 * 
	 * @return nbCroyantsMax
	 */
	public int getNbCroyantsMax() {
		return this.nbCroyantsMax;
	}

	public Dogme getDogme(int i) {
		return this.dogme[i];
	}

	/**
	 * Returns capaciteSpeciale.
	 * 
	 * @return capaciteSpeciale
	 */
	public String getEffet() {
		return this.effet;
	}

	/**
	 * Returns nbCroyantsAttachés.
	 * 
	 * @return nbCroyantsAttachés
	 */
	public int getNbCroyantsAttachés() {
		this.nbCroyantsAttachés = this.croyantAttache.size();
		return this.nbCroyantsAttachés;
	}

	public ArrayList<Croyant> getCroyantAttache() {
		return croyantAttache;
	}

	public void setCroyantAttache(ArrayList<Croyant> croyantAttache) {
		this.croyantAttache = croyantAttache;
	}

	public void attacherCroyant(Croyant croyant) {
		this.croyantAttache.add(croyant);
		croyant.setGuidePropriétaire(this);
	}

	@Override
	public String toString() {
		return (" \n| " + "Type : " + this.getType() + " | Nom : " + this.getNom() + " | Origine : " + this.getOrigine()
				+ " | Dogmes : " + this.getDogme(0) + ", " + this.getDogme(1) + "\nNombre de croyant max attachables : "
				+ this.getNbCroyantsMax() + " | Nombre de croyants attachés : " + this.getNbCroyantsAttachés()
				+ "\nCroyants attachés au guide : " + this.getCroyantAttache() + "\nCapacité de sacrifice : "
				+ this.getEffet());
	}

	public void afficherCroyantsAttachés() {
		for (Iterator<Croyant> it = this.croyantAttache.iterator(); it.hasNext();) {
			int i = 1;
			Croyant croyant = it.next();
			System.out.println("Carte attachée n°" + i);
			System.out.print(croyant);
			i++;
		}

	}

	public boolean verificationCroyant() {

		if (this.getNbCroyantsAttachés() == 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verifierDogme(cartes.Dogme dogme) {
		boolean dogmeOk = false;
		for (int i = 0; i < 2; i++) {
			if (dogme == this.getDogme(i)) {
				dogmeOk = true;
			}
		}
		return dogmeOk;
	}

	public boolean isProtege() {
		return protege;
	}

	public void setProtege(boolean protege) {
		this.protege = protege;
	}

}
