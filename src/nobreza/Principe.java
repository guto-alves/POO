package nobreza;

public class Principe extends Nobre implements Cavaleiro {

	@Override
	public void governar() {
		System.out.println("Pr�ncipe governando");
	}

	@Override
	public void duelar() {
		System.out.println("Pr�ncipe duelando");
	}
	
}
