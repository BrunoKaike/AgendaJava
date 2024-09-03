/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifrn.queiroga.dao;

import br.ifrn.queiroga.conexao.FabricaConexao;
import br.ifrn.queiroga.model.Aluno;
import br.ifrn.queiroga.model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeferson
 */
public class PessoaDAO {

    public boolean create(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException();
        }
        String sql = "INSERT INTO pessoa ( nome, apelido,email,telefone, nascimento, categoria) VALUES (?,?,?,?,?,?)";
        try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getApelido());
            ps.setString(3, pessoa.getEmail());
            ps.setString(4, pessoa.getTelefone());
            ps.setString(5, pessoa.getNasc());
            ps.setInt(6, pessoa.getCategoria());
            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Pessoa> getAll() {
        String sql = "SELECT * FROM pessoa";
        ResultSet rs = null;
        List<Pessoa> allPessoa = new ArrayList<>();
        try (
                Connection conn = FabricaConexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setApelido(rs.getString("apelido"));
                p.setEmail(rs.getString("email"));
                p.setTelefone(rs.getString("telefone"));
                p.setNasc(rs.getString("nascimento"));
                p.setCategoria(rs.getInt("categoria"));
                allPessoa.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(rs);
        }
        return allPessoa;
    }

    public Pessoa getById(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * FROM pessoa WHERE id=?";
        ResultSet rs = null;
        Pessoa pe = null;
        try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();

            pe = new Pessoa();
            pe.setId(rs.getInt("id"));
            pe.setNome(rs.getString("nome"));
            pe.setEmail(rs.getString("email"));
            pe.setApelido(rs.getString("apelido"));
            pe.setNasc(rs.getString("nascimento"));
            pe.setTelefone(rs.getString("telefone"));
            pe.setCategoria(rs.getInt("categoria"));
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(rs);
        }
        return pe;
    }
    
     public List<Pessoa> getByName(String nome) {
        List <Pessoa> nomes = new ArrayList<Pessoa>();
        
        String sql = "SELECT * FROM pessoa WHERE nome LIKE ?";
        ResultSet rs = null;
        Pessoa pe = null;
        try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%"+nome+"%");
            rs = ps.executeQuery();
            
            while( rs.next()){
                pe = new Pessoa();
                pe.setId(rs.getInt("id"));
                pe.setNome(rs.getString("nome"));
                pe.setEmail(rs.getString("email"));
                pe.setApelido(rs.getString("apelido"));
                pe.setNasc(rs.getString("nascimento"));
                pe.setTelefone(rs.getString("telefone"));
                pe.setCategoria(rs.getInt("categoria"));
                
                nomes.add(pe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(rs);
        }
        return nomes;
    }

    public boolean update(Pessoa pessoa) {
        if (pessoa == null || pessoa.getId() <= 0 || pessoa.getId() <= 0) {
            throw new IllegalArgumentException();
        }
        String sql = "UPDATE pessoa SET nome = ?,apelido = ?, email=?, telefone = ?, nascimento = ?, categoria = ? WHERE id = ?" ;
        try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pessoa.getNome() );
            ps.setString(2, pessoa.getApelido());
            ps.setString(4, pessoa.getEmail() );
            ps.setString(3, pessoa.getTelefone() );
            ps.setString(5, pessoa.getNasc());
            ps.setInt(6, pessoa.getCategoria());
            ps.setInt(7, pessoa.getId());
            
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
		String sql = "DELETE FROM pessoa WHERE pessoa.id = ?";
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

}
