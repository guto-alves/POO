package nobreza;

public class Principe extends Nobre implements Cavaleiro {

	@Override
	public void governar() {
		System.out.println("Príncipe governando");
	}

	@Override
	public void duelar() {
		System.out.println("Príncipe duelando");
	}
	
}
