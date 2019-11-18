package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import regras.*;
import observador.*;

public class FRPosicionaArmas extends JFrame implements EscutaCliqueCampoBatalha {
	Facade facade;
	public FRPosicionaArmas(Facade f, Jogador jog) {
		PNCampoDeBatalha campoDeBatalha;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		this.facade = f;
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		campoDeBatalha = new PNCampoDeBatalha(wid/2 + 10, 20, (wid/3), 2*hei/3, 1);
		campoDeBatalha.addCliqueListener(this);
		getContentPane().add(campoDeBatalha);
		setTitle("Batalha Naval");
	}
	public void recebeClique(int x, int y, int jogador) {
		/* Recebe clique no campo de batalha */
		
	}
}
