package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDao extends GenericoDAO<Cliente> {
    
    public void salvar(Cliente cliente) {
        String insert = "INSERT INTO CLIENTE(NOME, CPF, TELEFONE, EMAIL) VALUES (?, ?, ?, ?)";
        save(insert, cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail());
    }
    
    public void alterar(Cliente cliente) {
        String update = "UPDATE CLIENTE SET NOME=?, CPF=?, TELEFONE=?, EMAIL=? WHERE ID=?";
        save(update, cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail(), cliente.getId());
    }
    
    public void excluir(Cliente cliente) {
        String delete = "DELETE FROM CLIENTE WHERE ID=?";
        save(delete, cliente.getId());
    }
    
    public Cliente buscarPorId(Long id) {
        String select = "SELECT * FROM CLIENTE WHERE ID=?";
        return buscarPorId(select, new ClienteRowMapper(), id);
    }
    
    public List<Cliente> buscarTodos() {
        String select = "SELECT * FROM CLIENTE";
        return buscarTodos(select, new ClienteRowMapper());
    }
    
    public static class ClienteRowMapper implements RowMapper<Cliente> {

        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("ID"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setCpf(rs.getString("CPF"));
            cliente.setTelefone(rs.getString("TELEFONE"));
            cliente.setEmail(rs.getString("EMAIL"));
            return cliente;
        }
        
    }
}
