package alunosbcefx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoControl {
	private ObservableList<Aluno> alunos;
 
	public AlunoControl() {
		alunos = FXCollections.observableArrayList();
	}

	public void adicionar(Aluno aluno) {
		alunos.add(aluno);
	}

	public Aluno pesquisarPorNome(String nome) {
		for (Aluno aluno : alunos) {
			if (aluno.getNome().contains(nome)) {
				return aluno;
			}
		}
		return null;
	}

	public ObservableList<Aluno> getAlunos() {
		return alunos;
	}
}
