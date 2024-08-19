package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao extends GenericoDAO<Funcionario> {
    
    public void salvar(Funcionario funcionario) {
        String insert = "INSERT INTO FUNCIONARIO(NOME, CARGO, SALARIO) VALUES (?, ?, ?)";
        save(insert, funcionario.getNome(), funcionario.getCargo(), funcionario.getSalario());
    }
    
    public void alterar(Funcionario funcionario) {
        String update = "UPDATE FUNCIONARIO SET NOME=?, CARGO=?, SALARIO=? WHERE ID=?";
        save(update, funcionario.getNome(), funcionario.getCargo(), funcionario.getSalario(), funcionario.getId());
    }
    
    public void excluir(Funcionario funcionario) {
        String delete = "DELETE FROM FUNCIONARIO WHERE ID=?";
        save(delete, funcionario.getId());
    }
    
    public Funcionario buscarPorId(Long id) {
        String select = "SELECT * FROM FUNCIONARIO WHERE ID=?";
        return buscarPorId(select, new FuncionarioRowMapper(), id);
    }
    
    public List<Funcionario> buscarTodos() {
        String select = "SELECT * FROM FUNCIONARIO";
        return buscarTodos(select, new FuncionarioRowMapper());
    }
    
    public static class FuncionarioRowMapper implements RowMapper<Funcionario> {

        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getLong("ID"));
            funcionario.setNome(rs.getString("NOME"));
            funcionario.setCargo(rs.getString("CARGO"));
            funcionario.setSalario(rs.getDouble("SALARIO"));
            return funcionario;
        }
        
    }
}
