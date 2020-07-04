package jdbcalunos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDaoImpl implements AlunoDao {
	private PreparedStatement inserirAluno;
	private PreparedStatement deletarAluno;
	private PreparedStatement atualizarAluno;
	private PreparedStatement selecionarAlunoPorRa;
	private PreparedStatement selecionarTodosAlunos;
	
	public AlunoDaoImpl() { 
		try {
			Connection connection = DBUtil.getInstance().getConnection();
			
			inserirAluno = connection.prepareStatement(
					"INSERT INTO alunos " +
					"(ra, nome, idade, nascimento) " +
					"VALUES (?, ?, ?, ?)");

			deletarAluno = connection.prepareStatement(
					"DELETE FROM alunos WHERE ra = ?");

			atualizarAluno = connection.prepareStatement(
					"UPDATE alunos " +
					"SET ra = ?, nome = ?, idade = ?, nascimento = ? " +
					"WHERE ra = ?");

			selecionarTodosAlunos = connection.prepareStatement(
					"SELECT * FROM alunos");
			
			selecionarAlunoPorRa = connection.prepareStatement(
					"SELECT * FROM alunos WHERE ra = ?");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public int adicionarAluno(Aluno aluno) {
		try {
			inserirAluno.setString(1, aluno.getRa());
			inserirAluno.setString(2, aluno.getNome());
			inserirAluno.setInt(3, aluno.getIdade());
			inserirAluno.setString(4, aluno.getNascimento());

			return inserirAluno.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boolean deletarAluno(Aluno aluno) {
		try {
			deletarAluno.setString(1, aluno.getRa());
			
			deletarAluno.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean atualizarAluno(Aluno aluno, String raAtual) {
		try {
			atualizarAluno.setString(1, aluno.getRa());
			atualizarAluno.setString(2, aluno.getNome());
			atualizarAluno.setInt(3, aluno.getIdade());
			atualizarAluno.setString(4, aluno.getNascimento());
			atualizarAluno.setString(5, raAtual);
			
			atualizarAluno.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	@Override
	public List<Aluno> selecionarTodosAlunos(){
		try (ResultSet resultSet = selecionarTodosAlunos.executeQuery()) {
			List<Aluno> alunos = new ArrayList<Aluno>();
			
			while (resultSet.next()) {
				alunos.add(new Aluno(
					resultSet.getString("ra"),
					resultSet.getString("nome"),
					resultSet.getInt("idade"),
					resultSet.getString("nascimento")));
			}
			
			return alunos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Aluno selecionarAlunoPorRa(String ra) {
		try {
			selecionarAlunoPorRa.setString(1, ra);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		try (ResultSet resultSet = selecionarAlunoPorRa.executeQuery()) {
			resultSet.next();

			Aluno aluno = new Aluno(
					resultSet.getString("ra"),
					resultSet.getString("nome"),
					resultSet.getInt("idade"),
					resultSet.getString("nascimento"));

			return aluno;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
