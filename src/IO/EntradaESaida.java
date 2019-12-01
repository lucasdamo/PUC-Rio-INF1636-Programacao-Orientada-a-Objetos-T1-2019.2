package IO;
import regras.*;

import java.io.*;

public final class EntradaESaida {
	
	public static void SalvarJogo(String path) {
		Facade fachada = Facade.getFacade();
		CtrlRegras ctrl = fachada.getCtrlRegras();
		try {
			FileOutputStream f = new FileOutputStream(new File(path));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(ctrl);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void CarregarJogo(File inputFile) {
		FileInputStream f;
		try {
			f = new FileInputStream(inputFile);
			ObjectInputStream o = new ObjectInputStream(f);
			CtrlRegras ctrl = (CtrlRegras) o.readObject();
			Facade.getFacade().setCtrlRegras(ctrl);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
