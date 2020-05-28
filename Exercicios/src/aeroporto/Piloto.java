package aeroporto;

public class Piloto {
	private String nome;
	private String dtNasc;
	private String rg;
	private String licenca;
	private String dtContratacao;

	public Piloto(String nome) {
		setNome(nome);
	}

	public void falar() {
		System.out.println(getNome() + " diz: Estamos entrando numa turbulência!");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(String dtNasc) {
		this.dtNasc = dtNasc;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getLicenca() {
		return licenca;
	}

	public void setLicenca(String licenca) {
		this.licenca = licenca;
	}

	public String getDtContratacao() {
		return dtContratacao;
	}

	public void setDtContratacao(String dtContratacao) {
		this.dtContratacao = dtContratacao;
	}

}
