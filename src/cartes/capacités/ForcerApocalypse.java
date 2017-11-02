package cartes.capacités;

import java.util.Iterator;
//Carte :
//Killinstred

import partie.Partie;
import cartes.CarteAction;
import cartes.Defausse;
import joueurs.Joueur;

public class ForcerApocalypse implements Capacité {
	// Killistreed ou Llewella
	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaire();
		boolean apocalypse = true;
		Iterator<CarteAction> it = Partie.getInstance().getJoueurs().get(adversaire).getMainJoueur().getContenuMain()
				.iterator();
		if (adversaire != -1) {
			while ((it.hasNext()) && (apocalypse)) {
				CarteAction ca = it.next();
				if (ca.getType() == cartes.Type.Apocalypse) {
					System.out.println(Partie.getInstance().getJoueurs().get(adversaire).getNom()
							+ "a une carte apocalypse dans sa main. L'effet est activé.");

					ca.getCapacité().sacrifier(Partie.getInstance().getJoueurs().get(adversaire));
					Partie.getInstance().setApocalypseDejaJouee(0);
					Partie.getInstance().getJoueurs().get(adversaire).getMainJoueur().getContenuMain().remove(ca);
					Defausse.getInstance().défausser(ca);
					apocalypse = false;
				}
			}
			if (apocalypse = false) {
				System.out.println(Partie.getInstance().getJoueurs().get(adversaire).getNom()
						+ " n'a pas de carte apocalypse dans sa main. Il ne se passe rien.");
			}

		}
	}

}
