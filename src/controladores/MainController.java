package controladores;

import gui.*;
import regras.*;


public class MainController {

	public static void main(String[] args) {
		Facade f = Facade.getFacade();
		(new FRMenuInicial()).setVisible(true);
		//(new FRBatalhaNaval(f)).setVisible(true);
	}

}
