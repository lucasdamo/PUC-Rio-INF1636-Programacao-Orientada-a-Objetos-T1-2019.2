package regras;

import java.util.*;
import observador.Observable;
import observador.Observer;

public class CtrlRegras {
	int campoJog1[][] = new int[15][15];
	int campoJog2[][] = new int[15][15];
	int vez;
	String nomeJog1, nomeJog2;
	List<Observer> lob=new ArrayList<Observer>();
	public CtrlRegras() {
		vez = 1;
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
	public int[][] getTab2(){
		return campoJog2;
	}
	public int getVez() {
		return vez;
	}
	public void mudaVez() {
		if(vez == 1) vez = 2;
		else vez = 1;
	}
	public void atira(int i, int j, int jog) {
		//System.out.print(i + " " + j + " " + jog + "\n");
		if(jog == 1) {
			if(vez == 1) {
				campoJog1[i][j] = 1;
				mudaVez();
			}
		}
		else if(jog==2) {
			if(vez == 2) {
				campoJog2[i][j] = 1;
				mudaVez();
			}
		}
	}
	public void setNomeJog1(String inputNome) {
		this.nomeJog1 = inputNome;
	}
	public void setNomeJog2(String inputNome) {
		this.nomeJog2 = inputNome;
	}
	public String getNomeJog1(String inputNome) {
		return this.nomeJog1;
	}
	public String getNomeJog2(String inputNome) {
		return this.nomeJog2;
	}
}
