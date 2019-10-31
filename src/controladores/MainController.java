package controladores;

import gui.FRBatalhaNaval;
import regras.CtrlRegras;

public class MainController {

	public static void main(String[] args) {
		(new FRBatalhaNaval(new CtrlRegras())).setVisible(true);
	}

}
