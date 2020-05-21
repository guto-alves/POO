package bonusfuncionariosii;

import java.util.ArrayList;
import java.util.List;

public class RH {
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public double valorBonus(Funcionario funcionario) {
		double bonus;

		if (funcionario.getFaltasNoAno() <= 2) {
			bonus = 0.3;
		} else if (funcionario.getFaltasNoAno() >= 2 && funcionario.getFaltasNoAno() <= 4) {
			bonus = 0.15;
		} else {
			bonus = 0.05;
		}

		if (funcionario.getUltimaNotaAvaliacao() > 7) {
			bonus += 0.3;
		} else if (funcionario.getUltimaNotaAvaliacao() >= 5 && funcionario.getUltimaNotaAvaliacao() <= 7) {
			bonus += 0.15;
		} else {
			bonus += 0.05;
		}

		return bonus;
	}

	public void mostrarBonusTodosFuncionarios() {
		System.out.printf("%n\tBÔNUS DOS FUNCIONÁRIOS%n%11s %15s%n", "Nome", "bônus");

		for (Funcionario funcionario : funcionarios) {
			double bonus = valorBonus(funcionario);
			System.out.printf("%10s %13.0f%%%n", funcionario.getNome(), bonus * 100);
		}
	}

	public void adicionarFuncionario(Funcionario funcionario) {
		funcionarios.add(funcionario);
	}

	public void removeFuncionario(Funcionario funcionario) {
		funcionarios.remove(funcionario);
	}

}
