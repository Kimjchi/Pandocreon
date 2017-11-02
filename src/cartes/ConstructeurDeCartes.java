package cartes;

import java.util.ArrayList;
import cartes.capacités.*;

public abstract class ConstructeurDeCartes {

	// La classe abstraite ConstructeurDeCarte va construire les decks de cartes
	// action et de divinités.

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
				"Sacrifice :  Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu.",
				new EmpêcheSacrificeCroyant(cartes.Dogme.Mystique, cartes.Dogme.Nature));
		toutesCartesAction.add(travailleurs1);
		Croyant travailleurs2 = new Croyant(7, cartes.Type.Croyant, "Travailleurs2", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Nature, cartes.Dogme.Symbole, 2,
				"Sacrifice :  Empêche une Divinité possédant le Dogme Chaos ou Mystique de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",
				new EmpêcheSacrificeGuide(cartes.Dogme.Mystique, cartes.Dogme.Chaos));
		toutesCartesAction.add(travailleurs2);
		Croyant travailleurs3 = new Croyant(8, cartes.Type.Croyant, "Travailleurs3", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Mystique, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
				new PiocherMainAutreJoueur(2));
		toutesCartesAction.add(travailleurs3);

		Croyant ermite1 = new Croyant(9, cartes.Type.Croyant, "Ermite1", cartes.Origine.Jour, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos, 1,
				"Sacrifice : Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(ermite1);
		Croyant ermite2 = new Croyant(10, cartes.Type.Croyant, "Ermite2", cartes.Origine.Jour, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, 1,
				"Sacrifice : Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(ermite2);

		Croyant intégristes = new Croyant(11, cartes.Type.Croyant, "Integristes", cartes.Origine.Jour,
				cartes.Dogme.Nature, cartes.Dogme.Humain, cartes.Dogme.Chaos, 1,
				"Sacrifice : Impose le sacrifice d'un Guide Spirituel d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée.",
				new Devin());
		toutesCartesAction.add(intégristes);

		Croyant guerriersSaints = new Croyant(12, cartes.Type.Croyant, "Guerriers Saints", cartes.Origine.Jour,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, cartes.Dogme.Symbole, 4,
				"Sacrifice : Un Guide Spirituel revient dans la main de sa Divinité. SesCroyants reviennent au centre de la table.",
				new RetourGuideEnMain());
		toutesCartesAction.add(guerriersSaints);

		Croyant diplomates = new Croyant(13, cartes.Type.Croyant, "Diplomates", cartes.Origine.Jour,
				cartes.Dogme.Symbole, cartes.Dogme.Humain, cartes.Dogme.Chaos, 4,
				"Sacrifice : Relancez le dé de Cosmogonie. Le tour se finit normalement sous la nouvelle influence.",
				new RelancerDé());
		toutesCartesAction.add(diplomates);

		// Type Nuit
		Croyant démons1 = new Croyant(14, cartes.Type.Croyant, "Demons1", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Humain, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(démons1);
		Croyant démons2 = new Croyant(15, cartes.Type.Croyant, "Demons2", cartes.Origine.Nuit, cartes.Dogme.Chaos,
				cartes.Dogme.Mystique, cartes.Dogme.Humain, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(démons2);
		Croyant démons3 = new Croyant(16, cartes.Type.Croyant, "Demons3", cartes.Origine.Nuit, cartes.Dogme.Symbole,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(démons3);
		Croyant démons4 = new Croyant(17, cartes.Type.Croyant, "Demons4", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(démons4);
		Croyant démons5 = new Croyant(18, cartes.Type.Croyant, "Demons5", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos, 2, "Sacrifice : Donne un point d'action d'origine nuit.",
				new GagnerPointAction(cartes.Origine.Nuit));
		toutesCartesAction.add(démons5);

		Croyant alchimistes1 = new Croyant(19, cartes.Type.Croyant, "Alchimistes1", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Symbole, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Empêche une Divinité possédant le Dogme Humain ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu.",
				new EmpêcheSacrificeCroyant(cartes.Dogme.Humain, cartes.Dogme.Mystique));
		toutesCartesAction.add(alchimistes1);
		Croyant alchimistes2 = new Croyant(20, cartes.Type.Croyant, "Alchimistes2", cartes.Origine.Nuit,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Empêche une Divinité possédant le Dogme Nature ou Symbole de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",
				new EmpêcheSacrificeGuide(cartes.Dogme.Humain, cartes.Dogme.Symbole));
		toutesCartesAction.add(alchimistes2);
		Croyant alchimistes3 = new Croyant(21, cartes.Type.Croyant, "Alchimistes3", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Symbole, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Vous piochez au hasard deux cartes dans la main d'une autre Divinité.",
				new PiocherMainAutreJoueur(2));
		toutesCartesAction.add(alchimistes3);

		Croyant vampire1 = new Croyant(22, cartes.Type.Croyant, "Vampires1", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Symbole, cartes.Dogme.Humain, 1,
				"Sacrifice :  Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifié est jouée.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(vampire1);
		Croyant vampire2 = new Croyant(23, cartes.Type.Croyant, "Vampires2", cartes.Origine.Nuit, cartes.Dogme.Chaos,
				cartes.Dogme.Mystique, cartes.Dogme.Humain, 1,
				"Sacrifice :  Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifié est jouée.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(vampire2);

		Croyant lycanthropes = new Croyant(24, cartes.Type.Croyant, "Lycanthropes", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Humain, cartes.Dogme.Chaos, 4,
				"Sacrifice :  Retirez tous les Croyants attachés à l'un des Guides Spirituels d'une autre Divinité. Les Croyants reviennent au milieu de la table, le Guide Spirituel est défaussé. ",
				new DefausseGuideCroyantAuCentre());
		toutesCartesAction.add(lycanthropes);

		Croyant pillards = new Croyant(25, cartes.Type.Croyant, "Pillards", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, 4,
				"Sacrifice :  Récupérez les points d'Action d'une Divinité n'ayant pas encore joué durant ce tour. Les points d'Action gardent leur Origine. La Divinité perd ses points.",
				new VolerPointAction());
		toutesCartesAction.add(pillards);

		Croyant illusionnistes = new Croyant(26, cartes.Type.Croyant, "Illusionnistes", cartes.Origine.Nuit,
				cartes.Dogme.Chaos, cartes.Dogme.Humain, cartes.Dogme.Symbole, 4,
				"Sacrifice :  Vous bénéficiez de la capacité spéciale de sacrifice d'une carte de Crayants appartenant à une autre Divinité. La carte en question reste en jeu.",
				new VolerCapacitéAdversaire());
		toutesCartesAction.add(illusionnistes);

		// Type Chaos
		Croyant esprits1 = new Croyant(27, cartes.Type.Croyant, "Esprits1", cartes.Origine.Néant, cartes.Dogme.Mystique,
				cartes.Dogme.Humain, cartes.Dogme.Nature, 2, "Sacrifice :  Donne un point d'Action d'Origine Néant.",
				new GagnerPointAction(cartes.Origine.Néant));
		toutesCartesAction.add(esprits1);
		Croyant esprits2 = new Croyant(28, cartes.Type.Croyant, "Esprits2", cartes.Origine.Néant, cartes.Dogme.Mystique,
				cartes.Dogme.Humain, cartes.Dogme.Chaos, 2, "Sacrifice :  Donne un point d'Action d'Origine Néant.",
				new GagnerPointAction(cartes.Origine.Néant));
		toutesCartesAction.add(esprits2);
		Croyant esprits3 = new Croyant(29, cartes.Type.Croyant, "Esprits3", cartes.Origine.Néant, cartes.Dogme.Mystique,
				cartes.Dogme.Symbole, cartes.Dogme.Chaos, 2, "Sacrifice :  Donne un point d'Action d'Origine Néant.",
				new GagnerPointAction(cartes.Origine.Néant));
		toutesCartesAction.add(esprits3);
		Croyant esprits4 = new Croyant(30, cartes.Type.Croyant, "Esprits4", cartes.Origine.Néant, cartes.Dogme.Mystique,
				cartes.Dogme.Symbole, cartes.Dogme.Nature, 2, "Sacrifice :  Donne un point d'Action d'Origine Néant.",
				new GagnerPointAction(cartes.Origine.Néant));
		toutesCartesAction.add(esprits4);
		Croyant esprits5 = new Croyant(31, cartes.Type.Croyant, "Esprits5", cartes.Origine.Néant, cartes.Dogme.Mystique,
				cartes.Dogme.Chaos, cartes.Dogme.Nature, 2, "Sacrifice :  Donne un point d'Action d'Origine Néant.",
				new GagnerPointAction(cartes.Origine.Néant));
		toutesCartesAction.add(esprits5);

		Croyant aliénés1 = new Croyant(32, cartes.Type.Croyant, "Aliénés1", cartes.Origine.Néant, cartes.Dogme.Symbole,
				cartes.Dogme.Humain, cartes.Dogme.Chaos, 2,
				"Sacrifice :  Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier un de ses cartes de Croyants durant ce tour de jeu.",
				new EmpêcheSacrificeCroyant(cartes.Dogme.Mystique, cartes.Dogme.Nature));
		toutesCartesAction.add(aliénés1);
		Croyant aliénés2 = new Croyant(33, cartes.Type.Croyant, "Aliénés2", cartes.Origine.Néant, cartes.Dogme.Symbole,
				cartes.Dogme.Humain, cartes.Dogme.Nature, 2,
				"Sacrifice :  Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",
				new EmpêcheSacrificeGuide(cartes.Dogme.Mystique, cartes.Dogme.Chaos));
		toutesCartesAction.add(aliénés2);
		Croyant aliénés3 = new Croyant(34, cartes.Type.Croyant, "Aliénés3", cartes.Origine.Néant, cartes.Dogme.Mystique,
				cartes.Dogme.Humain, cartes.Dogme.Chaos, 2,
				"Sacrifice : Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
				new PiocherMainAutreJoueur(2));
		toutesCartesAction.add(aliénés3);

		Croyant revenant = new Croyant(35, cartes.Type.Croyant, "Revenant", cartes.Origine.Néant, cartes.Dogme.Mystique,
				cartes.Dogme.Humain, cartes.Dogme.Nature, 4,
				"Sacrifice : Lancez le dé de Cosmogonie. Le tour se fait normalement mais sous cette nouvelle influence",
				new RelancerDé());
		toutesCartesAction.add(revenant);

		Croyant révolutionnaires = new Croyant(36, cartes.Type.Croyant, "Révolutionnaires", cartes.Origine.Néant,
				cartes.Dogme.Symbole, cartes.Dogme.Humain, cartes.Dogme.Nature, 4,
				"Sacrifice : Imposez le sacrifice d'une carte de Croyant à autant de Divinités que vous le voulez. Chaque Divinité choisit la carte à sacrifier. Les capacités spéciales sont jouées.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(révolutionnaires);

		Croyant nihilistes = new Croyant(37, cartes.Type.Croyant, "Nihillistes", cartes.Origine.Néant,
				cartes.Dogme.Symbole, cartes.Dogme.Mystique, cartes.Dogme.Chaos, 4,
				"Sacrifice : Jusqu'à la fin du tour, plus aucune Divinité nerecoit de points d'Action.",
				new EmpêcheGagnerPA());
		toutesCartesAction.add(nihilistes);

		//Guides Spirituels
		GuideSpirituel martyr1 = new GuideSpirituel(38, cartes.Type.GuideSpirituel, "Martyr1", cartes.Origine.Jour,
				cartes.Dogme.Nature, cartes.Dogme.Humain, 2, "Sacrifice : Equivalent à la pose d'une carte Apocalypse",
				new CapacitéApocalypse());
		toutesCartesAction.add(martyr1);
		GuideSpirituel martyr2 = new GuideSpirituel(39, cartes.Type.GuideSpirituel, "Martyr2", cartes.Origine.Nuit,
				cartes.Dogme.Symbole, cartes.Dogme.Humain, 2, "Sacrifice : Equivalent à la pose d'une carte Apocalypse",
				new CapacitéApocalypse());
		toutesCartesAction.add(martyr2);
		GuideSpirituel martyr3 = new GuideSpirituel(40, cartes.Type.GuideSpirituel, "Martyr3", cartes.Origine.Néant,
				cartes.Dogme.Nature, cartes.Dogme.Chaos, 2, "Sacrifice : Equivalent à la pose d'une carte Apocalypse",
				new CapacitéApocalypse());
		toutesCartesAction.add(martyr3);

		GuideSpirituel clerc1 = new GuideSpirituel(41, cartes.Type.GuideSpirituel, "Clerc1", cartes.Origine.Jour,
				cartes.Dogme.Chaos, cartes.Dogme.Humain, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc1);
		GuideSpirituel clerc2 = new GuideSpirituel(42, cartes.Type.GuideSpirituel, "Clerc2", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Symbole, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc2);
		GuideSpirituel clerc3 = new GuideSpirituel(43, cartes.Type.GuideSpirituel, "Clerc3", cartes.Origine.Néant,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc3);
		GuideSpirituel clerc4 = new GuideSpirituel(44, cartes.Type.GuideSpirituel, "Clerc4", cartes.Origine.Jour,
				cartes.Dogme.Symbole, cartes.Dogme.Mystique, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc4);
		GuideSpirituel clerc5 = new GuideSpirituel(45, cartes.Type.GuideSpirituel, "Clerc5", cartes.Origine.Nuit,
				cartes.Dogme.Chaos, cartes.Dogme.Symbole, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc5);
		GuideSpirituel clerc6 = new GuideSpirituel(46, cartes.Type.GuideSpirituel, "Clerc6", cartes.Origine.Néant,
				cartes.Dogme.Chaos, cartes.Dogme.Mystique, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc6);
		GuideSpirituel clerc7 = new GuideSpirituel(47, cartes.Type.GuideSpirituel, "Clerc7", cartes.Origine.Jour,
				cartes.Dogme.Nature, cartes.Dogme.Humain, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc7);
		GuideSpirituel clerc8 = new GuideSpirituel(48, cartes.Type.GuideSpirituel, "Clerc8", cartes.Origine.Nuit,
				cartes.Dogme.Chaos, cartes.Dogme.Nature, 2,
				"Sacrifice : Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur.",
				new GagnerPointsActionClerc());
		toutesCartesAction.add(clerc8);

		GuideSpirituel shaman = new GuideSpirituel(49, cartes.Type.GuideSpirituel, "Shaman", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Symbole, 3,
				"Sacrifice : Sacrifie tous les Croyants d'Origine Néant d'une Divinité ayant le Dogme Humain. Les capacités spéciales sont jouées normalement.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(shaman);
		GuideSpirituel anarchiste = new GuideSpirituel(50, cartes.Type.GuideSpirituel, "Anarchiste",
				cartes.Origine.Néant, cartes.Dogme.Humain, cartes.Dogme.Chaos, 3,
				"Sacrifice : Sacrifie un Guide Spirituel, si lui ou sa Divinité ne croit au Dgme Chaos. Les capacités spéciales sont jouées normalement.",
				new Anarchiste());
		toutesCartesAction.add(anarchiste);
		GuideSpirituel paladin = new GuideSpirituel(51, cartes.Type.GuideSpirituel, "Paladin", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Mystique, 3,
				"Sacrifice : Tous les Croyants d'Origine Néant ou Néant et ayant le Dogme Nature, actuellement sur la table sont défaussés. Les capacités spéciales ne sont pas jouées.",
				new SacrifierCroyantMilieuTableDogmeetOrigine());
		toutesCartesAction.add(paladin);

		GuideSpirituel ascète = new GuideSpirituel(52, cartes.Type.GuideSpirituel, "Ascète", cartes.Origine.Nuit,
				cartes.Dogme.Humain, cartes.Dogme.Symbole, 1,
				"Sacrifice : Sacrifie 2 cartes Croyants d'une Divinité ayant ayant le Dogme Humain ou Symboles. Les capacités spéciales sont jouées normalement.",
				new ImposeSacrificeCroyant());
		toutesCartesAction.add(ascète);
		GuideSpirituel devin = new GuideSpirituel(53, cartes.Type.GuideSpirituel, "Devin", cartes.Origine.Néant,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, 1,
				"Sacrifice : Oblige une Divinité ayant le Dogme Nature ou Mystique à sacrifier l'un de ses Guides Spirituels. Les capacités spéciales sont jouées normalement.",
				new Devin());
		toutesCartesAction.add(devin);
		GuideSpirituel exorciste = new GuideSpirituel(54, cartes.Type.GuideSpirituel, "Exorciste", cartes.Origine.Jour,
				cartes.Dogme.Chaos, cartes.Dogme.Mystique, 1,
				"Sacrifice : Une Divinité d'Origine Nuit ou ayant les Dogmes Mystique et Chaos reprend dans sa main l'un de ses Guides Spirituels. Les Croyants qui y étaient attachés sont défaussés.",
				new RetourGuideMainCroyantDefaussé());
		toutesCartesAction.add(exorciste);

		GuideSpirituel sorcier = new GuideSpirituel(55, cartes.Type.GuideSpirituel, "Sorcier", cartes.Origine.Nuit,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole, 3,
				"Sacrifice : Echangez l'un de vos Guides Spirituels avec un d'une autre Divinité. Vous choisissez les deux guides Spirituels en question. Les Croyants restent attachés aux mêmes cartes.",
				new EchangeGuidesSpirituels());
		toutesCartesAction.add(sorcier);
		GuideSpirituel tyran = new GuideSpirituel(56, cartes.Type.GuideSpirituel, "Tyran", cartes.Origine.Néant,
				cartes.Dogme.Chaos, cartes.Dogme.Symbole, 3,
				"Sacrifice : Défausse tous les Croyants ayant le Dogme Mystique actuellement au centre de la table.",
				new SacrifierCroyantMilieuDogme());
		toutesCartesAction.add(tyran);
		GuideSpirituel messie = new GuideSpirituel(57, cartes.Type.GuideSpirituel, "Messie", cartes.Origine.Jour,
				cartes.Dogme.Humain, cartes.Dogme.Mystique, 3,
				"Sacrifice : Le joueur pose le dé de Cosmogonie sur la face qu'il désire et commence un nouveau tour de jeu.",
				new ChoisirDe());
		toutesCartesAction.add(messie);

		// Deus Ex
		// Avec Origine
		DeusEx colèreDivine1 = new DeusEx(58, cartes.Type.DeusEx, "ColèreDivine1", cartes.Origine.Jour,
				"Sacrifice : Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.",
				new CapacitéColèreDivine(cartes.Origine.Nuit));
		toutesCartesAction.add(colèreDivine1);
		DeusEx colèreDivine2 = new DeusEx(59, cartes.Type.DeusEx, "ColèreDivine2", cartes.Origine.Nuit,
				"Sacrifice : Détruit une carte Guide Spirituel d'Origine Jour ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.",
				new CapacitéColèreDivine(cartes.Origine.Jour));
		toutesCartesAction.add(colèreDivine2);

		DeusEx stase = new DeusEx(60, cartes.Type.DeusEx, "Stase", cartes.Origine.Jour,
				"Sacrifice : Protège un Guide Spirituel et ses Croyants jusqu'à ce que cette carte soit annulée ou jusqu'à la prochaine tentative d'Apocalypse.",
				new Stase());
		toutesCartesAction.add(stase);
		DeusEx ordreCéleste = new DeusEx(61, cartes.Type.DeusEx, "OrdreCéleste", cartes.Origine.Jour,
				"Sacrifice : Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés",
				new VolerGuideSpirituelEtCroyants());
		toutesCartesAction.add(ordreCéleste);

		DeusEx fourberie = new DeusEx(62, cartes.Type.DeusEx, "Fourberie", cartes.Origine.Nuit,
				"Sacrifice : Sacrifiez 2 cartes Croyants d'une autre Divinité. Les capacités spéciales ne sont pas jouées.",
				new SacrifierCroyantSansCapacité());
		toutesCartesAction.add(fourberie);
		DeusEx diversion = new DeusEx(63, cartes.Type.DeusEx, "Diversion", cartes.Origine.Nuit,
				"Sacrifice : Prenez 3 cartes dans la main d'un autre joueur et incluez-les à votre main.",
				new PiocherMainAutreJoueur(3));
		toutesCartesAction.add(diversion);

		DeusEx concentration = new DeusEx(64, cartes.Type.DeusEx, "Concentration", cartes.Origine.Néant,
				"Sacrifice : Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.",
				new VolerGuideSpirituelEtCroyants());
		toutesCartesAction.add(concentration);
		DeusEx trouNoir = new DeusEx(65, cartes.Type.DeusEx, "TrouNoir", cartes.Origine.Néant,
				"Sacrifice : Aucun autre joueur ne gagne de points d'Action durant ce tour.",
				new EmpêcherGagnerPATouSLesJoueurs());
		toutesCartesAction.add(trouNoir);
		DeusEx phoenix = new DeusEx(66, cartes.Type.DeusEx, "Phoenix", cartes.Origine.Néant,
				"Sacrifice : Permet de bénéficier de la capacité spéciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.",
				new ActiverEffetSansDetruire());
		toutesCartesAction.add(phoenix);

		// Sans Origine
		DeusEx influenceJour = new DeusEx(67, cartes.Type.DeusEx, "InfluenceJour", cartes.Origine.Nul,
				"Sacrifice : Annule la capacité spéciale d'une carte d'Action d'Origine Nuit ou Néant.",
				new AnnulerCapacité(cartes.Origine.Nuit, cartes.Origine.Néant));
		toutesCartesAction.add(influenceJour);
		DeusEx influenceNuit = new DeusEx(68, cartes.Type.DeusEx, "InfluenceNuit", cartes.Origine.Nul,
				"Sacrifice : Annule la capacité spéciale d'une carte d'Action d'Origine Jour ou Néant.",
				new AnnulerCapacité(cartes.Origine.Jour, cartes.Origine.Néant));
		toutesCartesAction.add(influenceNuit);
		DeusEx influenceNéant = new DeusEx(69, cartes.Type.DeusEx, "InfluenceNéant", cartes.Origine.Nul,
				"Sacrifice : Annule la capacité spéciale d'une carte d'Action d'Origine Jour ou Nuit.",
				new AnnulerCapacité(cartes.Origine.Nuit, cartes.Origine.Jour));
		toutesCartesAction.add(influenceNéant);
		DeusEx influenceNulle1 = new DeusEx(70, cartes.Type.DeusEx, "InfluenceNulle1", cartes.Origine.Nul,
				"Sacrifice : Annule la capacité spéciale d'une carte d'Action.",
				new AnnulerCapacité(cartes.Origine.Nul, cartes.Origine.Nul));
		toutesCartesAction.add(influenceNulle1);
		DeusEx influenceNulle2 = new DeusEx(71, cartes.Type.DeusEx, "InfluenceNulle2", cartes.Origine.Nul,
				"Sacrifice : Annule la capacité spéciale d'une carte d'Action.",
				new AnnulerCapacité(cartes.Origine.Nul, cartes.Origine.Nul));
		toutesCartesAction.add(influenceNulle2);

		DeusEx transe = new DeusEx(72, cartes.Type.DeusEx, "Transe", cartes.Origine.Nul,
				"Sacrifice : Permet de récupérer les effets bénéfiques d'une carte d'Action posée par une autre Divinité.",
				new Miroir());
		toutesCartesAction.add(transe);
		DeusEx miroir = new DeusEx(73, cartes.Type.DeusEx, "Miroir", cartes.Origine.Nul,
				"Sacrifice : Retourne les effets d'une carte d'Action sur la Divinité qui l'a posée.", new Miroir());
		toutesCartesAction.add(miroir);
		DeusEx boulversement = new DeusEx(74, cartes.Type.DeusEx, "Boulversement", cartes.Origine.Nul,
				"Sacrifice : Relancez le dé de Cosmogonie. Le tour de jeu se terminera normalement, mais sous la nouvelle influence.",
				new RelancerDé());
		toutesCartesAction.add(boulversement);
		DeusEx inquisition = new DeusEx(75, cartes.Type.DeusEx, "Inquisition", cartes.Origine.Nul,
				"Sacrifice : Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. Lancez le dé de Cosmogonie. Sur Jour, le Guide adverse est sacrifié, sur Nuit le votre est sacrifié, sur Néant rien ne se passe. Les capacités spéciales sont jouées normalement.",
				new Inquisition());
		toutesCartesAction.add(inquisition);

		// Apocalypse
		Apocalypse apocalypse1 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse1", cartes.Origine.Jour,
				new CapacitéApocalypse());
		toutesCartesAction.add(apocalypse1);
		Apocalypse apocalypse2 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse2", cartes.Origine.Nuit,
				new CapacitéApocalypse());
		toutesCartesAction.add(apocalypse2);
		Apocalypse apocalypse3 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse3", cartes.Origine.Néant,
				new CapacitéApocalypse());
		toutesCartesAction.add(apocalypse3);
		Apocalypse apocalypse4 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse4", cartes.Origine.Nul,
				new CapacitéApocalypse());
		toutesCartesAction.add(apocalypse4);
		Apocalypse apocalypse5 = new Apocalypse(76, cartes.Type.Apocalypse, "Apocalypse5", cartes.Origine.Nul,
				new CapacitéApocalypse());
		toutesCartesAction.add(apocalypse5);

		return toutesCartesAction;
	}

	public static ArrayList<Divinité> ConstruireDivinites() {

		ArrayList<Divinité> toutesCartesDivinite = new ArrayList<Divinité>();

		// Type Jour
		Divinité Brewalen = new Divinité(1, cartes.Type.Divinité, "Brewalen", cartes.Origine.Jour, cartes.Dogme.Nature,
				cartes.Dogme.Humain, cartes.Dogme.Mystique,
				"Premier enfant du Jour, Brewalen cherche à représenter une certaine image de l'harmonie, et tente de mettre en place un statu quo entre les Divinités",
				"Peut empêcher l'utilisation d'une carte Apocalypse. La carte est défaussée.", new AnnuleApocalypse());
		toutesCartesDivinite.add(Brewalen);
		Divinité Drinded = new Divinité(2, cartes.Type.Divinité, "Drinded", cartes.Origine.Jour, cartes.Dogme.Nature,
				cartes.Dogme.Humain, cartes.Dogme.Symbole,
				"Défenseur des hommes, quelles que soient leurs croyances, Drinded protège les chefs religieux",
				"Peut empêcher le sacrifice d'un des Guides Spirituels de n'importe quel joueur.",
				new AnnuleSacrificeGuide());
		toutesCartesDivinite.add(Drinded);
		Divinité Yarstur = new Divinité(3, cartes.Type.Divinité, "Yarstur", cartes.Origine.Jour, cartes.Dogme.Chaos,
				cartes.Dogme.Symbole, cartes.Dogme.Mystique,
				"Dernier pur du jour, Yarstur combat le Néant sous toutes ses formes",
				"Peut détruire toutes les cartes de Croyants au centre de la table d'Origine Néant.",
				new SacrifierCroyantMilieuOrigine(cartes.Origine.Néant));
		toutesCartesDivinite.add(Yarstur);
		// Type Nuit
		Divinité Killinstred = new Divinité(4, cartes.Type.Divinité, "Killinstred", cartes.Origine.Nuit,
				cartes.Dogme.Nature, cartes.Dogme.Mystique, cartes.Dogme.Chaos,
				"Divinité machiavélique et manipulatrice, Killinstred cherche à influencer et contrôler ses ennemis",
				"Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.", new ForcerApocalypse());
		toutesCartesDivinite.add(Killinstred);
		Divinité Llewella = new Divinité(5, cartes.Type.Divinité, "Llewella", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos,
				"Divinité machiavélique et manipulatrice, Killinstred cherche à influencer et contrôler ses ennemis.",
				"Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.", new ForcerApocalypse());
		toutesCartesDivinite.add(Llewella);
		Divinité PuiTara = new Divinité(6, cartes.Type.Divinité, "Pui-Tara", cartes.Origine.Nuit, cartes.Dogme.Nature,
				cartes.Dogme.Mystique, cartes.Dogme.Symbole,
				"Pui-Tara est la Divinité sur laquelle l'influence dela Nuit s'est faite la plus forte.",
				"Peut détruire toutes les cartes de Croyants au centre de la table d'Origine Jour.",
				new SacrifierCroyantMilieuOrigine(cartes.Origine.Jour));
		toutesCartesDivinite.add(PuiTara);
		// Type Aube
		Divinité Gwenghelen = new Divinité(7, cartes.Type.Divinité, "Gwenghelen", cartes.Origine.Aube,
				cartes.Dogme.Humain, cartes.Dogme.Mystique, cartes.Dogme.Symbole,
				"Première Divinité à recevoir l'influence du Néant, Gwenghelen est celle qui en a reçu le plus de puissance.",
				"Récupère autant de points d'Action supplémentaires d'Origine Néant que le nombre de Guides Spirituels que la Divinité possède.",
				new GagnerPointActionGwenghelen());
		toutesCartesDivinite.add(Gwenghelen);
		Divinité Shingva = new Divinité(8, cartes.Type.Divinité, "Shingva", cartes.Origine.Aube, cartes.Dogme.Humain,
				cartes.Dogme.Mystique, cartes.Dogme.Chaos,
				"Perverse et retorse, Shingva est une Divinité que toutes les autres détestent.",
				"Peut imposer le sacrifice d'un Guide Spirituel ayant le Dogme Symboles ou Nature.",
				new ImposeSacrificeGuide(cartes.Dogme.Symbole, cartes.Dogme.Nature));
		toutesCartesDivinite.add(Shingva);
		// Type Crépuscule
		Divinité Gorpa = new Divinité(9, cartes.Type.Divinité, "Gorpa", cartes.Origine.Crépuscule, cartes.Dogme.Humain,
				cartes.Dogme.Symbole, cartes.Dogme.Chaos,
				"Divinité joueuse et espiègle, Gorpa aime gêner ses consœurs dans leur recherche de puissance.",
				"Peut récupérer les points d'Action d'une autre Divinité en plus des siens. L'autre Divinité ne reçoit aucun point d'Action ce tour-ci.",
				new VolerPointActionEmpecherGagner());
		toutesCartesDivinite.add(Gorpa);
		Divinité Romtec = new Divinité(10, cartes.Type.Divinité, "Romtec", cartes.Origine.Crépuscule,
				cartes.Dogme.Nature, cartes.Dogme.Humain, cartes.Dogme.Chaos,
				"Romtec est une Divinité individualiste, pour qui chaque être vivant doit garder son libre arbitre.",
				"Peut empêcher un jour de créer un Guide Spirituel. La carte est défaussée.", new AnnulePoseGuide());
		toutesCartesDivinite.add(Romtec);

		return toutesCartesDivinite;
	}
}
