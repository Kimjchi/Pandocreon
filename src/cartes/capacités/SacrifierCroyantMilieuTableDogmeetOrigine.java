package cartes.capacités;

import java.util.*;

import cartes.Croyant;
import cartes.Defausse;
import cartes.Dogme;
import cartes.MilieuDeTable;
import cartes.Origine;
import joueurs.Joueur;

//Paladin
public class SacrifierCroyantMilieuTableDogmeetOrigine implements Capacité {

	public void sacrifier(Joueur j) {
		ArrayList<Integer> tabDefausse = new ArrayList<Integer>();
		// Iterator<Croyant> it =
		// MilieuDeTable.getInstance().getListeCroyants().iterator();
		// while (it.hasNext()) {
		for (int i = 0; i < MilieuDeTable.getInstance().getListeCroyants().size(); i++)
		// Croyant c = it.next();
		{
			if ((MilieuDeTable.getInstance().getListeCroyants().get(i).getOrigine() == Origine.Jour
					|| MilieuDeTable.getInstance().getListeCroyants().get(i).getOrigine() == Origine.Nuit)
					&& MilieuDeTable.getInstance().getListeCroyants().get(i).verifierDogme(Dogme.Nature)) {
				tabDefausse.add(i);

			}

		}
		int i = tabDefausse.size() - 1;
		while (!tabDefausse.isEmpty()) {
			Defausse.getInstance().défausser(MilieuDeTable.getInstance().getListeCroyants().get(tabDefausse.get(i)));
			MilieuDeTable.getInstance().enleverDuMilieu(MilieuDeTable.getInstance().getListeCroyants().get(tabDefausse.get(i)));
			tabDefausse.remove(i);
			i--;
		}
		System.out.println(
				"Tous les croyants de type Jour ou Nuit et d'origine Nature au centre de la table ont été détruit paladin.");

	}

}