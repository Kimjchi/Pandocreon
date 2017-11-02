package interfaceGraphique;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import interfaceGraphique.buttons.ButtonStop;
import joueurs.Joueur;
import partie.Partie;

public class VueChoisirAdversaire extends JFrame implements ThemeFoncé {

	private Joueur cible;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VueChoisirAdversaire() {
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		Joueur[] joueurs = null;
		String[] possibleValues = null;
		int j = 0;
		for (int i = 0; i < Partie.getInstance().getNombreJoueurs() - 1; i++) {
			if (!Partie.getInstance().getJoueurs().get(i).isEstRéel()) {
				possibleValues[j] = Partie.getInstance().getJoueurs().get(i).getNom();
				joueurs[j] = Partie.getInstance().getJoueurs().get(i);
				j++;

			}
		}
		String texte = "Quelle est votre cible ?";
		String choix = (String) JOptionPane.showInputDialog(this, texte, "Choix du nombre de joueurs - Pandocreon",
				JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
		int i = 0;
		while (Partie.getInstance().getJoueurs().get(i).getNom() != choix) {
			i ++;

		}
		this.cible = Partie.getInstance().getJoueurs().get(i);
	}
	
	public int getCible() {
		return Partie.getInstance().getJoueurs().indexOf(cible);
	}
}
