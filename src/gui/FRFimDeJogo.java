package gui;

import regras.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import observador.*;

public class FRFimDeJogo extends JFrame {
	Facade facade;
	JLabel labelGanhador;
	JLabel numeroDeTirosRestantes;
	JButton botaoFinalizarVez;
	JButton botaoIniciarVez;
	PNCampoDeBatalha tabEsq;
	PNCampoDeBatalha tabDir;
	public FRFimDeJogo() {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		facade = Facade.getFacade();
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		this.setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		tabEsq = new PNCampoDeBatalha(0, 20, (wid/3), 2*hei/3);
		tabEsq.setJog(facade.getJogador1());
		tabDir = new PNCampoDeBatalha(wid/2, 20, (wid/3), 2*hei/3);
		tabDir.setJog(facade.getJogador2());
		labelGanhador = new JLabel("Parabens ao ganhador !" + facade.checaGanhador().getNome());
		numeroDeTirosRestantes = new JLabel("Numero de tiros restantes: " + f.getNumeroTirosRestantes());
		System.out.print("Bounds = " + (int)(wid/2) + "," + (int)(hei-30) + ", 200, 50\n");
		labelGanhador.setBounds((int)(wid/2) - 200, (int)(hei-150), 400, 100);
		botaoFinalizarVez = new JButton("Novo Jogo");
		botaoFinalizarVez.setBounds((int)(wid/2) - 200, (int)(hei-200), 400, 50);
		botaoFinalizarVez.setEnabled(false);
		botaoFinalizarVez.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			 }
		});
		botaoIniciarVez = new JButton("Iniciar vez");
		botaoIniciarVez.setBounds((int)(wid/2) - 200, (int)(hei-200), 400, 50);
		botaoIniciarVez.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			 }
		});
		tabEsq.setVisible(false);
		tabDir.setVisible(false);
		botaoIniciarVez.setVisible(true);
		getContentPane().add(tabEsq);
		getContentPane().add(tabDir);
		getContentPane().add(numeroDeTirosRestantes);
		getContentPane().add(botaoFinalizarVez);
		getContentPane().add(botaoIniciarVez);
		facade.addObserver(tabEsq);
		facade.addObserver(tabDir);
		setTitle("Batalha Naval");
		repaint();
	}
}
