package br.com.beblue.desafio.service.filter;

public class GeneroFilter {

    private String genero;
    private Integer page;
    private Integer size;
    private String orderBy;
    private String direction;

    public GeneroFilter(String genero, Integer page, Integer size,
            String orderBy, String direction) {
        this.genero = genero;
        this.page = page;
        this.size = size;
        this.orderBy = orderBy;
        this.direction = direction;
    }

    @SuppressWarnings("unused")
    private GeneroFilter() {
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
