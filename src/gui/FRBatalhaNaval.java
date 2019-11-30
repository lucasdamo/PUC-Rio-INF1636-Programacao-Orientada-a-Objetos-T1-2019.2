package gui;

import regras.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import IO.EntradaESaida;
import controladores.EstadoJogo;
import controladores.MainController;
import observador.*;

public class FRBatalhaNaval extends JFrame implements EscutaCliqueCampoBatalha {
	Facade facade;
	JLabel labelJogadorDaVez;
	JLabel numeroDeTirosRestantes;
	JButton botaoFinalizarVez;
	JButton botaoIniciarVez;
	JButton botaoNovoJogo;
	JButton botaoSalvarJogo;
	PNCampoDeBatalha tabEsq;
	PNCampoDeBatalha tabDir;
	JFileChooser salvarJogo;
	public FRBatalhaNaval(Facade f) {
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
		System.out.print("Bounds = " + (int)(wid/2) + "," + (int)(hei-30) + ", 200, 50\n");
		labelJogadorDaVez.setBounds((int)(wid/2) - 200, (int)(hei-150), 400, 100);
		numeroDeTirosRestantes.setBounds((int)(wid/2) - 200, (int)(hei-200), 400, 100);
		numeroDeTirosRestantes.setVisible(false);
		JFileChooser salvarJogo = new JFileChooser("Salvar Jogo");
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
		botaoSalvarJogo = new JButton("Salvar jogo");
		botaoSalvarJogo.setBounds(50, (int)(hei-150), 150, 50);
		botaoSalvarJogo.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int returnValue = salvarJogo.showOpenDialog(null);
					if(returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = salvarJogo.getSelectedFile();
						System.out.print(selectedFile.getAbsolutePath());
						EntradaESaida.SalvarJogo(selectedFile.getAbsolutePath());				
					}
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
		getContentPane().add(botaoSalvarJogo);
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
		Jogador ganhador = Facade.getFacade().checaGanhador();
		if(facade.getNumeroTirosRestantes() < 1) botaoFinalizarVez.setEnabled(true);
		changeLabelNumeroDeTiros();
		if(ganhador != null) {
			repaint();
			terminaPartida();
		}

	}
	public void terminaPartida() {
		botaoFinalizarVez.setVisible(false);
		botaoIniciarVez.setVisible(false);
		labelJogadorDaVez.setVisible(false);
		numeroDeTirosRestantes.setVisible(false);
		botaoNovoJogo = new JButton("Novo Jogo");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		botaoNovoJogo.setBounds((int)(wid/2) - 200, (int)(hei-200), 400, 50);
		botaoNovoJogo.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 MainController.getControl().setEstado(EstadoJogo.Inicio);
			 }
		});
		getContentPane().add(botaoNovoJogo);
		JOptionPane.showMessageDialog(null, "Fim de jogo, jogador " + facade.checaGanhador().getNome() + " ganhou !!", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		
		
	}
}
