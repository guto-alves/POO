package animalbcefx;

import java.time.LocalDate;

public class Animal {
	private long id;
	private String nome;
	private LocalDate nascimento;
	private float peso;

	public Animal() {
	}

	public Animal(long id, String nome, LocalDate nascimento, float peso) {
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.peso = peso;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

}
