package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;

/**
 * Interface permettant � un composant graphique quelconque d'utiliser des
 * constantes de couleurs et polices pr�-configur�es. Cette interface est
 * uniquement porteuse de constantes.
 *
 */
public interface ThemeFonc� {
	/**
	 * Largeur standard de la fen�tre de jeu.
	 */
	public static final int WIDTH = 1600;

	/**
	 * Hauteur standard de la fen�tre de jeu.
	 */
	public static final int HEIGHT = 900;

	/**
	 * Couleur sombre de fond.
	 */
	public static final Color DARK_BG = new Color(51, 51, 51);

	/**
	 * Couleur claire d'�criture.
	 */
	public static final Color LIGHT_FG = new Color(230, 230, 230);

	/**
	 * Couleur des bordures.
	 */
	public static final Color BORDER_COLOR = new Color(95, 95, 95);

	/**
	 * Couleur d'accentuation/contraste principale (fond).
	 */
	public static final Color ACCENT_1 = new Color(85, 85, 85);

	/**
	 * Variante de la couleur d'accentation/contraste (fond).
	 */
	public static final Color ACCENT_2 = new Color(70, 70, 70);

	/**
	 * Couleur d'accentuation/contraste d'�criture.
	 */
	public static final Color ACCENT_FG = new Color(200, 200, 200);

	/**
	 * Police d'�criture pour les titres.
	 */
	public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 11);

	/**
	 * Police d'�criture pour les contenus normaux.
	 */
	public static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 11);
}
