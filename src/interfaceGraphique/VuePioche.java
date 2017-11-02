package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import cartes.ConstructeurDeCartes;
import partie.Partie;
import partie.PhaseDeJeu;

public class VuePioche extends JButton implements Observer, ThemeFoncé, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage image;

	private JLabel labelInfo;

	public VuePioche() {
		this.addMouseListener(this);

		this.image = null;

		this.setToolTipText("Cliquez pour completer votre main.");
		this.labelInfo = new JLabel("Pioche");
		this.labelInfo.setToolTipText("Pioche");
		this.labelInfo.setVerticalAlignment(SwingConstants.TOP);
		this.labelInfo.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.labelInfo.setHorizontalAlignment(SwingConstants.LEFT);
		this.labelInfo.setForeground(LIGHT_FG);

		String path = "src/resources/images/DosDeCarte.png";
		File file = new File(path);
		try {
			this.image = ImageIO.read(file);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIcon(new ImageIcon(image));
		this.add(labelInfo, "cell 0 0 2 1,alignx left,aligny top");

		this.setText("Pioche");
		// this.setSize(484, 343);
		// this.add(new JButton ("Completer sa main"));

	}

	public Dimension getPreferredSize() {
		return new Dimension(484, 343);
	}

	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		g2d.setPaint(gp);
		g2d.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.setColor(LIGHT_FG);
		g2d.setFont(new Font("SansSerif", Font.BOLD, 20));

		g2d.drawString("Pioche", 60, 50);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (Partie.getInstance().getPiocheCA().getCartes().isEmpty()) {
			Partie.getInstance().getPiocheCA().ajouterCartes(ConstructeurDeCartes.construireDeck()); // On

			Partie.getInstance().getPiocheCA().remettreCartesEtmélanger();
		}

		if (Partie.getInstance().getPhaseDeJeu() == PhaseDeJeu.PhasePioche) {

			Partie.getInstance().getJReel().getMainJoueur().remplirMain(Partie.getInstance().getPiocheCA());
		} else {
			JOptionPane.showMessageDialog(this,
					"Vous devez attendre la phase de pioche pour pouvoir completer votre main.", "Action impossible",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}