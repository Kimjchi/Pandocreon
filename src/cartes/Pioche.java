/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

package cartes;

import java.util.ArrayList;

import cartes.CarteAction;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

@SuppressWarnings("hiding")
public class Pioche<CarteAction> {

	/**
	 * Le tas de carte.
	 */
	private Queue<CarteAction> deck;

	/**
	 * Toutes les cartes gérées par le deck.
	 */
	private List<CarteAction> cartes;

	/**
	 * Construit un nouveau deck.
	 */
	public Pioche() {
		this.deck = new LinkedList<CarteAction>();
		this.cartes = new ArrayList<CarteAction>();
	}

	@SuppressWarnings("unchecked")
	public void prendreDefausse() {
		if (this.deck.size() == 0) {
			this.ajouterCartes((ArrayList<CarteAction>) Defausse.getInstance().getDefausse());
			this.remettreCartesEtmélanger();
		}
	}

	/**
	 * Récupère une carte du tas qui est ensuite retirée du tas.
	 * 
	 * @return la carte récupérée du tas
	 */
	public CarteAction piocherCarte() {
		CarteAction cartePiochée = this.deck.remove();
		((cartes.CarteAction) cartePiochée).setPosition("Main");
		return cartePiochée;
	}

	/**
	 * Ajoute des cartes au tas.
	 * 
	 * @param cartes
	 *            une collection de cartes à ajouter Note : Attention, il faut
	 *            bien mélanger les cartes à nouveaux après car cela n'est pas
	 *            fait automatiquement (les cartes ne seraient alors pas prises
	 *            en compte pour la distribution).
	 */

	public void ajouterCartes(ArrayList<CarteAction> cartes) {
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

	public Queue<CarteAction> getDeck() {
		return deck;
	}

	public void setDeck(Queue<CarteAction> deck) {
		this.deck = deck;
	}

	public List<CarteAction> getCartes() {
		return cartes;
	}

	public void setCartes(List<CarteAction> cartes) {
		this.cartes = cartes;
	}

}
