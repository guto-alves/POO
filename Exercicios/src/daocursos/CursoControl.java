package daocursos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CursoControl {
	private ObservableList<Curso> cursos;
	private CursoDao cursoDao;

	public CursoControl() {
		cursoDao = new CursoDaoImpl();
		cursos = FXCollections.observableArrayList();
		pesquisarPorNome("");
	}
	
	public void adicionar(Curso curso) {
		cursoDao.adicionar(curso);
		pesquisarPorNome("");
	}
	
	public void pesquisarPorNome(String nome) {
		cursos.setAll(cursoDao.pesquisarPorNome("%" + nome + "%"));
	}
	
	public ObservableList<Curso> getCursos() {
		return cursos;
	}
}
