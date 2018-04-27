package ru.myori.model;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/*@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = SummaryOrder.GET_SUMMARY, query = "SELECT op.product, SUM(volume) FROM OrderProduct op GROUP BY op.product")
})*/


public class SummaryOrder extends AbstractBaseEntity{

//    public static final String GET_SUMMARY="SummaryOrder.getAll";


    private Product product;

    private int volume;

    public SummaryOrder() {
    }

    public SummaryOrder(Product product, int volume) {
        this.product = product;
        this.volume = volume;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
