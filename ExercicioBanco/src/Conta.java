import java.util.Date;

public class Conta {
	private double saldo;
	private String numero;
	private String senha;
	private Transacao[] historico;

	public boolean efetuarSaque(double valor) {
		return false;
	}

	public boolean efetuarDeposito(double valor) {
		return false;
	}

	public boolean efetuarPagamento(String boleto, double valor) {
		return false;
	}

	public Transacao[] extrato(Date initialDate, Date finalDate) {
		return null;
	}
}
