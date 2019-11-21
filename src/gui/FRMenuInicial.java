package gui;

import regras.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import controladores.MainController;
import observador.Observable;
import observador.Observer;



public class FRMenuInicial extends JFrame {
	Facade f = Facade.getFacade();
	public FRMenuInicial() {
		JLabel labelNomeJog1 = new JLabel("Nome do jogador 1");
		JLabel labelNomeJog2 = new JLabel("Nome do jogador 2");
		JTextField inputNomeJog1 = new JTextField();
		JTextField inputNomeJog2 = new JTextField();
		JButton botaoInicia = new JButton("Iniciar Jogo");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		setBounds(0,0,450,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		labelNomeJog1.setBounds(5, 5, 200, 30);
		inputNomeJog1.setBounds(210, 5, 200, 30);
		labelNomeJog2.setBounds(5, 60, 200, 30);
		inputNomeJog2.setBounds(210, 60, 200, 30);
		botaoInicia.setBounds(210, 150, 200, 30);
		getContentPane().add(labelNomeJog1);
		getContentPane().add(labelNomeJog2);
		getContentPane().add(inputNomeJog1);
		getContentPane().add(inputNomeJog2);
		getContentPane().add(botaoInicia);
		botaoInicia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(inputNomeJog1.getText().matches("([A-Z]*)([a-z ])*") && inputNomeJog2.getText().matches("([A-Z]*)([a-z ])*")) {
            		if(inputNomeJog1.getText().equals(inputNomeJog2.getText())) {
            			JOptionPane.showMessageDialog(null, "Por favor insira nomes diferentes");
            		}
            		else {
                        MainController.getControl().nextEstado();
            		}
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "Por favor insira nomes sem acentos e caracteres especiais");
            	}
            }
        });
		setTitle("Batalha Naval");
	}
}
