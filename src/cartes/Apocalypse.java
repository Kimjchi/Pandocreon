/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

//import java.util.Collections;

import cartes.capacités.Capacité;

/**
 * La classe Apocalypse gère les cartes Apocalypse et la méthode
 * activerApocalypse qui permet d'éliminer un joueur et de déclarer un gagnant
 * en fonction du nombre de joueurs restants.
 * 
 * @author martin
 */
public class Apocalypse extends CarteAction {

	public Apocalypse(int identifiant, Type type, String nom, Origine origine, Capacité capacité) {
		super(identifiant, type, nom, origine, capacité);
		this.origine = origine;
	}

	/**
	 * On distingue deux cas : il y a deux ou trois joueurs dans la partie et il
	 * y en a plus. Dans le premier cas, on parcours la liste du nombre du
	 * nombre de prières de chaque joueur et si un joueur en a plus que les
	 * autres, il gagne. Dans le second cas, si un joueurs a moins de points que
	 * les autres, il est éliminé. Il ne faut pas négliger les cas où deux
	 * joueurs ont le plus ou le moins de prières. Dans ces cas, l'effet de la
	 * carte est nul.
	 */

	public String toString() {
		return (" \n| Nom : " + this.getNom() + " | Origine : " + this.getOrigine());
	}
}
