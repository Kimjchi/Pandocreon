/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

package cartes;
// Start of user code (user defined imports)

// End of user code
import cartes.capacités.Capacité;
import joueurs.Joueur;

/**
 * Description of CarteAction.
 * 
 * @author marti
 */
public class CarteAction extends Carte {

	/**
	 * Indique la position d'une carte action (enumeration Main, terrainJoeur ou
	 * Centre).
	 */
	protected Position position;

	/**
	 * Indique l'effet du croyant sous forme d'une string. Permet au joueur
	 * d'avoir des informations sur la carte.
	 */
	protected String effet;

	/**
	 * Constructeur d'une carte action, surcharge du constructeur de carte.
	 */
	public CarteAction(int identifiant, Type type, String nom, Origine origine, Capacité capacité) {
		super(identifiant, type, nom, origine, capacité);
		setPosition(Position.Pioche);
	}

	public void afficherCarteAction(int numMain) {
		numMain++;
		System.out.println("Carte n°" + numMain + "  ~ ID :" + this.getId() + ", " + this.getNom() + ", type "
				+ this.getType() + ", origine " + this.getOrigine() + ", dogmes " + this.getDogme(0) + ", "
				+ this.getDogme(1) + " et " + this.getDogme(2) + ".\n     " + this.getEffet());
	}

	/**
	 * Description of the method sacrifier.
	 */

	// Start of user code (user defined methods for CarteAction)

	// End of user code
	/**
	 * Returns position.
	 * 
	 * @return position
	 */
	public Position getPosition() {
		this.notifyObservers();
		return this.position;
		
	}

	/**
	 * Sets a value to attribute position.
	 * 
	 * @param newPosition
	 */
	public void setPosition(String position) {
		this.position = Position.valueOf(position);
		this.notifyObservers();
	}
	
	public void setPosition(Position position) {
		this.position = position;
		this.notifyObservers();
	}

	public String getEffet() {
		return this.effet;
	}
	
	public boolean estJouable(Joueur j) {
		if ((this.getOrigine() == cartes.Origine.Nuit) && (j.getPointsNuit() > 0)) {
			return true;
		} else if ((this.getOrigine() == cartes.Origine.Jour) && (j.getPointsJour() > 0)) {
			return true;
		} else if ((this.getOrigine() == cartes.Origine.Néant) && (j.getPointsNeant() > 0)) {
			return true;
		} else if ((this.getOrigine() == cartes.Origine.Néant) && (j.getPointsJour() > 1)) {
			return true;
		} else if ((this.getOrigine() == cartes.Origine.Néant) && (j.getPointsNuit() > 1)) {
			return true;
		} else if ((this.getOrigine() == cartes.Origine.Nul)) {
			return true;
		} else {
			return false;
		}
	}

}
