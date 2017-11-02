package cartes;

import java.util.ArrayList;
import cartes.capacit�s.*;

public abstract class ConstructeurDeCartes {

	// La classe abstraite ConstructeurDeCarte va construire les decks de cartes
	// action et de divinit�s.

	public static ArrayList<CarteAction> construireDeck() {

		// Croyants

		// Type Jour
		ArrayList<CarteAction> toutesCartesAction = new ArrayList<CarteAction>();
		Croyant moines1 = new Croyant(1, cartes.Type.Croyant, "Moines1", cartes.Origine.Jour, cartes.Dogme.Humain,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, 2, "Sacrifice :  Donne un point d'Action d'Origine Jour.",
				new GagnerPointAction(cartes.Origine.Jour));
		toutesCartesAction.add(moines1);
		Croyant moines2 = new Croyant(2, cartes.Type.Croyant, "Moines2", cartes.Origine.Jour, cartes.Dogme.Humain,
				cartes.Dogme.Chaos, cartes.Dogme.Mystique, 2, "Sacrifice :  Donne un point d'Action d'Origine Jour.",
				new GagnerPointAction(cartes.Origine.Jour));
		toutesCartesAction.add(moines2);
		Croyant moines3 = new Croyant(3, cartes.Type.Croyant, "Moines3", cartes.Origine.Jour, cartes.Dogme.Chaos,
				cartes.Dogme.Symbole, cartes.Dogme.Mystique, 2, "Sacrifice :  Donne un point d'Action d'Origine Jour.",
				new GagnerPointAction(cartes.Origine.Jour));
		toutesCartesAction.add(moines3);
		Croyant moines4 = new Croyant(4, cartes.Type.Croyant, "Moines4", cartes.Origine.Jour, cartes.Dogme.Symbole,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, 2, "Sacrifice :  Donne un point d'Action d'Origine Jour.",
				new GagnerPointAction(cartes.Origine.Jour));
		toutesCartesAction.add(moines4);
		Croyant moines5 = new Croyant(5, cartes.Type.Croyant, "Moines5", cartes.Origine.Jour, cartes.Dogme.Chaos,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, 2, "Sacrifice :  Donne un point d'Action d'Origine Jour.",
				new GagnerPointAction(cartes.Origine.Jour));
		toutesCartesAction.add(moines5);

		Croyant travailleurs1 = new Croyant(6, cartes.Type.Croyant, "Travailleurs1", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Chaos, cartes.Dogme.Symbole, 2,
				"Sacrifice :  Emp�che une Divinit� poss�dant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu.",
				new Emp�cheSacrificeCroyant(cartes.Dogme.Mystique, cartes.Dogme.Nature));
		toutesCartesAction.add(travailleurs1);
		Croyant travailleurs2 = new Croyant(7, cartes.Type.Croyant, "Travailleurs2", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Nature, cartes.Dogme.Symbole, 2,
				"Sacrifice :  Emp�che une Divinit� poss�dant le Dogme Chaos ou Mystique de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",
				new Emp�cheSacrificeGuide(cartes.Dogme.Mystique, cartes.Dogme.Chaos));
		toutesCartesAction.add(travailleurs2);
		Croyant travailleurs3 = new Croyant(8, cartes.Type.Croyant, "Travailleurs3", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Mystique, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Vous piochez deux cartes au hasard dans la main d'une autre Divinit�.",
				new PiocherMainAutreJoueur(2));
		toutesCartesAction.add(travailleurs3);

		Croyant ermite1 = new Croyant(9, cartes.Type.Croyant, "Ermite1", cartes.Origine.Jour, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos, 1,
				"Sacrifice : Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(ermite1);
		Croyant ermite2 = new Croyant(10, cartes.Type.Croyant, "Ermite2", cartes.Origine.Jour, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, 1,
				"Sacrifice : Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(ermite2);

		Croyant int�gristes = new Croyant(11, cartes.Type.Croyant, "Integristes", cartes.Origine.Jour,
				cartes.Dogme.Nature, cartes.Dogme.Humain, cartes.Dogme.Chaos, 1,
				"Sacrifice : Impose le sacrifice d'un Guide Spirituel d'un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e.",
				new Devin());
		toutesCartesAction.add(int�gristes);

		Croyant guerriersSaints = new Croyant(12, cartes.Type.Croyant, "Guerriers Saints", cartes.Origine.Jour,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, cartes.Dogme.Symbole, 4,
				"Sacrifice : Un Guide Spirituel revient dans la main de sa Divinit�. SesCroyants reviennent au centre de la table.",
				new RetourGuideEnMain());
		toutesCartesAction.add(guerriersSaints);

		Croyant diplomates = new Croyant(13, cartes.Type.Croyant, "Diplomates", cartes.Origine.Jour,
				cartes.Dogme.Symbole, cartes.Dogme.Humain, cartes.Dogme.Chaos, 4,
				"Sacrifice : Relancez le d� de Cosmogonie. Le tour se finit normalement sous la nouvelle influence.",
				new RelancerD�());
		toutesCartesAction.add(diplomates);

		// Type Nuit
		Croyant d�mons1 = new Croyant(14, cartes.Type.Croyant, "Demons1", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Humain, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(d�mons1);
		Croyant d�mons2 = new Croyant(15, cartes.Type.Croyant, "Demons2", cartes.Origine.Nuit, cartes.Dogme.Chaos,
				cartes.Dogme.Mystique, cartes.Dogme.Humain, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(d�mons2);
		Croyant d�mons3 = new Croyant(16, cartes.Type.Croyant, "Demons3", cartes.Origine.Nuit, cartes.Dogme.Symbole,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(d�mons3);
		Croyant d�mons4 = new Croyant(17, cartes.Type.Croyant, "Demons4", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(d�mons4);
		Croyant d�mons5 = new Croyant(18, cartes.Type.Croyant, "Demons5", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(d�mons5);

		Croyant alchimistes1 = new Croyant(19, cartes.Type.Croyant, "Alchimistes1", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Symbole, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Emp�che une Divinit� poss�dant le Dogme Humain ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu.",
				new Emp�cheSacrificeCroyant(cartes.Dogme.Humain, cartes.Dogme.Mystique));
		toutesCartesAction.add(alchimistes1);
		Croyant alchimistes2 = new Croyant(20, cartes.Type.Croyant, "Alchimistes2", cartes.Origine.Nuit,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Emp�che une Divinit� poss�dant le Dogme Nature ou Symbole de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",
				new Emp�cheSacrificeGuide(cartes.Dogme.Humain, cartes.Dogme.Symbole));
		toutesCartesAction.add(alchimistes2);
		Croyant alchimistes3 = new Croyant(21, cartes.Type.Croyant, "Alchimistes3", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Symbole, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Vous piochez au hasard deux cartes dans la main d'une autre Divinit�.",
				new PiocherMainAutreJoueur(2));
		toutesCartesAction.add(alchimistes3);

		Croyant vampire1 = new Croyant(22, cartes.Type.Croyant, "Vampires1", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Symbole, cartes.Dogme.Humain, 1,
				"Sacrifice :  Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifi�. La capacit� sp�ciale du sacrifi� est jou�e.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(vampire1);
		Croyant vampire2 = new Croyant(23, cartes.Type.Croyant, "Vampires2", cartes.Origine.Nuit, cartes.Dogme.Chaos,
				cartes.Dogme.Mystique, cartes.Dogme.Humain, 1,
				"Sacrifice :  Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifi�. La capacit� sp�ciale du sacrifi� est jou�e.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(vampire2);

		Croyant lycanthropes = new Croyant(24, cartes.Type.Croyant, "Lycanthropes", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Humain, cartes.Dogme.Chaos, 4,
				"Sacrifice :  Retirez tous les Croyants attach�s � l'un des Guides Spirituels d'une autre Divinit�. Les Croyants reviennent au milieu de la table, le Guide Spirituel est d�fauss�. ",
				new DefausseGuideCroyantAuCentre());
		toutesCartesAction.add(lycanthropes);

		Croyant pillards = new Croyant(25, cartes.Type.Croyant, "Pillards", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, 4,
				"Sacrifice :  R�cup�rez les points d'Action d'une Divinit� n'ayant pas encore jou� durant ce tour. Les points d'Action gardent leur Origine. La Divinit� perd ses points.",
				new VolerPointAction());
		toutesCartesAction.add(pillards);

		Croyant illusionnistes = new Croyant(26, cartes.Type.Croyant, "Illusionnistes", cartes.Origine.Nuit,
				cartes.Dogme.Chaos, cartes.Dogme.Humain, cartes.Dogme.Symbole, 4,
				"Sacrifice :  Vous b�n�ficiez de la capacit� sp�ciale de sacrifice d'une carte de Crayants appartenant � une autre Divinit�. La carte en question reste en jeu.",
				new VolerCapacit�Adversaire());
		toutesCartesAction.add(illusionnistes);

		// Type Chaos
		Croyant esprits1 = new Croyant(27, cartes.Type.Croyant, "Esprits1", cartes.Origine.N�ant, cartes.Dogme.Mystique,
				cartes.Dogme.Humain, cartes.Dogme.Nature, 2, "Sacrifice :  Donne un point d'Action d'Origine N�ant.",
				new GagnerPointAction(cartes.Origine.N�ant));
		toutesCartesAction.add(esprits1);
		Croyant esprits2 = new Croyant(28, cartes.Type.Croyant, "Esprits2", cartes.Origine.N�ant, cartes.Dogme.Mystique,
				cartes.Dogme.Humain, cartes.Dogme.Chaos, 2, "Sacrifice :  Donne un point d'Action d'Origine N�ant.",
				new GagnerPointAction(cartes.Origine.N�ant));
		toutesCartesAction.add(esprits2);
		Croyant esprits3 = new Croyant(29, cartes.Type.Croyant, "Esprits3", cartes.Origine.N�ant, cartes.Dogme.Mystique,
				cartes.Dogme.Symbole, cartes.Dogme.Chaos, 2, "Sacrifice :  Donne un point d'Action d'Origine N�ant.",
				new GagnerPointAction(cartes.Origine.N�ant));
		toutesCartesAction.add(esprits3);
		Croyant esprits4 = new Croyant(30, cartes.Type.Croyant, "Esprits4", cartes.Origine.N�ant, cartes.Dogme.Mystique,
				cartes.Dogme.Symbole, cartes.Dogme.Nature, 2, "Sacrifice :  Donne un point d'Action d'Origine N�ant.",
				new GagnerPointAction(cartes.Origine.N�ant));
		toutesCartesAction.add(esprits4);
		Croyant esprits5 = new Croyant(31, cartes.Type.Croyant, "Esprits5", cartes.Origine.N�ant, cartes.Dogme.Mystique,
				cartes.Dogme.Chaos, cartes.Dogme.Nature, 2, "Sacrifice :  Donne un point d'Action d'Origine N�ant.",
				new GagnerPointAction(cartes.Origine.N�ant));
		toutesCartesAction.add(esprits5);

		Croyant ali�n�s1 = new Croyant(32, cartes.Type.Croyant, "Ali�n�s1", cartes.Origine.N�ant, cartes.Dogme.Symbole,
				cartes.Dogme.Humain, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Emp�che une Divinit� poss�dant le Dogme Nature ou Mystique de sacrifier un de ses cartes de Croyants durant ce tour de jeu.",
				new Emp�cheSacrificeCroyant(cartes.Dogme.Mystique, cartes.Dogme.Nature));
		toutesCartesAction.add(ali�n�s1);
		Croyant ali�n�s2 = new Croyant(33, cartes.Type.Croyant, "Ali�n�s2", cartes.Origine.N�ant, cartes.Dogme.Symbole,
				cartes.Dogme.Humain, cartes.Dogme.Nature, 2,
				"Sacrifice :  Emp�che une Divinit� poss�dant le Dogme Nature ou Mystique de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",
				new Emp�cheSacrificeGuide(cartes.Dogme.Mystique, cartes.Dogme.Chaos));
		toutesCartesAction.add(ali�n�s2);
		Croyant ali�n�s3 = new Croyant(34, cartes.Type.Croyant, "Ali�n�s3", cartes.Origine.N�ant, cartes.Dogme.Mystique,
				cartes.Dogme.Humain, cartes.Dogme.Chaos, 2,
				"Sacrifice : Vous piochez deux cartes au hasard dans la main d'une autre Divinit�.",
				new PiocherMainAutreJoueur(2));
		toutesCartesAction.add(ali�n�s3);

		Croyant revenant = new Croyant(35, cartes.Type.Croyant, "Revenant", cartes.Origine.N�ant, cartes.Dogme.Mystique,
				cartes.Dogme.Humain, cartes.Dogme.Nature, 4,
				"Sacrifice : Lancez le d� de Cosmogonie. Le tour se fait normalement mais sous cette nouvelle influence",
				new RelancerD�());
		toutesCartesAction.add(revenant);

		Croyant r�volutionnaires = new Croyant(36, cartes.Type.Croyant, "R�volutionnaires", cartes.Origine.N�ant,
				cartes.Dogme.Symbole, cartes.Dogme.Humain, cartes.Dogme.Nature, 4,
				"Sacrifice : Imposez le sacrifice d'une carte de Croyant � autant de Divinit�s que vous le voulez. Chaque Divinit� choisit la carte � sacrifier. Les capacit�s sp�ciales sont jou�es.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(r�volutionnaires);

		Croyant nihilistes = new Croyant(37, cartes.Type.Croyant, "Nihillistes", cartes.Origine.N�ant,
				cartes.Dogme.Symbole, cartes.Dogme.Mystique, cartes.Dogme.Chaos, 4,
				"Sacrifice : Jusqu'� la fin du tour, plus aucune Divinit� nerecoit de points d'Action.",
				new Emp�cheGagnerPA());
		toutesCartesAction.add(nihilistes);

		//Guides Spirituels
		GuideSpirituel martyr1 = new GuideSpirituel(38, cartes.Type.GuideSpirituel, "Martyr1", cartes.Origine.Jour,
				cartes.Dogme.Nature, cartes.Dogme.Humain, 2, "Sacrifice : Equivalent � la pose d'une carte Apocalypse",
				new Capacit�Apocalypse());
		toutesCartesAction.add(martyr1);
		GuideSpirituel martyr2 = new GuideSpirituel(39, cartes.Type.GuideSpirituel, "Martyr2", cartes.Origine.Nuit,
				cartes.Dogme.Symbole, cartes.Dogme.Humain, 2, "Sacrifice : Equivalent � la pose d'une carte Apocalypse",
				new Capacit�Apocalypse());
		toutesCartesAction.add(martyr2);
		GuideSpirituel martyr3 = new GuideSpirituel(40, cartes.Type.GuideSpirituel, "Martyr3", cartes.Origine.N�ant,
				cartes.Dogme.Nature, cartes.Dogme.Chaos, 2, "Sacrifice : Equivalent � la pose d'une carte Apocalypse",
				new Capacit�Apocalypse());
		toutesCartesAction.add(martyr3);

		GuideSpirituel clerc1 = new GuideSpirituel(41, cartes.Type.GuideSpirituel, "Clerc1", cartes.Origine.Jour,
				cartes.Dogme.Chaos, cartes.Dogme.Humain, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc1);
		GuideSpirituel clerc2 = new GuideSpirituel(42, cartes.Type.GuideSpirituel, "Clerc2", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Symbole, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc2);
		GuideSpirituel clerc3 = new GuideSpirituel(43, cartes.Type.GuideSpirituel, "Clerc3", cartes.Origine.N�ant,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc3);
		GuideSpirituel clerc4 = new GuideSpirituel(44, cartes.Type.GuideSpirituel, "Clerc4", cartes.Origine.Jour,
				cartes.Dogme.Symbole, cartes.Dogme.Mystique, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc4);
		GuideSpirituel clerc5 = new GuideSpirituel(45, cartes.Type.GuideSpirituel, "Clerc5", cartes.Origine.Nuit,
				cartes.Dogme.Chaos, cartes.Dogme.Symbole, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc5);
		GuideSpirituel clerc6 = new GuideSpirituel(46, cartes.Type.GuideSpirituel, "Clerc6", cartes.Origine.N�ant,
				cartes.Dogme.Chaos, cartes.Dogme.Mystique, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc6);
		GuideSpirituel clerc7 = new GuideSpirituel(47, cartes.Type.GuideSpirituel, "Clerc7", cartes.Origine.Jour,
				cartes.Dogme.Nature, cartes.Dogme.Humain, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc7);
		GuideSpirituel clerc8 = new GuideSpirituel(48, cartes.Type.GuideSpirituel, "Clerc8", cartes.Origine.Nuit,
				cartes.Dogme.Chaos, cartes.Dogme.Nature, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc8);

		GuideSpirituel shaman = new GuideSpirituel(49, cartes.Type.GuideSpirituel, "Shaman", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Symbole, 3,
				"Sacrifice : Sacrifie tous les Croyants d'Origine N�ant d'une Divinit� ayant le Dogme Humain. Les capacit�s sp�ciales sont jou�es normalement.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(shaman);
		GuideSpirituel anarchiste = new GuideSpirituel(50, cartes.Type.GuideSpirituel, "Anarchiste",
				cartes.Origine.N�ant, cartes.Dogme.Humain, cartes.Dogme.Chaos, 3,
				"Sacrifice : Sacrifie un Guide Spirituel, si lui ou sa Divinit� ne croit au Dgme Chaos. Les capacit�s sp�ciales sont jou�es normalement.",
				new Anarchiste());
		toutesCartesAction.add(anarchiste);
		GuideSpirituel paladin = new GuideSpirituel(51, cartes.Type.GuideSpirituel, "Paladin", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Mystique, 3,
				"Sacrifice : Tous les Croyants d'Origine N�ant ou N�ant et ayant le Dogme Nature, actuellement sur la table sont d�fauss�s. Les capacit�s sp�ciales ne sont pas jou�es.",
				new SacrifierCroyantMilieuTableDogmeetOrigine());
		toutesCartesAction.add(paladin);

		GuideSpirituel asc�te = new GuideSpirituel(52, cartes.Type.GuideSpirituel, "Asc�te", cartes.Origine.Nuit,
				cartes.Dogme.Humain, cartes.Dogme.Symbole, 1,
				"Sacrifice : Sacrifie 2 cartes Croyants d'une Divinit� ayant ayant le Dogme Humain ou Symboles. Les capacit�s sp�ciales sont jou�es normalement.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(asc�te);
		GuideSpirituel devin = new GuideSpirituel(53, cartes.Type.GuideSpirituel, "Devin", cartes.Origine.N�ant,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, 1,
				"Sacrifice : Oblige une Divinit� ayant le Dogme Nature ou Mystique � sacrifier l'un de ses Guides Spirituels. Les capacit�s sp�ciales sont jou�es normalement.",
				new Devin());
		toutesCartesAction.add(devin);
		GuideSpirituel exorciste = new GuideSpirituel(54, cartes.Type.GuideSpirituel, "Exorciste", cartes.Origine.Jour,
				cartes.Dogme.Chaos, cartes.Dogme.Mystique, 1,
				"Sacrifice : Une Divinit� d'Origine Nuit ou ayant les Dogmes Mystique et Chaos reprend dans sa main l'un de ses Guides Spirituels. Les Croyants qui y �taient attach�s sont d�fauss�s.",
				new RetourGuideMainCroyantDefauss�());
		toutesCartesAction.add(exorciste);

		GuideSpirituel sorcier = new GuideSpirituel(55, cartes.Type.GuideSpirituel, "Sorcier", cartes.Origine.Nuit,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, 3,
				"Sacrifice : Echangez l'un de vos Guides Spirituels avec un d'une autre Divinit�. Vous choisissez les deux guides Spirituels en question. Les Croyants restent attach�s aux m�mes cartes.",
				new EchangeGuidesSpirituels());
		toutesCartesAction.add(sorcier);
		GuideSpirituel tyran = new GuideSpirituel(56, cartes.Type.GuideSpirituel, "Tyran", cartes.Origine.N�ant,
				cartes.Dogme.Chaos, cartes.Dogme.Symbole, 3,
				"Sacrifice : D�fausse tous les Croyants ayant le Dogme Mystique actuellement au centre de la table.",
				new SacrifierCroyantMilieuDogme());
		toutesCartesAction.add(tyran);
		GuideSpirituel messie = new GuideSpirituel(57, cartes.Type.GuideSpirituel, "Messie", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Mystique, 3,
				"Sacrifice : Le joueur pose le d� de Cosmogonie sur la face qu'il d�sire et commence un nouveau tour de jeu.",
				new ChoisirDe());
		toutesCartesAction.add(messie);

		// Deus Ex
		// Avec Origine
		DeusEx col�reDivine1 = new DeusEx(58, cartes.Type.DeusEx, "Col�reDivine1", cartes.Origine.Jour,
				"Sacrifice : D�truit une carte Guide Spirituel d'Origine Nuit ou N�ant, dont la capacit� sp�ciale n'a pas effet. Les Croyants attach�s reviennent au centre de la table.",
				new Capacit�Col�reDivine(cartes.Origine.Nuit));
		toutesCartesAction.add(col�reDivine1);
		DeusEx col�reDivine2 = new DeusEx(59, cartes.Type.DeusEx, "Col�reDivine2", cartes.Origine.Nuit,
				"Sacrifice : D�truit une carte Guide Spirituel d'Origine Jour ou N�ant, dont la capacit� sp�ciale n'a pas effet. Les Croyants attach�s reviennent au centre de la table.",
				new Capacit�Col�reDivine(cartes.Origine.Jour));
		toutesCartesAction.add(col�reDivine2);

		DeusEx stase = new DeusEx(60, cartes.Type.DeusEx, "Stase", cartes.Origine.Jour,
				"Sacrifice : Prot�ge un Guide Spirituel et ses Croyants jusqu'� ce que cette carte soit annul�e ou jusqu'� la prochaine tentative d'Apocalypse.",
				new Stase());
		toutesCartesAction.add(stase);
		DeusEx ordreC�leste = new DeusEx(61, cartes.Type.DeusEx, "OrdreC�leste", cartes.Origine.Jour,
				"Sacrifice : Vous r�cup�rez un des Guides Spirituels pos�s devant une autre Divinit� et le placez devant vous avec les Croyants qui y sont attach�s",
				new VolerGuideSpirituelEtCroyants());
		toutesCartesAction.add(ordreC�leste);

		DeusEx fourberie = new DeusEx(62, cartes.Type.DeusEx, "Fourberie", cartes.Origine.Nuit,
				"Sacrifice : Sacrifiez 2 cartes Croyants d'une autre Divinit�. Les capacit�s sp�ciales ne sont pas jou�es.",
				new SacrifierCroyantSansCapacit�());
		toutesCartesAction.add(fourberie);
		DeusEx diversion = new DeusEx(63, cartes.Type.DeusEx, "Diversion", cartes.Origine.Nuit,
				"Sacrifice : Prenez 3 cartes dans la main d'un autre joueur et incluez-les � votre main.",
				new PiocherMainAutreJoueur(3));
		toutesCartesAction.add(diversion);

		DeusEx concentration = new DeusEx(64, cartes.Type.DeusEx, "Concentration", cartes.Origine.N�ant,
				"Sacrifice : Vous r�cup�rez un des Guides Spirituels pos�s devant une autre Divinit� et le placez devant vous avec les Croyants qui y sont attach�s.",
				new VolerGuideSpirituelEtCroyants());
		toutesCartesAction.add(concentration);
		DeusEx trouNoir = new DeusEx(65, cartes.Type.DeusEx, "TrouNoir", cartes.Origine.N�ant,
				"Sacrifice : Aucun autre joueur ne gagne de points d'Action durant ce tour.",
				new Emp�cherGagnerPATouSLesJoueurs());
		toutesCartesAction.add(trouNoir);
		DeusEx phoenix = new DeusEx(66, cartes.Type.DeusEx, "Phoenix", cartes.Origine.N�ant,
				"Sacrifice : Permet de b�n�ficier de la capacit� sp�ciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.",
				new ActiverEffetSansDetruire());
		toutesCartesAction.add(phoenix);

		// Sans Origine
		DeusEx influenceJour = new DeusEx(67, cartes.Type.DeusEx, "InfluenceJour", cartes.Origine.Nul,
				"Sacrifice : Annule la capacit� sp�ciale d'une carte d'Action d'Origine Nuit ou N�ant.",
				new AnnulerCapacit�(cartes.Origine.Nuit, cartes.Origine.N�ant));
		toutesCartesAction.add(influenceJour);
		DeusEx influenceNuit = new DeusEx(68, cartes.Type.DeusEx, "InfluenceNuit", cartes.Origine.Nul,
				"Sacrifice : Annule la capacit� sp�ciale d'une carte d'Action d'Origine Jour ou N�ant.",
				new AnnulerCapacit�(cartes.Origine.Jour, cartes.Origine.N�ant));
		toutesCartesAction.add(influenceNuit);
		DeusEx influenceN�ant = new DeusEx(69, cartes.Type.DeusEx, "InfluenceN�ant", cartes.Origine.Nul,
				"Sacrifice : Annule la capacit� sp�ciale d'une carte d'Action d'Origine Jour ou Nuit.",
				new AnnulerCapacit�(cartes.Origine.Nuit, cartes.Origine.Jour));
		toutesCartesAction.add(influenceN�ant);
		DeusEx influenceNulle1 = new DeusEx(70, cartes.Type.DeusEx, "InfluenceNulle1", cartes.Origine.Nul,
				"Sacrifice : Annule la capacit� sp�ciale d'une carte d'Action.",
				new AnnulerCapacit�(cartes.Origine.Nul, cartes.Origine.Nul));
		toutesCartesAction.add(influenceNulle1);
		DeusEx influenceNulle2 = new DeusEx(71, cartes.Type.DeusEx, "InfluenceNulle2", cartes.Origine.Nul,
				"Sacrifice : Annule la capacit� sp�ciale d'une carte d'Action.",
				new AnnulerCapacit�(cartes.Origine.Nul, cartes.Origine.Nul));
		toutesCartesAction.add(influenceNulle2);

		DeusEx transe = new DeusEx(72, cartes.Type.DeusEx, "Transe", cartes.Origine.Nul,
				"Sacrifice : Permet de r�cup�rer les effets b�n�fiques d'une carte d'Action pos�e par une autre Divinit�.",
				new Miroir());
		toutesCartesAction.add(transe);
		DeusEx miroir = new DeusEx(73, cartes.Type.DeusEx, "Miroir", cartes.Origine.Nul,
				"Sacrifice : Retourne les effets d'une carte d'Action sur la Divinit� qui l'a pos�e.", new Miroir());
		toutesCartesAction.add(miroir);
		DeusEx boulversement = new DeusEx(74, cartes.Type.DeusEx, "Boulversement", cartes.Origine.Nul,
				"Sacrifice : Relancez le d� de Cosmogonie. Le tour de jeu se terminera normalement, mais sous la nouvelle influence.",
				new RelancerD�());
		toutesCartesAction.add(boulversement);
		DeusEx inquisition = new DeusEx(75, cartes.Type.DeusEx, "Inquisition", cartes.Origine.Nul,
				"Sacrifice : Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. Lancez le d� de Cosmogonie. Sur Jour, le Guide adverse est sacrifi�, sur Nuit le votre est sacrifi�, sur N�ant rien ne se passe. Les capacit�s sp�ciales sont jou�es normalement.",
				new Inquisition());
		toutesCartesAction.add(inquisition);

		// Apocalypse
		Apocalypse apocalypse1 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse1", cartes.Origine.Jour,
				new Capacit�Apocalypse());
		toutesCartesAction.add(apocalypse1);
		Apocalypse apocalypse2 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse2", cartes.Origine.Nuit,
				new Capacit�Apocalypse());
		toutesCartesAction.add(apocalypse2);
		Apocalypse apocalypse3 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse3", cartes.Origine.N�ant,
				new Capacit�Apocalypse());
		toutesCartesAction.add(apocalypse3);
		Apocalypse apocalypse4 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse4", cartes.Origine.Nul,
				new Capacit�Apocalypse());
		toutesCartesAction.add(apocalypse4);
		Apocalypse apocalypse5 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse5", cartes.Origine.Nul,
				new Capacit�Apocalypse());
		toutesCartesAction.add(apocalypse5);

		return toutesCartesAction;
	}

	public static ArrayList<Divinit�> ConstruireDivinites() {

		ArrayList<Divinit�> toutesCartesDivinite = new ArrayList<Divinit�>();

		// Type Jour
		Divinit� Brewalen = new Divinit�(1, cartes.Type.Divinit�, "Brewalen", cartes.Origine.Jour, cartes.Dogme.Nature,
				cartes.Dogme.Humain, cartes.Dogme.Mystique,
				"Premier enfant du Jour, Brewalen cherche � repr�senter une certaine image de l'harmonie, et tente de mettre en place un statu quo entre les Divinit�s",
				"Peut emp�cher l'utilisation d'une carte Apocalypse. La carte est d�fauss�e.", new AnnuleApocalypse());
		toutesCartesDivinite.add(Brewalen);
		Divinit� Drinded = new Divinit�(2, cartes.Type.Divinit�, "Drinded", cartes.Origine.Jour, cartes.Dogme.Nature,
				cartes.Dogme.Humain, cartes.Dogme.Symbole,
				"D�fenseur des hommes, quelles que soient leurs croyances, Drinded prot�ge les chefs religieux",
				"Peut emp�cher le sacrifice d'un des Guides Spirituels de n'importe quel joueur.",
				new AnnuleSacrificeGuide());
		toutesCartesDivinite.add(Drinded);
		Divinit� Yarstur = new Divinit�(3, cartes.Type.Divinit�, "Yarstur", cartes.Origine.Jour, cartes.Dogme.Chaos,
				cartes.Dogme.Symbole, cartes.Dogme.Mystique,
				"Dernier pur du jour, Yarstur combat le N�ant sous toutes ses formes",
				"Peut d�truire toutes les cartes de Croyants au centre de la table d'Origine N�ant.",
				new SacrifierCroyantMilieuOrigine(cartes.Origine.N�ant));
		toutesCartesDivinite.add(Yarstur);
		// Type Nuit
		Divinit� Killinstred = new Divinit�(4, cartes.Type.Divinit�, "Killinstred", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, cartes.Dogme.Chaos,
				"Divinit� machiav�lique et manipulatrice, Killinstred cherche � influencer et contr�ler ses ennemis",
				"Peut obliger un joueur � poser une carte Apocalypse s'il en poss�de une.", new ForcerApocalypse());
		toutesCartesDivinite.add(Killinstred);
		Divinit� Llewella = new Divinit�(5, cartes.Type.Divinit�, "Llewella", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos,
				"Divinit� machiav�lique et manipulatrice, Killinstred cherche � influencer et contr�ler ses ennemis.",
				"Peut obliger un joueur � poser une carte Apocalypse s'il en poss�de une.", new ForcerApocalypse());
		toutesCartesDivinite.add(Llewella);
		Divinit� PuiTara = new Divinit�(6, cartes.Type.Divinit�, "Pui-Tara", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole,
				"Pui-Tara est la Divinit� sur laquelle l'influence dela Nuit s'est faite la plus forte.",
				"Peut d�truire toutes les cartes de Croyants au centre de la table d'Origine Jour.",
				new SacrifierCroyantMilieuOrigine(cartes.Origine.Jour));
		toutesCartesDivinite.add(PuiTara);
		// Type Aube
		Divinit� Gwenghelen = new Divinit�(7, cartes.Type.Divinit�, "Gwenghelen", cartes.Origine.Aube,
				cartes.Dogme.Humain, cartes.Dogme.Mystique, cartes.Dogme.Symbole,
				"Premi�re Divinit� � recevoir l'influence du N�ant, Gwenghelen est celle qui en a re�u le plus de puissance.",
				"R�cup�re autant de points d'Action suppl�mentaires d'Origine N�ant que le nombre de Guides Spirituels que la Divinit� poss�de.",
				new GagnerPointActionGwenghelen());
		toutesCartesDivinite.add(Gwenghelen);
		Divinit� Shingva = new Divinit�(8, cartes.Type.Divinit�, "Shingva", cartes.Origine.Aube, cartes.Dogme.Humain,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos,
				"Perverse et retorse, Shingva est une Divinit� que toutes les autres d�testent.",
				"Peut imposer le sacrifice d'un Guide Spirituel ayant le Dogme Symboles ou Nature.",
				new ImposeSacrificeGuide(cartes.Dogme.Symbole, cartes.Dogme.Nature));
		toutesCartesDivinite.add(Shingva);
		// Type Cr�puscule
		Divinit� Gorpa = new Divinit�(9, cartes.Type.Divinit�, "Gorpa", cartes.Origine.Cr�puscule, cartes.Dogme.Humain,
				cartes.Dogme.Symbole, cartes.Dogme.Chaos,
				"Divinit� joueuse et espi�gle, Gorpa aime g�ner ses cons�urs dans leur recherche de puissance.",
				"Peut r�cup�rer les points d'Action d'une autre Divinit� en plus des siens. L'autre Divinit� ne re�oit aucun point d'Action ce tour-ci.",
				new VolerPointActionEmpecherGagner());
		toutesCartesDivinite.add(Gorpa);
		Divinit� Romtec = new Divinit�(10, cartes.Type.Divinit�, "Romtec", cartes.Origine.Cr�puscule,
				cartes.Dogme.Nature, cartes.Dogme.Humain, cartes.Dogme.Chaos,
				"Romtec est une Divinit� individualiste, pour qui chaque �tre vivant doit garder son libre arbitre.",
				"Peut emp�cher un jour de cr�er un Guide Spirituel. La carte est d�fauss�e.", new AnnulePoseGuide());
		toutesCartesDivinite.add(Romtec);

		return toutesCartesDivinite;
	}
}
