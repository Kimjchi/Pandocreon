/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

import cartes.capacités.Capacité;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of DivinitÃ©.
 * 
 * @author marti
 */
public class Divinité extends Carte {
	/**
	 * Description of the property laCapaciteEstUtilisee.
	 */
	private boolean laCapaciteEstUtilisee = false;

	/**
	 * Description of the property .
	 */

	/**
	 * Description of the property capacite.
	 */
	private String capacite = "";

	// Start of user code (user defined attributes for DivinitÃ©)

	// End of user code

	private String histoire = "";

	/**
	 * The constructor.
	 */
	public Divinité(int identifiant, Type type, String nom, Origine origine, Dogme dogme1, Dogme dogme2, Dogme dogme3,
			String histoire, String capacite, Capacité capacité) {
		// Start of user code constructor for DivinitÃ©)
		super(identifiant, type, nom, origine, capacité);
		this.dogme[0] = dogme1;
		this.dogme[1] = dogme2;
		this.dogme[2] = dogme3;
		this.histoire = histoire;
		this.capacite = capacite;
		// End of user code
	}

	/**
	 * Description of the method utiliserCapacitÃ©.
	 */
	public void utiliserCapacité() {
		// Start of user code for method utiliserCapacitÃ©

		setLaCapaciteEstUtilisee(true);
		// End of user code
	}

	// Start of user code (user defined methods for DivinitÃ©)

	// End of user code
	/**
	 * Returns laCapaciteEstUtilisee.
	 * 
	 * @return laCapaciteEstUtilisee
	 */
	public boolean getLaCapaciteEstUtilisee() {
		return this.laCapaciteEstUtilisee;
	}

	/**
	 * Sets a value to attribute laCapaciteEstUtilisee.
	 * 
	 * @param newLaCapaciteEstUtilisee
	 */
	public void setLaCapaciteEstUtilisee(boolean newLaCapaciteEstUtilisee) {
		this.laCapaciteEstUtilisee = newLaCapaciteEstUtilisee;
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

	/**
	 * Returns capacite.
	 * 
	 * @return capacite
	 */
	public String getCapacite() {
		return this.capacite;
	}

	/**
	 * Sets a value to attribute capacite.
	 * 
	 * @param newCapacite
	 */
	public void setCapacite(String newCapacite) {
		this.capacite = newCapacite;
	}

	public String getHistoire() {
		return histoire;
	}

	public void setHistoire(String histoire) {
		this.histoire = histoire;
	}

	/*
	 * public void afficherDivinite(){ System.out.println("  ~ ID :" +
	 * this.getId() + ", " + this.getNom() +", type " + this.getType() +
	 * ", origine " + this.getOrigine() + ", dogmes " + this.getDogme(0) + ", "+
	 * this.getDogme(1) + " et "+ this.getDogme(2) + ".\n     " +
	 * this.getHistoire() + "\n     " + this.getCapacite()); }
	 */

	@Override
	public String toString() {
		return (this.getNom() + " | Origine : " + this.getOrigine() + " | Dogmes : " + this.getDogme(0) + ", "
				+ this.getDogme(1) + ", " + this.getDogme(2) + "\n" + "Description : " + this.getHistoire() + "\n"
				+ "Capacité : " + this.getCapacite());
	}

}
