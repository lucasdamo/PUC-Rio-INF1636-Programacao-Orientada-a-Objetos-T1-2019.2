package regras;

import java.util.*;
import observador.Observable;
import observador.Observer;

public class CtrlRegras {
	int campoJog1[][] = new int[15][15]; // 0 = agua  1 = navio  2 = tiro perdido  3 = tiro em navio
	int campoJog2[][] = new int[15][15];
	Jogador vez;
	Jogador [] jogadores = new Jogador[2];
	String nomeJog1, nomeJog2;
	List<Observer> lob=new ArrayList<Observer>();
	int tiros;
	public CtrlRegras() {
		vez = jogadores[0];
		for(int i = 0; i<15; i++) {
			for(int j = 0; j<15; j++) {
				campoJog1[i][j] = 0;
				campoJog2[i][j] = 0;
			}
		}
		tiros = 3;
		System.out.print("Array inicializado");
	}
	public int[][] getTab1(){
		return campoJog1;
	}
	public void setTab1(int matriz[][]) {
		campoJog1 = matriz;
	}
	public void setTab2(int matriz[][]) {
		campoJog2 = matriz;
	}
	public int[][] getTab2(){
		return campoJog2;
	}
	public Jogador getVez() {
		return vez;
	}
	private void novaVez() {
		tiros = 3;
	}
	public void mudaVez() {
		if(vez.id == 1) vez = jogadores[1];
		else vez = jogadores[0];
		novaVez();
	}
	public void atira(int i, int j, Jogador jog) {
		//System.out.print(i + " " + j + " " + jog + "\n");
		if(tiros>0) {
			if(jog.getId() == 1) {
				if(vez.id == 2) {
					if(campoJog1[i][j] == 1) {
						campoJog1[i][j] = 3;
						tiros--;
					}
					else if(campoJog1[i][j] == 0) {
						campoJog1[i][j] = 2;
						tiros--;
					}
				}
			}
			else if(jog.getId() ==2) {
				if(vez.id == 1) {
					if(campoJog2[i][j] == 1) {
						campoJog2[i][j] = 3;
						tiros--;
					}
					else if(campoJog2[i][j] == 0) {
						campoJog2[i][j] = 2;
						tiros--;
					}
				}
			}
		}
	}
	public void createJogadores(String nome1, String nome2) {
		this.jogadores[0] = new Jogador(nome1, 1);
		this.jogadores[1] = new Jogador(nome2, 2);
		
		vez = jogadores[0];
	}
	public Jogador getJogador1() {
		return jogadores[0];
	}
	public Jogador getJogador2() {
		return jogadores[1];
	}
	public Jogador getJogadorDaVez() {
		return this.vez;
	}
	public int getNumeroTiros() {
		return this.tiros;
	}
}
