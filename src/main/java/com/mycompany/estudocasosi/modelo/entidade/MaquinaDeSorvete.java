package com.mycompany.estudocasosi.modelo.entidade;

/**
 *
 * @author guilh
 */
public class MaquinaDeSorvete {
    private Long id;
    private String modelo;
    private Double capacidade;
    private java.sql.Date dataAquisicao;
    private String status;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public java.sql.Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(java.sql.Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
