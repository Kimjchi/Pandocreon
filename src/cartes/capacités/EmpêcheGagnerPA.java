package cartes.capacités;

import java.util.Iterator;

import joueurs.Joueur;
import partie.Partie;

//Nihiliste
public class EmpêcheGagnerPA implements Capacité {

	@Override
	public void sacrifier(Joueur j) {
		// TODO Auto-generated method stub
		System.out.println(
				"Les nihilistes sont là, la vie n'a pas de sens, plus aucun joueur ne gagne de points d'action jusqu'à la fin du tour");
		Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
		while (it.hasNext()) {
			Joueur joueur = it.next();
			joueur.setPeutGagnerPointAction(false);
		}
	}

}
