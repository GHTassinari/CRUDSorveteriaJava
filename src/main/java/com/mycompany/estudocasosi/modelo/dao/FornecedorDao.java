package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorDao extends GenericoDAO<Fornecedor> {
    
    public void salvar(Fornecedor fornecedor) {
        String insert = "INSERT INTO FORNECEDOR(NOME, CONTATO, ENDERECO) VALUES (?, ?, ?)";
        save(insert, fornecedor.getNome(), fornecedor.getContato(), fornecedor.getEndereco());
    }
    
    public void alterar(Fornecedor fornecedor) {
        String update = "UPDATE FORNECEDOR SET NOME=?, CONTATO=?, ENDERECO=? WHERE ID=?";
        save(update, fornecedor.getNome(), fornecedor.getContato(), fornecedor.getEndereco(), fornecedor.getId());
    }
    
    public void excluir(Fornecedor fornecedor) {
        String delete = "DELETE FROM FORNECEDOR WHERE ID=?";
        save(delete, fornecedor.getId());
    }
    
    public Fornecedor buscarPorId(Long id) {
        String select = "SELECT * FROM FORNECEDOR WHERE ID=?";
        return buscarPorId(select, new FornecedorRowMapper(), id);
    }
    
    public List<Fornecedor> buscarTodos() {
        String select = "SELECT * FROM FORNECEDOR";
        return buscarTodos(select, new FornecedorRowMapper());
    }
    
    public static class FornecedorRowMapper implements RowMapper<Fornecedor> {

        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(rs.getLong("ID"));
            fornecedor.setNome(rs.getString("NOME"));
            fornecedor.setContato(rs.getString("CONTATO"));
            fornecedor.setEndereco(rs.getString("ENDERECO"));
            return fornecedor;
        }
        
    }
}
