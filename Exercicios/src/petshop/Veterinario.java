package petshop;
import java.util.List;

public class Veterinario {
	private String nome;
	private int idade;
	private String crm;
	private String email;
	private List<String> especialidade;

	public Veterinario(String nome, int idade, String crm, String email, List<String> especialidade) {
		this.nome = nome;
		this.idade = idade;
		this.crm = crm;
		this.email = email;
		this.especialidade = especialidade;
	}

	public void atender(Animal animal) {
		System.out.println(nome + " atendendo o animal " + animal.getNome());
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(List<String> especialidade) {
		this.especialidade = especialidade;
	}

}
