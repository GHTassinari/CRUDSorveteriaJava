package com.mycompany.estudocasosi.modelo.entidade;

import java.util.Date;

public class Venda {
    private Long id;
    private Long idCliente;
    private Long idFuncionario;
    private Long idFornecedor;
    private Long idSorvete;
    private Long idMaquinaDeSorvete;
    private Long idPagamento;
    private Long idLoja;
    private Date dataVenda;

    // Referências para objetos relacionados
    private Cliente cliente;
    private Funcionario funcionario;
    private Fornecedor fornecedor;
    private Sorvete sorvete;
    private MaquinaDeSorvete maquinaDeSorvete;
    private Pagamento pagamento;
    private Loja loja;

    // Getters e Setters para ID's
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Long getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Long idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Long getIdSorvete() {
        return idSorvete;
    }

    public void setIdSorvete(Long idSorvete) {
        this.idSorvete = idSorvete;
    }

    public Long getIdMaquinaDeSorvete() {
        return idMaquinaDeSorvete;
    }

    public void setIdMaquinaDeSorvete(Long idMaquinaDeSorvete) {
        this.idMaquinaDeSorvete = idMaquinaDeSorvete;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    // Getters e Setters para Objetos Relacionados
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Sorvete getSorvete() {
        return sorvete;
    }

    public void setSorvete(Sorvete sorvete) {
        this.sorvete = sorvete;
    }

    public MaquinaDeSorvete getMaquinaDeSorvete() {
        return maquinaDeSorvete;
    }

    public void setMaquinaDeSorvete(MaquinaDeSorvete maquinaDeSorvete) {
        this.maquinaDeSorvete = maquinaDeSorvete;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
