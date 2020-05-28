package funcionario;

import java.util.Date;

import javax.swing.JOptionPane;

public class Funcionario {
	private long id;
	private String nome;
	private String matricula;
	private Date admissao;
	private Date demissao;
	private float salario;
	private String horario;

	public Funcionario(long id, String nome, String matricula, Date admissao, Date demissao, float salario,
			String horario) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.admissao = admissao;
		this.demissao = demissao;
		this.salario = salario;
		this.horario = horario;
	}

	public void exibir() {
		JOptionPane.showMessageDialog(null, 
				String.format("\tAluno%n%s: %d%n%s: %s%n%s: %s%n%s: %s%n",
						"Id", id,
						"Nome", nome,
						"Matricula", matricula,
						"Data de Admissão", GestaoFuncionario.FORMATO_DATA.format(admissao),
						"Data de Demissão", GestaoFuncionario.FORMATO_DATA.format(demissao),
						"Salário", salario,
						"Horário", horario));
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public Date getDemissao() {
		return demissao;
	}

	public void setDemissao(Date demissao) {
		this.demissao = demissao;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

}
