package gui;

import javax.swing.*;
import armas.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import regras.*;
import observador.*;
import observador.Observable;
import observador.Observer;

public class FRPosicionaArmas extends JFrame implements EscutaCliqueCampoBatalha, Observer {
	Facade facade;
	Arma armaSelecionada;
	public FRPosicionaArmas(Facade f, Jogador jog) {
		PNCampoDeBatalha campoDeBatalha;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		this.facade = f;
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		campoDeBatalha = new PNCampoDeBatalha(wid/2 + 10, 20, (wid/3), 2*hei/3);
		campoDeBatalha.setJog(new Jogador("", 1));
		campoDeBatalha.addCliqueListener(this);
		getContentPane().add(campoDeBatalha);
		setTitle("Posicionamento de armas para jogador");
		QuadradoArma.setAltura(campoDeBatalha.getAlturaCelula());
		QuadradoArma.setLargura(campoDeBatalha.getLarguraCelula());
		Submarino sub1 = new Submarino(10, 10);
		sub1.addObserver(this);
		sub1.setBounds(10, 10, 100, 100);
		getContentPane().add(sub1);
	}
	public void recebeClique(int x, int y, Jogador jogador) {
		/* Recebe clique no campo de batalha */
		
	}
	@Override
	public void notify(Observable o) {
		// Arma selecionada
		armaSelecionada = (Arma) o;
		armaSelecionada.setOpaque();
		armaSelecionada.repaint();
	}
}
