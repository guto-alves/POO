package exerciciocomplementar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CursoControl {
	private ObservableList<Curso> cursos;

	public CursoControl() {
		cursos = FXCollections.observableArrayList();
	}
	
	public void adicionar(Curso curso) {
		cursos.add(curso);
	}

	public void deletar(Curso curso) {
		cursos.remove(curso);
	}
	
	public ObservableList<Curso> getCursos() {
		return cursos;
	}
}
