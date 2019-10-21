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
		return campoJog1;
	}
	public int getVez() {
		return vez;
	}
	public void mudaVez() {
		if(vez==0) vez = 1;
		else vez = 0;
	}
	
}
