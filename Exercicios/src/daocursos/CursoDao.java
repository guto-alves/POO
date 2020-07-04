package daocursos;

import java.util.List;

public interface CursoDao {
	
	public abstract void adicionar(Curso curso);
	
	public abstract List<Curso> pesquisarPorNome(String nome);

}
