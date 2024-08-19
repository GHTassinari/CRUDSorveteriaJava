package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.Pagamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PagamentoDao extends GenericoDAO<Pagamento> {

    public void salvar(Pagamento pagamento) {
        String insert = "INSERT INTO PAGAMENTO (VALOR, METODO, DATA_PAGAMENTO, TIPO, DESCRICAO) VALUES (?, ?, ?, ?, ?)";
        save(insert, pagamento.getValor(), pagamento.getMetodo(), pagamento.getDataPagamento(), pagamento.getTipo(), pagamento.getDescricao());
    }

    public void alterar(Pagamento pagamento) {
        String update = "UPDATE PAGAMENTO SET VALOR=?, METODO=?, DATA_PAGAMENTO=?, TIPO=?, DESCRICAO=? WHERE ID=?";
        save(update, pagamento.getValor(), pagamento.getMetodo(), pagamento.getDataPagamento(), pagamento.getTipo(), pagamento.getDescricao(), pagamento.getId());
    }

    public void excluir(Pagamento pagamento) {
        String delete = "DELETE FROM PAGAMENTO WHERE ID=?";
        save(delete, pagamento.getId());
    }

    public Pagamento buscarPorId(Long id) {
        String select = "SELECT * FROM PAGAMENTO WHERE ID=?";
        return buscarPorId(select, new PagamentoRowMapper(), id);
    }

    public List<Pagamento> buscarTodos() {
        String select = "SELECT * FROM PAGAMENTO";
        return buscarTodos(select, new PagamentoRowMapper());
    }

    public static class PagamentoRowMapper implements RowMapper<Pagamento> {

        @Override
        public Pagamento mapRow(ResultSet rs) throws SQLException {
            Pagamento pagamento = new Pagamento();
            pagamento.setId(rs.getLong("ID"));
            pagamento.setValor(rs.getDouble("VALOR"));
            pagamento.setMetodo(rs.getString("METODO"));
            pagamento.setDataPagamento(rs.getTimestamp("DATA_PAGAMENTO"));
            pagamento.setTipo(rs.getString("TIPO"));
            pagamento.setDescricao(rs.getString("DESCRICAO"));
            return pagamento;
        }

    }
}
