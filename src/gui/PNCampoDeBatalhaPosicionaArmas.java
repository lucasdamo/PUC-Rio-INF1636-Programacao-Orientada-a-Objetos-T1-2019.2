package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import armas.TipoArma;

public class PNCampoDeBatalhaPosicionaArmas extends PNCampoDeBatalha {
	
	public PNCampoDeBatalhaPosicionaArmas(double xInicial, double yInicial, double largura, double altura) {
		super(xInicial, yInicial, largura, altura);
	}
	@Override
	public void desenhaCampo(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		int campoJog[][];
		if(jog.getId() == 1) {
			campoJog = f.getTab1();
		}
		else {
			campoJog = f.getTab2();
		}
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				if(campoJog[j][i] == 1) g2d.setPaint(Color.red);
				else g2d.setPaint(Color.cyan);
				rt=new Rectangle2D.Double(tab[i][j].x, tab[i][j].y, tab[i][j].larg, tab[i][j].alt);
				//System.out.print("Rectangle2D.Double("+tab1[i][j].x+", "+tab1[i][j].y + ", "+tab1[i][j].larg + ", " + tab1[i][j].alt +");\n");
				g2d.fill(rt);
			}
		}
		g2d.setPaint(Color.black);
		// Desenha todas as linhas 
		for(int i =0; i<16; i++) {
			g2d.draw(lnX[i]);
			g2d.draw(lnY[i]);
		}
		// Desenha os indices
		for(int i =0; i<15; i++) {
			String write = Integer.toString(i+1);
			g2d.setPaint(Color.black);
			g2d.drawString(write, (int) (tab[0][i].x + larg/3), (int) yIni/2); // (yIni / 2) porque 0 não exibe a string
			g2d.drawString(write, 0, (int) (tab[i][0].y + alt/2));
		}
	}
}
