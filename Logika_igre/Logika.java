package Logika_igre;

import java.util.LinkedList;
import java.util.List;

public class Logika {
	public static final int M = 6; /* Vi�ina igralne plo��e. */
	public static final int N = 7; /* �irina igralne plo��e. */
	public static final int A = 4; /* Dol�ina vrste za zmago. */
	
	public Igralec naPotezi; /* Igralec je tisti, ki je na potezi. */
	public Polje[][] plosca;
	
	 /**
	  * Vsi mo�ni �etvorci na plo��i.
	  */
	private static final List<Cetvorec> cetvorci = new LinkedList<Cetvorec>();
	
	{ /* Se izvede, ko se po�ene program */
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int [] smer : new int [][] {{1, 0}, {0, 1}, {1, 1}}) {
					int smer_i = smer[0];
					int smer_j = smer[1];
					if ((i + (A - 1) * smer_i < M) && (j + (A - 1) * smer_j < N)) {
						int [] x = new int [A];
						int [] y = new int [A];
						for (int k = 0; k < A; k++) {
							x[k] = i + smer_i * k;
							y[k] = j + smer_j * k;
						}
						cetvorci.add(new Cetvorec(x, y));
					}
				}
			}
		}
	novaIgra();
	}
	
	/**
	 * Na za�etku so vsa polja prazna, na potezi je O.
	 */	
	public void novaIgra() {
		naPotezi = Igralec.O;
		Polje [][] plosca = new Polje[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
	}
	
	/**
	 * Vrne true, �e je v stolpcu �e prosto mesto, in odigra potezo p;
	 * vrne false sicer.
	 */
	public boolean odigrajPotezo(Poteza p) {		
		int x = p.vrniX();
		int y = p.vrniY();
		if (plosca[x][y] == Polje.PRAZNO) {
			switch (naPotezi) {
				case O: plosca[x][y] = Polje.O;
				case X: plosca[x][y] = Polje.X;
				default: System.out.println("Nih�e ni na potezi");
			naPotezi = naPotezi.nasprotnik();
			return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Ali je �etvorec zmagovalen, oz. ali so vsa polja �etvorca enaka p?
	 */
	
	public boolean zmagovalniCetvorec(Cetvorec z, Polje p) {
		for (int i = 0; i < A; i++) {
			if (plosca[z.x[i]][z.y[i]] != p) {return false;}
		}
		return true;
	}
	
	/**
	 * Ali je igre konec? Konec je, ko na plo��i obstaja �etvorec,
	 * katerega vsa polja so O ali X.
	 */
	
	public Stanje stanjeIgre() {
		for (Cetvorec z : cetvorci) {
			if (zmagovalniCetvorec(z, Polje.O)) {
				return Stanje.ZMAGA_O;
			}
			else if (zmagovalniCetvorec(z, Polje.X)) {
				return Stanje.ZMAGA_X;
			} else {continue;}
		}
		/* Ker je pri tej igri mogo� tudi remi, je treba preveriti, �e je kak�no polje �e nezasedeno. */
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca [i][j] == Polje.PRAZNO) {
					if (naPotezi == Igralec.O) {
						return Stanje.NA_POTEZI_O;
					} else {
						return Stanje.NA_POTEZI_X;
					}
				}
			}
		}
		/* Zmagovalca ni, vsa polja pa so neprazna. */
		return Stanje.NEODLOCENO;
	}
	
}
