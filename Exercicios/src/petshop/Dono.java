package petshop;
import java.util.List;

public class Dono {
	private String nome;
	private String cpf;
	private String endereco;
	private String cep;
	private String email;
	private List<Integer> telefones;

	public Dono(String nome, String cpf, String endereco, String cep, String email, List<Integer> telefones) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cep = cep;
		this.email = email;
		this.telefones = telefones;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Integer> telefones) {
		this.telefones = telefones;
	}

}
