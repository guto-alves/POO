package aeroporto;

import java.util.List;
import java.util.ArrayList;

public class Aeroporto {

	public static void main(String[] args) {
		Aeronave aeronave = new Aeronave();

		int qtdTripulacao = 9;

		Piloto piloto = new Piloto("Piloto João");

		aeronave.setPiloto(piloto);
		aeronave.setPassageiros(getTripulacao(qtdTripulacao));

		aeronave.decolar();
		aeronave.getPassageiros().get(getPassageiroRandom(qtdTripulacao)).falar();
		piloto.falar();
		aeronave.getPassageiros().get(getPassageiroRandom(qtdTripulacao)).falar();
	}

	public static List<Passageiro> getTripulacao(int qtd) {
		List<Passageiro> tripulacao = new ArrayList<Passageiro>();

		for (int i = 0; i < qtd; i++) {
			tripulacao.add(new Passageiro("Passageiro " + (i + 1)));
		}

		return tripulacao;
	}

	public static int getPassageiroRandom(int qtdTripulacao) {
		return (int) (Math.random() * qtdTripulacao);
	}

}
