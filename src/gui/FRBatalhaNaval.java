package gui;

import regras.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import observador.*;

public class FRBatalhaNaval extends JFrame implements EscutaCliqueCampoBatalha, Observer {
	Facade facade;
	JLabel labelJogadorDaVez;
	JLabel numeroDeTirosRestantes;
	JButton botaoFinalizarVez;
	JButton botaoIniciarVez;
	PNCampoDeBatalha tabEsq;
	PNCampoDeBatalha tabDir;
	public FRBatalhaNaval(Facade f) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		this.facade = f;
		facade.addObserver(this);
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
		System.out.print("Bounds = " + (int)(wid/2) + "," + (int)(hei-30) + ", 200, 50\n");
		labelJogadorDaVez.setBounds((int)(wid/2) - 200, (int)(hei-150), 400, 100);
		numeroDeTirosRestantes.setBounds((int)(wid/2) - 200, (int)(hei-200), 400, 100);
		numeroDeTirosRestantes.setVisible(false);
		botaoFinalizarVez = new JButton("Finalizar vez");
		botaoFinalizarVez.setBounds((int) wid - 200, (int)(hei-150), 150, 50);
		botaoFinalizarVez.setEnabled(false);
		botaoFinalizarVez.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 f.mudaVez();
				 changeLabelJogadorDaVez();
				 changeLabelNumeroDeTiros();
				 finalizarVez();
			 }
		});
		botaoIniciarVez = new JButton("Iniciar vez");
		botaoIniciarVez.setBounds((int)(wid/2) - 200, (int)(hei-200), 400, 50);
		botaoIniciarVez.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 iniciarVez();
			 }
		});
		tabEsq.setVisible(false);
		tabDir.setVisible(false);
		botaoIniciarVez.setVisible(true);
		getContentPane().add(tabEsq);
		getContentPane().add(tabDir);
		getContentPane().add(labelJogadorDaVez);
		getContentPane().add(numeroDeTirosRestantes);
		getContentPane().add(botaoFinalizarVez);
		getContentPane().add(botaoIniciarVez);
		facade.addObserver(tabEsq);
		facade.addObserver(tabDir);
		setTitle("Batalha Naval");
		repaint();
	}
	private void changeLabelJogadorDaVez() {
		labelJogadorDaVez.setText("Vez do jogador: " + facade.getJogadorDaVez().getNome());
	}
	private void changeLabelNumeroDeTiros() {
		numeroDeTirosRestantes.setText("Numero de tiros restantes: " + facade.getNumeroTirosRestantes());
	}
	private void iniciarVez() {
		tabEsq.setVisible(true);
		tabDir.setVisible(true);
		botaoIniciarVez.setVisible(false);
		numeroDeTirosRestantes.setVisible(true);
	}
	private void finalizarVez() {
		tabEsq.setVisible(false);
		tabDir.setVisible(false);
		botaoIniciarVez.setVisible(true);
		numeroDeTirosRestantes.setVisible(false);
		botaoFinalizarVez.setEnabled(false);
	}	
	public void recebeClique(int x, int y, Jogador jogador) {
		facade.atira(x, y, jogador);
		if(facade.getNumeroTirosRestantes() < 1) botaoFinalizarVez.setEnabled(true);
		changeLabelNumeroDeTiros();
	}

	public void notify(Observable o) {
		Jogador ganhador = null;
		ganhador = Facade.getFacade().checaGanhador();
		if(ganhador != null) {
			JOptionPane.showMessageDialog(null, "Jogador " + ganhador.getNome() + " ganhou!", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
