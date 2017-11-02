package cartes.capacit�s;

import java.util.Iterator;

import joueurs.Joueur;
import partie.Partie;

//Nihiliste
public class Emp�cheGagnerPA implements Capacit� {

	@Override
	public void sacrifier(Joueur j) {
		// TODO Auto-generated method stub
		System.out.println(
				"Les nihilistes sont l�, la vie n'a pas de sens, plus aucun joueur ne gagne de points d'action jusqu'� la fin du tour");
		Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
		while (it.hasNext()) {
			Joueur joueur = it.next();
			joueur.setPeutGagnerPointAction(false);
		}
	}

}
