package observer;

public class ObserverTest {

	public static void main(String[] args) {
		SuperInteressante interessante = new SuperInteressante();
		Leitor leitor = new Leitor();

		interessante.registrar(leitor);

		interessante.publicarArtigo("Fatec ZL faz parceria com o MIT para desenvolvimento de novas"
				+ " tecnologias");
	}

}
