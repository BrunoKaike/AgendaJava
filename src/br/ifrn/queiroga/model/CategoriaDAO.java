/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifrn.queiroga.model;

import br.ifrn.queiroga.conexao.FabricaConexao;
import br.ifrn.queiroga.model.Categoria;
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
public class CategoriaDAO {

    public List<Categoria> getAll() {
        String sql = "SELECT * FROM categoria";
        ResultSet rs = null;
        List<Categoria> allCategoria = new ArrayList<>();
        try (
                Connection conn = FabricaConexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria p = new Categoria();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                allCategoria.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(rs);
        }
        return allCategoria;
    }

    public Categoria getById(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * FROM categoria WHERE id=?";
        ResultSet rs = null;
        Categoria pe = null;
        try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();

            pe = new Categoria();
            pe.setId(rs.getInt("id"));
            pe.setNome(rs.getString("nome"));
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(rs);
        }
        return pe;
    }
}
    