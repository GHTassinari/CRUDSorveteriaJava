package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.Loja;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LojaDao extends GenericoDAO<Loja> {
    
    public void salvar(Loja loja) {
        String insert = "INSERT INTO LOJA (NOME, ENDERECO, TELEFONE) VALUES (?, ?, ?)";
        save(insert, loja.getNome(), loja.getEndereco(), loja.getTelefone());
    }
    
    public void alterar(Loja loja) {
        String update = "UPDATE LOJA SET NOME=?, ENDERECO=?, TELEFONE=? WHERE ID=?";
        save(update, loja.getNome(), loja.getEndereco(), loja.getTelefone(), loja.getId());
    }
    
    public void excluir(Loja loja) {
        String delete = "DELETE FROM LOJA WHERE ID=?";
        save(delete, loja.getId());
    }
    
    public Loja buscarPorId(Long id) {
        String select = "SELECT * FROM LOJA WHERE ID=?";
        return buscarPorId(select, new LojaRowMapper(), id);
    }
    
    public List<Loja> buscarTodos() {
        String select = "SELECT * FROM LOJA";
        return buscarTodos(select, new LojaRowMapper());
    }
    
    public static class LojaRowMapper implements RowMapper<Loja> {

        @Override
        public Loja mapRow(ResultSet rs) throws SQLException {
            Loja loja = new Loja();
            loja.setId(rs.getLong("ID"));
            loja.setNome(rs.getString("NOME"));
            loja.setEndereco(rs.getString("ENDERECO"));
            loja.setTelefone(rs.getString("TELEFONE"));
            return loja;
        }
        
    }
}
