package funcionario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class GestaoFuncionario {
	public static final SimpleDateFormat FORMATO_DATA = new SimpleDateFormat("dd/MM/yyyy");
	private static final int TOTAL_FUNCIONARIOS = 50;
	private static int indice;
	private static Funcionario[] funcionarios = new Funcionario[TOTAL_FUNCIONARIOS];

	public static void main(String[] args) {
		menu();
	}

	public static void criar() {
		long id = Long.parseLong(JOptionPane.showInputDialog("Digite o número de ID:"));
		String nome = JOptionPane.showInputDialog("Digite o nome:");
		String matricula = JOptionPane.showInputDialog("Digite a matrícula:");
		float salario = Float.parseFloat(JOptionPane.showInputDialog("Digite o salário:"));
		String horario = JOptionPane.showInputDialog("Digite o horário:");
		
		Date admissao;
		while (true) {
			try {
				admissao = FORMATO_DATA
						.parse(JOptionPane.showInputDialog("Digite a data de admissão (no formato: dd/mm/yyyy):"));
				break;
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Data de admissão inválida, tente novamente!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		Date demissao;
		while (true) {
			try {
				demissao = FORMATO_DATA
						.parse(JOptionPane.showInputDialog("Digite a data de demissão (no formato: dd/mm/yyyy):"));
				break;
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Data de demissão inválida, tente novamente!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		funcionarios[indice] = new Funcionario(id, nome, matricula, admissao, demissao, salario, horario);
		indice++;
	}

	public static void atualizar() {
		String matricula = JOptionPane.showInputDialog(null, "Digite o número do RA:", "Atualização de Funcionario",
				JOptionPane.QUESTION_MESSAGE);

		for (Funcionario funcionario : funcionarios) {
			if (funcionario == null) {
				continue;
			}

			if (funcionario.getMatricula().equals(matricula)) {
				String nome = JOptionPane.showInputDialog("Digite o nome:");
				float salario = Float.parseFloat(JOptionPane.showInputDialog("Digite o salário:"));
				String horario = JOptionPane.showInputDialog("Digite o horário:");
				
				Date admissao;
				while (true) {
					try {
						admissao = FORMATO_DATA
								.parse(JOptionPane.showInputDialog("Digite a data de admissão (no formato: dd/mm/yyyy):"));
						break;
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Data de admissão inválida, tente novamente!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				Date demissao;
				while (true) {
					try {
						demissao = FORMATO_DATA
								.parse(JOptionPane.showInputDialog("Digite a data de demissão (no formato: dd/mm/yyyy):"));
						break;
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Data de demissão inválida, tente novamente!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
				funcionario.setNome(nome);
				funcionario.setAdmissao(admissao);
				funcionario.setDemissao(demissao);
				funcionario.setSalario(salario);
				funcionario.setHorario(horario);
				break;
			}
		}
	}

	public static void excluir() {
		String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula:", "Exclusão de Funcionario",
				JOptionPane.QUESTION_MESSAGE);

		for (int i = 0; i < TOTAL_FUNCIONARIOS; i++) {
			Funcionario funcionario = funcionarios[i];

			if (funcionario == null) {
				continue;
			}

			if (funcionario.getMatricula().equals(matricula)) {
				funcionarios[i] = null;
			}
		}
	}

	public static void exibir() {
		String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula:", "Exibição de Funcionario",
				JOptionPane.QUESTION_MESSAGE);

		for (Funcionario funcionario : funcionarios) {
			if (funcionario == null) {
				continue;
			}

			if (funcionario.getMatricula().equals(matricula)) {
				funcionario.exibir();
			}
		}
	}

	public static void menu() {
		String input;
		char opc;

		while (true) {
			input = JOptionPane
					.showInputDialog("Escolha uma opção:\n" + "(C)riar\n(E)xibir\n(R)emover\n(A)tualizar\n(S)air");
			opc = input.toUpperCase().trim().charAt(0);

			switch (opc) {
			case 'C':
				criar();
				break;
			case 'E':
				exibir();
				break;
			case 'R':
				excluir();
				break;
			case 'A':
				atualizar();
				break;
			case 'S':
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
