/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

package cartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Iterator;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of PileDivinit�s.
 * 
 * @author marti
 */
/**
 * Repr�sente un deck de carte de type E
 *
 * @param <E>
 *            le type de cartes g�r�es par le deck (sera Divinit�s ou
 *            CarteAllies suivant le deck voulu)
 */

@SuppressWarnings("hiding")
public class PileDivinit�s<Divinit�> {

	/**
	 * Le tas de carte.
	 */
	private Queue<Divinit�> deck;

	/**
	 * Toutes les cartes g�r�es par le deck.
	 */
	private List<Divinit�> cartes;

	/**
	 * Construit un nouveau deck.
	 */
	public PileDivinit�s() {
		this.deck = new LinkedList<Divinit�>();
		this.cartes = new ArrayList<Divinit�>();
	}

	/**
	 * R�cup�re une carte du tas qui est ensuite retir�e du tas.
	 */
	public Divinit� piocherCarte() {
		return this.deck.remove();
	}

	/**
	 * Ajoute des cartes au tas.
	 * 
	 * @param cartes
	 *            une collection de cartes � ajouter Note : Attention, il faut
	 *            bien remelanger les cartes apr�s car cela n'est pas fait
	 *            automatiquement (les cartes ne serait alors pas prises en
	 *            compte pour la distribution).
	 */

	public void ajouterCartes(Collection<Divinit�> cartes) {
		this.cartes.addAll(cartes);
	}

	public void remettreCartesEtm�langer() {
		this.deck.clear();

		// On cr�e un tableau qui contient les index de toutes les cartes dans
		// "cartes"
		// On m�lange ce tableau d'indice, et on ajoute les cartes point�es par
		// les index
		// dans la "file" (tas) de carte.
		ArrayList<Integer> cartesAMelanger = new ArrayList<Integer>(cartes.size());
		for (int i = 0; i < this.cartes.size(); i++) {
			cartesAMelanger.add(i);
		}

		Collections.shuffle(cartesAMelanger);

		for (Iterator<Integer> it = cartesAMelanger.iterator(); it.hasNext();) {
			this.deck.add(this.cartes.get(it.next().intValue()));
		}
	}
}
