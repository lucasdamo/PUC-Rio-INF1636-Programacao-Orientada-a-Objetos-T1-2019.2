package regras;

import java.util.ArrayList;
import java.util.List;

import observador.Observable;
import observador.Observer;

import tratadores.*;

public class Facade implements Observable {
	CtrlRegras ctrl;
	static Facade f = null;
	List<Observer> lob=new ArrayList<Observer>();
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
	
	public void atira(int x, int y, int jog) {
		ctrl.atira(x, y, jog);
		notifyObservers();
	}
	public void addObserver(Observer o) {
		lob.add(o);
	}
	public void removeObserver(Observer o) {
		lob.remove(o);
	}
	public Object get() {
		return this;
	}
	public int tipo() {
		return 0;
	}
	private void notifyObservers() {
		for(Observer o:lob)
			o.notify(this);
	}
	public void createJogadores(String nomeJog1, String nomeJog2) {
		ctrl.createJogadores(nomeJog1, nomeJog2);
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
	
	
}
