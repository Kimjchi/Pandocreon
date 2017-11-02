package joueurs;

import cartes.Carte;
import cartes.GuideSpirituel;

public interface Strategie {

	public int sélectionnerAction(IA ia);

	public void voulezVousDefausser(IA ia);

	public void voulezVousPiocher(IA ia);

	public boolean verifierPoints(IA ia, Carte carte);

	public void poserApocalypse(IA ia);

	public boolean peutPoserApocalypse(IA ia);

	public boolean aApocalypseDansSaMain(IA ia);

	public void poserGuide(IA ia);

	public boolean peutPoserGuide(IA ia);

	public boolean peutGuiderCroyant(GuideSpirituel guide, int numCarte);

	public void poserCroyant(IA ia);

	public boolean peutPoserCroyant(IA ia);

	public void utiliserDeusEx(IA ia);

	public boolean peutPoserDeuxEx(IA ia);

	public void utiliserDivinité(IA ia);

	public void guiderCroyant(IA ia, GuideSpirituel guide, int numCarte);

	public void sacrifierGuide(IA ia);

	public void sacrifierCroyant(IA ia);

	public int choisirUnDeSesGuides(IA ia);

	public int choisirUnDeSesCroyant(IA ia, int guide);

	public int choisirGuideOuCroyant();

	public int choisirAdversaire(IA ia);
	
	public int choisirAdversaireAvec2Croyants(IA ia);

	public int choisirAdversaireAvecGuideSprituels(IA ia);

	public int choisirGuideAdversaire(int adversaire);

	public int sélectionnerCroyant(int adversaire, int guide);

	public int choisirAutreCroyant(int adversaire, int guide1, int guide2, int croyant);
}
