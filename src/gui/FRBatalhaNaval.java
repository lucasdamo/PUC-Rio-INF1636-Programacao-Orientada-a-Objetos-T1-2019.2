package gui;

import regras.*;

import java.awt.*;
import javax.swing.*;
import observador.*;

public class FRBatalhaNaval extends JFrame implements EscutaCliqueCampoBatalha {
	Facade facade;
	JLabel labelJogadorDaVez;
	JLabel numeroDeTirosRestantes;
	public FRBatalhaNaval(Facade f) {
		PNCampoDeBatalha tabEsq;
		PNCampoDeBatalha tabDir;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		this.facade = f;
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		this.setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		tabEsq = new PNCampoDeBatalha(0, 20, (wid/3), 2*hei/3);
		tabEsq.setJog(f.getJogador1());
		tabDir = new PNCampoDeBatalha(wid/2, 20, (wid/3), 2*hei/3);
		tabDir.setJog(f.getJogador2());
		tabEsq.addCliqueListener(this);
		tabDir.addCliqueListener(this);
		labelJogadorDaVez = new JLabel("Vez do jogador: " + f.getJogadorDaVez().getNome());
		numeroDeTirosRestantes = new JLabel("Numero de tiros restantes: " + f.getNumeroTirosRestantes());
		System.out.print("BOunds = " + (int)(wid/2) + "," + (int)(hei-30) + ", 200, 50\n");
		labelJogadorDaVez.setBounds((int)(wid/2) - 200, (int)(hei-200), 400, 100);
		numeroDeTirosRestantes.setBounds((int)(wid/2) - 200, (int)(hei-150), 400, 100);
		getContentPane().add(tabEsq);
		getContentPane().add(tabDir);
		getContentPane().add(labelJogadorDaVez);
		getContentPane().add(numeroDeTirosRestantes);
		facade.addObserver(tabEsq);
		facade.addObserver(tabDir);
		setTitle("Batalha Naval");
		repaint();
	}
	private void changeLabelJogadorDaVez() {
		labelJogadorDaVez.setText("Vez do jogador: " + facade.getJogadorDaVez().getNome());
		numeroDeTirosRestantes.setText("Numero de tiros restantes: " + facade.getNumeroTirosRestantes());
	}
	
	public void recebeClique(int x, int y, Jogador jogador) {
		facade.atira(x, y, jogador);
		changeLabelJogadorDaVez();
	}
}
