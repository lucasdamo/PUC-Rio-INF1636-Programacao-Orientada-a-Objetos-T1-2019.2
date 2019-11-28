package gui;

import javax.swing.*;
import armas.*;
import controladores.MainController;

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
import tratadores.ErroAoIdentificarJogador;
public class FRPosicionaArmas extends JFrame implements EscutaCliqueCampoBatalha, Observer, KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6036353713634699387L;
	Facade facade;
	Jogador jogador;
	Arma armaSelecionada = null;
	PNCampoDeBatalha campoDeBatalha;
	double wid, hei;
	double campoX, campoY;
	ArrayList<Arma> loa = new ArrayList<Arma>();
	JButton botaoGuardarTab;
	public FRPosicionaArmas(Facade f, Jogador jog) {
		this.jogador = jog;
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setLayout(null);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		this.facade = f;
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		this.setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		campoX = wid/2 + 10;
		campoY = 20;
		campoDeBatalha = new PNCampoDeBatalha(campoX, campoY, wid/2 - 10, hei - 100);
		campoDeBatalha.setJog(jogador);
		campoDeBatalha.addCliqueListener(this);
		setTitle("Posicionamento de armas para jogador");
		QuadradoArma.setAltura(campoDeBatalha.getAlturaCelula());
		QuadradoArma.setLargura(campoDeBatalha.getLarguraCelula());

		botaoGuardarTab = new JButton("Guardar");
		botaoGuardarTab.setBounds((int)(wid/2 - 50), (int)(hei-150),100, 50);
		botaoGuardarTab.setVisible(true);
		botaoGuardarTab.setEnabled(false);
		botaoGuardarTab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		try {
					f.setTab(geraMatriz(), jogador);
					MainController.getControl().nextEstado();
				} catch (ErroAoIdentificarJogador e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro, jogador invalido", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
            		
				}
            }
		});
		getContentPane().add(botaoGuardarTab);
		
		int largDivArmas = (int)(wid/2 -20);
		int alturaDivArmas = (int)(hei - 20);
		
		int xAux;
		// Destroyers
		xAux = 20;
		int largDes = Destroyer.getLarguraPadrao() + (largDivArmas - (Destroyer.getLarguraPadrao() * 4)) /4 ;
		for(int i=0; i<3; i++) {
			Destroyer des = new Destroyer(xAux,100,-20,-20);
			des.addObserver(this);
			getContentPane().add(des);
			loa.add(des);
			xAux = xAux + largDes;	
		}
		 
		// Submarinos
		xAux = 20;
		int largSub = Submarino.getLarguraPadrao() + (largDivArmas - (Submarino.getLarguraPadrao() * 5)) /5 ;
		for(int i=0; i<4; i++) {
			Submarino sub = new Submarino(xAux,20,-20,-20);
			sub.addObserver(this);
			getContentPane().add(sub);
			loa.add(sub);
			xAux = xAux + largSub;
		}		
		
		// Cruzadores
		xAux = 20;
		int largCru = Cruzador.getLarguraPadrao() + (largDivArmas - (Cruzador.getLarguraPadrao() * 3)) /3 ;
		for(int i=0; i<2; i++) {
			Cruzador cru = new Cruzador(xAux,200,-20,-20);
			cru.addObserver(this);
			getContentPane().add(cru);
			loa.add(cru);
			xAux = xAux + largDes;
		}
		
		
		// Couracados
		xAux = 20;
		Couracado cou = new Couracado(xAux, 300, -20, -20);
		cou.addObserver(this);
		getContentPane().add(cou);
		loa.add(cou);
		
		// Hidroavioes
		xAux = 20;
		int largHid = Hidroaviao.getLarguraPadrao() + (largDivArmas - (Hidroaviao.getLarguraPadrao() * 6)) /6 ;
		for(int i=0; i<5; i++) {
			Hidroaviao hid = new Hidroaviao(xAux,400,-20,-20);
			hid.addObserver(this);
			getContentPane().add(hid);
			loa.add(hid);
			xAux = xAux + largHid;
		}	
		
		// Ordem dos layers a serem adicionados, os primeiros ficam em cima dos seguintes
		getContentPane().add(campoDeBatalha);
		repaint();
	}
	private boolean checaConflitos(QuadradoArma quadrado, Arma arma) {
		ArrayList<Arma> armasAux = (ArrayList<Arma>) loa.clone();
		int relX = quadrado.getRelX();
		int relY = quadrado.getRelY();
		armasAux.remove(arma);
		
		for(Arma a: armasAux) {
			for(QuadradoArma q: a.getListQuadradoArma()) {
				int rx = q.getRelX();
				int ry = q.getRelY();
				if(rx == relX && ry == relY) return false;
				if((rx+1 == relX || rx-1 == relX) && ry == relY) return false;
				if((ry+1 == relY || ry-1 == relY) && rx == relX) return false;
				if((rx-1 == relX || rx+1 == relX) && (ry-1 == relY || ry+1 == relY)) return false;
			}
		}
		return true;
	}
	private int[][] geraMatriz(){
		int matriz[][] = new int[15][15];
		int contaArmasFaltando = 0;
		ArrayList<QuadradoArma> quad = new ArrayList<QuadradoArma>();
		for(Arma a: loa) {
			for(QuadradoArma q: a.getListQuadradoArma()) {
				int relX, relY;
				relX = q.getRelX();
				relY = q.getRelY();
				if((relX < 0 || relX > 14) || (relY < 0 || relY > 14)) {
					if(!(relX < -9 || relY < -9)) {
						q.setCor(Color.red);
					}
					contaArmasFaltando++;
					
				}
				else if(checaConflitos(q, a)) {
					q.setCor(a.getCor());
					matriz[relY][relX] = 1;
				}
				else {
					q.setCor(Color.red);
				}
			}
		}
		if(contaArmasFaltando != 0) {
			matriz[0][0] = -1; // Para marcar que a matriz não está OK
		}
		repaint();
		return matriz;
	}
	public Boolean armasOK() {
		int[][] matriz = geraMatriz();
		for(int i = 0; i<15; i++) {
			for(int j=0; j<15; j++) {
				if(matriz[i][j] < 0) return false;
			}
		}
		System.out.print("Armas OK");
		return true;
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
	public void notify(Observable o) {
		// Arma selecionada
		if(armaSelecionada == null) {
			armaSelecionada = (Arma) o;
			armaSelecionada.setOpaque();
			armaSelecionada.repaint();
		}
	}
	public void keyPressed(KeyEvent arg0) {
		int keyCode;
		keyCode = arg0.getKeyCode();
		//System.out.print(keyCode);
		if(keyCode == 27) {
			/* ESC */
			if(armaSelecionada != null) {
				Boolean habilitarGuardar;
				armaSelecionada.unOpaque();
				habilitarGuardar = armasOK();
				if(habilitarGuardar) {
					botaoGuardarTab.setEnabled(true);
				}
				armaSelecionada = null;
			}
		}
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
	public void mouseClicked(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}
