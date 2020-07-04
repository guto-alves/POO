package jdbcalunos;

public class Aluno {
	private String ra;
	private String nome;
	private int idade;
	private String nascimento;

	public Aluno() {
	}

	public Aluno(String ra, String nome, int idade, String nascimento) {
		this.ra = ra;
		this.nome = nome;
		this.idade = idade;
		this.nascimento = nascimento;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
}
