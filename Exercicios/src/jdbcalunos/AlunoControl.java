package jdbcalunos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoControl {
	private ObservableList<Aluno> alunos;
	private AlunoDao alunoDao;

	public AlunoControl() {
		alunoDao = new AlunoDaoImpl(); 
		alunos = FXCollections.observableArrayList();
		selecionarTodosAlunos();
	}
	
	public void adicionar(Aluno aluno) {
		alunoDao.adicionarAluno(aluno); 
		selecionarTodosAlunos();
	}
	
	public void deletarAluno(Aluno aluno) {
		alunoDao.deletarAluno(aluno);
		selecionarTodosAlunos();
	}
	
	public void atualizarAluno(Aluno aluno, String raAtual) {
		alunoDao.atualizarAluno(aluno, raAtual);
		selecionarTodosAlunos();
	}
	
	public void selecionarTodosAlunos() {
		alunos.setAll(alunoDao.selecionarTodosAlunos());
	}
	
	public Aluno selecionarAlunoPorRa(String ra) {
		return alunoDao.selecionarAlunoPorRa(ra);
	}
	
	public ObservableList<Aluno> getAlunos() {
		return alunos;
	}
}
