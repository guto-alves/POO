package observer;

public class Leitor implements Observer {

	@Override
	public void update(Revista revista) {
		System.out.println("Lendo o artigo " + revista.artigo());
	}

}
