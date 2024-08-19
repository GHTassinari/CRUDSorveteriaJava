package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.Avaliacao;
import com.mycompany.estudocasosi.modelo.entidade.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AvaliacaoDao extends GenericoDAO<Avaliacao> {

    public void salvar(Avaliacao avaliacao) {
        String insert = "INSERT INTO avaliacao(venda_id, descricao, nota) VALUES (?, ?, ?)";
        save(insert, avaliacao.getVendaId(), avaliacao.getDescricao(), avaliacao.getNota());
    }

    public void alterar(Avaliacao avaliacao) {
        String update = "UPDATE avaliacao SET venda_id=?, descricao=?, nota=? WHERE id=?";
        save(update, avaliacao.getVendaId(), avaliacao.getDescricao(), avaliacao.getNota(), avaliacao.getId());
    }

    public void excluir(Avaliacao avaliacao) {
        String delete = "DELETE FROM avaliacao WHERE id=?";
        save(delete, avaliacao.getId());
    }

    public Avaliacao buscarPorId(Long id) {
        String select = "SELECT * FROM avaliacao WHERE id=?";
        VendaDao vendaDao = new VendaDao();
        Avaliacao avaliacao = buscarPorId(select, new AvaliacaoRowMapper(), id);

        if (avaliacao != null) {
            Venda venda = vendaDao.buscarPorId(avaliacao.getVendaId());
            avaliacao.setVenda(venda);
        }

        return avaliacao;
    }

    public List<Avaliacao> buscarTodos() {
        String select = "SELECT * FROM avaliacao";
        List<Avaliacao> avaliacoes = buscarTodos(select, new AvaliacaoRowMapper());

        VendaDao vendaDao = new VendaDao();
        for (Avaliacao avaliacao : avaliacoes) {
            Venda venda = vendaDao.buscarPorId(avaliacao.getVendaId());
            avaliacao.setVenda(venda);
        }

        return avaliacoes;
    }

    public static class AvaliacaoRowMapper implements RowMapper<Avaliacao> {

        @Override
        public Avaliacao mapRow(ResultSet rs) throws SQLException {
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setId(rs.getLong("id"));
            avaliacao.setVendaId(rs.getLong("venda_id"));
            avaliacao.setDescricao(rs.getString("descricao"));
            avaliacao.setNota(rs.getInt("nota"));
            return avaliacao;
        }
    }
}
