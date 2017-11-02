package cartes.capacités;

import joueurs.IA;
import joueurs.Joueur;
import partie.Partie;

//Fourberie
public class SacrifierCroyantSansCapacité implements Capacité {

	int j2, k2;

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvec2Croyants();
		int guide1 = j.choisirGuideAdversaire(adversaire);
		int croyant1 = j.sélectionnerCroyant(adversaire, guide1);
		int guide2 = j.choisirGuideAdversaire(adversaire);
		int i = 0;
		int croyant2 = j.sélectionnerCroyant(adversaire, guide2);
		if (adversaire != -1 && guide1 != -1 && croyant1 != -1 && guide2 != -1 && croyant2 != -1) {
			while (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide1).getCroyantAttache()
					.get(croyant1).getId() == Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant()
							.get(guide2).getCroyantAttache().get(croyant2).getId()) {
				System.out.println("Veuillez selectionner deux croyants differents.");
				guide1 = j.choisirGuideAdversaire(adversaire);
				croyant1 = j.sélectionnerCroyant(adversaire, guide1);
				guide2 = j.choisirGuideAdversaire(adversaire);
				if (!j.isEstRéel()){
					croyant2 = ((IA) j).choisirAutreCroyant(adversaire, guide1, guide2, croyant1);
				}
				else croyant2 = j.sélectionnerCroyant(adversaire, guide2);
			}
			System.out.println(
					"La fourberie de " + j.getNom() + " entraine la destruction de "
							+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide1)
									.getCroyantAttache().get(croyant1).getNom()
							+ " et de "
							+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide2)
									.getCroyantAttache().get(croyant2).getNom()
							+ " qui appartenaient à "
							+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide1)
									.getCroyantAttache().get(croyant1).getNom()
							+ " et de " + Partie.getInstance().getJoueurs().get(adversaire).getNom() + ".");
			Partie.getInstance().getJoueurs().get(adversaire).defausserCroyant(guide1, croyant1);
			Partie.getInstance().getJoueurs().get(adversaire).defausserCroyant(guide2, croyant2);
			i++;
		}
		if(i == 1){
			System.out.println("La carte est défaussée.");
		}
	}

}