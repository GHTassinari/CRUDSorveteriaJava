package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.Sorvete;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SorveteDao extends GenericoDAO<Sorvete> {

    public void salvar(Sorvete sorvete) {
        String insert = "INSERT INTO SORVETE(SABOR, TAMANHO, PRECO, QUANTIDADE) VALUES (?, ?, ?, ?)";
        save(insert, sorvete.getSabor(), sorvete.getTamanho(), sorvete.getPreco(), sorvete.getQuantidade());
    }

    public void alterar(Sorvete sorvete) {
        String update = "UPDATE SORVETE SET SABOR=?, TAMANHO=?, PRECO=?, QUANTIDADE=? WHERE ID=?";
        save(update, sorvete.getSabor(), sorvete.getTamanho(), sorvete.getPreco(), sorvete.getQuantidade(), sorvete.getId());
    }

    public void excluir(Sorvete sorvete) {
        String delete = "DELETE FROM SORVETE WHERE ID=?";
        save(delete, sorvete.getId());
    }

    public Sorvete buscarPorId(Long id) {
        String select = "SELECT * FROM SORVETE WHERE ID=?";
        return buscarPorId(select, new SorveteRowMapper(), id);
    }

    public List<Sorvete> buscarTodos() {
        String select = "SELECT * FROM SORVETE";
        return buscarTodos(select, new SorveteRowMapper());
    }

    public static class SorveteRowMapper implements RowMapper<Sorvete> {

        @Override
        public Sorvete mapRow(ResultSet rs) throws SQLException {
            Sorvete sorvete = new Sorvete();
            sorvete.setId(rs.getLong("ID"));
            sorvete.setSabor(rs.getString("SABOR"));
            sorvete.setTamanho(rs.getDouble("TAMANHO"));
            sorvete.setPreco(rs.getDouble("PRECO"));
            sorvete.setQuantidade(rs.getInt("QUANTIDADE"));
            return sorvete;
        }
        
    }
}
