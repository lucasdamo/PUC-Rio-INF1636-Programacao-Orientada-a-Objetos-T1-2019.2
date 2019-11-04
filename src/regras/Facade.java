package regras;

public class Facade {
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
}
