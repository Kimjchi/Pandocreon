/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

import java.util.ArrayList;
import java.util.Iterator;
import cartes.capacit�s.Capacit�;
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
	 * Description of the property nbCroyantsAttachés.
	 */
	private int nbCroyantsAttach�s;

	private ArrayList<Croyant> croyantAttache;

	private boolean protege;

	/**
	 * The constructor.
	 */
	public GuideSpirituel(int identifiant, Type type, String nom, Origine origine, Dogme dogme1, Dogme dogme2,
			int nbCroyantsMax, String effet, Capacit� capacit�) {
		super(identifiant, type, nom, origine, capacit�);
		this.dogme[0] = dogme1;
		this.dogme[1] = dogme2;
		this.effet = effet;
		this.nbCroyantsMax = nbCroyantsMax;
		this.nbCroyantsAttach�s = 0;
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
	 * Returns nbCroyantsAttach�s.
	 * 
	 * @return nbCroyantsAttach�s
	 */
	public int getNbCroyantsAttach�s() {
		this.nbCroyantsAttach�s = this.croyantAttache.size();
		return this.nbCroyantsAttach�s;
	}

	public ArrayList<Croyant> getCroyantAttache() {
		return croyantAttache;
	}

	public void setCroyantAttache(ArrayList<Croyant> croyantAttache) {
		this.croyantAttache = croyantAttache;
	}

	public void attacherCroyant(Croyant croyant) {
		this.croyantAttache.add(croyant);
		croyant.setGuidePropri�taire(this);
	}

	@Override
	public String toString() {
		return (" \n| " + "Type : " + this.getType() + " | Nom : " + this.getNom() + " | Origine : " + this.getOrigine()
				+ " | Dogmes : " + this.getDogme(0) + ", " + this.getDogme(1) + "\nNombre de croyant max attachables : "
				+ this.getNbCroyantsMax() + " | Nombre de croyants attach�s : " + this.getNbCroyantsAttach�s()
				+ "\nCroyants attach�s au guide : " + this.getCroyantAttache() + "\nCapacit� de sacrifice : "
				+ this.getEffet());
	}

	public void afficherCroyantsAttach�s() {
		for (Iterator<Croyant> it = this.croyantAttache.iterator(); it.hasNext();) {
			int i = 1;
			Croyant croyant = it.next();
			System.out.println("Carte attach�e n�" + i);
			System.out.print(croyant);
			i++;
		}

	}

	public boolean verificationCroyant() {

		if (this.getNbCroyantsAttach�s() == 0) {
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
