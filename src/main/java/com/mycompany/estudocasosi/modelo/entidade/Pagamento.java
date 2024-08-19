package com.mycompany.estudocasosi.modelo.entidade;

/**
 *
 * @author guilh
 */
public class Pagamento {
    private Long id;
    private Double valor;
    private String metodo;
    private java.sql.Timestamp dataPagamento;
    private String tipo;
    private String descricao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public java.sql.Timestamp getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(java.sql.Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
