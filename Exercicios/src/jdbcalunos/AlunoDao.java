package jdbcalunos;

import java.util.List;

public interface AlunoDao {

	public abstract int adicionarAluno(Aluno aluno);

	public abstract boolean deletarAluno(Aluno aluno);

	public abstract boolean atualizarAluno(Aluno aluno, String raAtual);
	
	public abstract List<Aluno> selecionarTodosAlunos();
	
	public abstract Aluno selecionarAlunoPorRa(String ra);

}
