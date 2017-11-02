/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

import cartes.capacit�s.Capacit�;;

/**
 * Description of DeusEx.
 * 
 * @author marti
 */
public class DeusEx extends CarteAction {

	/**
	 * The constructor.
	 */
	public DeusEx(int identifiant, Type type, String nom, Origine origine, String effet, Capacit� capacit�) {
		super(identifiant, type, nom, origine, capacit�);
		this.effet = effet;
	}

	public String toString() {
		return (" \n| " + "Type : " + this.getType() + " | Nom : " + this.getNom() + " | Origine : " + this.getOrigine()
				+ " \nCapacit� : " + this.getEffet());
	}

}
