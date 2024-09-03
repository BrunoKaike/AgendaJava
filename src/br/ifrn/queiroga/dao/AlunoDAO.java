package br.ifrn.queiroga.dao;

import br.ifrn.queiroga.model.Aluno;
import br.ifrn.queiroga.conexao.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AlunoDAO {

    
        /**
         * Método utilizado para buscar todos os alunos.
         * @return 
         */
	
	public List<Aluno> getAll() {
		String sql = "SELECT * FROM aluno";
		ResultSet rs = null;
		List<Aluno> allAluno = new ArrayList<>();
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			rs = ps.executeQuery();
			while (rs.next()) {
			    Aluno a = new Aluno();
                            a.setId(rs.getInt("id_aluno"));
                            a.setNome(rs.getString("nome"));
                            a.setCpf( rs.getString("cpf") );
                            a.setEmail( rs.getString("email"));
                            allAluno.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FabricaConexao.fecharConexao(rs);
		}
		return allAluno;
	}

	
	public Aluno getById(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException();
		}
		String sql = "SELECT * FROM aluno WHERE id=?";
		ResultSet rs = null;
		Aluno aluno = null;
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			aluno = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),rs.getString("cpf"));	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FabricaConexao.fecharConexao(rs);
		}
		return aluno;
	}

	
	public boolean create(Aluno aluno) {
		if (aluno == null) {
			throw new IllegalArgumentException();
		}
		String sql = "INSERT INTO aluno (nome, email,cpf) VALUES (?, ?, ?)";
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, aluno.getNome() );
			ps.setString(2, aluno.getEmail() );
                        ps.setString(3, aluno.getCpf() );
			int linhasAfetadas = ps.executeUpdate();
			if (linhasAfetadas > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean update(Aluno aluno) {
		if (aluno == null || aluno.getId() <=0 || aluno.getId() <= 0) {
			throw new IllegalArgumentException();
		}
		String sql = "UPDATE aluno SET nome = ?, email = ?, cpf=? WHERE id_aluno = ?";
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getEmail());
                        ps.setString(3, aluno.getCpf());
			ps.setInt(4, aluno.getId());
			int linhasAfetadas = ps.executeUpdate();
			if (linhasAfetadas > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean delete(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException();
		}
		String sql = "DELETE FROM aluno WHERE aluno.id_aluno = ?";
		try 
                    (Connection conn = FabricaConexao.getConexao();
                        PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			int linhasAfetadas = ps.executeUpdate();
			if (linhasAfetadas > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}


	public List<Aluno> getByName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		String sql = "SELECT * FROM `aluno` where nome like ?";
		ResultSet rs = null;
		List<Aluno> allAlunos = new ArrayList<>();
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Aluno a = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),rs.getString("cpf"));	
                                allAlunos.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FabricaConexao.fecharConexao(rs);
		}
		return allAlunos;
	}

}
