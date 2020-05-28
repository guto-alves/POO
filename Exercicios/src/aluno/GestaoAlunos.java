package aluno;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class GestaoAlunos {
	public static final SimpleDateFormat FORMATO_DATA = new SimpleDateFormat("dd/MM/yyyy");
	
	private static final int TOTAL_ALUNOS = 50;
	private static int indice;
	private static Aluno[] alunos = new Aluno[TOTAL_ALUNOS];

	public static void main(String[] args) {
		menu();
	}

	public static void criar() {
		long id = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de ID:"));
		String nome = JOptionPane.showInputDialog("Digite o nome:");
		String ra = JOptionPane.showInputDialog("Digite o RA:");

		Date nascimento;
		while (true) {
			try {
				nascimento = FORMATO_DATA
						.parse(JOptionPane.showInputDialog("Digite a data de nascimento (no formato: dd/mm/yyyy):"));
				break;
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Data de nascimento inválida, tente novamente!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		alunos[indice] = new Aluno(id, nome, ra, nascimento);
		indice++;
	}

	public static void atualizar() {
		String ra = JOptionPane.showInputDialog(null, "Digite o número do RA:", "Atualização de Aluno",
				JOptionPane.QUESTION_MESSAGE);

		for (Aluno aluno : alunos) {
			if (aluno == null) {
				continue;
			}

			if (aluno.getRa().equals(ra)) {
				String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");

				Date nascimento;
				while (true) {
					try {
						nascimento = FORMATO_DATA.parse(
								JOptionPane.showInputDialog("Digite a data de nascimento (no formato: dd/mm/yyyy):"));
						break;
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Data de nascimento inválida, tente novamente!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				aluno.setNome(nome);
				aluno.setNascimento(nascimento);
				break;
			}
		}
	}

	public static void excluir() {
		String ra = JOptionPane.showInputDialog(null, "Digite o número do RA:", "Exclusão de Aluno",
				JOptionPane.QUESTION_MESSAGE);

		for (int i = 0; i < TOTAL_ALUNOS; i++) {
			Aluno aluno = alunos[i];

			if (aluno == null) {
				continue;
			}

			if (aluno.getRa().equals(ra)) {
				alunos[i] = null;
			}
		}
	}

	public static void exibir() {
		String ra = JOptionPane.showInputDialog(null, "Digite o número do RA:", "Exibição de Aluno",
				JOptionPane.QUESTION_MESSAGE);

		for (Aluno aluno : alunos) {
			if (aluno == null) {
				continue;
			}

			if (aluno.getRa().equals(ra)) {
				aluno.exibir();
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
