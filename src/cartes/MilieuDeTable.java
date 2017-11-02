/*******************************************************************************
 * 2016, All rights reserved.

 *******************************************************************************/
package cartes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of MilieuDeTable.
 * 
 * @author marti
 */
public class MilieuDeTable extends Observable {
	/**
	 * Description of the property .
	 */

	/**
	 * Description of the property listeCroyants.
	 */
	private static MilieuDeTable instance;
	private static ArrayList<Croyant> listeCroyants;
	private ArrayList<Croyant> croyantAGuider;


	// Start of user code (user defined attributes for MilieuDeTable)

	// End of user code

	/**
	 * The constructor.
	 */
	private MilieuDeTable() {
		// Start of user code constructor for MilieuDeTable)
		listeCroyants = new ArrayList<Croyant>();
		// End of user code
	}

	// Start of user code (user defined methods for MilieuDeTable)
	public static MilieuDeTable getInstance() {
		if (instance == null) {
			instance = new MilieuDeTable();
		}
		return instance;
	}

	// End of user code
	/**
	 * Returns .
	 * 
	 * @return
	 */

	/**
	 * Returns listeCroyants.
	 * 
	 * @return listeCroyants
	 */
	public ArrayList<Croyant> getListeCroyants() {
		return listeCroyants;
	}

	public void poserCroyantMilieu(Croyant elem) {
		if (elem.getType() == cartes.Type.Croyant) {
			elem.setPosition(Position.Centre);
			listeCroyants.add(elem);
		}
		this.setChanged();
		this.notifyObservers();
	}

	public void afficherMilieu() {
		System.out.println("Cartes au centre :");
		int i = 0;
		for (Iterator<Croyant> it = listeCroyants.iterator(); it.hasNext();) {
			i++;
			Croyant croyant = it.next();
			System.out.print("Carte n°" + i);
			System.out.println(croyant);
			System.out.println(
					"----------------------------------------------------------------------------------------------");
		}
	}

	public void enleverDuMilieu(Croyant c1) {
		listeCroyants.remove(c1);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Croyant> getCroyantAGuider() {
		return this.croyantAGuider;
	}
	
	public void addCroyantAGuider(Croyant croyant) {
		this.croyantAGuider.add(croyant);
	}
	
	public void clearCroyantAGuider() {
		this.croyantAGuider.clear();
	}

}
