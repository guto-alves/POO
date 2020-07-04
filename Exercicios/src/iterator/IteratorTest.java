package iterator;

public class IteratorTest {

	public static void main(String[] args) {
		Plateia plateia = new Plateia();
		plateia.adicionar("Gustavo", 0, 0);
		plateia.adicionar("Maria", 0, 1);
		plateia.adicionar("Michelle", 0, 2);
		plateia.adicionar("Larissa", 0, 3);
		
		Iterator iterator = plateia.criarIterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
