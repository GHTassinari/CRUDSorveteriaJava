package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.MaquinaDeSorvete;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MaquinaDeSorveteDao extends GenericoDAO<MaquinaDeSorvete> {

    public void salvar(MaquinaDeSorvete maquinaDeSorvete) {
        String insert = "INSERT INTO MAQUINADESORVETE(MODELO, CAPACIDADE, DATA_AQUISICAO, STATUS) VALUES (?, ?, ?, ?)";
        save(insert, maquinaDeSorvete.getModelo(), maquinaDeSorvete.getCapacidade(), maquinaDeSorvete.getDataAquisicao(), maquinaDeSorvete.getStatus());
    }

    public void alterar(MaquinaDeSorvete maquinaDeSorvete) {
        String update = "UPDATE MAQUINADESORVETE SET MODELO=?, CAPACIDADE=?, DATA_AQUISICAO=?, STATUS=? WHERE ID=?";
        save(update, maquinaDeSorvete.getModelo(), maquinaDeSorvete.getCapacidade(), maquinaDeSorvete.getDataAquisicao(), maquinaDeSorvete.getStatus(), maquinaDeSorvete.getId());
    }

    public void excluir(MaquinaDeSorvete maquinaDeSorvete) {
        String delete = "DELETE FROM MAQUINADESORVETE WHERE ID=?";
        save(delete, maquinaDeSorvete.getId());
    }

    public MaquinaDeSorvete buscarPorId(Long id) {
        String select = "SELECT * FROM MAQUINADESORVETE WHERE ID=?";
        return buscarPorId(select, new MaquinaDeSorveteRowMapper(), id);
    }

    public List<MaquinaDeSorvete> buscarTodos() {
        String select = "SELECT * FROM MAQUINADESORVETE";
        return buscarTodos(select, new MaquinaDeSorveteRowMapper());
    }

    public static class MaquinaDeSorveteRowMapper implements RowMapper<MaquinaDeSorvete> {

        @Override
        public MaquinaDeSorvete mapRow(ResultSet rs) throws SQLException {
            MaquinaDeSorvete maquina = new MaquinaDeSorvete();
            maquina.setId(rs.getLong("ID"));
            maquina.setModelo(rs.getString("MODELO"));
            maquina.setCapacidade(rs.getDouble("CAPACIDADE"));
            maquina.setDataAquisicao(rs.getDate("DATA_AQUISICAO"));
            maquina.setStatus(rs.getString("STATUS"));
            return maquina;
        }
        
    }
}
