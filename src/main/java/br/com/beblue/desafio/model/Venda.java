package br.com.beblue.desafio.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nomeAlbum;
    private BigDecimal valorAlbum;
    private BigDecimal valorCashBack;
    private Integer quantidade;
    private BigDecimal valorTotal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataDaCompra;
    private BigDecimal valorRetornado;

    public Venda(String nomeAlbum, BigDecimal valorAlbum,
            BigDecimal valorCashBack, Integer quantidade, BigDecimal valorTotal,
            LocalDate dataDaCompra, BigDecimal valorRetornado) {
        this.nomeAlbum = nomeAlbum;
        this.valorAlbum = valorAlbum;
        this.valorCashBack = valorCashBack;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataDaCompra = dataDaCompra;
        this.valorRetornado = valorRetornado;
    }

    public Venda() {
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public BigDecimal getValorAlbum() {
        return valorAlbum;
    }

    public void setValorAlbum(BigDecimal valorAlbum) {
        this.valorAlbum = valorAlbum;
    }

    public BigDecimal getValorCashBack() {
        return valorCashBack;
    }

    public void setValorCashBack(BigDecimal valorCashBack) {
        this.valorCashBack = valorCashBack;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(LocalDate dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getValorRetornado() {
        return valorRetornado;
    }

    public void setValorRetornado(BigDecimal valorRetornado) {
        this.valorRetornado = valorRetornado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dataDaCompra == null) ? 0 : dataDaCompra.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((nomeAlbum == null) ? 0 : nomeAlbum.hashCode());
        result = prime * result
                + ((quantidade == null) ? 0 : quantidade.hashCode());
        result = prime * result
                + ((valorAlbum == null) ? 0 : valorAlbum.hashCode());
        result = prime * result
                + ((valorCashBack == null) ? 0 : valorCashBack.hashCode());
        result = prime * result
                + ((valorRetornado == null) ? 0 : valorRetornado.hashCode());
        result = prime * result
                + ((valorTotal == null) ? 0 : valorTotal.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Venda other = (Venda) obj;
        if (dataDaCompra == null) {
            if (other.dataDaCompra != null)
                return false;
        } else if (!dataDaCompra.equals(other.dataDaCompra))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nomeAlbum == null) {
            if (other.nomeAlbum != null)
                return false;
        } else if (!nomeAlbum.equals(other.nomeAlbum))
            return false;
        if (quantidade == null) {
            if (other.quantidade != null)
                return false;
        } else if (!quantidade.equals(other.quantidade))
            return false;
        if (valorAlbum == null) {
            if (other.valorAlbum != null)
                return false;
        } else if (!valorAlbum.equals(other.valorAlbum))
            return false;
        if (valorCashBack == null) {
            if (other.valorCashBack != null)
                return false;
        } else if (!valorCashBack.equals(other.valorCashBack))
            return false;
        if (valorRetornado == null) {
            if (other.valorRetornado != null)
                return false;
        } else if (!valorRetornado.equals(other.valorRetornado))
            return false;
        if (valorTotal == null) {
            if (other.valorTotal != null)
                return false;
        } else if (!valorTotal.equals(other.valorTotal))
            return false;
        return true;
    }

}
