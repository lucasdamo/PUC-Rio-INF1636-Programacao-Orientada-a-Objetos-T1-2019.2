package gui;

import regras.*;

import java.awt.*;
import javax.swing.*;
import observador.*;

public class FRBatalhaNaval extends JFrame implements EscutaCliqueCampoBatalha {
	Facade facade;
	public FRBatalhaNaval(Facade f) {
		PNCampoDeBatalha tabEsq;
		PNCampoDeBatalha tabDir;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		this.facade = f;
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		tabEsq = new PNCampoDeBatalha(0, 20, (wid/3), 2*hei/3);
		tabEsq.setJog(f.getJogador1());
		tabDir = new PNCampoDeBatalha(wid/2, 20, (wid/3), 2*hei/3);
		tabDir.setJog(f.getJogador2());
		tabEsq.addCliqueListener(this);
		tabDir.addCliqueListener(this);
		getContentPane().add(tabEsq);
		getContentPane().add(tabDir);
		facade.addObserver(tabEsq);
		facade.addObserver(tabDir);
		setTitle("Batalha Naval");
	}
	
	public void recebeClique(int x, int y, Jogador jogador) {
		facade.atira(x, y, jogador);
	}
}
