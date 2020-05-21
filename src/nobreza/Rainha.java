package nobreza;

public class Rainha extends Nobre implements Diplomata {

	@Override
	public void governar() {
		System.out.println("Rainha governando");
	}

	@Override
	public void fazerDiplomacia() {
		System.out.print("Rainha fazendo diplomacia");
	}
	
}
