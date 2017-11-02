/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

import cartes.capacités.Capacité;;

/**
 * Description of DeusEx.
 * 
 * @author marti
 */
public class DeusEx extends CarteAction {

	/**
	 * The constructor.
	 */
	public DeusEx(int identifiant, Type type, String nom, Origine origine, String effet, Capacité capacité) {
		super(identifiant, type, nom, origine, capacité);
		this.effet = effet;
	}

	public String toString() {
		return (" \n| " + "Type : " + this.getType() + " | Nom : " + this.getNom() + " | Origine : " + this.getOrigine()
				+ " \nCapacité : " + this.getEffet());
	}

}
