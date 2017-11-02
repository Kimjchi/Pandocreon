/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package cartes;

import java.util.ArrayList;;

/**
 * Description of Defausse.
 * 
 * @author marti
 */
public class Defausse {

	/**
	 * La liste de carte de la defausse.
	 */
	private static Defausse instance;

	private static ArrayList<CarteAction> defausse;

	/**
	 * The constructor.
	 */
	private Defausse() {
		defausse = new ArrayList<CarteAction>();
	}

	public static Defausse getInstance() {
		if (instance == null) {
			instance = new Defausse();
		}
		return instance;
	}

	/**
	 * Description of the method défausser.
	 */
	public void d�fausser(CarteAction c1) {
		defausse.add(c1);
		c1.setPosition("D�fausse");
	}

	public ArrayList<CarteAction> getDefausse() {
		return defausse;
	}

	/**
	 * Returns .
	 * 
	 * @return
	 * 
	 * 		public HashSet<CarteAction> get() { return this.; }
	 */

	/**
	 * Returns listeDefausse.
	 * 
	 * @return listeDefausse
	 * 
	 *         public HashSet<CarteAction> getListeDefausse() { return
	 *         this.listeDefausse; }
	 */

}
