/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package joueurs;

import cartes.*;

import java.util.*;

/**
 * Description of Main.
 * 
 * @author martin
 */
public class Main extends Observable{
	/**
	 * Indique le nombre de cartes dans la main du joueur.
	 */
	private int nbCartes;

	/**
	 * Description of the property .
	 */
	private ArrayList<CarteAction> contenuMain = new ArrayList<CarteAction>();

	/**
	 * Le constructeur qui initialise la main du joueur à 7 cartes.
	 */
	public Main(Pioche<CarteAction> pioche) {
		this.nbCartes = 7;
		for (int i = 0; i < 7; i++) {
			this.contenuMain.add(pioche.piocherCarte());
			System.out.println("On est al");
		}

	}

	public void afficherMain() {
		System.out.println("Votre main :");
		int i = 1;
		for (Iterator<CarteAction> it = this.contenuMain.iterator(); it.hasNext();) {

			CarteAction ca = it.next();
			System.out.print("Carte n°" + i);
			System.out.println(ca);
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			i++;
		}
	}

	public void enleverMain(int i) {
		this.get(i).setPosition("Défausse");
		this.contenuMain.remove(i);
		this.nbCartes--;
		this.setChanged();		
		this.notifyObservers();
	}
	
	public void enleverMain(CarteAction carte) {
		this.contenuMain.remove(carte);
		carte.setPosition(Position.Défausse);
		this.nbCartes--;
		this.setChanged();		
		this.notifyObservers();
		
	}
	// Start of user code (user defined methods for Main)

	// End of user code
	/**
	 * Returns nbCartes.
	 * 
	 * @return nbCartes
	 */
	public int getNbCartes() {
		return this.contenuMain.size();
	}

	public void remplirMain(Pioche<CarteAction> pioche) {
		for (int i = this.contenuMain.size(); i < 7; i++) {
			if (pioche.getDeck().size() > 0) {
				this.contenuMain.add(pioche.piocherCarte());
				//this.nbCartes++;
			}
		}
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * Returns Joueur .
	 * 
	 * @return
	 * 
	 * 		public Joueur get() { return this.; }
	 */

	/**
	 * Sets a value to attribute Joueur.
	 * 
	 * @param new
	 * 
	 *            public void set(Joueur new) { if(this. != null) { this..set
	 *            (null); } this..set (this); }
	 */

	public ArrayList<CarteAction> getContenuMain() {
		return contenuMain;
	}

	public void setContenuMain(ArrayList<CarteAction> contenuMain) {
		this.contenuMain = contenuMain;
	}

	/**
	 * Returns .
	 * 
	 * @return
	 */
	public CarteAction get(int numCarte) {
		return this.contenuMain.get(numCarte);
	}
	
	@SuppressWarnings("null")
	public ArrayList<Croyant> getCroyants() {
		ArrayList<Croyant> listeCroyant = null;
		for (int i = 0; i < this.getContenuMain().size(); i++) {
			if (this.getContenuMain().get(i).getType() == Type.Croyant) {
				listeCroyant.add((Croyant) this.getContenuMain().get(i));
			}
		}
		
		return listeCroyant;
	}
	@SuppressWarnings("null")
	public ArrayList<DeusEx> getDeusEx() {
		ArrayList<DeusEx> listeCroyant = null;
		for (int i = 0; i < this.getContenuMain().size(); i++) {
			if (this.getContenuMain().get(i).getType() == Type.DeusEx) {
				listeCroyant.add((DeusEx) this.getContenuMain().get(i));
			}
		}
		
		return listeCroyant;
	}
	@SuppressWarnings("null")
	public ArrayList<GuideSpirituel> getGuides() {
		ArrayList<GuideSpirituel> listeCroyant = null;
		for (int i = 0; i < this.getContenuMain().size(); i++) {
			if (this.getContenuMain().get(i).getType() == Type.GuideSpirituel) {
				listeCroyant.add((GuideSpirituel) this.getContenuMain().get(i));
			}
		}
		
		return listeCroyant;
	}
	@SuppressWarnings("null")
	public ArrayList<Apocalypse> getApocalypses() {
		ArrayList<Apocalypse> listeCroyant = null;
		for (int i = 0; i < this.getContenuMain().size(); i++) {
			if (this.getContenuMain().get(i).getType() == Type.Apocalypse) {
				listeCroyant.add((Apocalypse) this.getContenuMain().get(i));
			}
		}
		
		return listeCroyant;
	}
}
