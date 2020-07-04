package daocursos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoImpl implements CursoDao {
	private PreparedStatement inserirCurso;
	private PreparedStatement selecionarCursoPorNome;
	
	public CursoDaoImpl() {
		try {
			Connection connection = DBUtil.getInstance().getConnection();
			inserirCurso = connection.prepareStatement(
					"INSERT INTO cursos " + 
					"(nome, descricao, ativo, inicio, termino) " +
					"VALUES (?, ?, ?, ?, ?)");
			
			selecionarCursoPorNome = connection.prepareStatement(
					"SELECT * FROM cursos WHERE nome LIKE ?");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void adicionar(Curso curso) {
		try {
			inserirCurso.setString(1, curso.getNome());
			inserirCurso.setString(2, curso.getDescricao());
			inserirCurso.setBoolean(3, curso.isAtivo());
			inserirCurso.setString(4, curso.getInicio());
			inserirCurso.setString(5, curso.getTermino());
			
			inserirCurso.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Curso> pesquisarPorNome(String nome) {
		try {
			selecionarCursoPorNome.setString(1, nome);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		try (ResultSet resultSet = selecionarCursoPorNome.executeQuery()) {
			List<Curso> cursos = new ArrayList<Curso>();
			
			while (resultSet.next()) {
				cursos.add(new Curso(
						resultSet.getLong("id"), 
						resultSet.getString("nome"), 
						resultSet.getString("descricao"), 
						resultSet.getBoolean("ativo"), 
						resultSet.getString("inicio"), 
						resultSet.getString("termino")));
			}
			
			return cursos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
