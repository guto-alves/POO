package aeroporto;

public class Passageiro {
	private String nome;
	private String dtNasc;
	private String rg;
	private int assento;
	private String passaporte;
	private String ticket;

	public Passageiro(String nome) {
		this.setNome(nome);
	}

	public void falar() {
		System.out.println(getNome() + " diz: A gente já chegou?");
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

	public int getAssento() {
		return assento;
	}

	public void setAssento(int assento) {
		this.assento = assento;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

}
