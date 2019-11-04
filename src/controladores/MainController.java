package controladores;

import gui.FRBatalhaNaval;
import regras.*;


public class MainController {

	public static void main(String[] args) {
		(new FRBatalhaNaval(Facade.getFacade())).setVisible(true);
	}

}
