package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendaDao extends GenericoDAO<Venda> {

    private final ClienteDao clienteDao;
    private final FuncionarioDao funcionarioDao;
    private final FornecedorDao fornecedorDao;
    private final SorveteDao sorveteDao;
    private final MaquinaDeSorveteDao maquinaDeSorveteDao;
    private final PagamentoDao pagamentoDao;
    private final LojaDao lojaDao;

    public VendaDao() {
        this.clienteDao = new ClienteDao();
        this.funcionarioDao = new FuncionarioDao();
        this.fornecedorDao = new FornecedorDao();
        this.sorveteDao = new SorveteDao();
        this.maquinaDeSorveteDao = new MaquinaDeSorveteDao();
        this.pagamentoDao = new PagamentoDao();
        this.lojaDao = new LojaDao();
    }

    public void salvar(Venda venda) {
        String insert = "INSERT INTO VENDA(id_cliente, id_funcionario, id_fornecedor, id_sorvete, id_maquinaDeSorvete, id_pagamento, id_loja, data_venda) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        save(insert, venda.getIdCliente(), venda.getIdFuncionario(), venda.getIdFornecedor(), venda.getIdSorvete(), venda.getIdMaquinaDeSorvete(), venda.getIdPagamento(), venda.getIdLoja(), venda.getDataVenda());
    }

    public void alterar(Venda venda) {
        String update = "UPDATE VENDA SET id_cliente=?, id_funcionario=?, id_fornecedor=?, id_sorvete=?, id_maquinaDeSorvete=?, id_pagamento=?, id_loja=?, data_venda=? WHERE id=?";
        save(update, venda.getIdCliente(), venda.getIdFuncionario(), venda.getIdFornecedor(), venda.getIdSorvete(), venda.getIdMaquinaDeSorvete(), venda.getIdPagamento(), venda.getIdLoja(), venda.getDataVenda(), venda.getId());
    }

    public void excluir(Venda venda) {
        String delete = "DELETE FROM VENDA WHERE id=?";
        save(delete, venda.getId());
    }

    public Venda buscarPorId(Long id) {
        String select = "SELECT * FROM VENDA WHERE id=?";
        Venda venda = buscarPorId(select, new VendaRowMapper(), id);

        // Carrega os objetos relacionados
        venda.setCliente(clienteDao.buscarPorId(venda.getIdCliente()));
        venda.setFuncionario(funcionarioDao.buscarPorId(venda.getIdFuncionario()));
        venda.setFornecedor(fornecedorDao.buscarPorId(venda.getIdFornecedor()));
        venda.setSorvete(sorveteDao.buscarPorId(venda.getIdSorvete()));
        venda.setMaquinaDeSorvete(maquinaDeSorveteDao.buscarPorId(venda.getIdMaquinaDeSorvete()));
        venda.setPagamento(pagamentoDao.buscarPorId(venda.getIdPagamento()));
        venda.setLoja(lojaDao.buscarPorId(venda.getIdLoja()));

        return venda;
    }

    public List<Venda> buscarTodos() {
        String select = "SELECT * FROM VENDA";
        List<Venda> vendas = buscarTodos(select, new VendaRowMapper());

        for (Venda venda : vendas) {
            // Carrega os objetos relacionados
            venda.setCliente(clienteDao.buscarPorId(venda.getIdCliente()));
            venda.setFuncionario(funcionarioDao.buscarPorId(venda.getIdFuncionario()));
            venda.setFornecedor(fornecedorDao.buscarPorId(venda.getIdFornecedor()));
            venda.setSorvete(sorveteDao.buscarPorId(venda.getIdSorvete()));
            venda.setMaquinaDeSorvete(maquinaDeSorveteDao.buscarPorId(venda.getIdMaquinaDeSorvete()));
            venda.setPagamento(pagamentoDao.buscarPorId(venda.getIdPagamento()));
            venda.setLoja(lojaDao.buscarPorId(venda.getIdLoja()));
        }

        return vendas;
    }

    // Métodos para buscar as listas relacionadas
    public List<Cliente> buscarTodosClientes() {
        return clienteDao.buscarTodos();
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        return funcionarioDao.buscarTodos();
    }

    public List<Fornecedor> buscarTodosFornecedores() {
        return fornecedorDao.buscarTodos();
    }

    public List<Sorvete> buscarTodosSorvetes() {
        return sorveteDao.buscarTodos();
    }

    public List<MaquinaDeSorvete> buscarTodasMaquinasDeSorvete() {
        return maquinaDeSorveteDao.buscarTodos();
    }

    public List<Pagamento> buscarTodosPagamentos() {
        return pagamentoDao.buscarTodos();
    }

    public List<Loja> buscarTodasLojas() {
        return lojaDao.buscarTodos();
    }

    public static class VendaRowMapper implements RowMapper<Venda> {

        @Override
        public Venda mapRow(ResultSet rs) throws SQLException {
            Venda venda = new Venda();
            venda.setId(rs.getLong("id"));
            venda.setIdCliente(rs.getLong("id_cliente"));
            venda.setIdFuncionario(rs.getLong("id_funcionario"));
            venda.setIdFornecedor(rs.getLong("id_fornecedor"));
            venda.setIdSorvete(rs.getLong("id_sorvete"));
            venda.setIdMaquinaDeSorvete(rs.getLong("id_maquinaDeSorvete"));
            venda.setIdPagamento(rs.getLong("id_pagamento"));
            venda.setIdLoja(rs.getLong("id_loja"));
            venda.setDataVenda(rs.getTimestamp("data_venda"));
            return venda;
        }
    }
}
