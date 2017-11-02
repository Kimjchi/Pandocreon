/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

//import java.util.Collections;

import cartes.capacit�s.Capacit�;

/**
 * La classe Apocalypse g�re les cartes Apocalypse et la m�thode
 * activerApocalypse qui permet d'�liminer un joueur et de d�clarer un gagnant
 * en fonction du nombre de joueurs restants.
 * 
 * @author martin
 */
public class Apocalypse extends CarteAction {

	public Apocalypse(int identifiant, Type type, String nom, Origine origine, Capacit� capacit�) {
		super(identifiant, type, nom, origine, capacit�);
		this.origine = origine;
	}

	/**
	 * On distingue deux cas : il y a deux ou trois joueurs dans la partie et il
	 * y en a plus. Dans le premier cas, on parcours la liste du nombre du
	 * nombre de pri�res de chaque joueur et si un joueur en a plus que les
	 * autres, il gagne. Dans le second cas, si un joueurs a moins de points que
	 * les autres, il est �limin�. Il ne faut pas n�gliger les cas o� deux
	 * joueurs ont le plus ou le moins de pri�res. Dans ces cas, l'effet de la
	 * carte est nul.
	 */

	public String toString() {
		return (" \n| Nom : " + this.getNom() + " | Origine : " + this.getOrigine());
	}
}
