package dinossauro;

public class Dinossauro {
	private int energia;
	private int velocidade;
	private int temperatura;
	private int humor;

	public void pular() {
		energia--;
		humor++;
		System.out.printf("Pulando - energia: %d / velocidade: %d / temperatura: %d / humor: %d\n", energia, velocidade,
				temperatura, humor);
	}

	public void correr() {
		energia--;
		humor++;
		System.out.printf("Correndo - energia: %d / velocidade: %d / temperatura: %d / humor: %d\n", energia,
				velocidade, temperatura, humor);
	}

	public void comer() {
		energia++;
		humor++;
		System.out.printf("Comendo - energia: %d / velocidade: %d / temperatura: %d / humor: %d\n", energia, velocidade,
				temperatura, humor);
	}

	public void atirar() {
		energia--;
		humor++;
		System.out.printf("Atirando - energia: %d / velocidade: %d / temperatura: %d / humor: %d\n", energia,
				velocidade, temperatura, humor);
	}

	public void tomarSol() {
		velocidade++;
		energia--;
		humor++;
		System.out.printf("No Sol - energia: %d / velocidade: %d / temperatura: %d / humor: %d\n", energia, velocidade,
				temperatura, humor);
	}

	public void ficarNaSombra() {
		energia++;
		humor--;
		System.out.printf("Na Sombra - energia: %d / velocidade: %d / temperatura: %d / humor: %d\n", energia,
				velocidade, temperatura, humor);
	}

}
