package aluno;

import java.util.Date;

import javax.swing.JOptionPane;

public class Aluno {
	private long id;
	private String nome;
	private String ra;
	private Date nascimento;

	public Aluno(long id, String nome, String ra, Date nascimento) {
		this.id = id;
		this.nome = nome;
		this.ra = ra;
		this.nascimento = nascimento;
	}

	public void exibir() {
		JOptionPane.showMessageDialog(null, 
				String.format("\tAluno%n%s: %d%n%s: %s%n%s: %s%n%s: %s%n",
						"Id", id,
						"Nome", nome,
						"RA", ra,
						"Data de Nascimento", GestaoAlunos.FORMATO_DATA.format(nascimento)));
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

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

}
