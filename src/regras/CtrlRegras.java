package regras;

import java.util.*;
import observador.Observable;
import observador.Observer;

public class CtrlRegras {
	int campoJog1[][] = new int[15][15];
	int campoJog2[][] = new int[15][15];
	Jogador vez;
	Jogador [] jogadores = new Jogador[2];
	String nomeJog1, nomeJog2;
	List<Observer> lob=new ArrayList<Observer>();
	public CtrlRegras() {
		vez = jogadores[0];
		for(int i = 0; i<15; i++) {
			for(int j = 0; j<15; j++) {
				campoJog1[i][j] = 0;
				campoJog2[i][j] = 0;
			}
		}
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
	public void mudaVez() {
		if(vez.id == 1) vez = jogadores[1];
		else vez = jogadores[0];
	}
	public void atira(int i, int j, Jogador jog) {
		//System.out.print(i + " " + j + " " + jog + "\n");
		if(jog.getId() == 1) {
			if(vez.id == 1) {
				campoJog1[i][j] = 1;
				mudaVez();
			}
		}
		else if(jog.getId() ==2) {
			if(vez.id == 2) {
				campoJog2[i][j] = 1;
				mudaVez();
			}
		}
	}
	public void createJogadores(String nome1, String nome2) {
		this.jogadores[0] = new Jogador(nome1, 1);
		this.jogadores[1] = new Jogador(nome2, 2);
	}
	public Jogador getJogador1() {
		return jogadores[0];
	}
	public Jogador getJogador2() {
		return jogadores[1];
	}
}
