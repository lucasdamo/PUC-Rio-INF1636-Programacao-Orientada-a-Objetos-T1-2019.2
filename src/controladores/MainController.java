package controladores;

import javax.swing.JFrame;

import gui.*;
import regras.*;
import observador.Observer;
import observador.Observable;	

public class MainController implements Observer {
	EstadoJogo estadoAtual;
	Facade f = Facade.getFacade();
	JFrame frameAtual;
	
	public MainController() {
		this.estadoAtual = estadoAtual.Inicio;
		executaEstadoAtual();
	}

	private void nextEstado() {
		int index = estadoAtual.ordinal();
		int nextIndex = index + 1;
		EstadoJogo[] estados = EstadoJogo.values();
		nextIndex %= estados.length;
		estadoAtual = estados[nextIndex];
	}
	
	public void notify(Observable o) {
		nextEstado();
		executaEstadoAtual();
	}
	
	public void executaEstadoAtual() {
		if(frameAtual != null)frameAtual.setVisible(false);
		switch(estadoAtual) {
			case Inicio:
				frameAtual = new FRMenuInicial();
				break;
			case Batalha:
				frameAtual = new FRBatalhaNaval(f);
				break;
		}
		frameAtual.setVisible(true);
	}

}
