package banco;

import java.util.Date;

public class Conta {
	private double saldo;
	private String numero;
	private String senha;
	private Transacao[] historico;

	public Conta(String numero, String senha, double saldo) {
		this.numero = numero;
		this.senha = senha;
		this.saldo = saldo;
	}

	public boolean efetuarSaque(double valor) {
		if (valor <= saldo) {
			saldo -= valor;
			return true;
		}

		return false;
	}

	public boolean efetuarDeposito(double valor) {
		if (valor > 0) {
			saldo += valor;
			return true;
		}

		return false;
	}

	public boolean efetuarPagamento(String boleto, double valor) {
		if ((valor > 0) && (!(boleto == null))) {
			saldo -= valor;
			return true;
		}

		return false;
	}

	public Transacao[] extrato(Date inicio, Date fim) {
		return historico;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getNumero() {
		return numero;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}
}
