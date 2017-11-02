/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

import java.util.Observable;

//import java.util.HashSet;
import cartes.capacit�s.Capacit�;

/**
 * Description of Carte.
 * 
 * @author marti
 */
public class Carte extends Observable {

	/**
	 * Origine de la carte.
	 */
	protected Origine origine;

	/**
	 * les 3 dogmes de la carte sont stock�es dans un tableau.
	 */
	protected Dogme[] dogme = new Dogme[3];

	/**
	 * Nom de la carte.
	 */
	private String nom;

	/**
	 * identifiant de la carte, permet de s�lectionner une carte en lignes de
	 * commande.
	 */
	private int id;

	/**
	 * Type de la carte.
	 */
	private Type type;

	protected Capacit� capacit�;

	/**
	 * Le constructeur d'une carte va �tre surcharg� avec la m�thode super()
	 * dans les diff�rents types de carte.
	 */
	public Carte(int identifiant, Type type, String nom, Origine origine, Capacit� capacit�) {
		super();
		this.id = identifiant;
		this.nom = nom;
		this.origine = origine;
		this.type = type;
		this.capacit� = capacit�;
	}

	// Start of user code (user defined methods for Carte)

	public Capacit� getCapacit�() {
		return capacit�;
	}

	public void setCapacit�(Capacit� capacit�) {
		this.capacit� = capacit�;
	}

	// End of user code
	/**
	 * Returns origine.
	 * 
	 * @return origine
	 */
	public Origine getOrigine() {
		return this.origine;
	}

	/**
	 * Sets a value to attribute origine.
	 * 
	 * @param newOrigine
	 */
	public void setOrigine(Origine newOrigine) {
		this.origine = newOrigine;
	}

	/**
	 * Returns dogme.
	 * 
	 * @return dogme
	 */
	public Dogme getDogme(int i) {
		return this.dogme[i];
	}

	/**
	 * Returns nom.
	 * 
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	public int getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

}
