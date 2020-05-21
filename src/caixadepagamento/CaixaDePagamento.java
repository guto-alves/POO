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
				"Cartão Debito", "Pago R$ %.2f em cartão de débito",
				"Cartão de Crédito", "Foi utilizado R$ %.2f do limite do cartão de crédito",
				"Cheque", "Pago R$ %.2f em cheque",
				"Depósito", "Pago R$ %.2f em deposito bancário");
	
		System.out.printf(formasPagamentoMap .get(tipo), valor);	
	}

}
