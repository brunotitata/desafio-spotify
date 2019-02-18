package br.com.beblue.desafio.model;

import java.time.LocalDate;

public class RegistraVenda {

    private String idAlbum;
    private LocalDate diaDaCompra;
    private Integer quantidade;

    public RegistraVenda(String idAlbum, LocalDate diaDaCompra,
            Integer quantidade) {
        this.idAlbum = idAlbum;
        this.diaDaCompra = diaDaCompra;
        this.quantidade = quantidade;
    }

    @SuppressWarnings("unused")
    private RegistraVenda() {
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public LocalDate getDiaDaCompra() {
        return diaDaCompra;
    }

    public void setDiaDaCompra(LocalDate diaDaCompra) {
        this.diaDaCompra = diaDaCompra;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((diaDaCompra == null) ? 0 : diaDaCompra.hashCode());
        result = prime * result + ((idAlbum == null) ? 0 : idAlbum.hashCode());
        result = prime * result
                + ((quantidade == null) ? 0 : quantidade.hashCode());
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
        RegistraVenda other = (RegistraVenda) obj;
        if (diaDaCompra == null) {
            if (other.diaDaCompra != null)
                return false;
        } else if (!diaDaCompra.equals(other.diaDaCompra))
            return false;
        if (idAlbum == null) {
            if (other.idAlbum != null)
                return false;
        } else if (!idAlbum.equals(other.idAlbum))
            return false;
        if (quantidade == null) {
            if (other.quantidade != null)
                return false;
        } else if (!quantidade.equals(other.quantidade))
            return false;
        return true;
    }

}
