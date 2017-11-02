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
 * Description of PileDivinités.
 * 
 * @author marti
 */
/**
 * Représente un deck de carte de type E
 *
 * @param <E>
 *            le type de cartes gérées par le deck (sera Divinités ou
 *            CarteAllies suivant le deck voulu)
 */

@SuppressWarnings("hiding")
public class PileDivinités<Divinité> {

	/**
	 * Le tas de carte.
	 */
	private Queue<Divinité> deck;

	/**
	 * Toutes les cartes gérées par le deck.
	 */
	private List<Divinité> cartes;

	/**
	 * Construit un nouveau deck.
	 */
	public PileDivinités() {
		this.deck = new LinkedList<Divinité>();
		this.cartes = new ArrayList<Divinité>();
	}

	/**
	 * Récupère une carte du tas qui est ensuite retirée du tas.
	 */
	public Divinité piocherCarte() {
		return this.deck.remove();
	}

	/**
	 * Ajoute des cartes au tas.
	 * 
	 * @param cartes
	 *            une collection de cartes à ajouter Note : Attention, il faut
	 *            bien remelanger les cartes après car cela n'est pas fait
	 *            automatiquement (les cartes ne serait alors pas prises en
	 *            compte pour la distribution).
	 */

	public void ajouterCartes(Collection<Divinité> cartes) {
		this.cartes.addAll(cartes);
	}

	public void remettreCartesEtmélanger() {
		this.deck.clear();

		// On crée un tableau qui contient les index de toutes les cartes dans
		// "cartes"
		// On mélange ce tableau d'indice, et on ajoute les cartes pointées par
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
