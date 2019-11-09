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
	static MainController mainControl = null;
	public MainController() {
		MainController.setControl(this);
		this.estadoAtual = estadoAtual.Inicio;
		executaEstadoAtual();
	}
	
	private static void setControl(MainController mc) {
		mainControl = mc;
	}
	
	public static MainController getControl() {
		return mainControl;
	}
	
	public void nextEstado() {
		System.out.print("NEXT ESTADO\n");
		int index = estadoAtual.ordinal();
		int nextIndex = index + 1;
		EstadoJogo[] estados = EstadoJogo.values();
		nextIndex %= estados.length;
		estadoAtual = estados[nextIndex];
		executaEstadoAtual();
	}
	
	public void notify(Observable o) {
		nextEstado();
	}
	
	private void executaEstadoAtual() {
		if(frameAtual != null)frameAtual.setVisible(false);
		switch(estadoAtual) {
			case Inicio:
				frameAtual = new FRMenuInicial();
				break;
			case PosicionamentoArmas:
				frameAtual = new FRPosicionamento();
				break;
			case Batalha:
				frameAtual = new FRBatalhaNaval(f);
				break;
		}
		frameAtual.setVisible(true);
	}

}
