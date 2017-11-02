package cartes.capacités;

import java.util.Iterator;

import joueurs.Joueur;
import partie.Partie;

//trou noir
public class EmpêcherGagnerPATouSLesJoueurs implements Capacité {

	public void sacrifier(Joueur j) {

		System.out.println("Un mysterieux trou noir venant d'une faille spatio-temporelle ouverte par "
				+ j.getNom()
				+ " frappe le monde de Pandocréon ! Heureusement sa seule conséquence est que les autres joueurs ne peuvent plus gagner de Points d'Action jusqu'à la fin du tour.");
		Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator();
		while (it.hasNext()) {
			Joueur joueur = it.next();
			if (joueur != j)
				joueur.setPeutGagnerPointAction(false);
		}

	}

}
