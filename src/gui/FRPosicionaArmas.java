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
	ArrayList<Arma> loa = new ArrayList<Arma>();
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

		
		int largDivArmas = (int)(wid/2 -20);
		int alturaDivArmas = (int)(hei - 20);
		
		int xAux;
		// Destroyers
		xAux = 20;
		int largDes = Destroyer.getLarguraPadrao() + (largDivArmas - (Destroyer.getLarguraPadrao() * 4)) /4 ;
		for(int i=0; i<3; i++) {
			Destroyer des = new Destroyer(xAux,100,-1,-1);
			des.addObserver(this);
			getContentPane().add(des);
			loa.add(des);
			xAux = xAux + largDes;
			
		}	
		
		// Submarinos
		xAux = 20;
		int largSub = Submarino.getLarguraPadrao() + (largDivArmas - (Destroyer.getLarguraPadrao() * 5)) /5 ;
		for(int i=0; i<5; i++) {
			Submarino sub = new Submarino(xAux,20,-1,-1);
			sub.addObserver(this);
			getContentPane().add(sub);
			loa.add(sub);
			xAux = xAux + largSub;
			
		}		
		
		// Ordem dos layers a serem adicionados, os primeiros ficam em cima dos seguintes
		getContentPane().add(campoDeBatalha);
	}
	
	private int[][] geraMatriz(){
		int matriz[][] = new int[15][15];
		int contaArmasFaltando = 0;
		int contaQuadrado = 0;
		ArrayList<QuadradoArma> quad = new ArrayList<QuadradoArma>();
		for(Arma a: loa) {
			for(QuadradoArma q: a.getListQuadradoArma()) {
				int relX, relY;
				contaQuadrado++;
				relX = q.getRelX();
				relY = q.getRelY();
				System.out.print(relX + " - " + relY + "\n");
				if(relX >= 0 && relY >= 0) {
					if(relX > 14 || relY > 14) {
						System.out.print("Erro, quadrado fora do campo\n");
						q.setCor(Color.red);
						matriz[0][0] = -1; // Para marcar que houve erro e a matriz não deve ser aceita
					}
					else if(matriz[relY][relX] == 1) {
						System.out.print("Erro sobreposicao de armas\n");
						if(armaSelecionada != null) {
							int mudou = 0;
							for(QuadradoArma qq: armaSelecionada.getListQuadradoArma()) {
								if(qq.getRelX() == relX && qq.getRelY() == relY) {
									qq.setCor(Color.red);
									mudou = 1;
								}
							}
							if(mudou == 0) {
								q.setCor(Color.red);
							}
						}
						matriz[relY][relX] = -1;
					}
					else if(matriz[relY][relX] == 0) {
						matriz[relY][relX] = 1;
						q.setCor(a.getCor());
					}
				}
				else if (relX == -2 || relY == -2) {
					contaArmasFaltando++;
					matriz[0][0] = -1; // Para marcar que houve erro e a matriz não deve ser aceita
				}	
			}
		}
		if(contaArmasFaltando != 0) {
			System.out.print("Nem todas as armas foram posicionadas!\n");
		}
		System.out.print("Quadrado contados " + contaQuadrado + "\n");
		repaint();
		return matriz;
	}
	
	public void recebeClique(int x, int y, Jogador jogador) {
		/* Recebe clique no campo de batalha */
		if(armaSelecionada != null) {
			armaSelecionada.move((int)(campoDeBatalha.getAbsX(x, y) + campoX), (int)(campoDeBatalha.getAbsY(x, y) + campoY), x, y);
		}
		else {
			JOptionPane.showMessageDialog(null, "Nenhuma arma foi selecionada!", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
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
		//System.out.print(keyCode);
		if(keyCode == 27) {
			/* ESC */
			if(armaSelecionada != null) {
				armaSelecionada.unOpaque();
				geraMatriz();
				armaSelecionada = null;
			}
		}
		if(keyCode == 82) {
			/* R */
			System.out.print("KEYCODE 82 RECEBIDO\n");
			if(armaSelecionada != null) {
				armaSelecionada.changeRotatation();
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
