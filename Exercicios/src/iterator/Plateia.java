package iterator;

public class Plateia implements Agregavel {
	private String[][] plateia = new String[16][16];

	private int totalPessoas;
	
	public void adicionar(String nome, int fileira, int cadeira) {
		if (fileira >= 16 || cadeira >= 16) {
			return;
		} 
		
		plateia[fileira][cadeira] = nome;
		
		totalPessoas++;
	}

	@Override
	public Iterator criarIterator() {
		return new PlateiaIterator(plateia, totalPessoas);
	}
}
