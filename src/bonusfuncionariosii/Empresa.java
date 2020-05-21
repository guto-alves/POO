package bonusfuncionariosii;

import java.util.Scanner;

public class Empresa {
	private static int totalFuncionarios = 0;
	private RH rh;

	public Empresa(RH rh) {
		this.rh = rh;
	}

	public Funcionario contratarFuncionario() {
		System.out.println("\tCADASTRO DE FUNCIONÁRIO");

		Funcionario funcionario = new Funcionario(++totalFuncionarios);

		Scanner input = new Scanner(System.in);

		System.out.print("Nome: ");
		funcionario.setNome(input.nextLine());

		System.out.print("Matricula: ");
		funcionario.setMatricula(input.nextLine());

		System.out.print("Faltas no ano: ");
		funcionario.setFaltasNoAno(input.nextInt());

		System.out.print("Última nota de avaliação: ");
		funcionario.setUltimaNotaAvaliacao(input.nextFloat());

		return funcionario;
	}

	public RH getRh() {
		return rh;
	}

	public static void main(String[] args) {
		Empresa empresa = new Empresa(new RH());

		for (int i = 0; i < 2; i++) {
			Funcionario funcionarioContratado = empresa.contratarFuncionario();
			empresa.getRh().adicionarFuncionario(funcionarioContratado);
		}

		empresa.getRh().mostrarBonusTodosFuncionarios();
	}

}
