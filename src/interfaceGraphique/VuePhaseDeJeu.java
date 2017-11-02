package interfaceGraphique;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import partie.Partie;

public class VuePhaseDeJeu extends JPanel implements ThemeFoncé, Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VuePhaseDeJeu() {
		Partie.getInstance().addObserver(this);
		JLabel labelPhase = new JLabel(Partie.getInstance().getPhaseDeJeu().toString());
		this.setBackground(ACCENT_1);

		labelPhase.setBackground(ACCENT_1);
		labelPhase.setFont(UIManager.getFont("Table.font"));
		labelPhase.setForeground(LIGHT_FG);
		this.add(labelPhase);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.removeAll();
		JLabel labelPhase = new JLabel(Partie.getInstance().getPhaseDeJeu().toString());
		labelPhase.setBackground(ACCENT_1);
		labelPhase.setFont(UIManager.getFont("Table.font"));
		labelPhase.setForeground(LIGHT_FG);
		this.add(labelPhase);
		this.repaint();
		this.revalidate();
	}
}
