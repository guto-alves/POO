package bonusfuncionariosi;

import java.util.List;

public class RH {
	private List<Funcionario> funcionarios;

	public double valorBonus(Funcionario funcionario) {
		double bonus;

		if (funcionario.getFaltasNoAno() <= 2) {
			bonus = 0.3;
		} else if (funcionario.getFaltasNoAno() >= 2 && funcionario.getFaltasNoAno() <= 4) {
			bonus = 0.15;
		} else {
			bonus = 0.05;
		}

		return bonus;
	}

	public void mostrarBonusTodosFuncionarios() {
		System.out.printf("%10s %10s%n", "Nome", "bônus");

		for (Funcionario funcionario : funcionarios) {
			double bonus = valorBonus(funcionario);
			System.out.printf("%10s %10.2f%n", funcionario.getNome(), bonus * 100);
		}
	}

	public void adicionarFuncionario(Funcionario funcionario) {
		funcionarios.add(funcionario);
	}

	public void removeFuncionario(Funcionario funcionario) {
		funcionarios.remove(funcionario);
	}

}
