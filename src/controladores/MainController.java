package controladores;

import javax.swing.JFrame;
import tratadores.ErroAoIdentificarJogador;

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
		if(frameAtual != null)frameAtual.dispose();
		switch(estadoAtual) {
			case Inicio:
				frameAtual = new FRMenuInicial();
				break;
			case PosicionamentoArmasJog1:
				frameAtual = new FRPosicionaArmas(f, f.getJogador1());
				break;
			case PosicionamentoArmasJog2:
				frameAtual = new FRPosicionaArmas(f, f.getJogador2());
				break;
			case Batalha:
				// --- Inicio stub para teste ---
//				int[][] tab1 = new int[15][15];
//				int[][] tab2 = new int[15][15];
//				tab1[0][0] = 1;
//				tab1[0][1] = 1;
//				f.createJogadores("A", "B");
//				try {
//					f.setTab(tab1, f.getJogador1());
//					f.setTab(tab2, f.getJogador2());
//				} catch (ErroAoIdentificarJogador e) {
//					e.printStackTrace();
//				}
				// --- fim ---
				frameAtual = new FRBatalhaNaval(f);
				break;
		}
		frameAtual.setVisible(true);
	}

}
