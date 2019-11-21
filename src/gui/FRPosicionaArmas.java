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

public class FRPosicionaArmas extends JFrame implements EscutaCliqueCampoBatalha, Observer, KeyListener {
	Facade facade;
	Arma armaSelecionada = null;
	PNCampoDeBatalha campoDeBatalha;
	double wid, hei;
	double campoX, campoY;
	public FRPosicionaArmas(Facade f, Jogador jog) {
		this.addKeyListener(this);
		
		this.setLayout(null);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		this.facade = f;
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		this.setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		campoX = wid/2 + 10;
		campoY = 20;
		campoDeBatalha = new PNCampoDeBatalha(campoX, campoY, (wid/3), 2*hei/3);
		campoDeBatalha.setJog(new Jogador("", 1));
		campoDeBatalha.addCliqueListener(this);
		setTitle("Posicionamento de armas para jogador");
		QuadradoArma.setAltura(campoDeBatalha.getAlturaCelula());
		QuadradoArma.setLargura(campoDeBatalha.getLarguraCelula());

		Submarino sub1 = new Submarino(10, 10, 0, 0);
		sub1.addObserver(this);
		sub1.setBounds(10, 10, 100, 100);
		
		Destroyer dest1 = new Destroyer(10, 100, 0, 0);
		dest1.addObserver(this);
		
		// Ordem dos layers a serem adicionados, os primeiros ficam em cima dos seguintes
		getContentPane().add(sub1);
		getContentPane().add(dest1);
		getContentPane().add(campoDeBatalha);
	}
	public void recebeClique(int x, int y, Jogador jogador) {
		/* Recebe clique no campo de batalha */
		armaSelecionada.move((int)(campoDeBatalha.getAbsX(x, y) + campoX), (int)(campoDeBatalha.getAbsY(x, y) + campoY), x, y);
		
	}
	@Override
	public void notify(Observable o) {
		// Arma selecionada
		if(armaSelecionada == null) {
			armaSelecionada = (Arma) o;
			armaSelecionada.setOpaque();
			armaSelecionada.repaint();
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyCode;
		keyCode = arg0.getKeyCode();
		if(keyCode == 27) {
			/* ESC */
			if(armaSelecionada != null) {
				armaSelecionada.unOpaque();
				armaSelecionada = null;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
