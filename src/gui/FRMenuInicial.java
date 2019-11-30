package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import IO.EntradaESaida;

import java.io.*;

import controladores.EstadoJogo;
import controladores.MainController;
import regras.Facade;

public class FRMenuInicial extends JFrame {
	public FRMenuInicial() {
		JButton botaoInicia = new JButton("Iniciar Novo Jogo");
		JButton botaoCarregaJogo = new JButton("Carregar Jogo");
		JFileChooser carregaJogo = new JFileChooser("Carregar Jogo");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		setBounds(0,0,450,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		botaoInicia.setBounds(115, 5, 200, 30);
		botaoCarregaJogo.setBounds(115, 50, 200, 30);
		getContentPane().add(botaoInicia);
		getContentPane().add(botaoCarregaJogo);
		getContentPane().add(carregaJogo);
		botaoInicia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Facade.getFacade().reiniciaPartida();
            	MainController.getControl().nextEstado();
            }
        });
		botaoCarregaJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = carregaJogo.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = carregaJogo.getSelectedFile();
					System.out.print(selectedFile.getAbsolutePath());
					EntradaESaida.CarregarJogo(selectedFile);
					MainController.getControl().setEstado(EstadoJogo.Batalha);
					
				}
            }
		});
		setTitle("Batalha Naval");
	}
}
