package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import Logika_igre.*;

public class Okno extends JFrame implements ActionListener {
	
	/**
	 * Statusna vrstica.
	 */
	private JLabel vrstica;
	
	/**
	 * Logika igre.
	 */
	private Logika igra;
	
	private Mislec MislecO; /* Vle�e poteze O */
	private Mislec MislecX; /* Vle�e poteze X */
	
	private JMenuItem RacunalnikClovek;
	private JMenuItem RacunalnikRacunalnik;
	private JMenuItem ClovekClovek;
	
	/**
	 * Glavno okno, ki se ustvari na za�etku. Tudi po�ene igro.
	 */
	public Okno() {
		this.setTitle("�tiri v vrsto");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		/* Polje. */
		Polje polje = new Polje(this);
		GridBadConstraints postavitev_polja = new GridBadConstraints();
		postavitev_polja.gridx = 0;
		postavitev_polja.gridy = 0;
		postavitev_polja.weightx = 1;
		postavitev_polja.weighty = 1;
		postavitev_polja.fill = GridBagConstraints.CENTER;
		
		getContentPane().add(polje, postavitev_polja);
		
		/* Menu. */
		JMenuBar menu_bar = new JMenuBar();
		this.setJMenuBar(menu_bar);
		JMenu menu_igra = new JMenu("Igra");
		menu_bar.add(menu_igra);
		
		/* Vsi �tirje mo�ni tipi igre, ki so na voljo v meniju */
		final JMenuItem ClovekRacunalnik = new JMenuItem("�lovek <> Ra�unalnik");
		menu_igra.add(ClovekRacunalnik);
		ClovekRacunalnik.addActionListener(this);
		
		final JMenuItem RacunalnikClovek = new JMenuItem("Ra�unalnik <> �lovek");
		menu_igra.add(RacunalnikClovek);
		RacunalnikClovek.addActionListener(this);
		
		final JMenuItem ClovekClovek = new JMenuItem("�lovek <> �lovek");
		menu_igra.add(ClovekClovek);
		ClovekClovek.addActionListener(this);
		
		final JMenuItem RacunalnikRacunalnik = new JMenuItem("Ra�unalnik <> Ra�unalnik");
		menu_igra.add(RacunalnikRacunalnik);
		RacunalnikRacunalnik.addActionListener(this);
		
		/* Po�enemo igro. */
	}
	
	/**
	 * Po�ene novo igro.
	 */
	public void novaIgra(Mislec IgralecO, Mislec IgralecX) {
		/* Prekinemo igralce. */
		if (MislecO != null) {MislecO.prekini();}
		if (MislecX != null) {MislecX.prekini();}
		this.igra = new Logika();
		MislecO = IgralecO;
		MislecX = IgralecX;
		switch (igra.stanjeIgre()) {
			case NA_POTEZI_O: MislecO.na_potezi(); break;
			case NA_POTEZI_X: MislecX.na_potezi(); break;
			default: break;
		}
		}
}
	
