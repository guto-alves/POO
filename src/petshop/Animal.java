package petshop;
import java.util.Set;

public class Animal {
	private String nome;
	private double peso;
	private String sexo;
	private String raca;
	private String observacoes;
	private Set<Doenca> doencas;
	private Dono dono;

	public Animal(String nome, double peso, String sexo, String raca, String observacoes, Set<Doenca> doencas,
			Dono dono) {
		this.nome = nome;
		this.peso = peso;
		this.sexo = sexo;
		this.raca = raca;
		this.observacoes = observacoes;
		this.doencas = doencas;
		this.dono = dono;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Set<Doenca> getDoencas() {
		return doencas;
	}

	public void setDoencas(Set<Doenca> doencas) {
		this.doencas = doencas;
	}

	public Dono getDono() {
		return dono;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}

}
