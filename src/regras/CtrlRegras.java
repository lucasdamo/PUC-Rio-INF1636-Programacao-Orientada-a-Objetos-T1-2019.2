package regras;

import java.util.Arrays;

public class CtrlRegras {
	int campoJog1[][] = new int[15][15];
	int campoJog2[][] = new int[15][15];
	int vez;
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
}
