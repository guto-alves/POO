package banco;

import java.util.Date;

public class Pagamento extends Transacao{
	private String boleto;
	
	public Pagamento(String boleto, double valor, Date data) {
		super(valor, data);
		this.boleto = boleto;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}
	
}
