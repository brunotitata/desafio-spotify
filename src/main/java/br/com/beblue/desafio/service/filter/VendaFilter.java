package br.com.beblue.desafio.service.filter;

import java.time.LocalDate;

public class VendaFilter {

    private LocalDate startDate;
    private LocalDate endDate;
    private String orderBy;
    private Integer page;
    private Integer size;
    private String direction;

    public VendaFilter(LocalDate startDate, LocalDate endDate, String orderBy,
            Integer page, Integer size, String direction) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderBy = orderBy;
        this.page = page;
        this.size = size;
        this.direction = direction;
    }

    @SuppressWarnings("unused")
    private VendaFilter() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
