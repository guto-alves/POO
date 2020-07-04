package iterator;

public class PlateiaIterator implements Iterator {
	private String[][] plateia;

	private int totalCadeiras;
	
	private int fileiraAtual;
	private int cadeiraAtual;
	
	private int totalPessoas;
	private int countPessoas;
	
	public PlateiaIterator(String[][] plateia, int totalPessoas) {
		this.plateia = plateia;
		totalCadeiras = plateia[0].length;
		this.totalPessoas = totalPessoas;
	}

	@Override
	public boolean hasNext() {
		return countPessoas < totalPessoas;
	}

	@Override
	public String next() {
		if (hasNext()) {
			countPessoas++;
			
			String nome = plateia[fileiraAtual][cadeiraAtual];
			
			cadeiraAtual++;
			
			if (cadeiraAtual >= totalCadeiras) {
				cadeiraAtual = 0;
				fileiraAtual++;
			} 
				
			return nome;
		}
		
		return null;
	}

}
