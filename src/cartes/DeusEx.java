/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

import cartes.capacitÚs.CapacitÚ;;

/**
 * Description of DeusEx.
 * 
 * @author marti
 */
public class DeusEx extends CarteAction {

	/**
	 * The constructor.
	 */
	public DeusEx(int identifiant, Type type, String nom, Origine origine, String effet, CapacitÚ capacitÚ) {
		super(identifiant, type, nom, origine, capacitÚ);
		this.effet = effet;
	}

	public String toString() {
		return (" \n| " + "Type : " + this.getType() + " | Nom : " + this.getNom() + " | Origine : " + this.getOrigine()
				+ " \nCapacitÚ : " + this.getEffet());
	}

}
