package regras;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import observador.Observable;
import observador.Observer;

import tratadores.*;

public class Facade implements Observable, Serializable {
	private static final long serialVersionUID = -5439405546289188343L;
	CtrlRegras ctrl;
	static Facade f = null;
	public Facade() {
		ctrl = new CtrlRegras();
	}
	
	public static Facade getFacade() {
		if(f == null) {
			f = new Facade();
		}
		return f;
	}
	
	public int[][] getTab1(){
		return ctrl.getTab1();
	}
	
	public int[][] getTab2(){
		return ctrl.getTab2();
	}
	
	public void atira(int x, int y, Jogador jog) {
		ctrl.atira(x, y, jog);
	}
	public void addObserver(Observer o) {
		ctrl.addObserver(o);
	}
	public void removeObserver(Observer o) {
		ctrl.removeObserver(o);
	}
	public Object get() {
		return this;
	}
	public int tipo() {
		return 0;
	}
	public void createJogadores(String nomeJog1, String nomeJog2) {
		ctrl.createJogadores(nomeJog1, nomeJog2);
	}
	public Jogador checaGanhador() {
		return ctrl.checaGanhador();
	}
	public int[][] getTabJogador(Jogador jog) throws ErroAoIdentificarJogador {
		if(jog.getId() == 1) {
			return ctrl.getTab1();
		}
		else if (jog.getId() == 2) {
			return ctrl.getTab2();
		}
		else {
			throw new ErroAoIdentificarJogador("Id do jogador <" + jog.getNome() + "> não é 1 nem 2");
		}
	}
	public Jogador getJogador1() {
		return ctrl.getJogador1();
	}
	public Jogador getJogador2() {
		return ctrl.getJogador2();
	}
	public Jogador getJogadorDaVez() {
		return ctrl.getJogadorDaVez();
	}
	public int getNumeroTirosRestantes() {
		return ctrl.getNumeroTiros();
	}
	public void setTab(int matriz[][], Jogador jog) throws ErroAoIdentificarJogador {
		if(jog == null) throw new ErroAoIdentificarJogador("Jogador é null");

		if(jog.id == 1) {
			ctrl.setTab1(matriz);
		}
		else if(jog.id == 2) {
			ctrl.setTab2(matriz);
		}
		else throw new ErroAoIdentificarJogador("\"Id do jogador <\" + jog.getNome() + \"> não é 1 nem 2");
	}
	public void mudaVez() {
		ctrl.mudaVez();
	}
	public void setCtrlRegras(CtrlRegras r) {
		this.ctrl = r;
	}
	public CtrlRegras getCtrlRegras() {
		return this.ctrl;
	}
	
}
