package caixadepagamento;

import java.util.Map;
import java.util.Scanner;

public class CaixaDePagamento {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Valor para pagamento: ");
		double valor = input.nextDouble();

		System.out.print("Tipo de pagamento: ");
		String tipo = input.next();
		input.close();
		
		Map<String, String> formasPagamentoMap = Map.of(
				"Dinheiro", "Pago R$ %.2f em dinheiro",
				"Cart�o Debito", "Pago R$ %.2f em cart�o de d�bito",
				"Cart�o de Cr�dito", "Foi utilizado R$ %.2f do limite do cart�o de cr�dito",
				"Cheque", "Pago R$ %.2f em cheque",
				"Dep�sito", "Pago R$ %.2f em deposito banc�rio");
	
		System.out.printf(formasPagamentoMap .get(tipo), valor);	
	}

}
